package net.threetag.pantheonsent.compat.jei;

import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.item.PSItems;

import java.util.List;

public class PotterySherdDuplicationRecipeMaker {

    private static final String group = "pottery_sherd_duplication";

    @SuppressWarnings("deprecation")
    public static List<CraftingRecipe> createRecipes() {
        return BuiltInRegistries.ITEM.stream()
                .filter(item -> item.builtInRegistryHolder().is(ItemTags.DECORATED_POT_SHERDS))
                .map(PotterySherdDuplicationRecipeMaker::createRecipe)
                .toList();
    }

    private static CraftingRecipe createRecipe(Item sherd) {
        NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY,
                Ingredient.of(PSItems.ANCIENT_CLAY_SHARD.get()),
                Ingredient.of(PSItems.ANCIENT_CLAY_SHARD.get()),
                Ingredient.of(PSItems.ANCIENT_CLAY_SHARD.get()),
                Ingredient.of(PSItems.ANCIENT_CLAY_SHARD.get()),
                Ingredient.of(sherd),
                Ingredient.of(PSItems.ANCIENT_CLAY_SHARD.get()),
                Ingredient.of(PSItems.ANCIENT_CLAY_SHARD.get()),
                Ingredient.of(PSItems.ANCIENT_CLAY_SHARD.get()),
                Ingredient.of(PSItems.ANCIENT_CLAY_SHARD.get()));

        ItemStack output = new ItemStack(sherd, 2);
        ResourceLocation id = PantheonSent.id(group + "." + output.getDescriptionId());

        return new ShapelessRecipe(id, group, CraftingBookCategory.MISC, output, inputs);
    }

}
