package com.sweegy.invasioncodered.entity.stonecutterman.actions;

import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import net.minecraft.world.level.LevelAccessor;

public class StoneCutterManDeadAction {
    public static void execute(LevelAccessor world, StoneCutterManEntity mob,
                               double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        if(actionTicks == 0){
            mob.isKnocked = true;
            mob.setInAction(true);
        }
        else if(actionTicks > 80){
            mob.discard();
        }
    }
}
