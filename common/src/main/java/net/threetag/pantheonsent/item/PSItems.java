package net.threetag.pantheonsent.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Registry;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.threetag.palladium.item.*;
import net.threetag.palladiumcore.item.PalladiumSpawnEggItem;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.palladiumcore.registry.client.ItemPropertyRegistry;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.entity.PSEntityTypes;
import net.threetag.pantheonsent.tags.PSBannerPatternTags;

public class PSItems {

    public static final CreativeModeTabFiller FILLER_AFTER_DIRT = new CreativeModeTabFiller(() -> Items.DIRT);
    public static final CreativeModeTabFiller FILLER_AFTER_GRAVEL = new CreativeModeTabFiller(() -> Items.GRAVEL);
    public static final CreativeModeTabFiller FILLER_AFTER_SAND = new CreativeModeTabFiller(() -> Items.SAND);
    public static final CreativeModeTabFiller FILLER_AFTER_CHISELED_SANDSTONE = new CreativeModeTabFiller(() -> Items.CHISELED_SANDSTONE);
    public static final CreativeModeTabFiller FILLER_AFTER_CUT_SANDSTONE = new CreativeModeTabFiller(() -> Items.CUT_SANDSTONE);
    public static final CreativeModeTabFiller FILLER_AFTER_DAYLIGHT_DETECTOR = new CreativeModeTabFiller(() -> Items.DAYLIGHT_DETECTOR);
    public static final CreativeModeTabFiller FILLER_AFTER_COMPASS = new CreativeModeTabFiller(() -> Items.RECOVERY_COMPASS);
    public static final CreativeModeTabFiller FILLER_AFTER_STONECUTTER = new CreativeModeTabFiller(() -> Blocks.STONECUTTER);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(PantheonSent.MOD_ID, Registry.ITEM_REGISTRY);

    public static final RegistrySupplier<Item> GILDED_SANDSTONE = ITEMS.register("gilded_sandstone", () -> new SortedBlockItem(PSBlocks.GILDED_SANDSTONE.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS), FILLER_AFTER_CUT_SANDSTONE));
    public static final RegistrySupplier<Item> GILDED_SANDSTONE_PILLAR = ITEMS.register("gilded_sandstone_pillar", () -> new SortedBlockItem(PSBlocks.GILDED_SANDSTONE_PILLAR.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS), FILLER_AFTER_CUT_SANDSTONE));
    public static final RegistrySupplier<Item> ARCHEOLOGY_TABLE = ITEMS.register("archeology_table", () -> new SortedBlockItem(PSBlocks.ARCHEOLOGY_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), FILLER_AFTER_STONECUTTER));
    public static final RegistrySupplier<Item> BROKEN_KHONSHU_USHABTI = ITEMS.register("broken_khonshu_ushabti", () -> new BlockItem(PSBlocks.BROKEN_KHONSHU_USHABTI.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistrySupplier<Item> KHONSHU_USHABTI = ITEMS.register("khonshu_ushabti", () -> new KhonshuUshabtiBlockItem(PSBlocks.KHONSHU_USHABTI.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistrySupplier<Item> MYSTERIOUS_DIRT = ITEMS.register("mysterious_dirt", () -> new SortedBlockItem(PSBlocks.MYSTERIOUS_DIRT.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS), FILLER_AFTER_DIRT));
    public static final RegistrySupplier<Item> MYSTERIOUS_GRAVEL = ITEMS.register("mysterious_gravel", () -> new SortedBlockItem(PSBlocks.MYSTERIOUS_GRAVEL.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS), FILLER_AFTER_GRAVEL));
    public static final RegistrySupplier<Item> MYSTERIOUS_SAND = ITEMS.register("mysterious_sand", () -> new SortedBlockItem(PSBlocks.MYSTERIOUS_SAND.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS), FILLER_AFTER_SAND));
    public static final RegistrySupplier<Item> SANDSTONE_TOTEM_HOLDER = ITEMS.register("sandstone_totem_holder", () -> new SortedBlockItem(PSBlocks.SANDSTONE_TOTEM_HOLDER.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS), FILLER_AFTER_CHISELED_SANDSTONE));
    public static final RegistrySupplier<Item> LUNAR_STONE = ITEMS.register("lunar_stone", () -> new SortedBlockItem(PSBlocks.LUNAR_STONE.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE), FILLER_AFTER_DAYLIGHT_DETECTOR));

    public static final RegistrySupplier<Item> BRUSH = ITEMS.register("brush", () -> new BrushItem(new Item.Properties().durability(512).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistrySupplier<Item> ANCIENT_CLAY_SHARD = ITEMS.register("ancient_clay_shard", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistrySupplier<Item> ANCIENT_GOLD_SHARD = ITEMS.register("ancient_gold_shard", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistrySupplier<Item> LUNAR_SHARD = ITEMS.register("lunar_shard", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistrySupplier<Item> BROKEN_LUNAR_TOTEM = ITEMS.register("broken_lunar_totem", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.UNCOMMON).stacksTo(1)));
    public static final RegistrySupplier<Item> LUNAR_TOTEM = ITEMS.register("lunar_totem", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.UNCOMMON).stacksTo(1)));
    public static final RegistrySupplier<Item> BROKEN_SCARAB_COMPASS = ITEMS.register("broken_scarab_compass", () -> new SortedItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1), FILLER_AFTER_COMPASS));
    public static final RegistrySupplier<Item> SCARAB_COMPASS = ITEMS.register("scarab_compass", () -> new ScarabCompassItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).rarity(Rarity.RARE).stacksTo(1), FILLER_AFTER_COMPASS));
    public static final RegistrySupplier<Item> BROKEN_EYE_OF_HORUS = ITEMS.register("broken_eye_of_horus", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1)));
    public static final RegistrySupplier<Item> EYE_OF_HORUS = ITEMS.register("eye_of_horus", () -> new EyeOfHorusItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).rarity(Rarity.RARE).durability(3)));

    public static final RegistrySupplier<Item> KHONSHU_SPAWN_EGG = ITEMS.register("khonshu_spawn_egg", () -> new SortedSpawnEggItem(PSEntityTypes.KHONSHU, 0xd9d7d8, 0xd7c283, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistrySupplier<Item> CRESCENT_BANNER_PATTERN = ITEMS.register("crescent_banner_pattern", () -> new SortedBannerPatternItem(PSBannerPatternTags.PATTERN_CRESCENT, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE)));

    @Environment(EnvType.CLIENT)
    public static void initProperties() {
        ItemPropertyRegistry.register(SCARAB_COMPASS.get(), PantheonSent.id("angle"), new ScarabCompassItem.PropertyFunction());
        ItemPropertyRegistry.register(SCARAB_COMPASS.get(), PantheonSent.id("active"), (itemStack, clientLevel, livingEntity, i) -> itemStack.getOrCreateTag().contains("TargetPos") ? 1F : 0F);
    }

}
