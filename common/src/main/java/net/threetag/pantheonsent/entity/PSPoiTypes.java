package net.threetag.pantheonsent.entity;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.PSBlocks;

public class PSPoiTypes {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(PantheonSent.MOD_ID, Registry.POINT_OF_INTEREST_TYPE_REGISTRY);

    public static final RegistrySupplier<PoiType> ARCHEOLOGIST = POI_TYPES.register("archeologist",
            () -> PoiType.registerBlockStates(new PoiType("archeologist", PoiType.getBlockStates(PSBlocks.SANDSTONE_TOTEM_HOLDER.get()), 1, 1)));
}