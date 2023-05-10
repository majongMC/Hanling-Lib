package majongmc.hllib.common.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public class PlayerEvent extends LivingEvent{
	private final Player player;
	public PlayerEvent(Player player)
    {
		super(player);
        this.player = player;
    }
	@Override
	public Player getEntity()
    {
        return player;
    }
	public static class Clone extends PlayerEvent
    {
        private final Player original;
        private final boolean wasDeath;

        public Clone(Player _new, Player oldPlayer, boolean wasDeath)
        {
            super(_new);
            this.original = oldPlayer;
            this.wasDeath = wasDeath;
        }
        public Player getOriginal()
        {
            return original;
        }
        public boolean isWasDeath()
        {
            return wasDeath;
        }
    }
	public static class HarvestCheck extends PlayerEvent
    {
        private final BlockState state;
        private boolean success;

        public HarvestCheck(Player player, BlockState state, boolean success)
        {
            super(player);
            this.state = state;
            this.success = success;
        }

        public BlockState getTargetBlock() { return this.state; }
        public boolean canHarvest() { return this.success; }
        public void setCanHarvest(boolean success){ this.success = success; }
    }
	public static class PlayerLoggedInEvent extends PlayerEvent {
        public PlayerLoggedInEvent(Player player)
        {
            super(player);
        }
    }
	public static class PlayerLoggedOutEvent extends PlayerEvent {
        public PlayerLoggedOutEvent(Player player)
        {
            super(player);
        }
    }
}
