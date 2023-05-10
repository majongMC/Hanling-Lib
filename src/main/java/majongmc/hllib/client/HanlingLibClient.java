package majongmc.hllib.client;

import majongmc.hllib.client.init.ClientEventRegister;
import majongmc.hllib.client.init.ClientFabricEventRegister;
import majongmc.hllib.common.event.EventBus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
@Environment(value=EnvType.CLIENT)
public class HanlingLibClient implements ClientModInitializer{
	@Override
	public void onInitializeClient() {
		EventBus bus=EventBus.getModEventBus();
		ClientEventRegister.register(bus);
		ClientFabricEventRegister.register();
	}

}
