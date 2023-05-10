package majongmc.hllib.mixin.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import majongmc.hllib.common.event.EventBus;
import majongmc.hllib.common.event.ProjectileImpactEvent;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.HitResult;

@Mixin(Projectile.class)
public class ProjectileMixin {
	@Inject(at = @At("HEAD"), method = "onHit")
	private void onHit(HitResult hitResult,CallbackInfo info) {
		EventBus.EVENT_BUS.post(new ProjectileImpactEvent((Projectile)(Object)this, hitResult));
	}
}
