package majongmc.hllib.common.init;

import majongmc.hllib.common.event.EventBus;
import majongmc.hllib.common.tickutils.TickManager;

public class EventRegister {
	public static void register(EventBus bus) {
		bus.addListener(TickManager::onServerTick);
	}
}
