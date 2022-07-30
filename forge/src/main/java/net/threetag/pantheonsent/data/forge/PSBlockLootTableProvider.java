package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
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
        this.dropSelf(PSBlocks.KHONSHU_USHABTI.get());
        this.dropWhenSilkTouch(PSBlocks.LUNAR_STONE.get());
        this.add(PSBlocks.MYSTERIOUS_DIRT.get(), (arg) -> createSingleItemTableWithSilkTouch(arg, Blocks.DIRT));
        this.add(PSBlocks.MYSTERIOUS_GRAVEL.get(), (arg) -> createSingleItemTableWithSilkTouch(arg, Blocks.GRAVEL));
        this.add(PSBlocks.MYSTERIOUS_SAND.get(), (arg) -> createSingleItemTableWithSilkTouch(arg, Blocks.SAND));

        this.lootTables.put(PSLootTables.BRUSHABLE_DIRT, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(Items.BONE))
                .add(LootItem.lootTableItem(Items.DIAMOND_BLOCK))));
        this.lootTables.put(PSLootTables.BRUSHABLE_GRAVEL, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(Items.IRON_NUGGET))));
        this.lootTables.put(PSLootTables.BRUSHABLE_SAND, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(PSItems.SCARAB_COMPASS.get()))
                        .add(LootItem.lootTableItem(Items.RAW_GOLD))
                        .add(LootItem.lootTableItem(Items.CLOCK)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(4.0F))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET))
                        .add(LootItem.lootTableItem(Items.DEAD_BUSH))));
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
