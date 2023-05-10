package majongmc.hllib.common.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class AttackEntityEvent extends PlayerEvent{
	private final Entity target;
    public AttackEntityEvent(Player player, Entity target)
    {
        super(player);
        this.target = target;
    }

    public Entity getTarget()
    {
        return target;
    }
}
