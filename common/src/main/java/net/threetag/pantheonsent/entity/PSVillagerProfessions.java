package net.threetag.pantheonsent.entity;

import com.google.common.collect.ImmutableSet;
import dev.architectury.registry.level.entity.trade.TradeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.threetag.palladium.entity.BasicItemListing;
import net.threetag.pantheonsent.PantheonSent;

public class PSVillagerProfessions {

    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(PantheonSent.MOD_ID, Registry.VILLAGER_PROFESSION_REGISTRY);

    public static final RegistrySupplier<VillagerProfession> ARCHEOLOGIST = PROFESSIONS.register("archeologist",
            () -> new VillagerProfession("archeologist", (poi) -> poi.is(PSPoiTypes.ARCHEOLOGIST.getId()), (poi) -> poi.is(PSPoiTypes.ARCHEOLOGIST.getId()), ImmutableSet.of(), ImmutableSet.of(), null));

    public static void init() {
        TradeRegistry.registerVillagerTrade(ARCHEOLOGIST.get(), 1,
                new BasicItemListing(1, new ItemStack(Items.BONE, 6), 3, 2),
                new BasicItemListing(1, new ItemStack(Items.BONE_BLOCK, 3), 2, 2));
    }

}
