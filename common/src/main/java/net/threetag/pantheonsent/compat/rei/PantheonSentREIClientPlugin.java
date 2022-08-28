package net.threetag.pantheonsent.compat.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.client.screen.RestorationScreen;
import net.threetag.pantheonsent.item.crafting.PSRecipeSerializers;
import net.threetag.pantheonsent.item.crafting.RestorationRecipe;

@Environment(EnvType.CLIENT)
public class PantheonSentREIClientPlugin implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new RestorationCategory());
        registry.addWorkstations(PantheonSentREIServerPlugin.RESTORATION, EntryStacks.of(PSBlocks.ARCHEOLOGY_TABLE.get()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(RestorationRecipe.class, PSRecipeSerializers.RESTORATION_RECIPE_TYPE.get(), RestorationDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerContainerClickArea(new Rectangle(102, 48, 22, 15), RestorationScreen.class, PantheonSentREIServerPlugin.RESTORATION);
    }

    @Override
    public void registerTransferHandlers(TransferHandlerRegistry registry) {

    }
}
