//package com.sweegy.invasioncodered.entity.cuccita;
//
//import com.sweegy.invasioncodered.entity.ravager_hunter.RavagerHunterEntity;
//import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
//import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModEntities;
//import com.sweegy.invasioncodered.interfaces.IUsingSimpleFadingAnims;
//import com.sweegy.invasioncodered.interfaces.SimpleFadingOutAnimation;
//import com.sweegy.invasioncodered.util.animation.EntityModelCache;
//import com.sweegy.invasioncodered.util.animation.SimpleFadingAnimationController;
//import net.minecraft.network.syncher.EntityDataAccessor;
//import net.minecraft.network.syncher.EntityDataSerializers;
//import net.minecraft.network.syncher.SynchedEntityData;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.sounds.SoundEvent;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.damagesource.DamageSource;
//import net.minecraft.world.effect.MobEffectInstance;
//import net.minecraft.world.effect.MobEffects;
//import net.minecraft.world.entity.*;
//import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
//import net.minecraft.world.entity.ai.attributes.Attributes;
//import net.minecraft.world.entity.ai.goal.*;
//import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
//import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
//import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
//import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
//import net.minecraft.world.entity.animal.Animal;
//import net.minecraft.world.entity.animal.IronGolem;
//import net.minecraft.world.entity.animal.Turtle;
//import net.minecraft.world.entity.monster.Monster;
//import net.minecraft.world.entity.npc.AbstractVillager;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.level.Level;
//import net.minecraftforge.event.ForgeEventFactory;
//import net.minecraftforge.network.PlayMessages;
//import org.jetbrains.annotations.Nullable;
//
//public class CuccitaTamedEntity extends TamableAnimal implements IUsingSimpleFadingAnims {
//    private static final double WILD_MAX_HEALTH = 100.0D;
//    private static final double TAMED_MAX_HEALTH = 230.0D;
//    public static final EntityDataAccessor<Byte> DATA_actionState = SynchedEntityData.defineId(CuccitaTamedEntity.class, EntityDataSerializers.BYTE);
//
//    private final SimpleFadingAnimationController simpleFade =
//            new SimpleFadingAnimationController(3.0F, 3.0F);
//    private final EntityModelCache animCache = new EntityModelCache();
//
//    private int animIdx = 0;
//
//    public CuccitaTamedEntity(PlayMessages.SpawnEntity packet, Level world) {
//        this(InvasioncoderedsweegyportModEntities.CUCCITA.get(), world);
//    }
//
//    public CuccitaTamedEntity(EntityType<? extends CuccitaTamedEntity> type, Level level) {
//        super(type, level);
//        this.xpReward = 10;
//    }
//
//    public EntityModelCache getAnimCache() {
//        return animCache;
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(DATA_actionState, (byte) 0);
//    }
//
//    public static AttributeSupplier.Builder createAttributes() {
//        return Mob.createMobAttributes()
//                .add(Attributes.MAX_HEALTH, WILD_MAX_HEALTH)
//                .add(Attributes.ATTACK_DAMAGE, 8.0D)
//                .add(Attributes.MOVEMENT_SPEED, 0.32D)
//                .add(Attributes.FOLLOW_RANGE, 23.0D)
//                .add(Attributes.ARMOR, 0.0D);
//    }
//
//    @Override
//    protected void registerGoals() {
//        goalSelector.addGoal(0, new FloatGoal(this));
//        goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
//        goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 9.0F, 4.0F, false));
//        goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.24D, true));
//        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.3D));
//        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
//        goalSelector.addGoal(7, new RandomLookAroundGoal(this));
//
//        targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
//        targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
//        targetSelector.addGoal(3, new HurtByTargetGoal(this));
//        targetSelector.addGoal(4, wildTarget(Player.class));
//        targetSelector.addGoal(5, wildTarget(IronGolem.class));
//        targetSelector.addGoal(7, wildTarget(AbstractVillager.class));
//        targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(
//                this, Turtle.class, 10, true, false,
//                enemy -> !isTame() && enemy instanceof Turtle turtle && turtle.isBaby() && !turtle.isInWater()) {
//            @Override
//            public boolean canUse() {
//                return !CuccitaTamedEntity.this.isTame() && super.canUse();
//            }
//        });
//        targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(
//                this, Monster.class, 10, true, false,
//                enemy -> isTame()
//                        && !(enemy instanceof RavagerHunterEntity)
//                        && !(enemy instanceof StoneCutterManEntity)
//                        && !isAlliedTo(enemy)) {
//            @Override
//            public boolean canUse() {
//                return CuccitaTamedEntity.this.isTame() && super.canUse();
//            }
//        });
//    }
//
//    private <T extends LivingEntity> NearestAttackableTargetGoal<T> wildTarget(Class<T> targetClass) {
//        return new NearestAttackableTargetGoal<>(this, targetClass, true) {
//            @Override
//            public boolean canUse() {
//                return !CuccitaTamedEntity.this.isTame() && super.canUse();
//            }
//        };
//    }
//
//    @Override
//    public void setTame(boolean tamed) {
//        super.setTame(tamed);
//        var healthAttribute = getAttribute(Attributes.MAX_HEALTH);
//        if (healthAttribute != null) {
//            healthAttribute.setBaseValue(tamed ? TAMED_MAX_HEALTH : WILD_MAX_HEALTH);
//        }
//    }
//
//    @Override
//    public InteractionResult mobInteract(Player player, InteractionHand hand) {
//        ItemStack stack = player.getItemInHand(hand);
//
//        if (!isTame() && stack.is(Items.IRON_INGOT)) {
//            if (!level().isClientSide()) {
//                if (!player.getAbilities().instabuild) stack.shrink(1);
//                if (getRandom().nextFloat() < 0.2F && !ForgeEventFactory.onAnimalTame(this, player)) {
//                    tame(player);
//                    setHealth(getMaxHealth());
//                    addEffect(new MobEffectInstance(MobEffects.REGENERATION, 999999, 2, false, false));
//                    getNavigation().stop();
//                    setTarget(null);
//                    level().broadcastEntityEvent(this, (byte) 7);
//                } else {
//                    level().broadcastEntityEvent(this, (byte) 6);
//                }
//            }
//            return InteractionResult.sidedSuccess(level().isClientSide());
//        }
//
//        if (isTame() && isOwnedBy(player) && stack.isEmpty()) {
//            if (!level().isClientSide()) {
//                setOrderedToSit(!isOrderedToSit());
//                setTarget(null);
//                getNavigation().stop();
//            }
//            return InteractionResult.sidedSuccess(level().isClientSide());
//        }
//
//        return super.mobInteract(player, hand);
//    }
//
//    // Iron ingots are for taming, not breeding.
//    @Override
//    public boolean isFood(ItemStack stack) {
//        return false;
//    }
//
//    @Override
//    public boolean canMate(Animal other) {
//        return false;
//    }
//
//    @Nullable
//    @Override
//    public CuccitaTamedEntity getBreedOffspring(ServerLevel level, AgeableMob partner) {
//        return null;
//    }
//
//    @Override
//    public boolean canBeLeashed(Player player) {
//        return true;
//    }
//
//    @Override
//    public boolean canAttack(LivingEntity target) {
//        return !(target instanceof RavagerHunterEntity)
//                && !(target instanceof StoneCutterManEntity)
//                && super.canAttack(target);
//    }
//
//    @Override
//    public boolean hurt(DamageSource source, float amount) {
//        if (isTame() && source.getEntity() instanceof Player) return false;
//        if (source.getEntity() instanceof RavagerHunterEntity
//                || source.getEntity() instanceof StoneCutterManEntity) {
//            return false;
//        }
//        return super.hurt(source, amount);
//    }
//
//    @Override
//    public void tick() {
//        super.tick();
//        updateSwingTime();
//        if(level().isClientSide()){
//            handleClientAnimation();
//            return;
//        }
//        if (!level().isClientSide() && isOrderedToSit() && getTarget() != null) {
//            setTarget(null);
//        }
//    }
//
//    private void handleClientAnimation(){
//        simpleFade.transitionTo(animIdx);
//        simpleFade.tick();
//        if(swinging){
//            animIdx = 2;
//        } else if (walkAnimation.isMoving()) {
//            animIdx = 1;
//        } else{
//            animIdx = 0;
//        }
//    }
//
//    @Override
//    protected void updateSwingTime() {
//        int i = 10;
//        if (this.swinging) {
//            ++this.swingTime;
//            if (this.swingTime >= i) {
//                this.swingTime = 0;
//                this.swinging = false;
//            }
//        } else {
//            this.swingTime = 0;
//        }
//
//        this.attackAnim = (float)this.swingTime / (float)i;
//    }
//
//    @Override
//    public boolean isAlliedTo(net.minecraft.world.entity.Entity entity) {
//        if (entity instanceof RavagerHunterEntity || entity instanceof StoneCutterManEntity) {
//            return true;
//        }
//        return super.isAlliedTo(entity);
//    }
//
//    @Override
//    public float getFadeInTime(float partialTicks) {
//        return simpleFade.getFadeInTime(partialTicks);
//    }
//
//    @Override
//    public float getAnimTicks(float partialTicks) {
//        return simpleFade.getAnimTicks(partialTicks);
//    }
//
//    @Override
//    public int getAnimIndex() {
//        return simpleFade.getAnimIndex();
//    }
//
//    @Override
//    @javax.annotation.Nullable
//    public SimpleFadingOutAnimation getFadingOutAnimation() {
//        return simpleFade.getFadingOutAnimation();
//    }
//
//    @Override
//    public void setFadingOutAnimation(@javax.annotation.Nullable SimpleFadingOutAnimation animation) {
//        simpleFade.setFadingOutAnimation(animation);
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound() {
//        return SoundEvents.WOLF_AMBIENT;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource source) {
//        return SoundEvents.WOLF_HURT;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        return SoundEvents.WOLF_DEATH;
//    }
//}
