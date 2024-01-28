package net.threetag.pantheonsent.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.threetag.palladiumcore.item.PalladiumRecordItem;
import net.threetag.palladiumcore.item.PalladiumSpawnEggItem;
import net.threetag.palladiumcore.registry.CreativeModeTabRegistry;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.palladiumcore.registry.client.ItemPropertyRegistry;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.entity.PSEntityTypes;
import net.threetag.pantheonsent.sound.PSSoundEvents;
import net.threetag.pantheonsent.tags.PSBannerPatternTags;

public class PSItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(PantheonSent.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> GILDED_SANDSTONE = ITEMS.register("gilded_sandstone", () -> new BlockItem(PSBlocks.GILDED_SANDSTONE.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> GILDED_SANDSTONE_PILLAR = ITEMS.register("gilded_sandstone_pillar", () -> new BlockItem(PSBlocks.GILDED_SANDSTONE_PILLAR.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> ARCHEOLOGY_TABLE = ITEMS.register("archeology_table", () -> new BlockItem(PSBlocks.ARCHEOLOGY_TABLE.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> BROKEN_KHONSHU_USHABTI = ITEMS.register("broken_khonshu_ushabti", () -> new BlockItem(PSBlocks.BROKEN_KHONSHU_USHABTI.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> KHONSHU_USHABTI = ITEMS.register("khonshu_ushabti", () -> new KhonshuUshabtiBlockItem(PSBlocks.KHONSHU_USHABTI.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> SANDSTONE_TOTEM_HOLDER = ITEMS.register("sandstone_totem_holder", () -> new BlockItem(PSBlocks.SANDSTONE_TOTEM_HOLDER.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> LUNAR_STONE = ITEMS.register("lunar_stone", () -> new BlockItem(PSBlocks.LUNAR_STONE.get(), new Item.Properties()));

    public static final RegistrySupplier<Item> ANCIENT_CLAY_SHARD = ITEMS.register("ancient_clay_shard", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> ANCIENT_GOLD_SHARD = ITEMS.register("ancient_gold_shard", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> LUNAR_SHARD = ITEMS.register("lunar_shard", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> BROKEN_LUNAR_TOTEM = ITEMS.register("broken_lunar_totem", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1)));
    public static final RegistrySupplier<Item> LUNAR_TOTEM = ITEMS.register("lunar_totem", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1)));
    public static final RegistrySupplier<Item> BROKEN_SCARAB_COMPASS = ITEMS.register("broken_scarab_compass", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> SCARAB_COMPASS = ITEMS.register("scarab_compass", () -> new ScarabCompassItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistrySupplier<Item> BROKEN_EYE_OF_HORUS = ITEMS.register("broken_eye_of_horus", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> EYE_OF_HORUS = ITEMS.register("eye_of_horus", () -> new EyeOfHorusItem(new Item.Properties().rarity(Rarity.RARE).durability(3)));

    public static final RegistrySupplier<Item> KHONSHU_SPAWN_EGG = ITEMS.register("khonshu_spawn_egg", () -> new PalladiumSpawnEggItem(PSEntityTypes.KHONSHU, 0xd9d7d8, 0xd7c283, new Item.Properties()));
    public static final RegistrySupplier<Item> CRESCENT_BANNER_PATTERN = ITEMS.register("crescent_banner_pattern", () -> new BannerPatternItem(PSBannerPatternTags.PATTERN_CRESCENT, (new Item.Properties()).stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> CRESCENT_POTTERY_SHERD = ITEMS.register("crescent_pottery_sherd", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> MUSIC_DISC_CHONS = ITEMS.register("music_disc_chons", () -> new PalladiumRecordItem(1, PSSoundEvents.MUSIC_DISC_CHONS, (new Item.Properties()).stacksTo(1).rarity(Rarity.RARE), 69));

    public static void init() {
        CreativeModeTabRegistry.addToTab(CreativeModeTabs.BUILDING_BLOCKS, entries -> {
            entries.addAfter(Items.CUT_STANDSTONE_SLAB, GILDED_SANDSTONE.get(), GILDED_SANDSTONE_PILLAR.get(), SANDSTONE_TOTEM_HOLDER.get());
        });

        CreativeModeTabRegistry.addToTab(CreativeModeTabs.REDSTONE_BLOCKS, entries -> {
            entries.addAfter(Items.DAYLIGHT_DETECTOR, LUNAR_STONE.get());
        });

        CreativeModeTabRegistry.addToTab(CreativeModeTabs.FUNCTIONAL_BLOCKS, entries -> {
            entries.addAfter(Items.SMITHING_TABLE, ARCHEOLOGY_TABLE.get());
            entries.addAfter(Items.DRAGON_EGG, BROKEN_KHONSHU_USHABTI.get(), KHONSHU_USHABTI.get());
        });

        CreativeModeTabRegistry.addToTab(CreativeModeTabs.INGREDIENTS, entries -> {
            entries.addBefore(Items.ANGLER_POTTERY_SHERD, ANCIENT_CLAY_SHARD.get(), ANCIENT_GOLD_SHARD.get(), LUNAR_SHARD.get());
            entries.addAfter(Items.PIGLIN_BANNER_PATTERN, CRESCENT_BANNER_PATTERN.get());
            entries.addAfter(Items.SNORT_POTTERY_SHERD, CRESCENT_POTTERY_SHERD.get());
        });

        CreativeModeTabRegistry.addToTab(CreativeModeTabs.TOOLS_AND_UTILITIES, entries -> {
            entries.addAfter(Items.MUSIC_DISC_RELIC, MUSIC_DISC_CHONS.get());
            entries.addAfter(Items.WARPED_FUNGUS_ON_A_STICK, BROKEN_LUNAR_TOTEM.get(), LUNAR_TOTEM.get(), BROKEN_SCARAB_COMPASS.get(), SCARAB_COMPASS.get(), BROKEN_EYE_OF_HORUS.get(), EYE_OF_HORUS.get());
        });

        CreativeModeTabRegistry.addToTab(CreativeModeTabs.SPAWN_EGGS, entries -> {
            entries.add(KHONSHU_SPAWN_EGG.get());
        });
    }

    @Environment(EnvType.CLIENT)
    public static void initProperties() {
        ItemPropertyRegistry.register(SCARAB_COMPASS.get(), PantheonSent.id("angle"), new ScarabCompassItem.PropertyFunction());
        ItemPropertyRegistry.register(SCARAB_COMPASS.get(), PantheonSent.id("active"), (itemStack, clientLevel, livingEntity, i) -> itemStack.getOrCreateTag().contains("TargetPos") ? 1F : 0F);
    }

}
