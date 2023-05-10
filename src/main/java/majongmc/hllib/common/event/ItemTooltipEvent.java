package majongmc.hllib.common.event;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class ItemTooltipEvent extends PlayerEvent{
	private final TooltipFlag flags;
    @NotNull
    private final ItemStack itemStack;
    private final List<Component> toolTip;

    public ItemTooltipEvent(@NotNull ItemStack itemStack, @Nullable Player player, List<Component> list, TooltipFlag flags)
    {
        super(player);
        this.itemStack = itemStack;
        this.toolTip = list;
        this.flags = flags;
    }

    public TooltipFlag getFlags()
    {
        return flags;
    }

    @NotNull
    public ItemStack getItemStack()
    {
        return itemStack;
    }

    public List<Component> getToolTip()
    {
        return toolTip;
    }

    @Override
    @Nullable
    public Player getEntity()
    {
        return super.getEntity();
    }
}
