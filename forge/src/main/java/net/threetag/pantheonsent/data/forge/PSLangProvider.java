package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;
import net.threetag.palladium.data.forge.ExtendedLangProvider;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.ability.PSAbilities;
import net.threetag.pantheonsent.accessory.PSAccessories;
import net.threetag.pantheonsent.block.PSBannerPatterns;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.entity.PSEntityTypes;
import net.threetag.pantheonsent.item.PSItems;
import net.threetag.pantheonsent.item.enchantment.PSEnchantments;
import net.threetag.pantheonsent.sound.PSSoundEvents;

public abstract class PSLangProvider extends ExtendedLangProvider {

    public PSLangProvider(PackOutput packOutput, String locale) {
        super(packOutput, PantheonSent.MOD_ID, locale);
    }

    public static class English extends PSLangProvider {

        public English(PackOutput packOutput) {
            super(packOutput, "en_us");
        }

        @Override
        protected void addTranslations() {
            // Blocks
            this.addBlock(PSBlocks.GILDED_SANDSTONE, "Gilded Sandstone");
            this.addBlock(PSBlocks.GILDED_SANDSTONE_PILLAR, "Gilded Sandstone Pillar");
            this.addBlock(PSBlocks.ARCHEOLOGY_TABLE, "Archeology Table");
            this.addBlock(PSBlocks.BROKEN_KHONSHU_USHABTI, "Broken Khonshu Ushabti");
            this.addBlock(PSBlocks.KHONSHU_USHABTI, "Khonshu Ushabti");
            this.addBlock(PSBlocks.SANDSTONE_TOTEM_HOLDER, "Sandstone Totem Holder");
            this.addBlock(PSBlocks.LUNAR_STONE, "Lunar Stone");

            // Items
            this.addItem(PSItems.ANCIENT_CLAY_SHARD, "Ancient Clay Shard");
            this.addItem(PSItems.ANCIENT_GOLD_SHARD, "Ancient Gold Shard");
            this.addItem(PSItems.LUNAR_SHARD, "Lunar Shard");
            this.addItem(PSItems.BROKEN_LUNAR_TOTEM, "Broken Lunar Totem");
            this.addItem(PSItems.LUNAR_TOTEM, "Lunar Totem");
            this.addItem(PSItems.BROKEN_SCARAB_COMPASS, "Broken Scarab Compass");
            this.addItem(PSItems.SCARAB_COMPASS, "Scarab Compass");
            this.addItem(PSItems.BROKEN_EYE_OF_HORUS, "Broken Eye of Horus");
            this.addItem(PSItems.EYE_OF_HORUS, "Eye of Horus");
            this.addItem(PSItems.KHONSHU_SPAWN_EGG, "Khonshu Spawn Egg");
            this.addItem(PSItems.CRESCENT_BANNER_PATTERN, "Banner Pattern");
            this.add(PSItems.CRESCENT_BANNER_PATTERN.get().getDescriptionId() + ".desc", "Crescent");
            this.addItem(PSItems.CRESCENT_POTTERY_SHERD, "Crescent Pottery Sherd");
            this.addItem(PSItems.MUSIC_DISC_CHONS, "Music Disc");
            this.add(PSItems.MUSIC_DISC_CHONS.get().getDescriptionId() + ".desc", "Neon - Chons");

            // Enchantments
            this.addEnchantment(PSEnchantments.GODLY_ENCAPSULATING, "Godly Encapsulating");

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
            this.add("ability.pantheonsent.moon_knight.summon_suit", "Summon Suit");
            this.add("ability.pantheonsent.moon_knight.summon_stealth", "Stealth");
            this.add("ability.pantheonsent.moon_knight.crescent_dart", "Crescent Dart");

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
            this.add("entity.minecraft.villager.archeologist", "Archeologist");

            // Accessories
            this.add(PSAccessories.SLOT, "Moon Knight Suit");
            this.addAccessory(PSAccessories.MR_KNIGHT, "Mr. Knight");

            // Subtitles
            this.add(PSSoundDefinitionsProvider.subtitle(PSSoundEvents.CAPE), "Cape");
            this.add(PSSoundDefinitionsProvider.subtitle(PSSoundEvents.EYE_OF_HORUS), "Eye of Horus");
            this.add(PSSoundDefinitionsProvider.subtitle(PSSoundEvents.MOON_KNIGHT_TRANSFORMATION), "Moon Knight Transformation");
            this.add(PSSoundDefinitionsProvider.subtitle(PSSoundEvents.KHONSHU_CAPTURED), "Khonshu captured");
        }
    }

