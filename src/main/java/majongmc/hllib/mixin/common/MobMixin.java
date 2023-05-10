package majongmc.hllib.mixin.common;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import majongmc.hllib.common.event.EventBus;
import majongmc.hllib.common.event.LivingSetAttackTargetEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

@Mixin(Mob.class)
public class MobMixin {
	@Inject(at = @At("HEAD"), method = "setTarget")
	private void setTarget(@Nullable LivingEntity livingEntity,CallbackInfo info) {
		if(livingEntity!=null)
			EventBus.EVENT_BUS.post(new LivingSetAttackTargetEvent((Mob)(Object)this,livingEntity));
	}
}
