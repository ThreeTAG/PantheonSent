package net.threetag.pantheonsent.compat.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.displays.crafting.DefaultCustomDisplay;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.client.screen.RestorationScreen;
import net.threetag.pantheonsent.item.PSItems;
import net.threetag.pantheonsent.item.crafting.PSRecipeSerializers;
import net.threetag.pantheonsent.item.crafting.RestorationRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Environment(EnvType.CLIENT)
public class PantheonSentREIClientPlugin implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new RestorationCategory());
        registry.addWorkstations(PantheonSentREIServerPlugin.RESTORATION, EntryStacks.of(PSBlocks.ARCHEOLOGY_TABLE.get()));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(RestorationRecipe.class, PSRecipeSerializers.RESTORATION_RECIPE_TYPE.get(), RestorationDisplay::new);

        EntryIngredient shard = EntryIngredient.of(EntryStacks.of(PSItems.ANCIENT_CLAY_SHARD.get()));
        for (Item item : BuiltInRegistries.ITEM) {
            if (item.builtInRegistryHolder().is(ItemTags.DECORATED_POT_SHERDS)) {
                List<EntryIngredient> input = new ArrayList<>();

                for (int i = 0; i < 4; i++)
                    input.add(shard);
                input.add(EntryIngredients.of(item));
                for (int i = 0; i < 4; i++)
                    input.add(shard);

                registry.add(new DefaultCustomDisplay(null, input, Collections.singletonList(EntryIngredients.of(item, 2))));
            }
        }
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerContainerClickArea(new Rectangle(102, 48, 22, 15), RestorationScreen.class, PantheonSentREIServerPlugin.RESTORATION);
    }

    @Override
    public void registerTransferHandlers(TransferHandlerRegistry registry) {

    }
}
