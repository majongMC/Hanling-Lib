package majongmc.hllib.common.iforgeport;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface MiniIForgeItem {
	default boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity)
    {
        return false;
    }
}