    public static class German extends PSLangProvider {

        public German(PackOutput packOutput) {
            super(packOutput, "de_de");
        }

        @Override
        protected void addTranslations() {
            // Blocks
            this.addBlock(PSBlocks.GILDED_SANDSTONE, "Golddurchzogener Sandstein");
            this.addBlock(PSBlocks.GILDED_SANDSTONE_PILLAR, "Golddurchzogene Sandsteins\u00E4ule");
            this.addBlock(PSBlocks.ARCHEOLOGY_TABLE, "Archeologietisch");
            this.addBlock(PSBlocks.BROKEN_KHONSHU_USHABTI, "Kaputtes Khonshu Uschebti");
            this.addBlock(PSBlocks.KHONSHU_USHABTI, "Khonshu Uschebti");
            this.addBlock(PSBlocks.SANDSTONE_TOTEM_HOLDER, "Sandsteintotemhalter");
            this.addBlock(PSBlocks.LUNAR_STONE, "Lunarstein");

            // Items
            this.addItem(PSItems.ANCIENT_CLAY_SHARD, "Antike Tonscherbe");
            this.addItem(PSItems.ANCIENT_GOLD_SHARD, "Antike Goldscherbe");
            this.addItem(PSItems.LUNAR_SHARD, "Lunarscherbe");
            this.addItem(PSItems.BROKEN_LUNAR_TOTEM, "Kaputtes Lunartotem");
            this.addItem(PSItems.LUNAR_TOTEM, "Lunartotem");
            this.addItem(PSItems.BROKEN_SCARAB_COMPASS, "Kaputter Skarab\u00E4uskompass");
            this.addItem(PSItems.SCARAB_COMPASS, "Skarab\u00E4uskompass");
            this.addItem(PSItems.BROKEN_EYE_OF_HORUS, "Kaputtes Horusauge");
            this.addItem(PSItems.EYE_OF_HORUS, "Horusauge");
            this.addItem(PSItems.KHONSHU_SPAWN_EGG, "Khonshu-Spawn-Ei");
            this.addItem(PSItems.CRESCENT_BANNER_PATTERN, "Bannervorlage");
            this.add(PSItems.CRESCENT_BANNER_PATTERN.get().getDescriptionId() + ".desc", "Halbmond");
            this.addItem(PSItems.CRESCENT_POTTERY_SHERD, "Halbmond-Töpferscherbe");
            this.addItem(PSItems.MUSIC_DISC_CHONS, "Schallplatte");
            this.add(PSItems.MUSIC_DISC_CHONS.get().getDescriptionId() + ".desc", "Neon - Chons");

            // Enchantments
            this.addEnchantment(PSEnchantments.GODLY_ENCAPSULATING, "G\u00F6ttliche Einkapselung");

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
            this.add("ability.pantheonsent.moon_knight.summon_suit", "Anzug beschw\u00F6ren");
            this.add("ability.pantheonsent.moon_knight.summon_stealth", "List");
            this.add("ability.pantheonsent.moon_knight.crescent_dart", "Halbmondpfeil");

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
            this.add("entity.minecraft.villager.archeologist", "Arch\u00E4ologe");

            // Accessories
            this.add(PSAccessories.SLOT, "Moon Knight Anzug");
            this.addAccessory(PSAccessories.MR_KNIGHT, "Mr. Knight");

            // Subtitles
            this.add(PSSoundDefinitionsProvider.subtitle(PSSoundEvents.CAPE), "Cape");
            this.add(PSSoundDefinitionsProvider.subtitle(PSSoundEvents.EYE_OF_HORUS), "Horusauge");
            this.add(PSSoundDefinitionsProvider.subtitle(PSSoundEvents.MOON_KNIGHT_TRANSFORMATION), "Moon Knight Transformation");
            this.add(PSSoundDefinitionsProvider.subtitle(PSSoundEvents.KHONSHU_CAPTURED), "Khonshu eingefangen");
        }
    }

