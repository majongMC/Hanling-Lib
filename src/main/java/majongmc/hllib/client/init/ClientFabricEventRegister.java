package majongmc.hllib.client.init;

import majongmc.hllib.client.event.ClientTickEvent;
import majongmc.hllib.common.event.EventBus;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ClientFabricEventRegister {
	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register((client)->{
			EventBus.EVENT_BUS.post(new ClientTickEvent());
		});
	}
}
