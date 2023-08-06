package net.threetag.pantheonsent.ability;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.threetag.palladium.power.IPowerHolder;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.palladium.power.ability.AbilityEntry;
import net.threetag.palladium.power.ability.AbilityUtil;
import net.threetag.palladium.power.ability.AnimationTimer;
import net.threetag.palladium.util.property.IntegerProperty;
import net.threetag.palladium.util.property.PalladiumProperty;
import net.threetag.palladium.util.property.PropertyManager;
import net.threetag.palladium.util.property.SyncType;
import net.threetag.pantheonsent.sound.PSSoundEvents;

public class MoonKnightGlidingAbility extends Ability implements AnimationTimer {

    public static final PalladiumProperty<Integer> TIME_IN_AIR = new IntegerProperty("time_in_air").sync(SyncType.NONE);
    public static final PalladiumProperty<Integer> PREV_TIME_IN_AIR = new IntegerProperty("prev_time_in_air").sync(SyncType.NONE);

    @Override
    public void registerUniqueProperties(PropertyManager manager) {
        manager.register(TIME_IN_AIR, 0);
        manager.register(PREV_TIME_IN_AIR, 0);
    }

    @Override
    public void tick(LivingEntity entity, AbilityEntry entry, IPowerHolder holder, boolean enabled) {
        if (entity.level.isClientSide) {
            int timer = entry.getProperty(TIME_IN_AIR);
            entry.setUniqueProperty(PREV_TIME_IN_AIR, timer);

            if ((entity.isOnGround() || entity.isInWater() || entity.isColliding(entity.blockPosition(), entity.level.getBlockState(entity.blockPosition().below(2)))) && timer > 0) {
                entry.setUniqueProperty(TIME_IN_AIR, timer = timer - 1);
            } else if (enabled && !entity.isOnGround() && timer < 10) {
                entry.setUniqueProperty(TIME_IN_AIR, timer = timer + 1);
            }

            if (timer == 1) {
                entity.playSound(PSSoundEvents.CAPE.get());
            }
        }
        if (enabled && !entity.isOnGround()) {
            var lookVec = entity.getLookAngle().scale(0.1D);
            entity.setDeltaMovement(entity.getDeltaMovement().x + lookVec.x, Mth.clamp(lookVec.y, -0.8F, -0.2F), entity.getDeltaMovement().z + lookVec.z);
            entity.fallDistance = 0F;
            var movement = entity.getDeltaMovement();

            for (Entity target : entity.level.getEntities(entity, entity.getBoundingBox())) {
                target.hurt(entity instanceof Player ? DamageSource.playerAttack((Player) entity) : DamageSource.mobAttack(entity), (float) movement.length());
                if (target instanceof LivingEntity livingEntity) {
                    livingEntity.knockback(movement.x * 2.5F, movement.y * 2.5F, movement.z * 2.5F);
                }
            }
        }
    }

    @Override
    public float getAnimationValue(AbilityEntry entry, float partialTick) {
        return Mth.lerp(partialTick, entry.getProperty(PREV_TIME_IN_AIR), entry.getProperty(TIME_IN_AIR)) / 10F;
    }

    @Override
    public float getAnimationTimer(AbilityEntry entry, float partialTick, boolean maxedOut) {
        if (maxedOut) {
            return 10;
        }
        return entry.getProperty(TIME_IN_AIR);
    }

    public static float getProgress(LivingEntity entity, float partialTicks) {
        float max = 0;
        var entries = AbilityUtil.getEntries(entity, PSAbilities.MOON_KNIGHT_GLIDING.get());

        if (entries.isEmpty()) {
            return 0F;
        }

        for (AbilityEntry entry : entries) {
            float timeInAir = ((MoonKnightGlidingAbility) PSAbilities.MOON_KNIGHT_GLIDING.get()).getAnimationValue(entry, partialTicks);

            if (timeInAir > max) {
                max = timeInAir;
            }
        }

        return Mth.clamp(max, 0F, 1F);
    }
}
