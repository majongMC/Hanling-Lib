package majongmc.hllib.common.event;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
/**
 * <p>注意：目前Fabric端弩不会触发本事件</p>
 * <p>如果要让自己的弓或弩触发事件，需要自己触发，触发方式参考Forge，只不过将ForgeEventFactory改成{@link HllibEventFactory}</p>
 * */
public class ArrowLooseEvent extends PlayerEvent{
	private final ItemStack bow;
    private final Level level;
    private final boolean hasAmmo;
    private int charge;

    public ArrowLooseEvent(Player player, @NotNull ItemStack bow, Level level, int charge, boolean hasAmmo)
    {
        super(player);
        this.bow = bow;
        this.level = level;
        this.charge = charge;
        this.hasAmmo = hasAmmo;
    }

    @NotNull
    public ItemStack getBow() { return this.bow; }
    public Level getLevel() { return this.level; }
    public boolean hasAmmo() { return this.hasAmmo; }
    public int getCharge() { return this.charge; }
    public void setCharge(int charge) { this.charge = charge; }
}
