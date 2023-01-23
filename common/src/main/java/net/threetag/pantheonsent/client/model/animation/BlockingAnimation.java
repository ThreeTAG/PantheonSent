package net.threetag.pantheonsent.client.model.animation;

import dev.kosmx.playerAnim.core.util.Ease;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.client.model.animation.PalladiumAnimation;
import net.threetag.palladium.power.ability.AbilityEntry;
import net.threetag.palladium.power.ability.AbilityUtil;
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
            float timer = Mth.lerp(partialTicks, entry.getProperty(MoonKnightBlockingAbility.PREV_TIMER), entry.getProperty(MoonKnightBlockingAbility.TIMER));

            if (timer > max) {
                max = timer;
            }
        }

        return Mth.clamp(max / 10F, 0F, 1F);
    }

    @Override
    public void animate(Builder builder, AbstractClientPlayer player, HumanoidModel<?> model, FirstPersonContext firstPersonContext, float partialTicks) {
        var progress = this.getProgress(player, partialTicks);

        if (progress > 0F) {
            builder.get(PlayerModelPart.RIGHT_ARM)
                    .translateX(model.rightArm.x + 2)
                    .rotateXDegrees(-15)
                    .rotateYDegrees(-25)
                    .rotateZDegrees(-20)
                    .animate(Ease.INOUTSINE, progress);
            builder.get(PlayerModelPart.LEFT_ARM)
                    .translateX(model.leftArm.x - 2)
                    .rotateXDegrees(-15)
                    .rotateYDegrees(25)
                    .rotateZDegrees(20)
                    .animate(Ease.INOUTSINE, progress);
            builder.get(PlayerModelPart.HEAD).rotateX(0).animate(Ease.INOUTSINE, progress);
            builder.get(PlayerModelPart.HEAD).rotateY(0).animate(Ease.INOUTSINE, progress);
        }
    }
}
