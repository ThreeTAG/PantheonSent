package net.threetag.pantheonsent.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import mezz.jei.common.util.ErrorUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.client.screen.RestorationScreen;
import net.threetag.pantheonsent.inventory.PSMenuTypes;
import net.threetag.pantheonsent.inventory.RestorationMenu;
import net.threetag.pantheonsent.item.crafting.RestorationRecipe;

@JeiPlugin
public class PantheonSentJEIPlugin implements IModPlugin {

    public static final RecipeType<RestorationRecipe> RESTORATION =
            RecipeType.create(PantheonSent.MOD_ID, "restoration", RestorationRecipe.class);

    private RestorationRecipeCategory restorationCategory;

    @Override
    public ResourceLocation getPluginUid() {
        return PantheonSent.id("plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(restorationCategory = new RestorationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ErrorUtil.checkNotNull(this.restorationCategory, "restorationCategory");
        PantheonSentRecipes recipes = new PantheonSentRecipes();
        registration.addRecipes(RESTORATION, recipes.getRestorationRecipes(this.restorationCategory));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(RestorationScreen.class, 102, 48, 22, 15, RESTORATION);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(RestorationMenu.class, PSMenuTypes.RESTORATION.get(), RESTORATION, 0, 2, 3, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(PSBlocks.ARCHEOLOGY_TABLE.get()), RESTORATION);
    }
}
