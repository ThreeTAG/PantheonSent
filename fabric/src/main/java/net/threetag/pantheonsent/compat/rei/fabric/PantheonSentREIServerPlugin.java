package net.threetag.pantheonsent.compat.rei.fabric;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import net.threetag.pantheonsent.PantheonSent;

public class PantheonSentREIServerPlugin implements REIServerPlugin {

    public static final CategoryIdentifier<RestorationDisplay> RESTORATION = CategoryIdentifier.of(PantheonSent.MOD_ID, "restoration");

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(RESTORATION, RestorationDisplay.serializer());
    }
}
