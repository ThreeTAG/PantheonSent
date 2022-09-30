package net.threetag.pantheonsent.entity;

import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.threetag.palladiumcore.network.NetworkManager;

public class CrescentDart extends AbstractArrow {

    public int rotation;
    public int prevRotation;

    public CrescentDart(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        super.tick();

        this.prevRotation = this.rotation;

        if (this.xo != this.getX() || this.yo != this.getY() || this.zo != this.getZ()) {
            this.rotation++;
        }
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        // TODO custom sound event
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public void playerTouch(Player player) {

    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);

        BlockState state = this.level.getBlockState(blockHitResult.getBlockPos());

        if (state.getMaterial() == Material.GLASS && state.getDestroySpeed(this.level, blockHitResult.getBlockPos()) <= 0.3F) {
            // TODO check if can break
            this.level.destroyBlock(blockHitResult.getBlockPos(), true);
        }
    }

    @Override
    public boolean shouldRender(double x, double y, double z) {
        return true;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkManager.createAddEntityPacket(this);
    }
}
