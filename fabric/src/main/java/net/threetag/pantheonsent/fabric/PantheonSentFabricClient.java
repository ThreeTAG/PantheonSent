package net.threetag.pantheonsent.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.threetag.pantheonsent.PantheonSentClient;
import net.threetag.pantheonsent.client.renderer.entity.CrescentDartRenderer;
import net.threetag.pantheonsent.entity.PSEntityTypes;

public class PantheonSentFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        PantheonSentClient.init();
        EntityRendererRegistry.register(PSEntityTypes.CRESCENT_DART.get(), CrescentDartRenderer::new);
    }
}
