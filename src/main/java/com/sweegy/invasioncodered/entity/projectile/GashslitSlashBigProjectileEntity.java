package com.sweegy.invasioncodered.entity.projectile;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.util.RandomSource;

import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModEntities;

public class GashslitSlashBigProjectileEntity extends GashSlitSlashProjectileEntity {
	public GashslitSlashBigProjectileEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(InvasioncoderedsweegyportModEntities.GASHSLIT_SLASH_BIG_PROJECTILE.get(), world);
	}

	public GashslitSlashBigProjectileEntity(EntityType<? extends GashslitSlashBigProjectileEntity> type, Level world) {
		super(type, world);
	}

	public GashslitSlashBigProjectileEntity(EntityType<? extends GashslitSlashBigProjectileEntity> type, double x, double y, double z, Level world) {
		super(type, x, y, z, world);
	}

	// AbstractArrow가 해주던 초기 위치(눈높이) 설정 및 Owner 설정 복구
	public GashslitSlashBigProjectileEntity(EntityType<? extends GashslitSlashBigProjectileEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
	}

	public static GashslitSlashBigProjectileEntity shoot(Level world, LivingEntity entity, RandomSource source) {
		return shoot(world, entity, source, 1f, 5, 5);
	}

	public static GashslitSlashBigProjectileEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
		return shoot(world, entity, source, pullingPower * 1f, 5, 5);
	}

	public static GashslitSlashBigProjectileEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
		GashslitSlashBigProjectileEntity entityarrow = new GashslitSlashBigProjectileEntity(InvasioncoderedsweegyportModEntities.GASHSLIT_SLASH_BIG_PROJECTILE.get(), entity, world);
		entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		entityarrow.setBaseDamage(damage);
		entityarrow.setKnockback(knockback);
		world.addFreshEntity(entityarrow);
		return entityarrow;
	}

	public static GashslitSlashBigProjectileEntity shoot(LivingEntity entity, LivingEntity target) {
		GashslitSlashBigProjectileEntity entityarrow = new GashslitSlashBigProjectileEntity(InvasioncoderedsweegyportModEntities.GASHSLIT_SLASH_BIG_PROJECTILE.get(), entity, entity.level());
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1;
		double dz = target.getZ() - entity.getZ();
		entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1f * 2, 12.0F);
		entityarrow.setBaseDamage(5);
		entityarrow.setKnockback(5);
		entity.level().addFreshEntity(entityarrow);
		return entityarrow;
	}
}