    public static class Saxon extends PSLangProvider {

        public Saxon(PackOutput packOutput) {
            super(packOutput, "sxu");
        }

        @Override
        protected void addTranslations() {
            // Blocks
            this.addBlock(PSBlocks.GILDED_SANDSTONE, "Golddurschzochnorr Sandstein");
            this.addBlock(PSBlocks.GILDED_SANDSTONE_PILLAR, "Golddurschzochne Sandschdeens\u00E4ule");
            this.addBlock(PSBlocks.ARCHEOLOGY_TABLE, "Arscheolojiedisch");
            this.addBlock(PSBlocks.BROKEN_KHONSHU_USHABTI, "Gabuttes Khonshu Uschebti");
            this.addBlock(PSBlocks.KHONSHU_USHABTI, "Khonshu Uschebti");
            this.addBlock(PSBlocks.SANDSTONE_TOTEM_HOLDER, "Sandschdeentotemhalderr");
            this.addBlock(PSBlocks.LUNAR_STONE, "Lunarschdeen");

            // Items
            this.addItem(PSItems.ANCIENT_CLAY_SHARD, "Antige Donscherbe");
            this.addItem(PSItems.ANCIENT_GOLD_SHARD, "Antige Goldscherbe");
            this.addItem(PSItems.LUNAR_SHARD, "Lunarscherbe");
            this.addItem(PSItems.BROKEN_LUNAR_TOTEM, "Gabuttes Lunartotem");
            this.addItem(PSItems.LUNAR_TOTEM, "Lunartotem");
            this.addItem(PSItems.BROKEN_SCARAB_COMPASS, "Gabutter Schgarab\u00E4usgombass");
            this.addItem(PSItems.SCARAB_COMPASS, "Schgarab\u00E4usgombass");
            this.addItem(PSItems.BROKEN_EYE_OF_HORUS, "Gabuttes Horusooche");
            this.addItem(PSItems.EYE_OF_HORUS, "Horusooche");
            this.addItem(PSItems.KHONSHU_SPAWN_EGG, "Khonshu-Schborn-Ei");
            this.addItem(PSItems.CRESCENT_BANNER_PATTERN, "Bannorrvorlaache");
            this.add(PSItems.CRESCENT_BANNER_PATTERN.get().getDescriptionId() + ".desc", "Halbmond");
            this.addItem(PSItems.CRESCENT_POTTERY_SHERD, "Halbmond-Töpferscherbe");
            this.addItem(PSItems.MUSIC_DISC_CHONS, "Schallbladde");
            this.add(PSItems.MUSIC_DISC_CHONS.get().getDescriptionId() + ".desc", "Neon - Chons");

            // Enchantments
            this.addEnchantment(PSEnchantments.GODLY_ENCAPSULATING, "J\u00F6ttlische Ehngapselung");

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
            this.add("ability.pantheonsent.moon_knight.summon_suit", "Anzuch beschw\u00F6rn");
            this.add("ability.pantheonsent.moon_knight.summon_stealth", "List");
            this.add("ability.pantheonsent.moon_knight.crescent_dart", "Halbmondpfeil");

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
            this.add("entity.minecraft.villager.archeologist", "Arsch\u00E4oloje");

            // Accessories
            this.add(PSAccessories.SLOT, "Moon Knight Anzuch");
            this.addAccessory(PSAccessories.MR_KNIGHT, "Mr. Knight");

            // Subtitles
            this.add(PSSoundDefinitionsProvider.subtitle(PSSoundEvents.CAPE), "Cape");
            this.add(PSSoundDefinitionsProvider.subtitle(PSSoundEvents.EYE_OF_HORUS), "Horusooche");
            this.add(PSSoundDefinitionsProvider.subtitle(PSSoundEvents.MOON_KNIGHT_TRANSFORMATION), "Moon Knight Transformation");
            this.add(PSSoundDefinitionsProvider.subtitle(PSSoundEvents.KHONSHU_CAPTURED), "Khonshu einjefang'n");
        }
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
