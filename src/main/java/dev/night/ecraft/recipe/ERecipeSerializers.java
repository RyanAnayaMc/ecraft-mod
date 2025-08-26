package dev.night.ecraft.recipe;

import dev.night.ecraft.Ecraft;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ERecipeSerializers {
    public static final RecipeSerializer<ManaStarRecipe> MANA_STAR_RECIPE = new ManaStarRecipe.Serializer();

    public static void initialize() {
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(Ecraft.MOD_ID, "mana_star"), MANA_STAR_RECIPE);
    }
}
