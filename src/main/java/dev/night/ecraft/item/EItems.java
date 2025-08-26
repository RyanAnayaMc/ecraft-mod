package dev.night.ecraft.item;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.component.EComponents;
import dev.night.ecraft.item.tools.*;
import dev.night.ecraft.item.tools.weapons.GunlanceItem;
import dev.night.ecraft.item.trinkets.ManaStarTrinketItem;
import dev.night.ecraft.item.trinkets.MedallionOfSpaceTrinketItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;

public class EItems {
    //region Mega Buster
    public static final Item MEGA_BUSTER_SHOT_1 =
            registerItem("mega_buster_shot_1", new Item(new Item.Settings()));

    public static final Item MEGA_BUSTER_SHOT_2 =
            registerItem("mega_buster_shot_2", new Item(new Item.Settings()));

    public static final Item MEGA_BUSTER_SHOT_3 =
            registerItem("mega_buster_shot_3", new Item(new Item.Settings()));

    public static final Item MEGA_BUSTER_SHOT_4 =
            registerItem("mega_buster_shot_4", new Item(new Item.Settings()));
    //endregion

    //region Staff Projectiles
    public static final Item COPPER_STAFF_PROJECTILE = registerItem("copper_staff_projectile", new Item(new Item.Settings()));
    public static final Item TUNGSTEN_STAFF_PROJECTILE = registerItem("tungsten_staff_projectile", new Item(new Item.Settings()));
    public static final Item IRON_STAFF_PROJECTILE = registerItem("iron_staff_projectile", new Item(new Item.Settings()));
    public static final Item GOLDEN_STAFF_PROJECTILE = registerItem("gold_staff_projectile", new Item(new Item.Settings()));
    public static final Item DEMONITE_STAFF_PROJECTILE = registerItem("demonite_staff_projectile", new Item(new Item.Settings()));
    public static final Item HELLSTONE_STAFF_PROJECTILE = registerItem("hellstone_staff_projectile", new Item(new Item.Settings()));
    public static final Item LIGHTNING_STAFF_PROJECTILE = registerItem("staff_of_lightning_projectile", new Item(new Item.Settings()));
    public static final Item FIRE_STAFF_PROJECTILE = registerItem("staff_of_fire_projectile", new Item(new Item.Settings()));
    //endregion

    public static final Item E_DEBUG_STICK =
            registerItem("e_debug_stick", new DebugStick(new Item.Settings().component(EComponents.GUNLANCE_COMPONENT, GunlanceItem.getDefaultComponent(8))));

