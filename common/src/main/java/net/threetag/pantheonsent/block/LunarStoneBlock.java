package net.threetag.pantheonsent.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.threetag.pantheonsent.block.entity.LunarStoneBlockEntity;
import net.threetag.pantheonsent.block.entity.PSBlockEntityTypes;
import org.jetbrains.annotations.Nullable;

public class LunarStoneBlock extends BaseEntityBlock {

    public static final IntegerProperty PHASE = IntegerProperty.create("phase", 0, 7);

    public LunarStoneBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(PHASE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PHASE);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(PHASE, context.getLevel().getMoonPhase());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LunarStoneBlockEntity(pos, state);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return getIntensity(state.getValue(PHASE));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return !level.isClientSide && level.dimensionType().hasSkyLight() ? createTickerHelper(blockEntityType, PSBlockEntityTypes.LUNAR_STONE.get(), LunarStoneBlock::tickEntity) : null;
    }

    private static void tickEntity(Level level, BlockPos pos, BlockState state, LunarStoneBlockEntity blockEntity) {
        if (level.getGameTime() % 100L == 0L) {
            int phase = level.getMoonPhase();
            if (state.getValue(PHASE) != phase) {
                level.setBlock(pos, state.setValue(PHASE, phase), 3);
            }
        }
    }

    public static int getIntensity(int phase) {
        if (phase == 0) {
            return 8;
        } else if (phase == 1 || phase == 7) {
            return 6;
        } else if (phase == 2 || phase == 6) {
            return 4;
        } else if (phase == 3 || phase == 5) {
            return 2;
        } else {
            return 0;
        }
    }

}
