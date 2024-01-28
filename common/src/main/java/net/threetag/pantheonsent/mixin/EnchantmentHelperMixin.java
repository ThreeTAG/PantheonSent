package net.threetag.pantheonsent.mixin;

import com.google.common.collect.Lists;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.threetag.pantheonsent.item.PSItems;
import net.threetag.pantheonsent.item.enchantment.PSEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

    @Inject(method = "getAvailableEnchantmentResults", at = @At("HEAD"), cancellable = true)
    private static void getAvailableEnchantmentResults(int level, ItemStack stack, boolean allowTreasure, CallbackInfoReturnable<List<EnchantmentInstance>> ci) {
        if (stack.getItem() == PSItems.KHONSHU_USHABTI.get()) {
            List<EnchantmentInstance> list = Lists.newArrayList();
            list.add(new EnchantmentInstance(PSEnchantments.GODLY_ENCAPSULATING.get(), 1));
            ci.setReturnValue(list);
        } else if (stack.getItem() == Items.BRUSH) {
            List<EnchantmentInstance> list = Lists.newArrayList();
            for (Enchantment enchantment : BuiltInRegistries.ENCHANTMENT) {
                if ((!enchantment.isTreasureOnly() || allowTreasure) && enchantment.isDiscoverable() && enchantment.canEnchant(stack)) {
                    for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
                        if (level >= enchantment.getMinCost(i) && level <= enchantment.getMaxCost(i)) {
                            list.add(new EnchantmentInstance(enchantment, i));
                            break;
                        }
                    }
                }
            }

            ci.setReturnValue(list);
        }
    }

}
