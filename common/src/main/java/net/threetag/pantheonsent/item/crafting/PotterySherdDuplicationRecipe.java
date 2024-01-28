package net.threetag.pantheonsent.item.crafting;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.threetag.pantheonsent.item.PSItems;
import org.jetbrains.annotations.NotNull;

public class PotterySherdDuplicationRecipe extends CustomRecipe {

    public PotterySherdDuplicationRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        int sherd = 0;
        int shards = 0;

        for (ItemStack item : container.getItems()) {
            if (item.is(ItemTags.DECORATED_POT_SHERDS)) {
                sherd++;
            } else if (item.getItem() == PSItems.ANCIENT_CLAY_SHARD.get()) {
                shards++;
            }
        }

        return sherd == 1 && shards == 8;
    }

    @Override
    public @NotNull ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        for (ItemStack item : container.getItems()) {
            if (item.is(ItemTags.DECORATED_POT_SHERDS)) {
                return new ItemStack(item.getItem(), 2);
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 9;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return PSRecipeSerializers.POTTERY_SHERD_DUPLICATION.get();
    }
}
