package com.sweegy.invasioncodered.entity.cuccita;

import com.sweegy.invasioncodered.entity.ai.CopyOwnerTargetGoal;
import com.sweegy.invasioncodered.entity.ravager_hunter.RavagerHunterEntity;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModEntities;
import com.sweegy.invasioncodered.interfaces.IUsingSimpleFadingAnims;
import com.sweegy.invasioncodered.interfaces.SimpleFadingOutAnimation;
import com.sweegy.invasioncodered.util.animation.EntityModelCache;
import com.sweegy.invasioncodered.util.animation.SimpleFadingAnimationController;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

import javax.annotation.Nullable;
import java.util.UUID;

public class CuccitaEntity extends Monster implements TraceableEntity, IUsingSimpleFadingAnims {
    public static final EntityDataAccessor<Byte> DATA_actionState = SynchedEntityData.defineId(CuccitaEntity.class, EntityDataSerializers.BYTE);

    private final SimpleFadingAnimationController simpleFade =
            new SimpleFadingAnimationController(3.0F, 3.0F);
    private final EntityModelCache animCache = new EntityModelCache();

    private int animIdx = 0;
    private LivingEntity owner;
    private UUID ownerUUID;
    @Nullable
    private UUID pendingLeashHolderUUID;
    @Nullable
    private BlockPos pendingLeashFencePos;

