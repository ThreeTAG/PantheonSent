package net.threetag.pantheonsent.entity;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.threetag.pantheonsent.PantheonSent;

import java.util.function.Supplier;

public class PSEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(PantheonSent.MOD_ID, Registry.ENTITY_TYPE_REGISTRY);

    public static final RegistrySupplier<EntityType<CrescentDart>> CRESCENT_DART = register("crescent_dart", () -> EntityType.Builder.<CrescentDart>of(CrescentDart::new, MobCategory.MISC).sized(0.1F, 0.1F));

    public static <T extends Entity> RegistrySupplier<EntityType<T>> register(String id, Supplier<EntityType.Builder<T>> builderSupplier) {
        return ENTITIES.register(id, () -> builderSupplier.get().build(PantheonSent.MOD_ID + ":" + id));
    }

}
