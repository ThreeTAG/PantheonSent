package net.threetag.pantheonsent.block;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.item.PSLootTables;

public class PSBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(PantheonSent.MOD_ID, Registry.BLOCK_REGISTRY);

    public static final RegistrySupplier<Block> GILDED_SANDSTONE = BLOCKS.register("gilded_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).strength(1.9F, 3.4F)));
    public static final RegistrySupplier<Block> GILDED_SANDSTONE_PILLAR = BLOCKS.register("gilded_sandstone_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).strength(1.9F, 3.4F)));
    public static final RegistrySupplier<Block> ARCHEOLOGY_TABLE = BLOCKS.register("archeology_table", () -> new ArcheologyTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
    public static final RegistrySupplier<Block> KHONSHU_USHABTI = BLOCKS.register("khonshu_ushabti", () -> new UshabtiBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MYSTERIOUS_DIRT = BLOCKS.register("mysterious_dirt", () -> new BrushableBlock(Blocks.DIRT.defaultBlockState(), PSLootTables.BRUSHABLE_DIRT, BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistrySupplier<Block> MYSTERIOUS_GRAVEL = BLOCKS.register("mysterious_gravel", () -> new BrushableBlock(Blocks.GRAVEL.defaultBlockState(), PSLootTables.BRUSHABLE_GRAVEL, BlockBehaviour.Properties.copy(Blocks.GRAVEL)));
    public static final RegistrySupplier<Block> MYSTERIOUS_SAND = BLOCKS.register("mysterious_sand", () -> new FallingBrushableBlock(Blocks.SAND.defaultBlockState(), PSLootTables.BRUSHABLE_SAND, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> SANDSTONE_TOTEM_HOLDER = BLOCKS.register("sandstone_totem_holder", () -> new TotemHolderBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE).lightLevel(state -> state.getValue(TotemHolderBlock.ACTIVE) ? 7 : 0)));
    public static final RegistrySupplier<Block> LUNAR_STONE = BLOCKS.register("lunar_stone", () -> new LunarStoneBlock(BlockBehaviour.Properties.copy(Blocks.STONE).lightLevel(state -> LunarStoneBlock.getIntensity(state.getValue(LunarStoneBlock.PHASE)))));

}
