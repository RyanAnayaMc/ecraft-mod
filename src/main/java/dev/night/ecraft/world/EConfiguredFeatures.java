package dev.night.ecraft.world;

import cn.leolezury.eternalstarlight.common.registry.ESBlocks;
import dev.night.ecraft.Ecraft;
import dev.night.ecraft.block.EBlocks;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class EConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> MAGIC_ORE_OVERWORLD = registerKey("magic_ore_overworld");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TUNGSTEN_ORE_OVERWORLD = registerKey("tungsten_ore_overworld");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEMONITE_ORE_OVERWORLD = registerKey("demonite_ore_overworld");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HELLSTONE_ORE_NETHER = registerKey("hellstone_ore_nether");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COBALT_ORE_OVERWORLD = registerKey("cobalt_ore_overworld");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORICHALCUM_ORE_OVERWORLD = registerKey("orichalcum_ore_overworld");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TITANIUM_ORE_OVERWORLD = registerKey("titanium_ore_overworld");
    public static final RegistryKey<ConfiguredFeature<?, ?>> VERDANT_ORE_ETERNAL_STARLIGHT = registerKey("verdant_ore_eternal_starlight");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DETONIUM_ORE_ETERNAL_STARLIGHT = registerKey("detonium_ore_eternal_starlight");
    public static final RegistryKey<ConfiguredFeature<?, ?>> AETHERIUM_ORE_NETHER = registerKey("aetherium_ore_nether");
    public static final RegistryKey<ConfiguredFeature<?, ?>> AETHERIUM_ORE_END = registerKey("aetherium_ore_end");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest dirtReplacables = new TagMatchRuleTest(BlockTags.DIRT);
        RuleTest andesiteReplacables = new BlockMatchRuleTest(Blocks.ANDESITE);
        RuleTest dioriteReplacables = new BlockMatchRuleTest(Blocks.DIORITE);
        RuleTest graniteReplacables = new BlockMatchRuleTest(Blocks.GRANITE);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest tuffReplacables = new BlockMatchRuleTest(Blocks.TUFF);

        RuleTest netherStoneReplacables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest blackstoneReplaceables = new BlockMatchRuleTest(Blocks.BLACKSTONE);
        RuleTest basaltReplacables = new BlockMatchRuleTest(Blocks.BASALT);

        RuleTest grimstoneReplacables = new BlockMatchRuleTest(ESBlocks.GRIMSTONE.get());
        RuleTest voidstoneReplacables = new BlockMatchRuleTest(ESBlocks.VOIDSTONE.get());
        RuleTest eternalIceReplaceables = new BlockMatchRuleTest(ESBlocks.ETERNAL_ICE.get());
        RuleTest hazeIceReplacables = new BlockMatchRuleTest(ESBlocks.HAZE_ICE.get());

        RuleTest endStoneReplacables = new TagMatchRuleTest(ConventionalBlockTags.END_STONES);

        List<OreFeatureConfig.Target> overworldMagicOres = List.of(
                OreFeatureConfig.createTarget(stoneReplacables, EBlocks.MAGIC_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(dirtReplacables, EBlocks.DIRT_MAGIC_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(andesiteReplacables, EBlocks.ANDESITE_MAGIC_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(dioriteReplacables, EBlocks.DIORITE_MAGIC_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(graniteReplacables, EBlocks.GRANITE_MAGIC_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplacables, EBlocks.DEEPSLATE_MAGIC_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(tuffReplacables, EBlocks.TUFF_MAGIC_ORE.getDefaultState())
        );

        List<OreFeatureConfig.Target> overworldTungstenOres = List.of(
                OreFeatureConfig.createTarget(stoneReplacables, EBlocks.TUNGSTEN_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplacables, EBlocks.DEEPSLATE_TUNGSTEN_ORE.getDefaultState())
        );

        List<OreFeatureConfig.Target> overworldDemoniteOres = List.of(
                OreFeatureConfig.createTarget(stoneReplacables, EBlocks.DEMONITE_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplacables, EBlocks.DEEPSLATE_DEMONITE_ORE.getDefaultState())
        );

        List<OreFeatureConfig.Target> netherHellstoneOres = List.of(
                OreFeatureConfig.createTarget(netherStoneReplacables, EBlocks.HELLSTONE_ORE.getDefaultState())
        );

        List<OreFeatureConfig.Target> overworldCobaltOres = List.of(
                OreFeatureConfig.createTarget(stoneReplacables, EBlocks.COBALT_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplacables, EBlocks.DEEPSLATE_COBALT_ORE.getDefaultState())
        );

        List<OreFeatureConfig.Target> overworldOrichalcumOres = List.of(
                OreFeatureConfig.createTarget(stoneReplacables, EBlocks.ORICHALCUM_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplacables, EBlocks.DEEPSLATE_ORICHALCUM_ORE.getDefaultState())
        );

        List<OreFeatureConfig.Target> overworldTitaniumOres = List.of(
                OreFeatureConfig.createTarget(stoneReplacables, EBlocks.TITANIUM_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplacables, EBlocks.DEEPSLATE_TITANIUM_ORE.getDefaultState())
        );

        List<OreFeatureConfig.Target> eternalStarlightVerdantOres = List.of(
                OreFeatureConfig.createTarget(stoneReplacables, EBlocks.VERDANT_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplacables, EBlocks.DEEPSLATE_VERDANT_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(grimstoneReplacables, EBlocks.GRIMSTONE_VERDANT_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(voidstoneReplacables, EBlocks.VOIDSTONE_VERDANT_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(eternalIceReplaceables, EBlocks.ETERNAL_ICE_VERDANT_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(hazeIceReplacables, EBlocks.HAZE_ICE_VERDANT_ORE.getDefaultState())
        );

        List<OreFeatureConfig.Target> eternalStarlightDetoniumOres = List.of(
                OreFeatureConfig.createTarget(stoneReplacables, EBlocks.DETONIUM_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplacables, EBlocks.DEEPSLATE_DETONIUM_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(grimstoneReplacables, EBlocks.GRIMSTONE_DETONIUM_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(voidstoneReplacables, EBlocks.VOIDSTONE_DETONIUM_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(eternalIceReplaceables, EBlocks.ETERNAL_ICE_DETONIUM_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(hazeIceReplacables, EBlocks.HAZE_ICE_DETONIUM_ORE.getDefaultState())
        );

        List<OreFeatureConfig.Target> netherAetheriumOres = List.of(
                OreFeatureConfig.createTarget(deepslateReplacables, EBlocks.DEEPSLATE_AETHERIUM_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(basaltReplacables, EBlocks.BASALT_AETHERIUM_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(blackstoneReplaceables, EBlocks.BLACKSTONE_AETHERIUM_ORE.getDefaultState())
        );

        List<OreFeatureConfig.Target> endAetheriumOres = List.of(
                OreFeatureConfig.createTarget(endStoneReplacables, EBlocks.END_AETHERIUM_ORE.getDefaultState())
        );

        register(context, MAGIC_ORE_OVERWORLD, Feature.ORE, new OreFeatureConfig(overworldMagicOres, 20, .6f));
        register(context, TUNGSTEN_ORE_OVERWORLD, Feature.ORE, new OreFeatureConfig(overworldTungstenOres, 9, .4f));
        register(context, DEMONITE_ORE_OVERWORLD, Feature.ORE, new OreFeatureConfig(overworldDemoniteOres, 8, .4f));
        register(context, HELLSTONE_ORE_NETHER, Feature.ORE, new OreFeatureConfig(netherHellstoneOres, 8, .1f));
        register(context, COBALT_ORE_OVERWORLD, Feature.ORE, new OreFeatureConfig(overworldCobaltOres, 10, .2f));
        register(context, ORICHALCUM_ORE_OVERWORLD, Feature.ORE, new OreFeatureConfig(overworldOrichalcumOres, 9, .2f));
        register(context, TITANIUM_ORE_OVERWORLD, Feature.ORE, new OreFeatureConfig(overworldTitaniumOres, 7, .1f));
        register(context, VERDANT_ORE_ETERNAL_STARLIGHT, Feature.ORE, new OreFeatureConfig(eternalStarlightVerdantOres, 6, .2f));
        register(context, DETONIUM_ORE_ETERNAL_STARLIGHT, Feature.ORE, new OreFeatureConfig(eternalStarlightDetoniumOres, 6, .2f));
        register(context, AETHERIUM_ORE_NETHER, Feature.ORE, new OreFeatureConfig(netherAetheriumOres, 5, .01f));
        register(context, AETHERIUM_ORE_END, Feature.ORE, new OreFeatureConfig(endAetheriumOres, 16, .0075f));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Ecraft.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<ConfiguredFeature<?, ?>> context,
            RegistryKey<ConfiguredFeature<?, ?>> key,
            F feature, FC config
    ) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
