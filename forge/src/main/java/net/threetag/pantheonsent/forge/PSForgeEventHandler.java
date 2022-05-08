package net.threetag.pantheonsent.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.entity.PSBlockEntityTypes;
import net.threetag.pantheonsent.client.model.CrescentDartModel;
import net.threetag.pantheonsent.client.renderer.blockentity.BrushableBlockEntityRenderer;
import net.threetag.pantheonsent.client.renderer.entity.CrescentDartRenderer;
import net.threetag.pantheonsent.entity.PSEntityTypes;

@Mod.EventBusSubscriber(modid = PantheonSent.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PSForgeEventHandler {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onEntityRendererRegister(EntityRenderersEvent.RegisterRenderers e) {
        e.registerEntityRenderer(PSEntityTypes.CRESCENT_DART.get(), CrescentDartRenderer::new);
        e.registerBlockEntityRenderer(PSBlockEntityTypes.BRUSHABLE.get(), BrushableBlockEntityRenderer::new);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void event(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(CrescentDartModel.MODEL_LAYER, CrescentDartModel::createLayer);
    }

}
