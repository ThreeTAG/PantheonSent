package net.threetag.pantheonsent.condition;

import net.threetag.palladium.condition.ConditionSerializer;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;

public class PSConditionSerializers {

    public static final DeferredRegister<ConditionSerializer> CONDITION_SERIALIZERS = DeferredRegister.create(PantheonSent.MOD_ID, ConditionSerializer.REGISTRY);

    public static final RegistrySupplier<ConditionSerializer> IS_MOON_KNIGHT_GLIDING = CONDITION_SERIALIZERS.register("is_moon_knight_gliding", IsMoonKnightGlidingCondition.Serializer::new);

}
