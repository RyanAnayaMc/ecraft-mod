package dev.night.ecraft.world.gen;

import cn.leolezury.eternalstarlight.common.data.ESBiomes;
import dev.night.ecraft.Ecraft;
import dev.night.ecraft.world.EPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

import java.util.function.Predicate;

public class EOreGeneration {
    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing ore generation");

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                EPlacedFeatures.MAGIC_ORE_PLACED_OVERWORLD
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                EPlacedFeatures.TUNGSTEN_ORE_PLACED_OVERWORLD
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                EPlacedFeatures.DEMONITE_ORE_PLACED_OVERWORLD
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                EPlacedFeatures.HELLSTONE_ORE_PLACED_NETHER
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                EPlacedFeatures.COBALT_ORE_PLACED_OVERWORLD
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                EPlacedFeatures.ORICHALCUM_ORE_PLACED_OVERWORLD
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                EPlacedFeatures.TITANIUM_ORE_PLACED_OVERWORLD
        );

        BiomeModifications.addFeature(
                foundInEternalStarlight(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                EPlacedFeatures.VERDANT_ORE_PLACED_ETERNAL_STARLIGHT
        );

        BiomeModifications.addFeature(
                foundInEternalStarlight(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                EPlacedFeatures.DETONIUM_ORE_PLACED_ETERNAL_STARLIGHT
        );

        BiomeModifications.addFeature(
                foundInEternalStarlight(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                EPlacedFeatures.DETONIUM_ORE_PLACED_ETERNAL_STARLIGHT
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                EPlacedFeatures.AETHERIUM_ORE_PLACED_NETHER
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                EPlacedFeatures.AETHERIUM_ORE_PLACED_END
        );
    }

    static Predicate<BiomeSelectionContext> foundInEternalStarlight() {
        return BiomeSelectors.includeByKey(ESBiomes.STARLIGHT_DENSE_FOREST, ESBiomes.STARLIGHT_FOREST, ESBiomes.STARLIGHT_PERMAFROST_FOREST,
                ESBiomes.DARK_SWAMP, ESBiomes.SCARLET_FOREST, ESBiomes.TORREYA_FOREST, ESBiomes.CRYSTALLIZED_DESERT, ESBiomes.SHIMMER_RIVER,
                ESBiomes.ETHER_RIVER, ESBiomes.ETHER_RIVER, ESBiomes.STARLIT_SEA, ESBiomes.SPIRAL_KELP_FOREST, ESBiomes.THE_ABYSS, ESBiomes.WARM_SHORE);
    }
}
