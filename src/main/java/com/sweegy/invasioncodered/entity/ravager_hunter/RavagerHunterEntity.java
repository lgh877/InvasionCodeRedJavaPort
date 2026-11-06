package com.sweegy.invasioncodered.entity.ravager_hunter;

import com.sweegy.invasioncodered.client.model.animations.indices.RavagerHunterAnimationIndex;
import com.sweegy.invasioncodered.config.InvasionCodeRedConfig;
import com.sweegy.invasioncodered.entity.ai.DoNothingGoal;
import com.sweegy.invasioncodered.entity.ai.actions.ActionCommand;
import com.sweegy.invasioncodered.entity.cuccita.CuccitaEntity;
import com.sweegy.invasioncodered.entity.ravager_hunter.actions.RavagerHunterActions;
import com.sweegy.invasioncodered.entity.ravager_hunter.actions.RavagerHunterPursuitGoal;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import com.sweegy.invasioncodered.interfaces.SimpleFadingOutAnimation;
import com.sweegy.invasioncodered.interfaces.IActionStateMob;
import com.sweegy.invasioncodered.interfaces.IUsingSimpleFadingAnims;
import com.sweegy.invasioncodered.util.EntityUtil;
import com.sweegy.invasioncodered.util.animation.EntityModelCache;
import com.sweegy.invasioncodered.util.animation.SimpleFadingAnimationController;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.server.level.ServerLevel;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModEntities;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.network.PlayMessages;

import javax.annotation.Nullable;

public class RavagerHunterEntity extends Raider implements IActionStateMob, IUsingSimpleFadingAnims {
    private static final EntityDataAccessor<Integer> DATA_ACTION_STATE = SynchedEntityData.defineId(RavagerHunterEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_IN_ACTION = SynchedEntityData.defineId(RavagerHunterEntity.class, EntityDataSerializers.BOOLEAN);

    public int actionTicks, actionProgress;
    public int rangedCooldown;

    public int armAnimAmpO = 0, armAnimAmp = 0, legAnimAmpO = 0, legAnimAmp = 0, nextBlockDuration;

    public boolean blockAfterMelee;
    private boolean cuccitaSpawned;
    private ActionCommand<RavagerHunterEntity> currentAction;

    // Client-side AnimationDefinition timer and exactly ONE outgoing animation.
    private final SimpleFadingAnimationController simpleFade =
            new SimpleFadingAnimationController(5.0F, 5.0F);
    private final EntityModelCache animCache = new EntityModelCache();

    public RavagerHunterEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(InvasioncoderedsweegyportModEntities.RAVAGER_HUNTER.get(), world);
    }

