package majongmc.hllib.common.iforgeport;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class MiniIForgeItemStackApi {
	/**
	 * <p>同IForgeItemStack下的serializeNBT</p>
	 * */
	public static CompoundTag serializeNBT(ItemStack stack)
    {
        CompoundTag ret = new CompoundTag();
        stack.save(ret);
        return ret;
    }
}
