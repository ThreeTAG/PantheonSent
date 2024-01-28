package net.threetag.pantheonsent.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;

public class PSBannerPatterns {

    public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(PantheonSent.MOD_ID, Registries.BANNER_PATTERN);

    public static final RegistrySupplier<BannerPattern> CRESCENT = create("crescent");

    private static RegistrySupplier<BannerPattern> create(String id) {
        return BANNER_PATTERNS.register(id, () -> new BannerPattern(PantheonSent.MOD_ID + "/" + id));
    }

}
