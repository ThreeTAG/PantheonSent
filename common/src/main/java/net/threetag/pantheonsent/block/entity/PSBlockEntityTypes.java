package net.threetag.pantheonsent.block.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.PSBlocks;

public class PSBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(PantheonSent.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<LunarStoneBlockEntity>> LUNAR_STONE = BLOCK_ENTITIES.register("lunar_stone", () -> BlockEntityType.Builder.of(LunarStoneBlockEntity::new, PSBlocks.LUNAR_STONE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<UshabtiBlockEntity>> USHABTI = BLOCK_ENTITIES.register("ushabti", () -> BlockEntityType.Builder.of(UshabtiBlockEntity::new, PSBlocks.KHONSHU_USHABTI.get()).build(null));

}
