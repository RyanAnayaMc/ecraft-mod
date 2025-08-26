package dev.night.ecraft.datagen;

import com.sun.jna.platform.win32.Ddeml;
import dev.night.ecraft.block.EBlocks;
import dev.night.ecraft.item.EArmor;
import dev.night.ecraft.item.EWeapons;
import dev.night.ecraft.item.EItems;
import dev.night.ecraft.item.food.EFood;
import dev.night.ecraft.item.tools.weapons.StaffItem;
import dev.night.ecraft.tags.ETags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.*;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ERecipeProvider extends FabricRecipeProvider {
    public ERecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        //region Gunlance Reipces
        offerSmithingTemplateCopyingRecipe(exporter, EItems.GUNLANCE_SMITHING_TEMPLATE, EItems.SMOOTHBORE_BARREL, Items.DIAMOND);
        offerSmithingUpgradeRecipe(exporter, EItems.GUNLANCE_SMITHING_TEMPLATE, EItems.SMOOTHBORE_BARREL, EWeapons.CINCINNASITE_DIAMOND_LANCE, RecipeCategory.COMBAT, EWeapons.CINCINNASITE_DIAMOND_GUNLANCE);
        offerSmithingUpgradeRecipe(exporter, EItems.GUNLANCE_SMITHING_TEMPLATE, EItems.SMOOTHBORE_BARREL, EWeapons.NETHERITE_LANCE, RecipeCategory.COMBAT, EWeapons.NETHERITE_GUNLANCE);
        offerSmithingUpgradeRecipe(exporter, EItems.GUNLANCE_SMITHING_TEMPLATE, EItems.SMOOTHBORE_BARREL, EWeapons.HELLSTONE_LANCE, RecipeCategory.COMBAT, EWeapons.HELLSTONE_GUNLANCE);
        offerSmithingUpgradeRecipe(exporter, EItems.GUNLANCE_SMITHING_TEMPLATE, EItems.SMOOTHBORE_BARREL, EWeapons.DETONIUM_LANCE, RecipeCategory.COMBAT, EWeapons.DETONIUM_GUNLANCE);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EItems.SMOOTHBORE_BARREL, 1)
                .pattern("  M")
                .pattern(" M ")
                .pattern("M  ")
                .input('M', ETags.Items.STORAGE_BLOCKS_TUNGSTEN)
                .criterion("has_tungsten", conditionsFromTag(ETags.Items.RAW_MATERIALS_TUNGSTEN))
                .offerTo(exporter, "smoothbore_barrel_from_tungsten");
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EItems.GUNLANCE_SHELL, 4)
                .pattern(" t ")
                .pattern("mgm")
                .pattern("mgm")
                .input('m', ConventionalItemTags.COPPER_INGOTS)
                .input('g', ConventionalItemTags.GUNPOWDERS)
                .input('t', ETags.Items.INGOTS_TUNGSTEN)
                .criterion("has_tungsten", conditionsFromTag(ETags.Items.RAW_MATERIALS_TUNGSTEN))
                .offerTo(exporter, "gunlance_shell");
        //endregion

        //region Magic Recipes
        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, EItems.MAGIC_DUST,
                RecipeCategory.REDSTONE, EBlocks.RAW_MAGIC_BLOCK,
                getRecipeName(EItems.MAGIC_DUST) + "_to_" + getRecipeName(EBlocks.RAW_MAGIC_BLOCK) + "_compacting", null,
                getRecipeName(EBlocks.RAW_MAGIC_BLOCK) + "_to_" + getRecipeName(EItems.MAGIC_DUST) + "_expanding", null
        );

        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, EItems.MAGIC_CRYSTAL,
                RecipeCategory.REDSTONE, EBlocks.MAGIC_BLOCK,
                getRecipeName(EItems.MAGIC_CRYSTAL) + "_to_" + getRecipeName(EBlocks.MAGIC_BLOCK) + "_compacting", null,
                getRecipeName(EBlocks.MAGIC_BLOCK) + "_to_" + getRecipeName(EItems.MAGIC_CRYSTAL) + "_expanding", null
        );

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EItems.MANA_STAR, 1)
                .pattern(" m ")
                .pattern("mpm")
                .pattern("m m")
                .input('p', Items.ENDER_PEARL)
                .input('m', ETags.Items.GEMS_MAGIC)
                .criterion("has_magic", conditionsFromTag(ETags.Items.GEMS_MAGIC))
                .offerTo(exporter, "mana_star_from_ender_pearl_and_magic_crystal");

        offerSmithingUpgradeRecipe(exporter, EItems.MANA_STAR_SMITHING_TEMPLATE, EItems.VERDANT_INGOT, EItems.MANA_STAR, RecipeCategory.COMBAT, EItems.VERDANT_MANA_STAR);
        offerSmithingUpgradeRecipe(exporter, EItems.MANA_STAR_SMITHING_TEMPLATE, EItems.REFINED_AETHERIUM_CRYSTAL, EItems.VERDANT_MANA_STAR, RecipeCategory.COMBAT, EItems.AETHERIUM_MANA_STAR);
        offerSmithingUpgradeRecipe(exporter, EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EItems.AETHERIUM_MANA_STAR, RecipeCategory.COMBAT, EItems.FLAWLESS_AETHERIUM_MANA_STAR);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, EItems.MAGIC_ARROW, 4)
                .pattern("mmm")
                .pattern("mam")
                .pattern("mmm")
                .input('a', Items.ARROW)
                .input('m', ETags.Items.GEMS_MAGIC)
                .criterion("has_magic", conditionsFromTag(ETags.Items.GEMS_MAGIC))
                .offerTo(exporter, "magic_apple_from_apple_and_magic_crystals");

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, EFood.MAGIC_APPLE, 1)
                .pattern("mmm")
                .pattern("mam")
                .pattern("mmm")
                .input('a', Items.APPLE)
                .input('m', ETags.Items.DUSTS_MAGIC)
                .criterion("has_magic", conditionsFromTag(ETags.Items.DUSTS_MAGIC))
                .offerTo(exporter, "magic_arrow_from_arrow_and_magic_dust");

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EBlocks.MAGIC_LAMP, 1)
                .pattern(" m ")
                .pattern("mGm")
                .pattern(" m ")
                .input('m', ETags.Items.GEMS_MAGIC)
                .input('G', Items.GLOWSTONE)
                .criterion("has_magic", conditionsFromTag(ETags.Items.GEMS_MAGIC))
                .offerTo(exporter, "magic_lamp_from_glowstone_and_magic_crystals");

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EBlocks.MAGIC_RAIL, 32)
                .pattern("cmc")
                .pattern("csc")
                .pattern("cmc")
                .input('c', ETags.Items.INGOTS_COBALT)
                .input('m', ETags.Items.GEMS_MAGIC)
                .input('s', Items.STICK)
                .criterion("has_magic", conditionsFromTag(ETags.Items.INGOTS_COBALT))
                .offerTo(exporter, "magic_rail_from_cobalt_and_magic_crystals");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EItems.MANA_STAR_SMITHING_TEMPLATE, 2)
                .input('#', ETags.Items.DUSTS_MAGIC)
                .input('S', EItems.MANA_STAR_SMITHING_TEMPLATE)
                .pattern("#S#")
                .pattern("###")
                .pattern("###")
                .criterion(hasItem(EItems.MANA_STAR_SMITHING_TEMPLATE), conditionsFromItem(EItems.MANA_STAR_SMITHING_TEMPLATE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, EItems.ENDLESS_WATER_BUCKET, 1)
                .pattern("BmB")
                .pattern("mBm")
                .pattern("BmB")
                .input('B', Items.WATER_BUCKET)
                .input('m', EItems.MAGIC_CRYSTAL)
                .criterion(hasItem(EItems.MAGIC_DUST), conditionsFromItem(EItems.MAGIC_DUST))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, EItems.ENDLESS_LAVA_BUCKET, 1)
                .pattern("BNB")
                .pattern("mBm")
                .pattern("BmB")
                .input('N', ConventionalItemTags.NETHER_STARS)
                .input('B', Items.LAVA_BUCKET)
                .input('m', EItems.MAGIC_CRYSTAL)
                .criterion(hasItem(EItems.MAGIC_DUST), conditionsFromItem(EItems.MAGIC_DUST))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, EItems.ENDLESS_MILK_BUCKET, 1)
                .pattern("BmB")
                .pattern("mBm")
                .pattern("BmB")
                .input('B', Items.MILK_BUCKET)
                .input('m', EItems.MAGIC_CRYSTAL)
                .criterion(hasItem(EItems.MAGIC_DUST), conditionsFromItem(EItems.MAGIC_DUST))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.CAKE, 1)
                .pattern(" B ")
                .pattern("ses")
                .pattern("www")
                .input('B', EItems.ENDLESS_MILK_BUCKET)
                .input('s', Items.SUGAR)
                .input('e', ConventionalItemTags.EGGS)
                .input('w', ConventionalItemTags.WHEAT_CROPS)
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter);
        //endregion

        //region Chicken recipes
        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.FOOD, EFood.RAW_CHICKEN_NUGGET,
                RecipeCategory.FOOD, Items.CHICKEN,
                getRecipeName(EFood.RAW_CHICKEN_NUGGET) + "_to_" + getRecipeName(Items.CHICKEN) + "_compacting", null,
                getRecipeName(Items.CHICKEN) + "_to_" + getRecipeName(EFood.RAW_CHICKEN_NUGGET) + "_expanding", null
        );

        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.FOOD, EFood.COOKED_CHICKEN_NUGGET,
                RecipeCategory.FOOD, Items.COOKED_CHICKEN,
                getRecipeName(EFood.COOKED_CHICKEN_NUGGET) + "_to_" + getRecipeName(Items.COOKED_CHICKEN) + "_compacting", null,
                getRecipeName(Items.COOKED_CHICKEN) + "_to_" + getRecipeName(EFood.COOKED_CHICKEN_NUGGET) + "_expanding", null
        );

        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.FOOD, Items.CHICKEN,
                RecipeCategory.FOOD, EBlocks.RAW_CHICKEN_BLOCK,
                getRecipeName(Items.CHICKEN) + "_to_" + getRecipeName(EBlocks.RAW_CHICKEN_BLOCK) + "_compacting", null,
                getRecipeName(EBlocks.RAW_CHICKEN_BLOCK) + "_to_" + getRecipeName(Items.CHICKEN) + "_expanding", null
        );

        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.FOOD, Items.COOKED_CHICKEN,
                RecipeCategory.FOOD, EBlocks.COOKED_CHICKEN_BLOCK,
                getRecipeName(Items.COOKED_CHICKEN) + "_to_" + getRecipeName(EBlocks.COOKED_CHICKEN_BLOCK) + "_compacting", null,
                getRecipeName(EBlocks.COOKED_CHICKEN_BLOCK) + "_to_" + getRecipeName(Items.COOKED_CHICKEN) + "_expanding", null
        );

        offerSmeltingSmokingCampfireCooking(exporter, List.of(EFood.RAW_CHICKEN_NUGGET), RecipeCategory.FOOD, EFood.COOKED_CHICKEN_NUGGET, .03f, 30, "chicken");
        offerSmeltingSmokingCampfireCooking(exporter, List.of(EBlocks.RAW_CHICKEN_BLOCK), RecipeCategory.FOOD, EBlocks.COOKED_CHICKEN_BLOCK, 3.15f, 1800, "chicken");

        //endregion

        // Explosives recipes
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EItems.DETONIUM_DETONATOR, 1)
                .pattern(" R ")
                .pattern("trt")
                .pattern("trt")
                .input('R', Items.LIGHTNING_ROD)
                .input('r', ConventionalItemTags.REDSTONE_DUSTS)
                .input('t', ETags.Items.INGOTS_TUNGSTEN)
                .criterion("has_tungsten", conditionsFromTag(ETags.Items.INGOTS_TUNGSTEN))
                .offerTo(exporter, "detonator_from_tungsten_redstone_and_lightning_rod");

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EBlocks.CHARGE, 1)
                .pattern(" r ")
                .pattern("rTr")
                .pattern(" r ")
                .input('r', ConventionalItemTags.REDSTONE_DUSTS)
                .input('T', Items.TNT)
                .criterion("has_redstone", conditionsFromItem(Items.REDSTONE))
                .offerTo(exporter, "charge_from_tnt_and_redstone");

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EBlocks.DETONIUM_CHARGE, 1)
                .pattern("dRd")
                .pattern("RTR")
                .pattern("dRd")
                .input('d', EItems.DETONIUM_INGOT)
                .input('R', ConventionalItemTags.STORAGE_BLOCKS_REDSTONE)
                .input('T', EBlocks.CHARGE)
                .criterion("has_detonium_ingot", conditionsFromItem(EItems.DETONIUM_INGOT))
                .offerTo(exporter, "detonium_charge_from_charge_and_redstone_and_detonium");

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EBlocks.VERDANT_CHARGE, 1)
                .pattern("dRd")
                .pattern("RTR")
                .pattern("dRd")
                .input('d', EItems.VERDANT_INGOT)
                .input('R', ConventionalItemTags.GOLD_INGOTS)
                .input('T', EBlocks.DETONIUM_CHARGE)
                .criterion("has_detonium_charge", conditionsFromItem(EBlocks.DETONIUM_CHARGE))
                .offerTo(exporter, "verdant_charge_from_ingots_gold_and_detonium_charge");

        // TODO make recipe harder
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, EBlocks.DETONIUM_NUKE, 1)
                .pattern("TDT")
                .pattern("DBD")
                .pattern("TDT")
                .input('D', EBlocks.DETONIUM_CHARGE)
                .input('B', EBlocks.DETONIUM_BLOCK)
                .input('T', EBlocks.CHARGE)
                .criterion("has_detonium_ingot", conditionsFromItem(EItems.DETONIUM_INGOT))
                .offerTo(exporter, "detonium_nuke_from_charges_and_detonium");

        // Medallion of Space recipes
        offerMedallionOfSpaceRecipe(exporter, "stone", EItems.MEDALLION_OF_SPACE_STONE, ConventionalItemTags.CHESTS, ItemTags.STONE_TOOL_MATERIALS);
        offerMedallionOfSpaceRecipe(exporter, "tungsten", EItems.MEDALLION_OF_SPACE_TUNGSTEN, EItems.MEDALLION_OF_SPACE_STONE, ETags.Items.INGOTS_TUNGSTEN);
        offerMedallionOfSpaceRecipe(exporter, "demonite", EItems.MEDALLION_OF_SPACE_DEMONITE, EItems.MEDALLION_OF_SPACE_TUNGSTEN, ETags.Items.INGOTS_DEMONITE);
        offerMedallionOfSpaceRecipe(exporter, "cobalt", EItems.MEDALLION_OF_SPACE_COBALT, EItems.MEDALLION_OF_SPACE_DEMONITE, ETags.Items.INGOTS_DEMONITE);
        offerMedallionOfSpaceRecipe(exporter, "verdant", EItems.MEDALLION_OF_SPACE_VERDANT, EItems.MEDALLION_OF_SPACE_COBALT, ETags.Items.INGOTS_DEMONITE);
        offerMedallionOfSpaceRecipe(exporter, "aetherium", EItems.MEDALLION_OF_SPACE_AETHERIUM, EItems.MEDALLION_OF_SPACE_VERDANT, ETags.Items.INGOTS_DEMONITE);
        offerSmithingUpgradeRecipe(exporter, EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EItems.MEDALLION_OF_SPACE_AETHERIUM, RecipeCategory.MISC, EItems.MEDALLION_OF_SPACE_FLAWLESS_AETHERIUM);

        // Base material lance recipes
        offerLanceRecipe(exporter, "wood", EWeapons.WOODEN_LANCE, ItemTags.PLANKS);
        offerLanceRecipe(exporter, "stone", EWeapons.STONE_LANCE, ItemTags.STONE_TOOL_MATERIALS);
        offerLanceRecipe(exporter, "iron", EWeapons.IRON_LANCE, ConventionalItemTags.IRON_INGOTS);
        offerLanceRecipe(exporter, "gold", EWeapons.GOLDEN_LANCE, ConventionalItemTags.GOLD_INGOTS);
        offerLanceRecipe(exporter, "diamond", EWeapons.DIAMOND_LANCE, ConventionalItemTags.DIAMOND_GEMS);

        offerNetheriteUpgradeRecipe(exporter, EWeapons.DIAMOND_LANCE, RecipeCategory.COMBAT, EWeapons.NETHERITE_LANCE);
        offerSmeltingBlasting(exporter, List.of(EWeapons.IRON_LANCE), RecipeCategory.MISC, Items.IRON_NUGGET, .1f, 200, "iron");
        offerSmeltingBlasting(exporter, List.of(EWeapons.GOLDEN_LANCE), RecipeCategory.MISC, Items.GOLD_NUGGET, .1f, 200, "gold");

        offerPicksawRecipe(exporter, "wood", EWeapons.WOODEN_PICKSAW, Items.WOODEN_PICKAXE, Items.WOODEN_SHOVEL, Items.WOODEN_AXE, ItemTags.PLANKS);
        offerPicksawRecipe(exporter, "stone", EWeapons.STONE_PICKSAW, Items.STONE_PICKAXE, Items.STONE_SHOVEL, Items.STONE_AXE, ItemTags.STONE_TOOL_MATERIALS);
        offerPicksawRecipe(exporter, "iron", EWeapons.IRON_PICKSAW, Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_AXE, ConventionalItemTags.IRON_INGOTS);
        offerPicksawRecipe(exporter, "gold", EWeapons.GOLDEN_PICKSAW, Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_AXE, ConventionalItemTags.GOLD_INGOTS);
        offerPicksawRecipe(exporter, "diamond", EWeapons.DIAMOND_PICKSAW, Items.DIAMOND_PICKAXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_AXE, ConventionalItemTags.DIAMOND_GEMS);
        offerPicksawRecipe(exporter, "netherite", EWeapons.NETHERITE_PICKSAW, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_AXE, ConventionalItemTags.NETHERITE_INGOTS);
        offerNetheriteUpgradeRecipe(exporter, EWeapons.DIAMOND_PICKSAW, RecipeCategory.COMBAT, EWeapons.NETHERITE_PICKSAW);
        offerSmeltingBlasting(exporter, List.of(EWeapons.IRON_PICKSAW), RecipeCategory.MISC, Items.IRON_NUGGET, .1f, 200, "iron");
        offerSmeltingBlasting(exporter, List.of(EWeapons.GOLDEN_PICKSAW), RecipeCategory.MISC, Items.GOLD_NUGGET, .1f, 200, "gold");

        // Base material staff recipes
        offerStaffRecipe(exporter, "iron", EWeapons.IRON_STAFF, ConventionalItemTags.IRON_INGOTS);
        offerStaffRecipe(exporter, "gold", EWeapons.GOLDEN_STAFF, ConventionalItemTags.GOLD_INGOTS);
        offerSmeltingBlasting(exporter, List.of(EWeapons.IRON_STAFF), RecipeCategory.MISC, Items.IRON_NUGGET, .1f, 200, "iron");
        offerSmeltingBlasting(exporter, List.of(EWeapons.GOLDEN_STAFF), RecipeCategory.MISC, Items.GOLD_NUGGET, .1f, 200, "gold");

        // Specialty Recipes
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EWeapons.BLADE_OF_GRASS, 1)
                .pattern("vlv")
                .pattern("mlm")
                .pattern("sLs")
                .input('v', Items.VINE)
                .input('l', Items.JUNGLE_LEAVES)
                .input('m', Items.MOSS_BLOCK)
                .input('s', Items.STICK)
                .input('L', Items.JUNGLE_WOOD)
                .criterion("has_jungle_log", conditionsFromItem(Items.JUNGLE_LOG))
                .offerTo(exporter, "blade_of_grass_from_jungle_materials");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, EWeapons.NIGHTS_EDGE, 1)
                .input(EWeapons.DEMONITE_SWORD)
                .input(EWeapons.BLADE_OF_GRASS)
                .input(EWeapons.VOLCANO)
                .input(EWeapons.MURAMASA)
                .criterion("has_muramasa", conditionsFromItem(EWeapons.MURAMASA))
                .offerTo(exporter, "nights_edge_from_four_swords");

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, EWeapons.MAGIC_LASER_DRILL, 1)
                .pattern("iCi")
                .pattern("iSL")
                .pattern("iii")
                .input('S', EItems.MANA_STAR)
                .input('i', ConventionalItemTags.IRON_INGOTS)
                .input('C', EBlocks.CINCINNASITE_DIAMOND_BLOCK.asItem())
                .input('L', EBlocks.MAGIC_LAMP.asItem())
                .criterion("has_magic", conditionsFromItem(EItems.MAGIC_DUST))
                .offerTo(exporter, "magic_laser_drill");


        // Cincinnasite-Diamond Recipes
        offerLanceRecipe(exporter, "cincinnasite_diamond", EWeapons.CINCINNASITE_DIAMOND_LANCE, ETags.Items.INGOTS_CINCINNASITE_DIAMOND);
        offerSmithingUpgradeRecipe(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_INGOT, EWeapons.CINCINNASITE_DIAMOND_PICKSAW, RecipeCategory.COMBAT, EWeapons.NETHERITE_PICKSAW);

        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, EItems.CINCINNASITE_DIAMOND_NUGGET,
                RecipeCategory.MISC, EItems.CINCINNASITE_DIAMOND_INGOT,
                getRecipeName(EItems.CINCINNASITE_DIAMOND_NUGGET) + "_to_" + getRecipeName(EItems.CINCINNASITE_DIAMOND_INGOT) + "_compacting", null,
                getRecipeName(EItems.CINCINNASITE_DIAMOND_INGOT) + "_to_" + getRecipeName(EItems.CINCINNASITE_DIAMOND_NUGGET) + "_expanding", null
        );

        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, EItems.CINCINNASITE_DIAMOND_INGOT,
                RecipeCategory.MISC, EBlocks.CINCINNASITE_DIAMOND_BLOCK.asItem(),
                getRecipeName(EItems.CINCINNASITE_DIAMOND_INGOT) + "_to_" + getRecipeName(EBlocks.CINCINNASITE_DIAMOND_BLOCK.asItem()) + "_compacting", null,
                getRecipeName(EBlocks.CINCINNASITE_DIAMOND_BLOCK.asItem()) + "_to_" + getRecipeName(EItems.CINCINNASITE_DIAMOND_INGOT) + "_expanding", null
        );

        // Copper Recipes
        offerToolsAndArmorWithNuggets(
                exporter, "copper",
                EWeapons.COPPER_SWORD, EWeapons.COPPER_LANCE, EWeapons.COPPER_AXE, EWeapons.COPPER_PICKAXE, EWeapons.COPPER_SHOVEL, EWeapons.COPPER_HOE, EWeapons.COPPER_PICKSAW, EWeapons.COPPER_STAFF,
                EArmor.COPPER_HELMET, EArmor.COPPER_CHESTPLATE, EArmor.COPPER_LEGGINGS, EArmor.COPPER_BOOTS,
                ConventionalItemTags.COPPER_INGOTS,
                EItems.COPPER_NUGGET
        );

        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, EItems.COPPER_NUGGET,
                RecipeCategory.MISC, Items.COPPER_INGOT,
                getRecipeName(EItems.COPPER_NUGGET) + "_to_" + getRecipeName(Items.COPPER_INGOT) + "_compacting", null,
                getRecipeName(Items.COPPER_INGOT) + "_to_" + getRecipeName(EItems.COPPER_NUGGET) + "_expanding", null
        );

        // Tungsten Recipes
        offerFullSuite(
                exporter, "tungsten",
                List.of(EItems.RAW_TUNGSTEN, EBlocks.TUNGSTEN_ORE, EBlocks.DEEPSLATE_TUNGSTEN_ORE),
                EItems.RAW_TUNGSTEN, EBlocks.RAW_TUNGSTEN_BLOCK,
                EItems.TUNGSTEN_NUGGET, EItems.TUNGSTEN_INGOT, EBlocks.TUNGSTEN_BLOCK,
                ETags.Items.STORAGE_BLOCKS_RAW_TUNGSTEN, ETags.Items.STORAGE_BLOCKS_TUNGSTEN, ETags.Items.ORES_TUNGSTEN,
                ETags.Items.RAW_MATERIALS_TUNGSTEN, ETags.Items.INGOTS_TUNGSTEN,
                EWeapons.TUNGSTEN_SWORD, EWeapons.TUNGSTEN_LANCE, EWeapons.TUNGSTEN_AXE, EWeapons.TUNGSTEN_PICKAXE, EWeapons.TUNGSTEN_SHOVEL, EWeapons.TUNGSTEN_HOE, EWeapons.TUNGSTEN_PICKSAW, EWeapons.TUNGSTEN_STAFF,
                EArmor.TUNGSTEN_HELMET, EArmor.TUNGSTEN_CHESTPLATE, EArmor.TUNGSTEN_LEGGINGS, EArmor.TUNGSTEN_BOOTS
        );

        // Demonite Recipes
        offerCompactingTrioRecipes(exporter, EItems.RAW_DEMONITE, EBlocks.RAW_DEMONITE_BLOCK, EItems.DEMONITE_NUGGET, EItems.DEMONITE_INGOT, EBlocks.DEMONITE_BLOCK);
        offerToolsAndArmorWithNuggets(
                exporter, "demonite",
                EWeapons.DEMONITE_SWORD, EWeapons.DEMONITE_LANCE, EWeapons.DEMONITE_AXE, EWeapons.DEMONITE_PICKAXE,
                EWeapons.DEMONITE_SHOVEL, EWeapons.DEMONITE_HOE, EWeapons.DEMONITE_PICKSAW, EWeapons.DEMONITE_STAFF,
                EArmor.DEMONITE_HELMET, EArmor.DEMONITE_CHESTPLATE, EArmor.DEMONITE_LEGGINGS, EArmor.DEMONITE_BOOTS,
                ETags.Items.INGOTS_DEMONITE, EItems.DEMONITE_NUGGET
        );

        // Hellstone Recipes
        /*AlloyForgeryRecipeBuilder.create(ModTags.Items.INGOTS_HELLSTONE)
                .input(ModTags.Items.RAW_MATERIALS_HELLSTONE, 3)
                .input(ConventionalItemTags.OBSIDIANS, 1)
                .setMinimumForgeTier(2)
                .overrideRange(3, true, 3)
                .setFuelPerTick(90)
                .group("hellstone")
                .offerTo(exporter, "forge_hellstone_ingot_from_hellstone_and_obsidian");*/
        offerCompactingTrioRecipes(exporter,
                EItems.RAW_HELLSTONE, EBlocks.RAW_HELLSTONE_BLOCK,
                EItems.HELLSTONE_NUGGET, EItems.HELLSTONE_INGOT, EBlocks.HELLSTONE_BLOCK
        );
        offerToolsAndArmorWithNuggets(exporter, "hellstone",
                EWeapons.HELLSTONE_SWORD, EWeapons.HELLSTONE_LANCE, EWeapons.HELLSTONE_AXE, EWeapons.HELLSTONE_PICKAXE, EWeapons.HELLSTONE_SHOVEL, EWeapons.HELLSTONE_HOE, EWeapons.HELLSTONE_PICKSAW, EWeapons.HELLSTONE_STAFF,
                EArmor.HELLSTONE_HELMET, EArmor.HELLSTONE_CHESTPLATE, EArmor.HELLSTONE_LEGGINGS, EArmor.HELLSTONE_BOOTS,
                ETags.Items.INGOTS_HELLSTONE, EItems.HELLSTONE_NUGGET
        );

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EWeapons.VOLCANO, 1)
                .pattern("B")
                .pattern("B")
                .pattern("i")
                .input('B', EBlocks.HELLSTONE_BLOCK)
                .input('i', EItems.HELLSTONE_INGOT)
                .criterion("has_hellstone_ingot", conditionsFromItem(EItems.HELLSTONE_INGOT))
                .offerTo(exporter, "volcano_from_hellstone_block_and_hellstone_ingot");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EBlocks.HELLSTONE_BRICKS, 8)
                .pattern(" b ")
                .pattern("bhb")
                .pattern(" b ")
                .input('b', Items.BRICK)
                .input('h', EItems.RAW_HELLSTONE)
                .criterion("has_raw_hellstone", conditionsFromItem(EItems.RAW_HELLSTONE))
                .offerTo(exporter, "hellstone_bricks_from_bricks_and_raw_hellstone");

        // Cobalt Recipes
        offerImprovedFullSuite(exporter, "cobalt",
                List.of(EItems.RAW_COBALT, EBlocks.COBALT_ORE, EBlocks.DEEPSLATE_COBALT_ORE),
                EItems.RAW_COBALT, EBlocks.RAW_COBALT_BLOCK,
                EItems.COBALT_NUGGET, EItems.COBALT_INGOT, EBlocks.COBALT_BLOCK,
                ETags.Items.STORAGE_BLOCKS_RAW_COBALT, ETags.Items.STORAGE_BLOCKS_COBALT, ETags.Items.ORES_COBALT,
                ETags.Items.RAW_MATERIALS_COBALT, ETags.Items.INGOTS_COBALT,
                EWeapons.COBALT_SWORD, EWeapons.COBALT_LANCE, EWeapons.COBALT_BATTLEAXE, EWeapons.COBALT_PICKAXE, EWeapons.COBALT_SHOVEL, EWeapons.COBALT_SCYTHE, EWeapons.COBALT_PICKSAW,
                EArmor.COBALT_HELMET, EArmor.COBALT_CHESTPLATE, EArmor.COBALT_LEGGINGS, EArmor.COBALT_BOOTS
        );

        // Orichalcum Recipes
        offerImprovedFullSuite(exporter, "orichalcum",
                List.of(EItems.RAW_ORICHALCUM, EBlocks.ORICHALCUM_ORE, EBlocks.DEEPSLATE_ORICHALCUM_ORE),
                EItems.RAW_ORICHALCUM, EBlocks.RAW_ORICHALCUM_BLOCK,
                EItems.ORICHALCUM_NUGGET, EItems.ORICHALCUM_INGOT, EBlocks.ORICHALCUM_BLOCK,
                ETags.Items.STORAGE_BLOCKS_RAW_ORICHALCUM, ETags.Items.STORAGE_BLOCKS_ORICHALCUM, ETags.Items.ORES_ORICHALCUM,
                ETags.Items.RAW_MATERIALS_ORICHALCUM, ETags.Items.INGOTS_ORICHALCUM,
                EWeapons.ORICHALCUM_SWORD, EWeapons.ORICHALCUM_LANCE, EWeapons.ORICHALCUM_BATTLEAXE, EWeapons.ORICHALCUM_PICKAXE, EWeapons.ORICHALCUM_SHOVEL, EWeapons.ORICHALCUM_SCYTHE, EWeapons.ORICHALCUM_PICKSAW,
                EArmor.ORICHALCUM_HELMET, EArmor.ORICHALCUM_CHESTPLATE, EArmor.ORICHALCUM_LEGGINGS, EArmor.ORICHALCUM_BOOTS
        );

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EWeapons.WIND_STAFF)
                .pattern("gSg")
                .pattern("mgm")
                .pattern("mOm")
                .input('S', ConventionalItemTags.NETHER_STARS)
                .input('g', ConventionalItemTags.GOLD_INGOTS)
                .input('m', EItems.MAGIC_CRYSTAL)
                .input('O', EBlocks.ORICHALCUM_BLOCK.asItem())
                .criterion("has_orichalcum", conditionsFromTag(ETags.Items.INGOTS_ORICHALCUM))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EWeapons.FIRE_STAFF)
                .pattern("gSg")
                .pattern("mgm")
                .pattern("mOm")
                .input('S', ConventionalItemTags.NETHER_STARS)
                .input('g', ETags.Items.INGOTS_HELLSTONE)
                .input('m', EItems.MAGIC_CRYSTAL)
                .input('O', EBlocks.ORICHALCUM_BLOCK.asItem())
                .criterion("has_orichalcum", conditionsFromTag(ETags.Items.INGOTS_ORICHALCUM))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EWeapons.ICE_STAFF)
                .pattern("gSg")
                .pattern("mgm")
                .pattern("mOm")
                .input('S', ConventionalItemTags.NETHER_STARS)
                .input('g', ETags.Items.INGOTS_COBALT)
                .input('m', EItems.MAGIC_CRYSTAL)
                .input('O', EBlocks.ORICHALCUM_BLOCK.asItem())
                .criterion("has_orichalcum", conditionsFromTag(ETags.Items.INGOTS_ORICHALCUM))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EWeapons.LIGHTNING_STAFF)
                .pattern("gSg")
                .pattern("mgm")
                .pattern("mOm")
                .input('S', ConventionalItemTags.NETHER_STARS)
                .input('g', ETags.Items.INGOTS_DEMONITE)
                .input('m', EItems.MAGIC_CRYSTAL)
                .input('O', EBlocks.ORICHALCUM_BLOCK.asItem())
                .criterion("has_orichalcum", conditionsFromTag(ETags.Items.INGOTS_ORICHALCUM))
                .offerTo(exporter);

        // Titanium Recipes
        offerImprovedFullSuite(exporter, "titanium",
                List.of(EItems.RAW_TITANIUM, EBlocks.TITANIUM_ORE, EBlocks.DEEPSLATE_TITANIUM_ORE),
                EItems.RAW_TITANIUM, EBlocks.RAW_TITANIUM_BLOCK,
                EItems.TITANIUM_NUGGET, EItems.TITANIUM_INGOT, EBlocks.TITANIUM_BLOCK,
                ETags.Items.STORAGE_BLOCKS_RAW_TITANIUM, ETags.Items.STORAGE_BLOCKS_TITANIUM, ETags.Items.ORES_TITANIUM,
                ETags.Items.RAW_MATERIALS_TITANIUM, ETags.Items.INGOTS_TITANIUM,
                EWeapons.TITANIUM_SWORD, EWeapons.TITANIUM_LANCE, EWeapons.TITANIUM_BATTLEAXE, EWeapons.TITANIUM_PICKAXE, EWeapons.TITANIUM_SHOVEL, EWeapons.TITANIUM_SCYTHE, EWeapons.TITANIUM_PICKSAW,
                EArmor.TITANIUM_HELMET, EArmor.TITANIUM_CHESTPLATE, EArmor.TITANIUM_LEGGINGS, EArmor.TITANIUM_BOOTS
        );

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, EWeapons.BASTARD_SWORD)
                .pattern("dmd")
                .pattern("dmd")
                .pattern("qcq")
                .input('m', EBlocks.TITANIUM_BLOCK.asItem())
                .input('d', EItems.MAGIC_CRYSTAL)
                .input('q', Items.QUARTZ)
                .input('c', Items.COPPER_INGOT)
                .criterion("has_titanium", conditionsFromTag(ETags.Items.INGOTS_TITANIUM))
                .offerTo(exporter);

        // Hallowed Recipes
        offerImprovedToolsAndArmorWithNuggets(exporter, "hallowed",
                EWeapons.HALLOWED_SWORD, EWeapons.HALLOWED_LANCE, EWeapons.HALLOWED_BATTLEAXE, EWeapons.HALLOWED_PICKAXE,
                EWeapons.HALLOWED_SHOVEL, EWeapons.HALLOWED_SCYTHE, EWeapons.HALLOWED_PICKSAW,
                EArmor.HALLOWED_HELMET, EArmor.HALLOWED_CHESTPLATE, EArmor.HALLOWED_LEGGINGS, EArmor.HALLOWED_BOOTS,
                ETags.Items.INGOTS_HALLOWED, EItems.HALLOWED_NUGGET);

        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, EItems.HALLOWED_NUGGET,
                RecipeCategory.MISC, EItems.HALLOWED_INGOT,
                getRecipeName(EItems.HALLOWED_NUGGET) + "_to_" + getRecipeName(EItems.HALLOWED_INGOT) + "_compacting", "hallowed",
                getRecipeName(EItems.HALLOWED_INGOT) + "_to_" + getRecipeName(EItems.HALLOWED_NUGGET) + "_expanding", "hallowed"
        );

        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, EItems.HALLOWED_INGOT,
                RecipeCategory.BUILDING_BLOCKS, EBlocks.HALLOWED_BLOCK,
                getRecipeName(EItems.HALLOWED_INGOT) + "_to_" + getRecipeName(EBlocks.HALLOWED_BLOCK) + "_compacting", "hallowed",
                getRecipeName(EBlocks.HALLOWED_BLOCK) + "_to_" + getRecipeName(EItems.HALLOWED_INGOT) + "_expanding", "hallowed"
        );

        // Verdant Recipes
        offerImprovedToolsAndArmorWithNuggets(exporter, "verdant",
                EWeapons.VERDANT_SWORD, EWeapons.VERDANT_LANCE, EWeapons.VERDANT_BATTLEAXE, EWeapons.VERDANT_PICKAXE,
                EWeapons.VERDANT_SHOVEL, EWeapons.VERDANT_SCYTHE, EWeapons.VERDANT_PICKSAW,
                EArmor.VERDANT_HELMET, EArmor.VERDANT_CHESTPLATE, EArmor.VERDANT_LEGGINGS, EArmor.VERDANT_BOOTS,
                ETags.Items.INGOTS_VERDANT, EItems.VERDANT_NUGGET);

        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, EItems.VERDANT_NUGGET,
                RecipeCategory.MISC, EItems.VERDANT_INGOT,
                getRecipeName(EItems.VERDANT_NUGGET) + "_to_" + getRecipeName(EItems.VERDANT_INGOT) + "_compacting", "verdant",
                getRecipeName(EItems.VERDANT_INGOT) + "_to_" + getRecipeName(EItems.VERDANT_NUGGET) + "_expanding", "verdant"
        );

        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, EItems.VERDANT_INGOT,
                RecipeCategory.BUILDING_BLOCKS, EBlocks.VERDANT_BLOCK,
                getRecipeName(EItems.VERDANT_INGOT) + "_to_" + getRecipeName(EBlocks.VERDANT_BLOCK) + "_compacting", "verdant",
                getRecipeName(EBlocks.VERDANT_BLOCK) + "_to_" + getRecipeName(EItems.VERDANT_INGOT) + "_expanding", "verdant"
        );

        // Detonium Recipes
        offerImprovedToolsAndArmorWithNuggets(exporter, "detonium",
                EWeapons.DETONIUM_SWORD, EWeapons.DETONIUM_LANCE, EWeapons.DETONIUM_BATTLEAXE, EWeapons.DETONIUM_PICKAXE,
                EWeapons.DETONIUM_SHOVEL, EWeapons.DETONIUM_SCYTHE, EWeapons.DETONIUM_PICKSAW,
                EArmor.DETONIUM_HELMET, EArmor.DETONIUM_CHESTPLATE, EArmor.DETONIUM_LEGGINGS, EArmor.DETONIUM_BOOTS,
                ETags.Items.INGOTS_DETONIUM, EItems.DETONIUM_NUGGET);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, EWeapons.DETONIUM_BLASTAXE)
                .pattern("DDD")
                .pattern(" s ")
                .pattern(" s ")
                .input('D', EBlocks.DETONIUM_BLOCK.asItem())
                .input('s', Items.STICK)
                .criterion("has_detonium", conditionsFromTag(ETags.Items.INGOTS_DETONIUM))
                .offerTo(exporter);

        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, EItems.DETONIUM_NUGGET,
                RecipeCategory.MISC, EItems.DETONIUM_INGOT,
                getRecipeName(EItems.DETONIUM_NUGGET) + "_to_" + getRecipeName(EItems.DETONIUM_INGOT) + "_compacting", "detonium",
                getRecipeName(EItems.DETONIUM_INGOT) + "_to_" + getRecipeName(EItems.DETONIUM_NUGGET) + "_expanding", "detonium"
        );

        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, EItems.DETONIUM_INGOT,
                RecipeCategory.BUILDING_BLOCKS, EBlocks.DETONIUM_BLOCK,
                getRecipeName(EItems.DETONIUM_INGOT) + "_to_" + getRecipeName(EBlocks.DETONIUM_BLOCK) + "_compacting", "detonium",
                getRecipeName(EBlocks.DETONIUM_BLOCK) + "_to_" + getRecipeName(EItems.DETONIUM_INGOT) + "_expanding", "detonium"
        );

        // Aetherium Recipes
        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, EItems.RAW_AETHERIUM_CRYSTAL,
                RecipeCategory.MISC, EItems.REFINED_AETHERIUM_CRYSTAL,
                getRecipeName(EItems.RAW_AETHERIUM_CRYSTAL) + "_to_" + getRecipeName(EItems.REFINED_AETHERIUM_CRYSTAL) + "_compacting", "aetherium",
                getRecipeName(EItems.REFINED_AETHERIUM_CRYSTAL) + "_to_" + getRecipeName(EItems.RAW_AETHERIUM_CRYSTAL) + "_expanding", "aetherium"
        );
        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, EItems.REFINED_AETHERIUM_CRYSTAL,
                RecipeCategory.MISC, EBlocks.REFINED_AETHERIUM_BLOCK,
                getRecipeName(EItems.REFINED_AETHERIUM_CRYSTAL) + "_to_" + getRecipeName(EBlocks.REFINED_AETHERIUM_BLOCK) + "_compacting", "aetherium",
                getRecipeName(EBlocks.REFINED_AETHERIUM_BLOCK) + "_to_" + getRecipeName(EItems.REFINED_AETHERIUM_CRYSTAL) + "_expanding", "aetherium"
        );

        offerSmithingTemplateCopyingRecipe(exporter, EItems.CALIBURN_SMITHING_TEMPLATE, EItems.REFINED_AETHERIUM_CRYSTAL, EItems.TITANIUM_INGOT);
        offerSmithingTemplateCopyingRecipe(exporter, EItems.DURENDAL_SMITHING_TEMPLATE, EItems.REFINED_AETHERIUM_CRYSTAL, EItems.TITANIUM_INGOT);
        offerSmithingTemplateCopyingRecipe(exporter, EItems.SOLAIS_SMITHING_TEMPLATE, EItems.REFINED_AETHERIUM_CRYSTAL, EItems.TITANIUM_INGOT);
        offerSmithingTemplateCopyingRecipe(exporter, EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EItems.REFINED_AETHERIUM_CRYSTAL);

        offerSmithingUpgradeRecipe(exporter, EItems.CALIBURN_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EWeapons.BASTARD_SWORD, RecipeCategory.COMBAT, EWeapons.CALIBURN);
        offerSmithingUpgradeRecipe(exporter, EItems.DURENDAL_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EWeapons.BASTARD_SWORD, RecipeCategory.COMBAT, EWeapons.DURENDAL);
        offerSmithingUpgradeRecipe(exporter, EItems.SOLAIS_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EWeapons.BASTARD_SWORD, RecipeCategory.COMBAT, EWeapons.SOLAIS);

        offerSmithingUpgradeRecipe(exporter, EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EWeapons.NIGHTS_EDGE, RecipeCategory.COMBAT, EWeapons.TRUE_NIGHTS_EDGE);
        offerSmithingUpgradeRecipe(exporter, EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EWeapons.MAGIC_LASER_DRILL, RecipeCategory.TOOLS, EWeapons.AETHERIUM_LASER_DRILL);

        offerSmithingUpgradeRecipe(exporter, EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EWeapons.WIND_STAFF, RecipeCategory.COMBAT, EWeapons.WIND_STAFF_UPGRADED);
        offerSmithingUpgradeRecipe(exporter, EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EWeapons.FIRE_STAFF, RecipeCategory.COMBAT, EWeapons.FIRE_STAFF_UPGRADED);
        offerSmithingUpgradeRecipe(exporter, EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EWeapons.LIGHTNING_STAFF, RecipeCategory.COMBAT, EWeapons.LIGHTNING_STAFF_UPGRADED);
        offerSmithingUpgradeRecipe(exporter, EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EWeapons.ICE_STAFF, RecipeCategory.COMBAT, EWeapons.ICE_STAFF_UPGRADED);

        offerSmithingUpgradeRecipe(exporter, EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EItems.ROD_OF_DISCORD, RecipeCategory.TOOLS, EItems.ROD_OF_HARMONY);

        offerArmorRecipes(exporter, "refined_aetherium", EArmor.REFINED_AETHERIUM_HELMET, EArmor.REFINED_AETHERIUM_CHESTPLATE, EArmor.REFINED_AETHERIUM_LEGGINGS, EArmor.REFINED_AETHERIUM_BOOTS, ETags.Items.GEMS_AETHERIUM);
        offerSmithingUpgradeRecipe(exporter, EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EArmor.REFINED_AETHERIUM_HELMET, RecipeCategory.COMBAT, EArmor.FLAWLESS_AETHERIUM_HELMET);
        offerSmithingUpgradeRecipe(exporter, EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EArmor.REFINED_AETHERIUM_CHESTPLATE, RecipeCategory.COMBAT, EArmor.FLAWLESS_AETHERIUM_CHESTPLATE);
        offerSmithingUpgradeRecipe(exporter, EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EArmor.REFINED_AETHERIUM_LEGGINGS, RecipeCategory.COMBAT, EArmor.FLAWLESS_AETHERIUM_LEGGINGS);
        offerSmithingUpgradeRecipe(exporter, EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EArmor.REFINED_AETHERIUM_BOOTS, RecipeCategory.COMBAT, EArmor.FLAWLESS_AETHERIUM_BOOTS);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EBlocks.AETHERIUM_BRICKS, 8)
                .pattern(" b ")
                .pattern("bhb")
                .pattern(" b ")
                .input('b', Items.BRICK)
                .input('h', EItems.RAW_AETHERIUM_CRYSTAL)
                .criterion("has_raw_aetherium", conditionsFromItem(EItems.RAW_AETHERIUM_CRYSTAL))
                .offerTo(exporter, "aetherium_bricks_from_bricks_and_raw_aetherium");
    }

    //region Combo Crafting Shortcuts
    public static void offerSmithingTemplateCopyingRecipe(RecipeExporter exporter, ItemConvertible template, ItemConvertible singleResouce, ItemConvertible multiResource) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, template, 2)
                .input('#', multiResource)
                .input('C', singleResouce)
                .input('S', template)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .criterion(hasItem(template), conditionsFromItem(template))
                .offerTo(exporter);
    }

    // Doesn't create alloy forge recipes
    private static void offerImprovedFullSuite(
            RecipeExporter exporter,
            String name,
            List<ItemConvertible> smeltables,
            ItemConvertible rawMaterial, ItemConvertible rawMaterialBlock,
            ItemConvertible nugget, ItemConvertible ingot, ItemConvertible processedBlock,
            TagKey<Item> rawStorageBlockTag, TagKey<Item> processedStorageBlockTag, TagKey<Item> oreBlockTag,
            TagKey<Item> rawItemTag, TagKey<Item> ingotTag,
            ItemConvertible sword, ItemConvertible lance, ItemConvertible battleaxe, ItemConvertible pickaxe, ItemConvertible shovel, ItemConvertible scythe, ItemConvertible picksaw,
            ItemConvertible helmet, ItemConvertible chestplate, ItemConvertible leggings, ItemConvertible boots
    ) {
        // Smelt and Blast smeltables into ingot
        offerSmeltingBlasting(exporter, smeltables, RecipeCategory.MISC, ingot, .25f, 200, name);

        // Forge block of raw material into block of processed material
        // createStandardRawBlockRecipe(name, processedStorageBlockTag, rawStorageBlockTag);

        // Forge ingot from raw item
        // createStandardRawOreRecipe(name, ingotTag, rawItemTag);

        // Forge ingot from ore block
        // createStandardOreRecipe(name, ingotTag, oreBlockTag);

        // Offer compacting recipes
        offerCompactingTrioRecipes(exporter, rawMaterial, rawMaterialBlock, nugget, ingot, processedBlock);

        // Offer weapons and armor
        offerImprovedToolsAndArmorWithNuggets(exporter, name, sword, lance, battleaxe, pickaxe, shovel, scythe, picksaw, helmet, chestplate, leggings, boots, ingotTag, nugget);
    }

    private static void offerFullSuite(
            RecipeExporter exporter,
            String name,
            List<ItemConvertible> smeltables,
            ItemConvertible rawMaterial, ItemConvertible rawMaterialBlock,
            ItemConvertible nugget, ItemConvertible ingot, ItemConvertible processedBlock,
            TagKey<Item> rawStorageBlockTag, TagKey<Item> processedStorageBlockTag, TagKey<Item> oreBlockTag,
            TagKey<Item> rawItemTag, TagKey<Item> ingotTag,
            ItemConvertible sword, ItemConvertible lance, ItemConvertible axe, ItemConvertible pickaxe, ItemConvertible shovel, ItemConvertible hoe, ItemConvertible picksaw, ItemConvertible staff,
            ItemConvertible helmet, ItemConvertible chestplate, ItemConvertible leggings, ItemConvertible boots
    ) {
        // Smelt and Blast smeltables into ingot
        offerSmeltingBlasting(exporter, smeltables, RecipeCategory.MISC, ingot, .25f, 200, name);

        // Forge block of raw material into block of processed material
        // createStandardRawBlockRecipe(name, processedStorageBlockTag, rawStorageBlockTag);

        // Forge ingot from raw item
        // createStandardRawOreRecipe(name, ingotTag, rawItemTag);

        // Forge ingot from ore block
        // createStandardOreRecipe(name, ingotTag, oreBlockTag);

        // Offer compacting recipes
        offerCompactingTrioRecipes(exporter, rawMaterial, rawMaterialBlock, nugget, ingot, processedBlock);

        // Offer weapons and armor
        offerToolsAndArmorWithNuggets(exporter, name, sword, lance, axe, pickaxe, shovel, hoe, picksaw, staff, helmet, chestplate, leggings, boots, ingotTag, nugget);
    }

    private static void offerCompactingTrioRecipes(RecipeExporter exporter, ItemConvertible rawMaterial, ItemConvertible rawMaterialBlock, ItemConvertible nugget, ItemConvertible ingot, ItemConvertible processedBlock) {
        // Compact ingot and processed block
        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, ingot,
                RecipeCategory.BUILDING_BLOCKS, processedBlock,
                getRecipeName(ingot) + "_to_" + getRecipeName(processedBlock) + "_compacting", null,
                getRecipeName(processedBlock) + "_to_" + getRecipeName(ingot) + "_expanding", null
        );

        // Compact raw material and block of raw material
        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, rawMaterial,
                RecipeCategory.BUILDING_BLOCKS, rawMaterialBlock,
                getRecipeName(rawMaterial) + "_to_" + getRecipeName(rawMaterialBlock) + "_compacting", null,
                getRecipeName(rawMaterialBlock) + "_to_"+ getRecipeName(rawMaterial) + "_expanding", null
        );

        // Compact nugget and ingot
        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.MISC, nugget,
                RecipeCategory.MISC, ingot,
                getRecipeName(nugget) + "_to_" + getRecipeName(ingot) + "_compacting", null,
                getRecipeName(ingot) + "_to_" + getRecipeName(nugget) + "_expanding", null
        );
    }

    private static void offerImprovedToolsAndArmorWithNuggets(
            RecipeExporter exporter,
            String name,
            ItemConvertible sword,
            ItemConvertible lance,
            ItemConvertible battleaxe,
            ItemConvertible pickaxe,
            ItemConvertible shovel,
            ItemConvertible scythe,
            ItemConvertible picksaw,
            ItemConvertible helmet,
            ItemConvertible chestplate,
            ItemConvertible leggings,
            ItemConvertible boots,
            TagKey<Item> ingotTag,
            ItemConvertible nugget
    ) {
        // Create tools
        offerImprovedToolRecipes(exporter, name, sword, lance, battleaxe, pickaxe, shovel, scythe, picksaw, ingotTag);

        // Create armors
        offerArmorRecipes(exporter, name, helmet, chestplate, leggings, boots, ingotTag);

        // Smelt or blast the tools and armors into nuggets
        offerSmeltingBlasting(
                exporter,
                List.of(sword, lance, battleaxe, pickaxe, shovel, scythe, picksaw, helmet, chestplate, leggings, boots),
                RecipeCategory.MISC,
                nugget,
                .1f,
                200,
                name
        );
    }

    private static void offerToolsAndArmorWithNuggets(
            RecipeExporter exporter,
            String name,
            ItemConvertible sword,
            ItemConvertible lance,
            ItemConvertible axe,
            ItemConvertible pickaxe,
            ItemConvertible shovel,
            ItemConvertible hoe,
            ItemConvertible picksaw,
            ItemConvertible staff,
            ItemConvertible helmet,
            ItemConvertible chestplate,
            ItemConvertible leggings,
            ItemConvertible boots,
            TagKey<Item> ingotTag,
            ItemConvertible nugget
    ) {
        // Create tools
        offerToolRecipes(exporter, name, sword, lance, axe, pickaxe, shovel, hoe, picksaw, staff, ingotTag);

        // Create armors
        offerArmorRecipes(exporter, name, helmet, chestplate, leggings, boots, ingotTag);

        // Smelt or blast the tools and armors into nuggets
        offerSmeltingBlasting(
                exporter,
                List.of(sword, lance, axe, pickaxe, shovel, hoe, picksaw, staff, helmet, chestplate, leggings, boots),
                RecipeCategory.MISC,
                nugget,
                .1f,
                200,
                name
        );
    }

    private static void offerImprovedToolRecipes(
            RecipeExporter exporter,
            String criterionName,
            ItemConvertible sword,
            ItemConvertible lance,
            ItemConvertible battleaxe,
            ItemConvertible pickaxe,
            ItemConvertible shovel,
            ItemConvertible scythe,
            ItemConvertible picksaw,
            TagKey<Item> material
    ) {
        offerSwordRecipe(exporter, criterionName, sword, material);
        offerLanceRecipe(exporter, criterionName, lance, material);
        offerBattleaxeRecipe(exporter, criterionName, battleaxe, material);
        offerPickaxeRecipe(exporter, criterionName, pickaxe, material);
        offerShovelRecipe(exporter, criterionName, shovel, material);
        offerScytheRecipe(exporter, criterionName, scythe, material);
        offerPicksawRecipe(exporter, criterionName, picksaw, pickaxe, shovel, battleaxe, material);
    }

    private static void offerToolRecipes(
            RecipeExporter exporter,
            String criterionName,
            ItemConvertible sword,
            ItemConvertible lance,
            ItemConvertible axe,
            ItemConvertible pickaxe,
            ItemConvertible shovel,
            ItemConvertible hoe,
            ItemConvertible picksaw,
            ItemConvertible staff,
            TagKey<Item> material
    ) {
        offerSwordRecipe(exporter, criterionName, sword, material);
        offerLanceRecipe(exporter, criterionName, lance, material);
        offerAxeRecipe(exporter, criterionName, axe, material);
        offerPickaxeRecipe(exporter, criterionName, pickaxe, material);
        offerShovelRecipe(exporter, criterionName, shovel, material);
        offerHoeRecipe(exporter, criterionName, hoe, material);
        offerPicksawRecipe(exporter, criterionName, picksaw, pickaxe, shovel, axe, material);
        offerStaffRecipe(exporter, criterionName, staff, material);
    }

    private static void offerArmorRecipes(
            RecipeExporter exporter,
            String criterionName,
            ItemConvertible helmet,
            ItemConvertible chestplate,
            ItemConvertible leggings,
            ItemConvertible boots,
            TagKey<Item> material
    ) {
        offerHelmetRecipe(exporter, criterionName, helmet, material);
        offerChestplateRecipe(exporter, criterionName, chestplate, material);
        offerLeggingsRecipe(exporter, criterionName, leggings, material);
        offerBootsRecipe(exporter, criterionName, boots, material);
    }

    private static void offerSmeltingBlasting(
            RecipeExporter exporter,
            @NotNull List<ItemConvertible> input,
            RecipeCategory category,
            ItemConvertible output,
            float xp,
            int cookingTime,
            String group
    )
    {
        offerSmelting(
                exporter,
                input,
                category,
                output,
                xp,
                cookingTime,
                group + "_smelting"
        );

        offerBlasting(
                exporter,
                input,
                category,
                output,
                xp,
                cookingTime / 2,
                group + "_blasting"
        );
    }
    //endregion

    //region Additional Crafting Offerings
    private static void offerHelmetRecipe(RecipeExporter exporter, String criterionName, ItemConvertible helmetOutput, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, helmetOutput)
                .pattern("MMM")
                .pattern("M M")
                .input('M', material)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);
    }

    private static void offerChestplateRecipe(RecipeExporter exporter, String criterionName, ItemConvertible chestplateOutput, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, chestplateOutput)
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .input('M', material)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);
    }

    private static void offerLeggingsRecipe(RecipeExporter exporter, String criterionName, ItemConvertible leggingsOutput, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, leggingsOutput)
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .input('M', material)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);
    }

    private static void offerBootsRecipe(RecipeExporter exporter, String criterionName, ItemConvertible bootsOutput, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, bootsOutput)
                .pattern("M M")
                .pattern("M M")
                .input('M', material)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);
    }

    private static void offerLanceRecipe(RecipeExporter exporter, String criterionName, ItemConvertible lanceOutput, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, lanceOutput)
                .pattern("  M")
                .pattern(" s ")
                .pattern("s  ")
                .input('M', material)
                .input('s', Items.STICK)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);
    }

    private static void offerPicksawRecipe(RecipeExporter exporter, String criterionName, ItemConvertible picksawOutput, ItemConvertible pickaxeInput, ItemConvertible shovelInput, ItemConvertible axeInput, TagKey<Item> material) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, picksawOutput)
                .input(pickaxeInput)
                .input(shovelInput)
                .input(axeInput)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);
    }

    private static void offerStaffRecipe(RecipeExporter exporter, String criterionName, ItemConvertible staffOutput, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, staffOutput)
                .pattern("mGm")
                .pattern(" M ")
                .pattern(" M ")
                .input('G', ((StaffItem) staffOutput.asItem()).gemItem)
                .input('m', ETags.Items.GEMS_MAGIC)
                .input('M', material)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);
    }

    private static void offerSwordRecipe(RecipeExporter exporter, String criterionName, ItemConvertible swordOutput, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, swordOutput)
                .pattern("M")
                .pattern("M")
                .pattern("s")
                .input('M', material)
                .input('s', Items.STICK)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);
    }

    private static void offerAxeRecipe(RecipeExporter exporter, String criterionName, ItemConvertible axeOutput, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, axeOutput)
                .pattern("MM")
                .pattern("Ms")
                .pattern(" s")
                .input('M', material)
                .input('s', Items.STICK)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);
    }

    private static void offerBattleaxeRecipe(RecipeExporter exporter, String criterionName, ItemConvertible battleaxeOutput, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, battleaxeOutput)
                .pattern("MMM")
                .pattern("MsM")
                .pattern(" s ")
                .input('M', material)
                .input('s', Items.STICK)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);
    }

    private static void offerShovelRecipe(RecipeExporter exporter, String criterionName, ItemConvertible shovelOutput, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, shovelOutput)
                .pattern("M")
                .pattern("s")
                .pattern("s")
                .input('M', material)
                .input('s', Items.STICK)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);
    }

    private static void offerPickaxeRecipe(RecipeExporter exporter, String criterionName, ItemConvertible pickaxeOutput, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, pickaxeOutput)
                .pattern("MMM")
                .pattern(" s ")
                .pattern(" s ")
                .input('M', material)
                .input('s', Items.STICK)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);
    }

    private static void offerHoeRecipe(RecipeExporter exporter, String criterionName, ItemConvertible hoeOutput, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, hoeOutput)
                .pattern("MM")
                .pattern(" s")
                .pattern(" s")
                .input('M', material)
                .input('s', Items.STICK)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);
    }

    private static void offerScytheRecipe(RecipeExporter exporter, String criterionName, ItemConvertible scytheOutput, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, scytheOutput)
                .pattern("MMM")
                .pattern("M s")
                .pattern("  s")
                .input('M', material)
                .input('s', Items.STICK)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);
    }

    private static void offerMedallionOfSpaceRecipe(RecipeExporter exporter, String criterionName, ItemConvertible upgradedMedallion, ItemConvertible baseMedallion, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, upgradedMedallion)
                .pattern(" m ")
                .pattern("mMm")
                .pattern(" m ")
                .input('M', baseMedallion)
                .input('m', material)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);

    }

    private static void offerMedallionOfSpaceRecipe(RecipeExporter exporter, String criterionName, ItemConvertible upgradedMedallion, TagKey<Item> baseMedallion, TagKey<Item> material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, upgradedMedallion)
                .pattern(" m ")
                .pattern("mMm")
                .pattern(" m ")
                .input('M', baseMedallion)
                .input('m', material)
                .criterion("has_" + criterionName, conditionsFromTag(material))
                .offerTo(exporter);

    }

    public static void offerSmeltingSmokingCampfireCooking(RecipeExporter exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group) {
        offerSmelting(exporter, inputs, category, output, experience, cookingTime, group);
        offerSmoking(exporter, inputs, category, output, experience, cookingTime / 2, group);
        offerCampfireCooking(exporter, inputs, category, output, experience, cookingTime * 3, group);
    }

    public static void offerSmoking(RecipeExporter exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group) {
        offerMultipleOptions(exporter, RecipeSerializer.SMOKING, SmokingRecipe::new, inputs, category, output, experience, cookingTime, group, "_from_smoking");
    }

    public static void offerCampfireCooking(RecipeExporter exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group) {
        offerMultipleOptions(exporter, RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new, inputs, category, output, experience, cookingTime, group, "_from_campfire_cooking");
    }

    public static void offerSmithingUpgradeRecipe(RecipeExporter exporter, ItemConvertible smithingTemplate, ItemConvertible upgradeItem, Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(smithingTemplate), Ingredient.ofItems(input), Ingredient.ofItems(upgradeItem), category, result
                )
                .criterion("has_netherite_ingot", conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter, getItemPath(input) + "_to_" + getItemPath(result) + "_using_" + getItemPath(upgradeItem) + "_smithing");
    }
    //endregion
}
