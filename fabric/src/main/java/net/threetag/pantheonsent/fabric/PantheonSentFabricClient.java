package net.threetag.pantheonsent.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.threetag.pantheonsent.PantheonSentClient;

public class PantheonSentFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        PantheonSentClient.init();
    }

}
