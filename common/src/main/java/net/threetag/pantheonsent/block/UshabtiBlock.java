package net.threetag.pantheonsent.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.threetag.pantheonsent.ability.PSAbilities;
import net.threetag.pantheonsent.block.entity.PSBlockEntityTypes;
import net.threetag.pantheonsent.block.entity.UshabtiBlockEntity;
import net.threetag.pantheonsent.entity.Khonshu;
import net.threetag.pantheonsent.item.enchantment.PSEnchantments;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class UshabtiBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {

    public static final VoxelShape SHAPE = Block.box(5, 0, 5, 11, 16, 11);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty USED = BooleanProperty.create("used");

    public UshabtiBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.WATERLOGGED, false).setValue(FACING, Direction.NORTH).setValue(USED, false));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        boolean bl = fluidState.getType() == Fluids.WATER;
        return Objects.requireNonNull(super.getStateForPlacement(context)).setValue(BlockStateProperties.WATERLOGGED, bl).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(USED, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, BlockStateProperties.WATERLOGGED, USED);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (!level.isClientSide && EnchantmentHelper.getItemEnchantmentLevel(PSEnchantments.GODLY_ENCAPSULATING.get(), stack) > 0 && placer instanceof Player player && PSAbilities.hasMoonKnightPower(player)) {
            level.setBlock(pos, state.setValue(USED, true), 3);

            if (level.getBlockEntity(pos) instanceof UshabtiBlockEntity ushabti) {
                ushabti.owner = player.getUUID();
                ushabti.progress = 100;
            }
        }
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerDestroy(level, player, pos, state, blockEntity, tool);

        if (state.getValue(USED) && blockEntity instanceof UshabtiBlockEntity ushabti && ushabti.owner != null && ushabti.owner.equals(player.getUUID())) {
            Khonshu khonshu = new Khonshu(level, player, Khonshu.Mode.RECRUITING);
            var entityPos = Khonshu.findRandomPos(player.getOnPos(), khonshu, player, level, 3, 7, 7);
            khonshu.setPos(entityPos.x, entityPos.y, entityPos.z);
            level.addFreshEntity(khonshu);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new UshabtiBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, PSBlockEntityTypes.USHABTI.get(), UshabtiBlockEntity::serverTick);
    }
}
