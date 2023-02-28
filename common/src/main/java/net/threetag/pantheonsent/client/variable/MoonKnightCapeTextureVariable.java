package net.threetag.pantheonsent.client.variable;

import com.google.gson.JsonObject;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.client.dynamictexture.variable.CapeTextureVariable;
import net.threetag.pantheonsent.client.model.animation.BlockingAnimation;

public class MoonKnightCapeTextureVariable extends CapeTextureVariable {

    public MoonKnightCapeTextureVariable(JsonObject json) {
        super(json);
    }

    @Override
    public float getNumber(Entity entity) {
        if(entity instanceof LivingEntity living) {
            float val = super.getNumber(entity);
            float blocking = BlockingAnimation.INSTANCE.getProgress(living, 1F) > 0F ? BlockingAnimation.INSTANCE.getProgress(living, 1F) * 80F : 0F;
            return Math.max(val, blocking);
        } else {
            return super.getNumber(entity);
        }
    }
}
