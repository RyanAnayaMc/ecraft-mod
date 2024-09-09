package dev.night.ecraft.item;

import com.google.common.base.Suppliers;
import dev.night.ecraft.tags.ModBlockTags;
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

public enum ModToolMaterials implements ToolMaterial {
    CINCINNASITE_DIAMOND(2061, 8.2F, 6F, 25, BlockTags.INCORRECT_FOR_DIAMOND_TOOL, EntityAttributes.GENERIC_ATTACK_SPEED, 0F, () -> Ingredient.ofItems(ModItems.CINCINNASITE_DIAMOND_INGOT)),
    TERRASTEEL(2740, 17F, 7F, 28, TagKey.of(RegistryKeys.BLOCK, Identifier.of("mythicmetals", "incorrect_for_unobtainium_alloy_tools")), EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2f, () -> Ingredient.ofItems(ModItems.TERRASTEEL_INGOT)),
    COPPER(420, 4f, 2f, 14, ModBlockTags.INCORRECT_FOR_COPPER_TOOL, EntityAttributes.GENERIC_ATTACK_SPEED, 0F, () -> Ingredient.ofItems(Items.COPPER_INGOT));


    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final TagKey<Block> inverseTag;
    private final Supplier<Ingredient> repairIngredient;
    private final RegistryEntry<EntityAttribute> customAttribute;
    private final float customAttributeValue;

    private ModToolMaterials (int itemDurability, float miningSpeed, float attackDamage, int enchantability, TagKey<Block> inverseTag, RegistryEntry<EntityAttribute> customAttribute, float customAttributeValue, Supplier<Ingredient> repairIngredient) {
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
