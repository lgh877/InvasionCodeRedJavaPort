package com.sweegy.invasioncodered.entity.gashslit.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.GashslitAnimationIndex;
import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.entity.ai.actions.ActionCommand;

import java.util.HashMap;
import java.util.Map;

public class GashslitActions {
    public static final Map<Integer, ActionCommand<GashslitEntity>> COMMAND_MAP = new HashMap<>();

    static {
        COMMAND_MAP.put(GashslitAnimationIndex.anims_attack, GashslitAttackAction::execute);
        COMMAND_MAP.put(GashslitAnimationIndex.anims_slowattack, GashslitSlowAttackAction::handleSlowAttack); // 또는 execute
        COMMAND_MAP.put(GashslitAnimationIndex.anims_quickat1, GashslitQuickAttackAction::execute);
        COMMAND_MAP.put(GashslitAnimationIndex.anims_quickat2, GashslitQuickAttackAction::execute);
        COMMAND_MAP.put(GashslitAnimationIndex.anims_quickat4, GashslitQuickAttackAction::execute);
        COMMAND_MAP.put(GashslitAnimationIndex.anims_quickat5, GashslitQuickAttackAction::execute);
        COMMAND_MAP.put(GashslitAnimationIndex.anims_prepare, GashslitDashPrepareAction::execute);
        COMMAND_MAP.put(GashslitAnimationIndex.anims_flew2, GashslitDashAction::execute);
        COMMAND_MAP.put(GashslitAnimationIndex.anims_shoot, GashslitShootAction::execute);
        COMMAND_MAP.put(GashslitAnimationIndex.anims_flex, GashslitFlexAction::execute);
        COMMAND_MAP.put(GashslitAnimationIndex.anims_block, GashslitBlockAction::execute);
        COMMAND_MAP.put(GashslitAnimationIndex.anims_stepback, GashslitStepbackAction::execute);
        COMMAND_MAP.put(GashslitAnimationIndex.anims_quickat3, GashslitBarragePrepareAction::execute);
        COMMAND_MAP.put(GashslitAnimationIndex.anims_charge, GashslitBarrageChargeAction::execute);
    }
}