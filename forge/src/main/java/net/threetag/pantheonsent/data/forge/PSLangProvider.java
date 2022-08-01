package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.entity.PSEntityTypes;
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
            this.addBlock(PSBlocks.GILDED_SANDSTONE, "Gilded Sandstone");
            this.addBlock(PSBlocks.GILDED_SANDSTONE_PILLAR, "Gilded Sandstone Pillar");
            this.addBlock(PSBlocks.ARCHEOLOGY_TABLE, "Archeology Table");
            this.addBlock(PSBlocks.KHONSHU_USHABTI, "Khonshu Ushabti");
            this.addBlock(PSBlocks.MYSTERIOUS_DIRT, "Mysterious Dirt");
            this.addBlock(PSBlocks.MYSTERIOUS_GRAVEL, "Mysterious Gravel");
            this.addBlock(PSBlocks.MYSTERIOUS_SAND, "Mysterious Sand");
            this.addBlock(PSBlocks.SANDSTONE_TOTEM_HOLDER, "Sandstone Totem Holder");
            this.addBlock(PSBlocks.LUNAR_STONE, "Lunar Stone");

            // Items
            this.addItem(PSItems.BRUSH, "Brush");
            this.addItem(PSItems.LUNAR_TOTEM, "Lunar Totem");
            this.addItem(PSItems.SCARAB_COMPASS, "Scarab Compass");
            this.addItem(PSItems.EYE_OF_HORUS, "Eye of Horus");

            // Powers
            this.add("power.pantheonsent.moon_knight", "Moon Knight");

            // Entities
            this.addEntityType(PSEntityTypes.KHONSHU, "Khonshu");
            this.add("entity.pantheonsent.khonshu.recruitment_line_1", "You are a worthy candidate to serve me during this time.");
            this.add("entity.pantheonsent.khonshu.recruitment_line_2", "Do you swear to protect the travellers of the night...");
            this.add("entity.pantheonsent.khonshu.recruitment_line_3", "...and bring my vengeance to those who would do them harm?");
            this.add("entity.pantheonsent.khonshu.recruitment_line_4", "Then rise as my fist of vengeance...");
            this.add("entity.pantheonsent.khonshu.recruitment_line_5", "...as my...");
            this.add("entity.pantheonsent.khonshu.recruitment_line_6", "...Moon Knight!");
            this.addEntityType(PSEntityTypes.CRESCENT_DART, "Crescent Dart");
        }
    }

    public static class German extends PSLangProvider {

        public German(DataGenerator gen) {
            super(gen, "de_de");
        }

        @Override
        protected void addTranslations() {
            // Blocks
            this.addBlock(PSBlocks.GILDED_SANDSTONE, "Golddurchzogener Sandstein");
            this.addBlock(PSBlocks.GILDED_SANDSTONE_PILLAR, "Golddurchzogene Sandsteins\u00E4ule");
            this.addBlock(PSBlocks.ARCHEOLOGY_TABLE, "Archeologietisch");
            this.addBlock(PSBlocks.KHONSHU_USHABTI, "Khonshu Uschebti");
            this.addBlock(PSBlocks.MYSTERIOUS_DIRT, "Mysteri\u00F6se Erde");
            this.addBlock(PSBlocks.MYSTERIOUS_GRAVEL, "Mysteri\u00F6ser Kies");
            this.addBlock(PSBlocks.MYSTERIOUS_SAND, "Mysteri\u00F6ser Sand");
            this.addBlock(PSBlocks.SANDSTONE_TOTEM_HOLDER, "Sandsteintotemhalter");
            this.addBlock(PSBlocks.LUNAR_STONE, "Lunarstein");

            // Items
            this.addItem(PSItems.BRUSH, "B\u00FCrste");
            this.addItem(PSItems.LUNAR_TOTEM, "Lunartotem");
            this.addItem(PSItems.SCARAB_COMPASS, "Skarab\u00E4uskompass");
            this.addItem(PSItems.EYE_OF_HORUS, "Horusauge");

            // Powers
            this.add("power.pantheonsent.moon_knight", "Moon Knight");

            // Entities
            this.addEntityType(PSEntityTypes.KHONSHU, "Khonshu");
            this.add("entity.pantheonsent.khonshu.recruitment_line_1", "Du bist ein würdiger Kandidat mir in dieser Zeit zu dienen.");
            this.add("entity.pantheonsent.khonshu.recruitment_line_2", "Schwörst du hier die Reisenden der Nacht zu beschützen...");
            this.add("entity.pantheonsent.khonshu.recruitment_line_3", "...und meine Vergeltung an jenen zu üben, die ihnen Leid antun?");
            this.add("entity.pantheonsent.khonshu.recruitment_line_4", "Dann erhebe dich als meine Faust der Vergeltung!");
            this.add("entity.pantheonsent.khonshu.recruitment_line_5", "Du bist...");
            this.add("entity.pantheonsent.khonshu.recruitment_line_6", "...Moon Knight!");
            this.addEntityType(PSEntityTypes.CRESCENT_DART, "Halbmondpfeil");
        }
    }

    public static class Saxon extends PSLangProvider {

        public Saxon(DataGenerator gen) {
            super(gen, "sxu");
        }

        @Override
        protected void addTranslations() {
            // Blocks
            this.addBlock(PSBlocks.GILDED_SANDSTONE, "Golddurschzochnorr Sandstein");
            this.addBlock(PSBlocks.GILDED_SANDSTONE_PILLAR, "Golddurschzochne Sandschdeens\u00E4ule");
            this.addBlock(PSBlocks.ARCHEOLOGY_TABLE, "Arscheolojiedisch");
            this.addBlock(PSBlocks.KHONSHU_USHABTI, "Khonshu Uschebti");
            this.addBlock(PSBlocks.MYSTERIOUS_DIRT, "Mysteri\u00F6se Erde");
            this.addBlock(PSBlocks.MYSTERIOUS_GRAVEL, "Mysteri\u00F6ser Gies");
            this.addBlock(PSBlocks.MYSTERIOUS_SAND, "Mysteri\u00F6ser Sand");
            this.addBlock(PSBlocks.SANDSTONE_TOTEM_HOLDER, "Sandschdeentotemhalderr");
            this.addBlock(PSBlocks.LUNAR_STONE, "Lunarschdeen");

            // Items
            this.addItem(PSItems.BRUSH, "B\u00FCrschde");
            this.addItem(PSItems.LUNAR_TOTEM, "Lunartotem");
            this.addItem(PSItems.SCARAB_COMPASS, "Schgarab\u00E4usgombass");
            this.addItem(PSItems.EYE_OF_HORUS, "Horusooche");

            // Powers
            this.add("power.pantheonsent.moon_knight", "Moon Knight");

            // Entities
            this.addEntityType(PSEntityTypes.KHONSHU, "Khonshu");
            this.add("entity.pantheonsent.khonshu.recruitment_line_1", "Du bischd ehn würd'ger Kandidat mir in dieser Zeit zu dienen.");
            this.add("entity.pantheonsent.khonshu.recruitment_line_2", "Schwörste hier de Reisend'n der Nacht zu beschützen...");
            this.add("entity.pantheonsent.khonshu.recruitment_line_3", "...und mehne Verjeltung an jenen zu üben, die ihnen Lehd antun?");
            this.add("entity.pantheonsent.khonshu.recruitment_line_4", "Dann erheb' dich als mehne Faust der Verjeltung!");
            this.add("entity.pantheonsent.khonshu.recruitment_line_5", "Du bischd...");
            this.add("entity.pantheonsent.khonshu.recruitment_line_6", "...Moon Knight!");
            this.addEntityType(PSEntityTypes.CRESCENT_DART, "Halbmondpfeil");
        }
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
