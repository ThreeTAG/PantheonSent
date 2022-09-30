package net.threetag.pantheonsent.ability;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.power.IPowerHolder;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.palladium.power.ability.AbilityEntry;
import net.threetag.palladium.util.property.IntegerProperty;
import net.threetag.palladium.util.property.PalladiumProperty;
import net.threetag.palladium.util.property.PropertyManager;
import net.threetag.palladium.util.property.SyncType;
import net.threetag.palladiumcore.event.EventResult;
import net.threetag.palladiumcore.event.LivingEntityEvents;

public class MoonKnightBlockingAbility extends Ability implements LivingEntityEvents.Hurt {

    public static final PalladiumProperty<Integer> TIMER = new IntegerProperty("timer").sync(SyncType.NONE);
    public static final PalladiumProperty<Integer> PREV_TIMER = new IntegerProperty("prev_timer").sync(SyncType.NONE);

    public MoonKnightBlockingAbility() {
        LivingEntityEvents.HURT.register(this);
    }

    @Override
    public void registerUniqueProperties(PropertyManager manager) {
        manager.register(TIMER, 0);
        manager.register(PREV_TIMER, 0);
    }

    @Override
    public void tick(LivingEntity entity, AbilityEntry entry, IPowerHolder holder, boolean enabled) {
        if (entity.level.isClientSide) {
            int timer = entry.getProperty(TIMER);
            entry.setOwnProperty(PREV_TIMER, timer);

            if (enabled && timer < 10) {
                entry.setOwnProperty(TIMER, timer + 1);
            } else if (!enabled && timer > 0) {
                entry.setOwnProperty(TIMER, timer - 1);
            }
        }
    }

    @Override
    public EventResult livingEntityHurt(LivingEntity entity, DamageSource damageSource, float amount) {
        if (!Ability.getEnabledEntries(entity, PSAbilities.MOON_KNIGHT_BLOCKING.get()).isEmpty()) {
            return EventResult.cancel();
        }

        if (damageSource.getDirectEntity() instanceof LivingEntity livingEntity) {
            if (!Ability.getEnabledEntries(livingEntity, PSAbilities.MOON_KNIGHT_BLOCKING.get()).isEmpty()) {
                return EventResult.cancel();
            }
        }

        return EventResult.pass();
    }
}
