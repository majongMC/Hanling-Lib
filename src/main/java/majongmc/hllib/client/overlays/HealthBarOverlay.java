package majongmc.hllib.client.overlays;

import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import majongmc.hllib.client.render.Color;
import majongmc.hllib.client.render.RenderShapeInGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;

public class HealthBarOverlay extends GuiComponent{
	private final int w;
    private final int h;
    private PoseStack PoseStack;
	public HealthBarOverlay(PoseStack PoseStack) {
		this.w = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.h = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.PoseStack = PoseStack;
	}
	public void render(float percentage,float buffer,Component name,@Nullable Component subname,Color base,Color bufferc,Color bar) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		PoseStack.scale(2.0F, 2.0F, 2.0F);
    	drawCenteredString(PoseStack,Minecraft.getInstance().font, name,(int)(0.25F*w),(int)(0.025F*h),16777215);
    	PoseStack.scale(0.5F, 0.5F, 0.5F);
    	if(subname!=null)
    		drawCenteredString(PoseStack,Minecraft.getInstance().font, subname,(int)(0.5F*w),(int)(0.01F*h),16777215);
    	RenderShapeInGUI.precisefill(PoseStack,this.w*0.35F, this.h*0.1F,this.w*0.65F, this.h*0.1F+2F, base.getRGB());
    	RenderShapeInGUI.precisefill(PoseStack,this.w*0.35F, this.h*0.1F,this.w*(0.35F+0.3F*buffer), this.h*0.1F+2F, bufferc.getRGB());
    	RenderShapeInGUI.precisefill(PoseStack,this.w*0.35F, this.h*0.1F,this.w*(0.35F+0.3F*percentage), this.h*0.1F+2F, bar.getRGB());
    }
}
