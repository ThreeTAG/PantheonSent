package net.threetag.pantheonsent;

import net.minecraft.resources.ResourceLocation;
import net.threetag.pantheonsent.entity.PSEntityTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PantheonSent {

    public static final String MOD_ID = "pantheonsent";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        PSEntityTypes.ENTITIES.register();
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

}
