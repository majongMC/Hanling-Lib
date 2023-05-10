package majongmc.hllib.mixin.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import majongmc.hllib.common.event.EventBus;
import majongmc.hllib.common.event.PlayerEvent.PlayerLoggedInEvent;
import majongmc.hllib.common.event.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

@Mixin(PlayerList.class)
public class PlayerListMixin {
	@Inject(at = @At("TAIL"), method = "placeNewPlayer")
	   private void placeNewPlayer(Connection connection, ServerPlayer serverPlayer,CallbackInfo info) {
		   EventBus.EVENT_BUS.post(new PlayerLoggedInEvent(serverPlayer));
	   }
	@Inject(at = @At("HEAD"), method = "remove")
	   private void remove(ServerPlayer serverPlayer,CallbackInfo info) {
		   EventBus.EVENT_BUS.post(new PlayerLoggedOutEvent(serverPlayer));
	   }
}
