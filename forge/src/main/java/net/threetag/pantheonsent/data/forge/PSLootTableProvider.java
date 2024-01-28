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

public class PSLootTableProvider extends LootTableProvider {

    public PSLootTableProvider(PackOutput output) {
        super(output, BuiltInLootTables.all(), List.of(
                new LootTableProvider.SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(ArcheologyLoot::new, LootContextParamSets.ARCHAEOLOGY),
                new LootTableProvider.SubProviderEntry(ChestLoot::new, LootContextParamSets.CHEST)
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
                            .add(LootItem.lootTableItem(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE))
                            .add(LootItem.lootTableItem(PSItems.MUSIC_DISC_CHONS.get()))
                            .add(LootItem.lootTableItem(PSItems.BROKEN_KHONSHU_USHABTI.get()))
                            .add(LootItem.lootTableItem(PSItems.BROKEN_LUNAR_TOTEM.get()))
                            .add(LootItem.lootTableItem(PSItems.BROKEN_EYE_OF_HORUS.get()))
                            .add(LootItem.lootTableItem(PSItems.BROKEN_SCARAB_COMPASS.get()))
                            .add(LootItem.lootTableItem(PSItems.CRESCENT_BANNER_PATTERN.get()))
                            .add(LootItem.lootTableItem(PSItems.CRESCENT_POTTERY_SHERD.get()))
                            .add(LootItem.lootTableItem(Items.RAW_GOLD).setWeight(2))
                            .add(LootItem.lootTableItem(Items.GOLDEN_SWORD).setWeight(2))
                            .add(LootItem.lootTableItem(Items.GOLDEN_CHESTPLATE).setWeight(2))
                            .add(LootItem.lootTableItem(Items.GOLDEN_HELMET).setWeight(2))
                            .add(LootItem.lootTableItem(Items.GOLDEN_LEGGINGS).setWeight(2))
                            .add(LootItem.lootTableItem(Items.GOLDEN_BOOTS).setWeight(2))
                            .add(LootItem.lootTableItem(Items.CLOCK).setWeight(2))
                            .add(LootItem.lootTableItem(PSItems.LUNAR_SHARD.get()).setWeight(5))
                            .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(10))
                            .add(LootItem.lootTableItem(Items.BONE).setWeight(10))
                            .add(LootItem.lootTableItem(PSItems.ANCIENT_CLAY_SHARD.get()).setWeight(20))
                            .add(LootItem.lootTableItem(PSItems.ANCIENT_GOLD_SHARD.get()).setWeight(20))
                            .add(LootItem.lootTableItem(Items.DEAD_BUSH).setWeight(10))));
        }
    }

    public static class ChestLoot implements LootTableSubProvider {

        @Override
        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> output) {
            output.accept(PSLootTables.DIG_SITE_BARRELS, LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(2.0F))
                            .add(LootItem.lootTableItem(Items.BRUSH))
                            .add(LootItem.lootTableItem(Items.STONE_SHOVEL))
                            .add(LootItem.lootTableItem(Items.IRON_SHOVEL))
                            .add(LootItem.lootTableItem(Items.TORCH)))
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(4.0F))
                            .add(LootItem.lootTableItem(Items.FEATHER))
                            .add(LootItem.lootTableItem(Items.BONE))
                            .add(LootItem.lootTableItem(Items.BONE_BLOCK))
                            .add(LootItem.lootTableItem(Items.BONE_MEAL))
                            .add(LootItem.lootTableItem(Items.POTATO).setQuality(5))));
        }
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @NotNull ValidationContext context) {
        map.forEach((id, table) -> table.validate(context.setParams(table.getParamSet()).enterElement("{" + id + "}", new LootDataId<>(LootDataType.TABLE, id))));
    }
}
