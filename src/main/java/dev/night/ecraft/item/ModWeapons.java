package dev.night.ecraft.item;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.items.MaceItem;
import com.provismet.AdditionalArmoury.registries.AADataComponentTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import dev.night.ecraft.Ecraft;
import dev.night.ecraft.item.fishing_rod.EModFishingRodItem;
import dev.night.ecraft.item.fishing_rod.EModOnAStickItem;
import dev.night.ecraft.item.spear.ESpearItem;
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
import solipingen.sassot.SpearsAxesSwordsShieldsAndOtherTools;
import solipingen.sassot.item.ModFishingRodItem;
import solipingen.sassot.item.ModOnAStickItem;
import solipingen.sassot.item.ModShieldItem;
import solipingen.sassot.item.SpearItem;

public class ModWeapons {

    public static final Item METALLURGIUM_SPEAR = ModItems.registerItem("metallurgium_spear",
            new ESpearItem(
                    MythicToolMaterials.METALLURGIUM,
                    new Item.Settings()
                            .rarity(Rarity.RARE)
                            .attributeModifiers(
                                    SpearItem.createAttributeModifiers(
                                            MythicToolMaterials.METALLURGIUM,
                                            solipingen.sassot.item.ModItems.SPEAR_BASE_ATTACK_DAMAGE,
                                            -2.3f
                                    )
                            )
            )
            , Ecraft.SASSOT_ID); // spoofed mod ID because sassot hardcodes it in asset loading



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

    public static final Item CINCINNASITE_DIAMOND_SPEAR = ModItems.registerItem("cincinnasite_diamond_spear",
            new ESpearItem(
                    ModToolMaterials.CINCINNASITE_DIAMOND,
                    new Item.Settings()
                            .rarity(Rarity.UNCOMMON)
                            .attributeModifiers(
                                    SpearItem.createAttributeModifiers(
                                            ModToolMaterials.CINCINNASITE_DIAMOND,
                                            solipingen.sassot.item.ModItems.SPEAR_BASE_ATTACK_DAMAGE,
                                            -2.2f
                                    )
                            )
            )
    , Ecraft.SASSOT_ID); // spoofed mod ID because sassot hardcodes it in asset loading

    public static final Item CINCINNASITE_DIAMOND_SHIELD = ModItems.registerItem("cincinnasite_diamond_shield",
            new ModShieldItem(
                    ModToolMaterials.CINCINNASITE_DIAMOND,
                    5.7F,
                    false,
                    130,
                    0.14f,
                    new Item.Settings()
            )
    , Ecraft.SASSOT_ID); // spoofed mod ID because sassot hardcodes it in asset loading

    public static final Item CINCINNASITE_DIAMOND_FRAMED_WOODEN_SHIELD = ModItems.registerItem("cincinnasite_diamond_framed_wooden_shield",
            new ModShieldItem(
                    ModToolMaterials.CINCINNASITE_DIAMOND,
                    4.0F,
                    true,
                    60,
                    0.5f,
                    new Item.Settings()
            )
    , Ecraft.SASSOT_ID); // spoofed mod ID because sassot hardcodes it in asset loading

    public static final Item CINCINNASITE_DIAMOND_FUSED_FISHING_ROD = ModItems.registerItem("cincinnasite_diamond_fused_fishing_rod",
            new EModFishingRodItem(
                    new Item.Settings(),
                    ModToolMaterials.CINCINNASITE_DIAMOND
            )
    , Ecraft.SASSOT_ID); // spoofed mod ID because sassot hardcodes it in asset loading

    public static final Item WARPED_FUNGUS_ON_A_CINCINNASITE_DIAMOND_FUSED_STICK = ModItems.registerItem("warped_fungus_on_a_cincinnasite_diamond_fused_stick",
            new EModOnAStickItem<StriderEntity>(
                    new Item.Settings().maxDamage(100),
                    EntityType.STRIDER,
                    ModToolMaterials.CINCINNASITE_DIAMOND,
                    1
            )
    , Ecraft.SASSOT_ID);

    public static final Item CARROT_ON_A_CINCINNASITE_DIAMOND_FUSED_STICK = ModItems.registerItem("carrot_on_a_cincinnasite_diamond_fused_stick",
            new EModOnAStickItem<PigEntity>(
                    new Item.Settings().maxDamage(25),
                    EntityType.PIG,
                    ModToolMaterials.CINCINNASITE_DIAMOND,
                    7
            )
    , Ecraft.SASSOT_ID);
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
