package com.sweegy.invasioncodered.entity.gashslit;

import com.sweegy.invasioncodered.client.model.animations.indices.GashslitAnimationIndex;
import com.sweegy.invasioncodered.entity.ai.DoNothingGoal;
import com.sweegy.invasioncodered.entity.ai.RecentAttackerTracker;
import com.sweegy.invasioncodered.entity.gashslit.actions.GashslitActionSelector;
import com.sweegy.invasioncodered.entity.gashslit.actions.GashslitActions;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import com.sweegy.invasioncodered.entity.ai.actions.ActionCommand;
import com.sweegy.invasioncodered.interfaces.FadingOutAnimation;
import com.sweegy.invasioncodered.interfaces.IActionStateMob;
import com.sweegy.invasioncodered.interfaces.IUsingFadingAnims;
import com.sweegy.invasioncodered.util.ServerLevelRelatedUtils;
import com.sweegy.invasioncodered.network.SyncBossBarRenderPacket;
import com.sweegy.invasioncodered.util.animation.EntityModelCache;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

import java.util.List;
import java.util.ArrayList;

import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModParticleTypes;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModItems;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModEntities;

public class GashslitEntity extends Raider implements IActionStateMob, IUsingFadingAnims {
	public static final EntityDataAccessor<Byte> DATA_actionState = SynchedEntityData.defineId(GashslitEntity.class, EntityDataSerializers.BYTE);
	public static final EntityDataAccessor<Byte> DATA_animSpeed = SynchedEntityData.defineId(GashslitEntity.class, EntityDataSerializers.BYTE);
	public static final EntityDataAccessor<Boolean> DATA_updateAnim = SynchedEntityData.defineId(GashslitEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> DATA_isInAction = SynchedEntityData.defineId(GashslitEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Byte> DATA_phase = SynchedEntityData.defineId(GashslitEntity.class, EntityDataSerializers.BYTE);
	public static final EntityDataAccessor<Byte> DATA_swingType = SynchedEntityData.defineId(GashslitEntity.class, EntityDataSerializers.BYTE);
	public final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.RED, ServerBossEvent.BossBarOverlay.PROGRESS);
    private EntityModelCache animCache;
    private ActionCommand<GashslitEntity> currentAction;

	public GashslitEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(InvasioncoderedsweegyportModEntities.GASHSLIT.get(), world);
	}

