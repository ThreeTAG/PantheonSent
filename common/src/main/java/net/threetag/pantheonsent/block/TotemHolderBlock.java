package net.threetag.pantheonsent.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.threetag.pantheonsent.item.PSItems;

public class TotemHolderBlock extends Block {

    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public TotemHolderBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);

        if (!state.getValue(ACTIVE) && stack.getItem() == PSItems.LUNAR_TOTEM.get()) {
            level.setBlock(pos, state.setValue(ACTIVE, true), 2);
            stack.shrink(1);
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else if (state.getValue(ACTIVE)) {
            level.setBlock(pos, state.setValue(ACTIVE, false), 2);
            player.getInventory().add(new ItemStack(PSItems.LUNAR_TOTEM.get()));
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

}
