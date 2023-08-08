package net.threetag.pantheonsent;

import net.minecraft.resources.ResourceLocation;
import net.threetag.palladium.util.SupporterHandler;
import net.threetag.palladiumcore.event.LifecycleEvents;
import net.threetag.palladiumcore.util.Platform;
import net.threetag.pantheonsent.ability.PSAbilities;
import net.threetag.pantheonsent.accessory.PSAccessories;
import net.threetag.pantheonsent.block.PSBannerPatterns;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.block.entity.PSBlockEntityTypes;
import net.threetag.pantheonsent.client.particle.PSParticleTypes;
import net.threetag.pantheonsent.condition.PSConditionSerializers;
import net.threetag.pantheonsent.entity.PSEntityTypes;
import net.threetag.pantheonsent.entity.PSPoiTypes;
import net.threetag.pantheonsent.entity.PSVillagerProfessions;
import net.threetag.pantheonsent.inventory.PSMenuTypes;
import net.threetag.pantheonsent.item.PSItems;
import net.threetag.pantheonsent.item.crafting.PSRecipeSerializers;
import net.threetag.pantheonsent.item.enchantment.PSEnchantments;
import net.threetag.pantheonsent.network.PSNetwork;
import net.threetag.pantheonsent.sound.PSSoundEvents;
import net.threetag.pantheonsent.util.PantheonSentProperties;
import net.threetag.pantheonsent.world.PSStructureProcessorTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PantheonSent {

    public static final String MOD_ID = "pantheonsent";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        SupporterHandler.enableSupporterCheck();

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
        PSConditionSerializers.CONDITION_SERIALIZERS.register();
        PSBannerPatterns.BANNER_PATTERNS.register();
        PSEnchantments.ENCHANTMENTS.register();
        PSSoundEvents.SOUNDS.register();
        PSParticleTypes.PARTICLE_TYPES.register();
        PSAccessories.ACCESSORIES.register();

        PSNetwork.init();
        PSEntityTypes.init();
        PantheonSentProperties.init();

        LifecycleEvents.SETUP.register(PSVillagerProfessions::init);

        if (!Platform.isProduction()) {
            PantheonSentDebug.init();
        }
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

}
