package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.item.PSItems;

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
            // Blocks
            this.addBlock(PSBlocks.MYSTERIOUS_DIRT, "Mysterious Dirt");
            this.addBlock(PSBlocks.MYSTERIOUS_GRAVEL, "Mysterious Gravel");
            this.addBlock(PSBlocks.MYSTERIOUS_SAND, "Mysterious Sand");
            this.addBlock(PSBlocks.SANDSTONE_TOTEM_HOLDER, "Sandstone Totem Holder");
            this.addBlock(PSBlocks.LUNAR_STONE, "Lunar Stone");

            // Items
            this.addItem(PSItems.BRUSH, "Brush");
            this.addItem(PSItems.LUNAR_TOTEM, "Lunar Totem");
            this.addItem(PSItems.SCARAB_COMPASS, "Scarab Compass");

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
            // Blocks
            this.addBlock(PSBlocks.MYSTERIOUS_DIRT, "Mysteri\u00F6se Erde");
            this.addBlock(PSBlocks.MYSTERIOUS_GRAVEL, "Mysteri\u00F6ser Kies");
            this.addBlock(PSBlocks.MYSTERIOUS_SAND, "Mysteri\u00F6ser Sand");
            this.addBlock(PSBlocks.SANDSTONE_TOTEM_HOLDER, "Sandsteintotemhalter");
            this.addBlock(PSBlocks.LUNAR_STONE, "Lunarstein");

            // Items
            this.addItem(PSItems.BRUSH, "B\u00FCrste");
            this.addItem(PSItems.LUNAR_TOTEM, "Lunartotem");
            this.addItem(PSItems.SCARAB_COMPASS, "Skarab\u00E4uskompass");

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
            // Blocks
            this.addBlock(PSBlocks.MYSTERIOUS_DIRT, "Mysteri\u00F6se Erde");
            this.addBlock(PSBlocks.MYSTERIOUS_GRAVEL, "Mysteri\u00F6ser Gies");
            this.addBlock(PSBlocks.MYSTERIOUS_SAND, "Mysteri\u00F6ser Sand");
            this.addBlock(PSBlocks.SANDSTONE_TOTEM_HOLDER, "Sandschdeentotemhalderr");
            this.addBlock(PSBlocks.LUNAR_STONE, "Lunarschdeen");

            // Items
            this.addItem(PSItems.BRUSH, "B\u00FCrschde");
            this.addItem(PSItems.LUNAR_TOTEM, "Lunartotem");
            this.addItem(PSItems.SCARAB_COMPASS, "Schgarab\u00E4usgombass");

            // Powers
            this.add("power.pantheonsent.moon_knight", "Moon Knight");
        }
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
