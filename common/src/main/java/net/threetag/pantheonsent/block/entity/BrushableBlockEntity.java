package net.threetag.pantheonsent.block.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.item.enchantment.PSEnchantments;
import org.jetbrains.annotations.Nullable;

public class BrushableBlockEntity extends RandomizableContainerBlockEntity {

    public NonNullList<ItemStack> item = NonNullList.withSize(1, ItemStack.EMPTY);

    public BrushableBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(PSBlockEntityTypes.BRUSHABLE.get(), blockPos, blockState);
    }

    @Override
    public void unpackLootTable(@Nullable Player player) {
        if (this.lootTable != null && this.level.getServer() != null) {
            LootTable lootTable = this.level.getServer().getLootTables().get(this.lootTable);
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.GENERATE_LOOT.trigger((ServerPlayer) player, this.lootTable);
            }

            this.lootTable = null;
            LootContext.Builder builder = new LootContext.Builder((ServerLevel) this.level)
                    .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(this.worldPosition))
                    .withOptionalRandomSeed(this.lootTableSeed);

            if (player != null) {
                builder.withLuck(player.getLuck() + EnchantmentHelper.getItemEnchantmentLevel(PSEnchantments.FORTUNATE_FIND.get(), player.getUseItem())).withParameter(LootContextParams.THIS_ENTITY, player);
            }

            lootTable.fill(this, builder.create(LootContextParamSets.CHEST));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.item);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.item);
        }
    }

    @Override
    protected Component getDefaultName() {
        return PSBlocks.MYSTERIOUS_DIRT.get().getName();
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return null;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    @Override
    public void setChanged() {
        super.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.item;
    }

    public ItemStack getItem() {
        return this.item.get(0);
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemStacks) {
        this.item = itemStacks;
    }

    @Override
    public int getContainerSize() {
        return this.item.size();
    }
}
