package com.sweegy.invasioncodered.entity.ravager_hunter.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.RavagerHunterAnimationIndex;
import com.sweegy.invasioncodered.entity.ravager_hunter.RavagerHunterEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.LevelAccessor;

public class RavagerHunterBowAttackAction {
    private RavagerHunterBowAttackAction() {}

    public static void execute(LevelAccessor world, RavagerHunterEntity mob, double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        mob.setDeltaMovement(
                mob.getDeltaMovement().x * 0.5,
                mob.getDeltaMovement().y > 0 ? mob.getDeltaMovement().y * 0.5 : mob.getDeltaMovement().y,
                mob.getDeltaMovement().z * 0.5
        );
        if (actionTicks < 10) {
            LivingEntity target = mob.getTarget();
            if (target != null && target.isAlive()) {
                mob.getLookControl().setLookAt(target, 60.0F, 60.0F);
            }
        }else{
            LivingEntity target = mob.getTarget();
            if (target != null && target.isAlive()) {
                mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
            }
        }
        if (actionTicks == 0) {
            mob.setInAction(true);
            mob.getNavigation().stop();
        } else if (actionTicks == 12) {
            LivingEntity target = mob.getTarget();
            if (target != null && target.isAlive() && mob.hasLineOfSight(target)) {
                Arrow arrow = new Arrow(mob.level(), mob);
                arrow.setBaseDamage(4.0D);
                arrow.setKnockback(1);
                double dx = target.getX() - mob.getX();
                double dy = target.getY(0.3333333333D) - arrow.getY();
                double dz = target.getZ() - mob.getZ();
                double horizontal = Math.sqrt(dx * dx + dz * dz);
                arrow.shoot(dx, dy + horizontal * 0.2D, dz, 1.6F, 8.0F);
                mob.level().addFreshEntity(arrow);
                mob.playSound(SoundEvents.ARROW_SHOOT, 1.0F, 1.0F);
            }
            mob.rangedCooldown = 20 + mob.getRandom().nextInt(21);
        } else if (actionTicks > 24) {
            mob.setInAction(false);
            mob.setActionState(RavagerHunterAnimationIndex.anims_bowwalk);
        }
    }
}
