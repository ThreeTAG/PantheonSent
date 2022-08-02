package net.threetag.pantheonsent.compat.jei.forge;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.config.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.item.crafting.RestorationRecipe;

public class RestorationRecipeCategory implements IRecipeCategory<RestorationRecipe> {

    private final IDrawable background;
    private final IDrawable icon;

    public RestorationRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(Constants.RECIPE_GUI_VANILLA, 0, 168, 125, 18);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(PSBlocks.ARCHEOLOGY_TABLE.get()));
    }

    @SuppressWarnings("removal")
    @Override
    public ResourceLocation getUid() {
        return getRecipeType().getUid();
    }

    @SuppressWarnings("removal")
    @Override
    public Class<? extends RestorationRecipe> getRecipeClass() {
        return getRecipeType().getRecipeClass();
    }

    @Override
    public RecipeType<RestorationRecipe> getRecipeType() {
        return PantheonSentJEIPlugin.RESTORATION;
    }

    @Override
    public Component getTitle() {
        return PSBlocks.ARCHEOLOGY_TABLE.get().getName();
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RestorationRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
                .addIngredients(recipe.base);

        builder.addSlot(RecipeIngredientRole.INPUT, 50, 1)
                .addIngredients(recipe.addition);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 1)
                .addItemStack(recipe.getResultItem());
    }

    @Override
    public boolean isHandled(RestorationRecipe recipe) {
        return !recipe.isSpecial();
    }
}
