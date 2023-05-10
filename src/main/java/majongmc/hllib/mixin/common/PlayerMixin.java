package majongmc.hllib.mixin.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import majongmc.hllib.common.event.EventBus;
import majongmc.hllib.common.event.HllibEventFactory;
import majongmc.hllib.common.event.PlayerEvent;
import majongmc.hllib.common.event.PlayerInteractEvent;
import majongmc.hllib.common.event.PlayerWakeUpEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(Player.class)
public class PlayerMixin {
	@Shadow
	private final Inventory inventory = new Inventory((Player)(Object)this);
	@Inject(at = @At("HEAD"), method = "stopSleepInBed")
	private void stopSleepInBed(boolean bl, boolean bl2,CallbackInfo info) {
		EventBus.EVENT_BUS.post(new PlayerWakeUpEvent((Player)(Object)this,bl,bl2));
	}
	@Inject(at = @At("HEAD"), method = "interactOn", cancellable = true)
	private void interactOn(Entity entity, InteractionHand interactionHand,CallbackInfoReturnable<InteractionResult> info) {
		if (!((Player)(Object)this).isSpectator()) {
			PlayerInteractEvent.EntityInteract evt = new PlayerInteractEvent.EntityInteract((Player)(Object)this, interactionHand, entity);
	        EventBus.EVENT_BUS.post(evt);
	        InteractionResult cancelResult =evt.isCanceled() ? evt.getCancellationResult() : null;
	        if (cancelResult != null) info.setReturnValue(cancelResult);;
		}
	}
	@Inject(at = @At("RETURN"), method = "hasCorrectToolForDrops", cancellable = true)
	private void hasCorrectToolForDrops(BlockState blockState,CallbackInfoReturnable<Boolean> info) {
		PlayerEvent.HarvestCheck event = new PlayerEvent.HarvestCheck((Player)(Object)this, blockState, !blockState.requiresCorrectToolForDrops() || this.inventory.getSelected().isCorrectToolForDrops(blockState));
        EventBus.EVENT_BUS.post(event);
        info.setReturnValue(event.canHarvest());
	}
	@Inject(at = @At("HEAD"), method = "attack", cancellable = true)
	public void attack(Entity entity,CallbackInfo info) {
		if (!HllibEventFactory.onPlayerAttackTarget((Player)(Object)this, entity)) 
			info.cancel();
	}
}
