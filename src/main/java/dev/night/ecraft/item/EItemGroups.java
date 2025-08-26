package dev.night.ecraft.item;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.block.EBlocks;
import dev.night.ecraft.item.food.EFood;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EItemGroups {
    public static final RegistryKey<ItemGroup> ECRAFT_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Ecraft.MOD_ID, "ecraft"));

    public static final ItemGroup ECRAFT_GROUP = Registry.register(Registries.ITEM_GROUP,
            ECRAFT_GROUP_KEY,
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.ecraft"))
                    .icon(() -> new ItemStack(EItems.CINCINNASITE_DIAMOND_INGOT))
                    .entries(((displayContext, entries) -> {



                    })).build());

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " registering item groups");

        ItemGroupEvents.modifyEntriesEvent(ECRAFT_GROUP_KEY).register(
                entries -> {
                    // entries.add(ModItems.E_DEBUG_STICK);

                    // Add foods
                    entries.add(EFood.DR_PEPPER);
                    entries.add(EFood.JARRITOS);
                    entries.add(EFood.PIBB_XTRA);
                    entries.add(EFood.RED_BULL);
                    entries.add(EFood.SNAPPLE);
                    entries.add(EFood.MAGIC_APPLE);
                    entries.add(EFood.RAW_CHICKEN_NUGGET);
                    entries.add(EFood.COOKED_CHICKEN_NUGGET);
                    entries.add(EBlocks.RAW_CHICKEN_BLOCK);
                    entries.add(EBlocks.COOKED_CHICKEN_BLOCK);

                    // Add items
                    entries.add(EItems.SMOOTHBORE_BARREL);
                    entries.add(EItems.GUNLANCE_SMITHING_TEMPLATE);
                    entries.add(EItems.PACK_A_PUNCH_SMITHING_TEMPLATE);

                    entries.add(EItems.DETONIUM_DETONATOR);
                    entries.add(EBlocks.CHARGE);
                    entries.add(EBlocks.DETONIUM_CHARGE);
                    entries.add(EBlocks.VERDANT_CHARGE);
                    entries.add(EBlocks.DETONIUM_NUKE);

                    entries.add(EItems.SHARD_OF_TRAVEL);
                    entries.add(EItems.RAW_DIAMOND);
                    entries.add(Items.DIAMOND);
                    entries.add(EBlocks.RAW_DIAMOND_BLOCK);
                    entries.add(EItems.CINCINNASITE_DIAMOND_INGOT);
                    entries.add(EItems.CINCINNASITE_DIAMOND_NUGGET);
                    entries.add(EBlocks.CINCINNASITE_DIAMOND_BLOCK);
                    entries.add(EItems.TERRASTEEL_INGOT);
                    entries.add(EItems.TERRASTEEL_NUGGET);
                    entries.add(EBlocks.TERRASTEEL_BLOCK);

                    entries.add(EItems.MAGIC_DUST);
                    entries.add(EItems.MAGIC_CRYSTAL);
                    entries.add(EItems.MAGIC_ARROW);
                    entries.add(EBlocks.MAGIC_ORE);
                    entries.add(EBlocks.DIRT_MAGIC_ORE);
                    entries.add(EBlocks.ANDESITE_MAGIC_ORE);
                    entries.add(EBlocks.DIORITE_MAGIC_ORE);
                    entries.add(EBlocks.GRANITE_MAGIC_ORE);
                    entries.add(EBlocks.DEEPSLATE_MAGIC_ORE);
                    entries.add(EBlocks.TUFF_MAGIC_ORE);
                    entries.add(EBlocks.RAW_MAGIC_BLOCK);
                    entries.add(EBlocks.MAGIC_BLOCK);
                    entries.add(EBlocks.MAGIC_LAMP);
                    entries.add(EBlocks.MAGIC_RAIL);

                    entries.add(EItems.COPPER_NUGGET);

                    entries.add(EBlocks.TUNGSTEN_ORE);
                    entries.add(EBlocks.DEEPSLATE_TUNGSTEN_ORE);
                    entries.add(EItems.RAW_TUNGSTEN);
                    entries.add(EBlocks.RAW_TUNGSTEN_BLOCK);
                    entries.add(EItems.TUNGSTEN_INGOT);
                    entries.add(EItems.TUNGSTEN_NUGGET);
                    entries.add(EBlocks.TUNGSTEN_BLOCK);

                    entries.add(EBlocks.DEMONITE_ORE);
                    entries.add(EBlocks.DEEPSLATE_DEMONITE_ORE);
                    entries.add(EItems.RAW_DEMONITE);
                    entries.add(EBlocks.RAW_DEMONITE_BLOCK);
                    entries.add(EItems.DEMONITE_INGOT);
                    entries.add(EItems.DEMONITE_NUGGET);
                    entries.add(EBlocks.DEMONITE_BLOCK);

                    entries.add(EBlocks.HELLSTONE_ORE);
                    entries.add(EItems.RAW_HELLSTONE);
                    entries.add(EBlocks.RAW_HELLSTONE_BLOCK);
                    entries.add(EItems.HELLSTONE_INGOT);
                    entries.add(EItems.HELLSTONE_NUGGET);
                    entries.add(EBlocks.HELLSTONE_BLOCK);

                    entries.add(EBlocks.COBALT_ORE);
                    entries.add(EBlocks.DEEPSLATE_COBALT_ORE);
                    entries.add(EItems.RAW_COBALT);
                    entries.add(EBlocks.RAW_COBALT_BLOCK);
                    entries.add(EItems.COBALT_INGOT);
                    entries.add(EItems.COBALT_NUGGET);
                    entries.add(EBlocks.COBALT_BLOCK);

                    entries.add(EBlocks.ORICHALCUM_ORE);
                    entries.add(EBlocks.DEEPSLATE_ORICHALCUM_ORE);
                    entries.add(EItems.RAW_ORICHALCUM);
                    entries.add(EBlocks.RAW_ORICHALCUM_BLOCK);
                    entries.add(EItems.ORICHALCUM_INGOT);
                    entries.add(EItems.ORICHALCUM_NUGGET);
                    entries.add(EBlocks.ORICHALCUM_BLOCK);

                    entries.add(EBlocks.TITANIUM_ORE);
                    entries.add(EBlocks.DEEPSLATE_TITANIUM_ORE);
                    entries.add(EItems.RAW_TITANIUM);
                    entries.add(EBlocks.RAW_TITANIUM_BLOCK);
                    entries.add(EItems.TITANIUM_INGOT);
                    entries.add(EItems.TITANIUM_NUGGET);
                    entries.add(EBlocks.TITANIUM_BLOCK);

                    entries.add(EItems.HALLOWED_INGOT);
                    entries.add(EItems.HALLOWED_NUGGET);
                    entries.add(EBlocks.HALLOWED_BLOCK);

                    entries.add(EBlocks.VERDANT_ORE);
                    entries.add(EBlocks.DEEPSLATE_VERDANT_ORE);
                    entries.add(EBlocks.GRIMSTONE_VERDANT_ORE);
                    entries.add(EBlocks.VOIDSTONE_VERDANT_ORE);
                    entries.add(EBlocks.ETERNAL_ICE_VERDANT_ORE);
                    entries.add(EBlocks.HAZE_ICE_VERDANT_ORE);
                    entries.add(EItems.RAW_VERDANT);
                    entries.add(EBlocks.RAW_VERDANT_BLOCK);
                    entries.add(EItems.VERDANT_INGOT);
                    entries.add(EItems.VERDANT_NUGGET);
                    entries.add(EBlocks.VERDANT_BLOCK);

                    entries.add(EBlocks.DETONIUM_ORE);
                    entries.add(EBlocks.DEEPSLATE_DETONIUM_ORE);
                    entries.add(EBlocks.GRIMSTONE_DETONIUM_ORE);
                    entries.add(EBlocks.VOIDSTONE_DETONIUM_ORE);
                    entries.add(EBlocks.ETERNAL_ICE_DETONIUM_ORE);
                    entries.add(EBlocks.HAZE_ICE_DETONIUM_ORE);
                    entries.add(EItems.RAW_DETONIUM);
                    entries.add(EBlocks.RAW_DETONIUM_BLOCK);
                    entries.add(EItems.DETONIUM_INGOT);
                    entries.add(EItems.DETONIUM_NUGGET);
                    entries.add(EBlocks.DETONIUM_BLOCK);

                    entries.add(EBlocks.BASALT_AETHERIUM_ORE);
                    entries.add(EBlocks.BLACKSTONE_AETHERIUM_ORE);
                    entries.add(EBlocks.DEEPSLATE_AETHERIUM_ORE);
                    entries.add(EBlocks.END_AETHERIUM_ORE);
                    entries.add(EItems.RAW_AETHERIUM_CRYSTAL);
                    entries.add(EItems.REFINED_AETHERIUM_CRYSTAL);
                    entries.add(EItems.FLAWLESS_AETHERIUM_CRYSTAL);
                    entries.add(EBlocks.REFINED_AETHERIUM_BLOCK);
                    entries.add(EBlocks.AETHERIUM_BRICKS);

                    // Add armor
                    entries.add(EArmor.COPPER_HELMET);
                    entries.add(EArmor.COPPER_CHESTPLATE);
                    entries.add(EArmor.COPPER_LEGGINGS);
                    entries.add(EArmor.COPPER_BOOTS);

                    entries.add(EArmor.TUNGSTEN_HELMET);
                    entries.add(EArmor.TUNGSTEN_CHESTPLATE);
                    entries.add(EArmor.TUNGSTEN_LEGGINGS);
                    entries.add(EArmor.TUNGSTEN_BOOTS);

                    entries.add(EArmor.DEMONITE_HELMET);
                    entries.add(EArmor.DEMONITE_CHESTPLATE);
                    entries.add(EArmor.DEMONITE_LEGGINGS);
                    entries.add(EArmor.DEMONITE_BOOTS);

                    entries.add(EArmor.HELLSTONE_HELMET);
                    entries.add(EArmor.HELLSTONE_CHESTPLATE);
                    entries.add(EArmor.HELLSTONE_LEGGINGS);
                    entries.add(EArmor.HELLSTONE_BOOTS);

                    entries.add(EArmor.COBALT_HELMET);
                    entries.add(EArmor.COBALT_CHESTPLATE);
                    entries.add(EArmor.COBALT_LEGGINGS);
                    entries.add(EArmor.COBALT_BOOTS);

                    entries.add(EArmor.ORICHALCUM_HELMET);
                    entries.add(EArmor.ORICHALCUM_CHESTPLATE);
                    entries.add(EArmor.ORICHALCUM_LEGGINGS);
                    entries.add(EArmor.ORICHALCUM_BOOTS);

                    entries.add(EArmor.TITANIUM_HELMET);
                    entries.add(EArmor.TITANIUM_CHESTPLATE);
                    entries.add(EArmor.TITANIUM_LEGGINGS);
                    entries.add(EArmor.TITANIUM_BOOTS);

                    entries.add(EArmor.HALLOWED_HELMET);
                    entries.add(EArmor.HALLOWED_CHESTPLATE);
                    entries.add(EArmor.HALLOWED_LEGGINGS);
                    entries.add(EArmor.HALLOWED_BOOTS);

                    entries.add(EArmor.VERDANT_HELMET);
                    entries.add(EArmor.VERDANT_CHESTPLATE);
                    entries.add(EArmor.VERDANT_LEGGINGS);
                    entries.add(EArmor.VERDANT_BOOTS);

                    entries.add(EArmor.DETONIUM_HELMET);
                    entries.add(EArmor.DETONIUM_CHESTPLATE);
                    entries.add(EArmor.DETONIUM_LEGGINGS);
                    entries.add(EArmor.DETONIUM_BOOTS);

                    entries.add(EArmor.REFINED_AETHERIUM_HELMET);
                    entries.add(EArmor.REFINED_AETHERIUM_CHESTPLATE);
                    entries.add(EArmor.REFINED_AETHERIUM_LEGGINGS);
                    entries.add(EArmor.REFINED_AETHERIUM_BOOTS);

                    entries.add(EArmor.FLAWLESS_AETHERIUM_HELMET);
                    entries.add(EArmor.FLAWLESS_AETHERIUM_CHESTPLATE);
                    entries.add(EArmor.FLAWLESS_AETHERIUM_LEGGINGS);
                    entries.add(EArmor.FLAWLESS_AETHERIUM_BOOTS);

                    entries.add(EArmor.CINCINNASITE_DIAMOND_HELMET);
                    entries.add(EArmor.CINCINNASITE_DIAMOND_CHESTPLATE);
                    entries.add(EArmor.CINCINNASITE_DIAMOND_LEGGINGS);
                    entries.add(EArmor.CINCINNASITE_DIAMOND_BOOTS);

                    // entries.add(EArmor.TERRASTEEL_HELMET);
                    // entries.add(EArmor.TERRASTEEL_CHESTPLATE);
                    // entries.add(EArmor.TERRASTEEL_LEGGINGS);
                    // entries.add(EArmor.TERRASTEEL_BOOTS);

                    // Add tools
                    entries.add(EWeapons.MURAMASA);
                    entries.add(EWeapons.VOLCANO);
                    entries.add(EWeapons.BLADE_OF_GRASS);
                    entries.add(EWeapons.NIGHTS_EDGE);
                    entries.add(EWeapons.TRUE_NIGHTS_EDGE);
                    entries.add(EWeapons.MAGIC_LASER_DRILL);

                    entries.add(EWeapons.BASTARD_SWORD);
                    entries.add(EWeapons.CALIBURN);
                    entries.add(EWeapons.DURENDAL);
                    entries.add(EWeapons.SOLAIS);
                    entries.add(EItems.CALIBURN_SMITHING_TEMPLATE);
                    entries.add(EItems.DURENDAL_SMITHING_TEMPLATE);
                    entries.add(EItems.SOLAIS_SMITHING_TEMPLATE);

                    entries.add(EWeapons.WIND_STAFF);
                    entries.add(EWeapons.WIND_STAFF_UPGRADED);
                    entries.add(EWeapons.LIGHTNING_STAFF);
                    entries.add(EWeapons.LIGHTNING_STAFF_UPGRADED);
                    entries.add(EWeapons.FIRE_STAFF);
                    entries.add(EWeapons.FIRE_STAFF_UPGRADED);
                    entries.add(EWeapons.ICE_STAFF);
                    entries.add(EWeapons.ICE_STAFF_UPGRADED);
                    entries.add(EWeapons.AETHERIUM_LASER_DRILL);

                    entries.add(EWeapons.COPPER_SWORD);
                    entries.add(EWeapons.COPPER_AXE);
                    entries.add(EWeapons.COPPER_PICKAXE);
                    entries.add(EWeapons.COPPER_SHOVEL);
                    entries.add(EWeapons.COPPER_HOE);

                    entries.add(EWeapons.TUNGSTEN_SWORD);
                    entries.add(EWeapons.TUNGSTEN_AXE);
                    entries.add(EWeapons.TUNGSTEN_PICKAXE);
                    entries.add(EWeapons.TUNGSTEN_SHOVEL);
                    entries.add(EWeapons.TUNGSTEN_HOE);

                    entries.add(EWeapons.DEMONITE_SWORD);
                    entries.add(EWeapons.DEMONITE_AXE);
                    entries.add(EWeapons.DEMONITE_PICKAXE);
                    entries.add(EWeapons.DEMONITE_SHOVEL);
                    entries.add(EWeapons.DEMONITE_HOE);

                    entries.add(EWeapons.HELLSTONE_SWORD);
                    entries.add(EWeapons.HELLSTONE_AXE);
                    entries.add(EWeapons.HELLSTONE_PICKAXE);
                    entries.add(EWeapons.HELLSTONE_SHOVEL);
                    entries.add(EWeapons.HELLSTONE_HOE);

                    entries.add(EWeapons.COBALT_SWORD);
                    entries.add(EWeapons.COBALT_BATTLEAXE);
                    entries.add(EWeapons.COBALT_PICKAXE);
                    entries.add(EWeapons.COBALT_SHOVEL);
                    entries.add(EWeapons.COBALT_SCYTHE);

                    entries.add(EWeapons.ORICHALCUM_SWORD);
                    entries.add(EWeapons.ORICHALCUM_BATTLEAXE);
                    entries.add(EWeapons.ORICHALCUM_PICKAXE);
                    entries.add(EWeapons.ORICHALCUM_SHOVEL);
                    entries.add(EWeapons.ORICHALCUM_SCYTHE);

                    entries.add(EWeapons.TITANIUM_SWORD);
                    entries.add(EWeapons.TITANIUM_BATTLEAXE);
                    entries.add(EWeapons.TITANIUM_PICKAXE);
                    entries.add(EWeapons.TITANIUM_SHOVEL);
                    entries.add(EWeapons.TITANIUM_SCYTHE);

                    entries.add(EWeapons.HALLOWED_SWORD);
                    entries.add(EWeapons.HALLOWED_BATTLEAXE);
                    entries.add(EWeapons.HALLOWED_PICKAXE);
                    entries.add(EWeapons.HALLOWED_SHOVEL);
                    entries.add(EWeapons.HALLOWED_SCYTHE);

                    entries.add(EWeapons.VERDANT_SWORD);
                    entries.add(EWeapons.VERDANT_BATTLEAXE);
                    entries.add(EWeapons.VERDANT_PICKAXE);
                    entries.add(EWeapons.VERDANT_SHOVEL);
                    entries.add(EWeapons.VERDANT_SCYTHE);

                    entries.add(EWeapons.DETONIUM_SWORD);
                    entries.add(EWeapons.DETONIUM_BATTLEAXE);
                    entries.add(EWeapons.DETONIUM_PICKAXE);
                    entries.add(EWeapons.DETONIUM_SHOVEL);
                    entries.add(EWeapons.DETONIUM_SCYTHE);
                    entries.add(EWeapons.DETONIUM_BLASTAXE);

                    /*
                    entries.add(EWeapons.TERRASTEEL_SWORD);
                    entries.add(EWeapons.TERRASTEEL_AXE);
                    entries.add(EWeapons.TERRASTEEL_PICKAXE);
                    entries.add(EWeapons.TERRASTEEL_SHOVEL);
                    entries.add(EWeapons.TERRASTEEL_HOE);
                    */

                    // Add staves
                    for (Item item : EWeapons.staves)
                        entries.add(item);

                    // Add picksaws
                    for (Item item : EWeapons.picksaws)
                        entries.add(item);

                    // Add lances
                    for (Item item : EWeapons.lances)
                        entries.add(item);

                    // Add gunlances
                    entries.add(EItems.GUNLANCE_SHELL);
                    for (Item item : EWeapons.gunlances)
                        entries.add(item);

                    // Add misc. weapons
                    entries.add(EWeapons.MEGA_BUSTER);
                    entries.add(EWeapons.X_BUSTER);

                    // Add medallions
                    entries.add(EItems.MEDALLION_OF_SPACE_STONE);
                    entries.add(EItems.MEDALLION_OF_SPACE_TUNGSTEN);
                    entries.add(EItems.MEDALLION_OF_SPACE_DEMONITE);
                    entries.add(EItems.MEDALLION_OF_SPACE_COBALT);
                    entries.add(EItems.MEDALLION_OF_SPACE_VERDANT);
                    entries.add(EItems.MEDALLION_OF_SPACE_AETHERIUM);
                    entries.add(EItems.MEDALLION_OF_SPACE_FLAWLESS_AETHERIUM);

                    entries.add(EItems.ROD_OF_DISCORD);
                    entries.add(EItems.ROD_OF_HARMONY);
                    entries.add(EItems.ENDLESS_WATER_BUCKET);
                    entries.add(EItems.ENDLESS_LAVA_BUCKET);
                    entries.add(EItems.ENDLESS_MILK_BUCKET);

                    // Add mana star items
                    entries.add(EItems.MANA_STAR);
                    entries.add(EItems.VERDANT_MANA_STAR);
                    entries.add(EItems.AETHERIUM_MANA_STAR);
                    entries.add(EItems.FLAWLESS_AETHERIUM_MANA_STAR);
                    entries.add(EItems.MANA_STAR_SMITHING_TEMPLATE);
                }
        );
    }
}
