package com.sweegy.invasioncodered.entity.ai.actions;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;

@FunctionalInterface
public interface ActionCommand<E extends Mob> {
    void execute(LevelAccessor world, E mob, double x, double y, double z);
}
