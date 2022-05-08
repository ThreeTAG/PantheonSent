package net.threetag.pantheonsent;

import dev.architectury.event.events.common.LifecycleEvent;
import net.minecraft.resources.ResourceLocation;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.block.entity.PSBlockEntityTypes;
import net.threetag.pantheonsent.entity.PSEntityTypes;
import net.threetag.pantheonsent.entity.PSVillagerProfessions;
import net.threetag.pantheonsent.item.PSItems;
import net.threetag.pantheonsent.world.PSStructureFeatures;
import net.threetag.pantheonsent.world.PSStructureProcessorTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PantheonSent {

    public static final String MOD_ID = "pantheonsent";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        PSBlocks.BLOCKS.register();
        PSBlockEntityTypes.BLOCK_ENTITIES.register();
        PSItems.ITEMS.register();
        PSEntityTypes.ENTITIES.register();
        PSVillagerProfessions.PROFESSIONS.register();
        PSStructureFeatures.STRUCTURES.register();
        PSStructureProcessorTypes.PROCESSOR_TYPES.register();

        LifecycleEvent.SETUP.register(PSStructureFeatures::registerStructureFeatures);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

}
