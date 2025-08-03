package net.threetag.pantheonsent.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.entity.CrescentDart;
import net.threetag.pantheonsent.entity.ThrownBaton;

public class ThrownBatonModel extends EntityModel<ThrownBaton> {

    public static final ModelLayerLocation MODEL_LAYER = new ModelLayerLocation(PantheonSent.id("thrown_baton"), "main");

    private final ModelPart root;

    public ThrownBatonModel(ModelPart root) {
        this.root = root.getChild("root");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition
                .addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F))
                .addOrReplaceChild("baton", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -12.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -3.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 16);
    }

    @Override
    public void setupAnim(ThrownBaton entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
