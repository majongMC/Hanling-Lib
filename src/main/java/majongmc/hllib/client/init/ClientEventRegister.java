package majongmc.hllib.client.init;

import majongmc.hllib.client.effects.CameraShake;
import majongmc.hllib.client.overlays.HasTagAutoRender;
import majongmc.hllib.client.overlays.HealthBarRenderer;
import majongmc.hllib.common.event.EventBus;
import majongmc.hllib.common.tickutils.TickManager;

public class ClientEventRegister {
	public static void register(EventBus bus) {
		bus.addListener(TickManager::onClientTick);
		bus.addListener(CameraShake::onSetupCamera);
		bus.addListener(HasTagAutoRender::onEntityTick);
		bus.addListener(HealthBarRenderer::onOverlayRender);
	}
}
