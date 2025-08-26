package dev.night.ecraft.recipe;

import dev.night.ecraft.component.ManaStarComponent;
import dev.night.ecraft.component.EComponents;
import dev.night.ecraft.item.EItems;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.serialization.CodecUtils;
import io.wispforest.owo.serialization.EndecRecipeSerializer;
import io.wispforest.owo.serialization.endec.MinecraftEndecs;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

public record ManaStarRecipe(Ingredient template, Ingredient base, Ingredient addition, ItemStack result) implements SmithingRecipe {
    public static final int MAX_MANA_STAR_MANA = 90;

    @Override
    public ItemStack craft(SmithingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack stack1 = input.base().copy();
        ItemStack stack2 = input.addition().copy();

        int mana1 = stack1.get(EComponents.MANA_STAR_COMPONENT).mana();
        int mana2 = stack2.get(EComponents.MANA_STAR_COMPONENT).mana();

        int newMana = Math.min(MAX_MANA_STAR_MANA, mana1 + mana2);

        ItemStack newManaStar =  stack1.copyComponentsToNewStack(EItems.MANA_STAR, 1);
        newManaStar.set(EComponents.MANA_STAR_COMPONENT, new ManaStarComponent(newMana));

        return newManaStar;
    }

    @Override
    public boolean matches(SmithingRecipeInput input, World world) {
        if (!(this.template.test(input.template()) && this.base.test(input.base()) && this.addition.test(input.addition()))) {
            return false;
        }

        ItemStack stack1 = input.base();
        ItemStack stack2 = input.addition();

        if (!stack1.contains(EComponents.MANA_STAR_COMPONENT) || !stack2.contains(EComponents.MANA_STAR_COMPONENT) || !stack1.isOf(EItems.MANA_STAR) || !stack2.isOf(EItems.MANA_STAR))
            return false;

        return stack1.get(EComponents.MANA_STAR_COMPONENT).mana() < MAX_MANA_STAR_MANA && stack2.get(EComponents.MANA_STAR_COMPONENT).mana() < MAX_MANA_STAR_MANA;
    }

    @Override
    public boolean testTemplate(ItemStack stack) {
        return this.template.test(stack);
    }

    @Override
    public boolean testBase(ItemStack stack) {
        return this.base.test(stack);
    }

    @Override
    public boolean testAddition(ItemStack stack) {
        return this.addition.test(stack);
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ERecipeSerializers.MANA_STAR_RECIPE;
    }

    public static class Serializer extends EndecRecipeSerializer<ManaStarRecipe> {
        private static final StructEndec<ManaStarRecipe> ENDEC = StructEndecBuilder.of(
                CodecUtils.toEndec(Ingredient.ALLOW_EMPTY_CODEC).fieldOf("template", recipe -> recipe.template),
                CodecUtils.toEndec(Ingredient.ALLOW_EMPTY_CODEC).fieldOf("base", recipe -> recipe.base),
                CodecUtils.toEndec(Ingredient.ALLOW_EMPTY_CODEC).fieldOf("addition", recipe -> recipe.addition),
                MinecraftEndecs.ITEM_STACK.fieldOf("result", recipe -> recipe.result),
                ManaStarRecipe::new
        );

        public Serializer() {
            super(ENDEC);
        }
    }
}
