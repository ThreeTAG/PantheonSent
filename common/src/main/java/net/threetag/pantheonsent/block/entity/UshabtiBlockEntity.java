package net.threetag.pantheonsent.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.threetag.palladium.power.SuperpowerUtil;
import net.threetag.palladium.util.PlayerUtil;
import net.threetag.pantheonsent.ability.PSAbilities;

import java.util.UUID;

public class UshabtiBlockEntity extends BlockEntity {

    public UUID owner;
    public int progress = 0;

    public UshabtiBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(PSBlockEntityTypes.USHABTI.get(), blockPos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        if (this.owner != null) {
            tag.putUUID("OwnerUUID", this.owner);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        if (tag.contains("OwnerUUID")) {
            this.owner = tag.getUUID("OwnerUUID");
        }
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, UshabtiBlockEntity blockEntity) {
        if (blockEntity.progress > 0) {
            var owner = level.getPlayerByUUID(blockEntity.owner);

            if (owner != null) {
                if (PSAbilities.hasMoonKnightPower(owner)) {
                    SuperpowerUtil.removeSuperpower(owner, PSAbilities.getMoonKnightPower(level));
                }

                RandomSource random = RandomSource.create();
                for (int i = 0; i < blockEntity.progress / 10; i++) {
                    Vec3 startPos = owner.position().subtract(owner.getBbWidth() / 2D, 0, owner.getBbWidth() / 2).add(random.nextFloat() * owner.getBbWidth(), random.nextFloat() * owner.getBbHeight(), random.nextFloat() * owner.getBbWidth());
                    float xSpeed = (float) ((pos.getX() + 0.5F - startPos.x) / 10F);
                    float ySpeed = (float) ((pos.getY() + 0.5F - startPos.y) / 10F);
                    float zSpeed = (float) ((pos.getZ() + 0.5F - startPos.z) / 10F);
                    PlayerUtil.spawnParticleForAll(level, 50, ParticleTypes.SMOKE, false, startPos.x, startPos.y, startPos.z, xSpeed, ySpeed, zSpeed, 1F, 0);
                }
            }

            blockEntity.progress--;
        }
    }
}
