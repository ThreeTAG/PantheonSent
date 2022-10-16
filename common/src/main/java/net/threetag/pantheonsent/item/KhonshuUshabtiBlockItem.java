package net.threetag.pantheonsent.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class KhonshuUshabtiBlockItem extends BlockItem {

    public KhonshuUshabtiBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return 1;
    }
}
