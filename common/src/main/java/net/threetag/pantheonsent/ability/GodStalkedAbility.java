package net.threetag.pantheonsent.ability;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.threetag.palladium.power.IPowerHolder;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.palladium.power.ability.AbilityEntry;
import net.threetag.palladium.util.property.IntegerProperty;
import net.threetag.palladium.util.property.PalladiumProperty;
import net.threetag.palladium.util.property.PropertyManager;
import net.threetag.palladium.util.property.SyncType;
import net.threetag.pantheonsent.entity.Khonshu;

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
                var pos = getRandomPos(entity.getOnPos(), khonshu, player, entity.level, 20, 35, 10);

                if (pos != null) {
                    khonshu.setPos(new Vec3(pos.getX(), pos.getY(), pos.getZ()));
                    entity.level.addFreshEntity(khonshu);
                    ((Player) entity).displayClientMessage(Component.literal("hallo khonshu! " + pos.toShortString()), false);
                }

                entry.setOwnProperty(TIMER, getRandomMinutes());
            } else {
                entry.setOwnProperty(TIMER, timer - 1);
            }
        }
    }

    public int getRandomMinutes() {
        return 20 * 30;
//        return (10 + new Random().nextInt(15)) * 60 * 20;
    }

    public BlockPos getRandomPos(BlockPos center, Khonshu khonshu, Player player, Level level, int minRange, int maxRange, int attempt) {
        if (attempt == 0) {
            return null;
        } else {
            RandomSource rand = RandomSource.create();
            int x = (int) (center.getX() + (minRange + (maxRange - minRange) * rand.nextFloat()) * (rand.nextBoolean() ? 1F : -1F));
            int y = center.getY() + 20;
            int z = (int) (center.getZ() + (minRange + (maxRange - minRange) * rand.nextFloat()) * (rand.nextBoolean() ? 1F : -1F));

            for (int i = y; i > center.getY() - 20; i--) {
                BlockPos pos = new BlockPos(x, i, z);
                if (!level.isEmptyBlock(pos.below()) && level.isEmptyBlock(pos) && level.isEmptyBlock(pos.above())) {
                    khonshu.setPos(new Vec3(pos.getX(), pos.getY(), pos.getZ()));

                    if(khonshu.hasLineOfSight(player)) {
                        return pos;
                    }
                }
            }

            return getRandomPos(center, khonshu, player, level, minRange, maxRange, attempt - 1);
        }
    }

}
