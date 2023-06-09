package majongmc.hllib.common.util;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

public class BiomeUtil {
	public static String getBiomeName(Biome biome,Level world) {
		ResourceLocation biomeBaseKey = world.registryAccess().registryOrThrow(Registries.BIOME).getKey(biome);
		String key = Util.makeDescriptionId("biome", biomeBaseKey);
		return Component.translatable(key).getString();
	}
}
