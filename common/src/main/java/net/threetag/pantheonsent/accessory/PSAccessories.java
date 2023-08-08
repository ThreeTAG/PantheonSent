package net.threetag.pantheonsent.accessory;

import net.threetag.palladium.accessory.Accessory;
import net.threetag.palladium.accessory.AccessorySlot;
import net.threetag.palladium.accessory.RenderLayerAccessory;
import net.threetag.palladium.condition.HasPowerCondition;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;

public class PSAccessories {

    public static final DeferredRegister<Accessory> ACCESSORIES = DeferredRegister.create(PantheonSent.MOD_ID, Accessory.REGISTRY.getRegistryKey());
    public static final AccessorySlot SLOT = AccessorySlot.register(PantheonSent.id("moon_knight_suit"))
            .addVisibilityCondition(new HasPowerCondition(PantheonSent.id("moon_knight")))
            .setIcon(PantheonSent.id("textures/gui/accessory_slots/moon_knight_suit.png"));

    public static final RegistrySupplier<Accessory> MR_KNIGHT = ACCESSORIES.register("mr_knight", () -> new RenderLayerAccessory(PantheonSent.id("mr_knight")).disableRendering().slot(SLOT));

}
