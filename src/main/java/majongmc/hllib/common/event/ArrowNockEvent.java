package majongmc.hllib.common.event;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
/**
 * <p>注意：目前Fabric端弩不会触发本事件</p>
 * <p>如果要让自己的弓或弩触发事件，需要自己触发，触发方式参考Forge，只不过将ForgeEventFactory改成{@link HllibEventFactory}</p>
 * */
public class ArrowNockEvent extends PlayerEvent{
	private final ItemStack bow;
    private final InteractionHand hand;
    private final Level level;
    private final boolean hasAmmo;
    private InteractionResultHolder<ItemStack> action;

    public ArrowNockEvent(Player player, @NotNull ItemStack item, InteractionHand hand, Level level, boolean hasAmmo)
    {
        super(player);
        this.bow = item;
        this.hand = hand;
        this.level = level;
        this.hasAmmo = hasAmmo;
    }

    @NotNull
    public ItemStack getBow() { return this.bow; }
    public Level getLevel() { return this.level; }
    public InteractionHand getHand() { return this.hand; }
    public boolean hasAmmo() { return this.hasAmmo; }
    public InteractionResultHolder<ItemStack> getAction()
    {
        return this.action;
    }

    public void setAction(InteractionResultHolder<ItemStack> action)
    {
        this.action = action;
    }
}
