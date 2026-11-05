package com.sweegy.invasioncodered.entity.stonecutterman.control;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;

public class StoneCutterManMoveControl extends MoveControl {
    public float rotationLimit = 90;
    public StoneCutterManMoveControl(Mob mob){
        super(mob);
    }

    @Override
    protected float rotlerp(float p_24992_, float p_24993_, float p_24994_) {
        return super.rotlerp(p_24992_, p_24993_, rotationLimit);
    }
}
