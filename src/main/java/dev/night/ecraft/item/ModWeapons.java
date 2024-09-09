package dev.night.ecraft.item;

import com.provismet.AdditionalArmoury.items.AAToolMaterials;
import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.items.MaceItem;
import com.provismet.AdditionalArmoury.registries.AADataComponentTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import dev.night.ecraft.Ecraft;
import dev.night.ecraft.util.ItemList;
import dev.night.ecraft.util.Utilities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Rarity;
import nourl.mythicmetals.item.tools.MythicToolMaterials;

import java.util.ArrayList;
import java.util.List;

public class ModWeapons {
    protected static ItemList<Item> lances = new ItemList<Item>();
    protected static ItemList<Item> gunlances = new ItemList<Item>();

    //region Lances
    public static final Item WOODEN_LANCE = lances.put(ModItems.registerItem("wooden_lance",
            new LanceItem(ToolMaterials.WOOD)));

    public static final Item STONE_LANCE = lances.put(ModItems.registerItem("stone_lance",
            new LanceItem(ToolMaterials.STONE)));

    public static final Item COPPER_LANCE = lances.put(ModItems.registerItem("copper_lance",
            new LanceItem(MythicToolMaterials.COPPER)));

    public static final Item IRON_LANCE = lances.put(ModItems.registerItem("iron_lance",
            new LanceItem(ToolMaterials.IRON)));

    public static final Item GOLDEN_LANCE = lances.put(ModItems.registerItem("golden_lance",
            new LanceItem(ToolMaterials.GOLD, 0, 0.05f)));

    public static final Item DIAMOND_LANCE = lances.put(ModItems.registerItem("diamond_lance",
            new LanceItem(ToolMaterials.DIAMOND, 0, 0.05f)));

    public static final Item ADAMANTITE_LANCE = lances.put(ModItems.registerItem("adamantite_lance",
            new LanceItem(MythicToolMaterials.ADAMANTITE, 0, 0.1f)));

    public static final Item AQUARIUM_LANCE = lances.put(ModItems.registerItem("aquarium_lance",
            new LanceItem(MythicToolMaterials.AQUARIUM, 0, 0.05f)));

    public static final Item BANGLUM_LANCE = lances.put(ModItems.registerItem("banglum_lance",
            new LanceItem(MythicToolMaterials.BANGLUM, 0, 0.05f)));

    public static final Item BRONZE_LANCE = lances.put(ModItems.registerItem("bronze_lance",
            new LanceItem(MythicToolMaterials.BRONZE, 0, 0.05f)));

    // TODO add bonus fortune
    public static final Item CARMOT_LANCE = lances.put(ModItems.registerItem("carmot_lance",
            new LanceItem(MythicToolMaterials.CARMOT,0, 0.05f)));

    public static final Item CELESTIUM_LANCE = lances.put(ModItems.registerItem("celestium_lance",
            new LanceItem(MythicToolMaterials.CELESTIUM, 1, 0.7f)));

    public static final Item CINCINNASITE_DIAMOND_LANCE = lances.put(ModItems.registerItem("cincinnasite_diamond_lance",
            new LanceItem(ModToolMaterials.CINCINNASITE_DIAMOND, -2.3f, 0.4f)));

    public static final Item DURASTEEL_LANCE = lances.put(ModItems.registerItem("durasteel_lance",
            new LanceItem(MythicToolMaterials.DURASTEEL, 0, 0.05f)));

    // TODO buff endernether
    public static final Item ENDERNETHER_LANCE = lances.put(ModItems.registerItem("endernether_lance",
            new LanceItem(AAToolMaterials.ENDERNETHER, 0, 0.4f)));

    public static final Item HALLOWED_LANCE = lances.put(ModItems.registerItem("hallowed_lance",
            new LanceItem(MythicToolMaterials.HALLOWED, 0, 0.1f)));

    public static final Item KYBER_LANCE = lances.put(ModItems.registerItem("kyber_lance",
            new LanceItem(MythicToolMaterials.KYBER, 0, 0.1f)));