    public CuccitaEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(InvasioncoderedsweegyportModEntities.CUCCITA.get(), world);
    }

    public CuccitaEntity(EntityType<? extends CuccitaEntity> type, Level level) {
        super(type, level);
        this.xpReward = 10;
    }

    public EntityModelCache getAnimCache() {
        return animCache;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_actionState, (byte) 0);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 200.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.32D)
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.ARMOR, 0.0D);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.24D, true));
        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.3D));
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        targetSelector.addGoal(0, new CopyOwnerTargetGoal<>(this));
        targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    public void setOwner(@javax.annotation.Nullable LivingEntity p_36939_) {
        this.owner = p_36939_;
        this.ownerUUID = p_36939_ == null ? null : p_36939_.getUUID();
    }

    @Nullable
    public LivingEntity getOwner() {
        if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel) this.level()).getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity) entity;
            }
        }
        return this.owner;
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return true;
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return !(target instanceof RavagerHunterEntity)
                && !(target instanceof StoneCutterManEntity)
                && super.canAttack(target);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Entity sourceEntity = source.getEntity();
        if (sourceEntity != null && isAlliedTo(sourceEntity)) {
            return false;
        }
        super.tickLeash();
        return super.hurt(source, amount);
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if(level().isClientSide()){
            handleClientAnimation();
        }
    }

    /** Reconnect without invoking PathfinderMob's vanilla 10-block leash logic. */
    private void restoreExtendedLeash(ServerLevel server) {
        if (pendingLeashHolderUUID != null) {
            Entity found = server.getEntity(pendingLeashHolderUUID);
            if (found == null) return; // Holder has not loaded yet: retry next tick.

            pendingLeashHolderUUID = null;
            if (found.isAlive() && found.level() == level()) {
                setLeashedTo(found, true);
            } else {
                clearRestriction();
            }
            return;
        }

        if (pendingLeashFencePos != null) {
            BlockPos pos = pendingLeashFencePos;
            if (!server.hasChunkAt(pos)) return;

            pendingLeashFencePos = null;
            if (server.getBlockState(pos).getBlock() instanceof FenceBlock) {
                setLeashedTo(LeashFenceKnotEntity.getOrCreateKnot(server, pos), true);
            } else {
                clearRestriction();
            }
        }
    }

    private static final float LEASH_FOLLOW_DISTANCE = 20.0F;
    private static final float LEASH_PULL_DISTANCE = 20.0F;
    private static final float LEASH_BREAK_DISTANCE = 50;

    @Override
    protected void tickLeash() {
        if (level().isClientSide()) return;

        Entity holder = getLeashHolder();
        if (holder == null && level() instanceof ServerLevel server) {
            restoreExtendedLeash(server);
            holder = getLeashHolder();
        }

        if (holder == null) {
            // No leash, or its saved holder has not loaded yet.
            // In either case do NOT call dropLeash().
            if (pendingLeashHolderUUID == null && pendingLeashFencePos == null) {
                clearRestriction();
            }
            return;
        }

        // An actual attached leash supersedes any saved pending leash.
        pendingLeashHolderUUID = null;
        pendingLeashFencePos = null;

        if (!holder.isAlive() || holder.level() != level()) {
            dropLeash(true, true);
            clearRestriction();
            return;
        }

        float distance = distanceTo(holder);
        restrictTo(holder.blockPosition(), (int) LEASH_BREAK_DISTANCE - 5);
        onLeashDistance(distance);

        if (distance > LEASH_BREAK_DISTANCE) {
            dropLeash(true, true);
            clearRestriction();
            return;
        }

        // Keep the user's combat-target condition unchanged.
        if (distance > LEASH_FOLLOW_DISTANCE
                && (getTarget() == null || !getTarget().isAlive())) {
            if ((tickCount & 3) == 0) {
                getNavigation().moveTo(holder, 1.25D);
            }
        }

        if (distance > LEASH_PULL_DISTANCE) {
            Vec3 direction = holder.position().subtract(position()).normalize();
            double strength = 0.12D;
            setDeltaMovement(getDeltaMovement().add(
                    direction.x * strength,
                    direction.y * strength,
                    direction.z * strength));
            hasImpulse = true;
        }
    }


    private void handleClientAnimation(){
        simpleFade.transitionTo(animIdx);
        simpleFade.tick();
        if(swinging){
            animIdx = 2;
        } else if (walkAnimation.isMoving()) {
            animIdx = 1;
        } else{
            animIdx = 0;
        }
    }

    @Override
    protected void updateSwingTime() {
        int i = 10;
        if (this.swinging) {
            ++this.swingTime;
            if (this.swingTime >= i) {
                this.swingTime = 0;
                this.swinging = false;
            }
        } else {
            this.swingTime = 0;
        }

        this.attackAnim = (float)this.swingTime / (float)i;
    }

    @Override
    public boolean isAlliedTo(net.minecraft.world.entity.Entity entity) {
        if (entity instanceof RavagerHunterEntity || entity instanceof StoneCutterManEntity) {
            return true;
        }
        return (getOwner() == entity) || super.isAlliedTo(entity);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.ownerUUID != null) {
            compound.putUUID("Owner", this.ownerUUID);
        }
        if (getLeashHolder() != null) return;
        if (pendingLeashHolderUUID != null) {
            CompoundTag leash = new CompoundTag();
            leash.putUUID("UUID", pendingLeashHolderUUID);
            compound.put("Leash", leash);
        } else if (pendingLeashFencePos != null) {
            CompoundTag leash = new CompoundTag();
            leash.putInt("X", pendingLeashFencePos.getX());
            leash.putInt("Y", pendingLeashFencePos.getY());
            leash.putInt("Z", pendingLeashFencePos.getZ());
            compound.put("Leash", leash);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.hasUUID("Owner")) {
            this.ownerUUID = compound.getUUID("Owner");
        }
        pendingLeashHolderUUID = null;
        pendingLeashFencePos = null;

        if (!compound.contains("Leash", Tag.TAG_COMPOUND)) return;
        CompoundTag leash = compound.getCompound("Leash");
        if (leash.hasUUID("UUID")) {
            pendingLeashHolderUUID = leash.getUUID("UUID");
        } else if (leash.contains("X", Tag.TAG_INT)
                && leash.contains("Y", Tag.TAG_INT)
                && leash.contains("Z", Tag.TAG_INT)) {
            pendingLeashFencePos = new BlockPos(
                    leash.getInt("X"), leash.getInt("Y"), leash.getInt("Z"));
        }
    }

    @Override
    public float getFadeInTime(float partialTicks) {
        return simpleFade.getFadeInTime(partialTicks);
    }

    @Override
    public float getAnimTicks(float partialTicks) {
        return simpleFade.getAnimTicks(partialTicks);
    }

    @Override
    public int getAnimIndex() {
        return simpleFade.getAnimIndex();
    }

    @Override
    @javax.annotation.Nullable
    public SimpleFadingOutAnimation getFadingOutAnimation() {
        return simpleFade.getFadingOutAnimation();
    }

    @Override
    public void setFadingOutAnimation(@javax.annotation.Nullable SimpleFadingOutAnimation animation) {
        simpleFade.setFadingOutAnimation(animation);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WOLF_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.WOLF_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WOLF_DEATH;
    }
}
