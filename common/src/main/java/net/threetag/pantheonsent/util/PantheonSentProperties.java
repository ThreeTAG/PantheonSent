package net.threetag.pantheonsent.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.Entity;
import net.threetag.palladium.event.PalladiumEvents;
import net.threetag.palladium.power.ability.AbilityUtil;
import net.threetag.palladium.util.property.IntegerProperty;
import net.threetag.palladium.util.property.PalladiumProperty;
import net.threetag.palladium.util.property.SyncType;
import net.threetag.palladiumcore.event.InputEvents;
import net.threetag.palladiumcore.event.LivingEntityEvents;
import net.threetag.pantheonsent.ability.PSAbilities;
import net.threetag.pantheonsent.entity.Khonshu;

public class PantheonSentProperties {

    public static final PalladiumProperty<Integer> KHONSHU_RECRUITING_TIMER = new IntegerProperty("khonshu_recruiting_timer").sync(SyncType.EVERYONE);

    public static void init() {
        PalladiumEvents.REGISTER_PROPERTY.register(handler -> {
            handler.register(KHONSHU_RECRUITING_TIMER, 0);
        });

        LivingEntityEvents.TICK.register(entity -> {
            if (KHONSHU_RECRUITING_TIMER.isRegistered(entity) && KHONSHU_RECRUITING_TIMER.get(entity) > 0) {
                for (Entity en : entity.level().getEntities(entity, entity.getBoundingBox().inflate(30), entity1 -> entity1 instanceof Khonshu)) {
                    if (en instanceof Khonshu khonshu && khonshu.avatarId != null && khonshu.avatarId.equals(entity.getUUID())) {
                        return;
                    }
                }
                KHONSHU_RECRUITING_TIMER.set(entity, 0);
            }
        });
    }

    @Environment(EnvType.CLIENT)
    public static void initClient() {
        InputEvents.MOVEMENT_INPUT_UPDATE.register((player, input) -> {
            if (
                    (KHONSHU_RECRUITING_TIMER.isRegistered(player) && KHONSHU_RECRUITING_TIMER.get(player) > 0)
                            || !AbilityUtil.getEnabledEntries(player, PSAbilities.MOON_KNIGHT_BLOCKING.get()).isEmpty()
            ) {
                input.up = input.down = input.left = input.right = input.jumping = input.shiftKeyDown = false;
                input.forwardImpulse = input.leftImpulse = 0F;
            }
        });
    }

}
