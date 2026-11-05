package com.sweegy.invasioncodered.entity.brawler.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.BrawlerAnimationIndex;
import com.sweegy.invasioncodered.entity.brawler.BrawlerEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;

public class BrawlerBowActiveAction {
    public static void execute(LevelAccessor world, BrawlerEntity mob, double x, double y, double z) {
        if((mob.tickCount & 63) == 0){
            LivingEntity target = mob.getTarget();
            if(target != null && target.isAlive()){
                double flatDist = new Vec2((float) (target.getX() - mob.getX()), (float) (target.getZ() - mob.getZ())).length();
                double yDiff = target.getY() - mob.getY();
                double minDist = (mob.getBbWidth() + target.getBbWidth())/2;
                if(yDiff < mob.getBbHeight() && yDiff > -target.getBbHeight()
                        && flatDist < minDist + mob.getBbWidth() * 10){
                    mob.setActionState(BrawlerAnimationIndex.anims_aggressive);
                    mob.equipAxe();
                }else if(flatDist < minDist + mob.getBbWidth() * 50
                        && yDiff < mob.getBbHeight() * 5 && yDiff > -target.getBbHeight() - mob.getBbHeight() * 5){
                    mob.setActionState(BrawlerAnimationIndex.anims_bowstay);
                }
            }else{
                mob.setActionState(0);
                mob.equipAxe();
            }
        }
    }
}