    public static final Item ROD_OF_DISCORD =
            registerItem("rod_of_discord", new RodOfDiscordItem(new Item.Settings().component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true).rarity(Rarity.RARE).maxCount(1), true));

    public static final Item ROD_OF_HARMONY =
            registerItem("rod_of_harmony", new RodOfDiscordItem(new Item.Settings().component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true).rarity(Rarity.EPIC).maxCount(1), false));

    public static final Item ENDLESS_WATER_BUCKET =
            registerItem(
                    "endless_water_bucket",
                    new EndlessBucketItem(
                            Fluids.WATER,
                            new Item.Settings()
                                    .maxCount(1)
                                    .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                                    .rarity(Rarity.UNCOMMON)
                    ) {
                        @Override
                        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                            super.appendTooltip(stack, context, tooltip, type);
                            tooltip.add(Text.translatable("item.ecraft.endless_water_bucket.tooltip"));
                        }
                    }
            );

    public static final Item ENDLESS_LAVA_BUCKET =
            registerItem(
                    "endless_lava_bucket",
                    new EndlessBucketItem(
                            Fluids.LAVA,
                            new Item.Settings()
                                    .maxCount(1)
                                    .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                                    .rarity(Rarity.UNCOMMON)
                    ) {
                        @Override
                        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                            super.appendTooltip(stack, context, tooltip, type);
                            tooltip.add(Text.translatable("item.ecraft.endless_lava_bucket.tooltip"));
                            tooltip.add(Text.translatable("item.ecraft.endless_lava_bucket.tooltip_2"));
                        }
                    }
            );

    public static final Item ENDLESS_MILK_BUCKET =
            registerItem(
                    "endless_milk_bucket",
                    new EndlessMilkBucketItem(
                            new Item.Settings()
                                    .maxCount(1)
                                    .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                                    .rarity(Rarity.UNCOMMON)
                    ) {
                        @Override
                        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                            super.appendTooltip(stack, context, tooltip, type);
                            tooltip.add(Text.translatable("item.ecraft.endless_milk_bucket.tooltip"));
                            tooltip.add(Text.translatable("item.ecraft.endless_milk_bucket.tooltip_2"));
                        }
                    }
            );

    //region Ingots, Nuggets and Raw
    public static final Item CINCINNASITE_DIAMOND_INGOT =
            registerItem("cincinnasite_diamond_ingot", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));

    public static final Item CINCINNASITE_DIAMOND_NUGGET =
            registerItem("cincinnasite_diamond_nugget", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));

    public static final Item TERRASTEEL_INGOT =
            registerItem("terrasteel_ingot", new Item(new Item.Settings().rarity(Rarity.RARE).fireproof()));

    public static final Item TERRASTEEL_NUGGET =
            registerItem("terrasteel_nugget", new Item(new Item.Settings().rarity(Rarity.UNCOMMON).fireproof()));

    public static final Item RAW_DIAMOND =
            registerItem("raw_diamond", new Item(new Item.Settings()));

    public static final Item COPPER_NUGGET =
            registerItem("copper_nugget", new Item(new Item.Settings()));

    public static final Item RAW_DEMONITE =
            registerItem("raw_demonite", new Item(new Item.Settings()));

    public static final Item DEMONITE_INGOT =
            registerItem("demonite_ingot", new Item(new Item.Settings()));

    public static final Item DEMONITE_NUGGET =
            registerItem("demonite_nugget", new Item(new Item.Settings()));

    public static final Item RAW_TUNGSTEN =
            registerItem("raw_tungsten", new Item(new Item.Settings()));

    public static final Item TUNGSTEN_INGOT =
            registerItem("tungsten_ingot", new Item(new Item.Settings()));

    public static final Item TUNGSTEN_NUGGET =
            registerItem("tungsten_nugget", new Item(new Item.Settings()));

    public static final Item RAW_HELLSTONE =
            registerItem("raw_hellstone", new Item(new Item.Settings().fireproof()));

    public static final Item HELLSTONE_INGOT =
            registerItem("hellstone_ingot", new Item(new Item.Settings().fireproof()));

    public static final Item HELLSTONE_NUGGET =
            registerItem("hellstone_nugget", new Item(new Item.Settings().fireproof()));

    public static final Item RAW_COBALT =
            registerItem("raw_cobalt", new Item(new Item.Settings().fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item COBALT_INGOT =
            registerItem("cobalt_ingot", new Item(new Item.Settings().fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item COBALT_NUGGET =
            registerItem("cobalt_nugget", new Item(new Item.Settings().fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item RAW_ORICHALCUM =
            registerItem("raw_orichalcum", new Item(new Item.Settings().fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item ORICHALCUM_INGOT =
            registerItem("orichalcum_ingot", new Item(new Item.Settings().fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item ORICHALCUM_NUGGET =
            registerItem("orichalcum_nugget", new Item(new Item.Settings().fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item RAW_TITANIUM =
            registerItem("raw_titanium", new Item(new Item.Settings().fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item TITANIUM_INGOT =
            registerItem("titanium_ingot", new Item(new Item.Settings().fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item TITANIUM_NUGGET =
            registerItem("titanium_nugget", new Item(new Item.Settings().fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item HALLOWED_INGOT =
            registerItem("hallowed_ingot", new Item(new Item.Settings().fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item HALLOWED_NUGGET =
            registerItem("hallowed_nugget", new Item(new Item.Settings().fireproof().rarity(Rarity.UNCOMMON)));

    public static final Item RAW_VERDANT =
            registerItem("raw_verdant", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE)));

    public static final Item VERDANT_INGOT =
            registerItem("verdant_ingot", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE)));

    public static final Item VERDANT_NUGGET =
            registerItem("verdant_nugget", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE)));

    public static final Item RAW_DETONIUM =
            registerItem("raw_detonium", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE)));

    public static final Item DETONIUM_INGOT =
            registerItem("detonium_ingot", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE)));

    public static final Item DETONIUM_NUGGET =
            registerItem("detonium_nugget", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE)));

    public static final Item RAW_AETHERIUM_CRYSTAL =
            registerItem("raw_aetherium_crystal", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE)));

    public static final Item REFINED_AETHERIUM_CRYSTAL =
            registerItem("refined_aetherium_crystal", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE)));

    public static final Item FLAWLESS_AETHERIUM_CRYSTAL =
            registerItem("flawless_aetherium_crystal", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE)));
    //endregion

    public static final Item DETONIUM_DETONATOR =
            registerItem("detonium_detonator", new DetonatorItem(new Item.Settings()));

    public static final Item SHARD_OF_TRAVEL =
            registerItem("shard_of_travel",
                    new Item(
                            new Item.Settings()
                                .rarity(Rarity.RARE)
                                .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                    ) {
                        @Override
                        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                            tooltip.add(Text.translatable("item.ecraft.shard_of_travel.tooltip"));
                        }
                    }
            );

    public static final Item GUNLANCE_SHELL =
            registerItem("gunlance_shell", new Item(new Item.Settings()));

    public static final Item SMOOTHBORE_BARREL =
            registerItem("smoothbore_barrel", new Item(new Item.Settings()));

    public static final Item GUNLANCE_SMITHING_TEMPLATE =
            registerItem("gunlance_smithing_template", new Item(new Item.Settings()));

    public static final Item PACK_A_PUNCH_SMITHING_TEMPLATE =
            registerItem(
                    "pack_a_punch_smithing_template",
                    new Item(
                            new Item.Settings()
                                    .rarity(Rarity.EPIC)
                                    .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                    ) {
                        @Override
                        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                            tooltip.add(Text.translatable("item.ecraft.pack_a_punch_smithing_template.tooltip"));
                            tooltip.add(Text.translatable("item.ecraft.pack_a_punch_smithing_template.tooltip_2"));
                            tooltip.add(Text.translatable("item.ecraft.pack_a_punch_smithing_template.tooltip_3"));
                            tooltip.add(Text.translatable("item.ecraft.pack_a_punch_smithing_template.tooltip_4"));
                        }
                    }
            );

    public static final Item MAGIC_DUST =
            registerItem("magic_dust", new Item(new Item.Settings()));

    public static final Item MAGIC_CRYSTAL =
            registerItem("magic_crystal", new Item(new Item.Settings()));

    public static final Item VERDANT_MAGIC_CRYSTAL =
            registerItem("verdant_magic_crystal", new Item(new Item.Settings().fireproof().rarity(Rarity.RARE)));

    public static final Item MAGIC_ARROW =
            registerItem(
                    "magic_arrow",
                    new MagicArrowItem(
                            new Item.Settings()
                                    .rarity(Rarity.UNCOMMON)
                    ) {
                        @Override
                        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                            tooltip.add(Text.translatable("item.ecraft.magic_arrow.tooltip"));
                            tooltip.add(Text.translatable("item.ecraft.magic_arrow.tooltip_2"));
                        }
                    }
            );

    public static final Item MANA_STAR =
            registerItem("mana_star", new ManaStarTrinketItem(new Item.Settings().maxCount(1)));

    public static final Item VERDANT_MANA_STAR =
            registerItem("verdant_mana_star", new ManaStarTrinketItem(new Item.Settings().maxCount(1).fireproof().rarity(Rarity.RARE), 100));

    public static final Item AETHERIUM_MANA_STAR =
            registerItem("aetherium_mana_star", new ManaStarTrinketItem(new Item.Settings().maxCount(1).fireproof().rarity(Rarity.EPIC), 150));

    public static final Item FLAWLESS_AETHERIUM_MANA_STAR =
            registerItem("flawless_aetherium_mana_star", new ManaStarTrinketItem(new Item.Settings().maxCount(1).fireproof().rarity(Rarity.EPIC).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true), 200));

    public static final Item MANA_STAR_SMITHING_TEMPLATE =
            registerItem("mana_star_smithing_template",
                    new SmithingTemplateItem(
                            Text.translatable("smithing_template.mana_star_upgrade.applies_to").formatted(Formatting.BLUE),
                            Text.translatable("smithing_template.mana_star_upgrade.ingredients").formatted(Formatting.BLUE),
                            Text.translatable("mana_star_upgrade").formatted(Formatting.GRAY),
                            Text.translatable("smithing_template.mana_star_upgrade.base_slot_description"),
                            Text.translatable("smithing_template.mana_star_upgrade.additions_slot_description"),
                            List.of(Identifier.of(Ecraft.MOD_ID, "item/empty_slot_mana_star")),
                            List.of(Identifier.of(Ecraft.MOD_ID, "item/empty_slot_mana_star"))
                    )
            );

    public static final Item CALIBURN_SMITHING_TEMPLATE =
            registerItem("caliburn_smithing_template",
                    new SmithingTemplateItem(
                            Text.translatable("smithing_template.bastard_sword_upgrade.applies_to").formatted(Formatting.BLUE),
                            Text.translatable("smithing_template.bastard_sword_upgrade.ingredients").formatted(Formatting.BLUE),
                            Text.translatable("bastard_sword_upgrade.dragon").formatted(Formatting.GRAY),
                            Text.translatable("smithing_template.bastard_sword_upgrade.base_slot_description"),
                            Text.translatable("smithing_template.bastard_sword_upgrade.additions_slot_description"),
                            List.of(Identifier.of(Ecraft.MOD_ID, "item/empty_slot_bastard_sword")),
                            List.of(Identifier.of(Ecraft.MOD_ID, "item/empty_slot_flawless_aetherium_crystal"))
                    )
            );

    public static final Item DURENDAL_SMITHING_TEMPLATE =
            registerItem("durendal_smithing_template",
                    new SmithingTemplateItem(
                            Text.translatable("smithing_template.bastard_sword_upgrade.applies_to").formatted(Formatting.BLUE),
                            Text.translatable("smithing_template.bastard_sword_upgrade.ingredients").formatted(Formatting.BLUE),
                            Text.translatable("bastard_sword_upgrade.stag").formatted(Formatting.GRAY),
                            Text.translatable("smithing_template.bastard_sword_upgrade.base_slot_description"),
                            Text.translatable("smithing_template.bastard_sword_upgrade.additions_slot_description"),
                            List.of(Identifier.of(Ecraft.MOD_ID, "item/empty_slot_bastard_sword")),
                            List.of(Identifier.of(Ecraft.MOD_ID, "item/empty_slot_flawless_aetherium_crystal"))
                    )
            );

    public static final Item SOLAIS_SMITHING_TEMPLATE =
            registerItem("solais_smithing_template",
                    new SmithingTemplateItem(
                            Text.translatable("smithing_template.bastard_sword_upgrade.applies_to").formatted(Formatting.BLUE),
                            Text.translatable("smithing_template.bastard_sword_upgrade.ingredients").formatted(Formatting.BLUE),
                            Text.translatable("bastard_sword_upgrade.lion").formatted(Formatting.GRAY),
                            Text.translatable("smithing_template.bastard_sword_upgrade.base_slot_description"),
                            Text.translatable("smithing_template.bastard_sword_upgrade.additions_slot_description"),
                            List.of(Identifier.of(Ecraft.MOD_ID, "item/empty_slot_bastard_sword")),
                            List.of(Identifier.of(Ecraft.MOD_ID, "item/empty_slot_flawless_aetherium_crystal"))
                    )
            );

    public static final Item MEDALLION_OF_SPACE_STONE =
            registerItem("medallion_of_space_stone", new MedallionOfSpaceTrinketItem(new Item.Settings(), 3));

    public static final Item MEDALLION_OF_SPACE_TUNGSTEN =
            registerItem("medallion_of_space_tungsten", new MedallionOfSpaceTrinketItem(new Item.Settings(), 6));

    public static final Item MEDALLION_OF_SPACE_DEMONITE =
            registerItem("medallion_of_space_demonite", new MedallionOfSpaceTrinketItem(new Item.Settings().rarity(Rarity.UNCOMMON), 9));

    public static final Item MEDALLION_OF_SPACE_COBALT =
            registerItem("medallion_of_space_cobalt", new MedallionOfSpaceTrinketItem(new Item.Settings().rarity(Rarity.UNCOMMON), 12));

    public static final Item MEDALLION_OF_SPACE_VERDANT =
            registerItem("medallion_of_space_verdant", new MedallionOfSpaceTrinketItem(new Item.Settings().rarity(Rarity.RARE), 15));

    public static final Item MEDALLION_OF_SPACE_AETHERIUM =
            registerItem("medallion_of_space_aetherium", new MedallionOfSpaceTrinketItem(new Item.Settings().rarity(Rarity.RARE), 18));

    public static final Item MEDALLION_OF_SPACE_FLAWLESS_AETHERIUM =
            registerItem("medallion_of_space_flawless_aetherium", new MedallionOfSpaceTrinketItem(new Item.Settings().rarity(Rarity.EPIC).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true), 27));

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " registering items");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(EItems.CINCINNASITE_DIAMOND_INGOT);
        });
    }

    /**
     * Registers an item to the registry
     * @param name The name for the item
     * @param item The item to add
     * @return The item added
     */
    public static Item registerItem(String name, Item item) {
        return registerItem(name, item, Ecraft.MOD_ID);
    }

    /**
     * Registers an item to the registry with a spoofed mod ID
     * @param name The name for the item
     * @param item The item to add
     * @param spoofID The mod ID to use for this item
     * @return The item added
     */
    public static Item registerItem(String name, Item item, String spoofID) {
        return Registry.register(Registries.ITEM, Identifier.of(spoofID, name), item);
    }
}
