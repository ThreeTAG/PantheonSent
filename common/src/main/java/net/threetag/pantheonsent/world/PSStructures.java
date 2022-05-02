package net.threetag.pantheonsent.world;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.mixin.StructureFeatureAccessor;

public class PSStructures {

    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(PantheonSent.MOD_ID, Registry.STRUCTURE_FEATURE_REGISTRY);

    public static final RegistrySupplier<StructureFeature<JigsawConfiguration>> KHONSHU_TEMPLE = STRUCTURES.register("khonshu_temple", KhonshuTempleStructure::new);

    public static void registerStructureFeatures() {
        StructureFeatureAccessor.getSteps().put(KHONSHU_TEMPLE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES);
    }

}
