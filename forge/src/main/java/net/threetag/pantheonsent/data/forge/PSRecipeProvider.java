package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.item.PSItems;
import net.threetag.pantheonsent.item.crafting.RestorationRecipeBuilder;

import java.util.function.Consumer;

public class PSRecipeProvider extends RecipeProvider {

    public PSRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // Blocks
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PSItems.ARCHEOLOGY_TABLE.get()).pattern("BX").pattern("PP").define('B', Items.BRUSH).define('X', Items.BOOK).define('P', ItemTags.PLANKS).unlockedBy(getHasName(Items.BRUSH), has(Items.BRUSH)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, PSItems.GILDED_SANDSTONE.get()).requires(Items.SANDSTONE).requires(Items.SANDSTONE).requires(Items.SANDSTONE).requires(Items.GOLD_NUGGET).unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, PSItems.GILDED_SANDSTONE_PILLAR.get(), 2).pattern("S").pattern("S").define('S', PSItems.GILDED_SANDSTONE.get()).unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT)).save(consumer);

        // Restoration Recipes
        new RestorationRecipeBuilder(PSItems.KHONSHU_USHABTI.get(), RecipeCategory.TOOLS, Ingredient.of(PSItems.BROKEN_KHONSHU_USHABTI.get()), Ingredient.of(PSItems.ANCIENT_CLAY_SHARD.get())).unlocks("has_ushabti", has(PSItems.BROKEN_KHONSHU_USHABTI.get())).save(consumer, PantheonSent.id("khonshu_ushabti_restoration"));
        new RestorationRecipeBuilder(PSItems.LUNAR_TOTEM.get(), RecipeCategory.TOOLS, Ingredient.of(PSItems.BROKEN_LUNAR_TOTEM.get()), Ingredient.of(PSItems.ANCIENT_GOLD_SHARD.get())).unlocks("has_totem", has(PSItems.LUNAR_TOTEM.get())).save(consumer, PantheonSent.id("lunar_totem_restoration"));
        new RestorationRecipeBuilder(PSItems.EYE_OF_HORUS.get(), RecipeCategory.TOOLS, Ingredient.of(PSItems.BROKEN_EYE_OF_HORUS.get()), Ingredient.of(PSItems.ANCIENT_GOLD_SHARD.get())).unlocks("has_eye_of_horus", has(PSItems.BROKEN_EYE_OF_HORUS.get())).save(consumer, PantheonSent.id("eye_of_horus_restoration"));
        new RestorationRecipeBuilder(PSItems.SCARAB_COMPASS.get(), RecipeCategory.TOOLS, Ingredient.of(PSItems.BROKEN_SCARAB_COMPASS.get()), Ingredient.of(PSItems.ANCIENT_GOLD_SHARD.get())).unlocks("has_scarab_compass", has(PSItems.BROKEN_SCARAB_COMPASS.get())).save(consumer, PantheonSent.id("scarab_compass_restoration"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PSItems.LUNAR_STONE.get()).define('#', PSItems.LUNAR_SHARD.get()).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(PSItems.LUNAR_SHARD.get()), has(PSItems.LUNAR_SHARD.get())).save(consumer);

        // Shards burning
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(PSItems.ANCIENT_CLAY_SHARD.get()), RecipeCategory.MISC, Items.BRICK, 0.1F, 100).unlockedBy("has_shard", has(PSItems.ANCIENT_CLAY_SHARD.get())).save(consumer, PantheonSent.id("blast_ancient_clay_shard"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(PSItems.ANCIENT_GOLD_SHARD.get()), RecipeCategory.MISC, Items.GOLD_NUGGET, 0.1F, 100).unlockedBy("has_shard", has(PSItems.ANCIENT_GOLD_SHARD.get())).save(consumer, PantheonSent.id("blast_ancient_gold_shard"));
    }
}
