package net.threetag.pantheonsent.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.item.crafting.PSRecipeSerializers;
import net.threetag.pantheonsent.item.crafting.RestorationRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class RestorationMenu extends ItemCombinerMenu {

    private final Level level;
    @Nullable
    private RestorationRecipe selectedRecipe;

    public RestorationMenu(int i, Inventory inventory) {
        this(i, inventory, ContainerLevelAccess.NULL);
    }

    public RestorationMenu(int i, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
        super(PSMenuTypes.RESTORATION.get(), i, inventory, containerLevelAccess);
        this.level = inventory.player.level();
    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(PSBlocks.ARCHEOLOGY_TABLE.get());
    }

    @Override
    protected boolean mayPickup(Player player, boolean hasStack) {
        return this.selectedRecipe != null && this.selectedRecipe.matches(this.inputSlots, this.level);
    }

    @Override
    protected void onTake(Player player, ItemStack itemStack) {
        itemStack.onCraftedBy(player.level(), player, itemStack.getCount());
        this.resultSlots.awardUsedRecipes(player, Arrays.asList(this.inputSlots.getItem(0), this.inputSlots.getItem(1)));
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.access.execute((level, blockPos) -> {
            // TODO custom sound
            level.levelEvent(1044, blockPos, 0);
        });
    }

    private void shrinkStackInSlot(int index) {
        ItemStack itemStack = this.inputSlots.getItem(index);
        itemStack.shrink(1);
        this.inputSlots.setItem(index, itemStack);
    }

    @Override
    public void createResult() {
        List<RestorationRecipe> list = this.level.getRecipeManager().getRecipesFor(PSRecipeSerializers.RESTORATION_RECIPE_TYPE.get(), this.inputSlots, this.level);
        if (list.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        } else {
            this.selectedRecipe = list.get(0);
            ItemStack itemStack = this.selectedRecipe.assemble(this.inputSlots, this.level.registryAccess());
            this.resultSlots.setRecipeUsed(this.selectedRecipe);
            this.resultSlots.setItem(0, itemStack);
        }
    }

    @Override
    protected @NotNull ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(0, 27, 47, (itemStack) -> true)
                .withSlot(1, 76, 47, (itemStack) -> true)
                .withResultSlot(2, 134, 47).build();
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
    }
}
