package net.threetag.pantheonsent.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.threetag.pantheonsent.item.PSItems;

public class GodlyEncapsulatingEnchantment extends Enchantment {

    public GodlyEncapsulatingEnchantment(Rarity rarity, EnchantmentCategory enchantmentCategory) {
        super(rarity, enchantmentCategory, new EquipmentSlot[0]);
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() == PSItems.KHONSHU_USHABTI.get();
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }
}
