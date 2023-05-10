package majongmc.hllib.common.init;

import majongmc.hllib.common.event.EventBus;
import majongmc.hllib.common.event.LivingDamageEvent;
import majongmc.hllib.common.event.LivingDeathEvent;
import majongmc.hllib.common.event.PlayerEvent;
import majongmc.hllib.common.event.ServerTickEvent;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class FabricEventRegister {
	public static void register() {
		ServerTickEvents.END_SERVER_TICK.register((server)->{
			EventBus.EVENT_BUS.post(new ServerTickEvent(server));
		});
		ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive)->{
			EventBus.EVENT_BUS.post(new PlayerEvent.Clone(newPlayer, oldPlayer, !alive));
		});
		ServerLivingEntityEvents.ALLOW_DEATH.register((entity, damageSource, damageAmount)->{
			return !EventBus.EVENT_BUS.post(new LivingDeathEvent(entity,damageSource));
		});
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount)->{
			return !EventBus.EVENT_BUS.post(new LivingDamageEvent(entity,source,amount));
		});
	}
}
