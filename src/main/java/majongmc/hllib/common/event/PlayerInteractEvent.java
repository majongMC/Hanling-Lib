package majongmc.hllib.common.event;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.base.Preconditions;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
/**
 * <p>注意：相比于Forge没有getSide()方法</p>
 * */
public class PlayerInteractEvent extends PlayerEvent{
	private final InteractionHand hand;
    private final BlockPos pos;
    @Nullable
    private final Direction face;
    private InteractionResult cancellationResult = InteractionResult.PASS;

    private PlayerInteractEvent(Player player, InteractionHand hand, BlockPos pos, @Nullable Direction face)
    {
        super(Preconditions.checkNotNull(player, "Null player in PlayerInteractEvent!"));
        this.hand = Preconditions.checkNotNull(hand, "Null hand in PlayerInteractEvent!");
        this.pos = Preconditions.checkNotNull(pos, "Null position in PlayerInteractEvent!");
        this.face = face;
    }
    @NotNull
    public InteractionHand getHand()
    {
        return hand;
    }
    @NotNull
    public ItemStack getItemStack()
    {
        return getEntity().getItemInHand(hand);
    }
    @NotNull
    public BlockPos getPos()
    {
        return pos;
    }
    @Nullable
    public Direction getFace()
    {
        return face;
    }
    public Level getLevel()
    {
        return getEntity().level;
    }
    public InteractionResult getCancellationResult()
    {
        return cancellationResult;
    }
    public void setCancellationResult(InteractionResult result)
    {
        this.cancellationResult = result;
    }
    public static class EntityInteract extends PlayerInteractEvent
    {
        private final Entity target;

        public EntityInteract(Player player, InteractionHand hand, Entity target)
        {
            super(player, hand, target.blockPosition(), null);
            this.target = target;
        }

        public Entity getTarget()
        {
            return target;
        }
    }
}
