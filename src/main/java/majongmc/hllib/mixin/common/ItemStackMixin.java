package majongmc.hllib.mixin.common;

import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import majongmc.hllib.common.event.EventBus;
import majongmc.hllib.common.event.ItemTooltipEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Inject(at = @At("RETURN"), method = "getTooltipLines", locals = LocalCapture.CAPTURE_FAILHARD)
	private void getTooltipLines(@Nullable Player player, TooltipFlag tooltipFlag,CallbackInfoReturnable<List<Component>> info,List<Component> list) {
		ItemTooltipEvent event = new ItemTooltipEvent((ItemStack)(Object)this, player, list, tooltipFlag);
        EventBus.EVENT_BUS.post(event);
	}
}
