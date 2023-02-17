package net.threetag.pantheonsent.block;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;

public class PSBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(PantheonSent.MOD_ID, Registry.BLOCK_REGISTRY);

    public static final RegistrySupplier<Block> GILDED_SANDSTONE = BLOCKS.register("gilded_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).strength(1.9F, 3.4F)));
    public static final RegistrySupplier<Block> GILDED_SANDSTONE_PILLAR = BLOCKS.register("gilded_sandstone_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).strength(1.9F, 3.4F)));
    public static final RegistrySupplier<Block> ARCHEOLOGY_TABLE = BLOCKS.register("archeology_table", () -> new ArcheologyTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
    public static final RegistrySupplier<Block> BROKEN_KHONSHU_USHABTI = BLOCKS.register("broken_khonshu_ushabti", () -> new BrokenUshabtiBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> KHONSHU_USHABTI = BLOCKS.register("khonshu_ushabti", () -> new UshabtiBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> SUSPICIOUS_SAND = BLOCKS.register("suspicious_sand", () -> new SuspiciousSandBlock(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND).strength(0.25F).sound(SoundType.SAND)));
    public static final RegistrySupplier<Block> SANDSTONE_TOTEM_HOLDER = BLOCKS.register("sandstone_totem_holder", () -> new TotemHolderBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE).lightLevel(state -> state.getValue(TotemHolderBlock.ACTIVE) ? 7 : 0)));
    public static final RegistrySupplier<Block> LUNAR_STONE = BLOCKS.register("lunar_stone", () -> new LunarStoneBlock(BlockBehaviour.Properties.copy(Blocks.STONE).lightLevel(state -> LunarStoneBlock.getIntensity(state.getValue(LunarStoneBlock.PHASE)))));

}
