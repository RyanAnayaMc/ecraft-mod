package dev.night.ecraft.world;

import dev.night.ecraft.Ecraft;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class EPlacedFeatures {
    public static final RegistryKey<PlacedFeature> MAGIC_ORE_PLACED_OVERWORLD = registerKey("magic_ore_overworld");
    public static final RegistryKey<PlacedFeature> TUNGSTEN_ORE_PLACED_OVERWORLD = registerKey("tungsten_ore_overworld");
    public static final RegistryKey<PlacedFeature> DEMONITE_ORE_PLACED_OVERWORLD = registerKey("demonite_ore_overworld");
    public static final RegistryKey<PlacedFeature> HELLSTONE_ORE_PLACED_NETHER = registerKey("hellstone_ore_nether");
    public static final RegistryKey<PlacedFeature> COBALT_ORE_PLACED_OVERWORLD = registerKey("cobalt_ore_overworld");
    public static final RegistryKey<PlacedFeature> ORICHALCUM_ORE_PLACED_OVERWORLD = registerKey("orichalcum_ore_overworld");
    public static final RegistryKey<PlacedFeature> TITANIUM_ORE_PLACED_OVERWORLD = registerKey("titanium_ore_overworld");
    public static final RegistryKey<PlacedFeature> VERDANT_ORE_PLACED_ETERNAL_STARLIGHT = registerKey("verdant_ore_eternal_starlight");
    public static final RegistryKey<PlacedFeature> DETONIUM_ORE_PLACED_ETERNAL_STARLIGHT = registerKey("detonium_ore_eternal_starlight");
    public static final RegistryKey<PlacedFeature> AETHERIUM_ORE_PLACED_NETHER = registerKey("aetherium_ore_nether");
    public static final RegistryKey<PlacedFeature> AETHERIUM_ORE_PLACED_END = registerKey("aetherium_ore_end");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context,
                MAGIC_ORE_PLACED_OVERWORLD,
                configuredFeatures.getOrThrow(EConfiguredFeatures.MAGIC_ORE_OVERWORLD),
                EOrePlacement.modifiersWithCount(
                        9,
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(-48), YOffset.fixed(48)
                        )
                )
        );

        register(context,
                TUNGSTEN_ORE_PLACED_OVERWORLD,
                configuredFeatures.getOrThrow(EConfiguredFeatures.TUNGSTEN_ORE_OVERWORLD),
                EOrePlacement.modifiersWithCount(
                        14,
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(-50), YOffset.fixed(50)
                        )
                )
        );

        register(context,
                DEMONITE_ORE_PLACED_OVERWORLD,
                configuredFeatures.getOrThrow(EConfiguredFeatures.DEMONITE_ORE_OVERWORLD),
                EOrePlacement.modifiersWithCount(
                        8,
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(-80), YOffset.fixed(80)
                        )
                )
        );

        register(context,
                HELLSTONE_ORE_PLACED_NETHER,
                configuredFeatures.getOrThrow(EConfiguredFeatures.HELLSTONE_ORE_NETHER),
                EOrePlacement.modifiersWithCount(
                        50,
                        HeightRangePlacementModifier.uniform(
                                YOffset.fixed(-100), YOffset.fixed(100)
                        )
                )
        );

        register(context,
                COBALT_ORE_PLACED_OVERWORLD,
                configuredFeatures.getOrThrow(EConfiguredFeatures.COBALT_ORE_OVERWORLD),
                EOrePlacement.modifiersWithCount(
                        10,
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(-40), YOffset.fixed(80)
                        )
                )
        );

        register(context,
                ORICHALCUM_ORE_PLACED_OVERWORLD,
                configuredFeatures.getOrThrow(EConfiguredFeatures.ORICHALCUM_ORE_OVERWORLD),
                EOrePlacement.modifiersWithCount(
                        10,
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(-40), YOffset.fixed(40)
                        )
                )
        );

        register(context,
                TITANIUM_ORE_PLACED_OVERWORLD,
                configuredFeatures.getOrThrow(EConfiguredFeatures.TITANIUM_ORE_OVERWORLD),
                EOrePlacement.modifiersWithCount(
                        10,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-100), YOffset.fixed(0))
                )
        );

        register(context,
                VERDANT_ORE_PLACED_ETERNAL_STARLIGHT,
                configuredFeatures.getOrThrow(EConfiguredFeatures.VERDANT_ORE_ETERNAL_STARLIGHT),
                EOrePlacement.modifiersWithCount(
                        15,
                        HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())
                )
        );

        register(context,
                DETONIUM_ORE_PLACED_ETERNAL_STARLIGHT,
                configuredFeatures.getOrThrow(EConfiguredFeatures.DETONIUM_ORE_ETERNAL_STARLIGHT),
                EOrePlacement.modifiersWithCount(
                        13,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(20))
                )
        );

        register(context,
                AETHERIUM_ORE_PLACED_NETHER,
                configuredFeatures.getOrThrow(EConfiguredFeatures.AETHERIUM_ORE_NETHER),
                EOrePlacement.modifiersWithCount(
                        15,
                        HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())
                )
        );

        register(context,
                AETHERIUM_ORE_PLACED_END,
                configuredFeatures.getOrThrow(EConfiguredFeatures.AETHERIUM_ORE_END),
                EOrePlacement.modifiersWithCount(
                        20,
                        HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.TOP)
                )
        );
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Ecraft.MOD_ID, name));
    }

    private static void register(
            Registerable<PlacedFeature> context,
            RegistryKey<PlacedFeature> key,
            RegistryEntry<ConfiguredFeature<?, ?>> config,
            List<PlacementModifier> modifiers
    ) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
        Registerable<PlacedFeature> context,
        RegistryKey<PlacedFeature> key,
        RegistryEntry<ConfiguredFeature<?, ?>> config,
        PlacementModifier... modifiers
    ) {
        register(context, key, config, List.of(modifiers));
    }
}
