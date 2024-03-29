package net.threetag.pantheonsent.client.model.animation;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.client.model.animation.PalladiumAnimation;
import net.threetag.palladium.power.ability.AbilityEntry;
import net.threetag.palladium.power.ability.AbilityUtil;
import net.threetag.palladium.util.Easing;
import net.threetag.pantheonsent.ability.MoonKnightBlockingAbility;
import net.threetag.pantheonsent.ability.PSAbilities;

public class BlockingAnimation extends PalladiumAnimation {

    public static final BlockingAnimation INSTANCE = new BlockingAnimation();

    public BlockingAnimation() {
        super(20);
    }

    public float getProgress(LivingEntity entity, float partialTicks) {
        float max = 0;
        var entries = AbilityUtil.getEntries(entity, PSAbilities.MOON_KNIGHT_BLOCKING.get());

        if (entries.isEmpty()) {
            return 0F;
        }

        for (AbilityEntry entry : entries) {
            float timer = ((MoonKnightBlockingAbility) PSAbilities.MOON_KNIGHT_BLOCKING.get()).getAnimationValue(entry, partialTicks);

            if (timer > max) {
                max = timer;
            }
        }

        return Mth.clamp(max, 0F, 1F);
    }

    @Override
    public void animate(Builder builder, AbstractClientPlayer player, HumanoidModel<?> model, FirstPersonContext firstPersonContext, float partialTicks) {
        var progress = this.getProgress(player, partialTicks);

        if (progress > 0F) {

            if (firstPersonContext.firstPerson()) {
                builder.get(PlayerModelPart.RIGHT_ARM)
                        .setZ(5)
                        .setX(-2)
                        .setY(2)
                        .rotateXDegrees(-50F)
                        .rotateZDegrees(-40F)
                        .animate(Easing.INQUINT, progress);

                builder.get(PlayerModelPart.LEFT_ARM)
                        .setZ(5)
                        .setX(2)
                        .setY(2)
                        .rotateXDegrees(-50F)
                        .rotateZDegrees(40F)
                        .animate(Easing.INQUINT, progress);
            } else {
                builder.get(PlayerModelPart.RIGHT_ARM)
                        .setX(model.rightArm.x + 2)
                        .rotateXDegrees(-15)
                        .rotateYDegrees(-25)
                        .rotateZDegrees(-20)
                        .animate(Easing.INQUINT, progress);
                builder.get(PlayerModelPart.LEFT_ARM)
                        .setX(model.leftArm.x - 2)
                        .rotateXDegrees(-15)
                        .rotateYDegrees(25)
                        .rotateZDegrees(20)
                        .animate(Easing.INQUINT, progress);
                builder.get(PlayerModelPart.HEAD).rotateX(0).animate(Easing.INQUINT, progress);
                builder.get(PlayerModelPart.HEAD).rotateY(0).animate(Easing.INQUINT, progress);
            }
        }
    }
}