    // TODO add natural knockback 3
    public static final Item LEGENDARY_BANGLUM_LANCE = lances.put(ModItems.registerItem("legendary_banglum_lance",
            new LanceItem(MythicToolMaterials.LEGENDARY_BANGLUM)));

    public static final Item METALLURGIUM_LANCE = lances.put(ModItems.registerItem("metallurgium_lance",
            new LanceItem(MythicToolMaterials.METALLURGIUM, 3, 0.5f)));

    public static final Item MYTHRIL_LANCE = lances.put(ModItems.registerItem("mythril_lance",
            new LanceItem(MythicToolMaterials.MYTHRIL, 0, 0.25f)));

    // TODO buff netherite
    public static final Item NETHERITE_LANCE = lances.put(ModItems.registerItem("netherite_lance",
            new LanceItem(ToolMaterials.NETHERITE, 0, 0.1f)));

    public static final Item ORICHALCUM_LANCE = lances.put(ModItems.registerItem("orichalcum_lance",
            new LanceItem(MythicToolMaterials.ORICHALCUM)));

    public static final Item OSMIUM_LANCE = lances.put(ModItems.registerItem("osmium_lance",
            new LanceItem(MythicToolMaterials.OSMIUM)));

    // TODO buff overnether
    public static final Item OVERNETHER_LANCE = lances.put(ModItems.registerItem("overnether_lance",
            new LanceItem(AAToolMaterials.OVERNETHER, 0, 0.1f)));

    // TODO add branding
    public static final Item PALLADIUM_LANCE = lances.put(ModItems.registerItem("palladium_lance",
            new LanceItem(MythicToolMaterials.PALLADIUM, 0, 0.1f)));

    // TODO add regrowth
    public static final Item PROMETHEUM_LANCE = lances.put(ModItems.registerItem("prometheum_lance",
            new LanceItem(MythicToolMaterials.PROMETHEUM, 0, 0.05f)));

    public static final Item QUADRILLUM_LANCE = lances.put(ModItems.registerItem("quadrillum_lance",
            new LanceItem(MythicToolMaterials.QUADRILLUM)));

    public static final Item RUNITE_LANCE = lances.put(ModItems.registerItem("runite_lance",
            new LanceItem(MythicToolMaterials.RUNITE, 0f, 0.05f)));

    public static final Item STAR_PLATINUM_LANCE = lances.put(ModItems.registerItem("star_platinum_lance",
            new LanceItem(MythicToolMaterials.STAR_PLATINUM, 0, 0.3f)));

    public static final Item STEEL_LANCE = lances.put(ModItems.registerItem("steel_lance",
            new LanceItem(MythicToolMaterials.STEEL)));

    public static final Item STORMYX_LANCE = lances.put(ModItems.registerItem("stormyx_lance",
            new LanceItem(MythicToolMaterials.STORMYX, 0, 0.05f)));

    public static final Item TERRASTEEL_LANCE = lances.put(ModItems.registerItem("terrasteel_lance",
            new LanceItem(ModToolMaterials.TERRASTEEL, 1, 0.55f)));

    // TODO add submerged buff
    public static final Item TIDESINGER_LANCE = lances.put(ModItems.registerItem("tidesinger_lance",
            new LanceItem(MythicToolMaterials.TIDESINGER, 0, 0.3f)));
    //endregion

    //region Gunlances
    public static final Item CINCINNASITE_DIAMOND_GUNLANCE = gunlances.put(ModItems.registerItem("cincinnasite_diamond_gunlance",
            new GunlanceItem(
                    ModToolMaterials.CINCINNASITE_DIAMOND,
                    new Item.Settings()
                            .attributeModifiers(
                                    LanceItem.getLanceAttributes(
                                            ModToolMaterials.CINCINNASITE_DIAMOND,
                                            1.3f,
                                            -2.6f
                                    )
                            )
            )
    ));


