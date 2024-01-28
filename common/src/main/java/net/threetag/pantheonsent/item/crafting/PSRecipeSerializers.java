package net.threetag.pantheonsent.item.crafting;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;

public class PSRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(PantheonSent.MOD_ID, Registries.RECIPE_SERIALIZER);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(PantheonSent.MOD_ID, Registries.RECIPE_TYPE);

    public static final RegistrySupplier<RecipeSerializer<RestorationRecipe>> RESTORATION = RECIPE_SERIALIZERS.register("restoration", RestorationRecipe.Serializer::new);
    public static final RegistrySupplier<RecipeType<RestorationRecipe>> RESTORATION_RECIPE_TYPE = RECIPE_TYPES.register("restoration", () -> new RecipeType<>() {
        public String toString() {
            return "pantheonsent:restoration";
        }
    });
}
