package net.threetag.pantheonsent.forge;

import com.mojang.blaze3d.shaders.FogShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.client.PSClientEventHandler;

@Mod.EventBusSubscriber(modid = PantheonSent.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PSForgeEventHandler {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void event(ViewportEvent.RenderFog e) {
        float darkness = PSClientEventHandler.getDarkness(e.getPartialTick());

        if(darkness > 0F) {
            var far = 3F - e.getFarPlaneDistance();
            var near = 1F - e.getNearPlaneDistance();
            e.setFogShape(FogShape.SPHERE);
            e.setFarPlaneDistance(e.getFarPlaneDistance() + far * darkness);
            e.setNearPlaneDistance(e.getNearPlaneDistance() + near * darkness);
            e.setCanceled(true);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void event(ViewportEvent.ComputeFogColor e) {
        float darkness = PSClientEventHandler.getDarkness(e.getPartialTick());

        if(darkness > 0F) {
            darkness = 1F - darkness;
            e.setBlue(e.getBlue() * darkness);
            e.setGreen(e.getGreen() * darkness);
            e.setRed(e.getRed() * darkness);
        }
    }

}
