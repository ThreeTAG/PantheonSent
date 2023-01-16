package net.threetag.pantheonsent.ability;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.threetag.palladium.power.IPowerHolder;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.palladium.power.ability.AbilityEntry;
import net.threetag.palladium.util.property.IntegerProperty;
import net.threetag.palladium.util.property.PalladiumProperty;
import net.threetag.palladium.util.property.PropertyManager;
import net.threetag.palladium.util.property.SyncType;
import net.threetag.pantheonsent.entity.Khonshu;

import java.util.Random;

public class GodStalkedAbility extends Ability {

    public static final PalladiumProperty<Integer> TIMER = new IntegerProperty("timer").sync(SyncType.NONE);

    @Override
    public boolean isEffect() {
        return true;
    }

    @Override
    public void registerUniqueProperties(PropertyManager manager) {
        manager.register(TIMER, getRandomMinutes());
    }

    @Override
    public void tick(LivingEntity entity, AbilityEntry entry, IPowerHolder holder, boolean enabled) {
        if (enabled && !entity.level.isClientSide && entity instanceof Player player) {
            int timer = entry.getProperty(TIMER);

            if (timer == 0) {
                Khonshu khonshu = new Khonshu(entity.level, player, Khonshu.Mode.STALKING);
                var pos = Khonshu.findRandomPos(entity.getOnPos(), khonshu, player, entity.level, 20, 35, 20);
                khonshu.setPos(new Vec3(pos.x(), pos.y(), pos.z()));
                entity.level.addFreshEntity(khonshu);
                player.playSound(SoundEvents.AMBIENT_CAVE, 1F, 1F);
                entry.setUniqueProperty(TIMER, getRandomMinutes());
            } else {
                entry.setUniqueProperty(TIMER, timer - 1);
            }
        }
    }

    public int getRandomMinutes() {
        // TODO config/ability option?
        return (10 + new Random().nextInt(15)) * 60 * 20;
    }

}
