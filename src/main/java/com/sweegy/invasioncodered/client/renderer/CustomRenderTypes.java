package com.sweegy.invasioncodered.client.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;

public class CustomRenderTypes extends RenderType {
	protected static final RenderStateShard.DepthTestStateShard EQUAL_DEPTH_TEST = new RenderStateShard.DepthTestStateShard("==", GL11.GL_EQUAL);

	public CustomRenderTypes(String name, VertexFormat format, VertexFormat.Mode mode, int bufferSize, boolean affectsCrumbling, boolean sortOnDemand, Runnable setupState, Runnable clearState) {
		super(name, format, mode, bufferSize, affectsCrumbling, sortOnDemand, setupState, clearState);
	}

	public static RenderType customEnergySwirl(ResourceLocation texture, float xOffset, float yOffset) {
		return RenderType.create(//
				"icr_sweegy_custom_energy_swirl", //
				DefaultVertexFormat.NEW_ENTITY, //
				VertexFormat.Mode.QUADS, //
				256, //
				false, //
				true, // sortOnDemand
				RenderType.CompositeState.builder()//
						.setShaderState(RenderStateShard.RENDERTYPE_ENERGY_SWIRL_SHADER)//
						.setTextureState(new RenderStateShard.TextureStateShard(texture, false, false))//
						.setTexturingState(new RenderStateShard.OffsetTexturingStateShard(xOffset, yOffset))//
						.setTransparencyState(RenderStateShard.ADDITIVE_TRANSPARENCY)//
						.setCullState(RenderStateShard.NO_CULL)//
						.setLightmapState(RenderStateShard.LIGHTMAP)//
						.setOverlayState(RenderStateShard.OVERLAY)//
						.setWriteMaskState(RenderStateShard.COLOR_WRITE) //
						.createCompositeState(false)//
		);
	}

