package majongmc.hllib.common.iforgeport;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;

public class MiniIForgeEntityApi {
	public static CompoundTag getPersistentData(Entity entity) {
		return entity.getPersistentData_h();
	}
}
