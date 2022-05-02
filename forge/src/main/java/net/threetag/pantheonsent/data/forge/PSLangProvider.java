package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.threetag.pantheonsent.PantheonSent;

public abstract class PSLangProvider extends LanguageProvider {

    public PSLangProvider(DataGenerator gen, String locale) {
        super(gen, PantheonSent.MOD_ID, locale);
    }

    public static class English extends PSLangProvider {

        public English(DataGenerator gen) {
            super(gen, "en_us");
        }

        @Override
        protected void addTranslations() {
            // Powers
            this.add("power.pantheonsent.moon_knight", "Moon Knight");
        }
    }

    public static class German extends PSLangProvider {

        public German(DataGenerator gen) {
            super(gen, "de_de");
        }

        @Override
        protected void addTranslations() {
            // Powers
            this.add("power.pantheonsent.moon_knight", "Moon Knight");
        }
    }

    public static class Saxon extends PSLangProvider {

        public Saxon(DataGenerator gen) {
            super(gen, "sxu");
        }

        @Override
        protected void addTranslations() {
            // Powers
            this.add("power.pantheonsent.moon_knight", "Moon Knight");
        }
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
