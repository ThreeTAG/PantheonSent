package net.threetag.pantheonsent.fabric;

import net.fabricmc.api.ModInitializer;
import net.threetag.pantheonsent.PantheonSent;

public class PantheonSentFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        PantheonSent.init();
    }
}