	public GashslitEntity(EntityType<GashslitEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(1.6f);
		xpReward = 450;
		noCulling = true;
		setNoAi(false);
		setPersistenceRequired();
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(InvasioncoderedsweegyportModItems.ROSE_DIAMOND_SWORD.get()));
		this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(InvasioncoderedsweegyportModItems.ROSE_DIAMOND_SWORD.get()));
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_actionState, (byte) 0);
		this.entityData.define(DATA_animSpeed, (byte) 1);
		this.entityData.define(DATA_updateAnim, false);
		this.entityData.define(DATA_isInAction, false);
		this.entityData.define(DATA_swingType, (byte) 0);
		this.entityData.define(DATA_phase, (byte) 0);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		lookControl = new LookControl(this) {
			protected boolean resetXRotOnTick() {
				return GashslitEntity.this.getActionState() == 0;
			}
		};
		goalSelector.addGoal(0, new DoNothingGoal(this));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, false) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return -1;
			}
		});
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, LivingEntity.class, false, false) {
			protected AABB getTargetSearchArea(double p_26069_) {
				return this.mob.getBoundingBox().inflate(p_26069_, p_26069_ * 0.5, p_26069_);
			}
		});
		this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(4, new FloatGoal(this));
	}

	public final RecentAttackerTracker attackerTracker = new RecentAttackerTracker(this, 8);
	private boolean isProcessingDamage = true;

	@Override
	public boolean isOnFire() {
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.IN_FIRE))
			return false;
		if (this.getActionState() == GashslitAnimationIndex.anims_block) {
			amount = 0.0F;
		}
		GashslitCurrentStates formerState = this.getCrackiness();
		this.isProcessingDamage = true;
		boolean tookDamage = super.hurt(source, amount);
		this.isProcessingDamage = false;
		if (tookDamage && this.isAlive() && !this.level().isClientSide()) {
			GashslitCurrentStates currentState = this.getCrackiness();
			if (currentState != formerState) {
				this.applyPhaseChangeEffects(currentState);
			}
			if (source.getEntity() instanceof LivingEntity attacker) {
				this.attackerTracker.onAttackedBy(attacker);
			}
		}
		return tookDamage;
	}

	private void applyPhaseChangeEffects(GashslitCurrentStates state) {
		switch (state) {
			case FIRST :
				this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 800, 0));
				break;
			case SECOND :
				this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2400, 1));
				this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 780, 0));
				SyncBossBarRenderPacket.setBossbarType(this, this.bossInfo, 2);
				entityData.set(DATA_phase, (byte) 1);
				break;
			case THIRD :
				this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 780, 1));
				break;
			case FINAL :
				float bbWidth = this.getBbWidth();
				ServerLevelRelatedUtils.sendParticles((ServerLevel) this.level(), InvasioncoderedsweegyportModParticleTypes.GASHSLIT_POPFLAME_PARTICLE.get(), this.getX(), this.getY() + bbWidth * 1.666D, this.getZ(), 40, bbWidth * 0.5D,
						bbWidth * 1.666D, bbWidth * 0.5D, 0.2D);
				this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300, 10));
				this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 1));
				SyncBossBarRenderPacket.setBossbarType(this, this.bossInfo, 4);
				entityData.set(DATA_phase, (byte) 2);
				break;
		}
	}

	@Override
	public void setHealth(float health) {
		if (health < this.getHealth() && !this.isProcessingDamage) {
			return;
		}
		this.isProcessingDamage = false;
		super.setHealth(health);
	}

	public GashslitCurrentStates getCrackiness() {
		return GashslitCurrentStates.byFraction(this.getHealth() / this.getMaxHealth());
	}

	public void setSwingType(int input, boolean active) {
		int b0 = this.entityData.get(DATA_swingType);
		if (active) {
			this.entityData.set(DATA_swingType, (byte) (b0 | 1 << input));
		} else {
			this.entityData.set(DATA_swingType, (byte) (b0 & ~(1 << input)));
		}
	}

	public void setSwingType(int val) {
		entityData.set(DATA_swingType, (byte) val);
	}

	public int getSwingType() {
		return entityData.get(DATA_swingType);
	}

	public boolean getSwingType(int p_20292_) {
		return (entityData.get(DATA_swingType) & 1 << p_20292_) != 0;
	}

	@Override
	public boolean isLeftHanded() {
		return false;
	}

	public int actionTicks, actionProgress, prevActionState = 0, animIdx = 0, animSpeed = 1, prevPartialTicks = 0, //
			legAnimTicks, legAnimTicksO, rageTicks = 0, rageTicksO = 0, //
			dashAttackCooltime = 0, rangeAttackCooltime = 0, slowAttackCooltime = 0, batSummonCooltime = 0, blockCooltime = 0, barrageAtCooltime = 0, //
			rangeAttackShootCount = 0, blockDuration = 0, quickAttackCount = 0;
	public float animTicks = 0f, animTicksO = 0f, fadeInTicks = 5f, fadeInTicksO = 5f;
	public ArrayList<FadingOutAnimation> fadingAnims = new ArrayList<FadingOutAnimation>();
	public boolean updatedActionInitially = false, didSwing = false, shouldUpdateAnim = true;
	public Vec3 prevPos = position();
	public Vec3 rightParticlePrev, leftParticlePrev;

	protected float rotlerp(float p_24992_, float p_24993_, float p_24994_) {
		float f = Mth.wrapDegrees(p_24993_ - p_24992_);
		if (f > p_24994_) {
			f = p_24994_;
		}
		if (f < -p_24994_) {
			f = -p_24994_;
		}
		float f1 = p_24992_ + f;
		if (f1 < 0.0F) {
			f1 += 360.0F;
		} else if (f1 > 360.0F) {
			f1 -= 360.0F;
		}
		return f1;
	}

	public void lookAt(Vec3 p_20034_, int limit) {
		Vec3 vec3 = getEyePosition();
		double d0 = p_20034_.x - vec3.x;
		double d1 = p_20034_.y - vec3.y;
		double d2 = p_20034_.z - vec3.z;
		double d3 = Math.sqrt(d0 * d0 + d2 * d2);
		this.setXRot(rotlerp(getXRot(), Mth.wrapDegrees((float) (-(Mth.atan2(d1, d3) * (double) (180F / (float) Math.PI)))), limit));
		this.setYRot(rotlerp(getYRot(), Mth.wrapDegrees((float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F), limit));
		this.setYHeadRot(this.getYRot());
		this.xRotO = this.getXRot();
		this.yRotO = this.getYRot();
		this.yHeadRotO = this.yHeadRot;
		this.yBodyRot = this.yHeadRot;
		this.yBodyRotO = this.yBodyRot;
	}

	public void setAnimSpeed(int val) {
		entityData.set(DATA_animSpeed, (byte) val);
	}

	public int getAnimSpeed() {
		return entityData.get(DATA_animSpeed);
	}

	public int getActionState() {
		return entityData.get(DATA_actionState);
	}

	public void setActionState(int input) {
		entityData.set(DATA_actionState, (byte) input);
        currentAction = GashslitActions.COMMAND_MAP.get(input);
		actionTicks = actionProgress = 0;
		entityData.set(DATA_updateAnim, !entityData.get(DATA_updateAnim));
	}

	public void setInAction(boolean input) {
		entityData.set(DATA_isInAction, input);
	}

	public boolean isInAction() {
		return entityData.get(DATA_isInAction);//entityData.get(DATA_actionState) != 0;
	}

	public boolean isInDeathAction() {
		return animIdx == GashslitAnimationIndex.anims_death;
	}

	public float getAnimTicks(float partialTicks) {
		return Mth.lerp(partialTicks, animTicksO, animTicks);
	}

	public float getLegAnimTicks(float partialTicks) {
		return Mth.lerp(partialTicks, (float) legAnimTicksO, (float) legAnimTicks);
	}

	public float getFadeInTime(float partialTicks) {
		return Math.min(Mth.lerp(partialTicks, fadeInTicksO, fadeInTicks), 5) * 0.2f;
	}

	public int getAnimIndex() {
		return animIdx - 1;
	}

	public List<FadingOutAnimation> getFadingAnims() {
		return fadingAnims;
	}

	public boolean isIn2Phase() {
		return entityData.get(DATA_phase) != 0;
	}

	public boolean isInFinalChance() {
		return entityData.get(DATA_phase) == 2;
	}

	public void onSyncedDataUpdated(EntityDataAccessor<?> p_21104_) {
		super.onSyncedDataUpdated(p_21104_);
		if (level().isClientSide()) {
			if (p_21104_.equals(DATA_updateAnim)) {
				shouldUpdateAnim = true;
			}
			if (p_21104_.equals(DATA_swingType)) {
				rightParticlePrev = getEyePosition();
				leftParticlePrev = getEyePosition();
			}
		}
	}

	public int customDeathTicks = 0;

	@Override
	protected void tickDeath() {
		if (level().isClientSide())
			customDeathTicks++;
		if (!level().isClientSide()) {
			if (customDeathTicks++ > 261)
				discard();
		}
	}

	public boolean isAlliedTo(Entity p_32665_) {
		if (p_32665_ == null) {
			return false;
		} else if (p_32665_ == this) {
			return true;
		} else if (super.isAlliedTo(p_32665_)) {
			return true;
		} else if (p_32665_ instanceof TraceableEntity) {
			Entity owner = ((TraceableEntity) p_32665_).getOwner();
			return owner != null && this.isAlliedTo(owner);
		}
		else if (p_32665_ instanceof LivingEntity && ((LivingEntity) p_32665_).getMobType() == MobType.ILLAGER) {
			return this.getTeam() == null && p_32665_.getTeam() == null;
		}
		else {
			return false;
		}
	}

	@Override
	public MobType getMobType() {
		return MobType.ILLAGER;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return InvasioncoderedsweegyportModSounds.GASH_IDLE.get();
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return InvasioncoderedsweegyportModSounds.GASH_HURT.get();
	}

    protected void playStepSound(BlockPos p_219431_, BlockState p_219432_) {
        this.playSound(InvasioncoderedsweegyportModSounds.GASH_STEP.get(), 10.0F, 1.0F);
    }

	@Override
	public SoundEvent getDeathSound() {
		return InvasioncoderedsweegyportModSounds.GASH_DEATH.get();
	}

	@Override
	public SoundEvent getCelebrateSound() {
		return SoundEvents.EMPTY;
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);
        if (!level().isClientSide()) {
            setActionState(GashslitAnimationIndex.anims_death);
            setInAction(true);
            setAnimSpeed(1);
            setSwingType(0);
            setYRot(yBodyRot);
            setXRot(0);
            setYHeadRot(yBodyRot);
            removeAllEffects();
            SyncBossBarRenderPacket.setBossbarType(this, bossInfo, !isInFinalChance() ? 3 : 5);
            bossInfo.setProgress(0);
        }
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putByte("DataactionState", this.entityData.get(DATA_actionState));
		compound.putByte("DataanimSpeed", this.entityData.get(DATA_animSpeed));
		compound.putBoolean("DataupdateAnim", this.entityData.get(DATA_updateAnim));
		compound.putBoolean("DataisInAction", this.entityData.get(DATA_isInAction));
		compound.putByte("DataswingType", this.entityData.get(DATA_swingType));
		compound.putByte("Dataphase", this.entityData.get(DATA_phase));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("DataactionState")) {
            this.entityData.set(DATA_actionState, compound.getByte("DataactionState"));
            currentAction = GashslitActions.COMMAND_MAP.get(entityData.get(DATA_actionState));
        }
		if (compound.contains("DataanimSpeed"))
			this.entityData.set(DATA_animSpeed, compound.getByte("DataanimSpeed"));
		if (compound.contains("DataupdateAnim"))
			this.entityData.set(DATA_updateAnim, compound.getBoolean("DataupdateAnim"));
		if (compound.contains("DataisInAction"))
			this.entityData.set(DATA_isInAction, compound.getBoolean("DataisInAction"));
		if (compound.contains("DataswingType"))
			this.entityData.set(DATA_swingType, compound.getByte("DataswingType"));
		if (compound.contains("Dataphase"))
			this.entityData.set(DATA_phase, compound.getByte("Dataphase"));
	}

    public EntityModelCache getAnimCache() {
        if (this.animCache == null) {
            this.animCache = new EntityModelCache();
        }
        return this.animCache;
    }

	@Override
	public void baseTick() {
		super.baseTick();
        if(!level().isClientSide()){
            int actionState = getActionState();
            if (!updatedActionInitially) {
                setActionState(actionState);
                updatedActionInitially = true;
            }

            if(isAlive()){
                attackerTracker.tick();
                if (actionState == 0) {

                    if ((tickCount & 7) == 0) {
                        LivingEntity target = getTarget();
                        if (target != null) {
                            GashslitActionSelector.INSTANCE.evaluateAndChoose(this, target, getX(), getY(), getZ());
                        }
                    }
                } else {
                    if(currentAction != null) {
                        currentAction.execute(level(), this, getX(), getY(), getZ());
                    }
                }
            }
        }
		if (level().isClientSide()) {
			handleClientTick();
		}
	}

    private void handleClientTick() {
        if (shouldUpdateAnim) {
            animSpeed = entityData.get(DATA_animSpeed);
            if (animTicks != 0 && prevActionState != 0)
                getFadingAnims().add(new FadingOutAnimation(getFadingAnims(), animTicks, Math.min(animTicks, 5), prevActionState - 1, animSpeed));
            prevActionState = animIdx = entityData.get(DATA_actionState);
            animTicks = animTicksO = fadeInTicks = fadeInTicksO = 0f;
            shouldUpdateAnim = false;
        }

        this.animTicksO = this.animTicks;
        this.animTicks = this.animTicks + this.animSpeed;
        this.fadeInTicksO = this.fadeInTicks;
        this.fadeInTicks = Math.min(this.fadeInTicks + this.animSpeed, 5);

        for (FadingOutAnimation anim : this.getFadingAnims()) {
            anim.tick();
        }

        if (this.isAlive() && this.isIn2Phase()) {
            this.rageTicksO = this.rageTicks;
            this.rageTicks = !this.isInFinalChance() ? Math.min(this.rageTicks + 1, 40) : Math.min(this.rageTicks + 5, 200);
        } else {
            this.rageTicksO = this.rageTicks;
            this.rageTicks = Math.max((int) (this.rageTicks * 0.9) - 1, 0);
        }

        this.legAnimTicksO = this.legAnimTicks;
        if (this.isAggressive()) {
            this.legAnimTicks = Math.min(5, this.legAnimTicks + 1);
        } else {
            this.legAnimTicks = Math.max(0, this.legAnimTicks - 1);
        }

        if (this.rageTicks > 20 && (this.tickCount & 7) == 0) {
            double width = this.getBbWidth();
            level().addParticle(InvasioncoderedsweegyportModParticleTypes.GASHSLIT_POPFLAME_PARTICLE.get(),
                    getX() + width * (Math.random() - 0.5) * 3, getY() + width * Math.random() * 6, getZ() + width * (Math.random() - 0.5) * 3, 0, 0, 0);

            if (this.getHealth() / this.getMaxHealth() < 0.02) {
                for (int i = 0; i < 2; i++) {
                    level().addParticle(InvasioncoderedsweegyportModParticleTypes.GASHSLIT_POPFLAME_PARTICLE.get(),
                            getX() + width * (Math.random() - 0.5) * 3, getY() + width * Math.random() * 6, getZ() + width * (Math.random() - 0.5) * 3, 0, 0, 0);
                }
            }
        }
    }

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);
		this.bossInfo.addPlayer(player);
		int currentRenderType = !isIn2Phase() ? 1 : !isInFinalChance() ? 2 : 4;
		if (!isAlive())
			currentRenderType = 3;
		SyncBossBarRenderPacket.setBossbarType(player, bossInfo, currentRenderType);
		if (!isAlive()) {
			this.bossInfo.setProgress(0);
		}
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);
		this.bossInfo.removePlayer(player);
		SyncBossBarRenderPacket.removeBossbarType(player, bossInfo);
	}

	@Override
	public void customServerAiStep() {
		super.customServerAiStep();
		this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
	}

	public static void init() {
		//DungeonHooks.addDungeonMob(InvasioncoderedsweegyportModEntities.GASHSLIT.get(), 180);
        Raid.RaiderType.create("gashslit", InvasioncoderedsweegyportModEntities.GASHSLIT.get(), new int[]{0, 0, 0, 0, 0, 0, 0, 1});
    }

	@Override
	public void applyRaidBuffs(int num, boolean logic) {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.24);
		builder = builder.add(Attributes.MAX_HEALTH, 680);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 4);
		builder = builder.add(Attributes.FOLLOW_RANGE, 64);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
		return builder;
	}

    public static float getAttackDamageScale(Mob mob) {
        return 2.41f - Math.max(mob.getHealth() / mob.getMaxHealth(), 0.4f) * 1.41f;
    }
}