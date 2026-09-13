package com.sweegy.invasioncodered.entity.projectile;

import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nullable;

import java.util.List;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;

import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModEntities;

import com.google.common.collect.Lists;

public class GashSlitSlashProjectileEntity extends Projectile {
	private double baseDamage = 2.0D;
	private int knockback = 0;
	private boolean hasUpdatedRotation = false;
	@Nullable
	private IntOpenHashSet piercingIgnoreEntityIds;
	@Nullable
	private List<Entity> piercedAndKilledEntities;

	public GashSlitSlashProjectileEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(InvasioncoderedsweegyportModEntities.GASH_SLIT_SLASH_PROJECTILE.get(), world);
		this.setNoGravity(true);
		this.setInitialRotation();
	}

	public GashSlitSlashProjectileEntity(EntityType<? extends GashSlitSlashProjectileEntity> type, Level world) {
		super(type, world);
		this.setNoGravity(true);
		this.setInitialRotation();
	}

	public GashSlitSlashProjectileEntity(EntityType<? extends GashSlitSlashProjectileEntity> type, double x, double y, double z, Level world) {
		this(type, world);
		this.setPos(x, y, z);
	}

	public GashSlitSlashProjectileEntity(EntityType<? extends GashSlitSlashProjectileEntity> type, LivingEntity entity, Level world) {
		this(type, entity.getX(), entity.getEyeY() - 0.1D, entity.getZ(), world);
		this.setOwner(entity);
	}

	@Override
	protected void defineSynchedData() {
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public void setInitialRotation() {
		Vec3 vec3 = this.getDeltaMovement();
		double d0 = vec3.horizontalDistance();
		this.setYRot((float) (Mth.atan2(vec3.x, vec3.z) * (double) (180F / (float) Math.PI)));
		this.setXRot((float) (Mth.atan2(vec3.y, d0) * (double) (180F / (float) Math.PI)));
		this.yRotO = this.getYRot();
		this.xRotO = this.getXRot();
	}

	@Override
	public void tick() {
		super.tick();
		if (this.tickCount > 60) {
			this.discard();
			return;
		}
		if (!this.level().isClientSide()) {
			HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
			if (hitresult.getType() != HitResult.Type.MISS) {
				this.onHit(hitresult);
				if (this.isRemoved())
					return;
			}
		}
		Vec3 currentVelocity = this.getDeltaMovement();
		double dx = currentVelocity.x;
		double dy = currentVelocity.y;
		double dz = currentVelocity.z;
		double newX = this.getX() + dx;
		double newY = this.getY() + dy;
		double newZ = this.getZ() + dz;
		if (!this.hasUpdatedRotation) {
			this.updateRotationOptimized(dx, dy, dz);
			this.hasUpdatedRotation = true;
		}
		this.setDeltaMovement(dx, dy, dz);
		this.setPos(newX, newY, newZ);
	}

	private void updateRotationOptimized(double dx, double dy, double dz) {
		double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
		float newYRot = (float) (Mth.atan2(dx, dz) * (180D / Math.PI));
		float newXRot = (float) (Mth.atan2(dy, horizontalDistance) * (180D / Math.PI));
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			this.setYRot(newYRot);
			this.setXRot(newXRot);
			this.yRotO = newYRot;
			this.xRotO = newXRot;
		} else {
			this.setYRot(lerpRotation(this.yRotO, newYRot));
			this.setXRot(lerpRotation(this.xRotO, newXRot));
		}
	}

	@Override
	public void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);
		Entity target = entityHitResult.getEntity();
		Entity owner = this.getOwner();
		target.invulnerableTime = 0;
		boolean isHit = false;
		if (this.piercingIgnoreEntityIds == null) {
			this.piercingIgnoreEntityIds = new IntOpenHashSet(5);
		}
		if (this.piercedAndKilledEntities == null) {
			this.piercedAndKilledEntities = Lists.newArrayListWithCapacity(5);
		}
		this.piercingIgnoreEntityIds.add(target.getId());
		if (owner != null) {
			if (target != owner && !owner.isAlliedTo(target)) {
				target.hurt(this.damageSources().mobProjectile(this, (LivingEntity) owner), (float) this.baseDamage);
				isHit = true;
			}
		} else {
			target.hurt(this.damageSources().thrown(this, this), (float) this.baseDamage);
			isHit = true;
		}
		if (isHit) {
			if (this.knockback > 0 && target instanceof LivingEntity livingTarget) {
				double velX = this.getDeltaMovement().x;
				double velZ = this.getDeltaMovement().z;
				double dist = Math.sqrt(velX * velX + velZ * velZ);
				if (dist > 0.0D) {
					double kbScale = ((double) this.knockback * 0.6D) / dist;
					livingTarget.push(velX * kbScale, 0.1D, velZ * kbScale);
				}
			}
			this.level().playSound(null, this.blockPosition(), SoundEvents.SHULKER_BULLET_HIT, SoundSource.NEUTRAL, 1, 1);
		}
	}

	@Override
	protected boolean canHitEntity(Entity entity) {
		if (!super.canHitEntity(entity)) {
			return false;
		}
		return this.piercingIgnoreEntityIds == null || !this.piercingIgnoreEntityIds.contains(entity.getId());
	}

	@Override
	protected void onHitBlock(BlockHitResult p_36755_) {
		super.onHitBlock(p_36755_);
		this.level().playSound(null, this.blockPosition(), SoundEvents.SHULKER_BULLET_HIT, SoundSource.NEUTRAL, 1, 1);
		this.discard();
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putDouble("damage", this.baseDamage);
		compound.putInt("knockback", this.knockback);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("damage", 99)) {
			this.baseDamage = compound.getDouble("damage");
		}
		if (compound.contains("knockback", 99)) {
			this.knockback = compound.getInt("knockback");
		}
	}

	public void setBaseDamage(double damage) {
		this.baseDamage = damage;
	}

	public double getBaseDamage() {
		return this.baseDamage;
	}

	public void setKnockback(int knockback) {
		this.knockback = knockback;
	}

	public int getKnockback() {
		return this.knockback;
	}

	public static GashSlitSlashProjectileEntity shoot(Level world, LivingEntity entity, RandomSource source) {
		return shoot(world, entity, source, 1f, 5, 5);
	}

	public static GashSlitSlashProjectileEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
		return shoot(world, entity, source, pullingPower * 1f, 5, 5);
	}

	public static GashSlitSlashProjectileEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
		GashSlitSlashProjectileEntity entityarrow = new GashSlitSlashProjectileEntity(InvasioncoderedsweegyportModEntities.GASH_SLIT_SLASH_PROJECTILE.get(), entity, world);
		entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		entityarrow.setBaseDamage(damage);
		entityarrow.setKnockback(knockback);
		world.addFreshEntity(entityarrow);
		return entityarrow;
	}

	public static GashSlitSlashProjectileEntity shoot(LivingEntity entity, LivingEntity target) {
		GashSlitSlashProjectileEntity entityarrow = new GashSlitSlashProjectileEntity(InvasioncoderedsweegyportModEntities.GASH_SLIT_SLASH_PROJECTILE.get(), entity, entity.level());
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1D;
		double dz = target.getZ() - entity.getZ();
		entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2D, dz, 2.0F, 12.0F);
		entityarrow.setBaseDamage(5);
		entityarrow.setKnockback(5);
		entity.level().addFreshEntity(entityarrow);
		return entityarrow;
	}
}