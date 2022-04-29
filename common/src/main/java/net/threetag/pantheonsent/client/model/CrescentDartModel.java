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

public class CrescentDartModel extends EntityModel<CrescentDart> {

    public static final ModelLayerLocation MODEL_LAYER = new ModelLayerLocation(PantheonSent.id("crescent_dart"), "main");

    private final ModelPart root;

    public CrescentDartModel(ModelPart root) {
        this.root = root.getChild("root");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 6).addBox(-3.5F, -7.0F, 0.0F, 7.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.5F, -6.0F, -0.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(CrescentDart entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
