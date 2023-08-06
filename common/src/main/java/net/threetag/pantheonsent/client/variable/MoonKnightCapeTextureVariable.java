package net.threetag.pantheonsent.client.variable;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.client.dynamictexture.variable.AbstractFloatTextureVariable;
import net.threetag.palladium.client.dynamictexture.variable.CapeTextureVariable;
import net.threetag.palladium.client.dynamictexture.variable.ITextureVariable;
import net.threetag.palladium.client.dynamictexture.variable.ITextureVariableSerializer;
import net.threetag.palladium.documentation.JsonDocumentationBuilder;
import net.threetag.palladium.util.context.DataContext;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.client.model.animation.BlockingAnimation;

import java.util.List;

public class MoonKnightCapeTextureVariable extends CapeTextureVariable {

    public MoonKnightCapeTextureVariable(boolean bobbing, List<Pair<Operation, JsonPrimitive>> operations) {
        super(bobbing, operations);
    }

    @Override
    public float getNumber(DataContext context) {
        if (context.getEntity() instanceof LivingEntity living) {
            float val = super.getNumber(context);
            float blocking = BlockingAnimation.INSTANCE.getProgress(living, 1F) > 0F ? BlockingAnimation.INSTANCE.getProgress(living, 1F) * 80F : 0F;
            return Math.max(val, blocking);
        } else {
            return super.getNumber(context);
        }
    }

    public static class Serializer implements ITextureVariableSerializer {

        @Override
        public ITextureVariable parse(JsonObject json) {
            return new MoonKnightCapeTextureVariable(
                    GsonHelper.getAsBoolean(json, "bobbing", true),
                    AbstractFloatTextureVariable.parseOperations(json));
        }

        @Override
        public String getDocumentationDescription() {
            return "Returns the unfolding of the player's Moon Knight cape.";
        }

        @Override
        public void addDocumentationFields(JsonDocumentationBuilder builder) {
            builder.setTitle("Moon Knight Cape");

            builder.addProperty("bobbing", Boolean.class)
                    .description("Determines of bobbing should be taken into account when doing the calculation")
                    .fallback(true).exampleJson(new JsonPrimitive(true));

            AbstractFloatTextureVariable.addDocumentationFields(builder);
        }

        @Override
        public ResourceLocation getId() {
            return PantheonSent.id("moon_knight_cape");
        }
    }

}
