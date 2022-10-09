package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
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

        // Shards burning
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(PSItems.ANCIENT_CLAY_SHARD.get()), Items.BRICK, 0.1F, 100).unlockedBy("has_shard", has(PSItems.ANCIENT_CLAY_SHARD.get())).save(consumer, PantheonSent.id("blast_ancient_clay_shard"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(PSItems.ANCIENT_GOLD_SHARD.get()), Items.GOLD_NUGGET, 0.1F, 100).unlockedBy("has_shard", has(PSItems.ANCIENT_GOLD_SHARD.get())).save(consumer, PantheonSent.id("blast_ancient_gold_shard"));
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
