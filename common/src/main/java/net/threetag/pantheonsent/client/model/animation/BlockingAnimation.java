package net.threetag.pantheonsent.client.model.animation;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.client.model.animation.Animation;
import net.threetag.palladium.client.model.animation.AnimationUtil;
import net.threetag.palladium.power.ability.AbilityEntry;
import net.threetag.palladium.power.ability.AbilityUtil;
import net.threetag.pantheonsent.ability.MoonKnightBlockingAbility;
import net.threetag.pantheonsent.ability.PSAbilities;

public class BlockingAnimation extends Animation {

    public static final BlockingAnimation INSTANCE = new BlockingAnimation();

    @Override
    public int getPriority() {
        return 15;
    }

    @Override
    public boolean active(LivingEntity entity) {
        return getProgress(entity, 0F) > 0F;
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

        float val = Mth.clamp(max / 10F, 0F, 1F);
        float sqt = val * val;
        return sqt / (2F * (sqt - val) + 1F);
    }

    @Override
    public void setupAnimation(HumanoidModel<?> model, LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks) {
        float progress = getProgress(entity, partialTicks);
        AnimationUtil.interpolateXTo(model.rightArm, model.rightArm.x + 2, progress);
        AnimationUtil.interpolateXTo(model.leftArm, model.leftArm.x - 2, progress);

        AnimationUtil.interpolateXRotTo(model.head, 0, progress);
        AnimationUtil.interpolateYRotTo(model.head, 0, progress);
        model.hat.copyFrom(model.head);
        AnimationUtil.interpolateXRotTo(model.rightArm, (float) Math.toRadians(-15), progress);
        AnimationUtil.interpolateYRotTo(model.rightArm, (float) Math.toRadians(-25), progress);
        AnimationUtil.interpolateZRotTo(model.rightArm, (float) Math.toRadians(-20), progress);
        AnimationUtil.interpolateXRotTo(model.leftArm, (float) Math.toRadians(-15), progress);
        AnimationUtil.interpolateYRotTo(model.leftArm, (float) Math.toRadians(25), progress);
        AnimationUtil.interpolateZRotTo(model.leftArm, (float) Math.toRadians(20), progress);
    }
}
