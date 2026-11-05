package com.sweegy.invasioncodered.entity.brawler.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.BrawlerAnimationIndex;
import com.sweegy.invasioncodered.entity.brawler.BrawlerEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import java.util.EnumSet;

public class BrawlerPursuitGoal extends Goal {
    private final BrawlerEntity mob;
    private int recalcPath;

    public BrawlerPursuitGoal(BrawlerEntity mob) {
        this.mob = mob;
        setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = mob.getTarget();
        return target != null && target.isAlive() && !mob.isInAction();
    }

    @Override
    public boolean canContinueToUse() { return canUse(); }

    @Override
    public void start() { recalcPath = 0; }

    @Override
    public void stop() { mob.getNavigation().stop(); }

    @Override
    public void tick() {
        LivingEntity target = mob.getTarget();
        if (target == null) return;
        mob.getLookControl().setLookAt(target, 30.0F, 30.0F);

        if (--recalcPath <= 0) {
            recalcPath = 6 + mob.getRandom().nextInt(5);
            mob.getNavigation().moveTo(target, mob.getActionState() == BrawlerAnimationIndex.anims_aggressive ? 1.5D : 1D);
        }
    }
}
