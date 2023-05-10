package majongmc.hllib.mixin.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import majongmc.hllib.common.event.EventBus;
import majongmc.hllib.common.event.LivingEntityUseItemEvent;
import majongmc.hllib.common.event.LivingEvent.LivingTickEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@Shadow
	protected ItemStack useItem;
	@Inject(at = @At("HEAD"), method = "tick", cancellable = true)
	private void tick(CallbackInfo info) {
		if(EventBus.EVENT_BUS.post(new LivingTickEvent((LivingEntity)(Object)this)))
			info.cancel();
	}
	@Inject(at = @At("HEAD"), method = "startUsingItem", cancellable = true)
	private void startUsingItem(InteractionHand interactionHand,CallbackInfo info) {
		 ItemStack itemstack = ((LivingEntity)(Object)this).getItemInHand(interactionHand);
	      if (!itemstack.isEmpty() && !((LivingEntity)(Object)this).isUsingItem()) {
	    	  if(EventBus.EVENT_BUS.post(new LivingEntityUseItemEvent.Start((LivingEntity)(Object)this,itemstack,itemstack.getUseDuration())))
	  			info.cancel();
	      }
	}
	@Inject(at = @At("HEAD"), method = "releaseUsingItem")
	private void releaseUsingItem(CallbackInfo info) {
		if (!this.useItem.isEmpty()) {
			EventBus.EVENT_BUS.post(new LivingEntityUseItemEvent.Stop((LivingEntity)(Object)this,this.useItem,((LivingEntity)(Object)this).getUseItemRemainingTicks()));
		}
	}
}
