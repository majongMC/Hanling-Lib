package majongmc.hllib.common.iforgeport;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
/**
 * <p>部分由IForgeItemStack接口在原版类下增加的方法在Fabric上的实现</p>
 * */
public class MiniIForgeItemStackApi {
	/**
	 * <p>同IForgeItemStack下的serializeNBT，只不过是静态的</p>
	 * */
	public static CompoundTag serializeNBT(ItemStack stack)
    {
        CompoundTag ret = new CompoundTag();
        stack.save(ret);
        return ret;
    }
}
