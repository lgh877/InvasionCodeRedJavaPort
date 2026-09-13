package com.sweegy.invasioncodered.entity.gashslit;

import com.sweegy.invasioncodered.entity.projectile.DamageConfigurableSmallFireballEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import java.util.UUID;

import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModEntities;
import com.sweegy.invasioncodered.entity.ai.VexLikeRandomMoveGoal;
import com.sweegy.invasioncodered.entity.ai.DoNothingGoalUniversal;
import com.sweegy.invasioncodered.entity.ai.CopyOwnerTargetGoal;

public class GashslitDragonEntity extends Monster implements TraceableEntity {
	public static final EntityDataAccessor<Boolean> DATA_isShooting = SynchedEntityData.defineId(GashslitDragonEntity.class, EntityDataSerializers.BOOLEAN);

	public GashslitDragonEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(InvasioncoderedsweegyportModEntities.GASHSLIT_DRAGON.get(), world);
	}

	public GashslitDragonEntity(EntityType<GashslitDragonEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 0;
		setNoAi(false);
		setPersistenceRequired();
		this.moveControl = new FlyingMoveControl(this, 10, true);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_isShooting, false);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new FlyingPathNavigation(this, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		lookControl = new LookControl(this) {
			protected boolean resetXRotOnTick() {
				return getShootingState();
			}
		};
		goalSelector.addGoal(0, new DoNothingGoalUniversal(this) {
			@Override
			public boolean isInAction() {
				return getShootingState();
			}
		});
		goalSelector.addGoal(1, new VexLikeRandomMoveGoal(this) {
		});
		targetSelector.addGoal(0, new CopyOwnerTargetGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, false, false));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(4, new FloatGoal(this));
	}

	public float flyAnimTicksO, flyAnimTicks;
	public int attackPoseO, attackPose, actionTicks, lifeTime = -1;
	public boolean hasLimitedLife = false;

	public boolean getShootingState() {
		return entityData.get(DATA_isShooting);
	}

	public void setShootingState(boolean input) {
		entityData.set(DATA_isShooting, input);
	}

	private LivingEntity owner;
	private UUID ownerUUID;

	public void setOwner(@Nullable LivingEntity p_36939_) {
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

	public boolean isAlliedTo(Entity entity) {
		if (super.isAlliedTo(entity)) {
			return true;
		}
		return getOwner() == entity;
	}

	public void setLimitedLife(int input) {
		this.hasLimitedLife = true;
		this.lifeTime = input;
	}

	public float getShootAnimation(float partialTicks) {
		return Mth.lerp(partialTicks, (float) attackPoseO, (float) attackPose) * 0.2f;
	}

	public float getAnimationTicks(float partialTicks) {
		return Mth.lerp(partialTicks, flyAnimTicksO, flyAnimTicks);
	}

	public float getVoicePitch() {
		return 2f;
	}

	{
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return InvasioncoderedsweegyportModSounds.GASH_DRAGON_HURT.get();
	}

	@Override
	public SoundEvent getDeathSound() {
        return InvasioncoderedsweegyportModSounds.GASH_DRAGON_DEATH.get();
	}

	@Override
	public boolean causeFallDamage(float l, float d, DamageSource source) {
		return false;
	}

	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		if (damagesource.is(DamageTypes.IN_FIRE))
			return false;
		return super.hurt(damagesource, amount);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("DataisShooting", this.entityData.get(DATA_isShooting));
		if (this.hasLimitedLife) {
			compound.putInt("LifeTicks", this.lifeTime);
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("DataisShooting"))
			this.entityData.set(DATA_isShooting, compound.getBoolean("DataisShooting"));
		if (compound.contains("LifeTicks")) {
			this.setLimitedLife(compound.getInt("LifeTicks"));
		}
	}

	@Override
	public void baseTick() {
		super.baseTick();
        if (level().isClientSide()) {
            flyAnimTicksO = flyAnimTicks;
            flyAnimTicks += 1 + walkAnimation.speed(0);
            attackPoseO = attackPose;
            if (getShootingState()) {
                attackPose = Math.min(5, attackPose + 1);
            } else {
                attackPose = Math.max(0, attackPose - 1);
            }
        }
        if (!level().isClientSide()) {
            boolean isShooting = this.getShootingState();
            if (this.hasLimitedLife && --this.lifeTime < 0) {
                this.lifeTime = 20;
                this.hurt(this.damageSources().starve(), 1.0F);
            }
            if (!isShooting) {
                if ((this.tickCount & 31) == 0 && this.getRandom().nextDouble() > 0.4) {
                    LivingEntity target = this.getTarget();
                    if (target != null) {
                        double dist = this.position().subtract(target.position().add(0, target.getBbHeight() / 2, 0)).length();
                        double minDist = (target.getBbWidth() + target.getBbHeight()) / 2;
                        if (dist < minDist + 32) {
                            this.setShootingState(true);
                        }
                    }
                }
            } else {
                int actionTicks = this.actionTicks++;
                LivingEntity target = this.getTarget();
                if (target != null) {
                    this.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(target.getX(), target.getY() + target.getBbHeight() * 0.5, target.getZ()));
                }
                if (actionTicks == 10) {
                    Vec3 lookAngle = this.getLookAngle();
                    DamageConfigurableSmallFireballEntity smallfireball = new DamageConfigurableSmallFireballEntity(this.level(), this, this.getRandom().triangle(lookAngle.x(), 1), lookAngle.y(), this.getRandom().triangle(lookAngle.z(), 1));
                    smallfireball.baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
                    smallfireball.knockback = this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
                    smallfireball.setPos(smallfireball.getX(), this.getEyeY() - this.getBbWidth() * 0.416, smallfireball.getZ());
                    this.level().addFreshEntity(smallfireball);
                    this.playSound(SoundEvents.BLAZE_SHOOT, 1f, 1f);
                } else if (actionTicks > 15) {
                    this.setShootingState(false);
                    this.actionTicks = 0;
                }
            }
        }
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	}

	@Override
	public void setNoGravity(boolean ignored) {
		super.setNoGravity(true);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.setNoGravity(true);
	}

	@Override
	protected float getFlyingSpeed() {
		return (float) this.getAttributeValue(Attributes.FLYING_SPEED);
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 5);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 6);
		builder = builder.add(Attributes.ATTACK_KNOCKBACK, 1);
		builder = builder.add(Attributes.FOLLOW_RANGE, 64);
		builder = builder.add(Attributes.FLYING_SPEED, 1);
		return builder;
	}
}