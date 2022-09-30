package net.threetag.pantheonsent.entity;

import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.EntityAttributeRegistry;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;

import java.util.function.Supplier;

public class PSEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(PantheonSent.MOD_ID, Registry.ENTITY_TYPE_REGISTRY);

    public static final RegistrySupplier<EntityType<Khonshu>> KHONSHU = register("khonshu", () -> EntityType.Builder.<Khonshu>of(Khonshu::new, MobCategory.MISC).sized(1, 3));
    public static final RegistrySupplier<EntityType<CrescentDart>> CRESCENT_DART = register("crescent_dart", () -> EntityType.Builder.of(CrescentDart::new, MobCategory.MISC).sized(0.1F, 0.1F));

    public static <T extends Entity> RegistrySupplier<EntityType<T>> register(String id, Supplier<EntityType.Builder<T>> builderSupplier) {
        return ENTITIES.register(id, () -> builderSupplier.get().build(PantheonSent.MOD_ID + ":" + id));
    }

    public static void init() {
        EntityAttributeRegistry.register(KHONSHU, Mob::createMobAttributes);
    }

}
