package net.threetag.pantheonsent.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.PantheonSentClient;
import net.threetag.pantheonsent.data.forge.*;

@Mod(PantheonSent.MOD_ID)
@Mod.EventBusSubscriber(modid = PantheonSent.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PantheonSentForge {

    public PantheonSentForge() {
        EventBuses.registerModEventBus(PantheonSent.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        PantheonSent.init();
    }

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent e) {

    }

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent e) {
        PantheonSentClient.init();
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        e.getGenerator().addProvider(new PantheonSentLangProvider.English(e.getGenerator()));
        e.getGenerator().addProvider(new PantheonSentLangProvider.German(e.getGenerator()));
        e.getGenerator().addProvider(new PantheonSentLangProvider.Saxon(e.getGenerator()));
    }
}
