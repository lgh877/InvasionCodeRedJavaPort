package com.sweegy.invasioncodered.potion;

import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModAttributes;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CursedRageMobEffect extends MobEffect {
    public CursedRageMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -6750208);
        this.addAttributeModifier(InvasioncoderedsweegyportModAttributes.GASHSLIT_EQUITMENT_STATE_CHECKER.get(), "36343d69-8e99-3ced-9f6e-8796874481fa", 10, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if((entity.tickCount & 7) == 0) {
            ((ServerLevel) entity.level()).sendParticles(
                    InvasioncoderedsweegyportModParticleTypes.GASHSLIT_POPFLAME_PARTICLE.get(),
                    entity.getRandomX(entity.getBbWidth()),
                    entity.getRandomY(),
                    entity.getRandomZ(entity.getBbWidth()),
                    (int) (entity.getBbWidth() * entity.getBbHeight() * 3), 0, 0, 0, 0);
        }
    }
}