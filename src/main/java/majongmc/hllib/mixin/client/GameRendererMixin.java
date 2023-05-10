package majongmc.hllib.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import majongmc.hllib.client.event.ViewportEvent;
import majongmc.hllib.common.event.EventBus;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
	@Shadow
	private final Camera mainCamera = new Camera();
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;setup(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/world/entity/Entity;ZZF)V", shift = At.Shift.AFTER), method = "renderLevel")
	private void renderLevel(float f, long l, PoseStack poseStack,CallbackInfo info) {
		Camera c = this.mainCamera;
		ViewportEvent.ComputeCameraAngles event=new ViewportEvent.ComputeCameraAngles((GameRenderer)(Object)this, c, f, c.getYRot(), c.getXRot(), 0);
		EventBus.EVENT_BUS.post(event);
		((CameraAccessor)c).setyRot(event.getYaw());
		((CameraAccessor)c).setxRot(event.getPitch());
		poseStack.mulPose(Axis.ZP.rotationDegrees(event.getRoll()));
	}
}
