package majongmc.hllib.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import majongmc.hllib.client.render.Color;
import majongmc.hllib.client.render.RenderShapeInGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class BetterButton extends Button{
	private final int radius;
	protected BetterButton(Button.Builder builder) {
		super(builder);
		this.radius=((BetterButton.Builder)builder).radius;
	}
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
	      int i = getFGColor();
	      this.renderString(p_275468_, minecraft.font, i | Mth.ceil(this.alpha * 255.0F) << 24);
	   }
	@OnlyIn(Dist.CLIENT)
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
	         return build(BetterButton::new);
	    }
	    public BetterButton build(java.util.function.Function<Button.Builder, Button> builder) {
	         return (BetterButton)builder.apply(this);
	    }
	}
}
