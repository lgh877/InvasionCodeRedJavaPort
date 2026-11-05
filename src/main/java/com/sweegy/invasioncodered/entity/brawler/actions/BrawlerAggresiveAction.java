package com.sweegy.invasioncodered.entity.brawler.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.BrawlerAnimationIndex;
import com.sweegy.invasioncodered.entity.brawler.BrawlerEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class BrawlerAggresiveAction {
    public static void execute(LevelAccessor world, BrawlerEntity mob, double x, double y, double z) {
        if((mob.tickCount & 7) == 0){
            if(mob.blockAfterMelee){
                mob.blockAfterMelee = false;
                mob.setActionState(BrawlerAnimationIndex.anims_block);
                mob.nextBlockDuration = mob.getRandom().nextInt(60) + 40;
                return;
            }
            LivingEntity target = mob.getTarget();
            if(target != null && target.isAlive()){
                double flatDist = new Vec2((float) (target.getX() - mob.getX()), (float) (target.getZ() - mob.getZ())).length();
                double yDiff = target.getY() - mob.getY();
                double minDist = (mob.getBbWidth() + target.getBbWidth())/2;
                boolean yVailid = yDiff < mob.getBbHeight() && yDiff > -target.getBbHeight();
                mob.blockAfterMelee = mob.getRandom().nextFloat() > 0.9f;
                if(flatDist < minDist + mob.getBbWidth() * 2 && yVailid){
                    mob.doHurtTarget(target);
                    mob.swing(InteractionHand.MAIN_HAND);
                    mob.blockAfterMelee = mob.getRandom().nextFloat() > 0.6f;
                }else if(!yVailid || flatDist > minDist + mob.getBbWidth() * 12){
                    mob.setActionState(BrawlerAnimationIndex.anims_bowwalk);
                    mob.equipBow();
                }
            }else{
                mob.setActionState(0);
            }
        }
    }
}
