package dev.night.ecraft.item;

import com.google.common.base.Suppliers;
import dev.night.ecraft.tags.ETags;
import net.minecraft.block.Block;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public enum EToolMaterials implements ToolMaterial {
    COPPER(420, 4f, 2f, 14, ETags.Blocks.INCORRECT_FOR_COPPER_TOOL, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    TUNGSTEN(560, 3.5f, 2f, 8, BlockTags.INCORRECT_FOR_IRON_TOOL, () -> Ingredient.ofItems(EItems.TUNGSTEN_INGOT)),
    DEMONITE(750, 8f, 2.5f, 18, BlockTags.INCORRECT_FOR_DIAMOND_TOOL, () -> Ingredient.ofItems(EItems.DEMONITE_INGOT)),
    HELLSTONE(1750, 9f, 3.5f, 12, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.ofItems(EItems.HELLSTONE_INGOT)),
    CINCINNASITE_DIAMOND(2061, 8.2F, 4F, 25, BlockTags.INCORRECT_FOR_DIAMOND_TOOL, () -> Ingredient.ofItems(EItems.CINCINNASITE_DIAMOND_INGOT)),
    COBALT(2050, 10f, 4f, 15, ETags.Blocks.INCORRECT_FOR_COBALT_TOOL, () -> Ingredient.ofItems(EItems.COBALT_INGOT)),
    ORICHALCUM(2250, 10.5f, 4.25f, 18, ETags.Blocks.INCORRECT_FOR_ORICHALCUM_TOOL, () -> Ingredient.ofItems(EItems.ORICHALCUM_INGOT)),
    TITANIUM(2500, 11f, 4.75f, 21, ETags.Blocks.INCORRECT_FOR_TITANIUM_TOOL, () -> Ingredient.ofItems(EItems.TITANIUM_INGOT)),
    HALLOWED(3000, 12f, 5f, 25, ETags.Blocks.INCORRECT_FOR_HALLOWED_TOOL, () -> Ingredient.ofItems(EItems.HALLOWED_INGOT)),
    VERDANT(3500, 13f, 5.5f, 30, ETags.Blocks.INCORRECT_FOR_VERDANT_TOOL, () -> Ingredient.ofItems(EItems.VERDANT_INGOT)),
    DETONIUM(3750, 14f, 6f, 28, ETags.Blocks.INCORRECT_FOR_DETONIUM_TOOL, () -> Ingredient.ofItems(EItems.DETONIUM_INGOT)),
    REFINED_AETHERIUM(4250, 15f, 6.5f, 35, ETags.Blocks.INCORRECT_FOR_AETHERIUM_TOOL, () -> Ingredient.ofItems(EItems.REFINED_AETHERIUM_CRYSTAL)),
    FLAWLESS_AETHERIUM(Integer.MAX_VALUE, 16f, 7f, 40, ETags.Blocks.INCORRECT_FOR_AETHERIUM_TOOL, () -> Ingredient.ofItems(EItems.FLAWLESS_AETHERIUM_CRYSTAL)),
    TERRASTEEL(2740, 17F, 7F, 28, ETags.Blocks.INCORRECT_FOR_AETHERIUM_TOOL, EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2f, () -> Ingredient.ofItems(EItems.TERRASTEEL_INGOT));

    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final TagKey<Block> inverseTag;
    private final Supplier<Ingredient> repairIngredient;
    private final RegistryEntry<EntityAttribute> customAttribute;
    private final float customAttributeValue;

    EToolMaterials(int itemDurability, float miningSpeed, float attackDamage, int enchantability, TagKey<Block> inverseTag, Supplier<Ingredient> repairIngredient) {
        this(itemDurability, miningSpeed, attackDamage, enchantability, inverseTag, EntityAttributes.GENERIC_ATTACK_SPEED, 0f, repairIngredient);
    }

    EToolMaterials(int itemDurability, float miningSpeed, float attackDamage, int enchantability, TagKey<Block> inverseTag, RegistryEntry<EntityAttribute> customAttribute, float customAttributeValue, Supplier<Ingredient> repairIngredient) {
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.customAttribute = customAttribute;
        this.inverseTag = inverseTag;
        this.customAttributeValue = customAttributeValue;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getDurability () {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier () {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage () {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability () {
        return this.enchantability;
    }

    public RegistryEntry<EntityAttribute> getCustomAttribute () {
        return this.customAttribute;
    }

    public float getCustomAttributeValue () {
        return this.customAttributeValue;
    }

    @Override
    public Ingredient getRepairIngredient () {
        return this.repairIngredient.get();
    }


    /*
    public static final ToolMaterial CINCINNASITE_DIAMOND = new CincinnasiteDiamondMaterial();


    private static class CincinnasiteDiamondMaterial implements ToolMaterial {

        @Override
        public int getDurability() {
            return 2061;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 8.2F;
        }

        @Override
        public float getAttackDamage() {
            return 3.7F;
        }

        @Override
        public TagKey<Block> getInverseTag() {
            return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
        }

        @Override
        public int getEnchantability() {
            return 14;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(ModItems.CINCINNASITE_DIAMOND_INGOT);
        }
    }*/
}
