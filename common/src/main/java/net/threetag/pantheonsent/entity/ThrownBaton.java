package net.threetag.pantheonsent.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;

public class ThrownBaton extends CrescentDart {

    public ThrownBaton(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }
}
