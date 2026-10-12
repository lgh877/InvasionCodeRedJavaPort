package com.sweegy.invasioncodered.entity.stonecutterman.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.GashslitAnimationIndex;
import com.sweegy.invasioncodered.client.model.animations.indices.StoneCutterManAnimationIndex;
import com.sweegy.invasioncodered.entity.ai.actions.AbstractActionSelector;
import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.entity.gashslit.actions.GashslitActionSelector;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.util.TargetContext;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;

public class StoneCutterManActionSelector extends AbstractActionSelector<StoneCutterManEntity> {
    public static final StoneCutterManActionSelector INSTANCE = new StoneCutterManActionSelector();

    private StoneCutterManActionSelector() {}

    @Override
    protected void chooseAction(StoneCutterManEntity mob, Entity target, TargetContext ctx) {
        boolean isYValid = ctx.isYWithin(ctx.mobHeight, -ctx.targetHeight);

        RandomSource random = mob.getRandom();
        int rand = random.nextInt(3);

        switch (rand) {
            case 0 -> {
                if (isYValid && ctx.isWithinFlatRange(ctx.minDist + ctx.mobWidth * 7.0D)) {
                    mob.setActionState(StoneCutterManAnimationIndex.anims_attack);
                }
            }
            case 1 -> {
                if (isYValid && ctx.isWithinFlatRange(ctx.minDist + ctx.mobWidth * 7.0D)) {
                    mob.setActionState(StoneCutterManAnimationIndex.anims_attack2);
                }
            }
            case 2 -> {
                if (isYValid && ctx.isWithinFlatRange(ctx.minDist + ctx.mobWidth * 7.0D)) {
                    mob.setActionState(StoneCutterManAnimationIndex.anims_attack3);
                }
            }
            default -> mob.setActionState(0);
        }
    }
}
