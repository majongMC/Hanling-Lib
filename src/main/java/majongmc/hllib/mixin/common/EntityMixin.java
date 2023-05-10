package majongmc.hllib.mixin.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import majongmc.hllib.common.iforgeport.MiniIForgeEntityInject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
@Mixin(Entity.class)
public class EntityMixin implements MiniIForgeEntityInject{

	private CompoundTag persistentData_h;
	   @Override
	   public CompoundTag getPersistentData_h() {
	      if (persistentData_h == null)
	         persistentData_h = new CompoundTag();
	      return persistentData_h;
	   }
	   @Inject(at = @At("HEAD"), method = "load")
	   private void load(CompoundTag compoundTag,CallbackInfo info) {
		   if (compoundTag.contains("HllibData", 10)) persistentData_h = compoundTag.getCompound("HllibData");
	   }
	   @Inject(at = @At("HEAD"), method = "saveWithoutId")
	   private void saveWithoutId(CompoundTag compoundTag,CallbackInfoReturnable<CompoundTag> info) {
		   if (persistentData_h != null) compoundTag.put("HllibData", persistentData_h.copy());
	   }
}
