package net.threetag.pantheonsent.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.common.Constants;
import mezz.jei.library.util.RecipeUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.item.crafting.RestorationRecipe;
import org.jetbrains.annotations.NotNull;

public class RestorationRecipeCategory implements IRecipeCategory<RestorationRecipe> {

    private final IDrawable background;
    private final IDrawable icon;

    public RestorationRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(Constants.RECIPE_GUI_VANILLA, 0, 168, 125, 18);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(PSBlocks.ARCHEOLOGY_TABLE.get()));
    }

    @Override
    public @NotNull RecipeType<RestorationRecipe> getRecipeType() {
        return PantheonSentJEIPlugin.RESTORATION;
    }

    @Override
    public @NotNull Component getTitle() {
        return PSBlocks.ARCHEOLOGY_TABLE.get().getName();
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RestorationRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
                .addIngredients(recipe.base);

        builder.addSlot(RecipeIngredientRole.INPUT, 50, 1)
                .addIngredients(recipe.addition);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 1)
                .addItemStack(RecipeUtil.getResultItem(recipe));
    }

    @Override
    public boolean isHandled(RestorationRecipe recipe) {
        return !recipe.isSpecial();
    }
}
