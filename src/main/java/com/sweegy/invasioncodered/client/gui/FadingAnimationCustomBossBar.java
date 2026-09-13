/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside com.sweegy.invasioncoderedjavaport as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package com.sweegy.invasioncodered.client.gui;

import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;

import net.minecraft.world.BossEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;
import net.minecraft.Util;

import com.mojang.blaze3d.systems.RenderSystem;

public class FadingAnimationCustomBossBar extends CustomBossBar {
	protected final ResourceLocation prevBaseTexture;
	protected final ResourceLocation prevOverlayTexture;
	protected final boolean prevHasOverlay, prevHasTitle, prevHasBaseTexture;
	protected final int prevBarWidth, prevBarHeight, prevBaseOffsetY;
	protected final Component prevCustomTitle;
	protected final int prevTitleOffsetY;
	protected final float prevFontScale, prevFillRatio;
	protected final int fadeOutTimeTicks;
	protected long fadeStartTime = -1;

	public FadingAnimationCustomBossBar(
			//
			ResourceLocation prevBaseTexture, ResourceLocation prevOverlayTexture, //
			int prevBarWidth, int prevBarHeight, int prevBaseOffsetY, int prevVerticalIncrement, //
			Component prevCustomTitle, int prevTitleOffsetY, float prevFontScale, float prevFillRatio, //
			//
			ResourceLocation baseTexture, ResourceLocation overlayTexture, //
			int barWidth, int barHeight, int baseOffsetY, int verticalIncrement, //
			Component customTitle, int titleOffsetY, float fontScale, float fillRatio, //
			//
			int fadeOutTimeTicks) {
		super(baseTexture, overlayTexture, barWidth, barHeight, baseOffsetY, verticalIncrement, customTitle, titleOffsetY, fontScale, fillRatio);
		this.prevBaseTexture = prevBaseTexture;
		this.prevOverlayTexture = prevOverlayTexture;
		this.prevHasBaseTexture = prevBaseTexture != null;
		this.prevHasOverlay = prevOverlayTexture != null;
		this.prevHasTitle = prevCustomTitle != null;
		this.prevBarWidth = prevBarWidth;
		this.prevBarHeight = prevBarHeight;
		this.prevBaseOffsetY = prevBaseOffsetY;
		this.prevCustomTitle = prevCustomTitle;
		this.prevTitleOffsetY = prevTitleOffsetY;
		this.prevFontScale = prevFontScale;
		this.prevFillRatio = prevFillRatio;
		this.fadeOutTimeTicks = fadeOutTimeTicks;
	}

	public FadingAnimationCustomBossBar(
			//
			ResourceLocation prevBaseTexture, ResourceLocation prevOverlayTexture, int prevBarWidth, int prevBarHeight, int prevBaseOffsetY, int prevVerticalIncrement, float prevFillRatio,
			//
			ResourceLocation baseTexture, ResourceLocation overlayTexture, int barWidth, int barHeight, int baseOffsetY, int verticalIncrement, float fillRatio,
			//
			int fadeOutTimeTicks) {
		this(prevBaseTexture, prevOverlayTexture, //
				prevBarWidth, prevBarHeight, prevBaseOffsetY, prevVerticalIncrement, null, 0, 1.0F, prevFillRatio, //
				baseTexture, overlayTexture, //
				barWidth, barHeight, baseOffsetY, verticalIncrement, null, 0, 1.0F, fillRatio, //
				fadeOutTimeTicks);
	}

	public FadingAnimationCustomBossBar(//
			ResourceLocation prevBaseTexture, ResourceLocation prevOverlayTexture, //
			ResourceLocation baseTexture, ResourceLocation overlayTexture, //
			int barWidth, int barHeight, int baseOffsetY, int verticalIncrement, //
			Component customTitle, int titleOffsetY, float fontScale, float fillRatio, //
			//
			int fadeOutTimeTicks) {
		this(prevBaseTexture, prevOverlayTexture, //
				barWidth, barHeight, baseOffsetY, verticalIncrement, customTitle, titleOffsetY, fontScale, fillRatio, //
				baseTexture, overlayTexture, //
				barWidth, barHeight, baseOffsetY, verticalIncrement, customTitle, titleOffsetY, fontScale, fillRatio, //
				fadeOutTimeTicks);
	}

