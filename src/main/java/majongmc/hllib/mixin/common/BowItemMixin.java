package majongmc.hllib.mixin.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import majongmc.hllib.common.event.HllibEventFactory;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

@Mixin(BowItem.class)
public class BowItemMixin {
	@Inject(method = "use", at = @At("HEAD"), cancellable = true)
	private void use(Level level, Player player, InteractionHand interactionHand,CallbackInfoReturnable<InteractionResultHolder<ItemStack>> info) {
		ItemStack itemstack = player.getItemInHand(interactionHand);
        boolean flag = !player.getProjectile(itemstack).isEmpty();
        InteractionResultHolder<ItemStack> ret = HllibEventFactory.onArrowNock(itemstack, level, player, interactionHand, flag);
        if (ret != null) info.setReturnValue(ret);
	}
	@Inject(method = "releaseUsing", at = @At("HEAD"), cancellable = true)
	private void releaseUsing(ItemStack p_40667_, Level p_40668_, LivingEntity p_40669_, int p_40670_,CallbackInfo info) {
		if (p_40669_ instanceof Player player) {
	         boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, p_40667_) > 0;
	         ItemStack itemstack = player.getProjectile(p_40667_);

	         int i = ((BowItem)(Object)this).getUseDuration(p_40667_) - p_40670_;
	         i = HllibEventFactory.onArrowLoose(p_40667_, p_40668_, player, i, !itemstack.isEmpty() || flag);
	         if (i < 0) return;
		}
	}
}
