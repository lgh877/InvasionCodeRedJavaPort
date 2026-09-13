package com.sweegy.invasioncodered.entity.gashslit.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.GashslitAnimationIndex;
import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.entity.projectile.GashSlitSlashProjectileEntity;
import com.sweegy.invasioncodered.entity.projectile.GashslitSlashBigProjectileEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModEntities;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class GashslitShootAction {
    public static void execute(LevelAccessor world, GashslitEntity mob, double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        mob.setInAction(true);
        Entity target = mob.getTarget();
        if (target != null && target.isAlive()) {
            mob.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(target.getX(), target.getEyeY(), target.getZ()));
        }

        if (mob.actionProgress == 0) {
            if (actionTicks > 3) {
                spawnProjectile(mob);
                mob.actionProgress = 1;
                mob.playSound(InvasioncoderedsweegyportModSounds.CURSED_SLASH_SHOOT.get(), 2.0F, 1.0F);
            }
        } else if (mob.actionProgress == 1) {
            if (actionTicks > 8) {
                spawnProjectile(mob);
                mob.actionProgress = 2;
                mob.playSound(InvasioncoderedsweegyportModSounds.CURSED_SLASH_SHOOT.get(), 2.0F, 1.0F);
            }
        } else if (mob.actionProgress == 2) {
            if (actionTicks > 9) {
                if (mob.rangeAttackShootCount-- > 0 && target != null && target.isAlive()) {
                    mob.setActionState(GashslitAnimationIndex.anims_shoot);
                } else {
                    mob.actionProgress = 3;
                }
            }
        } else if (mob.actionProgress == 3) {
            if (actionTicks > 11) {
                mob.setActionState(0);
                mob.setAnimSpeed(1);
                mob.setInAction(false);
            }
        }
    }

    private static void spawnProjectile(GashslitEntity mob) {
        Level projectileLevel = mob.level();
        if (!projectileLevel.isClientSide()) {
            Projectile projectile;
            if (!mob.isIn2Phase()) {
                GashSlitSlashProjectileEntity slash = new GashSlitSlashProjectileEntity(InvasioncoderedsweegyportModEntities.GASH_SLIT_SLASH_PROJECTILE.get(), projectileLevel);
                slash.setOwner(mob);
                slash.setBaseDamage((float) mob.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.4615f);
                projectile = slash;
            } else {
                GashslitSlashBigProjectileEntity bigSlash = new GashslitSlashBigProjectileEntity(InvasioncoderedsweegyportModEntities.GASHSLIT_SLASH_BIG_PROJECTILE.get(), projectileLevel);
                bigSlash.setOwner(mob);
                bigSlash.setBaseDamage((float) mob.getAttributeValue(Attributes.ATTACK_DAMAGE) * 2);
                projectile = bigSlash;
            }
            projectile.setPos(mob.getX(), mob.getEyeY() - 0.1, mob.getZ());
            projectile.shoot(mob.getLookAngle().x, mob.getLookAngle().y, mob.getLookAngle().z, 2, 0);
            projectileLevel.addFreshEntity(projectile);
        }
    }
}