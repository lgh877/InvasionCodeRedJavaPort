package com.sweegy.invasioncodered.client.model.animations.indices;

import com.sweegy.invasioncodered.client.model.animations.CuccitaAnimation;
import com.sweegy.invasioncodered.client.model.animations.RavagerHunterAnimation;
import net.minecraft.client.animation.AnimationDefinition;

public class CuccitaAnimationIndex {
    public static final AnimationDefinition anims[] = {//
            CuccitaAnimation.pose, //
            CuccitaAnimation.run, //
            CuccitaAnimation.shake, //
    };
    public static final int
            anims_pose = 0,
            anims_run = 1,
            anims_shke = 2;
}
