package net.threetag.pantheonsent.client.model.animation;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.threetag.palladium.client.model.animation.PalladiumAnimation;
import net.threetag.pantheonsent.util.PantheonSentProperties;

public class KhonshuRecruitmentAnimation extends PalladiumAnimation {

    public KhonshuRecruitmentAnimation() {
        super(100);
    }

    @Override
    public void animate(Builder builder, AbstractClientPlayer player, HumanoidModel<?> model, FirstPersonContext firstPersonContext, float partialTicks) {
        if(PantheonSentProperties.KHONSHU_RECRUITING_TIMER.isRegistered(player)) {
            var timer = PantheonSentProperties.KHONSHU_RECRUITING_TIMER.get(player);

            if(timer > 0) {
                builder.get(PlayerModelPart.HEAD).rotateXDegrees(-30);
                builder.get(PlayerModelPart.CHEST).rotateXDegrees(-15);
                builder.get(PlayerModelPart.RIGHT_ARM).rotateXDegrees(20).rotateZDegrees(37.5F);
                builder.get(PlayerModelPart.LEFT_ARM).rotateXDegrees(20).rotateZDegrees(-37.5F);
                builder.get(PlayerModelPart.RIGHT_LEG).rotateXDegrees(90).rotateYDegrees(-22.5F);
                builder.get(PlayerModelPart.LEFT_LEG).rotateXDegrees(90).rotateYDegrees(22.5F);
                builder.get(PlayerModelPart.BODY).translateY(-9.5F / 16F);
            }
        }
    }
}
