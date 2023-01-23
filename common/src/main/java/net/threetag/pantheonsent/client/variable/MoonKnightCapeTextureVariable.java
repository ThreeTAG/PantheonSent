package net.threetag.pantheonsent.client.variable;

import com.google.gson.JsonObject;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.client.dynamictexture.variable.CapeTextureVariable;
import net.threetag.pantheonsent.client.model.animation.BlockingAnimation;

public class MoonKnightCapeTextureVariable extends CapeTextureVariable {

    public MoonKnightCapeTextureVariable(JsonObject json) {
        super(json);
    }

    @Override
    public float getNumber(LivingEntity entity) {
        float val = super.getNumber(entity);
        float blocking = BlockingAnimation.INSTANCE.getProgress(entity, 1F) > 0F ? BlockingAnimation.INSTANCE.getProgress(entity, 1F) * 80F : 0F;
        return Math.max(val, blocking);
    }
}
