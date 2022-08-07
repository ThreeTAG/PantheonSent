package net.threetag.pantheonsent.ability;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientRawInputEvent;
import dev.architectury.event.events.common.EntityEvent;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.threetag.palladium.power.IPowerHolder;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.palladium.power.ability.AbilityEntry;
import net.threetag.palladium.util.property.IntegerProperty;
import net.threetag.palladium.util.property.PalladiumProperty;
import net.threetag.palladium.util.property.PropertyManager;
import net.threetag.palladium.util.property.SyncType;

public class MoonKnightBlockingAbility extends Ability implements EntityEvent.LivingHurt {

    public static final PalladiumProperty<Integer> TIMER = new IntegerProperty("timer").sync(SyncType.NONE);
    public static final PalladiumProperty<Integer> PREV_TIMER = new IntegerProperty("prev_timer").sync(SyncType.NONE);

    public MoonKnightBlockingAbility() {
        EntityEvent.LIVING_HURT.register(this);
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
    public EventResult hurt(LivingEntity entity, DamageSource source, float amount) {
        if(!Ability.getEnabledEntries(entity, PSAbilities.MOON_KNIGHT_BLOCKING.get()).isEmpty()) {
            return EventResult.interruptFalse();
        }

        if(source.getDirectEntity() instanceof LivingEntity livingEntity) {
            if(!Ability.getEnabledEntries(livingEntity, PSAbilities.MOON_KNIGHT_BLOCKING.get()).isEmpty()) {
                return EventResult.interruptFalse();
            }
        }

        return EventResult.pass();
    }
}
