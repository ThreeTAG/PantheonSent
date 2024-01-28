package net.threetag.pantheonsent.client.particle;

import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.palladiumcore.registry.client.ParticleProviderRegistry;
import net.threetag.pantheonsent.PantheonSent;

import java.util.function.Supplier;

public class PSParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(PantheonSent.MOD_ID, Registries.PARTICLE_TYPE);

    public static final RegistrySupplier<SimpleParticleType> HIEROGLYPH = PARTICLE_TYPES.register("hieroglyph", () -> new SimpleParticleType(false));

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    public static void initProviders() {
        Supplier supplier = HIEROGLYPH;
        ParticleEngine.SpriteParticleRegistration provider = HieroglyphParticle.Provider::new;
        ParticleProviderRegistry.register(supplier, provider);
    }

}
