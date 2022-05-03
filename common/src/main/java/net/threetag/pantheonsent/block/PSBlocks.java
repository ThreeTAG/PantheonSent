package net.threetag.pantheonsent.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.threetag.pantheonsent.PantheonSent;

public class PSBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(PantheonSent.MOD_ID, Registry.BLOCK_REGISTRY);

    public static final RegistrySupplier<Block> SANDSTONE_TOTEM_HOLDER = BLOCKS.register("sandstone_totem_holder", () -> new TotemHolderBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE).lightLevel(state -> state.getValue(TotemHolderBlock.ACTIVE) ? 7 : 0)));
    public static final RegistrySupplier<Block> LUNAR_STONE = BLOCKS.register("lunar_stone", () -> new LunarStoneBlock(BlockBehaviour.Properties.copy(Blocks.STONE).lightLevel(state -> LunarStoneBlock.getIntensity(state.getValue(LunarStoneBlock.PHASE)))));

}
