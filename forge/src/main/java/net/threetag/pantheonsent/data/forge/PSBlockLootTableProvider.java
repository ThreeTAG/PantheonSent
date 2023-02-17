package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.threetag.palladium.data.forge.BlockLootTableProvider;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.item.PSItems;
import net.threetag.pantheonsent.item.PSLootTables;

public class PSBlockLootTableProvider extends BlockLootTableProvider {

    public PSBlockLootTableProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    public void addTables() {
        this.dropSelf(PSBlocks.GILDED_SANDSTONE.get());
        this.dropSelf(PSBlocks.GILDED_SANDSTONE_PILLAR.get());
        this.dropSelf(PSBlocks.ARCHEOLOGY_TABLE.get());
        this.dropSelf(PSBlocks.BROKEN_KHONSHU_USHABTI.get());
        this.dropSelf(PSBlocks.KHONSHU_USHABTI.get());
        this.dropWhenSilkTouch(PSBlocks.LUNAR_STONE.get());
//        this.add(PSBlocks.MYSTERIOUS_DIRT.get(), (arg) -> createSingleItemTableWithSilkTouch(arg, Blocks.DIRT));
//        this.add(PSBlocks.MYSTERIOUS_GRAVEL.get(), (arg) -> createSingleItemTableWithSilkTouch(arg, Blocks.GRAVEL));
        this.lootTables.put(PSBlocks.SUSPICIOUS_SAND.get().getLootTable(), LootTable.lootTable());

        this.lootTables.put(PSLootTables.SUSPICIOUS_SAND, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.RAW_GOLD))
                        .add(LootItem.lootTableItem(PSItems.BROKEN_KHONSHU_USHABTI.get()).setQuality(2))
                        .add(LootItem.lootTableItem(PSItems.BROKEN_LUNAR_TOTEM.get()).setQuality(1))
                        .add(LootItem.lootTableItem(PSItems.BROKEN_EYE_OF_HORUS.get()).setQuality(2))
                        .add(LootItem.lootTableItem(PSItems.BROKEN_SCARAB_COMPASS.get()).setQuality(1))
                        .add(LootItem.lootTableItem(PSItems.CRESCENT_BANNER_PATTERN.get()).setQuality(3))
                        .add(LootItem.lootTableItem(PSItems.LUNAR_SHARD.get()).setQuality(2))
                        .add(LootItem.lootTableItem(Items.CLOCK)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(4.0F))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET))
                        .add(LootItem.lootTableItem(PSItems.ANCIENT_CLAY_SHARD.get()).setWeight(2))
                        .add(LootItem.lootTableItem(PSItems.ANCIENT_GOLD_SHARD.get()).setWeight(10))
                        .add(LootItem.lootTableItem(Items.DEAD_BUSH))));
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
