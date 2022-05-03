package net.threetag.pantheonsent;

import dev.architectury.event.events.common.LifecycleEvent;
import net.minecraft.resources.ResourceLocation;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.block.entity.PSBlockEntityTypes;
import net.threetag.pantheonsent.entity.PSEntityTypes;
import net.threetag.pantheonsent.item.PSItems;
import net.threetag.pantheonsent.world.PSStructures;
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
        PSStructures.STRUCTURES.register();

        LifecycleEvent.SETUP.register(PSStructures::registerStructureFeatures);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

}
