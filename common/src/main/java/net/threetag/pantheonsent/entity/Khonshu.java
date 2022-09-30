package net.threetag.pantheonsent.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;
import net.threetag.palladium.util.property.PalladiumProperties;
import net.threetag.palladiumcore.network.ExtendedEntitySpawnData;
import net.threetag.palladiumcore.network.NetworkManager;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.util.PantheonSentProperties;

import java.util.UUID;

public class Khonshu extends Mob implements ExtendedEntitySpawnData {

    public UUID avatarId;
    public Player avatar;
    public boolean recruiting;
    public int recruitingTimer;

    public Khonshu(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }

    public Khonshu(Level level, Player avatar) {
        super(PSEntityTypes.KHONSHU.get(), level);
        this.avatarId = avatar.getUUID();
        this.avatar = avatar;
        this.setPos(avatar.position());
    }

    public Khonshu(Level level, Player avatar, boolean recruiting) {
        this(level, avatar);
        this.recruiting = recruiting;
        this.recruitingTimer = 0;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new RecruitAvatarGoal());
        this.goalSelector.addGoal(7, new LookAtAvatarGoal());
    }

    public Player getAvatar() {
        if (this.avatar != null) {
            return this.avatar;
        } else if (this.avatarId != null) {
            Player player = this.level.getPlayerByUUID(this.avatarId);

            if (player != null) {
                return this.avatar = player;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean isInvisibleTo(Player player) {
        return this.avatarId == null || !this.avatarId.equals(player.getUUID());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putUUID("AvatarUUID", this.avatarId);
        compound.putBoolean("Recruiting", this.recruiting);
        compound.putInt("RecruitingTimer", this.recruitingTimer);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.avatarId = compound.getUUID("AvatarUUID");
        this.recruiting = compound.getBoolean("Recruiting");
        this.recruitingTimer = compound.getInt("RecruitingTimer");
    }

    @Override
    public void saveAdditionalSpawnData(FriendlyByteBuf buf) {
        buf.writeUUID(this.avatarId);
        buf.writeBoolean(this.recruiting);
    }

    @Override
    public void loadAdditionalSpawnData(FriendlyByteBuf buf) {
        this.avatarId = buf.readUUID();
        this.recruiting = buf.readBoolean();
    }

    @Override
    public PushReaction getPistonPushReaction() {
        return PushReaction.IGNORE;
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkManager.createAddEntityPacket(this);
    }

    class LookAtAvatarGoal extends Goal {

        @Override
        public boolean canUse() {
            Player player = Khonshu.this.getAvatar();
            return player != null;
        }

        @Override
        public void tick() {
            Player avatar = Khonshu.this.getAvatar();
            Khonshu.this.getLookControl().setLookAt(avatar.getX(), avatar.getEyeY(), avatar.getZ());
        }
    }

    class RecruitAvatarGoal extends Goal {

        public static final int MAX_TIME = 20 * 30;

        @Override
        public boolean canUse() {
            return Khonshu.this.recruiting && Khonshu.this.getAvatar() != null;
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && Khonshu.this.recruitingTimer > 0;
        }

        @Override
        public void tick() {
            Player avatar = Khonshu.this.getAvatar();

            int timer = Khonshu.this.recruitingTimer;

            if (timer == 0 || timer == 6 * 20 || timer == 12 * 20 || timer == 18 * 20) {
                avatar.forceAddEffect(new MobEffectInstance(MobEffects.BLINDNESS, 120), null);
            } else if (timer == 20 || timer == 7 * 20 || timer == 13 * 20) {
                double g = avatar.getX() + (avatar.getRandom().nextDouble() - 0.5) * 16.0;
                double h = Mth.clamp(avatar.getY() + (double) (avatar.getRandom().nextInt(16) - 8), level.getMinBuildHeight(), level.getMinBuildHeight() + ((ServerLevel) level).getLogicalHeight() - 1);
                double j = avatar.getZ() + (avatar.getRandom().nextDouble() - 0.5) * 16.0;
                Khonshu.this.randomTeleport(g, h, j, false);
            } else if (timer == 19 * 20) {
                double g = avatar.getX() + (avatar.getRandom().nextDouble() - 0.5) * 3.0;
                double h = avatar.getY();
                double j = avatar.getZ() + (avatar.getRandom().nextDouble() - 0.5) * 3;
                Khonshu.this.randomTeleport(g, h, j, false);
            } else if (timer == MAX_TIME) {
                Khonshu.this.recruiting = false;
            }

            int line = 0;
            if (timer == 3 * 20)
                line = 1;
            else if (timer == 9 * 20)
                line = 2;
            else if (timer == 15 * 20)
                line = 3;
            else if (timer == 21 * 20)
                line = 4;
            else if (timer == 24 * 20)
                line = 5;
            else if (timer == 27 * 20)
                line = 6;

            if (timer == 24 * 20) {
                PalladiumProperties.SUPERPOWER_ID.set(avatar, PantheonSent.id("moon_knight_transformation"));
            }

            if (line > 0) {
                avatar.displayClientMessage(Component.translatable("entity.pantheonsent.khonshu.recruitment_line_" + line), true);
            }

            Khonshu.this.recruitingTimer++;
            PantheonSentProperties.KHONSHU_RECRUITING_TIMER.set(avatar, MAX_TIME - timer);
        }
    }
}
