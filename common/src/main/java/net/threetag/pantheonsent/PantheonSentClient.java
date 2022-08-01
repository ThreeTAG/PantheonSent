package net.threetag.pantheonsent;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.client.renderer.renderlayer.ModelLookup;
import net.threetag.palladium.client.renderer.renderlayer.RenderLayerRegistry;
import net.threetag.palladium.item.CurioTrinketRegistry;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.client.model.CrescentDartModel;
import net.threetag.pantheonsent.client.model.MoonKnightSuitModel;
import net.threetag.pantheonsent.client.renderer.entity.KhonshuRenderer;
import net.threetag.pantheonsent.client.renderer.item.EyeOfHorusRenderer;
import net.threetag.pantheonsent.entity.PSEntityTypes;
import net.threetag.pantheonsent.inventory.PSMenuTypes;
import net.threetag.pantheonsent.item.PSItems;

public class PantheonSentClient {

    @SuppressWarnings("unchecked")
    public static void init() {
        EntityModelLayerRegistry.register(CrescentDartModel.MODEL_LAYER, CrescentDartModel::createLayer);
        EntityRendererRegistry.register(PSEntityTypes.KHONSHU, KhonshuRenderer::new);
        ModelLookup.register(PantheonSent.id("moon_knight_suit"), new ModelLookup.Model(MoonKnightSuitModel::new, (en, model) -> model instanceof HumanoidModel));
        PSItems.initProperties();
        PSMenuTypes.initScreens();

        RenderTypeRegistry.register(RenderType.cutout(), PSBlocks.KHONSHU_USHABTI.get());
        CurioTrinketRegistry.registerRenderer(PSItems.EYE_OF_HORUS.get(), new EyeOfHorusRenderer(null));
        RenderLayerRegistry.addLayer(entityType -> true, renderLayerParent -> new EyeOfHorusRenderer((RenderLayerParent<LivingEntity, EntityModel<LivingEntity>>) renderLayerParent));
    }

}
