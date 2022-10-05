package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.DyeColor;
import net.threetag.palladium.data.forge.ExtendedLangProvider;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.ability.PSAbilities;
import net.threetag.pantheonsent.block.PSBannerPatterns;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.entity.PSEntityTypes;
import net.threetag.pantheonsent.item.PSItems;

public abstract class PSLangProvider extends ExtendedLangProvider {

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
            this.addItem(PSItems.CRESCENT_BANNER_PATTERN, "Banner Pattern");
            this.add(PSItems.CRESCENT_BANNER_PATTERN.get().getDescriptionId() + ".desc", "Crescent");

            // Banner Pattern
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.WHITE, "White Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.ORANGE, "Orange Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.MAGENTA, "Magenta Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.LIGHT_BLUE, "Light Blue Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.YELLOW, "Yellow Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.LIME, "Lime Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.PINK, "Pink Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.GRAY, "Gray Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.LIGHT_GRAY, "Light Gray Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.CYAN, "Cyan Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.PURPLE, "Purple Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.BLUE, "Blue Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.BROWN, "Brown Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.GREEN, "Green Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.RED, "Red Crescent");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.BLACK, "Black Crescent");

            // Powers
            this.add("power.pantheonsent.moon_knight", "Moon Knight");

            // Abilities
            this.addAbility(PSAbilities.MOON_KNIGHT_GLIDING, "Gliding");
            this.addAbility(PSAbilities.MOON_KNIGHT_BLOCKING, "Blocking");
            this.addAbility(PSAbilities.GOD_STALKED, "God Stalked");

            // Container
            this.add("container.pantheonsent.restoration", "Restoration");

            // Entities
            this.addEntityType(PSEntityTypes.KHONSHU, "Khonshu");
            this.add("entity.pantheonsent.khonshu.recruitment_line_1", "You are a worthy candidate to serve me during this time.");
            this.add("entity.pantheonsent.khonshu.recruitment_line_2", "Do you swear to protect the travellers of the night...");
            this.add("entity.pantheonsent.khonshu.recruitment_line_3", "...and bring my vengeance to those who would do them harm?");
            this.add("entity.pantheonsent.khonshu.recruitment_line_4", "Then rise as my fist of vengeance...");
            this.add("entity.pantheonsent.khonshu.recruitment_line_5", "...as my...");
            this.add("entity.pantheonsent.khonshu.recruitment_line_6", "...Moon Knight!");
            this.addEntityType(PSEntityTypes.CRESCENT_DART, "Crescent Dart");
            this.add("entity.minecraft.villager.pantheonsent.archeologist", "Archeologist");
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
            this.addItem(PSItems.CRESCENT_BANNER_PATTERN, "Bannervorlage");
            this.add(PSItems.CRESCENT_BANNER_PATTERN.get().getDescriptionId() + ".desc", "Halbmond");

            // Banner Pattern
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.WHITE, "Wei\u00DFer Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.ORANGE, "Oranger Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.MAGENTA, "Magenta Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.LIGHT_BLUE, "Hellblauer Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.YELLOW, "Gelber Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.LIME, "Hellgr\u00FCner Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.PINK, "Rosa Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.GRAY, "Grauer Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.LIGHT_GRAY, "Hellgrauer Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.CYAN, "T\u00FCrkiser Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.PURPLE, "Violetter Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.BLUE, "Blauer Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.BROWN, "Brauner Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.GREEN, "Gr\u00FCner Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.RED, "Roter Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.BLACK, "Schwarzer Halbmond");

            // Powers
            this.add("power.pantheonsent.moon_knight", "Moon Knight");

            // Abilities
            this.addAbility(PSAbilities.MOON_KNIGHT_GLIDING, "Gleiten");
            this.addAbility(PSAbilities.MOON_KNIGHT_BLOCKING, "Blocken");
            this.addAbility(PSAbilities.GOD_STALKED, "Vom Gott verfolgt");

            // Container
            this.add("container.pantheonsent.restoration", "Restauration");

            // Entities
            this.addEntityType(PSEntityTypes.KHONSHU, "Khonshu");
            this.add("entity.pantheonsent.khonshu.recruitment_line_1", "Du bist ein würdiger Kandidat mir in dieser Zeit zu dienen.");
            this.add("entity.pantheonsent.khonshu.recruitment_line_2", "Schwörst du hier die Reisenden der Nacht zu beschützen...");
            this.add("entity.pantheonsent.khonshu.recruitment_line_3", "...und meine Vergeltung an jenen zu üben, die ihnen Leid antun?");
            this.add("entity.pantheonsent.khonshu.recruitment_line_4", "Dann erhebe dich als meine Faust der Vergeltung!");
            this.add("entity.pantheonsent.khonshu.recruitment_line_5", "Du bist...");
            this.add("entity.pantheonsent.khonshu.recruitment_line_6", "...Moon Knight!");
            this.addEntityType(PSEntityTypes.CRESCENT_DART, "Halbmondpfeil");
            this.add("entity.minecraft.villager.pantheonsent.archeologist", "Arch\u00E4ologe");
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
            this.addItem(PSItems.CRESCENT_BANNER_PATTERN, "Bannorrvorlaache");
            this.add(PSItems.CRESCENT_BANNER_PATTERN.get().getDescriptionId() + ".desc", "Halbmond");

            // Banner Pattern
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.WHITE, "Weeßorr Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.ORANGE, "Orangschorr Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.MAGENTA, "Magenda Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.LIGHT_BLUE, "Hellblauorr Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.YELLOW, "Gelborr Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.LIME, "Hellgrienorr Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.PINK, "Pingorr Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.GRAY, "Grauorr Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.LIGHT_GRAY, "Hellgrauorr Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.CYAN, "D\u00FCrgisorr Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.PURPLE, "Lila Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.BLUE, "Blauorr Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.BROWN, "Braunorr Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.GREEN, "Grienorr Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.RED, "Rodorr Halbmond");
            this.addBannerPatternDesc(PSBannerPatterns.CRESCENT, DyeColor.BLACK, "Schworzorr Halbmond");

            // Powers
            this.add("power.pantheonsent.moon_knight", "Moon Knight");

            // Abilities
            this.addAbility(PSAbilities.MOON_KNIGHT_GLIDING, "Gleiten");
            this.addAbility(PSAbilities.MOON_KNIGHT_BLOCKING, "Blocken");
            this.addAbility(PSAbilities.GOD_STALKED, "Vom Jott verfolchd");

            // Container
            this.add("container.pantheonsent.restoration", "Restauration");

            // Entities
            this.addEntityType(PSEntityTypes.KHONSHU, "Khonshu");
            this.add("entity.pantheonsent.khonshu.recruitment_line_1", "Du bischd ehn würd'ger Kandidat mir in dieser Zeit zu dienen.");
            this.add("entity.pantheonsent.khonshu.recruitment_line_2", "Schwörste hier de Reisend'n der Nacht zu beschützen...");
            this.add("entity.pantheonsent.khonshu.recruitment_line_3", "...und mehne Verjeltung an jenen zu üben, die ihnen Lehd antun?");
            this.add("entity.pantheonsent.khonshu.recruitment_line_4", "Dann erheb' dich als mehne Faust der Verjeltung!");
            this.add("entity.pantheonsent.khonshu.recruitment_line_5", "Du bischd...");
            this.add("entity.pantheonsent.khonshu.recruitment_line_6", "...Moon Knight!");
            this.addEntityType(PSEntityTypes.CRESCENT_DART, "Halbmondpfeil");
            this.add("entity.minecraft.villager.pantheonsent.archeologist", "Arsch\u00E4oloje");
        }
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
