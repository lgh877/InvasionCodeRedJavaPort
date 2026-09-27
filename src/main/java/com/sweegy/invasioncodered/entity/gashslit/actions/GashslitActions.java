package com.sweegy.invasioncodered.entity.gashslit.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.GashslitAnimationIndex;
import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.entity.ai.actions.ActionCommand;

import java.util.HashMap;
import java.util.Map;

public class GashslitActions {
    public static final Map<Integer, ActionCommand<GashslitEntity>> COMMAND_MAP = new HashMap<>();

    static {
        register(GashslitAnimationIndex.anims_attack, GashslitAttackAction::execute);
        register(GashslitAnimationIndex.anims_slowattack, GashslitSlowAttackAction::handleSlowAttack); // 또는 execute
        register(GashslitAnimationIndex.anims_quickat1, GashslitQuickAttackAction::execute);
        register(GashslitAnimationIndex.anims_quickat2, GashslitQuickAttackAction::execute);
        register(GashslitAnimationIndex.anims_quickat4, GashslitQuickAttackAction::execute);
        register(GashslitAnimationIndex.anims_quickat5, GashslitQuickAttackAction::execute);
        register(GashslitAnimationIndex.anims_prepare, GashslitDashPrepareAction::execute);
        register(GashslitAnimationIndex.anims_flew2, GashslitDashAction::execute);
        register(GashslitAnimationIndex.anims_shoot, GashslitShootAction::execute);
        register(GashslitAnimationIndex.anims_flex, GashslitFlexAction::execute);
        register(GashslitAnimationIndex.anims_block, GashslitBlockAction::execute);
        register(GashslitAnimationIndex.anims_stepback, GashslitStepbackAction::execute);
        register(GashslitAnimationIndex.anims_quickat3, GashslitBarragePrepareAction::execute);
        register(GashslitAnimationIndex.anims_charge, GashslitBarrageChargeAction::execute);
    }

    public static void register(int actionState, ActionCommand<GashslitEntity> command) {
        COMMAND_MAP.put(actionState, command);
    }

    public static ActionCommand<GashslitEntity> getAction(int actionState){
        return COMMAND_MAP.get(actionState);
    }
}