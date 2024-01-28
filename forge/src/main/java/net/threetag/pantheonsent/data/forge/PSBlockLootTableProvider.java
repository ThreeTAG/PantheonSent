package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.item.PSItems;
import net.threetag.pantheonsent.item.PSLootTables;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class PSBlockLootTableProvider extends LootTableProvider {

    public PSBlockLootTableProvider(PackOutput output) {
        super(output, BuiltInLootTables.all(), List.of(
                new LootTableProvider.SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(ArcheologyLoot::new, LootContextParamSets.ARCHAEOLOGY)
        ));
    }

    public static class BlockLoot extends BlockLootSubProvider {

        protected BlockLoot() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            this.dropSelf(PSBlocks.GILDED_SANDSTONE.get());
            this.dropSelf(PSBlocks.GILDED_SANDSTONE_PILLAR.get());
            this.dropSelf(PSBlocks.ARCHEOLOGY_TABLE.get());
            this.dropSelf(PSBlocks.BROKEN_KHONSHU_USHABTI.get());
            this.dropSelf(PSBlocks.KHONSHU_USHABTI.get());
            this.dropWhenSilkTouch(PSBlocks.LUNAR_STONE.get());
            this.dropOther(PSBlocks.SANDSTONE_TOTEM_HOLDER.get(), Blocks.SANDSTONE);
        }

        @Override
        protected @NotNull Iterable<Block> getKnownBlocks() {
            return PSBlocks.BLOCKS.getEntries().stream().map(RegistrySupplier::get).toList();
        }

    }

    public static class ArcheologyLoot implements LootTableSubProvider {

        @Override
        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> output) {
            output.accept(PSLootTables.DIG_SITE_SAND, LootTable.lootTable()
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
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @NotNull ValidationContext context) {
        map.forEach((id, table) -> table.validate(context.setParams(table.getParamSet()).enterElement("{" + id + "}", new LootDataId<>(LootDataType.TABLE, id))));
    }
}