    public RavagerHunterEntity(EntityType<RavagerHunterEntity> type, Level level) {
        super(type, level);
        this.xpReward = 10;
        setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 230.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 7.0D)
                .add(Attributes.FOLLOW_RANGE, 100.0D)
                .add(Attributes.ARMOR, 0.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.0D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_ACTION_STATE, 0);
        entityData.define(DATA_IN_ACTION, false);
    }

    @Override
    protected void registerGoals() {
        lookControl = new LookControl(this) {
            protected boolean resetXRotOnTick() {
                return !RavagerHunterEntity.this.isInAction();
            }
        };
        goalSelector.addGoal(0, new DoNothingGoal<>(this));
        goalSelector.addGoal(1, new RavagerHunterPursuitGoal(this));
        goalSelector.addGoal(2, new FloatGoal(this));
        goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    @Override
    public boolean isLeftHanded(){
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide()) {
            handleClientTick();
            return;
        }
        if (!isAlive()) return;

        if (!cuccitaSpawned && (tickCount == 1 || (tickCount & 15) == 0)) {
            spawnLeashedCuccita();
        }

        if (rangedCooldown > 0) rangedCooldown--;

        if (getActionState() != 0) {
            if(currentAction != null) {
                currentAction.execute(level(), this, getX(), getY(), getZ());
            }
        } else if ((tickCount & 31) == 0) {
            LivingEntity target = getTarget();
            if (target != null && target.isAlive()) {
                double flatDist = new Vec2((float) (target.getX() - getX()), (float) (target.getZ() - getZ())).length();
                double yDiff = target.getY() - getY();
                double minDist = (getBbWidth() + target.getBbWidth())/2;
                boolean isInMeleeRadius =
                        yDiff < getBbHeight() && yDiff > -target.getBbHeight() &&
                                flatDist < minDist + getBbWidth() * 10;
                if(isInMeleeRadius){
                    setActionState(RavagerHunterAnimationIndex.anims_aggressive);
                    equipAxe();
                }else{
                    setActionState(RavagerHunterAnimationIndex.anims_bowwalk);
                    equipBow();
                }
            }
        }
    }

    private void spawnLeashedCuccita() {
        if (!(level() instanceof ServerLevel server)) return;

        CuccitaEntity cuccita = InvasioncoderedsweegyportModEntities.CUCCITA.get().create(server);
        if (cuccita == null) return;

        cuccita.setOwner(this);
        cuccita.moveTo(getX() + 1.0D, getY(), getZ() + 0.5D, getYRot(), 0.0F);
        if (server.addFreshEntity(cuccita)) {
            cuccita.setLeashedTo(this, true);
            cuccitaSpawned = true;
        }
    }

    private void handleClientTick() {
        // Animation IDs in BrawlerAnimationIndex start at 1; the array is zero-based.
        // State 0 (idle) maps to -1, allowing the previous action to fade out.
        // transitionTo() ignores repeated IDs, so looping poses do NOT restart each tick.
//        simpleFade.transitionTo(getActionState() - 1);
        simpleFade.tick();

        this.armAnimAmpO = this.armAnimAmp;
        this.legAnimAmpO = this.legAnimAmp;

        switch(getActionState()){
            case RavagerHunterAnimationIndex.anims_block:
            case RavagerHunterAnimationIndex.anims_bowstay:
                armAnimAmp = Math.max(this.armAnimAmp - 1, 0);
                legAnimAmp = Math.max(this.legAnimAmp - 1, 0);
                break;
            case RavagerHunterAnimationIndex.anims_bowwalk:
                armAnimAmp = Math.max(this.armAnimAmp - 1, 0);
                legAnimAmp = Math.min(this.legAnimAmp + 1, 5);
                break;
            case RavagerHunterAnimationIndex.anims_aggressive :
                armAnimAmp = armAnimAmp > 2
                        ? Math.max(this.armAnimAmp - 1, 2)
                        : Math.min(this.armAnimAmp + 1, 2);
                legAnimAmp = Math.min(this.legAnimAmp + 1, 5);
                break;
            case 0:
                armAnimAmp = Math.min(this.armAnimAmp + 1, 5);
                legAnimAmp = Math.min(this.legAnimAmp + 1, 5);
                break;
        }

    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_21104_) {
        super.onSyncedDataUpdated(p_21104_);
        if (level().isClientSide()) {
            if (p_21104_.equals(DATA_ACTION_STATE)) simpleFade.transitionTo(getActionState() - 1);
        }
    }

    public float getArmAnimAmp(float partialTicks){
        return Mth.lerp(partialTicks, armAnimAmpO, armAnimAmp) * 0.2f;
    }
    public float getLegAnimAmp(float partialTicks){
        return Mth.lerp(partialTicks, legAnimAmpO, legAnimAmp) * 0.2f;
    }

    public int getActionState() {
        return entityData.get(DATA_ACTION_STATE);
    }

    public void setActionState(int state) {
        entityData.set(DATA_ACTION_STATE, state);
        currentAction = RavagerHunterActions.COMMAND_MAP.get(state);
        actionTicks = 0;
        actionProgress = 0;
    }

    @Override
    public boolean isInDeathAction() {
        return false;
    }

    public boolean isInAction() {
        return entityData.get(DATA_IN_ACTION);
    }

    public void setInAction(boolean value) {
        entityData.set(DATA_IN_ACTION, value);
    }

    public void equipAxe(){
        setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
    }

    public void equipBow(){
        ItemStack bow = new ItemStack(Items.BOW);
        bow.enchant(Enchantments.POWER_ARROWS, 3);
        bow.enchant(Enchantments.PUNCH_ARROWS, 1);
        setItemSlot(EquipmentSlot.MAINHAND, bow);
    }

    public EntityModelCache getAnimCache() {
        return animCache;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("DataactionState", this.entityData.get(DATA_ACTION_STATE));
        tag.putBoolean("BrawlerCuccitaSpawned", cuccitaSpawned);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("DataactionState")) {
            this.entityData.set(DATA_ACTION_STATE, tag.getInt("DataactionState"));
            currentAction = RavagerHunterActions.COMMAND_MAP.get(entityData.get(DATA_ACTION_STATE));
        }
        cuccitaSpawned = tag.getBoolean("BrawlerCuccitaSpawned");
    }

    private boolean transformedOnDeath;

    private boolean isProcessingDamage = true;

    @Override
    public void setHealth(float health) {
        if (health < getHealth() && !isProcessingDamage) return;
        isProcessingDamage = false;
        super.setHealth(health);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        isProcessingDamage = true;
        try {
            return super.hurt(source, amount);
        } finally {
            isProcessingDamage = false;
        }
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        if (!level().isClientSide() && isDeadOrDying() && !transformedOnDeath) {
            LivingEntity target = getTarget();
            if (target != null && target.isAlive() && target.level() == level() && level() instanceof ServerLevel server) {
                transformedOnDeath = true;
                StoneCutterManEntity successor = InvasioncoderedsweegyportModEntities.STONE_CUTTER_MAN.get().create(server);
                if (successor != null) {
                    successor.moveTo(getX(), getY(), getZ(), getYRot(), getXRot());
                    successor.setYBodyRot(yBodyRot);
                    successor.setYHeadRot(yHeadRot);
                    successor.finalizeSpawn((ServerLevel)this.level(), this.level().getCurrentDifficultyAt(blockPosition()), MobSpawnType.CONVERSION, (SpawnGroupData)null, (CompoundTag)null);
                    if (server.addFreshEntity(successor)) successor.setTarget(target);
                }
                discard();
            }
        }
    }