    public static final Item NETHERITE_GUNLANCE = gunlances.put(ModItems.registerItem("netherite_gunlance",
            new GunlanceItem(
                    ToolMaterials.NETHERITE,
                    new Item.Settings()
                            .attributeModifiers(
                                    LanceItem.getLanceAttributes(
                                            ToolMaterials.NETHERITE,
                                            4f,
                                            -2.6f
                                    )
                            )
            )
    ));
    //endregion

    //region Cincinnasite-Diamond Gear
    public static final Item CINCINNASITE_DIAMOND_DAGGER = ModItems.registerItem("cincinnasite_diamond_dagger",
            new DaggerItem(
                    ModToolMaterials.CINCINNASITE_DIAMOND,
                    new Item.Settings()
                            .rarity(Rarity.UNCOMMON)
                            .maxCount(1)
                            .attributeModifiers(
                                    DaggerItem.createDefaultDaggerAttributes(ModToolMaterials.CINCINNASITE_DIAMOND)
                            )
            )
    );

    public static final Item CINCINNASITE_DIAMOND_MACE = ModItems.registerItem("cincinnasite_diamond_mace",
            new MaceItem(
                    ModToolMaterials.CINCINNASITE_DIAMOND,
                    new Item.Settings()
                            .rarity(Rarity.UNCOMMON)
                            .maxCount(1)
                            .attributeModifiers(
                                    MaceItem.createDefaultMaceAttributes(ModToolMaterials.CINCINNASITE_DIAMOND)
                            )
            )
    );
    //endregion

    //region Terrasteel Gear
    public static final Item TERRASTEEL_SWORD = ModItems.registerItem("terrasteel_sword",
            new SwordItem(
                    ModToolMaterials.TERRASTEEL,
                    new Item.Settings()
                            .rarity(Rarity.RARE)
                            .fireproof()
                            .attributeModifiers(
                                    Utilities.createToolAttributes(
                                            ModToolMaterials.TERRASTEEL,
                                            4f, -2f
                                    )
                            )
            )
    );

    public static final Item TERRASTEEL_AXE = ModItems.registerItem("terrasteel_axe",
            new AxeItem(
                    ModToolMaterials.TERRASTEEL,
                    new Item.Settings()
                            .rarity(Rarity.RARE)
                            .fireproof()
                            .attributeModifiers(
                                    Utilities.createToolAttributes(
                                            ModToolMaterials.TERRASTEEL,
                                            6f, -2.5f
                                    )
                            )
            )
    );

    public static final Item TERRASTEEL_SHOVEL = ModItems.registerItem("terrasteel_shovel",
            new ShovelItem(
                    ModToolMaterials.TERRASTEEL,
                    new Item.Settings()
                            .rarity(Rarity.RARE)
                            .fireproof()
                            .attributeModifiers(
                                    Utilities.createToolAttributes(
                                            ModToolMaterials.TERRASTEEL,
                                            1.5f, -2.4f
                                    )
                            )
            )
    );

    public static final Item TERRASTEEL_PICKAXE = ModItems.registerItem("terrasteel_pickaxe",
            new PickaxeItem(
                    ModToolMaterials.TERRASTEEL,
                    new Item.Settings()
                            .rarity(Rarity.RARE)
                            .fireproof()
                            .attributeModifiers(
                                    Utilities.createToolAttributes(
                                            ModToolMaterials.TERRASTEEL,
                                            2.5f, -2.5f
                                    )
                            )
            )
    );

    public static final Item TERRASTEEL_HOE = ModItems.registerItem("terrasteel_hoe",
            new HoeItem(
                    ModToolMaterials.TERRASTEEL,
                    new Item.Settings()
                            .rarity(Rarity.RARE)
                            .fireproof()
                            .attributeModifiers(
                                    Utilities.createToolAttributes(
                                            ModToolMaterials.TERRASTEEL,
                                            1f, -2.1f
                                    )
                            )
            )
    );
    //endregion

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing weapons");
    }
}
