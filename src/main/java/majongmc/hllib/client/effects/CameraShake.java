package majongmc.hllib.client.effects;

import majongmc.hllib.client.util.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class CameraShake {
	@SubscribeEvent
    public static void onSetupCamera(ViewportEvent.ComputeCameraAngles event) {
		ClientUtils.frameID++;
		if(CameraShakeApi.shakeframe>0) {
			Player player=Minecraft.getInstance().player;
			float yaw=player.yHeadRot;
			float pitch=player.xRotO;
			float adjustratio=computeadjustratio();
			event.setPitch(pitch+adjustratio*Mth.sin((float) (CameraShakeApi.shakeframe*2/ClientUtils.fpsratio())));
			event.setYaw(yaw+adjustratio*0.5F*Mth.cos((float) (CameraShakeApi.shakeframe/ClientUtils.fpsratio())));
			CameraShakeApi.shakeframe--;
		}
	}
	private static float computeadjustratio() {
		int alignedsf=(int) (CameraShakeApi.shakeframe/ClientUtils.fpsratio());
		return CameraShakeApi.extent*alignedsf/18.0F;
	}
}
