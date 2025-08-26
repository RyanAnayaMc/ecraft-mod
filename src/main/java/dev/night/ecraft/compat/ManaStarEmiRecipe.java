package dev.night.ecraft.compat;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.night.ecraft.Ecraft;
import dev.night.ecraft.component.EComponents;
import dev.night.ecraft.component.ManaStarComponent;
import dev.night.ecraft.recipe.ManaStarRecipe;
import dev.night.ecraft.tags.ETags;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class ManaStarEmiRecipe implements EmiRecipe {
    Ingredient template;
    Ingredient base;
    Ingredient addition;
    List<EmiIngredient> inputs;
    EmiStack outputs;
    Identifier id;

    public ManaStarEmiRecipe(ManaStarRecipe recipe) {
        this.template = recipe.template();
        this.base = recipe.base();
        this.addition = recipe.addition();

        if (this.template != null && this.base != null && this.addition != null) {
            ItemStack inputStack = Arrays.stream(this.base.getMatchingStacks()).findFirst().orElse(ItemStack.EMPTY).copy();
            ItemStack additionStack = Arrays.stream(this.addition.getMatchingStacks()).findFirst().orElse(ItemStack.EMPTY).copy();

            inputStack.set(EComponents.MANA_STAR_COMPONENT, new ManaStarComponent((byte) 10));
            additionStack.set(EComponents.MANA_STAR_COMPONENT, new ManaStarComponent((byte) 10));

            this.inputs = List.of(
                    EmiIngredient.of(this.template),
                    EmiStack.of(inputStack),
                    EmiStack.of(additionStack)
            );

            ItemStack outputStack = recipe.result();

            if (outputStack != null && inputStack.isIn(ETags.Items.MANA_STAR) && additionStack.isIn(ETags.Items.MANA_STAR)) {
                outputStack.set(EComponents.MANA_STAR_COMPONENT, new ManaStarComponent((byte) 20));
                outputs = EmiStack.of(outputStack);
                id = Identifier.of(Ecraft.MOD_ID, "smithing/combine_mana_stars");
            }
        }
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return VanillaEmiRecipeCategories.SMITHING;
    }

    @Override
    public @Nullable Identifier getId() {
        return this.id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return this.inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(this.outputs);
    }

    @Override
    public int getDisplayWidth() {
        return 112;
    }

    @Override
    public int getDisplayHeight() {
        return 18;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(EmiTexture.EMPTY_ARROW, 62, 1);
        widgets.addSlot(inputs.get(0), 0, 0);
        widgets.addSlot(inputs.get(1), 18, 0);
        widgets.addSlot(inputs.get(2), 36, 0);
        widgets.addSlot(outputs, 94, 0).recipeContext(this);
    }
}
