package majongmc.hllib.common.iforgeport;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
/**
 * <p>部分由IForgeItem接口在原版类下增加的方法在Fabric上的实现，当你需要覆写对应方法时可在自己的物品类中实现这个接口，覆写的方法会自动生效</p>
 * */
public interface MiniIForgeItem {
	default boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity)
    {
        return false;
    }
}
