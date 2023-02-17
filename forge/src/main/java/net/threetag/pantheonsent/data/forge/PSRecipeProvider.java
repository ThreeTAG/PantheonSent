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
        ShapedRecipeBuilder.shaped(PSItems.BRUSH.get()).define('X', Items.STICK).define('S', Items.STRING).pattern("SSS").pattern(" X ").pattern(" X ").unlockedBy(getHasName(Items.STRING), has(Items.STRING)).save(consumer);

        // Restoration Recipes
        new UpgradeRecipeBuilder(PSRecipeSerializers.RESTORATION.get(), Ingredient.of(PSItems.BROKEN_KHONSHU_USHABTI.get()), Ingredient.of(PSItems.ANCIENT_CLAY_SHARD.get()), PSItems.KHONSHU_USHABTI.get()).unlocks("has_ushabti", has(PSItems.BROKEN_KHONSHU_USHABTI.get())).save(consumer, PantheonSent.id("khonshu_ushabti_restoration"));
        new UpgradeRecipeBuilder(PSRecipeSerializers.RESTORATION.get(), Ingredient.of(PSItems.BROKEN_LUNAR_TOTEM.get()), Ingredient.of(PSItems.ANCIENT_GOLD_SHARD.get()), PSItems.LUNAR_TOTEM.get()).unlocks("has_totem", has(PSItems.LUNAR_TOTEM.get())).save(consumer, PantheonSent.id("lunar_totem_restoration"));
        new UpgradeRecipeBuilder(PSRecipeSerializers.RESTORATION.get(), Ingredient.of(PSItems.BROKEN_EYE_OF_HORUS.get()), Ingredient.of(PSItems.ANCIENT_GOLD_SHARD.get()), PSItems.EYE_OF_HORUS.get()).unlocks("has_eye_of_horus", has(PSItems.BROKEN_EYE_OF_HORUS.get())).save(consumer, PantheonSent.id("eye_of_horus_restoration"));
        new UpgradeRecipeBuilder(PSRecipeSerializers.RESTORATION.get(), Ingredient.of(PSItems.BROKEN_SCARAB_COMPASS.get()), Ingredient.of(PSItems.ANCIENT_GOLD_SHARD.get()), PSItems.SCARAB_COMPASS.get()).unlocks("has_scarab_compass", has(PSItems.BROKEN_SCARAB_COMPASS.get())).save(consumer, PantheonSent.id("scarab_compass_restoration"));

        ShapedRecipeBuilder.shaped(PSItems.LUNAR_STONE.get()).define('#', PSItems.LUNAR_SHARD.get()).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(PSItems.LUNAR_SHARD.get()), has(PSItems.LUNAR_SHARD.get())).save(consumer);

        // Shards burning
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(PSItems.ANCIENT_CLAY_SHARD.get()), Items.BRICK, 0.1F, 100).unlockedBy("has_shard", has(PSItems.ANCIENT_CLAY_SHARD.get())).save(consumer, PantheonSent.id("blast_ancient_clay_shard"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(PSItems.ANCIENT_GOLD_SHARD.get()), Items.GOLD_NUGGET, 0.1F, 100).unlockedBy("has_shard", has(PSItems.ANCIENT_GOLD_SHARD.get())).save(consumer, PantheonSent.id("blast_ancient_gold_shard"));
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
