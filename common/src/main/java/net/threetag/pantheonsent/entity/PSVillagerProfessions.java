package net.threetag.pantheonsent.entity;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.threetag.palladium.entity.BasicItemListing;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.palladiumcore.registry.VillagerTradeRegistry;
import net.threetag.pantheonsent.PantheonSent;

import java.util.function.Predicate;

public class PSVillagerProfessions {

    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(PantheonSent.MOD_ID, Registries.VILLAGER_PROFESSION);

    public static final Predicate<Holder<PoiType>> POI_PREDICATE = holder -> holder.is(BuiltInRegistries.POINT_OF_INTEREST_TYPE.getResourceKey(PSPoiTypes.ARCHEOLOGIST.get()).get());

    public static final RegistrySupplier<VillagerProfession> ARCHEOLOGIST = PROFESSIONS.register("archeologist",
            () -> new VillagerProfession("archeologist", POI_PREDICATE, POI_PREDICATE, ImmutableSet.of(), ImmutableSet.of(), null));

    public static void init() {
        VillagerTradeRegistry.registerForProfession(ARCHEOLOGIST.get(), 1,
                new BasicItemListing(1, new ItemStack(Items.BONE, 6), 3, 2),
                new BasicItemListing(1, new ItemStack(Items.BONE_BLOCK, 3), 2, 2));
    }

}
