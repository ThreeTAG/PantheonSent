package net.threetag.pantheonsent.item.enchantment;

import net.minecraft.core.Registry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.LootBonusEnchantment;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;

public class PSEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(PantheonSent.MOD_ID, Registry.ENCHANTMENT_REGISTRY);

    public static final RegistrySupplier<Enchantment> FORTUNATE_FIND = ENCHANTMENTS.register("fortunate_find",
            () -> new LootBonusEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.BREAKABLE, EquipmentSlot.MAINHAND));

    public static final RegistrySupplier<Enchantment> GODLY_ENCAPSULATING = ENCHANTMENTS.register("godly_encapsulating",
            () -> new GodlyEncapsulatingEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.BREAKABLE));

}
