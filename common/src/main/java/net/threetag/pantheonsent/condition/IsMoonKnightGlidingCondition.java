package net.threetag.pantheonsent.condition;

import com.google.gson.JsonObject;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.condition.Condition;
import net.threetag.palladium.condition.ConditionSerializer;
import net.threetag.palladium.power.IPowerHolder;
import net.threetag.palladium.power.Power;
import net.threetag.palladium.power.ability.AbilityEntry;
import net.threetag.pantheonsent.ability.MoonKnightGlidingAbility;
import org.jetbrains.annotations.Nullable;

public class IsMoonKnightGlidingCondition extends Condition {

    @Override
    public boolean active(LivingEntity entity, @Nullable AbilityEntry entry, @Nullable Power power, @Nullable IPowerHolder holder) {
        float gliding = MoonKnightGlidingAbility.getProgress(entity, 1F);
        return gliding > 0F;
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
