package net.threetag.pantheonsent.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;

public class PSStructureProcessorTypes {

    public static final DeferredRegister<StructureProcessorType<?>> PROCESSOR_TYPES = DeferredRegister.create(PantheonSent.MOD_ID, Registries.STRUCTURE_PROCESSOR);

    public static final RegistrySupplier<StructureProcessorType<?>> SUSPICIOUS_BLOCKS = PROCESSOR_TYPES.register("suspicious_blocks", () -> () -> SuspiciousBlocksProcessor.CODEC);

}
