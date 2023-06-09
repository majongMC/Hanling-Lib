package majongmc.hllib.client.overlays;

import majongmc.hllib.common.HanlingLib;
import majongmc.hllib.common.util.BiomeUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class HasTagAutoRender {
	public static final TagKey<EntityType<?>> HAS_HEALTH_BAR =TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(HanlingLib.MOD_ID,"has_healthbar"));
	@SubscribeEvent
	public static void onEntityTick(LivingTickEvent event) {
		if(event.getEntity().level.isClientSide&&event.getEntity().getType().getTags().anyMatch((TagKey<EntityType<?>> t)->t.equals(HAS_HEALTH_BAR))) {
			LivingEntity entity=(LivingEntity) event.getEntity();
			if(entity.blockPosition().getY()>=-64)
				HealthBarAPI.DisplayHealthBar(entity.getHealth()/entity.getMaxHealth(), entity.getName(),Component.translatable("hllib.healthbar.dafaultsubtitle",BiomeUtil.getBiomeName(entity.level.getBiome(entity.blockPosition()).get(),entity.level)));
		}
	}
}
