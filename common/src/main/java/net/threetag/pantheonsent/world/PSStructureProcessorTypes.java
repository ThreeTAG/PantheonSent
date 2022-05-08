package net.threetag.pantheonsent.world;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.threetag.pantheonsent.PantheonSent;

public class PSStructureProcessorTypes {

    public static final DeferredRegister<StructureProcessorType<?>> PROCESSOR_TYPES = DeferredRegister.create(PantheonSent.MOD_ID, Registry.STRUCTURE_PROCESSOR_REGISTRY);

    public static final RegistrySupplier<StructureProcessorType<?>> MYSTERIOUS_BLOCKS = PROCESSOR_TYPES.register("mysterious_blocks", () -> () -> MysteriousBlocksProcessor.CODEC);

}
