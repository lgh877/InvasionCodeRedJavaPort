package com.sweegy.invasioncodered.entity.stonecutterman.actions;

import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import com.sweegy.invasioncodered.util.EntityUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class StoneCutterManScreamAction {
    public static void execute(LevelAccessor world, StoneCutterManEntity mob,
                               double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        Entity target = mob.getTarget();
        if (target != null) {
            EntityUtil.lookAt(mob, target.getEyePosition(), 30);
        }
        if (actionTicks == 0) {
            mob.setInAction(true);
        } else if (actionTicks == 2) {
            mob.playSound(InvasioncoderedsweegyportModSounds.STONECUTTERMAN_INTRO.get(), 2.0F, mob.getVoicePitch());
        } else if (actionTicks > 40) {
            mob.setActionState(0);
            mob.spamAttackReady = true;
            mob.setInAction(false);
        }
    }
}
