package dev.night.ecraft.datagen;

import dev.night.ecraft.block.EBlocks;
import dev.night.ecraft.item.EArmor;
import dev.night.ecraft.item.EWeapons;
import dev.night.ecraft.item.EItems;
import dev.night.ecraft.item.food.EFood;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

public class EModelProvider extends FabricModelProvider {

    public EModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSingleton(EBlocks.CHARGE, TextureMap.sideEnd(EBlocks.CHARGE), Models.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(EBlocks.DETONIUM_CHARGE, TextureMap.sideEnd(EBlocks.DETONIUM_CHARGE), Models.CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(EBlocks.VERDANT_CHARGE, TextureMap.sideEnd(EBlocks.VERDANT_CHARGE), Models.CUBE_COLUMN);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DETONIUM_NUKE);

        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DEMONITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DEEPSLATE_DEMONITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.RAW_DEMONITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DEMONITE_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.RAW_DIAMOND_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.MAGIC_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DIRT_MAGIC_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.ANDESITE_MAGIC_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DIORITE_MAGIC_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.GRANITE_MAGIC_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DEEPSLATE_MAGIC_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.TUFF_MAGIC_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.RAW_MAGIC_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.MAGIC_BLOCK);
        registerLamp(blockStateModelGenerator, EBlocks.MAGIC_LAMP);
        blockStateModelGenerator.registerTurnableRail(EBlocks.MAGIC_RAIL);

        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.TUNGSTEN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DEEPSLATE_TUNGSTEN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.RAW_TUNGSTEN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.TUNGSTEN_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.HELLSTONE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.HELLSTONE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.RAW_HELLSTONE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.HELLSTONE_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.COBALT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DEEPSLATE_COBALT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.RAW_COBALT_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.COBALT_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.ORICHALCUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DEEPSLATE_ORICHALCUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.RAW_ORICHALCUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.ORICHALCUM_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.TITANIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DEEPSLATE_TITANIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.RAW_TITANIUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.TITANIUM_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.HALLOWED_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.VERDANT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DEEPSLATE_VERDANT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.GRIMSTONE_VERDANT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.VOIDSTONE_VERDANT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.ETERNAL_ICE_VERDANT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.HAZE_ICE_VERDANT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.RAW_VERDANT_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.VERDANT_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DETONIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DEEPSLATE_DETONIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.GRIMSTONE_DETONIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.VOIDSTONE_DETONIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.ETERNAL_ICE_DETONIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.HAZE_ICE_DETONIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.RAW_DETONIUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DETONIUM_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.DEEPSLATE_AETHERIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.BASALT_AETHERIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.BLACKSTONE_AETHERIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.END_AETHERIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.REFINED_AETHERIUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.AETHERIUM_BRICKS);

        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.CINCINNASITE_DIAMOND_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(EBlocks.TERRASTEEL_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //region Food Items
        itemModelGenerator.register(EFood.DR_PEPPER, Models.GENERATED);
        itemModelGenerator.register(EFood.JARRITOS, Models.GENERATED);
        itemModelGenerator.register(EFood.PIBB_XTRA, Models.GENERATED);
        itemModelGenerator.register(EFood.RED_BULL, Models.GENERATED);
        itemModelGenerator.register(EFood.SNAPPLE, Models.GENERATED);
        itemModelGenerator.register(EFood.RAW_CHICKEN_NUGGET, Models.GENERATED);
        itemModelGenerator.register(EFood.COOKED_CHICKEN_NUGGET, Models.GENERATED);
        itemModelGenerator.register(EFood.MAGIC_APPLE, Models.GENERATED);
        //endregion

        //region Mega Buster Projectiles
        itemModelGenerator.register(EItems.MEGA_BUSTER_SHOT_1, Models.GENERATED);
        itemModelGenerator.register(EItems.MEGA_BUSTER_SHOT_2, Models.GENERATED);
        itemModelGenerator.register(EItems.MEGA_BUSTER_SHOT_3, Models.GENERATED);
        itemModelGenerator.register(EItems.MEGA_BUSTER_SHOT_4, Models.GENERATED);
        //endregion

        //region Debug Items
        itemModelGenerator.register(EItems.E_DEBUG_STICK, Models.GENERATED);
        //endregion

        itemModelGenerator.register(EItems.ROD_OF_DISCORD, Models.HANDHELD);
        itemModelGenerator.register(EItems.ROD_OF_HARMONY, Models.HANDHELD);

        itemModelGenerator.register(EItems.ENDLESS_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(EItems.ENDLESS_LAVA_BUCKET, Models.GENERATED);
        itemModelGenerator.register(EItems.ENDLESS_MILK_BUCKET, Models.GENERATED);

        //region Raw, Smelted, and Nugget Materials
        itemModelGenerator.register(EItems.MAGIC_DUST, Models.GENERATED);
        itemModelGenerator.register(EItems.MAGIC_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(EItems.MAGIC_ARROW, Models.GENERATED);

        itemModelGenerator.register(EItems.CINCINNASITE_DIAMOND_INGOT, Models.GENERATED);
        itemModelGenerator.register(EItems.CINCINNASITE_DIAMOND_NUGGET, Models.GENERATED);
        itemModelGenerator.register(EItems.TERRASTEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(EItems.TERRASTEEL_NUGGET, Models.GENERATED);
        itemModelGenerator.register(EItems.RAW_DIAMOND, Models.GENERATED);
        itemModelGenerator.register(EItems.RAW_DEMONITE, Models.GENERATED);
        itemModelGenerator.register(EItems.COPPER_NUGGET, Models.GENERATED);
        itemModelGenerator.register(EItems.DEMONITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(EItems.DEMONITE_NUGGET, Models.GENERATED);
        itemModelGenerator.register(EItems.RAW_TUNGSTEN, Models.GENERATED);
        itemModelGenerator.register(EItems.TUNGSTEN_INGOT, Models.GENERATED);
        itemModelGenerator.register(EItems.TUNGSTEN_NUGGET, Models.GENERATED);
        itemModelGenerator.register(EItems.RAW_HELLSTONE, Models.GENERATED);
        itemModelGenerator.register(EItems.HELLSTONE_INGOT, Models.GENERATED);
        itemModelGenerator.register(EItems.HELLSTONE_NUGGET, Models.GENERATED);
        itemModelGenerator.register(EItems.RAW_COBALT, Models.GENERATED);
        itemModelGenerator.register(EItems.COBALT_INGOT, Models.GENERATED);
        itemModelGenerator.register(EItems.COBALT_NUGGET, Models.GENERATED);
        itemModelGenerator.register(EItems.RAW_ORICHALCUM, Models.GENERATED);
        itemModelGenerator.register(EItems.ORICHALCUM_INGOT, Models.GENERATED);
        itemModelGenerator.register(EItems.ORICHALCUM_NUGGET, Models.GENERATED);
        itemModelGenerator.register(EItems.RAW_TITANIUM, Models.GENERATED);
        itemModelGenerator.register(EItems.TITANIUM_INGOT, Models.GENERATED);
        itemModelGenerator.register(EItems.TITANIUM_NUGGET, Models.GENERATED);
        itemModelGenerator.register(EItems.HALLOWED_INGOT, Models.GENERATED);
        itemModelGenerator.register(EItems.HALLOWED_NUGGET, Models.GENERATED);
        itemModelGenerator.register(EItems.RAW_VERDANT, Models.GENERATED);
        itemModelGenerator.register(EItems.VERDANT_INGOT, Models.GENERATED);
        itemModelGenerator.register(EItems.VERDANT_NUGGET, Models.GENERATED);
        itemModelGenerator.register(EItems.RAW_DETONIUM, Models.GENERATED);
        itemModelGenerator.register(EItems.DETONIUM_INGOT, Models.GENERATED);
        itemModelGenerator.register(EItems.DETONIUM_NUGGET, Models.GENERATED);
        itemModelGenerator.register(EItems.RAW_AETHERIUM_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(EItems.REFINED_AETHERIUM_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(EItems.FLAWLESS_AETHERIUM_CRYSTAL, Models.GENERATED);
        //endregion

        //region Misc. Materials
        itemModelGenerator.register(EItems.SHARD_OF_TRAVEL, Models.GENERATED);
        itemModelGenerator.register(EItems.GUNLANCE_SHELL, Models.GENERATED);
        itemModelGenerator.register(EItems.GUNLANCE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(EItems.PACK_A_PUNCH_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(EItems.SMOOTHBORE_BARREL, Models.GENERATED);
        itemModelGenerator.register(EItems.CALIBURN_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(EItems.DURENDAL_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(EItems.SOLAIS_SMITHING_TEMPLATE, Models.GENERATED);
        //endregion

        //region Trinkets
        itemModelGenerator.register(EItems.MEDALLION_OF_SPACE_STONE, Models.GENERATED);
        itemModelGenerator.register(EItems.MEDALLION_OF_SPACE_TUNGSTEN, Models.GENERATED);
        itemModelGenerator.register(EItems.MEDALLION_OF_SPACE_DEMONITE, Models.GENERATED);
        itemModelGenerator.register(EItems.MEDALLION_OF_SPACE_COBALT, Models.GENERATED);
        itemModelGenerator.register(EItems.MEDALLION_OF_SPACE_VERDANT, Models.GENERATED);
        itemModelGenerator.register(EItems.MEDALLION_OF_SPACE_AETHERIUM, Models.GENERATED);
        itemModelGenerator.register(EItems.MEDALLION_OF_SPACE_FLAWLESS_AETHERIUM, Models.GENERATED);
        itemModelGenerator.register(EItems.MANA_STAR, Models.GENERATED);
        itemModelGenerator.register(EItems.VERDANT_MANA_STAR, Models.GENERATED);
        itemModelGenerator.register(EItems.AETHERIUM_MANA_STAR, Models.GENERATED);
        itemModelGenerator.register(EItems.FLAWLESS_AETHERIUM_MANA_STAR, Models.GENERATED);
        itemModelGenerator.register(EItems.MANA_STAR_SMITHING_TEMPLATE, Models.GENERATED);
        //endregion

        //region Base Picksaws
        itemModelGenerator.register(EWeapons.WOODEN_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.STONE_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.IRON_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.GOLDEN_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.DIAMOND_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.CINCINNASITE_DIAMOND_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.NETHERITE_PICKSAW, Models.HANDHELD);
        //endregion

        //region Base Lances
        itemModelGenerator.register(EWeapons.WOODEN_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(EWeapons.STONE_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(EWeapons.IRON_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(EWeapons.GOLDEN_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(EWeapons.DIAMOND_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(EWeapons.CINCINNASITE_DIAMOND_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(EWeapons.NETHERITE_LANCE, DatagenModels.LANCE);
        //endregion

        // Base Staves
        itemModelGenerator.register(EWeapons.IRON_STAFF, Models.HANDHELD);
        itemModelGenerator.register(EItems.IRON_STAFF_PROJECTILE, Models.GENERATED);
        itemModelGenerator.register(EWeapons.GOLDEN_STAFF, Models.HANDHELD);
        itemModelGenerator.register(EItems.GOLDEN_STAFF_PROJECTILE, Models.GENERATED);

        /*itemModelGenerator.register(ModWeapons.ADAMANTITE_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.AQUARIUM_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.BANGLUM_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.BRONZE_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.CARMOT_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.CELESTIUM_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.CINCINNASITE_DIAMOND_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.DURASTEEL_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.ENDERNETHER_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.HALLOWED_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.KYBER_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.LEGENDARY_BANGLUM_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.METALLURGIUM_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.MYTHRIL_LANCE, DatagenModels.LANCE);

        itemModelGenerator.register(ModWeapons.ORICHALCUM_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.OSMIUM_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.OVERNETHER_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.PALLADIUM_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.PROMETHEUM_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.QUADRILLUM_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.RUNITE_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.STAR_PLATINUM_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.STEEL_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.STORMYX_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.TERRASTEEL_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(ModWeapons.TIDESINGER_LANCE, DatagenModels.LANCE);*/
        //endregion

        //region Specialty Weapons
        itemModelGenerator.register(EWeapons.MURAMASA, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.VOLCANO, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.BLADE_OF_GRASS, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.NIGHTS_EDGE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TRUE_NIGHTS_EDGE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.BASTARD_SWORD, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.WIND_STAFF, DatagenModels.STAFF);
        itemModelGenerator.register(EWeapons.WIND_STAFF_UPGRADED, DatagenModels.STAFF);
        itemModelGenerator.register(EWeapons.LIGHTNING_STAFF, DatagenModels.STAFF);
        itemModelGenerator.register(EWeapons.LIGHTNING_STAFF_UPGRADED, DatagenModels.STAFF);
        itemModelGenerator.register(EItems.LIGHTNING_STAFF_PROJECTILE, Models.GENERATED);
        itemModelGenerator.register(EWeapons.FIRE_STAFF, DatagenModels.STAFF);
        itemModelGenerator.register(EWeapons.FIRE_STAFF_UPGRADED, DatagenModels.STAFF);
        itemModelGenerator.register(EItems.FIRE_STAFF_PROJECTILE, Models.GENERATED);
        itemModelGenerator.register(EWeapons.ICE_STAFF, DatagenModels.STAFF);
        itemModelGenerator.register(EWeapons.ICE_STAFF_UPGRADED, DatagenModels.STAFF);
        //endregion

        //region Standard Tools
        itemModelGenerator.register(EWeapons.COPPER_AXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.COPPER_HOE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.COPPER_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.COPPER_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.COPPER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.COPPER_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.COPPER_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(EWeapons.COPPER_STAFF, Models.HANDHELD);
        itemModelGenerator.register(EItems.COPPER_STAFF_PROJECTILE, Models.GENERATED);

        itemModelGenerator.register(EWeapons.TUNGSTEN_AXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TUNGSTEN_HOE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TUNGSTEN_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TUNGSTEN_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TUNGSTEN_SWORD, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TUNGSTEN_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TUNGSTEN_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(EWeapons.TUNGSTEN_STAFF, Models.HANDHELD);
        itemModelGenerator.register(EItems.TUNGSTEN_STAFF_PROJECTILE, Models.GENERATED);

        itemModelGenerator.register(EWeapons.DEMONITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.DEMONITE_HOE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.DEMONITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.DEMONITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.DEMONITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.DEMONITE_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.DEMONITE_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(EWeapons.DEMONITE_STAFF, Models.HANDHELD);
        itemModelGenerator.register(EItems.DEMONITE_STAFF_PROJECTILE, Models.GENERATED);

        itemModelGenerator.register(EWeapons.HELLSTONE_AXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.HELLSTONE_HOE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.HELLSTONE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.HELLSTONE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.HELLSTONE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.HELLSTONE_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.HELLSTONE_LANCE, DatagenModels.LANCE);
        itemModelGenerator.register(EWeapons.HELLSTONE_STAFF, Models.HANDHELD);
        itemModelGenerator.register(EItems.HELLSTONE_STAFF_PROJECTILE, Models.GENERATED);

        itemModelGenerator.register(EWeapons.COBALT_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.COBALT_SCYTHE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.COBALT_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.COBALT_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.COBALT_SWORD, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.COBALT_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.COBALT_LANCE, DatagenModels.LANCE);

        itemModelGenerator.register(EWeapons.ORICHALCUM_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.ORICHALCUM_SCYTHE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.ORICHALCUM_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.ORICHALCUM_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.ORICHALCUM_SWORD, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.ORICHALCUM_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.ORICHALCUM_LANCE, DatagenModels.LANCE);

        itemModelGenerator.register(EWeapons.TITANIUM_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TITANIUM_SCYTHE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TITANIUM_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TITANIUM_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TITANIUM_SWORD, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TITANIUM_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TITANIUM_LANCE, DatagenModels.LANCE);

        itemModelGenerator.register(EWeapons.HALLOWED_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.HALLOWED_SCYTHE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.HALLOWED_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.HALLOWED_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.HALLOWED_SWORD, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.HALLOWED_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.HALLOWED_LANCE, DatagenModels.LANCE);

        itemModelGenerator.register(EWeapons.VERDANT_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.VERDANT_SCYTHE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.VERDANT_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.VERDANT_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.VERDANT_SWORD, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.VERDANT_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.VERDANT_LANCE, DatagenModels.LANCE);

        itemModelGenerator.register(EWeapons.DETONIUM_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.DETONIUM_SCYTHE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.DETONIUM_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.DETONIUM_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.DETONIUM_BLASTAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.DETONIUM_SWORD, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.DETONIUM_PICKSAW, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.DETONIUM_LANCE, DatagenModels.LANCE);

        /*
        itemModelGenerator.register(EWeapons.TERRASTEEL_AXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TERRASTEEL_HOE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TERRASTEEL_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TERRASTEEL_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(EWeapons.TERRASTEEL_SWORD, Models.HANDHELD);
        */
        //endregion

        //region Armor
        itemModelGenerator.register(EArmor.COPPER_HELMET, Models.GENERATED);
        itemModelGenerator.register(EArmor.COPPER_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(EArmor.COPPER_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(EArmor.COPPER_BOOTS, Models.GENERATED);

        itemModelGenerator.register(EArmor.TUNGSTEN_HELMET, Models.GENERATED);
        itemModelGenerator.register(EArmor.TUNGSTEN_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(EArmor.TUNGSTEN_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(EArmor.TUNGSTEN_BOOTS, Models.GENERATED);

        itemModelGenerator.register(EArmor.DEMONITE_HELMET, Models.GENERATED);
        itemModelGenerator.register(EArmor.DEMONITE_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(EArmor.DEMONITE_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(EArmor.DEMONITE_BOOTS, Models.GENERATED);

        itemModelGenerator.register(EArmor.HELLSTONE_HELMET, Models.GENERATED);
        itemModelGenerator.register(EArmor.HELLSTONE_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(EArmor.HELLSTONE_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(EArmor.HELLSTONE_BOOTS, Models.GENERATED);

        itemModelGenerator.register(EArmor.COBALT_HELMET, Models.GENERATED);
        itemModelGenerator.register(EArmor.COBALT_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(EArmor.COBALT_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(EArmor.COBALT_BOOTS, Models.GENERATED);

        itemModelGenerator.register(EArmor.ORICHALCUM_HELMET, Models.GENERATED);
        itemModelGenerator.register(EArmor.ORICHALCUM_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(EArmor.ORICHALCUM_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(EArmor.ORICHALCUM_BOOTS, Models.GENERATED);

        itemModelGenerator.register(EArmor.TITANIUM_HELMET, Models.GENERATED);
        itemModelGenerator.register(EArmor.TITANIUM_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(EArmor.TITANIUM_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(EArmor.TITANIUM_BOOTS, Models.GENERATED);

        itemModelGenerator.register(EArmor.HALLOWED_HELMET, Models.GENERATED);
        itemModelGenerator.register(EArmor.HALLOWED_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(EArmor.HALLOWED_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(EArmor.HALLOWED_BOOTS, Models.GENERATED);

        itemModelGenerator.register(EArmor.VERDANT_HELMET, Models.GENERATED);
        itemModelGenerator.register(EArmor.VERDANT_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(EArmor.VERDANT_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(EArmor.VERDANT_BOOTS, Models.GENERATED);

        itemModelGenerator.register(EArmor.DETONIUM_HELMET, Models.GENERATED);
        itemModelGenerator.register(EArmor.DETONIUM_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(EArmor.DETONIUM_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(EArmor.DETONIUM_BOOTS, Models.GENERATED);

        itemModelGenerator.register(EArmor.REFINED_AETHERIUM_HELMET, Models.GENERATED);
        itemModelGenerator.register(EArmor.REFINED_AETHERIUM_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(EArmor.REFINED_AETHERIUM_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(EArmor.REFINED_AETHERIUM_BOOTS, Models.GENERATED);

        itemModelGenerator.register(EArmor.FLAWLESS_AETHERIUM_HELMET, Models.GENERATED);
        itemModelGenerator.register(EArmor.FLAWLESS_AETHERIUM_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(EArmor.FLAWLESS_AETHERIUM_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(EArmor.FLAWLESS_AETHERIUM_BOOTS, Models.GENERATED);

        itemModelGenerator.register(EArmor.CINCINNASITE_DIAMOND_HELMET, Models.GENERATED);
        itemModelGenerator.register(EArmor.CINCINNASITE_DIAMOND_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(EArmor.CINCINNASITE_DIAMOND_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(EArmor.CINCINNASITE_DIAMOND_BOOTS, Models.GENERATED);

        // itemModelGenerator.register(EArmor.TERRASTEEL_HELMET, Models.GENERATED);
        // itemModelGenerator.register(EArmor.TERRASTEEL_CHESTPLATE, Models.GENERATED);
        // itemModelGenerator.register(EArmor.TERRASTEEL_LEGGINGS, Models.GENERATED);
        // itemModelGenerator.register(EArmor.TERRASTEEL_BOOTS, Models.GENERATED);
        //endregion
    }

    public final void registerLamp(BlockStateModelGenerator generator, Block lampBlock) {
        Identifier baseId = Models.CUBE_ALL.upload(lampBlock, TextureMap.all(lampBlock), generator.modelCollector);
        Identifier onId = generator.createSubModel(lampBlock, "_on", Models.CUBE_ALL, TextureMap::all);
        generator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(lampBlock)
                        .coordinate(
                                BlockStateVariantMap.create(Properties.LIT)
                                        .register(
                                                (lit) -> BlockStateVariant.create().put(VariantSettings.MODEL, lit ? onId : baseId)
                                        )
                        )
        );
    }
}
