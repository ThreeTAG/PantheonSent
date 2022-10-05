package net.threetag.pantheonsent.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.threetag.pantheonsent.entity.Khonshu;

public class KhonshuModel extends PlayerModel<Khonshu> {

    public float alpha = 1F;

    public KhonshuModel(ModelPart modelPart, boolean bl) {
        super(modelPart, bl);
    }

    @Override
    public void prepareMobModel(Khonshu entity, float limbSwing, float limbSwingAmount, float partialTick) {
        super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);

        this.alpha = entity.isDespawning() ? Mth.clamp(entity.getDespawnTimer(partialTick) / 60F, 0, 1F) : 1F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha * this.alpha);
    }
}
