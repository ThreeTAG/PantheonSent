package net.threetag.pantheonsent.ability;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.pantheonsent.PantheonSent;

public class PSAbilities {

    public static final DeferredRegister<Ability> ABILITIES = DeferredRegister.create(PantheonSent.MOD_ID, Ability.RESOURCE_KEY);

    public static final RegistrySupplier<Ability> MOON_KNIGHT_GLIDING = ABILITIES.register("moon_knight_gliding", MoonKnightGlidingAbility::new);
    public static final RegistrySupplier<Ability> MOON_KNIGHT_BLOCKING = ABILITIES.register("moon_knight_blocking", MoonKnightBlockingAbility::new);

}
