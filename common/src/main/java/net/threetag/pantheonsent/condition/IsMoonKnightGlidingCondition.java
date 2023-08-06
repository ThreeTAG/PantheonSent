package net.threetag.pantheonsent.condition;

import com.google.gson.JsonObject;
import net.threetag.palladium.condition.Condition;
import net.threetag.palladium.condition.ConditionSerializer;
import net.threetag.palladium.util.context.DataContext;
import net.threetag.pantheonsent.ability.MoonKnightGlidingAbility;

public class IsMoonKnightGlidingCondition extends Condition {

    @Override
    public boolean active(DataContext context) {
        var entity = context.getLivingEntity();

        return entity != null && MoonKnightGlidingAbility.getProgress(entity, 1F) > 0F;
    }

    @Override
    public ConditionSerializer getSerializer() {
        return PSConditionSerializers.IS_MOON_KNIGHT_GLIDING.get();
    }

    public static class Serializer extends ConditionSerializer {

        @Override
        public Condition make(JsonObject json) {
            return new IsMoonKnightGlidingCondition();
        }

        @Override
        public String getDocumentationDescription() {
            return "Checks if the entity is current doing the Moon Knight gliding.";
        }
    }
}
