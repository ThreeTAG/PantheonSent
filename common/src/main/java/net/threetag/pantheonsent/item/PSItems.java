package net.threetag.pantheonsent.item;

import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.threetag.palladium.item.CreativeModeTabFiller;
import net.threetag.palladium.item.SortedBlockItem;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.PSBlocks;

public class PSItems {

    public static final CreativeModeTabFiller FILLER_AFTER_DIRT = new CreativeModeTabFiller(() -> Items.DIRT);
    public static final CreativeModeTabFiller FILLER_AFTER_GRAVEL = new CreativeModeTabFiller(() -> Items.GRAVEL);
    public static final CreativeModeTabFiller FILLER_AFTER_SAND = new CreativeModeTabFiller(() -> Items.SAND);
    public static final CreativeModeTabFiller FILLER_AFTER_CHISELED_SANDSTONE = new CreativeModeTabFiller(() -> Items.CHISELED_SANDSTONE);
    public static final CreativeModeTabFiller FILLER_AFTER_DAYLIGHT_DETECTOR = new CreativeModeTabFiller(() -> Items.DAYLIGHT_DETECTOR);
    public static final CreativeModeTabFiller FILLER_AFTER_COMPASS = new CreativeModeTabFiller(() -> Items.COMPASS);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(PantheonSent.MOD_ID, Registry.ITEM_REGISTRY);

    public static final RegistrySupplier<Item> MYSTERIOUS_DIRT = ITEMS.register("mysterious_dirt", () -> new SortedBlockItem(PSBlocks.MYSTERIOUS_DIRT.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS), FILLER_AFTER_DIRT));
    public static final RegistrySupplier<Item> MYSTERIOUS_GRAVEL = ITEMS.register("mysterious_gravel", () -> new SortedBlockItem(PSBlocks.MYSTERIOUS_GRAVEL.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS), FILLER_AFTER_GRAVEL));
    public static final RegistrySupplier<Item> MYSTERIOUS_SAND = ITEMS.register("mysterious_sand", () -> new SortedBlockItem(PSBlocks.MYSTERIOUS_SAND.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS), FILLER_AFTER_SAND));
    public static final RegistrySupplier<Item> SANDSTONE_TOTEM_HOLDER = ITEMS.register("sandstone_totem_holder", () -> new SortedBlockItem(PSBlocks.SANDSTONE_TOTEM_HOLDER.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS), FILLER_AFTER_CHISELED_SANDSTONE));
    public static final RegistrySupplier<Item> LUNAR_STONE = ITEMS.register("lunar_stone", () -> new SortedBlockItem(PSBlocks.LUNAR_STONE.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE), FILLER_AFTER_DAYLIGHT_DETECTOR));

    public static final RegistrySupplier<Item> BRUSH = ITEMS.register("brush", () -> new BrushItem(new Item.Properties().durability(512).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistrySupplier<Item> LUNAR_TOTEM = ITEMS.register("lunar_totem", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.UNCOMMON).stacksTo(1)));
    public static final RegistrySupplier<Item> SCARAB_COMPASS = ITEMS.register("scarab_compass", () -> new ScarabCompassItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1), FILLER_AFTER_COMPASS));

    @Environment(EnvType.CLIENT)
    public static void initProperties() {
        ItemPropertiesRegistry.register(SCARAB_COMPASS.get(), PantheonSent.id("angle"), new ScarabCompassItem.PropertyFunction());
        ItemPropertiesRegistry.register(SCARAB_COMPASS.get(), PantheonSent.id("active"), (itemStack, clientLevel, livingEntity, i) -> itemStack.getOrCreateTag().contains("TargetPos") ? 1F : 0F);
    }

}
