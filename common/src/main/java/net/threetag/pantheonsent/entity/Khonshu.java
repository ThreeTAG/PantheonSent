package net.threetag.pantheonsent.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.threetag.palladium.power.SuperpowerUtil;
import net.threetag.palladium.util.PlayerUtil;
import net.threetag.palladiumcore.network.ExtendedEntitySpawnData;
import net.threetag.palladiumcore.network.NetworkManager;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.network.DarknessMessage;
import net.threetag.pantheonsent.network.ForceThirdPersonMessage;
import net.threetag.pantheonsent.network.KhonshuTeleportMessage;
import net.threetag.pantheonsent.sound.PSSoundEvents;
import net.threetag.pantheonsent.util.PantheonSentProperties;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Khonshu extends PathfinderMob implements ExtendedEntitySpawnData {

    public Mode mode = Mode.WANDER;
    public UUID avatarId;
    public Player avatar;
    public int recruitingTimer;
    private static final EntityDataAccessor<Integer> DESPAWN_TIMER = SynchedEntityData.defineId(Khonshu.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> WIGGLE_ARM = SynchedEntityData.defineId(Khonshu.class, EntityDataSerializers.BOOLEAN);
    public int prevDespawnTimer = -1;
    public int prevWiggleArmsAnimation = 0;
    public int wiggleArmsAnimation = 0;

    public Khonshu(EntityType<? extends PathfinderMob> entityType, Level level) {
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
        this.entityData.define(WIGGLE_ARM, false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RandomStroll(this, 0.6));
        this.goalSelector.addGoal(2, new RandomLookAround(this));
        this.goalSelector.addGoal(4, new RecruitAvatarGoal());
        this.goalSelector.addGoal(7, new LookAtAvatarGoal());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 15.0);
    }

    public Player getAvatar() {
        if (this.avatar != null) {
            return this.avatar;
        } else if (this.avatarId != null) {
            Player player = this.level().getPlayerByUUID(this.avatarId);

            if (player != null) {
                return this.avatar = player;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean boundToAvatar() {
        return this.avatarId != null;
    }

    @Override
    public void tick() {
        super.tick();

        // Despawn Timer Logic
        int despawnTimer = this.getDespawnTimer();
        this.prevDespawnTimer = despawnTimer;

        if (despawnTimer > 0 && !this.level().isClientSide) {
            despawnTimer--;

            if (despawnTimer <= 0) {
                this.discard();
                despawnTimer = -1;
            }

            this.setDespawnTimer(despawnTimer);
        }


        // Arms Animation
        this.prevWiggleArmsAnimation = this.wiggleArmsAnimation;
        if (this.isWigglingArms() && this.wiggleArmsAnimation < 20) {
            this.wiggleArmsAnimation++;
        } else if (!this.isWigglingArms() && this.wiggleArmsAnimation > 0) {
            this.wiggleArmsAnimation--;
        }


        // Stalking
        if (this.mode == Mode.STALKING && !this.level().isClientSide && !this.isDespawning()) {
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

    public boolean isWigglingArms() {
        return this.entityData.get(WIGGLE_ARM);
    }

    public void setWiggleArms(boolean wiggle) {
        this.entityData.set(WIGGLE_ARM, wiggle);
    }

    public float getWiggleArmsProgress(float partialTicks) {
        return Mth.lerp(partialTicks, this.prevWiggleArmsAnimation, this.wiggleArmsAnimation) / 20F;
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
        compound.putBoolean("WiggleArms", this.isWigglingArms());
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
        this.setWiggleArms(compound.getBoolean("WiggleArms"));
    }

    @Override
    public void saveAdditionalSpawnData(FriendlyByteBuf buf) {
        buf.writeBoolean(this.avatarId != null);
        if (this.avatarId != null)
            buf.writeUUID(this.avatarId);
        buf.writeInt(this.mode.ordinal());
    }

    @Override
    public void loadAdditionalSpawnData(FriendlyByteBuf buf) {
        if (buf.readBoolean())
            this.avatarId = buf.readUUID();
        this.mode = Mode.values()[buf.readInt()];
    }

    @Override
    public @NotNull PushReaction getPistonPushReaction() {
        return PushReaction.IGNORE;
    }

    @Override
    public boolean isInvulnerable() {
        return super.isInvulnerable();
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if (source.getEntity() instanceof Player player) {
            return !player.isCreative();
        }
        return !source.is(DamageTypes.FELL_OUT_OF_WORLD);
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
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(Entity entity) {

    }

    @Override
    protected void pushEntities() {

    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkManager.createAddEntityPacket(this);
    }

    public static Vec3 findRandomPos(BlockPos center, Khonshu khonshu, Player player, Level level, int minRange, int maxRange, int maxYOffset) {
        return findRandomPos(center, khonshu, player, level, minRange, maxRange, maxYOffset, 50);
    }

    public static Vec3 findRandomPos(BlockPos center, Khonshu khonshu, Player player, Level level, int minRange, int maxRange, int maxYOffset, int attempts) {
        if (attempts == 0) {
            return new Vec3(center.getX() + 0.5F, center.getY(), center.getZ() + 0.5F);
        }

        RandomSource rand = RandomSource.create();
        int x = (int) (center.getX() + (minRange + (maxRange - minRange) * rand.nextFloat()) * (rand.nextBoolean() ? 1F : -1F));
        int y = center.getY() + maxYOffset;
        int z = (int) (center.getZ() + (minRange + (maxRange - minRange) * rand.nextFloat()) * (rand.nextBoolean() ? 1F : -1F));

        for (int i = y; i > center.getY() - 20; i--) {
            BlockPos pos = new BlockPos(x, i, z);
            if (!level.isEmptyBlock(pos.below()) && level.isEmptyBlock(pos) && level.isEmptyBlock(pos.above())) {
                Vec3 position = new Vec3(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);

                if (hasLineOfSight(khonshu, position.add(0, khonshu.getEyeHeight(), 0), player)) {
                    return position;
                }
            }
        }

        return findRandomPos(center, khonshu, player, level, minRange, maxRange, maxYOffset, attempts - 1);
    }

    public static boolean hasLineOfSight(Khonshu khonshu, Vec3 eyePos, Entity entity) {
        Vec3 target = entity.getEyePosition();
        if (target.distanceTo(eyePos) > 128.0) {
            return false;
        } else {
            return entity.level().clip(new ClipContext(eyePos, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, khonshu)).getType() == HitResult.Type.MISS;
        }
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
                if (avatar instanceof ServerPlayer serverPlayer) {
                    new DarknessMessage(120).send(serverPlayer);
                }
            } else if (timer == 20 || timer == 7 * 20 || timer == 13 * 20) {
                int i = timer == 20 ? 3 : (timer == 7 * 20 ? 2 : 1);
                var pos = findRandomPos(avatar.getOnPos(), Khonshu.this, avatar, Khonshu.this.level(), 3, i * 2 + 1, 15);
                new KhonshuTeleportMessage(Khonshu.this, pos).sendToTracking(Khonshu.this);
                Khonshu.this.teleportTo(pos.x(), pos.y(), pos.z());
                PlayerUtil.playSound(avatar, pos.x, pos.y, pos.z, SoundEvents.AMBIENT_CAVE.value(), SoundSource.AMBIENT);
            } else if (timer == 19 * 20) {
                var pos = findRandomPos(avatar.getOnPos(), Khonshu.this, avatar, Khonshu.this.level(), 2, 5, 7);
                new KhonshuTeleportMessage(Khonshu.this, pos).sendToTracking(Khonshu.this);
                Khonshu.this.teleportTo(pos.x(), pos.y(), pos.z());
                PlayerUtil.playSound(avatar, pos.x, pos.y, pos.z, SoundEvents.AMBIENT_CAVE.value(), SoundSource.AMBIENT);
            } else if (timer == MAX_TIME) {
                Khonshu.this.setDespawnTimer(60);
                Khonshu.this.setWiggleArms(false);
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

            if (line == 4 && avatar instanceof ServerPlayer serverPlayer) {
                Khonshu.this.setWiggleArms(true);
                new ForceThirdPersonMessage(MAX_TIME - timer).send(serverPlayer);
            }

            if (timer == 24 * 20) {
                SuperpowerUtil.addSuperpower(avatar, PantheonSent.id("moon_knight_transformation"));
                PlayerUtil.playSound(avatar, avatar.getX(), avatar.getEyeY(), avatar.getZ(), PSSoundEvents.MOON_KNIGHT_TRANSFORMATION.get(), SoundSource.PLAYERS);
            }

            if (line > 0) {
                avatar.displayClientMessage(Component.translatable("entity.pantheonsent.khonshu.recruitment_line_" + line), true);
            }

            Khonshu.this.recruitingTimer++;
            PantheonSentProperties.KHONSHU_RECRUITING_TIMER.set(avatar, MAX_TIME - timer);
        }

        @Override
        public void stop() {
            super.stop();
            Khonshu.this.setWiggleArms(false);
        }
    }

    public class RandomStroll extends WaterAvoidingRandomStrollGoal {

        public RandomStroll(PathfinderMob pathfinderMob, double d) {
            super(pathfinderMob, d);
        }

        @Override
        public boolean canUse() {
            return Khonshu.this.mode == Mode.WANDER && super.canUse();
        }
    }

    public class RandomLookAround extends RandomLookAroundGoal {

        public RandomLookAround(Mob mob) {
            super(mob);
        }

        @Override
        public boolean canUse() {
            return Khonshu.this.mode == Mode.WANDER && super.canUse();
        }
    }

    public enum Mode {

        WANDER,
        RECRUITING,
        STALKING;

    }

}
