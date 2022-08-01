package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.item.PSItems;
import net.threetag.pantheonsent.item.crafting.PSRecipeSerializers;

import java.util.function.Consumer;

public class PSRecipeProvider extends RecipeProvider {

    public PSRecipeProvider(DataGenerator arg) {
        super(arg);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // test recipe
        new UpgradeRecipeBuilder(PSRecipeSerializers.RESTORATION.get(), Ingredient.of(Items.BONE), Ingredient.of(Items.GOLD_NUGGET), PSItems.EYE_OF_HORUS.get()).unlocks("has_bone", has(Items.BONE)).save(consumer, PantheonSent.id("eye_of_horus_restoration"));
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
