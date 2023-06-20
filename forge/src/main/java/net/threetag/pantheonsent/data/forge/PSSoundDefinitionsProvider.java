package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.sound.PSSoundEvents;

public class PSSoundDefinitionsProvider extends SoundDefinitionsProvider {

    public PSSoundDefinitionsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, PantheonSent.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        this.add(PSSoundEvents.CAPE, definition().with(
                sound(PantheonSent.id("cape_1")),
                sound(PantheonSent.id("cape_2")),
                sound(PantheonSent.id("cape_3")),
                sound(PantheonSent.id("cape_4")),
                sound(PantheonSent.id("cape_5")),
                sound(PantheonSent.id("cape_6")),
                sound(PantheonSent.id("cape_7")),
                sound(PantheonSent.id("cape_8"))
        ).subtitle(subtitle(PSSoundEvents.CAPE)));
        this.add(PSSoundEvents.EYE_OF_HORUS, definition().with(sound(PantheonSent.id("eye_of_horus"))).subtitle(subtitle(PSSoundEvents.EYE_OF_HORUS)));
        this.add(PSSoundEvents.MUSIC_DISC_CHONS, definition().with(sound(PantheonSent.id("music_disc_chons")).stream()));
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }

    public static String subtitle(RegistrySupplier<SoundEvent> supplier) {
        return "subtitles." + PantheonSent.MOD_ID + "." + supplier.getId().getPath();
    }
}
