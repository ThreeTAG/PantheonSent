package net.threetag.pantheonsent.world;

import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SuspiciousBlocksProcessor extends StructureProcessor {

    public static final SuspiciousBlocksProcessor INSTANCE = new SuspiciousBlocksProcessor();
    public static final Codec<StructureProcessor> CODEC = Codec.unit(() -> INSTANCE);

    public static final Map<Block, Block> REPLACEMENTS = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(Blocks.SAND, Blocks.SUSPICIOUS_SAND);
    });

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos blockPos, BlockPos pos, StructureTemplate.StructureBlockInfo blockInfo, StructureTemplate.StructureBlockInfo relativeBlockInfo, StructurePlaceSettings settings) {
        if(relativeBlockInfo.state().is(Blocks.SPONGE)) {
            BlockState origBlock = level.getBlockState(relativeBlockInfo.pos());
            Block block = REPLACEMENTS.get(origBlock.getBlock());
            RandomSource random = settings.getRandom(relativeBlockInfo.pos());

            if (block == null || random.nextInt(3) != 0) {
                return new StructureTemplate.StructureBlockInfo(relativeBlockInfo.pos(), origBlock, null);
            } else {
                BlockState blockState2 = block.defaultBlockState();

                return new StructureTemplate.StructureBlockInfo(relativeBlockInfo.pos(), blockState2, relativeBlockInfo.nbt());
            }
        } else {
            return relativeBlockInfo;
        }
    }

    @Override
    protected @NotNull StructureProcessorType<?> getType() {
        return PSStructureProcessorTypes.SUSPICIOUS_BLOCKS.get();
    }
}
