package net.threetag.pantheonsent.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.block.entity.SuspiciousSandBlockEntity;

public class BrushItem extends Item {

    public BrushItem(Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.CROSSBOW;
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Player player = useOnContext.getPlayer();
        if (player != null) {
            player.startUsingItem(useOnContext.getHand());
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 225;
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int i) {
        if (i >= 0 && livingEntity instanceof Player player) {
            BlockHitResult blockHitResult = Item.getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
            BlockPos blockPos = blockHitResult.getBlockPos();
            if (blockHitResult.getType() == HitResult.Type.MISS) {
                livingEntity.releaseUsingItem();
            } else {
                int j = this.getUseDuration(itemStack) - i + 1;
                if (j == 1 || j % 10 == 0) {
                    BlockState blockState = level.getBlockState(blockPos);
                    this.spawnDustParticles(level, blockHitResult, blockState, livingEntity.getViewVector(0.0F));
//                    level.playSound(player, blockPos, SoundEvents.BRUSH_BRUSHING, SoundSource.PLAYERS);
                    if (!level.isClientSide() && blockState.is(PSBlocks.SUSPICIOUS_SAND.get())) {
                        BlockEntity var11 = level.getBlockEntity(blockPos);
                        if (var11 instanceof SuspiciousSandBlockEntity) {
                            SuspiciousSandBlockEntity suspiciousSandBlockEntity = (SuspiciousSandBlockEntity) var11;
                            boolean bl = suspiciousSandBlockEntity.brush(level.getGameTime(), player, blockHitResult.getDirection());
                            if (bl) {
                                itemStack.hurtAndBreak(1, livingEntity, (livingEntityx) -> {
                                    livingEntityx.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                                });
                            }
                        }
                    }
                }

            }
        } else {
            livingEntity.releaseUsingItem();
        }
    }

    public void spawnDustParticles(Level level, BlockHitResult blockHitResult, BlockState blockState, Vec3 vec3) {
        double d = 3.0;
        int i = level.getRandom().nextInt(7, 12);
        BlockParticleOption blockParticleOption = new BlockParticleOption(ParticleTypes.BLOCK, blockState);
        Direction direction = blockHitResult.getDirection();
        DustParticlesDelta dustParticlesDelta = BrushItem.DustParticlesDelta.fromDirection(vec3, direction);
        Vec3 vec32 = blockHitResult.getLocation();

        for (int j = 0; j < i; ++j) {
            level.addParticle(blockParticleOption, vec32.x - (double) (direction == Direction.WEST ? 1.0E-6F : 0.0F), vec32.y, vec32.z - (double) (direction == Direction.NORTH ? 1.0E-6F : 0.0F), dustParticlesDelta.xd() * 3.0 * level.getRandom().nextDouble(), 0.0, dustParticlesDelta.zd() * 3.0 * level.getRandom().nextDouble());
        }

    }

    record DustParticlesDelta(double xd, double yd, double zd) {

        public static DustParticlesDelta fromDirection(Vec3 vec3, Direction direction) {
            DustParticlesDelta var10000;
            switch (direction) {
                case DOWN:
                    var10000 = new DustParticlesDelta(-vec3.x(), 0.0, vec3.z());
                    break;
                case UP:
                    var10000 = new DustParticlesDelta(vec3.z(), 0.0, -vec3.x());
                    break;
                case NORTH:
                    var10000 = new DustParticlesDelta(1.0, 0.0, -0.1);
                    break;
                case SOUTH:
                    var10000 = new DustParticlesDelta(-1.0, 0.0, 0.1);
                    break;
                case WEST:
                    var10000 = new DustParticlesDelta(-0.1, 0.0, -1.0);
                    break;
                case EAST:
                    var10000 = new DustParticlesDelta(0.1, 0.0, 1.0);
                    break;
                default:
                    throw new IncompatibleClassChangeError();
            }

            return var10000;
        }
    }
}
