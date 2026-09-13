package com.sweegy.invasioncodered.client.gui;

import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;

import net.minecraft.world.BossEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;
import net.minecraft.ChatFormatting;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class CustomBossBar {
	public static Map<Integer, Supplier<CustomBossBar>> customBossBars = new HashMap<>();
	public static final int GASHSLIT_BOSSBAR_HEIGHT = 22;
	public static final int GASHSLIT_BOSSBAR_WIDTH = 190;
	public static final int GASHSLIT_BOSSBAR_OFFSET_Y = -6;
	public static final int GASHSLIT_BOSSBAR_INCREMENT = 23;
	public static final int GASHSLIT_TITLE_OFFSET_Y = -3;
	public static final float GASHSLIT_FONT_SCALE = 1.2F;
	public static final float GASHSLIT_FILL_RATIO = 0.83F;
	static {
		// Create the custom colored title
		Component gashslitTitle = Component.literal("Gashslit").//
				withStyle(ChatFormatting.DARK_RED).//
				append(Component.literal(" ")).//
				append(Component.literal("The Emperor").//
						withStyle(ChatFormatting.BLACK));
		// Gashslit Bossbar for Phase 1
		customBossBars.put(1, () -> new CustomBossBar(//
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_phase_1.png"), // baseTexture (Frame)
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_bars.png"), // overlayTexture (Bars)
                GASHSLIT_BOSSBAR_WIDTH, // barWidth
                GASHSLIT_BOSSBAR_HEIGHT, // barHeight
                GASHSLIT_BOSSBAR_OFFSET_Y, // baseOffsetY
                GASHSLIT_BOSSBAR_INCREMENT, // verticalIncrement
                gashslitTitle, // customTitle
                GASHSLIT_TITLE_OFFSET_Y, // titleOffsetY
                GASHSLIT_FONT_SCALE, //
                GASHSLIT_FILL_RATIO //
        ));
		// Gashslit Bossbar for Phase 2
		customBossBars.put(2, () -> new FadingAnimationCustomBossBar(//
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_phase_1.png"), // prevBaseTexture (Frame)
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_bars.png"), // prevOverlayTexture (Bars)
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_phase_2.png"), // baseTexture (Frame)
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_bars.png"), // overlayTexture (Bars)
                GASHSLIT_BOSSBAR_WIDTH, // barWidth
                GASHSLIT_BOSSBAR_HEIGHT, // barHeight
                GASHSLIT_BOSSBAR_OFFSET_Y, // baseOffsetY
                GASHSLIT_BOSSBAR_INCREMENT, // verticalIncrement
                gashslitTitle, // customTitle
                GASHSLIT_TITLE_OFFSET_Y, // titleOffsetY
                GASHSLIT_FONT_SCALE, //
                GASHSLIT_FILL_RATIO, //
                40));
		// Gashslit Bossbar for death
		customBossBars.put(3, () -> new FadingAnimationCustomBossBar(//
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_phase_2.png"), // baseTexture (Frame)
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_bars.png"), // overlayTexture (Bars)
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_death.png"), // baseTexture (Frame)
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_bars.png"), // overlayTexture (Bars)
                GASHSLIT_BOSSBAR_WIDTH, // barWidth
                GASHSLIT_BOSSBAR_HEIGHT, // barHeight
                GASHSLIT_BOSSBAR_OFFSET_Y, // baseOffsetY
                GASHSLIT_BOSSBAR_INCREMENT, // verticalIncrement
                gashslitTitle, // customTitle
                GASHSLIT_TITLE_OFFSET_Y, // titleOffsetY
                GASHSLIT_FONT_SCALE, //
                GASHSLIT_FILL_RATIO, //
                40));
		// Gashslit Bossbar for final chance
		customBossBars.put(4, () -> new FadingAnimationWithVibrationCustomBossBar(//
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_phase_2.png"), // baseTexture (Frame)
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_bars.png"), // overlayTexture (Bars)
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_phase_3.png"), // baseTexture (Frame)
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_bars.png"), // overlayTexture (Bars)
                GASHSLIT_BOSSBAR_WIDTH, // barWidth
                GASHSLIT_BOSSBAR_HEIGHT, // barHeight
                GASHSLIT_BOSSBAR_OFFSET_Y, // baseOffsetY
                GASHSLIT_BOSSBAR_INCREMENT, // verticalIncrement
                gashslitTitle, // customTitle
                GASHSLIT_TITLE_OFFSET_Y, // titleOffsetY
                GASHSLIT_FONT_SCALE, //
                GASHSLIT_FILL_RATIO, //
                40, 1));
		// Gashslit Bossbar for death after final chance
		customBossBars.put(5, () -> new FadingAnimationCustomBossBar(//
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_phase_3.png"), // baseTexture (Frame)
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_bars.png"), // overlayTexture (Bars)
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_death.png"), // baseTexture (Frame)
                ResourceLocation.tryParse("invasioncodered:textures/screens/gashlit_bossbar_bars.png"), // overlayTexture (Bars)
                GASHSLIT_BOSSBAR_WIDTH, // barWidth
                GASHSLIT_BOSSBAR_HEIGHT, // barHeight
                GASHSLIT_BOSSBAR_OFFSET_Y, // baseOffsetY
                GASHSLIT_BOSSBAR_INCREMENT, // verticalIncrement
                gashslitTitle, // customTitle
                GASHSLIT_TITLE_OFFSET_Y, // titleOffsetY
                GASHSLIT_FONT_SCALE, //
                GASHSLIT_FILL_RATIO, //
                40));
	}
	private final ResourceLocation baseTexture;
	private final ResourceLocation overlayTexture;
	private final boolean hasOverlay, hasTitle, hasBaseTexture;
	private final int barWidth, barHeight, baseOffsetY, verticalIncrement, titleOffsetY;
	private final Component customTitle;
	private final float fontScale, fillRatio;

	/**
	* Constructs a new CustomBossBar.
	* Dimensions and offsets for the overlay are now identical to the base texture.
	*
	* @param baseTexture The background (frame) texture.
	* @param overlayTexture The fill (bars) texture. Assumes top half is empty state, bottom half is full state.
	* @param barWidth The total rendering width of the boss bar.
	* @param barHeight The rendering height of a single boss bar state.
	* @param baseOffsetY The Y-offset starting point for rendering the textures on screen.
	* @param verticalIncrement The value by which the bar increments vertically for multiple bosses.
	* @param customTitle The customized Component for the boss name.
	* @param titleOffsetY The Y-offset for rendering the title relative to the base bar's Y position.
	* @param fontScale The scale multiplier for the font size (1.0F is default).
	* @param fillRatio The ratio of the total width that actually contains the health bar (e.g., 0.72 for 72%).
	*/
	public CustomBossBar(ResourceLocation baseTexture, //
			ResourceLocation overlayTexture, //
			int barWidth, //
			int barHeight, //
			int baseOffsetY, //
			int verticalIncrement, //
			Component customTitle, //
			int titleOffsetY, //
			float fontScale, //
			float fillRatio) {
		this.baseTexture = baseTexture;
		this.overlayTexture = overlayTexture;
		this.hasOverlay = overlayTexture != null;
		this.hasTitle = customTitle != null;
		this.hasBaseTexture = baseTexture != null;
		this.barWidth = barWidth;
		this.barHeight = barHeight;
		this.baseOffsetY = baseOffsetY;
		this.verticalIncrement = verticalIncrement;
		this.customTitle = customTitle;
		this.titleOffsetY = titleOffsetY;
		this.fontScale = fontScale;
		this.fillRatio = fillRatio;
	}

	public CustomBossBar(ResourceLocation baseTexture, ResourceLocation overlayTexture, int barWidth, int barHeight, int baseOffsetY, int verticalIncrement, float fillRatio) {
		this(baseTexture, overlayTexture, barWidth, barHeight, baseOffsetY, verticalIncrement, null, 0, 1.0F, fillRatio);
	}

	public ResourceLocation getBaseTexture() {
		return baseTexture;
	}

	public ResourceLocation getOverlayTexture() {
		return overlayTexture;
	}

	public boolean hasOverlay() {
		return hasOverlay;
	}

	public int getBarWidth() {
		return barWidth;
	}

	public int getBarHeight() {
		return barHeight;
	}

	public int getBaseOffsetY() {
		return baseOffsetY;
	}

	public int getVerticalIncrement() {
		return verticalIncrement;
	}

	public Component getCustomTitle() {
		return customTitle;
	}

	public int getTitleOffsetY() {
		return titleOffsetY;
	}

	public float getFontScale() {
		return fontScale;
	}

	public float getFillRatio() {
		return fillRatio;
	}

	public void renderBossBar(CustomizeGuiOverlayEvent.BossEventProgress event) {
		GuiGraphics guiGraphics = event.getGuiGraphics();
		BossEvent bossEvent = event.getBossEvent();
		int y = event.getY();
		int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
		int barX = screenWidth / 2 - this.barWidth / 2;
		int barY = y + this.baseOffsetY;
		Minecraft.getInstance().getProfiler().push("ICRSweegyPortCustomBossBar");
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		if (this.hasBaseTexture) {
			guiGraphics.blit(this.baseTexture, barX, barY, 0, 0, this.barWidth, this.barHeight, this.barWidth, this.barHeight);
		}
		// 2. Draw Overlay Bar
		if (this.hasOverlay) {
			guiGraphics.blit(this.overlayTexture, barX, barY, 0, 0, this.barWidth, this.barHeight, this.barWidth, this.barHeight * 2);
			float activeBarWidth = this.barWidth * this.fillRatio;
			float leftMargin = (this.barWidth - activeBarWidth) / 2.0F;
			int renderWidth = (int) (leftMargin + (activeBarWidth * bossEvent.getProgress()));
			if (renderWidth > 0) {
				guiGraphics.blit(this.overlayTexture, barX, barY, 0, this.barHeight, renderWidth, this.barHeight, this.barWidth, this.barHeight * 2);
			}
		}
		// 3. Draw Title
		if (this.hasTitle) {
			int originalTextWidth = Minecraft.getInstance().font.width(this.customTitle);
			float scaledTextWidth = originalTextWidth * this.fontScale;
			float textX = (screenWidth / 2.0F) - (scaledTextWidth / 2.0F);
			float textY = barY + this.titleOffsetY;
			guiGraphics.pose().pushPose();
			guiGraphics.pose().translate(textX, textY, 0);
			guiGraphics.pose().scale(this.fontScale, this.fontScale, 1.0F);
			guiGraphics.drawString(Minecraft.getInstance().font, this.customTitle, 0, 0, 16777215);
			guiGraphics.pose().popPose();
		}
		Minecraft.getInstance().getProfiler().pop();
		event.setIncrement(this.verticalIncrement);
	}
}