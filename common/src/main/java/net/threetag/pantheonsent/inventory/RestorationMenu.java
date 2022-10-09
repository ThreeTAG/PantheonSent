package net.threetag.pantheonsent.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.item.crafting.PSRecipeSerializers;
import net.threetag.pantheonsent.item.crafting.RestorationRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RestorationMenu extends ItemCombinerMenu {

    private final Level level;
    @Nullable
    private RestorationRecipe selectedRecipe;
    private final List<RestorationRecipe> recipes;

    public RestorationMenu(int i, Inventory inventory) {
        this(i, inventory, ContainerLevelAccess.NULL);
    }

    public RestorationMenu(int i, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
        super(PSMenuTypes.RESTORATION.get(), i, inventory, containerLevelAccess);
        this.level = inventory.player.level;
        this.recipes = this.level.getRecipeManager().getAllRecipesFor(PSRecipeSerializers.RESTORATION_RECIPE_TYPE.get());
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
        itemStack.onCraftedBy(player.level, player, itemStack.getCount());
        this.resultSlots.awardUsedRecipes(player);
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
            ItemStack itemStack = this.selectedRecipe.assemble(this.inputSlots);
            this.resultSlots.setRecipeUsed(this.selectedRecipe);
            this.resultSlots.setItem(0, itemStack);
        }
    }

    @Override
    protected boolean shouldQuickMoveToAdditionalSlot(ItemStack stack) {
        return this.recipes.stream().anyMatch((upgradeRecipe) -> upgradeRecipe.isAdditionIngredient(stack));
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
    }
}
