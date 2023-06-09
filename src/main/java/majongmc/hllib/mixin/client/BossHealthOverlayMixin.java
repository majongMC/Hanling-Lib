package majongmc.hllib.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import majongmc.hllib.client.overlays.HealthBarRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.BossHealthOverlay;

@Mixin(BossHealthOverlay.class)
public class BossHealthOverlayMixin {
	@Inject(at = @At("HEAD"), method = "render")
	private void renderstart(PoseStack poseStack,CallbackInfo info) {
		poseStack.pushPose();
		if(HealthBarRenderer.rendering)
			poseStack.translate(0F, 0.1F*Minecraft.getInstance().getWindow().getGuiScaledHeight(), 0F);
	}
	@Inject(at = @At("TAIL"), method = "render")
	private void renderfinish(PoseStack poseStack,CallbackInfo info) {
		poseStack.popPose();
	}
}