	public static RenderType customEnergySwirl2(ResourceLocation texture, float xOffset, float yOffset) {
		return RenderType.create(//
				"icr_sweegy_custom_energy_swirl2", //
				DefaultVertexFormat.NEW_ENTITY, //
				VertexFormat.Mode.QUADS, //
				256, //
				false, //
				true, // sortOnDemand
				RenderType.CompositeState.builder()//
						.setShaderState(RenderStateShard.RENDERTYPE_ENERGY_SWIRL_SHADER)//
						.setTextureState(new RenderStateShard.TextureStateShard(texture, false, false))//
						.setTexturingState(new RenderStateShard.OffsetTexturingStateShard(xOffset, yOffset))//
						.setTransparencyState(RenderStateShard.ADDITIVE_TRANSPARENCY)//
						.setCullState(RenderStateShard.NO_CULL)//
						.setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST) //
						.setLightmapState(RenderStateShard.LIGHTMAP)//
						.setOverlayState(RenderStateShard.OVERLAY)//
						.setWriteMaskState(RenderStateShard.COLOR_WRITE) //
						.createCompositeState(false)//
		);
	}

	private static final Function<ResourceLocation, RenderType> ENTITY_TRANSLUCENT_DECAL = Util.memoize((texture) -> {
		RenderType.CompositeState renderState = RenderType.CompositeState.builder().setShaderState(RENDERTYPE_ENTITY_TRANSLUCENT_SHADER) //
				.setTextureState(new RenderStateShard.TextureStateShard(texture, false, false)).setTransparencyState(TRANSLUCENT_TRANSPARENCY) //
				.setDepthTestState(EQUAL_DEPTH_TEST) //
				.setCullState(NO_CULL).setLightmapState(LIGHTMAP).setOverlayState(OVERLAY).createCompositeState(true); //
		return create("icr_sweegy_entity_translucent_decal", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, true, //
				renderState);
	});

	public static RenderType entityTranslucentDecal(ResourceLocation texture) {
		return ENTITY_TRANSLUCENT_DECAL.apply(texture);
	}

	private static final Function<ResourceLocation, RenderType> CUSTOM_EXPLOSION_ALPHA = Util.memoize((texture) -> {
		RenderType.CompositeState renderState = RenderType.CompositeState.builder().setShaderState(RENDERTYPE_ENTITY_ALPHA_SHADER).setTextureState(new RenderStateShard.TextureStateShard(texture, false, false)).setCullState(NO_CULL)
				.setWriteMaskState(DEPTH_WRITE).createCompositeState(true);
		return create("icr_sweegy_custom_explosion_alpha", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, true, renderState);
	});

	public static RenderType customExplosionAlpha(ResourceLocation texture) {
		return CUSTOM_EXPLOSION_ALPHA.apply(texture);
	}

    private static final Map<ResourceLocation, RenderType> GLINT_CACHE = new HashMap<>();
    private static final Map<ResourceLocation, RenderType> GLINT_DIRECT_CACHE = new HashMap<>();
    private static final Map<ResourceLocation, RenderType> ENTITY_GLINT_CACHE = new HashMap<>();
    private static final Map<ResourceLocation, RenderType> ENTITY_GLINT_DIRECT_CACHE = new HashMap<>();
    private static final Map<ResourceLocation, RenderType> ARMOR_GLINT_CACHE = new HashMap<>();

    //All based on vanilla rendertypes
    public static RenderType getCustomGlint(ResourceLocation texture) {
        return GLINT_CACHE.computeIfAbsent(texture, tex -> {
            RenderType.CompositeState state = RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_GLINT_SHADER)
                    .setTextureState(new RenderStateShard.TextureStateShard(tex, true, false))
                    .setWriteMaskState(COLOR_WRITE)
                    .setCullState(NO_CULL)
                    .setDepthTestState(EQUAL_DEPTH_TEST)
                    .setTransparencyState(GLINT_TRANSPARENCY)
                    .setTexturingState(GLINT_TEXTURING)
                    .createCompositeState(false);

            return create("icr_sweegy_custom_glint", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false, state);
        });
    }

    public static RenderType getCustomGlintDirect(ResourceLocation texture) {
        return GLINT_DIRECT_CACHE.computeIfAbsent(texture, tex -> {
            RenderType.CompositeState state = RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_GLINT_DIRECT_SHADER)
                    .setTextureState(new RenderStateShard.TextureStateShard(tex, true, false))
                    .setWriteMaskState(COLOR_WRITE)
                    .setCullState(NO_CULL)
                    .setDepthTestState(EQUAL_DEPTH_TEST)
                    .setTransparencyState(GLINT_TRANSPARENCY)
                    .setTexturingState(GLINT_TEXTURING)
                    .createCompositeState(false);

            return create("icr_sweegy_custom_glint_direct", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false, state);
        });
    }

    public static RenderType getCustomEntityGlint(ResourceLocation texture) {
        return ENTITY_GLINT_CACHE.computeIfAbsent(texture, tex -> {
            RenderType.CompositeState state = RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_ENTITY_GLINT_SHADER)
                    .setTextureState(new RenderStateShard.TextureStateShard(tex, true, false))
                    .setWriteMaskState(COLOR_WRITE)
                    .setCullState(NO_CULL)
                    .setDepthTestState(EQUAL_DEPTH_TEST)
                    .setTransparencyState(GLINT_TRANSPARENCY)
                    .setOutputState(ITEM_ENTITY_TARGET)
                    .setTexturingState(ENTITY_GLINT_TEXTURING)
                    .createCompositeState(false);

            return create("icr_sweegy_custom_armor_entity_glint", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, true, false, state);
        });
    }

    public static RenderType getCustomEntityGlintDirect(ResourceLocation texture) {
        return ENTITY_GLINT_DIRECT_CACHE.computeIfAbsent(texture, tex -> {
            RenderType.CompositeState state = RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_ENTITY_GLINT_DIRECT_SHADER)
                    .setTextureState(new RenderStateShard.TextureStateShard(tex, true, false))
                    .setWriteMaskState(COLOR_WRITE)
                    .setCullState(NO_CULL)
                    .setDepthTestState(EQUAL_DEPTH_TEST)
                    .setTransparencyState(GLINT_TRANSPARENCY)
                    .setTexturingState(ENTITY_GLINT_TEXTURING)
                    .createCompositeState(false);

            return create("icr_sweegy_custom_armor_entity_glint_direct", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, true, false, state);
        });
    }

    public static RenderType getCustomArmorEntityGlint(ResourceLocation texture) {
        return ARMOR_GLINT_CACHE.computeIfAbsent(texture, tex -> {
            RenderType.CompositeState state = RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_ARMOR_ENTITY_GLINT_SHADER)
                    .setTextureState(new RenderStateShard.TextureStateShard(tex, true, false))
                    .setWriteMaskState(COLOR_WRITE)
                    .setCullState(NO_CULL)
                    .setDepthTestState(EQUAL_DEPTH_TEST)
                    .setTransparencyState(GLINT_TRANSPARENCY)
                    .setTexturingState(ENTITY_GLINT_TEXTURING)
                    .setLayeringState(VIEW_OFFSET_Z_LAYERING)
                    .createCompositeState(false);

            return create("icr_sweegy_custom_armor_glint", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, true, false, state);
        });
    }
}