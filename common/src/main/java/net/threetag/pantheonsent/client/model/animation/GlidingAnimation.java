package net.threetag.pantheonsent.client.model.animation;

import dev.kosmx.playerAnim.core.util.Ease;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.client.model.animation.PalladiumAnimation;
import net.threetag.palladium.power.ability.AbilityEntry;
import net.threetag.palladium.power.ability.AbilityUtil;
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
            builder.get(PlayerModelPart.BODY).rotateXDegrees(-20F).animate(Ease.INOUTSINE, progress);
            builder.get(PlayerModelPart.HEAD).rotateX(model.head.xRot + (float) Math.toRadians(-20F * progress)).animate(Ease.INOUTSINE, progress);
            builder.get(PlayerModelPart.CHEST).rotateX(0).translateY(0F).animate(Ease.INOUTSINE, progress);
            builder.get(PlayerModelPart.RIGHT_LEG).translateY(12F).translateZ(0.1F).animate(Ease.INOUTSINE, progress);
            builder.get(PlayerModelPart.LEFT_LEG).translateY(12F).translateZ(0.1F).animate(Ease.INOUTSINE, progress);
            builder.get(PlayerModelPart.HEAD).translateY(0F).animate(Ease.INOUTSINE, progress);
            builder.get(PlayerModelPart.LEFT_ARM).translateY(2F).rotateX(0F).rotateZDegrees(-115F).animate(Ease.INOUTSINE, progress);
            builder.get(PlayerModelPart.RIGHT_ARM).translateY(2F).rotateX(0F).rotateZDegrees(115F).animate(Ease.INOUTSINE, progress);

            if (player.getMainArm() == HumanoidArm.RIGHT) {
                builder.get(PlayerModelPart.LEFT_LEG)
                        .translateX(model.leftLeg.x + 1F)
                        .translateY(-1F)
                        .translateZ(-3F)
                        .rotateX(0F)
                        .rotateYDegrees(-7.5F)
                        .animate(Ease.INOUTSINE, progress);
                builder.get(PlayerModelPart.RIGHT_LEG)
                        .rotateYDegrees(5F)
                        .rotateXDegrees(-60F)
                        .animate(Ease.INOUTSINE, progress);
            } else {
                builder.get(PlayerModelPart.LEFT_LEG)
                        .rotateXDegrees(-60F)
                        .rotateYDegrees(-5F)
                        .animate(Ease.INOUTSINE, progress);
                builder.get(PlayerModelPart.RIGHT_LEG)
                        .rotateYDegrees(7.5F)
                        .rotateX(0F)
                        .translateX(model.leftLeg.x - 1F)
                        .translateY(model.leftLeg.y - 3F)
                        .translateZ(model.leftLeg.z - 3.1F)
                        .animate(Ease.INOUTSINE, progress);
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
            float timeInAir = Mth.lerp(partialTicks, entry.getProperty(MoonKnightGlidingAbility.PREV_TIME_IN_AIR), entry.getProperty(MoonKnightGlidingAbility.TIME_IN_AIR));

            if (timeInAir > max) {
                max = timeInAir;
            }
        }

        return Mth.clamp(max / 10F, 0F, 1F);
    }

    public float getCapeRotation() {
        return 10F;
    }
}
