package com.sweegy.invasioncodered.entity.stonecutterman.actions;

import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.LevelAccessor;

public class StoneCutterManKnockDownAction {
    public static void execute(LevelAccessor world, StoneCutterManEntity mob,
                               double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        if(actionTicks == 0){
            mob.setInAction(true);
        }
        else if(actionTicks > 80){
            if(mob.isAlive()) {
                mob.setInAction(false);
                mob.setActionState(0);
                mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 1));
            }else{
                mob.discard();
            }
        }
    }
}
