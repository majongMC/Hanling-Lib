package majongmc.hllib.common.iforgeport;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
/**
 * <p>部分由IForgeEntity接口在原版类下增加的方法在Fabric上的实现</p>
 * */
public class MiniIForgeEntityApi {
	/**
	 * <p>同forge下的getPersistentData，只不过是静态的</p>
	 * */
	public static CompoundTag getPersistentData(Entity entity) {
		return entity.getPersistentData_h();
	}
}
