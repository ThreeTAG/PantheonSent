package net.threetag.pantheonsent.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.entity.Khonshu;
import net.threetag.pantheonsent.util.PantheonSentProperties;

public class KhonshuRenderer extends MobRenderer<Khonshu, PlayerModel<Khonshu>> {

    public static final ResourceLocation TEXTURE = PantheonSent.id("textures/entity/khonshu.png");

    public KhonshuRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER_SLIM), true), 0.7F);
    }

    @Override
    protected void scale(Khonshu livingEntity, PoseStack matrixStack, float partialTickTime) {
        matrixStack.scale(1F, 1.5F, 1F);
    }

    @Override
    public boolean shouldRender(Khonshu livingEntity, Frustum camera, double camX, double camY, double camZ) {
        return livingEntity.avatarId != null && livingEntity.avatarId.equals(Minecraft.getInstance().player.getUUID()) && super.shouldRender(livingEntity, camera, camX, camY, camZ);
    }

    @Override
    public ResourceLocation getTextureLocation(Khonshu entity) {
        return TEXTURE;
    }

    public static void avatarRecruitmentAnimation(LivingEntity entity, HumanoidModel<?> model) {
        if (entity instanceof Player) {
            int timer = PantheonSentProperties.KHONSHU_RECRUITING_TIMER.get(entity);

            if (timer > 0) {
                model.head.xRot = model.hat.xRot = (float) Math.toRadians(-30);
                model.body.xRot = (float) Math.toRadians(-15);

                model.rightArm.xRot = model.leftArm.xRot = (float) Math.toRadians(20);
                model.rightArm.zRot = (float) Math.toRadians(37.5F);
                model.leftArm.zRot = (float) Math.toRadians(-37.5F);

                model.rightLeg.xRot = model.leftLeg.xRot = (float) Math.toRadians(90);
                model.rightLeg.yRot = (float) Math.toRadians(-22.5F);
                model.leftLeg.yRot = (float) Math.toRadians(22.5F);
            }
        }
    }

    public static void translateAvatarRecruitmentAnimation(AbstractClientPlayer entity, PoseStack poseStack) {
        int timer = PantheonSentProperties.KHONSHU_RECRUITING_TIMER.get(entity);

        if (timer > 0) {
            poseStack.translate(0, -9.5F / 16F, 0);
        }
    }

}
