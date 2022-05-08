package net.threetag.pantheonsent.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.state.BlockState;

public class FallingBrushableBlock extends BrushableBlock implements Fallable {

    public FallingBrushableBlock(BlockState baseState, ResourceLocation lootTable, Properties properties) {
        super(baseState, lootTable, properties);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        level.scheduleTick(pos, this, this.getDelayAfterPlace());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        level.scheduleTick(currentPos, this, this.getDelayAfterPlace());
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    protected int getDelayAfterPlace() {
        return 2;
    }
}
