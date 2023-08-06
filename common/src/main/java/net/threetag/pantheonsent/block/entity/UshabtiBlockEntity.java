package net.threetag.pantheonsent.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.threetag.palladium.power.SuperpowerUtil;
import net.threetag.palladium.util.PlayerUtil;
import net.threetag.pantheonsent.ability.PSAbilities;
import net.threetag.pantheonsent.block.UshabtiBlock;
import net.threetag.pantheonsent.client.particle.PSParticleTypes;

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

        tag.putInt("Progress", this.progress);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        if (tag.contains("OwnerUUID")) {
            this.owner = tag.getUUID("OwnerUUID");
        }

        if (tag.contains("Progress")) {
            this.progress = tag.getInt("Progress");
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = super.getUpdateTag();
        compoundTag.putInt("Progress", this.progress);
        if (this.owner != null) {
            compoundTag.putUUID("OwnerUUID", this.owner);
        }
        return compoundTag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, UshabtiBlockEntity blockEntity) {
        if (blockEntity.progress > 0) {
            var owner = level.getPlayerByUUID(blockEntity.owner);

            if (blockEntity.progress == 1 & owner != null) {
                if (PSAbilities.hasMoonKnightPower(owner)) {
                    SuperpowerUtil.removeSuperpower(owner, PSAbilities.getMoonKnightPower(level));
                    level.setBlock(pos, state.setValue(UshabtiBlock.USED, true), 3);
                    PlayerUtil.playSoundToAll(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 50, SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS);
                }
            }

            blockEntity.progress--;
        }
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, UshabtiBlockEntity blockEntity) {
        if (blockEntity.progress > 0) {
            var owner = level.getPlayerByUUID(blockEntity.owner);

            if (owner != null) {
                RandomSource random = RandomSource.create();
                var playerPos = owner.position().add(0, owner.getBbHeight() / 2D, 0);
                var offset = playerPos.subtract(new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));
                for (int i = 0; i < blockEntity.progress / 20; i++) {
                    level.addParticle(
                            PSParticleTypes.HIEROGLYPH.get(),
                            (double) pos.getX() + 0.5,
                            (double) pos.getY() + 1.5,
                            (double) pos.getZ() + 0.5,
                            (float) offset.x() + random.nextFloat() - 0.5,
                            (float) offset.y() + random.nextFloat() - 0.5,
                            (float) offset.z() + random.nextFloat() - 0.5
                    );
                }
            }

            blockEntity.progress--;
        }
    }
}
