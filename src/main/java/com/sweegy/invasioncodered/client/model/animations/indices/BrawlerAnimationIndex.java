package com.sweegy.invasioncodered.client.model.animations.indices;

import com.sweegy.invasioncodered.client.model.animations.brawlerAnimation;
import com.sweegy.invasioncodered.client.model.animations.gashslitAnimation;
import net.minecraft.client.animation.AnimationDefinition;

public class BrawlerAnimationIndex {
    public static final AnimationDefinition anims[] = {//
            brawlerAnimation.block, //
            brawlerAnimation.bowstay, //
            brawlerAnimation.bowwalk, //
            brawlerAnimation.aggressive, //
    };
    public static final int
            anims_block = 1,
            anims_bowstay = 2,
            anims_bowwalk = 3,
            anims_aggressive = 4;
}
