package com.sweegy.invasioncodered.entity.projectile;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModEntities;

public class DamageConfigurableSmallFireballEntity extends Fireball {
	public double baseDamage = 2.0D;
	public double knockback;

	public DamageConfigurableSmallFireballEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(InvasioncoderedsweegyportModEntities.DAMAGE_CONFIGURABLE_SMALL_FIREBALL.get(), world);
	}

	public DamageConfigurableSmallFireballEntity(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
		super(InvasioncoderedsweegyportModEntities.DAMAGE_CONFIGURABLE_SMALL_FIREBALL.get(), p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
	}

	public DamageConfigurableSmallFireballEntity(EntityType<? extends DamageConfigurableSmallFireballEntity> type, Level world) {
		super(type, world);
	}

	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.putDouble("damage", this.baseDamage);
		compoundTag.putDouble("knockback", knockback);
	}

	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);
		if (compoundTag.contains("damage")) {
			this.baseDamage = compoundTag.getDouble("damage");
		}
		if (compoundTag.contains("knockback")) {
			this.knockback = compoundTag.getDouble("knockback");
		}
	}

	protected void onHitEntity(EntityHitResult p_37386_) {
		super.onHitEntity(p_37386_);
		if (!this.level().isClientSide) {
			Entity entity = p_37386_.getEntity();
			Entity entity1 = this.getOwner();
			DamageSource damagesource;
			if (entity1 == null) {
				damagesource = new DamageSource(level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_PROJECTILE), this);
			} else {
				damagesource = new DamageSource(level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_PROJECTILE), this, entity1);
			}
			int i = entity.getRemainingFireTicks();
			entity.setSecondsOnFire(5);
			if (!entity.hurt(damagesource, (float) baseDamage)) {
				entity.setRemainingFireTicks(i);
			} else if (entity1 instanceof LivingEntity) {
				this.doEnchantDamageEffects((LivingEntity) entity1, entity);
				if (this.knockback > 0 && entity instanceof LivingEntity livingentity) {
					double d0 = Math.max(0.0D, 1.0D - livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
					Vec3 vec3 = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double) this.knockback * 0.6D * d0);
					if (vec3.lengthSqr() > 0.0D) {
						livingentity.push(vec3.x, 0.1D, vec3.z);
					}
				}
			}
		}
	}

	protected void onHitBlock(BlockHitResult p_37384_) {
		super.onHitBlock(p_37384_);
		if (!this.level().isClientSide) {
			Entity entity = this.getOwner();
			if (!(entity instanceof Mob) || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level(), entity)) {
				BlockPos blockpos = p_37384_.getBlockPos().relative(p_37384_.getDirection());
				if (this.level().isEmptyBlock(blockpos)) {
					this.level().setBlockAndUpdate(blockpos, BaseFireBlock.getState(this.level(), blockpos));
				}
			}
		}
	}

	protected void onHit(HitResult p_37388_) {
		super.onHit(p_37388_);
		if (!this.level().isClientSide) {
			this.discard();
		}
	}

	protected boolean canHitEntity(Entity entity) {
		boolean isNotAllied = true;
		Entity owner = getOwner();
		if (owner != null) {
			isNotAllied = !entity.isAlliedTo(owner);
		}
		return super.canHitEntity(entity) && isNotAllied;
	}

	public boolean isPickable() {
		return false;
	}

	public boolean hurt(DamageSource p_37381_, float p_37382_) {
		return false;
	}
}