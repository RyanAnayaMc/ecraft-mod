package dev.night.ecraft.tags;

import dev.night.ecraft.Ecraft;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ETags {
    public static class Enchantments {
        public static final TagKey<Enchantment> RANGE_EXCLUSIVE_SET = create("exclusive_set/range");

        private static TagKey<Enchantment> create(String id) {
            return TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(Ecraft.MOD_ID, id));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> MAGIC_ORES = create("ores/magic");
        public static final TagKey<Block> TUNGSTEN_ORES = create("ores/tungsten");
        public static final TagKey<Block> DEMONITE_ORES = create("ores/demonite");
        public static final TagKey<Block> HELLSTONE_ORES = create("ores/hellstone");
        public static final TagKey<Block> COBALT_ORES = create("ores/cobalt");
        public static final TagKey<Block> ORICHALCUM_ORES = create("ores/orichalcum");
        public static final TagKey<Block> TITANIUM_ORES = create("ores/titanium");
        public static final TagKey<Block> VERDANT_ORES = create("ores/verdant");
        public static final TagKey<Block> DETONIUM_ORES = create("ores/detonium");
        public static final TagKey<Block> AETHERIUM_ORES = create("ores/aetherium");

        public static final TagKey<Block> PICKSAW_MINEABLE = create("mineable/picksaw");

        public static final TagKey<Block> NEEDS_COPPER_TOOL = create("needs_copper_tool");
        public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = create("incorrect_for_copper_tool");
        public static final TagKey<Block> NEEDS_NETHERITE_TOOL = create("needs_netherite_tool");
        public static final TagKey<Block> NEEDS_COBALT_TOOL = create("needs_cobalt_tool");
        public static final TagKey<Block> INCORRECT_FOR_COBALT_TOOL = create("incorrect_for_cobalt_tool");
        public static final TagKey<Block> NEEDS_ORICHALCUM_TOOL = create("needs_orichalcum_tool");
        public static final TagKey<Block> INCORRECT_FOR_ORICHALCUM_TOOL = create("incorrect_for_orichalcum_tool");
        public static final TagKey<Block> NEEDS_TITANIUM_TOOL = create("needs_titanium_tool");
        public static final TagKey<Block> INCORRECT_FOR_TITANIUM_TOOL = create("incorrect_for_titanium_tool");
        public static final TagKey<Block> NEEDS_HALLOWED_TOOL = create("needs_hallowed_tool");
        public static final TagKey<Block> INCORRECT_FOR_HALLOWED_TOOL = create("incorrect_for_hallowed_tool");
        public static final TagKey<Block> NEEDS_VERDANT_TOOL = create("needs_verdant_tool");
        public static final TagKey<Block> INCORRECT_FOR_VERDANT_TOOL = create("incorrect_for_verdant_tool");
        public static final TagKey<Block> NEEDS_DETONIUM_TOOL = create("needs_detonium_tool");
        public static final TagKey<Block> INCORRECT_FOR_DETONIUM_TOOL = create("incorrect_for_detonium_tool");
        public static final TagKey<Block> NEEDS_AETHERIUM_TOOL = create("needs_aetherium_tool");
        public static final TagKey<Block> INCORRECT_FOR_AETHERIUM_TOOL = create("incorrect_for_aetherium_tool");

        private static TagKey<Block> create(String tag) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Ecraft.MOD_ID, tag));
        }
    }

    public static class Items {
        public static final TagKey<Item> GEMS_RUBY = create("gems/ruby");

        public static final TagKey<Item> STORAGE_BLOCKS_RAW_MAGIC = create("storage_blocks/raw_magic");
        public static final TagKey<Item> STORAGE_BLOCKS_MAGIC = create("storage_blocks/magic");
        public static final TagKey<Item> DUSTS_MAGIC = create("dusts/magic");
        public static final TagKey<Item> ORES_MAGIC = create("ores/magic");
        public static final TagKey<Item> GEMS_MAGIC = create("gems/magic");

        public static final TagKey<Item> STORAGE_BLOCKS_CINCINNASITE_DIAMOND = create("storage_blocks/cincinnasite_diamond");
        public static final TagKey<Item> INGOTS_CINCINNASITE_DIAMOND = create("ingots/cincinnasite_diamond");
        public static final TagKey<Item> NUGGETS_CINCINNASITE_DIAMOND = create("nuggets/cincinnasite_diamond");

        public static final TagKey<Item> STORAGE_BLOCKS_RAW_TUNGSTEN = create("storage_blocks/raw_tungsten");
        public static final TagKey<Item> STORAGE_BLOCKS_TUNGSTEN = create("storage_blocks/tungsten");
        public static final TagKey<Item> RAW_MATERIALS_TUNGSTEN = create("raw_materials/tungsten");
        public static final TagKey<Item> ORES_TUNGSTEN = create("ores/tungsten");
        public static final TagKey<Item> INGOTS_TUNGSTEN = create("ingots/tungsten");
        public static final TagKey<Item> NUGGETS_TUNGSTEN = create("nuggets/tungsten");

        public static final TagKey<Item> STORAGE_BLOCKS_RAW_DEMONITE = create("storage_blocks/raw_demonite");
        public static final TagKey<Item> STORAGE_BLOCKS_DEMONITE = create("storage_blocks/demonite");
        public static final TagKey<Item> RAW_MATERIALS_DEMONITE = create("raw_materials/demonite");
        public static final TagKey<Item> ORES_DEMONITE = create("ores/demonite");
        public static final TagKey<Item> INGOTS_DEMONITE = create("ingots/demonite");
        public static final TagKey<Item> NUGGETS_DEMONITE = create("nuggets/demonite");

        public static final TagKey<Item> STORAGE_BLOCKS_RAW_HELLSTONE = create("storage_blocks/raw_hellstone");
        public static final TagKey<Item> STORAGE_BLOCKS_HELLSTONE = create("storage_blocks/hellstone");
        public static final TagKey<Item> RAW_MATERIALS_HELLSTONE = create("raw_materials/hellstone");
        public static final TagKey<Item> ORES_HELLSTONE = create("ores/hellstone");
        public static final TagKey<Item> INGOTS_HELLSTONE = create("ingots/hellstone");
        public static final TagKey<Item> NUGGETS_HELLSTONE = create("nuggets/hellstone");

        public static final TagKey<Item> STORAGE_BLOCKS_RAW_COBALT = create("storage_blocks/raw_cobalt");
        public static final TagKey<Item> STORAGE_BLOCKS_COBALT = create("storage_blocks/cobalt");
        public static final TagKey<Item> RAW_MATERIALS_COBALT = create("raw_materials/cobalt");
        public static final TagKey<Item> ORES_COBALT = create("ores/cobalt");
        public static final TagKey<Item> INGOTS_COBALT = create("ingots/cobalt");
        public static final TagKey<Item> NUGGETS_COBALT = create("nuggets/cobalt");

        public static final TagKey<Item> STORAGE_BLOCKS_RAW_ORICHALCUM = create("storage_blocks/raw_orichalcum");
        public static final TagKey<Item> STORAGE_BLOCKS_ORICHALCUM = create("storage_blocks/orichalcum");
        public static final TagKey<Item> RAW_MATERIALS_ORICHALCUM = create("raw_materials/orichalcum");
        public static final TagKey<Item> ORES_ORICHALCUM = create("ores/orichalcum");
        public static final TagKey<Item> INGOTS_ORICHALCUM = create("ingots/orichalcum");
        public static final TagKey<Item> NUGGETS_ORICHALCUM = create("nuggets/orichalcum");

        public static final TagKey<Item> STORAGE_BLOCKS_RAW_TITANIUM = create("storage_blocks/raw_titanium");
        public static final TagKey<Item> STORAGE_BLOCKS_TITANIUM = create("storage_blocks/titanium");
        public static final TagKey<Item> RAW_MATERIALS_TITANIUM = create("raw_materials/titanium");
        public static final TagKey<Item> ORES_TITANIUM = create("ores/titanium");
        public static final TagKey<Item> INGOTS_TITANIUM = create("ingots/titanium");
        public static final TagKey<Item> NUGGETS_TITANIUM = create("nuggets/titanium");

        public static final TagKey<Item> STORAGE_BLOCKS_HALLOWED = create("storage_blocks/hallowed");
        public static final TagKey<Item> INGOTS_HALLOWED = create("ingots/hallowed");
        public static final TagKey<Item> NUGGETS_HALLOWED = create("nuggets/hallowed");

        public static final TagKey<Item> STORAGE_BLOCKS_RAW_VERDANT = create("storage_blocks/raw_verdant");
        public static final TagKey<Item> STORAGE_BLOCKS_VERDANT = create("storage_blocks/verdant");
        public static final TagKey<Item> RAW_MATERIALS_VERDANT = create("raw_materials/verdant");
        public static final TagKey<Item> ORES_VERDANT = create("ores/verdant");
        public static final TagKey<Item> INGOTS_VERDANT = create("ingots/verdant");
        public static final TagKey<Item> NUGGETS_VERDANT = create("nuggets/verdant");

        public static final TagKey<Item> STORAGE_BLOCKS_RAW_DETONIUM = create("storage_blocks/raw_detonium");
        public static final TagKey<Item> STORAGE_BLOCKS_DETONIUM = create("storage_blocks/detonium");
        public static final TagKey<Item> RAW_MATERIALS_DETONIUM = create("raw_materials/detonium");
        public static final TagKey<Item> ORES_DETONIUM = create("ores/detonium");
        public static final TagKey<Item> INGOTS_DETONIUM = create("ingots/detonium");
        public static final TagKey<Item> NUGGETS_DETONIUM = create("nuggets/detonium");

        public static final TagKey<Item> ORES_AETHERIUM = create("ores/aetherium");
        public static final TagKey<Item> GEMS_AETHERIUM = create("gems/aetherium");
        public static final TagKey<Item> STORAGE_BLOCKS_AETHERIUM = create("storage_blocks/aetherium");

        public static final TagKey<Item> BATTLEAXES = create("battleaxes");
        public static final TagKey<Item> SCYTHES = create("scythes");
        public static final TagKey<Item> LANCES = create("lances");
        public static final TagKey<Item> LANCE_ENCHANTABLE = create("enchantable/lance");
        public static final TagKey<Item> PICKSAWS = create("picksaws");
        public static final TagKey<Item> GUNLANCES = create("gunlances");
        public static final TagKey<Item> STAVES = create("staves");
        public static final TagKey<Item> MEDALLIONS_OF_SPACE = create("trinkets", "hand/medallion_of_space");
        public static final TagKey<Item> MANA_STAR = create("trinkets", "hand/mana_star");

        private static TagKey<Item> create(String tag) {
            return create(Ecraft.MOD_ID, tag);
        }

        private static TagKey<Item> create(String namespace, String tag) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(namespace, tag));
        }
    }
}
