package com.sweegy.invasioncodered.entity.stonecutterman;

import com.sweegy.invasioncodered.client.model.animations.indices.GashslitAnimationIndex;
import com.sweegy.invasioncodered.client.model.animations.indices.StoneCutterManAnimationIndex;
import com.sweegy.invasioncodered.config.InvasionCodeRedConfig;
import com.sweegy.invasioncodered.entity.ai.DoNothingGoal;
import com.sweegy.invasioncodered.entity.ai.RecentAttackerTracker;
import com.sweegy.invasioncodered.entity.ai.actions.ActionCommand;
import com.sweegy.invasioncodered.entity.stonecutterman.actions.StoneCutterManActionSelector;
import com.sweegy.invasioncodered.entity.stonecutterman.actions.StoneCutterManActions;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModEntities;
import com.sweegy.invasioncodered.interfaces.FadingOutAnimation;
import com.sweegy.invasioncodered.interfaces.IActionStateMob;
import com.sweegy.invasioncodered.interfaces.IUsingFadingAnims;
import com.sweegy.invasioncodered.network.SyncBossBarRenderPacket;
import com.sweegy.invasioncodered.util.EntityUtil;
import com.sweegy.invasioncodered.util.animation.EntityModelCache;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.network.PlayMessages;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class StoneCutterManEntity extends Raider implements IActionStateMob, IUsingFadingAnims {
    public static final EntityDataAccessor<Byte> DATA_actionState = SynchedEntityData.defineId(StoneCutterManEntity.class, EntityDataSerializers.BYTE);
    public static final EntityDataAccessor<Boolean> DATA_updateAnim = SynchedEntityData.defineId(StoneCutterManEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> DATA_isInAction = SynchedEntityData.defineId(StoneCutterManEntity.class, EntityDataSerializers.BOOLEAN);
    public static double MELEE_COMBO_CHANCE, KNOCK_CHANCE, KNOCK_DOWN_CHANCE;
    public static boolean MELEE_COMBO_CHANCE_SET = false, KNOCK_CHANCE_SET = false;
    public final RecentAttackerTracker attackerTracker = new RecentAttackerTracker(this, 8);
    public final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.WHITE, ServerBossEvent.BossBarOverlay.PROGRESS);

    public int actionTicks, actionProgress, prevActionState = 0, animIdx = 0, animSpeed = 1, sprintTickO, sprintTick;
    public float animTicks = 0f, animTicksO = 0f, fadeInTicks = 5f, fadeInTicksO = 5f;
    public boolean updatedActionInitially = false, shouldUpdateAnim = true, isProcessingDamage = true;
    private EntityModelCache animCache;
    private ActionCommand<StoneCutterManEntity> currentAction;
    public ArrayList<FadingOutAnimation> fadingAnims = new ArrayList<FadingOutAnimation>();

    public StoneCutterManEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(InvasioncoderedsweegyportModEntities.STONE_CUTTER_MAN.get(), world);
    }

    public StoneCutterManEntity(EntityType<StoneCutterManEntity> type, Level world) {
        super(type, world);
        setMaxUpStep(1.6f);
        xpReward = 450;
        noCulling = true;
        setNoAi(false);
        setPersistenceRequired();
     }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34297_, DifficultyInstance p_34298_, MobSpawnType p_34299_, @Nullable SpawnGroupData p_34300_, @Nullable CompoundTag p_34301_) {
        EntityUtil.setConfigattribute(this, InvasionCodeRedConfig.GASHSLIT_HEALTH_MULTIPLIER.get());
        return super.finalizeSpawn(p_34297_, p_34298_, p_34299_, p_34300_, p_34301_);
    }

    public List<FadingOutAnimation> getFadingAnims(){ return fadingAnims; }

    public float getFadeInTime(float partialTicks) {
        return Math.min(Mth.lerp(partialTicks, fadeInTicksO, fadeInTicks), 5) * 0.2f;
    }

    public int getAnimIndex() {
        return animIdx - 1;
    }

    public float getAnimTicks(float partialTicks) {
        return Mth.lerp(partialTicks, animTicksO, animTicks);
    }

    public EntityModelCache getAnimCache() {
        if (this.animCache == null) {
            this.animCache = new EntityModelCache();
        }
        return this.animCache;
    }

    public int getActionState() {
        return entityData.get(DATA_actionState);
    }

    public void setActionState(int input) {
        entityData.set(DATA_actionState, (byte) input);
        currentAction = StoneCutterManActions.COMMAND_MAP.get(input);
        actionTicks = actionProgress = 0;
        entityData.set(DATA_updateAnim, !entityData.get(DATA_updateAnim));
    }

    public boolean isInDeathAction(){ return entityData.get(DATA_actionState) == StoneCutterManAnimationIndex.anims_dead; }

    public void setInAction(boolean input) {
        entityData.set(DATA_isInAction, input);
    }

    public boolean isInAction() { return entityData.get(DATA_isInAction); }

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_21104_) {
        super.onSyncedDataUpdated(p_21104_);
        if (level().isClientSide()) {
            if (p_21104_.equals(DATA_updateAnim)) {
                shouldUpdateAnim = true;
            }
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        lookControl = new LookControl(this) {
            protected boolean resetXRotOnTick() {
                return StoneCutterManEntity.this.getActionState() == 0;
            }
        };
        goalSelector.addGoal(0, new DoNothingGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return -1;
            }

            @Override
            public void start(){
                super.start();
                mob.setSprinting(true);
            }

            @Override
            public void stop(){
                super.stop();
                mob.setSprinting(false);
            }
        });
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, LivingEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new FloatGoal(this));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.IN_FIRE))
            return false;
        if (this.getActionState() == GashslitAnimationIndex.anims_block) {
            amount = 0.0F;
        }
        this.isProcessingDamage = true;
        boolean tookDamage = super.hurt(source, amount);
        this.isProcessingDamage = false;
        if (tookDamage && this.isAlive() && !this.level().isClientSide()) {

            if (source.getEntity() instanceof LivingEntity attacker) {
                this.attackerTracker.onAttackedBy(attacker);
            }
        }
        return tookDamage;
    }

    @Override
    public void setHealth(float health) {
        if (health < this.getHealth() && !this.isProcessingDamage) {
            return;
        }
        this.isProcessingDamage = false;
        super.setHealth(health);
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (!level().isClientSide()) {
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
                            StoneCutterManActionSelector.INSTANCE.evaluateAndChoose(this, target, getX(), getY(), getZ());
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
            animSpeed = 1;//entityData.get(DATA_animSpeed);
            if (animTicks != 0 && prevActionState != 0)
                getFadingAnims().add(new FadingOutAnimation(getFadingAnims(), animTicks, Math.min(animTicks, 5), prevActionState - 1, animSpeed));
            prevActionState = animIdx = entityData.get(DATA_actionState);
            animTicks = animTicksO = fadeInTicks = fadeInTicksO = 0f;
            shouldUpdateAnim = false;
        }

        this.animTicksO = this.animTicks;
        this.animTicks = this.animTicks + this.animSpeed;
        this.fadeInTicksO = this.fadeInTicks;
        this.sprintTickO = this.sprintTick;
        this.fadeInTicks = Math.min(this.fadeInTicks + this.animSpeed, 5);
        this.sprintTick = isSprinting() ? Math.min(this.sprintTick + 1, 5) : Math.max(this.sprintTick - 1, 0);

        for (FadingOutAnimation anim : this.getFadingAnims()) {
            anim.tick();
        }
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
        SyncBossBarRenderPacket.setBossbarType(player, bossInfo, 6);
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

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_actionState, (byte) 0);
        this.entityData.define(DATA_updateAnim, false);
        this.entityData.define(DATA_isInAction, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putByte("DataactionState", this.entityData.get(DATA_actionState));
        compound.putBoolean("DataupdateAnim", this.entityData.get(DATA_updateAnim));
        compound.putBoolean("DataisInAction", this.entityData.get(DATA_isInAction));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("DataactionState")) {
            this.entityData.set(DATA_actionState, compound.getByte("DataactionState"));
            currentAction = StoneCutterManActions.COMMAND_MAP.get(entityData.get(DATA_actionState));
        }
        if (compound.contains("DataupdateAnim"))
            this.entityData.set(DATA_updateAnim, compound.getBoolean("DataupdateAnim"));
        if (compound.contains("DataisInAction"))
            this.entityData.set(DATA_isInAction, compound.getBoolean("DataisInAction"));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.24);
        builder = builder.add(Attributes.MAX_HEALTH, 490);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 19);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
        return builder;
    }

    public float getDamageMultiplier(){
        return 2f - getHealth()/getMaxHealth();
    }


    @Override
    public void die(DamageSource source) {
        super.die(source);
        if (!level().isClientSide()) {
            setActionState(StoneCutterManAnimationIndex.anims_dead);
            setYRot(yBodyRot);
            setXRot(0);
            setYHeadRot(yBodyRot);
            removeAllEffects();
        }
    }

    //Config value getter
    public static double getMeleeComboChance(){
        if(!MELEE_COMBO_CHANCE_SET) {
            MELEE_COMBO_CHANCE_SET = true;
            MELEE_COMBO_CHANCE = InvasionCodeRedConfig.STONE_CUTTER_MAN_MELEE_COMBO_CHANCE.get();
        }
        return MELEE_COMBO_CHANCE;
    }

    public static double getKnockChance(){
        if(!KNOCK_CHANCE_SET) {
            KNOCK_CHANCE_SET = true;
            KNOCK_CHANCE = InvasionCodeRedConfig.STONE_CUTTER_MAN_KNOCK_CHANCE.get();
        }
        return MELEE_COMBO_CHANCE;
    }

    //dummy codes for inheritance
    @Override
    public void applyRaidBuffs(int num, boolean logic) {
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.EMPTY;
    }
}
