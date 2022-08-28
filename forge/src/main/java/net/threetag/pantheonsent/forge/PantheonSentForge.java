package net.threetag.pantheonsent.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.PantheonSentClient;
import net.threetag.pantheonsent.data.forge.*;
import top.theillusivec4.curios.api.SlotTypeMessage;

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
    public static void imcEnqueueEvent(InterModEnqueueEvent e) {
        InterModComms.sendTo("curios", "register_type", () -> new SlotTypeMessage.Builder("necklace").build());
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        BlockTagsProvider blockTags = new PSBlockTagsProvider(e.getGenerator(), e.getExistingFileHelper());
        e.getGenerator().addProvider(e.includeServer(), new PSRecipeProvider(e.getGenerator()));
        e.getGenerator().addProvider(e.includeClient(), new PSLangProvider.English(e.getGenerator()));
        e.getGenerator().addProvider(e.includeClient(), new PSLangProvider.German(e.getGenerator()));
        e.getGenerator().addProvider(e.includeClient(), new PSLangProvider.Saxon(e.getGenerator()));
        e.getGenerator().addProvider(e.includeClient(), new PSBlockStateProvider(e.getGenerator(), e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeClient(), new PSItemModelProvider(e.getGenerator(), e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeServer(), blockTags);
        e.getGenerator().addProvider(e.includeServer(), new PSItemTagsProvider(e.getGenerator(), blockTags, e.getExistingFileHelper()));
        e.getGenerator().addProvider(e.includeServer(), new PSBlockLootTableProvider(e.getGenerator()));
//        e.getGenerator().addProvider(new PSBiomeTagsProvider(e.getGenerator(), e.getExistingFileHelper()));
    }

}
