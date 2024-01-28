package net.threetag.pantheonsent.ability;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.threetag.palladium.power.Power;
import net.threetag.palladium.power.PowerManager;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;

public class PSAbilities {

    public static final DeferredRegister<Ability> ABILITIES = DeferredRegister.create(PantheonSent.MOD_ID, Ability.REGISTRY);

    public static final RegistrySupplier<Ability> MOON_KNIGHT_GLIDING = ABILITIES.register("moon_knight_gliding", MoonKnightGlidingAbility::new);
    public static final RegistrySupplier<Ability> MOON_KNIGHT_BLOCKING = ABILITIES.register("moon_knight_blocking", MoonKnightBlockingAbility::new);
    public static final RegistrySupplier<Ability> GOD_STALKED = ABILITIES.register("god_stalked", GodStalkedAbility::new);

    public static Power getMoonKnightPower(Level level) {
        return PowerManager.getInstance(level).getPower(PantheonSent.id("moon_knight"));
    }

    public static boolean hasMoonKnightPower(Player player) {
        var power = getMoonKnightPower(player.level());

        if (power == null) {
            return false;
        }

        var handler = PowerManager.getPowerHandler(player);
        return handler.map(powerHandler -> powerHandler.hasPower(power)).orElse(false);
    }

}
