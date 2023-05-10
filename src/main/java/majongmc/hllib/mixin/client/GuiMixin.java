package majongmc.hllib.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import majongmc.hllib.client.event.RenderGuiOverlayEvent;
import majongmc.hllib.common.event.EventBus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

@Mixin(Gui.class)
public class GuiMixin {
	@Inject(at = @At("TAIL"), method = "render")
	private void render(PoseStack poseStack, float f,CallbackInfo info) {
		EventBus.EVENT_BUS.post(new RenderGuiOverlayEvent.Post(Minecraft.getInstance().getWindow(), poseStack, f));
	}
}
