package majongmc.hllib.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import majongmc.hllib.client.render.Color;
import majongmc.hllib.client.render.RenderShapeInGUI;
import majongmc.hllib.mixin.client.ButtonBuilderAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
@Environment(value=EnvType.CLIENT)
public class BetterButton extends Button{
	protected BetterButton(BetterButton.Builder builder,int i, int j, int k, int l, Component component, OnPress onPress,
			CreateNarration createNarration) {
		super(i, j, k, l, component, onPress, createNarration);
		this.radius=builder.radius;
		// TODO Auto-generated constructor stub
	}
	private final int radius;
	
	public static BetterButton.Builder builder(Component p_254439_, Button.OnPress p_254567_) {
	      return new BetterButton.Builder(p_254439_, p_254567_);
	}
	@Override
	public void renderWidget(PoseStack p_275468_, int p_275505_, int p_275674_, float p_275696_) {
	      Minecraft minecraft = Minecraft.getInstance();
		  RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		  RenderShapeInGUI.renderRoundedRectangle(p_275468_, this.getX(), this.getY(), this.getWidth(), this.getHeight(), radius, new Color(50,50,50,128));
	      if(this.isHovered)
	    	  RenderShapeInGUI.renderRoundedRectangleFrame(p_275468_, this.getX(), this.getY(), this.getWidth(), this.getHeight(), 1, radius, Color.WHITE);
	      int i =this.active ? 0xFFFFFF : 0xA0A0A0;
	      this.renderString(p_275468_, minecraft.font, i | Mth.ceil(this.alpha * 255.0F) << 24);
	   }
	@Environment(value=EnvType.CLIENT)
	public static class Builder extends Button.Builder{
	    private int radius=8;
	    public Builder(Component p_254097_, net.minecraft.client.gui.components.Button.OnPress p_253761_) {
			super(p_254097_, p_253761_);
		}
	    public BetterButton.Builder radius(int radius) {
	    	 this.radius=radius;
	         return this;
	      }
		public BetterButton build() {
			ButtonBuilderAccessor accessor=(ButtonBuilderAccessor)this;
			BetterButton button = new BetterButton(this,accessor.x(), accessor.y(), accessor.width(), accessor.height(), accessor.message(), accessor.onPress(), accessor.createNarration());
            button.setTooltip(accessor.tooltip());
            return button;
	    }
	}
}
