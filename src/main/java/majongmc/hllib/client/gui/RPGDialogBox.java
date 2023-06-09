package majongmc.hllib.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.joml.Matrix4f;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;

import majongmc.hllib.client.render.Color;
import majongmc.hllib.client.render.RenderShapeInGUI;
import majongmc.hllib.client.util.ClientUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
/**
 * <p>RPG风格的对话框，最左侧为生物，中间为对话框，右侧为按钮，继承自Screen</p>
 * <p>使用时先调用builder()以获取Builder，然后在builder中设置相关参数，然后调用build构建</p>
 * <p>对话框也支持热更新，只需要调用update方法并传入Builder即可，参数只有指定过的会被覆盖，除了按钮和下一页，按钮和下一页每一次更新会被清空</p>
 * */
@Environment(value=EnvType.CLIENT)
public class RPGDialogBox extends Screen{
	private Builder builder;
	private Builder lastupdate;
	private final Color GUIBGC=new Color(50,50,50,128);
	private Component name;
	private Button next;
	private Button[] buttons=new Button[3];
	private LivingEntity entity;
	private float frame;
	private int speed=5;
	private String text;
	private String splited;
	private boolean initing;
	private List<FormattedCharSequence> cachedPageComponents;
	private Consumer<RPGDialogBox> onclose=(box)->{};
	private boolean canclose=true;
	private boolean pausegame=true;
	protected RPGDialogBox(Builder builder) {
		super(builder.title==null? Component.translatable(""):builder.title);
		this.builder=builder;
	}
	@Override
	protected void init() {
		buttons=new Button[3];
		next=null;
		this.name=builder.title==null? Component.translatable(""):builder.title;
		this.entity=builder.entity;
		if(builder.text!=null)
			this.text=builder.text;
		else
			this.text="";
		if(builder.canclose!=null) {
			this.canclose=builder.canclose;
		}
		if(builder.pausegame!=null) {
			this.pausegame=builder.pausegame;
		}
		if(builder.onclose!=null) {
			this.onclose=builder.onclose;
		}
		if(builder.nextpage!=null) {
			this.next = BetterButton.builder(Component.translatable("next"), (button) -> {builder.nextpage.onPress(this, button);}).radius(4).bounds((int)(this.width*0.25), (int)(this.height*0.6), (int)(this.width*0.5), (int)(this.height*0.3)).build();
			this.addRenderableWidget(this.next);
		}
		for(int i=0;i<builder.buttontitles.size();i++) {
			ButtonAction action=builder.buttonactions.get(i);
			buttons[i]=BetterButton.builder(builder.buttontitles.get(i), (button) -> {action.onPress(this, button);}).radius(4).bounds((int)(this.width*0.8), (int)(this.height*(0.63+0.1*i)),(int)(this.width*0.15), (int)(this.height*0.05)).build();
			this.addRenderableWidget(buttons[i]);
		}
		this.refreash();
		if(this.lastupdate!=null)
			this.update(lastupdate);
	}
	public static Builder builder() {
	      return new Builder();
	}
	public void update(Builder builder) {
		this.lastupdate=builder;
		if(builder.title!=null)
			this.name=builder.title;
		if(builder.entity!=null)
			this.entity=builder.entity;
		if(builder.text!=null)
			this.text=builder.text;
		if(builder.canclose!=null) {
			this.canclose=builder.canclose;
		}
		if(builder.pausegame!=null) {
			this.pausegame=builder.pausegame;
		}
		if(builder.onclose!=null) {
			this.onclose=builder.onclose;
		}
		if(this.next!=null)
			this.removeWidget(next);
		if(builder.nextpage!=null) {
			this.next = BetterButton.builder(Component.translatable("next"), (button) -> {builder.nextpage.onPress(this, button);}).radius(4).bounds((int)(this.width*0.25), (int)(this.height*0.6), (int)(this.width*0.5), (int)(this.height*0.3)).build();
			this.addRenderableWidget(this.next);
		}else {
			this.next=null;
		}
		for(int i=0;i<3;i++) {
			if(buttons[i]!=null) {
				this.removeWidget(buttons[i]);
				buttons[i]=null;
			}
		}
		for(int i=0;i<builder.buttontitles.size();i++) {
			ButtonAction action=builder.buttonactions.get(i);
			buttons[i]=BetterButton.builder(builder.buttontitles.get(i), (button) -> {action.onPress(this, button);}).radius(4).bounds((int)(this.width*0.8), (int)(this.height*(0.63+0.1*i)),(int)(this.width*0.15), (int)(this.height*0.05)).build();
			this.addRenderableWidget(buttons[i]);
		}
		this.refreash();
	}
	@Override
	public boolean shouldCloseOnEsc() {
	      return canclose;
	}
	@Override
	public void onClose() {
		this.onclose.accept(this);
		super.onClose();
	}
	@Override
	public boolean isPauseScreen() {
	      return pausegame;
	}
	@Override
	public void render(PoseStack PoseStack, int mouseX, int mouseY, float partialTicks) {
		if((int)((frame)/speed)<text.length()) {
			this.splited=text.substring(0, (int)((frame)/speed));
		}else {
			this.splited=text;
			if(initing)
				updatecachedPageComponents();
			this.initing=false;
		}
		if(initing) {
			updatecachedPageComponents();
		}
		if(entity!=null)
			InventoryScreen.renderEntityInInventoryFollowsMouse(PoseStack, (int)(this.width*0.15), (int)(this.height*0.9),50, (int)(this.width*0.15)-mouseX, (int)(this.height*0.7)-mouseY, entity);
		RenderShapeInGUI.renderRoundedRectangle(PoseStack, (int)(this.width*0.25), (int)(this.height*0.6), (int)(this.width*0.5), (int)(this.height*0.3), 8, GUIBGC);
		int a=8;
		if(!this.name.getString().equals("")) {
			fill(PoseStack, (int)(this.width*0.25)+17, (int)(this.height*0.6)+17, (int)(this.width*0.75)-17, (int)(this.height*0.6)+18, Color.WHITE.getRGB());
			drawCenteredString(PoseStack, font, this.name, (int)(this.width*0.5), (int)(this.height*0.6)+8, Color.WHITE.getRGB());
			a=21;
		}
		for(int l = 0; l < cachedPageComponents.size(); ++l) {
			FormattedCharSequence formattedcharsequence = cachedPageComponents.get(l);
			this.font.draw(PoseStack, formattedcharsequence, (int)(this.width*0.25)+17, (int)(this.height*0.6)+a+ l * 9, Color.WHITE.getRGBNOAlpha());
		}
		if(!initing) {
			if(next!=null)
				rendernextpage(PoseStack);
			for(int i=0;i<3;i++)
				if(buttons[i]!=null)
					buttons[i].render(PoseStack, mouseX, mouseY, partialTicks);
		}
		frame=frame+1/ClientUtils.fpsratio();
	}
	public void refreash() {
		this.frame=0;
		this.initing=true;
	}
	private void updatecachedPageComponents() {
		FormattedText formattedtext =FormattedText.of(splited);
		cachedPageComponents=this.font.split(formattedtext, (int)(this.width*0.5)-34);
	}
	private void rendernextpage(PoseStack PoseStack) {
		float x=this.width*0.5F;
		float y=this.height*0.9F-10+1F*Mth.sin(Mth.PI/15*frame);
		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionColorShader);
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		Matrix4f matrix4f =PoseStack.last().pose();
		bufferbuilder.begin(VertexFormat.Mode.TRIANGLES, DefaultVertexFormat.POSITION_COLOR);
		bufferbuilder.vertex(matrix4f, x,y+5, 0).color(1F,1F,1F,1F).endVertex();
		bufferbuilder.vertex(matrix4f, x+5,y, 0).color(1F,1F,1F,1F).endVertex();
	    bufferbuilder.vertex(matrix4f, x-5,y, 0).color(1F,1F,1F,1F).endVertex();
	    BufferUploader.drawWithShader(bufferbuilder.end());
		RenderSystem.disableBlend();
	}
	@Environment(value=EnvType.CLIENT)
	public interface ButtonAction {
		void onPress(RPGDialogBox box,Button button);
	}
	/**
	 * <p>RPGDialogBox的构建器，通过RPGDialogBox.builder()以获取，可调用build方法以直接构建RPGDialogBox，也可直接在update方法中传入以热更新RPGDialogBox</p>
	 * */
	@Environment(value=EnvType.CLIENT)
	public static class Builder {
		private Component title;
		private LivingEntity entity;
		private String text;
		private ButtonAction nextpage;
		private List<ButtonAction> buttonactions=new ArrayList<>();
		private List<Component> buttontitles=new ArrayList<>();
		private Consumer<RPGDialogBox> onclose;
		private Boolean canclose;
		private Boolean pausegame;
		/**
		 * <p>设置RPGDialogBox的标题</p>
		 * */
		public Builder title(Component title) {
	    	 this.title=title;
	         return this;
	    }
		/**
		 * <p>设置左侧渲染的实体</p>
		 * */
		public Builder entity(LivingEntity entity) {
	    	 this.entity=entity;
	         return this;
	    }
		/**
		 * <p>设置对话框内容，支持格式化代码（颜色代码），也可传入Component</p>
		 * */
		public Builder text(String text) {
	    	 this.text=text;
	         return this;
	    }
		/**
		 * <p>设置对话框内容，支持格式化代码（颜色代码），也可传入Component</p>
		 * */
		public Builder text(Component text) {
	    	 this.text=text.getString();
	         return this;
	    }
		/**
		 * <p>设置点击下一页箭头时的行为（只有指定了这一项才有下一页箭头）</p>
		 * */
		public Builder nextpage(ButtonAction nextpage) {
	    	 this.nextpage=nextpage;
	         return this;
	    }
		/**
		 * <p>能否通过ESC键关闭（不影响直接调用onClose关闭）</p>
		 * */
		public Builder canclose(Boolean canclose) {
	    	 this.canclose=canclose;
	         return this;
	    }
		/**
		 * <p>能否在单人游戏下暂停游戏</p>
		 * */
		public Builder canpause(Boolean pausegame) {
	    	 this.pausegame=pausegame;
	         return this;
	    }
		/**
		 * <p>关闭时触发传入的Consumer</p>
		 * */
		public Builder onclose(Consumer<RPGDialogBox> onclose) {
	    	 this.onclose=onclose;
	         return this;
	    }
		/**
		 * <p>添加按钮，最多添加3个，传入参数为按钮的标题，按钮的行为</p>
		 * */
		public Builder addButton(Component title,ButtonAction action) {
	    	 if(buttontitles.size()>=3)
	    		 return this;
	    	 else {
	    		 buttontitles.add(title);
	    		 buttonactions.add(action);
	    		 return this;
	    	 }
	    }
		/**
		 * <p>构建RPGDialogBox</p>
		 * */
		public RPGDialogBox build() {
			return new RPGDialogBox(this);
		}
	}
}
