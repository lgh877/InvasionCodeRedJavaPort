package com.sweegy.invasioncodered.entity.ai.actions;

import com.sweegy.invasioncodered.util.TargetContext;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;

public abstract class AbstractActionSelector<T extends Mob> {
    public void evaluateAndChoose(T mob, Entity target, double x, double y, double z) {
        if (mob == null || target == null || !target.isAlive()) {
            return;
        }
        TargetContext ctx = new TargetContext(mob, target, x, y, z);
        chooseAction(mob, target, ctx);
    }

    protected abstract void chooseAction(T mob, Entity target, TargetContext ctx);
}