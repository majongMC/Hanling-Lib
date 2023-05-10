package majongmc.hllib.common;

import majongmc.hllib.common.event.EventBus;
import majongmc.hllib.common.init.EventRegister;
import majongmc.hllib.common.init.FabricEventRegister;
import net.fabricmc.api.ModInitializer;

public class HanlingLib implements ModInitializer{
	public static final String MOD_ID="hllib";
	@Override
	public void onInitialize() {
		EventBus bus=EventBus.getModEventBus();
		EventRegister.register(bus);
		FabricEventRegister.register();
		//debug
	}

}
