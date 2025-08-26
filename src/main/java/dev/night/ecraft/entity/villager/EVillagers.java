package dev.night.ecraft.entity.villager;

import com.google.common.collect.ImmutableSet;
import dev.night.ecraft.Ecraft;
import dev.night.ecraft.block.EBlocks;
import dev.night.ecraft.item.EArmor;
import dev.night.ecraft.item.EItems;
import dev.night.ecraft.item.EWeapons;
import dev.night.ecraft.item.tools.weapons.SpecialtyWeapon;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class EVillagers {
    public static final RegistryKey<PointOfInterestType> HUNTER_POI_KEY = registerPoiKey("hunter_poi");
    public static final PointOfInterestType HUNTER_POI = registerPoi("hunter_poi", Blocks.ANVIL);
    public static final VillagerProfession HUNTER = registerVillagerProfession("hunter", HUNTER_POI_KEY);

    private static VillagerProfession registerVillagerProfession(String name, RegistryKey<PointOfInterestType> poiType) {
        return Registry.register(
                Registries.VILLAGER_PROFESSION,
                Identifier.of(Ecraft.MOD_ID, name),
                new VillagerProfession(
                        name,
                        pointOfInterestTypeRegistryEntry -> pointOfInterestTypeRegistryEntry.matchesKey(poiType),
                        pointOfInterestTypeRegistryEntry -> pointOfInterestTypeRegistryEntry.matchesKey(poiType),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.BLOCK_ANVIL_USE
                )
        );
    }

    private static PointOfInterestType registerPoi(String name, Block block) {
        return PointOfInterestHelper.register(Identifier.of(Ecraft.MOD_ID, name),
                1, 1, block);
    }


    private static RegistryKey<PointOfInterestType> registerPoiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier.of(Ecraft.MOD_ID, name));
    }

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing villagers");
    }

    public static void addTrades() {
        Ecraft.LOGGER.info("Registering Hunter trades for level 1");
        TradeOfferHelper.registerVillagerOffers(
                HUNTER,
                1,
                factories -> {
                    factories.add(new TradeOffers.SellItemFactory(
                            new ItemStack(EItems.MANA_STAR_SMITHING_TEMPLATE),
                            32, 1, 3, 4, 0.08f
                    ));
                    factories.add(new TradeOffers.SellItemFactory(
                            new ItemStack(EItems.MEDALLION_OF_SPACE_STONE),
                            8, 1, 8, 2, 0.04f
                    ));
                    factories.add(new TradeOffers.BuyItemFactory(
                            EItems.MAGIC_DUST,
                            6, 8, 2
                    ));
                    factories.add(new TradeOffers.SellItemFactory(
                            new ItemStack(Items.GLOW_INK_SAC),
                            20, 6, 8, 2, 0.04f
                    ));
                }
        );

        Ecraft.LOGGER.info("Registering Hunter trades for level 2");
        TradeOfferHelper.registerVillagerOffers(
                HUNTER,
                2,
                factories -> {
                    factories.add(new TradeOffers.SellEnchantedToolFactory(
                            EArmor.TUNGSTEN_HELMET,
                            4, 2, 35, 0.3f
                    ));
                    factories.add(new TradeOffers.SellEnchantedToolFactory(
                            EArmor.TUNGSTEN_CHESTPLATE,
                            8, 2, 35, 0.3f
                    ));
                    factories.add(new TradeOffers.SellEnchantedToolFactory(
                            EArmor.TUNGSTEN_LEGGINGS,
                            6, 2, 35, 0.3f
                    ));
                    factories.add(new TradeOffers.SellEnchantedToolFactory(
                            EArmor.TUNGSTEN_BOOTS,
                            3, 2, 35, 0.3f
                    ));
                }
        );

        Ecraft.LOGGER.info("Registering Hunter trades for level 3");
        TradeOfferHelper.registerVillagerOffers(
                HUNTER,
                3,
                factories -> {
                    factories.add(new TradeOffers.SellEnchantedToolFactory(
                            EWeapons.TUNGSTEN_LANCE,
                            8, 2, 35, 0.3f
                    ));
                    factories.add(new TradeOffers.SellEnchantedToolFactory(
                            EWeapons.TUNGSTEN_STAFF,
                            10, 2, 35, 0.3f
                    ));
                    factories.add(new TradeOffers.SellEnchantedToolFactory(
                            EWeapons.TUNGSTEN_PICKSAW,
                            12, 2, 35, 0.3f
                    ));
                }
        );

        Ecraft.LOGGER.info("Registering Hunter trades for level 4");
        TradeOfferHelper.registerVillagerOffers(
                HUNTER,
                4,
                factories -> {
                    factories.add(new TradeOffers.SellItemFactory(
                            new ItemStack(EItems.MAGIC_ARROW),
                            4, 16, 8, 8, 0.1f
                    ));
                    factories.add(new TradeOffers.SellItemFactory(
                            new ItemStack(EItems.SHARD_OF_TRAVEL),
                            16, 28, 8, 8, 0.1f
                    ));
                    factories.add(new TradeOffers.SellItemFactory(
                            new ItemStack(EItems.GUNLANCE_SHELL),
                            24, 16, 8, 8, 0.1f
                    ));
                    factories.add(new TradeOffers.SellItemFactory(
                            new ItemStack(EItems.GUNLANCE_SMITHING_TEMPLATE),
                            64, 1, 8, 8, 0.1f
                    ));
                }
        );

        Ecraft.LOGGER.info("Registering Hunter trades for level 5");
        TradeOfferHelper.registerVillagerOffers(
                HUNTER,
                5,
                factories -> {
                    factories.add(new TradeOffers.SellEnchantedToolFactory(
                            EWeapons.MURAMASA,
                            48, 2, 70, 0.3f
                    ));
                    factories.add(new TradeOffers.SellEnchantedToolFactory(
                            EWeapons.VOLCANO,
                            16, 2, 70, 0.3f
                    ));
                    factories.add(new TradeOffers.SellEnchantedToolFactory(
                            EWeapons.HELLSTONE_LANCE,
                            12, 2, 70, 0.3f
                    ));
                    factories.add(new TradeOffers.SellEnchantedToolFactory(
                            EWeapons.HELLSTONE_STAFF,
                            14, 2, 70, 0.3f
                    ));
                }
        );
    }
}
