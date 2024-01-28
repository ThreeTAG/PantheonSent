package net.threetag.pantheonsent.item.crafting;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.threetag.pantheonsent.block.PSBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class RestorationRecipe implements Recipe<Container> {

    public final Ingredient base;
    public final Ingredient addition;
    public final ItemStack result;
    private final ResourceLocation id;

    public RestorationRecipe(ResourceLocation resourceLocation, Ingredient ingredient, Ingredient ingredient2, ItemStack itemStack) {
        this.id = resourceLocation;
        this.base = ingredient;
        this.addition = ingredient2;
        this.result = itemStack;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return this.base.test(container.getItem(0)) && this.addition.test(container.getItem(1));
    }

    @Override
    public @NotNull ItemStack assemble(Container container, RegistryAccess registryAccess) {
        ItemStack itemStack = this.result.copy();
        CompoundTag compoundTag = container.getItem(0).getTag();
        if (compoundTag != null) {
            itemStack.setTag(compoundTag.copy());
        }

        return itemStack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    public boolean isAdditionIngredient(ItemStack addition) {
        return this.addition.test(addition);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(PSBlocks.ARCHEOLOGY_TABLE.get());
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return PSRecipeSerializers.RESTORATION.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return PSRecipeSerializers.RESTORATION_RECIPE_TYPE.get();
    }

    @Override
    public boolean isIncomplete() {
        return Stream.of(this.base, this.addition).anyMatch((ingredient) -> ingredient.getItems().length == 0);
    }

    public static class Serializer implements RecipeSerializer<RestorationRecipe> {

        @Override
        public @NotNull RestorationRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "base"));
            Ingredient ingredient2 = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "addition"));
            ItemStack itemStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            return new RestorationRecipe(recipeId, ingredient, ingredient2, itemStack);
        }

        @Override
        public @NotNull RestorationRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            Ingredient ingredient2 = Ingredient.fromNetwork(buffer);
            ItemStack itemStack = buffer.readItem();
            return new RestorationRecipe(recipeId, ingredient, ingredient2, itemStack);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, RestorationRecipe recipe) {
            recipe.base.toNetwork(buffer);
            recipe.addition.toNetwork(buffer);
            buffer.writeItem(recipe.result);
        }
    }
}
