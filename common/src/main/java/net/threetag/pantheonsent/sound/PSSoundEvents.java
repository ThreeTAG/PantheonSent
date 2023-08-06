package net.threetag.pantheonsent.sound;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;

public class PSSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(PantheonSent.MOD_ID, Registry.SOUND_EVENT_REGISTRY);

    public static final RegistrySupplier<SoundEvent> CAPE = make("entity.cape");
    public static final RegistrySupplier<SoundEvent> EYE_OF_HORUS = make("item.eye_of_horus.use");
    public static final RegistrySupplier<SoundEvent> MUSIC_DISC_CHONS = make("music_disc.chons");
    public static final RegistrySupplier<SoundEvent> MOON_KNIGHT_TRANSFORMATION = make("entity.moon_knight_transformation");
    public static final RegistrySupplier<SoundEvent> KHONSHU_CAPTURED = make("block.khonshu_captured");

    public static RegistrySupplier<SoundEvent> make(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(PantheonSent.MOD_ID, name)));
    }

}
