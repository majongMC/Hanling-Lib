package majongmc.hllib.common.event;

import majongmc.hllib.common.iforgeport.MiniIForgeItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HllibEventFactory {
	public static InteractionResultHolder<ItemStack> onArrowNock(ItemStack item, Level level, Player player, InteractionHand hand, boolean hasAmmo)
    {
        ArrowNockEvent event = new ArrowNockEvent(player, item, hand, level, hasAmmo);
        if (EventBus.EVENT_BUS.post(event))
            return new InteractionResultHolder<ItemStack>(InteractionResult.FAIL, item);
        return event.getAction();
    }
	public static int onArrowLoose(ItemStack stack, Level level, Player player, int charge, boolean hasAmmo)
    {
        ArrowLooseEvent event = new ArrowLooseEvent(player, stack, level, charge, hasAmmo);
        if (EventBus.EVENT_BUS.post(event))
            return -1;
        return event.getCharge();
    }
	public static boolean onPlayerAttackTarget(Player player, Entity target)
    {
        if (EventBus.EVENT_BUS.post(new AttackEntityEvent(player, target))) return false;
        ItemStack stack = player.getMainHandItem();
        return stack.isEmpty() ||!(stack.getItem() instanceof MiniIForgeItem)|| !((MiniIForgeItem)(stack.getItem())).onLeftClickEntity(stack, player, target);
    }
}
