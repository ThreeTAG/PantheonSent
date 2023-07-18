package net.threetag.pantheonsent.client.model.animation;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.client.model.animation.PalladiumAnimation;
import net.threetag.palladium.power.ability.AbilityEntry;
import net.threetag.palladium.power.ability.AbilityUtil;
import net.threetag.palladium.util.Easing;
import net.threetag.pantheonsent.ability.MoonKnightGlidingAbility;
import net.threetag.pantheonsent.ability.PSAbilities;

public class GlidingAnimation extends PalladiumAnimation {

    public static final GlidingAnimation INSTANCE = new GlidingAnimation();

    public GlidingAnimation() {
        super(20);
    }

    @Override
    public void animate(Builder builder, AbstractClientPlayer player, HumanoidModel<?> model, FirstPersonContext firstPersonContext, float partialTicks) {
        var progress = getProgress(player, partialTicks);

        if (progress > 0F && !firstPersonContext.firstPerson()) {
            builder.get(PlayerModelPart.BODY).setXRotDegrees(-20F).animate(Easing.INOUTSINE, progress);
            builder.get(PlayerModelPart.HEAD).setXRot(model.head.xRot + (float) Math.toRadians(-20F * progress)).animate(Easing.INOUTSINE, progress);
            builder.get(PlayerModelPart.CHEST).setXRot(0).setY(0F).animate(Easing.INOUTSINE, progress);
            builder.get(PlayerModelPart.RIGHT_LEG).setY(12F).setZ(0.1F).animate(Easing.INOUTSINE, progress);
            builder.get(PlayerModelPart.LEFT_LEG).setY(12F).setZ(0.1F).animate(Easing.INOUTSINE, progress);
            builder.get(PlayerModelPart.HEAD).setY(0F).animate(Easing.INOUTSINE, progress);
            builder.get(PlayerModelPart.LEFT_ARM).setY(2F).setXRot(0F).setYRot(0).setZRotDegrees(-115F).animate(Easing.INOUTSINE, progress);
            builder.get(PlayerModelPart.RIGHT_ARM).setY(2F).setXRot(0F).setYRot(0).setZRotDegrees(115F).animate(Easing.INOUTSINE, progress);

            if (player.getMainArm() == HumanoidArm.RIGHT) {
                builder.get(PlayerModelPart.LEFT_LEG)
                        .setX(model.leftLeg.x + 1F)
                        .moveY(-1F)
                        .moveZ(-3F)
                        .setXRot(0F)
                        .setYRotDegrees(-7.5F)
                        .animate(Easing.INOUTSINE, progress);
                builder.get(PlayerModelPart.RIGHT_LEG)
                        .setYRotDegrees(5F)
                        .setXRotDegrees(-60F)
                        .animate(Easing.INOUTSINE, progress);
            } else {
                builder.get(PlayerModelPart.LEFT_LEG)
                        .setXRotDegrees(-60F)
                        .setYRotDegrees(-5F)
                        .animate(Easing.INOUTSINE, progress);
                builder.get(PlayerModelPart.RIGHT_LEG)
                        .setYRotDegrees(7.5F)
                        .setXRot(0F)
                        .setX(model.leftLeg.x - 1F)
                        .setY(model.leftLeg.y - 3F)
                        .setZ(model.leftLeg.z - 3.1F)
                        .animate(Easing.INOUTSINE, progress);
            }
        }
    }

    public float getProgress(LivingEntity entity, float partialTicks) {
        float max = 0;
        var entries = AbilityUtil.getEntries(entity, PSAbilities.MOON_KNIGHT_GLIDING.get());

        if (entries.isEmpty()) {
            return 0F;
        }

        for (AbilityEntry entry : entries) {
            float timeInAir = ((MoonKnightGlidingAbility) PSAbilities.MOON_KNIGHT_GLIDING.get()).getAnimationValue(entry, partialTicks);

            if (timeInAir > max) {
                max = timeInAir;
            }
        }

        return Mth.clamp(max, 0F, 1F);
    }

    public float getCapeRotation() {
        return 10F;
    }
}
