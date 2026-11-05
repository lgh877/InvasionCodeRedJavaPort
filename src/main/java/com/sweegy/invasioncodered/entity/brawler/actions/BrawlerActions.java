package com.sweegy.invasioncodered.entity.brawler.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.BrawlerAnimationIndex;
import com.sweegy.invasioncodered.entity.ai.actions.ActionCommand;
import com.sweegy.invasioncodered.entity.brawler.BrawlerEntity;

import java.util.HashMap;
import java.util.Map;

public class BrawlerActions {
    public static final Map<Integer, ActionCommand<BrawlerEntity>> COMMAND_MAP = new HashMap<>();

    static {
        register(BrawlerAnimationIndex.anims_bowstay, BrawlerBowAttackAction::execute);
        register(BrawlerAnimationIndex.anims_block, BrawlerBlockAction::execute);
        register(BrawlerAnimationIndex.anims_aggressive, BrawlerAggresiveAction::execute);
        register(BrawlerAnimationIndex.anims_bowwalk, BrawlerBowActiveAction::execute);
    }

    private BrawlerActions() {}

    public static void register(int state, ActionCommand<BrawlerEntity> action) {
        COMMAND_MAP.put(state, action);
    }

    public static ActionCommand<BrawlerEntity> getAction(int state) {
        return COMMAND_MAP.get(state);
    }
}
