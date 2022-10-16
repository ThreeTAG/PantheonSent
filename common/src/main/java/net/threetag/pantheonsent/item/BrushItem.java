package net.threetag.pantheonsent.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.threetag.pantheonsent.block.BrushableBlock;
import net.threetag.pantheonsent.block.entity.BrushableBlockEntity;

public class BrushItem extends Item {

    public BrushItem(Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.CROSSBOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 16 * 20;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Block block = context.getLevel().getBlockState(context.getClickedPos()).getBlock();
        if (!context.getLevel().isClientSide && context.getPlayer() != null && block instanceof BrushableBlock) {
            context.getPlayer().startUsingItem(context.getHand());
        }

        return InteractionResult.sidedSuccess(context.getLevel().isClientSide);
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if (!level.isClientSide) {
            var clip = level.clip(new ClipContext(livingEntity.getEyePosition(), livingEntity.getEyePosition().add(livingEntity.getLookAngle().scale(4)), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, livingEntity));
            var pos = clip.getBlockPos();
            var state = level.getBlockState(pos);

            if (livingEntity.tickCount % 20 == 0) {
                if (level.getBlockEntity(pos) instanceof BrushableBlockEntity blockEntity && blockEntity.getBlockState().getBlock() instanceof BrushableBlock brushableBlock) {
                    int stage = state.getValue(BrushableBlock.STAGE);

                    if (blockEntity.lootTable == null && stage == 0) {
                        blockEntity.lootTable = brushableBlock.lootTable;
                    }

                    blockEntity.unpackLootTable(livingEntity instanceof Player player ? player : null);
                    blockEntity.setLootTable(null, 0);

                    if (stage == 3) {
                        level.destroyBlock(pos, false, livingEntity);
                        if (!blockEntity.getItem().isEmpty()) {
                            level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, blockEntity.getItem()));
                        }
                    } else {
                        level.setBlock(pos, state.setValue(BrushableBlock.STAGE, stage + 1), 3);
                        level.levelEvent(2001, pos, Block.BLOCK_STATE_REGISTRY.getId(state));
                    }
                } else {
                    livingEntity.stopUsingItem();
                }
            }
        }
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return 1;
    }
}
