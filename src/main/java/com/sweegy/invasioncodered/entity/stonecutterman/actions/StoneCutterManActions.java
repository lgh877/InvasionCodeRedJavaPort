package com.sweegy.invasioncodered.entity.stonecutterman.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.StoneCutterManAnimationIndex;
import com.sweegy.invasioncodered.entity.ai.actions.ActionCommand;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;

import java.util.HashMap;
import java.util.Map;

public class StoneCutterManActions {
    public static final Map<Integer, ActionCommand<StoneCutterManEntity>> COMMAND_MAP = new HashMap<>();

    static {
        COMMAND_MAP.put(StoneCutterManAnimationIndex.anims_attack, StoneCutterManMeleeMode1Action::execute);
        COMMAND_MAP.put(StoneCutterManAnimationIndex.anims_attack2, StoneCutterManMeleeMode2Action::execute);
        COMMAND_MAP.put(StoneCutterManAnimationIndex.anims_attack3, StoneCutterManMeleeMode3Action::execute);
        COMMAND_MAP.put(StoneCutterManAnimationIndex.anims_dead, StoneCutterManDeadAction::execute);
        COMMAND_MAP.put(StoneCutterManAnimationIndex.anims_knocked, StoneCutterManKnockedAction::execute);
        COMMAND_MAP.put(StoneCutterManAnimationIndex.anims_spam, StoneCutterManMeleeSpamAction::execute);
        COMMAND_MAP.put(StoneCutterManAnimationIndex.anims_intro, StoneCutterManScreamAction::execute);
        COMMAND_MAP.put(StoneCutterManAnimationIndex.anims_ram, StoneCutterManRamMode1Action::execute);
        COMMAND_MAP.put(StoneCutterManAnimationIndex.anims_spin, StoneCutterManRamMode2Action::execute);
        COMMAND_MAP.put(StoneCutterManAnimationIndex.anims_sidemove, StoneCutterManSideMoveRightAction::execute);
        COMMAND_MAP.put(StoneCutterManAnimationIndex.anims_sidemove2, StoneCutterManSideMoveLeftAction::execute);
        COMMAND_MAP.put(StoneCutterManAnimationIndex.anims_air, StoneCutterManJumpAction::execute);
        COMMAND_MAP.put(StoneCutterManAnimationIndex.anims_airland, StoneCutterManLandingAction::execute);
        COMMAND_MAP.put(StoneCutterManAnimationIndex.anims_Explode, StoneCutterManExplodeAction::execute);
    }
}
