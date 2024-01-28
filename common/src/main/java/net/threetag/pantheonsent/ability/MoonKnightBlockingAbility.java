package net.threetag.pantheonsent.ability;

import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.power.IPowerHolder;
import net.threetag.palladium.power.ability.*;
import net.threetag.palladium.util.property.IntegerProperty;
import net.threetag.palladium.util.property.PalladiumProperty;
import net.threetag.palladium.util.property.PropertyManager;
import net.threetag.palladium.util.property.SyncType;
import net.threetag.palladiumcore.event.EventResult;
import net.threetag.palladiumcore.event.LivingEntityEvents;
import net.threetag.pantheonsent.sound.PSSoundEvents;

public class MoonKnightBlockingAbility extends Ability implements LivingEntityEvents.Attack, AnimationTimer {

    public static final PalladiumProperty<Integer> TIMER = new IntegerProperty("timer").sync(SyncType.NONE);
    public static final PalladiumProperty<Integer> PREV_TIMER = new IntegerProperty("prev_timer").sync(SyncType.NONE);

    public MoonKnightBlockingAbility() {
        LivingEntityEvents.ATTACK.register(this);
    }

    @Override
    public void registerUniqueProperties(PropertyManager manager) {
        manager.register(TIMER, 0);
        manager.register(PREV_TIMER, 0);
    }

    @Override
    public void tick(LivingEntity entity, AbilityEntry entry, IPowerHolder holder, boolean enabled) {
        if (entity.level().isClientSide) {
            int timer = entry.getProperty(TIMER);
            entry.setUniqueProperty(PREV_TIMER, timer);

            if (enabled && timer < 5) {
                entry.setUniqueProperty(TIMER, timer + 1);
            } else if (!enabled && timer > 0) {
                entry.setUniqueProperty(TIMER, timer - 1);
            }
        }
    }

    @Override
    public void firstTick(LivingEntity entity, AbilityEntry entry, IPowerHolder holder, boolean enabled) {
        if (enabled) {
            entity.level().playLocalSound(entity.getX(), entity.getEyeY(), entity.getZ(), PSSoundEvents.CAPE.get(), SoundSource.PLAYERS, 1F, 1F, false);
        }
    }

    @Override
    public void lastTick(LivingEntity entity, AbilityEntry entry, IPowerHolder holder, boolean enabled) {
        if (enabled) {
            entity.level().playLocalSound(entity.getX(), entity.getEyeY(), entity.getZ(), PSSoundEvents.CAPE.get(), SoundSource.PLAYERS, 1F, 1F, false);
        }
    }

    @Override
    public EventResult livingEntityAttack(LivingEntity entity, DamageSource damageSource, float amount) {
        if (!AbilityUtil.getEnabledEntries(entity, PSAbilities.MOON_KNIGHT_BLOCKING.get()).isEmpty()) {
            return EventResult.cancel();
        }

        if (damageSource.getDirectEntity() instanceof LivingEntity livingEntity) {
            if (!AbilityUtil.getEnabledEntries(livingEntity, PSAbilities.MOON_KNIGHT_BLOCKING.get()).isEmpty()) {
                return EventResult.cancel();
            }
        }

        return EventResult.pass();
    }

    @Override
    public float getAnimationValue(AbilityEntry entry, float partialTick) {
        return Mth.lerp(partialTick, entry.getProperty(PREV_TIMER), entry.getProperty(TIMER)) / 5F;
    }

    @Override
    public float getAnimationTimer(AbilityEntry entry, float partialTick, boolean maxedOut) {
        if (maxedOut) {
            return 5;
        }
        return entry.getProperty(TIMER);
    }
}
