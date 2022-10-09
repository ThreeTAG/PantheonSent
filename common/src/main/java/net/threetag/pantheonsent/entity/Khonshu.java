package net.threetag.pantheonsent.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
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
import net.threetag.pantheonsent.ability.GodStalkedAbility;
import net.threetag.pantheonsent.util.PantheonSentProperties;

import java.util.UUID;

public class Khonshu extends Mob implements ExtendedEntitySpawnData {

    public Mode mode;
    public UUID avatarId;
    public Player avatar;
    public int recruitingTimer;
    private static final EntityDataAccessor<Integer> DESPAWN_TIMER = SynchedEntityData.defineId(Khonshu.class, EntityDataSerializers.INT);
    public int prevDespawnTimer = -1;

    public Khonshu(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }

    public Khonshu(Level level, Player avatar) {
        super(PSEntityTypes.KHONSHU.get(), level);
        this.avatarId = avatar.getUUID();
        this.avatar = avatar;
        this.setPos(avatar.position());
    }

    public Khonshu(Level level, Player avatar, Mode mode) {
        this(level, avatar);
        this.mode = mode;
        this.recruitingTimer = 0;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DESPAWN_TIMER, -1);
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
    public void tick() {
        super.tick();

        // Despawn Timer Logic
        int despawnTimer = this.getDespawnTimer();
        this.prevDespawnTimer = despawnTimer;

        if (despawnTimer > 0 && !this.level.isClientSide) {
            despawnTimer--;

            if (despawnTimer <= 0) {
                this.discard();
                despawnTimer = -1;
            }

            this.setDespawnTimer(despawnTimer);
        }


        // Stalking
        if (this.mode == Mode.STALKING && !this.level.isClientSide && !this.isDespawning()) {
            var avatar = this.getAvatar();

            if (avatar == null || this.distanceTo(avatar) <= 15 || this.tickCount >= 20 * 60) {
                this.setDespawnTimer(60);
            }
        }
    }

    @Override
    public boolean isInvisibleTo(Player player) {
        return this.avatarId == null || !this.avatarId.equals(player.getUUID());
    }

    public int getDespawnTimer() {
        return this.entityData.get(DESPAWN_TIMER);
    }

    public float getDespawnTimer(float partialTicks) {
        return Mth.lerp(partialTicks, this.prevDespawnTimer, this.getDespawnTimer());
    }

    public void setDespawnTimer(int timer) {
        this.entityData.set(DESPAWN_TIMER, timer);
    }

    public boolean isDespawning() {
        return this.getDespawnTimer() >= 0;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.avatarId != null) {
            compound.putUUID("AvatarUUID", this.avatarId);
        }
        compound.putInt("Mode", this.mode.ordinal());
        compound.putInt("RecruitingTimer", this.recruitingTimer);
        compound.putInt("DespawnTimer", this.getDespawnTimer());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("AvatarUUID")) {
            this.avatarId = compound.getUUID("AvatarUUID");
        }
        this.mode = Mode.values()[compound.getInt("Mode")];
        this.recruitingTimer = compound.getInt("RecruitingTimer");
        this.setDespawnTimer(compound.getInt("DespawnTimer"));
    }

    @Override
    public void saveAdditionalSpawnData(FriendlyByteBuf buf) {
        buf.writeUUID(this.avatarId);
        buf.writeInt(this.mode.ordinal());
    }

    @Override
    public void loadAdditionalSpawnData(FriendlyByteBuf buf) {
        this.avatarId = buf.readUUID();
        this.mode = Mode.values()[buf.readInt()];
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
    public boolean isInvulnerableTo(DamageSource source) {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        return false;
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
            return Khonshu.this.mode == Mode.RECRUITING && Khonshu.this.getAvatar() != null;
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
                int i = timer == 20 ? 3 : (timer == 7 * 20 ? 2 : 1);
                GodStalkedAbility.teleportRandom(avatar.getOnPos(), Khonshu.this, avatar, Khonshu.this.level, i * 3, i * 3 + 3, 7, 10);
            } else if (timer == 19 * 20) {
                double g = avatar.getX() + (avatar.getRandom().nextDouble() - 0.5) * 3.0;
                double h = avatar.getY();
                double j = avatar.getZ() + (avatar.getRandom().nextDouble() - 0.5) * 3.0;
                var pos = GodStalkedAbility.teleportRandom(avatar.getOnPos(), Khonshu.this, avatar, Khonshu.this.level, 5, 20, 7, 10);
                Khonshu.this.randomTeleport(g, h, j, false);
            } else if (timer == MAX_TIME) {
                Khonshu.this.setDespawnTimer(60);
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

    public enum Mode {

        RECRUITING,
        STALKING;

    }

}
