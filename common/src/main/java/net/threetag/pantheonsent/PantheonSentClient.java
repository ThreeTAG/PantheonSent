package net.threetag.pantheonsent;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.client.dynamictexture.DynamicTexture;
import net.threetag.palladium.client.renderer.renderlayer.ModelLookup;
import net.threetag.palladium.event.PalladiumClientEvents;
import net.threetag.palladium.item.CurioTrinketRegistry;
import net.threetag.palladiumcore.event.LifecycleEvents;
import net.threetag.palladiumcore.registry.client.EntityRendererRegistry;
import net.threetag.palladiumcore.registry.client.RenderTypeRegistry;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.client.PSClientEventHandler;
import net.threetag.pantheonsent.client.model.CrescentDartModel;
import net.threetag.pantheonsent.client.model.MoonKnightCapeModel;
import net.threetag.pantheonsent.client.model.MoonKnightSuitModel;
import net.threetag.pantheonsent.client.model.animation.BlockingAnimation;
import net.threetag.pantheonsent.client.model.animation.GlidingAnimation;
import net.threetag.pantheonsent.client.model.animation.KhonshuRecruitmentAnimation;
import net.threetag.pantheonsent.client.renderer.entity.KhonshuRenderer;
import net.threetag.pantheonsent.client.renderer.item.EyeOfHorusRenderer;
import net.threetag.pantheonsent.client.variable.MoonKnightCapeTextureVariable;
import net.threetag.pantheonsent.entity.PSEntityTypes;
import net.threetag.pantheonsent.inventory.PSMenuTypes;
import net.threetag.pantheonsent.item.PSItems;
import net.threetag.pantheonsent.util.PantheonSentProperties;

public class PantheonSentClient {

    @SuppressWarnings("unchecked")
    public static void init() {
        PantheonSentProperties.initClient();
        PSClientEventHandler.init();

        // Entity Renderers
        EntityRendererRegistry.registerModelLayer(CrescentDartModel.MODEL_LAYER, CrescentDartModel::createLayer);
        EntityRendererRegistry.register(PSEntityTypes.KHONSHU, KhonshuRenderer::new);
        EntityRendererRegistry.addRenderLayerToAll(renderLayerParent -> new EyeOfHorusRenderer((RenderLayerParent<LivingEntity, EntityModel<LivingEntity>>) renderLayerParent));

        // Model Types
        ModelLookup.register(PantheonSent.id("moon_knight_suit"), new ModelLookup.Model(MoonKnightSuitModel::new, (en, model) -> model instanceof HumanoidModel));
        ModelLookup.register(PantheonSent.id("moon_knight_cape"), new ModelLookup.Model(MoonKnightCapeModel::new, (en, model) -> model instanceof HumanoidModel));

        // Dynamic Texture Variables
        DynamicTexture.registerVariable(PantheonSent.id("moon_knight_cape"), MoonKnightCapeTextureVariable::new);

        // Animations
        PalladiumClientEvents.REGISTER_ANIMATIONS.register(registry -> {
            registry.accept(PantheonSent.id("khonshu_recruitment"), new KhonshuRecruitmentAnimation());
            registry.accept(PantheonSent.id("gliding"), GlidingAnimation.INSTANCE);
            registry.accept(PantheonSent.id("blocking"), BlockingAnimation.INSTANCE);
        });

        // During Setup
        LifecycleEvents.CLIENT_SETUP.register(() -> {
            PSItems.initProperties();
            PSMenuTypes.initScreens();

            // Render Types
            RenderTypeRegistry.registerBlock(RenderType.cutout(), PSBlocks.KHONSHU_USHABTI.get());
            CurioTrinketRegistry.registerRenderer(PSItems.EYE_OF_HORUS.get(), new EyeOfHorusRenderer(null));
        });
    }

}
