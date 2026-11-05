package com.sweegy.invasioncodered.entity.stonecutterman.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.StoneCutterManAnimationIndex;
import com.sweegy.invasioncodered.entity.ai.actions.AbstractActionSelector;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import com.sweegy.invasioncodered.util.TargetContext;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;

public class StoneCutterManActionSelector extends AbstractActionSelector<StoneCutterManEntity> {
    public static final StoneCutterManActionSelector INSTANCE = new StoneCutterManActionSelector();

    private StoneCutterManActionSelector() {
    }

    @Override
    protected void chooseAction(StoneCutterManEntity mob, Entity target, TargetContext ctx) {
        if(mob.avoidTicks-- > 0) return;
        if (mob.spamAttackReady) {
            mob.playSound(InvasioncoderedsweegyportModSounds.STONECUTTERMAN_SPAM.get(), 2.0F, mob.getVoicePitch());
            mob.setActionState(StoneCutterManAnimationIndex.anims_spam);
            mob.setStopLegAnim(true);
            mob.spamAttackReady = false;
            mob.spamComboSucceedCount = 0;
            mob.spamComboCount = 0;
            return;
        }
        boolean isYValid = ctx.isYWithin(ctx.mobHeight, -ctx.targetHeight);

        RandomSource random = mob.getRandom();
        int rand = random.nextInt(6);

        switch (rand) {
            case 0 -> {
                if (isYValid && ctx.isWithinFlatRange(ctx.minDist + ctx.mobWidth * 5.0D)) {
                    mob.setActionState(StoneCutterManAnimationIndex.anims_attack);
                }
            }
            case 1 -> {
                if (isYValid && ctx.isWithinFlatRange(ctx.minDist + ctx.mobWidth * 5.0D)) {
                    mob.setActionState(StoneCutterManAnimationIndex.anims_attack2);
                }
            }
            case 2 -> {
                if (isYValid && ctx.isWithinFlatRange(ctx.minDist + ctx.mobWidth * 4.0D)) {
                    mob.setActionState(StoneCutterManAnimationIndex.anims_attack3);
                }
            }
            case 3 -> {
                if (mob.screamCooltime++ > 3 && ctx.isWithinFlatRange(ctx.minDist + ctx.mobWidth * 15.0D)) {
                    mob.setActionState(StoneCutterManAnimationIndex.anims_intro);
                    mob.screamCooltime = 0;
                }
            }
            case 4 -> {
                if (mob.ramModeCoolTime++ > 3) {
                    mob.setActionState(StoneCutterManAnimationIndex.anims_ram);
                    mob.ramModeCoolTime = 0;
                    mob.canBounce = mob.getRandom().nextBoolean();
                }
            }
            case 5 -> {
                if (mob.jumpAttackCooltime++ > 3) {
                    mob.setActionState(StoneCutterManAnimationIndex.anims_air);
                    mob.jumpAttackCooltime = 0;
                }
            }
            default -> mob.setActionState(0);
        }
    }
}
