package dev.night.ecraft.potion;

import dev.night.ecraft.item.EItems;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;

public class BrewingRecipeRegistry {
    public static void registerBrewingRecipes() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(
                builder -> {
                    builder.registerPotionRecipe(Potions.AWKWARD, EItems.MAGIC_DUST, EPotions.LESSER_MANA_HEAL);
                    builder.registerPotionRecipe(EPotions.LESSER_MANA_HEAL, Items.GLOWSTONE_DUST, EPotions.MANA_HEAL);
                    builder.registerPotionRecipe(Potions.AWKWARD, EItems.MAGIC_CRYSTAL, EPotions.GREATER_MANA_HEAL);
                    builder.registerPotionRecipe(EPotions.GREATER_MANA_HEAL, Items.GLOWSTONE_DUST, EPotions.SUPER_MANA_HEAL);

                    builder.registerPotionRecipe(EPotions.LESSER_MANA_HEAL, Items.FERMENTED_SPIDER_EYE, EPotions.MANA_BLOCKADE);
                    builder.registerPotionRecipe(EPotions.MANA_HEAL, Items.FERMENTED_SPIDER_EYE, EPotions.MANA_BLOCKADE);
                    builder.registerPotionRecipe(EPotions.GREATER_MANA_HEAL, Items.FERMENTED_SPIDER_EYE, EPotions.MANA_BLOCKADE);
                    builder.registerPotionRecipe(EPotions.SUPER_MANA_HEAL, Items.FERMENTED_SPIDER_EYE, EPotions.MANA_BLOCKADE);
                    builder.registerPotionRecipe(EPotions.MANA_BLOCKADE, Items.REDSTONE, EPotions.LONG_MANA_BLOCKADE);
                    builder.registerPotionRecipe(EPotions.MANA_BLOCKADE, Items.GLOWSTONE_DUST, EPotions.STRONG_MANA_BLOCKADE);

                    builder.registerPotionRecipe(Potions.AWKWARD, Items.WARPED_FUNGUS, EPotions.MANA_REGENERATION);
                    builder.registerPotionRecipe(EPotions.MANA_REGENERATION, Items.REDSTONE, EPotions.LONG_MANA_REGENERATION);
                    builder.registerPotionRecipe(EPotions.MANA_REGENERATION, Items.GLOWSTONE_DUST, EPotions.STRONG_MANA_REGENERATION);

                    builder.registerPotionRecipe(EPotions.MANA_REGENERATION, Items.FERMENTED_SPIDER_EYE, EPotions.MAGIC_FATIGUE);
                    builder.registerPotionRecipe(EPotions.MAGIC_FATIGUE, Items.REDSTONE, EPotions.LONG_MAGIC_FATIGUE);
                    builder.registerPotionRecipe(EPotions.MAGIC_FATIGUE, Items.GLOWSTONE_DUST, EPotions.STRONG_MAGIC_FATIGUE);
                    builder.registerPotionRecipe(EPotions.LONG_MANA_REGENERATION, Items.FERMENTED_SPIDER_EYE, EPotions.LONG_MAGIC_FATIGUE);
                    builder.registerPotionRecipe(EPotions.STRONG_MANA_REGENERATION, Items.FERMENTED_SPIDER_EYE, EPotions.STRONG_MAGIC_FATIGUE);

                    builder.registerPotionRecipe(Potions.AWKWARD, Items.CRIMSON_FUNGUS, EPotions.MAGIC_POWER);
                    builder.registerPotionRecipe(EPotions.MAGIC_POWER, Items.REDSTONE, EPotions.LONG_MAGIC_POWER);
                    builder.registerPotionRecipe(EPotions.MANA_REGENERATION, Items.GLOWSTONE_DUST, EPotions.STRONG_MAGIC_POWER);

                    builder.registerPotionRecipe(EPotions.MAGIC_POWER, Items.FERMENTED_SPIDER_EYE, EPotions.MAGIC_INEPTITUDE);
                    builder.registerPotionRecipe(EPotions.MAGIC_INEPTITUDE, Items.REDSTONE, EPotions.LONG_MAGIC_INEPTITUDE);
                    builder.registerPotionRecipe(EPotions.MAGIC_INEPTITUDE, Items.GLOWSTONE_DUST, EPotions.STRONG_MAGIC_INEPTITUDE);
                    builder.registerPotionRecipe(EPotions.LONG_MAGIC_POWER, Items.FERMENTED_SPIDER_EYE, EPotions.LONG_MAGIC_INEPTITUDE);
                    builder.registerPotionRecipe(EPotions.STRONG_MAGIC_POWER, Items.FERMENTED_SPIDER_EYE, EPotions.STRONG_MAGIC_INEPTITUDE);

                    builder.registerPotionRecipe(Potions.AWKWARD, EItems.SHARD_OF_TRAVEL, EPotions.RECALL);
                    builder.registerPotionRecipe(EPotions.RECALL, Items.GLOWSTONE_DUST, EPotions.STRONG_RECALL);
                    builder.registerPotionRecipe(EPotions.RECALL, Items.FERMENTED_SPIDER_EYE, EPotions.RESURRECTION);
                    builder.registerPotionRecipe(EPotions.RESURRECTION, Items.GLOWSTONE_DUST, EPotions.STRONG_RESURRECTION);
                    builder.registerPotionRecipe(EPotions.STRONG_RECALL, Items.FERMENTED_SPIDER_EYE, EPotions.STRONG_RESURRECTION);

                    builder.registerPotionRecipe(Potions.THICK, EItems.FLAWLESS_AETHERIUM_CRYSTAL, EPotions.A_LITTLE_BIT_OF_EVERYTHING);

                    builder.registerPotionRecipe(Potions.WATER_BREATHING, Items.NAUTILUS_SHELL, EPotions.AQUATIC_SPELUNKER);
                    builder.registerPotionRecipe(EPotions.AQUATIC_SPELUNKER, Items.REDSTONE, EPotions.LONG_AQUATIC_SPELUNKER);
                    builder.registerPotionRecipe(Potions.LONG_WATER_BREATHING, Items.REDSTONE, EPotions.LONG_AQUATIC_SPELUNKER);
                }
        );
    }
}
