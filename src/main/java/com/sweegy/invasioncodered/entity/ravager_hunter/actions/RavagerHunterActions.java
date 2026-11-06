package com.sweegy.invasioncodered.entity.ravager_hunter.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.RavagerHunterAnimationIndex;
import com.sweegy.invasioncodered.entity.ai.actions.ActionCommand;
import com.sweegy.invasioncodered.entity.ravager_hunter.RavagerHunterEntity;

import java.util.HashMap;
import java.util.Map;

public class RavagerHunterActions {
    public static final Map<Integer, ActionCommand<RavagerHunterEntity>> COMMAND_MAP = new HashMap<>();

    static {
        register(RavagerHunterAnimationIndex.anims_bowstay, RavagerHunterBowAttackAction::execute);
        register(RavagerHunterAnimationIndex.anims_block, RavagerHunterBlockAction::execute);
        register(RavagerHunterAnimationIndex.anims_aggressive, RavagerHunterAggresiveAction::execute);
        register(RavagerHunterAnimationIndex.anims_bowwalk, RavagerHunterBowActiveAction::execute);
    }

    private RavagerHunterActions() {}

    public static void register(int state, ActionCommand<RavagerHunterEntity> action) {
        COMMAND_MAP.put(state, action);
    }

    public static ActionCommand<RavagerHunterEntity> getAction(int state) {
        return COMMAND_MAP.get(state);
    }
}
