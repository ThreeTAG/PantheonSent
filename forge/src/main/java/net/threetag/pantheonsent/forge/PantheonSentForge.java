package net.threetag.pantheonsent.forge;

import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.threetag.palladiumcore.forge.PalladiumCoreForge;
import net.threetag.palladiumcore.util.Platform;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.PantheonSentClient;
import net.threetag.pantheonsent.data.forge.*;

@Mod(PantheonSent.MOD_ID)
@Mod.EventBusSubscriber(modid = PantheonSent.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PantheonSentForge {

    public PantheonSentForge() {
        PalladiumCoreForge.registerModEventBus(PantheonSent.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        PantheonSent.init();

        if (Platform.isClient()) {
            PantheonSentClient.init();
        }
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        var output = e.getGenerator().getPackOutput();

        e.getGenerator().addProvider(e.includeClient(), new PSLangProvider.English(output));
        e.getGenerator().addProvider(e.includeClient(), new PSLangProvider.German(output));
        e.getGenerator().addProvider(e.includeClient(), new PSLangProvider.Saxon(output));
        e.getGenerator().addProvider(e.includeClient(), new PSBlockStateProvider(output, e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeClient(), new PSItemModelProvider(output, e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeClient(), new PSSoundDefinitionsProvider(output, e.getExistingFileHelper()));

        e.getGenerator().addProvider(e.includeServer(), new PSRecipeProvider(output));
        e.getGenerator().addProvider(e.includeServer(), new PSLootTableProvider(output));
        e.getGenerator().addProvider(e.includeServer(), new PSBlockTagsProvider(output, e.getLookupProvider(), e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeServer(), new PSItemTagsProvider(output, e.getLookupProvider(), e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeServer(), new PSPoiTypeTagsProvider(output, e.getLookupProvider(), e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeServer(), new PSBiomeTagsProvider(output, e.getLookupProvider(), e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeServer(), new PSBannerPatternTagsProvider(output, e.getLookupProvider(), e.getExistingFileHelper()));
        new PSSherdsProvider(e);
    }

}