//    @Override
//    public void onAddedToWorld() {
//        super.onAddedToWorld();
//        if (!level().isClientSide() && getMainHandItem().isEmpty()) equipForMode();
//    }

    public boolean isAlliedTo(Entity p_32665_) {
        if (p_32665_ == null) {
            return false;
        } else if (p_32665_ == this) {
            return true;
        } else if (super.isAlliedTo(p_32665_)) {
            return true;
        }
//		else if (p_32665_ instanceof LivingEntity && ((LivingEntity) p_32665_).getMobType() == MobType.ILLAGER) {
//			return this.getTeam() == null && p_32665_.getTeam() == null;
//		}
        else {
            return p_32665_ instanceof CuccitaEntity;
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
    @Nullable
    public SimpleFadingOutAnimation getFadingOutAnimation() {
        return simpleFade.getFadingOutAnimation();
    }

    @Override
    public void setFadingOutAnimation(@Nullable SimpleFadingOutAnimation animation) {
        simpleFade.setFadingOutAnimation(animation);
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34297_, DifficultyInstance p_34298_, MobSpawnType p_34299_, @Nullable SpawnGroupData p_34300_, @Nullable CompoundTag p_34301_) {
        EntityUtil.setConfigattribute(this, InvasionCodeRedConfig.RAVAGER_HUNTER_HEALTH_MULTIPLIER.get());
        return super.finalizeSpawn(p_34297_, p_34298_, p_34299_, p_34300_, p_34301_);
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return InvasioncoderedsweegyportModSounds.RAVAGER_HUNTER_HURT.get();
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.EMPTY;
    }

    @Override
    public void applyRaidBuffs(int num, boolean logic) {
    }
}
