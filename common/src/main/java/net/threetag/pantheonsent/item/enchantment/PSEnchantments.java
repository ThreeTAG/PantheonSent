package net.threetag.pantheonsent.item.enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;

public class PSEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(PantheonSent.MOD_ID, Registries.ENCHANTMENT);

    public static final RegistrySupplier<Enchantment> GODLY_ENCAPSULATING = ENCHANTMENTS.register("godly_encapsulating",
            () -> new GodlyEncapsulatingEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.BREAKABLE));

}
