package com.sweegy.invasioncodered.entity.brawler;

import com.sweegy.invasioncodered.client.model.animations.indices.BrawlerAnimationIndex;
import com.sweegy.invasioncodered.config.InvasionCodeRedConfig;
import com.sweegy.invasioncodered.entity.ai.DoNothingGoal;
import com.sweegy.invasioncodered.entity.ai.actions.ActionCommand;
import com.sweegy.invasioncodered.entity.brawler.actions.BrawlerActions;
import com.sweegy.invasioncodered.entity.brawler.actions.BrawlerPursuitGoal;
import com.sweegy.invasioncodered.interfaces.FadingOutAnimation;
import com.sweegy.invasioncodered.interfaces.IActionStateMob;
import com.sweegy.invasioncodered.interfaces.IUsingFadingAnims;
import com.sweegy.invasioncodered.util.EntityUtil;
import com.sweegy.invasioncodered.util.animation.EntityModelCache;
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
import java.util.ArrayList;
import java.util.List;

public class BrawlerEntity extends Raider implements IActionStateMob, IUsingFadingAnims {
    private static final EntityDataAccessor<Integer> DATA_ACTION_STATE = SynchedEntityData.defineId(BrawlerEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_IN_ACTION = SynchedEntityData.defineId(BrawlerEntity.class, EntityDataSerializers.BOOLEAN);

    public int actionTicks, actionProgress, prevActionState;
    public int rangedCooldown;
    public int animIdx;

    public int armAnimAmpO = 0, armAnimAmp = 0, legAnimAmpO = 0, legAnimAmp = 0,
            animTicks = 0, animTicksO = 0, fadeInTicks = 5, fadeInTicksO = 5, nextBlockDuration;

    public boolean blockAfterMelee;
    private boolean cuccitaSpawned;
    private boolean shouldUpdateAnim;
    private ActionCommand<BrawlerEntity> currentAction;

    private final List<FadingOutAnimation> fadingAnims = new ArrayList<>();
    private final EntityModelCache animCache = new EntityModelCache();

    public BrawlerEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(InvasioncoderedsweegyportModEntities.BRAWLER.get(), world);
    }

    public BrawlerEntity(EntityType<BrawlerEntity> type, Level level) {
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
                return !BrawlerEntity.this.isInAction();
            }
        };
        goalSelector.addGoal(0, new DoNothingGoal<>(this));
        goalSelector.addGoal(1, new BrawlerPursuitGoal(this));
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

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_21104_) {
        super.onSyncedDataUpdated(p_21104_);
        if (level().isClientSide()) {
            if (p_21104_.equals(DATA_ACTION_STATE)) {
                shouldUpdateAnim = true;
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide()) {
            handleClientTick();
            return;
        }
        if (!isAlive()) return;

//        if (!cuccitaSpawned && (tickCount == 1 || (tickCount & 15) == 0)) {
//            spawnLeashedCuccita();
//        }

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
                    setActionState(BrawlerAnimationIndex.anims_aggressive);
                    equipAxe();
                }else{
                    setActionState(BrawlerAnimationIndex.anims_bowwalk);
                    equipBow();
                }
                //BrawlerActionSelector.INSTANCE.evaluateAndChoose(this, target, getX(), getY(), getZ());
            }
        }
    }

//    private void spawnLeashedCuccita() {
//        if (!(level() instanceof ServerLevel server)) return;
//
//        CuccitaEntity cuccita = InvasioncoderedsweegyportModEntities.CUCCITA.get().create(server);
//        if (cuccita == null) return;
//
//        cuccita.moveTo(getX() + 1.0D, getY(), getZ() + 0.5D, getYRot(), 0.0F);
//        if (server.addFreshEntity(cuccita)) {
//            cuccita.setLeashedTo(this, true);
//            cuccitaSpawned = true;
//        }
//    }

    private void handleClientTick() {
        if (shouldUpdateAnim) {
            if (animTicks != 0 && prevActionState != 0)
                getFadingAnims().add(new FadingOutAnimation(getFadingAnims(), animTicks, Math.min(animTicks, 5), prevActionState - 1));
            prevActionState = animIdx = entityData.get(DATA_ACTION_STATE);
            animTicks = animTicksO = fadeInTicks = fadeInTicksO = 0;
            shouldUpdateAnim = false;
        }

        this.animTicksO = this.animTicks;
        this.animTicks = this.animTicks + 1;
        this.fadeInTicksO = this.fadeInTicks;
        this.fadeInTicks = Math.min(this.fadeInTicks + 1, 5);

        this.armAnimAmpO = this.armAnimAmp;
        this.legAnimAmpO = this.legAnimAmp;

        switch(animIdx){
            case BrawlerAnimationIndex.anims_block:
            case BrawlerAnimationIndex.anims_bowstay:
                armAnimAmp = Math.max(this.armAnimAmp - 1, 0);
                legAnimAmp = Math.max(this.legAnimAmp - 1, 0);
                break;
            case BrawlerAnimationIndex.anims_bowwalk:
                armAnimAmp = Math.max(this.armAnimAmp - 1, 0);
                legAnimAmp = Math.min(this.legAnimAmp + 1, 5);
                break;
            case BrawlerAnimationIndex.anims_aggressive :
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

        for (FadingOutAnimation anim : this.getFadingAnims()) {
            anim.tick();
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
        currentAction = BrawlerActions.COMMAND_MAP.get(state);
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
            currentAction = BrawlerActions.COMMAND_MAP.get(entityData.get(DATA_ACTION_STATE));
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
    public float getFadeInTime(float partialTicks) {
        return Math.min(Mth.lerp(partialTicks, fadeInTicksO, fadeInTicks), 5) * 0.2f;
    }

    public float getAnimTicks(float partialTicks) {
        return Mth.lerp(partialTicks, animTicksO, animTicks);
    }

    public int getAnimIndex() {
        return animIdx - 1;
    }

    public List<FadingOutAnimation> getFadingAnims() {
        return fadingAnims;
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34297_, DifficultyInstance p_34298_, MobSpawnType p_34299_, @Nullable SpawnGroupData p_34300_, @Nullable CompoundTag p_34301_) {
        EntityUtil.setConfigattribute(this, InvasionCodeRedConfig.BRAWLER_HEALTH_MULTIPLIER.get());
        return super.finalizeSpawn(p_34297_, p_34298_, p_34299_, p_34300_, p_34301_);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VINDICATOR_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.VINDICATOR_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VINDICATOR_DEATH;
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.VINDICATOR_CELEBRATE;
    }

    @Override
    public void applyRaidBuffs(int num, boolean logic) {
    }
}
