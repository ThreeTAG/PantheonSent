package net.threetag.pantheonsent.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.client.model.animation.AnimationUtil;
import net.threetag.palladium.util.Easing;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.entity.Khonshu;

public class KhonshuModel<T extends LivingEntity> extends HierarchicalModel<T> implements ArmedModel, HeadedModel {

    public static final ModelLayerLocation MODEL_LAYER = new ModelLayerLocation(PantheonSent.id("khonshu"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart head;
    public float alpha = 1F;

    public KhonshuModel(ModelPart root) {
        super(RenderType::entityTranslucent);
        this.root = root;
        this.body = root.getChild("body");
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
        this.head = root.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 20).addBox(-5.0F, -8.0F, -3.0F, 10.0F, 15.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-5.0F, -8.0F, -3.0F, 10.0F, 15.0F, 6.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -4.0F, 1.0F));

        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(76, 41).addBox(-4.0F, -2.0F, -3.0F, 5.0F, 19.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 68).addBox(-4.0F, -2.0F, -3.0F, 5.0F, 19.0F, 6.0F, new CubeDeformation(0.25F)), PartPose.offset(-6.0F, -10.0F, 1.0F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(22, 68).addBox(-1.0F, -2.0F, -3.0F, 5.0F, 19.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(64, 16).addBox(-1.0F, -2.0F, -3.0F, 5.0F, 19.0F, 6.0F, new CubeDeformation(0.25F)), PartPose.offset(6.0F, -10.0F, 1.0F));

        right_arm.addOrReplaceChild("staff", CubeListBuilder.create().texOffs(0, 68).addBox(-0.8333F, -1.0F, -3.6667F, 2.0F, 2.0F, 46.0F, new CubeDeformation(0.0F))
                .texOffs(98, 34).addBox(0.1667F, -6.5F, -15.6667F, 0.0F, 13.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(98, 49).addBox(-0.3333F, -6.5F, -16.6667F, 0.0F, 13.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3333F, 8.1468F, -13.8509F, -0.5236F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(60, 62).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 21.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(22, 41).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 21.0F, 6.0F, new CubeDeformation(0.25F)), PartPose.offset(-2.5F, 3.0F, 1.0F));

        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(44, 41).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 21.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 41).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 21.0F, 6.0F, new CubeDeformation(0.25F)), PartPose.offset(2.5F, 3.0F, 1.0F));

        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -8.0F, -6.0F, 10.0F, 9.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(42, 0).addBox(-3.0F, -5.0F, -16.0F, 6.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, 2.0F));

        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.head.yRot = netHeadYaw * (float) (Math.PI / 180.0);
        this.head.xRot = (float) (headPitch * (float) (Math.PI / 180.0) + Math.toRadians(15));
        this.head.y += Mth.sin(ageInTicks / 10F) / 4F;

        if (this.riding) {
            this.rightArm.xRot = (float) (-Math.PI / 5);
            this.rightArm.yRot = 0.0F;
            this.rightArm.zRot = 0.0F;
            this.leftArm.xRot = (float) (-Math.PI / 5);
            this.leftArm.yRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            this.rightLeg.xRot = -1.4137167F;
            this.rightLeg.yRot = (float) (Math.PI / 10);
            this.rightLeg.zRot = 0.07853982F;
            this.leftLeg.xRot = -1.4137167F;
            this.leftLeg.yRot = (float) (-Math.PI / 10);
            this.leftLeg.zRot = -0.07853982F;
        } else {
            this.rightArm.xRot = (float) Math.toRadians(-60) + Mth.clamp(Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount * 0.5F, -10, 0);
            this.rightArm.yRot = 0.0F;
            this.rightArm.zRot = 0.0F;
            this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
            this.leftArm.yRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
            this.rightLeg.yRot = 0.0F;
            this.rightLeg.zRot = 0.0F;
            this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
            this.leftLeg.yRot = 0.0F;
            this.leftLeg.zRot = 0.0F;
        }


        if (entity instanceof Khonshu khonshu) {
            var progress = khonshu.getWiggleArmsProgress(ageInTicks - entity.tickCount);

            if (progress > 0F) {
                progress = AnimationUtil.ease(Easing.INOUTSINE, progress);
                var wiggle = Mth.sin(ageInTicks / 10F) * 10F;
                interpolateXRotTo(this.rightArm, (float) Math.toRadians(-60 - wiggle), progress);
                interpolateYRotTo(this.rightArm, (float) Math.toRadians(-60), progress);
                interpolateXRotTo(this.leftArm, (float) Math.toRadians(-60 - wiggle), progress);
                interpolateYRotTo(this.leftArm, (float) Math.toRadians(60), progress);
            }
        }
    }

    public static void interpolateXRotTo(ModelPart modelPart, float destination, float progress) {
        modelPart.xRot += (destination - modelPart.xRot) * progress;
    }

    public static void interpolateYRotTo(ModelPart modelPart, float destination, float progress) {
        modelPart.yRot += (destination - modelPart.yRot) * progress;
    }

    public static void interpolateZRotTo(ModelPart modelPart, float destination, float progress) {
        modelPart.zRot += (destination - modelPart.zRot) * progress;
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTick) {
        super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
        if (entity instanceof Khonshu khonshu) {
            this.alpha = khonshu.isDespawning() ? Mth.clamp(khonshu.getDespawnTimer(partialTick) / 60F, 0, 1F) : 1F;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.young = false;
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha * this.alpha);
        this.alpha = 1F;
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    private ModelPart getArm(HumanoidArm arm) {
        return arm == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
    }

    @Override
    public void translateToHand(HumanoidArm side, PoseStack poseStack) {
        this.getArm(side).translateAndRotate(poseStack);
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }
}