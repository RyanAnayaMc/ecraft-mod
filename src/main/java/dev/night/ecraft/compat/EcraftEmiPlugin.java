package dev.night.ecraft.compat;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.night.ecraft.recipe.ManaStarRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmithingRecipe;

public class EcraftEmiPlugin implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        for (RecipeEntry<SmithingRecipe> recipe : registry.getRecipeManager().listAllOfType(RecipeType.SMITHING)) {
            if (recipe.value() instanceof ManaStarRecipe manaStarRecipe)
                registry.addRecipe(new ManaStarEmiRecipe(manaStarRecipe));
        }
    }
}
