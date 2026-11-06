package com.sweegy.invasioncodered.entity.ravager_hunter.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.RavagerHunterAnimationIndex;
import com.sweegy.invasioncodered.entity.ravager_hunter.RavagerHunterEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import java.util.EnumSet;

public class RavagerHunterPursuitGoal extends Goal {
    private final RavagerHunterEntity mob;
    private int recalcPath;

    public RavagerHunterPursuitGoal(RavagerHunterEntity mob) {
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
            mob.getNavigation().moveTo(target, mob.getActionState() == RavagerHunterAnimationIndex.anims_aggressive ? 1.5D : 1D);
        }
    }
}
