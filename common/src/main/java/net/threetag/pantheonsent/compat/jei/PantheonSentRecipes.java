package net.threetag.pantheonsent.compat.jei;

import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.library.plugins.vanilla.crafting.CategoryRecipeValidator;
import mezz.jei.common.util.ErrorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.threetag.pantheonsent.item.crafting.PSRecipeSerializers;
import net.threetag.pantheonsent.item.crafting.RestorationRecipe;

import java.util.List;

public class PantheonSentRecipes {

    private final RecipeManager recipeManager;
    private final IIngredientManager ingredientManager;

    public PantheonSentRecipes(IIngredientManager ingredientManager) {
        Minecraft minecraft = Minecraft.getInstance();
        ErrorUtil.checkNotNull(minecraft, "minecraft");
        ClientLevel world = minecraft.level;
        ErrorUtil.checkNotNull(world, "minecraft world");
        this.recipeManager = world.getRecipeManager();
        this.ingredientManager = ingredientManager;
    }

    public List<RestorationRecipe> getRestorationRecipes(IRecipeCategory<RestorationRecipe> category) {
        CategoryRecipeValidator<RestorationRecipe> validator = new CategoryRecipeValidator<>(category, this.ingredientManager, 0);
        return getValidHandledRecipes(this.recipeManager, PSRecipeSerializers.RESTORATION_RECIPE_TYPE.get(), validator);
    }

    private static <C extends Container, T extends Recipe<C>> List<T> getValidHandledRecipes(
            RecipeManager recipeManager,
            RecipeType<T> recipeType,
            CategoryRecipeValidator<T> validator
    ) {
        return recipeManager.getAllRecipesFor(recipeType)
                .stream()
                .filter(r -> validator.isRecipeValid(r) && validator.isRecipeHandled(r))
                .toList();
    }

}
