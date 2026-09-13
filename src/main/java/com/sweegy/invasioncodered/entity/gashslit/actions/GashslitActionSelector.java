package com.sweegy.invasioncodered.entity.gashslit.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.GashslitAnimationIndex;
import com.sweegy.invasioncodered.entity.ai.actions.AbstractActionSelector;
import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.util.TargetContext;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class GashslitActionSelector extends AbstractActionSelector<GashslitEntity> {

    // 싱글톤 인스턴스로 재사용
    public static final GashslitActionSelector INSTANCE = new GashslitActionSelector();

    private GashslitActionSelector() {}

    @Override
    protected void chooseAction(GashslitEntity mob, Entity target, TargetContext ctx) {
        boolean isYValid = ctx.isYWithin(ctx.mobWidth * 2.91D, -ctx.targetHeight + ctx.mobWidth * 1.25D);
        double rangeAttack1 = ctx.minDist + ctx.mobWidth * 7.0D;
        double rangeAttack2 = ctx.minDist + ctx.mobWidth * 13.0D;
        double rangeAttack3 = ctx.minDist + ctx.mobWidth * 2.5D;

        RandomSource random = mob.getRandom();
        int rand = random.nextInt(9);

        switch (rand) {
            case 0 -> {
                if (isYValid && ctx.isWithinFlatRange(rangeAttack1)) {
                    mob.setActionState(GashslitAnimationIndex.anims_attack);
                }
            }
            case 1 -> {
                if (mob.slowAttackCooltime++ > 1 && isYValid && ctx.isWithinFlatRange(rangeAttack2)) {
                    mob.setActionState(GashslitAnimationIndex.anims_slowattack);
                    mob.slowAttackCooltime = 0;
                }
            }
            case 2, 3 -> {
                if (isYValid && ctx.isWithinFlatRange(rangeAttack3)) {
                    setQuickAttack(mob, target);
                }
            }
            case 4 -> {
                if (mob.dashAttackCooltime++ > 1) {
                    mob.setActionState(GashslitAnimationIndex.anims_prepare);
                    mob.dashAttackCooltime = 0;
                }
            }
            case 5 -> {
                if (mob.rangeAttackCooltime++ > 1) {
                    mob.setActionState(GashslitAnimationIndex.anims_stepback);
                    mob.rangeAttackShootCount = random.nextInt(5) + 5;
                    mob.rangeAttackCooltime = 0;
                }
            }
            case 6 -> {
                if (!mob.isIn2Phase() && mob.batSummonCooltime++ > 3) {
                    mob.setActionState(GashslitAnimationIndex.anims_flex);
                    mob.batSummonCooltime = 0;
                }
            }
            case 7 -> {
                if (mob.blockCooltime++ > 1) {
                    mob.setActionState(GashslitAnimationIndex.anims_block);
                    mob.blockCooltime = 0;
                    mob.blockDuration = 10 + random.nextInt(80);
                }
            }
            case 8 -> {
                if (mob.barrageAtCooltime++ > 2) {
                    mob.setActionState(GashslitAnimationIndex.anims_quickat3);
                    mob.barrageAtCooltime = 0;
                }
            }
        }

        // 기본 선택이 되지 않았을 경우 폴백(Fallback) 선택
        if (mob.getActionState() == 0) {
            float anotherRand = random.nextFloat();
            if (anotherRand < 0.4F) {
                if (isYValid && ctx.isWithinFlatRange(rangeAttack2)) {
                    mob.setActionState(GashslitAnimationIndex.anims_slowattack);
                    mob.slowAttackCooltime = 0;
                }
            } else if (anotherRand < 0.8F) {
                double rangeAttackFallback = ctx.minDist + ctx.mobWidth * 5.5D;
                if (isYValid && ctx.isWithinFlatRange(rangeAttackFallback)) {
                    mob.setActionState(GashslitAnimationIndex.anims_attack);
                }
            } else {
                if (isYValid && ctx.isWithinFlatRange(rangeAttack3)) {
                    setQuickAttack(mob, target);
                }
            }
        }
    }

    private void setQuickAttack(GashslitEntity mob, Entity target) {
        mob.quickAttackCount = (mob.quickAttackCount + 1) & 3;
        switch (mob.quickAttackCount) {
            case 0 -> mob.setActionState(GashslitAnimationIndex.anims_quickat1);
            case 1 -> mob.setActionState(GashslitAnimationIndex.anims_quickat2);
            case 2 -> mob.setActionState(GashslitAnimationIndex.anims_quickat5);
            case 3 -> mob.setActionState(GashslitAnimationIndex.anims_quickat4);
        }
        mob.setAnimSpeed(3);
        mob.setSwingType((mob.quickAttackCount & 1), true);
        mob.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(target.getX(), target.getEyeY(), target.getZ()));
    }
}