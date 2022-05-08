package net.threetag.pantheonsent.world;

import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.threetag.pantheonsent.block.PSBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Random;

public class MysteriousBlocksProcessor extends StructureProcessor {

    public static final MysteriousBlocksProcessor INSTANCE = new MysteriousBlocksProcessor();
    public static final Codec<StructureProcessor> CODEC = Codec.unit(() -> INSTANCE);

    public static final Map<Block, Block> REPLACEMENTS = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(Blocks.DIRT, PSBlocks.MYSTERIOUS_DIRT.get());
        hashMap.put(Blocks.GRAVEL, PSBlocks.MYSTERIOUS_GRAVEL.get());
        hashMap.put(Blocks.SAND, PSBlocks.MYSTERIOUS_SAND.get());
    });

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos blockPos, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfo, StructureTemplate.StructureBlockInfo relativeBlockInfo, StructurePlaceSettings settings) {
        if(relativeBlockInfo.state.is(Blocks.SPONGE)) {
            BlockState origBlock = level.getBlockState(relativeBlockInfo.pos);
            Block block = REPLACEMENTS.get(origBlock.getBlock());
            Random random = settings.getRandom(relativeBlockInfo.pos);

            if (block == null || random.nextInt(3) != 0) {
                return new StructureTemplate.StructureBlockInfo(relativeBlockInfo.pos, origBlock, null);
            } else {
                BlockState blockState2 = block.defaultBlockState();

                return new StructureTemplate.StructureBlockInfo(relativeBlockInfo.pos, blockState2, relativeBlockInfo.nbt);
            }
        } else {
            return relativeBlockInfo;
        }
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return PSStructureProcessorTypes.MYSTERIOUS_BLOCKS.get();
    }
}
