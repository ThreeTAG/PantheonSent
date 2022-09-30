package net.threetag.pantheonsent.entity;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;

import java.util.function.Predicate;

public class PSVillagerProfessions {

    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(PantheonSent.MOD_ID, Registry.VILLAGER_PROFESSION_REGISTRY);

    public static final Predicate<Holder<PoiType>> POI_PREDICATE = holder -> holder.is(Registry.POINT_OF_INTEREST_TYPE.getResourceKey(PSPoiTypes.ARCHEOLOGIST.get()).get());

    public static final RegistrySupplier<VillagerProfession> ARCHEOLOGIST = PROFESSIONS.register("archeologist",
            () -> new VillagerProfession("archeologist", POI_PREDICATE, POI_PREDICATE, ImmutableSet.of(), ImmutableSet.of(), null));

    public static void init() {
        // TODO TradeRegistry in core
//        TradeRegistry.registerVillagerTrade(ARCHEOLOGIST.get(), 1,
//                new BasicItemListing(1, new ItemStack(Items.BONE, 6), 3, 2),
//                new BasicItemListing(1, new ItemStack(Items.BONE_BLOCK, 3), 2, 2));
    }

}