	@Override
	public void renderBossBar(CustomizeGuiOverlayEvent.BossEventProgress event) {
		if (this.fadeStartTime == -1) {
			this.fadeStartTime = Util.getMillis();
		}
		long elapsedMs = Util.getMillis() - this.fadeStartTime;
		long durationMs = this.fadeOutTimeTicks * 50L;
		float progress = Math.min(1.0F, (float) elapsedMs / durationMs);
		GuiGraphics guiGraphics = event.getGuiGraphics();
		BossEvent bossEvent = event.getBossEvent();
		int y = event.getY();
		int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
		Minecraft.getInstance().getProfiler().push("ICRSweegyPortFadingCustomBossBar");
		if (progress < 1.0F) {
			float prevAlpha = 1.0F - progress;
			renderSingleBar(guiGraphics, bossEvent, y, screenWidth, this.prevBaseTexture, this.prevOverlayTexture, this.prevHasOverlay, this.prevBarWidth, this.prevBarHeight, this.prevBaseOffsetY, this.prevFillRatio, this.prevCustomTitle,
					this.prevTitleOffsetY, this.prevFontScale, prevAlpha);
		}
		float currentAlpha = Math.min(1.0F, progress * 1.5f);
		renderSingleBar(guiGraphics, bossEvent, y, screenWidth, this.getBaseTexture(), this.getOverlayTexture(), this.hasOverlay(), this.getBarWidth(), this.getBarHeight(), this.getBaseOffsetY(), this.getFillRatio(), this.getCustomTitle(),
				this.getTitleOffsetY(), this.getFontScale(), currentAlpha);
		Minecraft.getInstance().getProfiler().pop();
		event.setIncrement(this.getVerticalIncrement());
	}

	private void renderSingleBar(GuiGraphics guiGraphics, BossEvent bossEvent, int y, int screenWidth, ResourceLocation baseTex, ResourceLocation overlayTex, boolean hasOverlay, int barWidth, int barHeight, int baseOffsetY, float fillRatio,
			Component customTitle, int titleOffsetY, float fontScale, float alpha) {
		if (alpha <= 0.01F)
			return;
		int barX = screenWidth / 2 - barWidth / 2;
		int barY = y + baseOffsetY;
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
		if (baseTex != null) {
			guiGraphics.blit(baseTex, barX, barY, 0, 0, barWidth, barHeight, barWidth, barHeight);
		}
		if (hasOverlay) {
			guiGraphics.blit(overlayTex, barX, barY, 0, 0, barWidth, barHeight, barWidth, barHeight * 2);
			float activeBarWidth = barWidth * fillRatio;
			float leftMargin = (barWidth - activeBarWidth) / 2.0F;
			int renderWidth = (int) (leftMargin + (activeBarWidth * bossEvent.getProgress()));
			if (renderWidth > 0) {
				guiGraphics.blit(overlayTex, barX, barY, 0, barHeight, renderWidth, barHeight, barWidth, barHeight * 2);
			}
		}
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.disableBlend();
		Component titleToDraw = customTitle != null ? customTitle : bossEvent.getName();
		int originalTextWidth = Minecraft.getInstance().font.width(titleToDraw);
		float scaledTextWidth = originalTextWidth * fontScale;
		float textX = (screenWidth / 2.0F) - (scaledTextWidth / 2.0F);
		float textY = barY + titleOffsetY;
		guiGraphics.pose().pushPose();
		guiGraphics.pose().translate(textX, textY, 0);
		guiGraphics.pose().scale(fontScale, fontScale, 1.0F);
		int alphaInt = (int) (alpha * 255.0F) & 0xFF;
		int textColor = (alphaInt << 24) | 0xFFFFFF;
		guiGraphics.drawString(Minecraft.getInstance().font, titleToDraw, 0, 0, textColor);
		guiGraphics.pose().popPose();
	}
}