package com.sweegy.invasioncodered.mixin;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.sweegy.invasioncodered.init.CustomGlintRegistry;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.LinkedHashMap;
import java.util.Map;

@Mixin(MultiBufferSource.BufferSource.class)
public class BufferSourceMixin {
    @Mutable
    @Shadow @Final
    private Map<RenderType, BufferBuilder> fixedBuffers;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectCustomGlintBuffers(CallbackInfo ci) {
        Map<RenderType, BufferBuilder> mutableBuffers = new LinkedHashMap<>(this.fixedBuffers);
        CustomGlintRegistry.putFixedBuffers(mutableBuffers);
        this.fixedBuffers = mutableBuffers;
    }
}