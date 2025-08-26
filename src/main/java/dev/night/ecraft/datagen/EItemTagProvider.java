package dev.night.ecraft.datagen;

import dev.night.ecraft.block.EBlocks;
import dev.night.ecraft.item.EArmor;
import dev.night.ecraft.item.EWeapons;
import dev.night.ecraft.item.EItems;
import dev.night.ecraft.item.food.EFood;
import dev.night.ecraft.tags.ETags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class EItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public EItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        //region Fabric Conventional Item Tags
        getOrCreateTagBuilder(ConventionalItemTags.WATER_BUCKETS)
                .add(EItems.ENDLESS_WATER_BUCKET);

        getOrCreateTagBuilder(ConventionalItemTags.LAVA_BUCKETS)
                .add(EItems.ENDLESS_LAVA_BUCKET);

        getOrCreateTagBuilder(ConventionalItemTags.MILK_BUCKETS)
                .add(EItems.ENDLESS_MILK_BUCKET);

        getOrCreateTagBuilder(ConventionalItemTags.FOODS)
                .add(EFood.DR_PEPPER)
                .add(EFood.JARRITOS)
                .add(EFood.PIBB_XTRA)
                .add(EFood.RED_BULL)
                .add(EFood.SNAPPLE);

        getOrCreateTagBuilder(ConventionalItemTags.FRUIT_FOODS)
                .add(EFood.MAGIC_APPLE);

        getOrCreateTagBuilder(ConventionalItemTags.RAW_MEAT_FOODS)
                .add(EFood.RAW_CHICKEN_NUGGET)
                .add(EBlocks.RAW_CHICKEN_BLOCK.asItem());

        getOrCreateTagBuilder(ConventionalItemTags.COOKED_MEAT_FOODS)
                .add(EFood.COOKED_CHICKEN_NUGGET)
                .add(EBlocks.COOKED_CHICKEN_BLOCK.asItem());

        getOrCreateTagBuilder(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS)
                .add(EBlocks.RAW_CHICKEN_BLOCK.asItem())
                .add(EBlocks.COOKED_CHICKEN_BLOCK.asItem());

        getOrCreateTagBuilder(ConventionalItemTags.FOOD_POISONING_FOODS)
                .add(EBlocks.RAW_CHICKEN_BLOCK.asItem());

        getOrCreateTagBuilder(ConventionalItemTags.STORAGE_BLOCKS)
                .add(EBlocks.RAW_MAGIC_BLOCK.asItem())
                .add(EBlocks.MAGIC_BLOCK.asItem())

                .add(EBlocks.RAW_TUNGSTEN_BLOCK.asItem())
                .add(EBlocks.TUNGSTEN_BLOCK.asItem())

                .add(EBlocks.RAW_DEMONITE_BLOCK.asItem())
                .add(EBlocks.DEMONITE_BLOCK.asItem())

                .add(EBlocks.RAW_DIAMOND_BLOCK.asItem())

                .add(EBlocks.RAW_HELLSTONE_BLOCK.asItem())
                .add(EBlocks.HELLSTONE_BLOCK.asItem())

                .add(EBlocks.RAW_COBALT_BLOCK.asItem())
                .add(EBlocks.COBALT_BLOCK.asItem())

                .add(EBlocks.RAW_ORICHALCUM_BLOCK.asItem())
                .add(EBlocks.ORICHALCUM_BLOCK.asItem())

                .add(EBlocks.RAW_TITANIUM_BLOCK.asItem())
                .add(EBlocks.TITANIUM_BLOCK.asItem())

                .add(EBlocks.HALLOWED_BLOCK.asItem())

                .add(EBlocks.RAW_VERDANT_BLOCK.asItem())
                .add(EBlocks.VERDANT_BLOCK.asItem())

                .add(EBlocks.RAW_DETONIUM_BLOCK.asItem())
                .add(EBlocks.DETONIUM_BLOCK.asItem())

                .add(EBlocks.REFINED_AETHERIUM_BLOCK.asItem())

                .add(EBlocks.CINCINNASITE_DIAMOND_BLOCK.asItem())
                .add(EBlocks.TERRASTEEL_BLOCK.asItem());

        getOrCreateTagBuilder(ConventionalItemTags.INGOTS)
                .add(EItems.DEMONITE_INGOT)
                .add(EItems.CINCINNASITE_DIAMOND_INGOT)
                .add(EItems.HELLSTONE_INGOT)
                .add(EItems.TUNGSTEN_INGOT)
                .add(EItems.TERRASTEEL_INGOT)
                .add(EItems.COBALT_INGOT)
                .add(EItems.ORICHALCUM_INGOT)
                .add(EItems.TITANIUM_INGOT)
                .add(EItems.HALLOWED_INGOT)
                .add(EItems.VERDANT_INGOT)
                .add(EItems.DETONIUM_INGOT);

        getOrCreateTagBuilder(ConventionalItemTags.GEMS)
                .forceAddTag(ETags.Items.GEMS_MAGIC)
                .forceAddTag(ETags.Items.GEMS_RUBY);

        getOrCreateTagBuilder(ConventionalItemTags.DUSTS)
                .forceAddTag(ETags.Items.DUSTS_MAGIC);

        getOrCreateTagBuilder(ConventionalItemTags.RAW_MATERIALS)
                .add(EItems.RAW_DEMONITE)
                .add(EItems.RAW_DIAMOND)
                .add(EItems.RAW_HELLSTONE)
                .add(EItems.RAW_TUNGSTEN)
                .add(EItems.RAW_COBALT)
                .add(EItems.RAW_ORICHALCUM)
                .add(EItems.RAW_TITANIUM)
                .add(EItems.RAW_VERDANT)
                .add(EItems.RAW_DETONIUM);

        getOrCreateTagBuilder(ConventionalItemTags.NUGGETS)
                .add(EItems.COPPER_NUGGET)
                .add(EItems.DEMONITE_NUGGET)
                .add(EItems.HELLSTONE_NUGGET)
                .add(EItems.TUNGSTEN_NUGGET)
                .add(EItems.CINCINNASITE_DIAMOND_NUGGET)
                .add(EItems.TERRASTEEL_NUGGET)
                .add(EItems.COBALT_NUGGET)
                .add(EItems.ORICHALCUM_NUGGET)
                .add(EItems.TITANIUM_NUGGET)
                .add(EItems.HALLOWED_NUGGET)
                .add(EItems.VERDANT_NUGGET)
                .add(EItems.DETONIUM_NUGGET);

        getOrCreateTagBuilder(ConventionalItemTags.MELEE_WEAPON_TOOLS)
                .addTag(ETags.Items.SCYTHES);

        getOrCreateTagBuilder(ConventionalItemTags.RANGED_WEAPON_TOOLS)
                .addTag(ETags.Items.STAVES);

        getOrCreateTagBuilder(ConventionalItemTags.MINING_TOOL_TOOLS)
                .addTag(ETags.Items.PICKSAWS);

        getOrCreateTagBuilder(ConventionalItemTags.BRICKS)
                .add(EBlocks.AETHERIUM_BRICKS.asItem());

        getOrCreateTagBuilder(ConventionalItemTags.NETHER_BRICKS)
                .add(EBlocks.HELLSTONE_BRICKS.asItem());
        //endregion

        //region Vanilla Item Tags
        getOrCreateTagBuilder(ItemTags.HORSE_FOOD)
                .add(EFood.MAGIC_APPLE);

        getOrCreateTagBuilder(ItemTags.HORSE_TEMPT_ITEMS)
                .add(EFood.MAGIC_APPLE);

        getOrCreateTagBuilder(ItemTags.AXES)
                .addTag(ETags.Items.PICKSAWS)
                .add(EWeapons.DEMONITE_AXE)
                .add(EWeapons.HELLSTONE_AXE)
                .add(EWeapons.TUNGSTEN_AXE)
                // .add(EWeapons.TERRASTEEL_AXE)
                .addTag(ETags.Items.BATTLEAXES);

        getOrCreateTagBuilder(ItemTags.HOES)
                .add(EWeapons.DEMONITE_HOE)
                .add(EWeapons.HELLSTONE_HOE)
                .add(EWeapons.TUNGSTEN_HOE)
                // .add(EWeapons.TERRASTEEL_HOE)
                .addTag(ETags.Items.SCYTHES);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .addTag(ETags.Items.PICKSAWS)
                .add(EWeapons.DETONIUM_BLASTAXE)
                .add(EWeapons.DETONIUM_PICKAXE)
                .add(EWeapons.VERDANT_PICKAXE)
                .add(EWeapons.HALLOWED_PICKAXE)
                .add(EWeapons.TITANIUM_PICKAXE)
                .add(EWeapons.ORICHALCUM_PICKAXE)
                .add(EWeapons.COBALT_PICKAXE)
                .add(EWeapons.DEMONITE_PICKAXE)
                .add(EWeapons.HELLSTONE_PICKAXE)
                .add(EWeapons.TUNGSTEN_PICKAXE);
                // .add(EWeapons.TERRASTEEL_PICKAXE);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .addTag(ETags.Items.PICKSAWS)
                .add(EWeapons.DETONIUM_SHOVEL)
                .add(EWeapons.VERDANT_SHOVEL)
                .add(EWeapons.HALLOWED_SHOVEL)
                .add(EWeapons.TITANIUM_SHOVEL)
                .add(EWeapons.ORICHALCUM_SHOVEL)
                .add(EWeapons.COBALT_SHOVEL)
                .add(EWeapons.DEMONITE_SHOVEL)
                .add(EWeapons.HELLSTONE_SHOVEL)
                .add(EWeapons.TUNGSTEN_SHOVEL);
                // .add(EWeapons.TERRASTEEL_SHOVEL);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(EWeapons.BASTARD_SWORD)
                .add(EWeapons.SOLAIS)
                .add(EWeapons.CALIBURN)
                .add(EWeapons.DURENDAL)
                .add(EWeapons.DETONIUM_SWORD)
                .add(EWeapons.VERDANT_SWORD)
                .add(EWeapons.HALLOWED_SWORD)
                .add(EWeapons.TITANIUM_SWORD)
                .add(EWeapons.ORICHALCUM_SWORD)
                .add(EWeapons.MURAMASA)
                .add(EWeapons.VOLCANO)
                .add(EWeapons.BLADE_OF_GRASS)
                .add(EWeapons.NIGHTS_EDGE)
                .add(EWeapons.TRUE_NIGHTS_EDGE)
                .add(EWeapons.COBALT_SWORD)
                .add(EWeapons.DEMONITE_SWORD)
                .add(EWeapons.HELLSTONE_SWORD)
                .add(EWeapons.TUNGSTEN_SWORD);
                // .add(EWeapons.TERRASTEEL_SWORD);

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(EArmor.FLAWLESS_AETHERIUM_HELMET)
                .add(EArmor.REFINED_AETHERIUM_HELMET)
                .add(EArmor.DETONIUM_HELMET)
                .add(EArmor.VERDANT_HELMET)
                .add(EArmor.HALLOWED_HELMET)
                .add(EArmor.TITANIUM_HELMET)
                .add(EArmor.ORICHALCUM_HELMET)
                .add(EArmor.COBALT_HELMET)
                .add(EArmor.COPPER_HELMET)
                .add(EArmor.DEMONITE_HELMET)
                .add(EArmor.HELLSTONE_HELMET)
                .add(EArmor.TUNGSTEN_HELMET)
                //.add(EArmor.TERRASTEEL_HELMET)
                .add(EArmor.CINCINNASITE_DIAMOND_HELMET);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(EArmor.FLAWLESS_AETHERIUM_CHESTPLATE)
                .add(EArmor.REFINED_AETHERIUM_CHESTPLATE)
                .add(EArmor.DETONIUM_CHESTPLATE)
                .add(EArmor.VERDANT_CHESTPLATE)
                .add(EArmor.HALLOWED_CHESTPLATE)
                .add(EArmor.TITANIUM_CHESTPLATE)
                .add(EArmor.ORICHALCUM_CHESTPLATE)
                .add(EArmor.COBALT_CHESTPLATE)
                .add(EArmor.COPPER_CHESTPLATE)
                .add(EArmor.DEMONITE_CHESTPLATE)
                .add(EArmor.HELLSTONE_CHESTPLATE)
                .add(EArmor.TUNGSTEN_CHESTPLATE)
                //.add(EArmor.TERRASTEEL_CHESTPLATE)
                .add(EArmor.CINCINNASITE_DIAMOND_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(EArmor.FLAWLESS_AETHERIUM_LEGGINGS)
                .add(EArmor.REFINED_AETHERIUM_LEGGINGS)
                .add(EArmor.DETONIUM_LEGGINGS)
                .add(EArmor.VERDANT_LEGGINGS)
                .add(EArmor.HALLOWED_LEGGINGS)
                .add(EArmor.TITANIUM_LEGGINGS)
                .add(EArmor.ORICHALCUM_LEGGINGS)
                .add(EArmor.COBALT_LEGGINGS)
                .add(EArmor.COPPER_LEGGINGS)
                .add(EArmor.DEMONITE_LEGGINGS)
                .add(EArmor.HELLSTONE_LEGGINGS)
                .add(EArmor.TUNGSTEN_LEGGINGS)
                //.add(EArmor.TERRASTEEL_LEGGINGS)
                .add(EArmor.CINCINNASITE_DIAMOND_LEGGINGS);

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(EArmor.FLAWLESS_AETHERIUM_BOOTS)
                .add(EArmor.REFINED_AETHERIUM_BOOTS)
                .add(EArmor.DETONIUM_BOOTS)
                .add(EArmor.VERDANT_BOOTS)
                .add(EArmor.HALLOWED_BOOTS)
                .add(EArmor.TITANIUM_BOOTS)
                .add(EArmor.ORICHALCUM_BOOTS)
                .add(EArmor.COBALT_BOOTS)
                .add(EArmor.COPPER_BOOTS)
                .add(EArmor.DEMONITE_BOOTS)
                .add(EArmor.HELLSTONE_BOOTS)
                .add(EArmor.TUNGSTEN_BOOTS)
                //.add(EArmor.TERRASTEEL_BOOTS)
                .add(EArmor.CINCINNASITE_DIAMOND_BOOTS);

        getOrCreateTagBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .forceAddTag(ETags.Items.LANCES);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .forceAddTag(ETags.Items.LANCES)
                .forceAddTag(ETags.Items.PICKSAWS);

        getOrCreateTagBuilder(ItemTags.MINING_ENCHANTABLE)
                .forceAddTag(ETags.Items.PICKSAWS);

        getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE)
                .forceAddTag(ETags.Items.PICKSAWS);
        //endregion

        //region ecraft Item Tags
        getOrCreateTagBuilder(ETags.Items.GEMS_RUBY)
                .addOptional(Identifier.of("betternether", "nether_ruby"));

        //region Magic Tags
        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_RAW_MAGIC)
                .add(EBlocks.RAW_MAGIC_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_MAGIC)
                .add(EBlocks.MAGIC_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.DUSTS_MAGIC)
                .add(EItems.MAGIC_DUST);

        getOrCreateTagBuilder(ETags.Items.GEMS_MAGIC)
                .add(EItems.MAGIC_CRYSTAL);

        getOrCreateTagBuilder(ETags.Items.ORES_MAGIC)
                .add(EBlocks.MAGIC_ORE.asItem())
                .add(EBlocks.DIRT_MAGIC_ORE.asItem())
                .add(EBlocks.ANDESITE_MAGIC_ORE.asItem())
                .add(EBlocks.DIORITE_MAGIC_ORE.asItem())
                .add(EBlocks.GRANITE_MAGIC_ORE.asItem())
                .add(EBlocks.TUFF_MAGIC_ORE.asItem())
                .add(EBlocks.DEEPSLATE_MAGIC_ORE.asItem());
        //endregion

        //region Cincinnasite-Diamond Tags
        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_CINCINNASITE_DIAMOND)
                .add(EBlocks.CINCINNASITE_DIAMOND_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.INGOTS_CINCINNASITE_DIAMOND)
                .add(EItems.CINCINNASITE_DIAMOND_INGOT);

        getOrCreateTagBuilder(ETags.Items.NUGGETS_CINCINNASITE_DIAMOND)
                .add(EItems.CINCINNASITE_DIAMOND_NUGGET);
        //endregion

        //region Tungsten Tags
        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_RAW_TUNGSTEN)
                .add(EBlocks.RAW_TUNGSTEN_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_TUNGSTEN)
                .add(EBlocks.TUNGSTEN_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.RAW_MATERIALS_TUNGSTEN)
                .add(EItems.RAW_TUNGSTEN);

        getOrCreateTagBuilder(ETags.Items.ORES_TUNGSTEN)
                .add(EBlocks.TUNGSTEN_ORE.asItem())
                .add(EBlocks.DEEPSLATE_TUNGSTEN_ORE.asItem());

        getOrCreateTagBuilder(ETags.Items.INGOTS_TUNGSTEN)
                .add(EItems.TUNGSTEN_INGOT);

        getOrCreateTagBuilder(ETags.Items.NUGGETS_TUNGSTEN)
                .add(EItems.TUNGSTEN_NUGGET);
        //endregion

        //region Demonite Tags
        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_RAW_DEMONITE)
                .add(EBlocks.RAW_DEMONITE_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_DEMONITE)
                .add(EBlocks.DEMONITE_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.RAW_MATERIALS_DEMONITE)
                .add(EItems.RAW_DEMONITE);

        getOrCreateTagBuilder(ETags.Items.ORES_DEMONITE)
                .add(EBlocks.DEMONITE_ORE.asItem())
                .add(EBlocks.DEEPSLATE_DEMONITE_ORE.asItem());

        getOrCreateTagBuilder(ETags.Items.INGOTS_DEMONITE)
                .add(EItems.DEMONITE_INGOT);

        getOrCreateTagBuilder(ETags.Items.NUGGETS_DEMONITE)
                .add(EItems.DEMONITE_NUGGET);
        //endregion

        //region Hellstone Tags
        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_RAW_HELLSTONE)
                .add(EBlocks.RAW_HELLSTONE_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_HELLSTONE)
                .add(EBlocks.HELLSTONE_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.RAW_MATERIALS_HELLSTONE)
                .add(EItems.RAW_HELLSTONE);

        getOrCreateTagBuilder(ETags.Items.ORES_HELLSTONE)
                .add(EBlocks.HELLSTONE_ORE.asItem());

        getOrCreateTagBuilder(ETags.Items.INGOTS_HELLSTONE)
                .add(EItems.HELLSTONE_INGOT);

        getOrCreateTagBuilder(ETags.Items.NUGGETS_HELLSTONE)
                .add(EItems.HELLSTONE_NUGGET);
        //endregion

        //region Cobalt Tags
        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_RAW_COBALT)
                .add(EBlocks.RAW_COBALT_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_COBALT)
                .add(EBlocks.COBALT_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.RAW_MATERIALS_COBALT)
                .add(EItems.RAW_COBALT);

        getOrCreateTagBuilder(ETags.Items.ORES_COBALT)
                .add(EBlocks.COBALT_ORE.asItem())
                .add(EBlocks.DEEPSLATE_COBALT_ORE.asItem());

        getOrCreateTagBuilder(ETags.Items.INGOTS_COBALT)
                .add(EItems.COBALT_INGOT);

        getOrCreateTagBuilder(ETags.Items.NUGGETS_COBALT)
                .add(EItems.COBALT_NUGGET);
        //endregion

        //region Orichalcum Tags
        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_RAW_ORICHALCUM)
                .add(EBlocks.RAW_ORICHALCUM_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_ORICHALCUM)
                .add(EBlocks.ORICHALCUM_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.RAW_MATERIALS_ORICHALCUM)
                .add(EItems.RAW_ORICHALCUM);

        getOrCreateTagBuilder(ETags.Items.ORES_ORICHALCUM)
                .add(EBlocks.ORICHALCUM_ORE.asItem())
                .add(EBlocks.DEEPSLATE_ORICHALCUM_ORE.asItem());

        getOrCreateTagBuilder(ETags.Items.INGOTS_ORICHALCUM)
                .add(EItems.ORICHALCUM_INGOT);

        getOrCreateTagBuilder(ETags.Items.NUGGETS_ORICHALCUM)
                .add(EItems.ORICHALCUM_NUGGET);
        //endregion

        //region Titanium Tags
        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_RAW_TITANIUM)
                .add(EBlocks.RAW_TITANIUM_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_TITANIUM)
                .add(EBlocks.TITANIUM_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.RAW_MATERIALS_TITANIUM)
                .add(EItems.RAW_TITANIUM);

        getOrCreateTagBuilder(ETags.Items.ORES_TITANIUM)
                .add(EBlocks.TITANIUM_ORE.asItem())
                .add(EBlocks.DEEPSLATE_TITANIUM_ORE.asItem());

        getOrCreateTagBuilder(ETags.Items.INGOTS_TITANIUM)
                .add(EItems.TITANIUM_INGOT);

        getOrCreateTagBuilder(ETags.Items.NUGGETS_TITANIUM)
                .add(EItems.TITANIUM_NUGGET);
        //endregion

        //region Hallowed Tags

        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_HALLOWED)
                .add(EBlocks.HALLOWED_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.INGOTS_HALLOWED)
                .add(EItems.HALLOWED_INGOT);

        getOrCreateTagBuilder(ETags.Items.NUGGETS_HALLOWED)
                .add(EItems.HALLOWED_NUGGET);
        //endregion

        //region Verdant Tags
        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_RAW_VERDANT)
                .add(EBlocks.RAW_VERDANT_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_VERDANT)
                .add(EBlocks.VERDANT_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.RAW_MATERIALS_VERDANT)
                .add(EItems.RAW_VERDANT);

        getOrCreateTagBuilder(ETags.Items.ORES_VERDANT)
                .add(EBlocks.VERDANT_ORE.asItem())
                .add(EBlocks.DEEPSLATE_VERDANT_ORE.asItem())
                .add(EBlocks.GRIMSTONE_VERDANT_ORE.asItem())
                .add(EBlocks.VOIDSTONE_VERDANT_ORE.asItem())
                .add(EBlocks.ETERNAL_ICE_VERDANT_ORE.asItem())
                .add(EBlocks.HAZE_ICE_VERDANT_ORE.asItem());

        getOrCreateTagBuilder(ETags.Items.INGOTS_VERDANT)
                .add(EItems.VERDANT_INGOT);

        getOrCreateTagBuilder(ETags.Items.NUGGETS_VERDANT)
                .add(EItems.VERDANT_NUGGET);
        //endregion

        //region Detonium Tags
        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_RAW_DETONIUM)
                .add(EBlocks.RAW_DETONIUM_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_DETONIUM)
                .add(EBlocks.DETONIUM_BLOCK.asItem());

        getOrCreateTagBuilder(ETags.Items.RAW_MATERIALS_DETONIUM)
                .add(EItems.RAW_DETONIUM);

        getOrCreateTagBuilder(ETags.Items.ORES_DETONIUM)
                .add(EBlocks.DETONIUM_ORE.asItem())
                .add(EBlocks.DEEPSLATE_DETONIUM_ORE.asItem())
                .add(EBlocks.GRIMSTONE_DETONIUM_ORE.asItem())
                .add(EBlocks.VOIDSTONE_DETONIUM_ORE.asItem())
                .add(EBlocks.ETERNAL_ICE_DETONIUM_ORE.asItem())
                .add(EBlocks.HAZE_ICE_DETONIUM_ORE.asItem());

        getOrCreateTagBuilder(ETags.Items.INGOTS_DETONIUM)
                .add(EItems.DETONIUM_INGOT);

        getOrCreateTagBuilder(ETags.Items.NUGGETS_DETONIUM)
                .add(EItems.DETONIUM_NUGGET);
        //endregion

        //region Aetherium Tags
        getOrCreateTagBuilder(ETags.Items.ORES_AETHERIUM)
                .add(EBlocks.DEEPSLATE_AETHERIUM_ORE.asItem())
                .add(EBlocks.BLACKSTONE_AETHERIUM_ORE.asItem())
                .add(EBlocks.BASALT_AETHERIUM_ORE.asItem())
                .add(EBlocks.END_AETHERIUM_ORE.asItem());

        getOrCreateTagBuilder(ETags.Items.GEMS_AETHERIUM)
                .add(EItems.REFINED_AETHERIUM_CRYSTAL);

        getOrCreateTagBuilder(ETags.Items.STORAGE_BLOCKS_AETHERIUM)
                .add(EBlocks.REFINED_AETHERIUM_BLOCK.asItem());
        //endregion

        getOrCreateTagBuilder(ETags.Items.MANA_STAR)
                .add(EItems.MANA_STAR)
                .add(EItems.VERDANT_MANA_STAR)
                .add(EItems.AETHERIUM_MANA_STAR)
                .add(EItems.FLAWLESS_AETHERIUM_MANA_STAR);

        getOrCreateTagBuilder(ETags.Items.MEDALLIONS_OF_SPACE)
                .add(EItems.MEDALLION_OF_SPACE_STONE)
                .add(EItems.MEDALLION_OF_SPACE_TUNGSTEN)
                .add(EItems.MEDALLION_OF_SPACE_DEMONITE)
                .add(EItems.MEDALLION_OF_SPACE_COBALT)
                .add(EItems.MEDALLION_OF_SPACE_VERDANT)
                .add(EItems.MEDALLION_OF_SPACE_AETHERIUM)
                .add(EItems.MEDALLION_OF_SPACE_FLAWLESS_AETHERIUM);

        getOrCreateTagBuilder(ETags.Items.STAVES)
                .add(
                        EWeapons.COPPER_STAFF,
                        EWeapons.IRON_STAFF,
                        EWeapons.TUNGSTEN_STAFF,
                        EWeapons.GOLDEN_STAFF,
                        EWeapons.DEMONITE_STAFF,
                        EWeapons.HELLSTONE_STAFF
                );

        getOrCreateTagBuilder(ETags.Items.PICKSAWS)
                .add(EWeapons.WOODEN_PICKSAW)
                .add(EWeapons.STONE_PICKSAW)
                .add(EWeapons.COPPER_PICKSAW)
                .add(EWeapons.IRON_PICKSAW)
                .add(EWeapons.GOLDEN_PICKSAW)
                .add(EWeapons.DIAMOND_PICKSAW)
                .add(EWeapons.TUNGSTEN_PICKSAW)
                .add(EWeapons.DEMONITE_PICKSAW)
                .add(EWeapons.HELLSTONE_PICKSAW)
                .add(EWeapons.COBALT_PICKSAW)
                .add(EWeapons.ORICHALCUM_PICKSAW)
                .add(EWeapons.TITANIUM_PICKSAW)
                .add(EWeapons.HALLOWED_PICKSAW)
                .add(EWeapons.VERDANT_PICKSAW)
                .add(EWeapons.DETONIUM_PICKSAW);

        getOrCreateTagBuilder(ETags.Items.BATTLEAXES)
                .add(EWeapons.COBALT_BATTLEAXE)
                .add(EWeapons.ORICHALCUM_BATTLEAXE)
                .add(EWeapons.TITANIUM_BATTLEAXE)
                .add(EWeapons.HALLOWED_BATTLEAXE)
                .add(EWeapons.VERDANT_BATTLEAXE)
                .add(EWeapons.DETONIUM_BATTLEAXE);

        getOrCreateTagBuilder(ETags.Items.SCYTHES)
                .add(EWeapons.COBALT_SCYTHE)
                .add(EWeapons.ORICHALCUM_SCYTHE)
                .add(EWeapons.TITANIUM_SCYTHE)
                .add(EWeapons.HALLOWED_SCYTHE)
                .add(EWeapons.VERDANT_SCYTHE)
                .add(EWeapons.DETONIUM_SCYTHE);

        getOrCreateTagBuilder(ETags.Items.LANCES)
                .add(EWeapons.WOODEN_LANCE)
                .add(EWeapons.STONE_LANCE)
                .add(EWeapons.COPPER_LANCE)
                .add(EWeapons.IRON_LANCE)
                .add(EWeapons.GOLDEN_LANCE)
                .add(EWeapons.DIAMOND_LANCE)
                .add(EWeapons.CINCINNASITE_DIAMOND_LANCE)
                .add(EWeapons.TUNGSTEN_LANCE)
                .add(EWeapons.DEMONITE_LANCE)
                .add(EWeapons.HELLSTONE_LANCE)
                .add(EWeapons.COBALT_LANCE)
                .add(EWeapons.ORICHALCUM_LANCE)
                .add(EWeapons.TITANIUM_LANCE)
                .add(EWeapons.HALLOWED_LANCE)
                .add(EWeapons.VERDANT_LANCE)
                .add(EWeapons.DETONIUM_LANCE);

                /*.add(ModWeapons.ADAMANTITE_LANCE)
                .add(ModWeapons.AQUARIUM_LANCE)
                .add(ModWeapons.BANGLUM_LANCE)
                .add(ModWeapons.BRONZE_LANCE)
                .add(ModWeapons.CARMOT_LANCE)
                .add(ModWeapons.CELESTIUM_LANCE)
                .add(ModWeapons.CINCINNASITE_DIAMOND_LANCE)
                .add(ModWeapons.DURASTEEL_LANCE)
                .add(ModWeapons.ENDERNETHER_LANCE)
                .add(ModWeapons.HALLOWED_LANCE)
                .add(ModWeapons.KYBER_LANCE)
                .add(ModWeapons.LEGENDARY_BANGLUM_LANCE)
                .add(ModWeapons.METALLURGIUM_LANCE)
                .add(ModWeapons.MYTHRIL_LANCE)
                .add(ModWeapons.NETHERITE_LANCE)
                .add(ModWeapons.ORICHALCUM_LANCE)
                .add(ModWeapons.OSMIUM_LANCE)
                .add(ModWeapons.OVERNETHER_LANCE)
                .add(ModWeapons.PALLADIUM_LANCE)
                .add(ModWeapons.PROMETHEUM_LANCE)
                .add(ModWeapons.QUADRILLUM_LANCE)
                .add(ModWeapons.RUNITE_LANCE)
                .add(ModWeapons.STAR_PLATINUM_LANCE)
                .add(ModWeapons.STEEL_LANCE)
                .add(ModWeapons.STORMYX_LANCE)
                .add(ModWeapons.TERRASTEEL_LANCE)
                .add(ModWeapons.TIDESINGER_LANCE)
                .addTag(ModTags.Items.GUNLANCES)*/

        getOrCreateTagBuilder(ETags.Items.LANCE_ENCHANTABLE)
                .addTag(ETags.Items.LANCES);

        getOrCreateTagBuilder(ETags.Items.GUNLANCES)
                .add(EWeapons.CINCINNASITE_DIAMOND_GUNLANCE)
                .add(EWeapons.NETHERITE_GUNLANCE)
                .add(EWeapons.DETONIUM_GUNLANCE)
                .add(EWeapons.HELLSTONE_GUNLANCE);
        //endregion
    }
}
