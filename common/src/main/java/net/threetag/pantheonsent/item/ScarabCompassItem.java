package net.threetag.pantheonsent.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.phys.Vec3;
import net.threetag.pantheonsent.PantheonSent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ScarabCompassItem extends Item {

    public ScarabCompassItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if (!stack.getOrCreateTag().contains("TargetPos") && level instanceof ServerLevel serverLevel) {
            Registry<Structure> registry = serverLevel.registryAccess().registryOrThrow(Registries.STRUCTURE);
            var structure = registry.get(PantheonSent.id("khonshu_temple"));

            if (structure != null) {
                var pos = serverLevel.getChunkSource().getGenerator().findNearestMapStructure(serverLevel, HolderSet.direct(Holder.direct(structure)), player.blockPosition(), 100, false);
                stack.getOrCreateTag().put("TargetPos", NbtUtils.writeBlockPos(Objects.requireNonNull(pos).getFirst()));
            }

            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }

        return InteractionResultHolder.pass(stack);
    }

    @Environment(EnvType.CLIENT)
    public static class PropertyFunction implements ClampedItemPropertyFunction {

        private final CompassWobble wobble = new CompassWobble();
        private final CompassWobble wobbleRandom = new CompassWobble();

        @Override
        public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i) {
            Entity entity = livingEntity != null ? livingEntity : itemStack.getEntityRepresentation();
            if (entity == null) {
                return 0.0F;
            } else {
                if (clientLevel == null && entity.level() instanceof ClientLevel) {
                    clientLevel = (ClientLevel) entity.level();
                }

                BlockPos blockPos = itemStack.getOrCreateTag().contains("TargetPos") ? NbtUtils.readBlockPos(itemStack.getOrCreateTag().getCompound("TargetPos")) : null;

                if (blockPos == null || clientLevel == null) {
                    return -1F;
                }

                long l = clientLevel.getGameTime();
                if (!(entity.position().distanceToSqr((double) blockPos.getX() + 0.5, entity.position().y(), (double) blockPos.getZ() + 0.5) < 9.999999747378752E-6)) {
                    boolean bl = livingEntity instanceof Player && ((Player) livingEntity).isLocalPlayer();
                    double e = 0.0;
                    if (bl) {
                        e = livingEntity.getYRot();
                    } else if (entity instanceof ItemFrame) {
                        e = this.getFrameRotation((ItemFrame) entity);
                    } else if (entity instanceof ItemEntity) {
                        e = 180.0F - ((ItemEntity) entity).getSpin(0.5F) / 6.2831855F * 360.0F;
                    } else if (livingEntity != null) {
                        e = livingEntity.yBodyRot;
                    }

                    e = Mth.positiveModulo(e / 360.0, 1.0);
                    double f = this.getAngleTo(Vec3.atCenterOf(blockPos), entity) / 6.2831854820251465;
                    double g;
                    if (bl) {
                        if (this.wobble.shouldUpdate(l)) {
                            this.wobble.update(l, 0.5 - (e - 0.25));
                        }

                        g = f + this.wobble.rotation;
                    } else {
                        g = 0.5 - (e - 0.25 - f);
                    }

                    return Mth.positiveModulo((float) g, 1.0F);
                } else {
                    if (this.wobbleRandom.shouldUpdate(l)) {
                        this.wobbleRandom.update(l, Math.random());
                    }

                    double d = this.wobbleRandom.rotation + (double) ((float) this.hash(i) / 2.14748365E9F);
                    return Mth.positiveModulo((float) d, 1.0F);
                }
            }
        }

        private int hash(int seed) {
            return seed * 1327217883;
        }

        private double getFrameRotation(ItemFrame frame) {
            Direction direction = frame.getDirection();
            int i = direction.getAxis().isVertical() ? 90 * direction.getAxisDirection().getStep() : 0;
            return Mth.wrapDegrees(180 + direction.get2DDataValue() * 90 + frame.getRotation() * 45 + i);
        }

        private double getAngleTo(Vec3 position, Entity entity) {
            return Math.atan2(position.z() - entity.getZ(), position.x() - entity.getX());
        }
    }

    @Environment(EnvType.CLIENT)
    private static class CompassWobble {
        double rotation;
        private double deltaRotation;
        private long lastUpdateTick;

        CompassWobble() {
        }

        boolean shouldUpdate(long gameTime) {
            return this.lastUpdateTick != gameTime;
        }

        void update(long gameTime, double wobbleAmount) {
            this.lastUpdateTick = gameTime;
            double d = wobbleAmount - this.rotation;
            d = Mth.positiveModulo(d + 0.5, 1.0) - 0.5;
            this.deltaRotation += d * 0.1;
            this.deltaRotation *= 0.8;
            this.rotation = Mth.positiveModulo(this.rotation + this.deltaRotation, 1.0);
        }
    }

}
