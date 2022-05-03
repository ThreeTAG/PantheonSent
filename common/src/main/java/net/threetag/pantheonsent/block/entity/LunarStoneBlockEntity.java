package net.threetag.pantheonsent.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LunarStoneBlockEntity extends BlockEntity {

    public LunarStoneBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(PSBlockEntityTypes.LUNAR_STONE.get(), blockPos, blockState);
    }

}
