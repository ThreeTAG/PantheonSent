package net.threetag.pantheonsent.client.model.animation;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.threetag.palladium.client.model.animation.Animation;
import net.threetag.pantheonsent.util.PantheonSentProperties;

public class KhonshuRecruitmentAnimation extends Animation {

    @Override
    public int getPriority() {
        return 50;
    }

    @Override
    public boolean active(LivingEntity entity) {
        return entity instanceof Player && PantheonSentProperties.KHONSHU_RECRUITING_TIMER.isRegistered(entity) && PantheonSentProperties.KHONSHU_RECRUITING_TIMER.get(entity) > 0;
    }

    @Override
    public void setupRotations(PlayerRenderer playerRenderer, AbstractClientPlayer player, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        poseStack.translate(0, -9.5F / 16F, 0);
    }

    @Override
    public void setupAnimation(HumanoidModel<?> model, LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks) {
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
