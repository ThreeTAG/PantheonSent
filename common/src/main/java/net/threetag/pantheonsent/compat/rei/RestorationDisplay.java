package net.threetag.pantheonsent.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.resources.ResourceLocation;
import net.threetag.pantheonsent.item.crafting.RestorationRecipe;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RestorationDisplay extends BasicDisplay {

    public RestorationDisplay(RestorationRecipe recipe) {
        this(
                Arrays.asList(
                        EntryIngredients.ofIngredient(recipe.base),
                        EntryIngredients.ofIngredient(recipe.addition)
                ),
                Collections.singletonList(EntryIngredients.of(recipe.getResultItem())),
                Optional.ofNullable(recipe.getId())
        );
    }

    public RestorationDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location) {
        super(inputs, outputs, location);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return PantheonSentREIServerPlugin.RESTORATION;
    }

    public static BasicDisplay.Serializer<RestorationDisplay> serializer() {
        return BasicDisplay.Serializer.ofSimple(RestorationDisplay::new);
    }
}
