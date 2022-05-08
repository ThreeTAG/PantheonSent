package net.threetag.pantheonsent.entity;

import com.google.common.collect.ImmutableSet;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.threetag.pantheonsent.PantheonSent;

public class PSVillagerProfessions {

    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(PantheonSent.MOD_ID, Registry.VILLAGER_PROFESSION_REGISTRY);

    public static final RegistrySupplier<VillagerProfession> ARCHEOLOGIST = PROFESSIONS.register("archeologist",
            () -> new VillagerProfession("archeologist", PoiType.CARTOGRAPHER, ImmutableSet.of(), ImmutableSet.of(), null));

}
