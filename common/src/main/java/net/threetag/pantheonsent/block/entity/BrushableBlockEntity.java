package net.threetag.pantheonsent.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.threetag.pantheonsent.block.PSBlocks;

public class BrushableBlockEntity extends RandomizableContainerBlockEntity {

    public NonNullList<ItemStack> item = NonNullList.withSize(1, ItemStack.EMPTY);

    public BrushableBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(PSBlockEntityTypes.BRUSHABLE.get(), blockPos, blockState);
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
