package net.threetag.pantheonsent;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.minecraft.client.model.HumanoidModel;
import net.threetag.palladium.client.renderer.renderlayer.ModelLookup;
import net.threetag.pantheonsent.client.model.CrescentDartModel;
import net.threetag.pantheonsent.client.model.MoonKnightSuitModel;
import net.threetag.pantheonsent.item.PSItems;

public class PantheonSentClient {

    public static void init() {
        EntityModelLayerRegistry.register(CrescentDartModel.MODEL_LAYER, CrescentDartModel::createLayer);
        ModelLookup.register(PantheonSent.id("moon_knight_suit"), new ModelLookup.Model(MoonKnightSuitModel::new, (en, model) -> model instanceof HumanoidModel));
        PSItems.initProperties();
    }

}
