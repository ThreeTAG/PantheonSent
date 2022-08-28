package net.threetag.pantheonsent;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.platform.Platform;
import net.minecraft.resources.ResourceLocation;
import net.threetag.pantheonsent.ability.PSAbilities;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.block.entity.PSBlockEntityTypes;
import net.threetag.pantheonsent.entity.PSEntityTypes;
import net.threetag.pantheonsent.entity.PSPoiTypes;
import net.threetag.pantheonsent.entity.PSVillagerProfessions;
import net.threetag.pantheonsent.inventory.PSMenuTypes;
import net.threetag.pantheonsent.item.PSItems;
import net.threetag.pantheonsent.item.crafting.PSRecipeSerializers;
import net.threetag.pantheonsent.util.PantheonSentProperties;
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
        PSPoiTypes.POI_TYPES.register();
        PSStructureProcessorTypes.PROCESSOR_TYPES.register();
        PSRecipeSerializers.RECIPE_SERIALIZERS.register();
        PSRecipeSerializers.RECIPE_TYPES.register();
        PSMenuTypes.MENU_TYPES.register();
        PSAbilities.ABILITIES.register();

        PSEntityTypes.init();
        PantheonSentProperties.init();

        LifecycleEvent.SETUP.register(PSVillagerProfessions::init);

        if (Platform.isDevelopmentEnvironment()) {
            PantheonSentDebug.init();
        }
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

}
