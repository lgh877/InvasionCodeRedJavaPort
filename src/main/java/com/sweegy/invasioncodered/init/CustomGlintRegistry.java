package com.sweegy.invasioncodered.init;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.sweegy.invasioncodered.client.renderer.CustomRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomGlintRegistry {
    private static final Set<ResourceLocation> REGISTERED_TEXTURES = new HashSet<>();

    public static final ResourceLocation RAGE_GLINT = register("invasioncodered:textures/misc/red_glint.png");

    private static ResourceLocation register(String path) {
        ResourceLocation loc = ResourceLocation.tryParse(path);
        if (loc != null) {
            REGISTERED_TEXTURES.add(loc);
        }
        return loc;
    }

    public static void putFixedBuffers(Map<RenderType, BufferBuilder> map) {
        for (ResourceLocation texture : REGISTERED_TEXTURES) {
            map.put(CustomRenderTypes.getCustomGlint(texture), new BufferBuilder(256));
            map.put(CustomRenderTypes.getCustomGlintDirect(texture), new BufferBuilder(256));
            map.put(CustomRenderTypes.getCustomEntityGlint(texture), new BufferBuilder(256));
            map.put(CustomRenderTypes.getCustomEntityGlintDirect(texture), new BufferBuilder(256));
            map.put(CustomRenderTypes.getCustomArmorEntityGlint(texture), new BufferBuilder(256));
        }
    }
}