package majongmc.hllib.common.event;

import net.minecraft.world.entity.player.Player;

public class PlayerWakeUpEvent extends PlayerEvent{
	private final boolean wakeImmediately;

    private final boolean updateLevel;

    public PlayerWakeUpEvent(Player player, boolean wakeImmediately, boolean updateLevel)
    {
        super(player);
        this.wakeImmediately = wakeImmediately;
        this.updateLevel = updateLevel;
    }

    public boolean wakeImmediately() { return wakeImmediately; }

    public boolean updateLevel() { return updateLevel; }
}
