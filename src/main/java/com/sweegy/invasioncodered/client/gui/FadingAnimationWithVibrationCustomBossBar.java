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

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;

import java.util.concurrent.ThreadLocalRandom;

public class FadingAnimationWithVibrationCustomBossBar extends FadingAnimationCustomBossBar {
	private final float vibrationIntensity;

	public FadingAnimationWithVibrationCustomBossBar(//
			ResourceLocation prevBaseTexture, ResourceLocation prevOverlayTexture, //
			int prevBarWidth, int prevBarHeight, int prevBaseOffsetY, int prevVerticalIncrement, //
			Component prevCustomTitle, int prevTitleOffsetY, float prevFontScale, float prevFillRatio, //
			ResourceLocation baseTexture, ResourceLocation overlayTexture, //
			int barWidth, int barHeight, int baseOffsetY, int verticalIncrement, //
			Component customTitle, int titleOffsetY, float fontScale, float fillRatio, //
			int fadeOutTimeTicks, float vibrationIntensity) {
		super(prevBaseTexture, prevOverlayTexture, //
				prevBarWidth, prevBarHeight, prevBaseOffsetY, prevVerticalIncrement, //
				prevCustomTitle, prevTitleOffsetY, prevFontScale, prevFillRatio, //
				baseTexture, overlayTexture, //
				barWidth, barHeight, baseOffsetY, verticalIncrement, //
				customTitle, titleOffsetY, fontScale, fillRatio, //
				fadeOutTimeTicks);
		this.vibrationIntensity = vibrationIntensity;
	}

	public FadingAnimationWithVibrationCustomBossBar(//
			ResourceLocation prevBaseTexture, ResourceLocation prevOverlayTexture, //
			int prevBarWidth, int prevBarHeight, int prevBaseOffsetY, int prevVerticalIncrement, float prevFillRatio, //
			ResourceLocation baseTexture, ResourceLocation overlayTexture, //
			int barWidth, int barHeight, int baseOffsetY, int verticalIncrement, float fillRatio, //
			int fadeOutTimeTicks, float vibrationIntensity) {
		super(prevBaseTexture, prevOverlayTexture, //
				prevBarWidth, prevBarHeight, prevBaseOffsetY, prevVerticalIncrement, prevFillRatio, //
				baseTexture, overlayTexture, //
				barWidth, barHeight, baseOffsetY, verticalIncrement, fillRatio, //
				fadeOutTimeTicks);
		this.vibrationIntensity = vibrationIntensity;
	}

	public FadingAnimationWithVibrationCustomBossBar(//
			ResourceLocation prevBaseTexture, ResourceLocation prevOverlayTexture, //
			ResourceLocation baseTexture, ResourceLocation overlayTexture, //
			int barWidth, int barHeight, int baseOffsetY, int verticalIncrement, //
			Component customTitle, int titleOffsetY, float fontScale, float fillRatio, //
			int fadeOutTimeTicks, float vibrationIntensity) {
		super(prevBaseTexture, prevOverlayTexture, //
				baseTexture, overlayTexture, //
				barWidth, barHeight, baseOffsetY, verticalIncrement, //
				customTitle, titleOffsetY, fontScale, fillRatio, //
				fadeOutTimeTicks);
		this.vibrationIntensity = vibrationIntensity;
	}

	@Override
	public void renderBossBar(CustomizeGuiOverlayEvent.BossEventProgress event) {
		GuiGraphics guiGraphics = event.getGuiGraphics();
		if (this.vibrationIntensity > 0.0F) {
			ThreadLocalRandom random = ThreadLocalRandom.current();
			float offsetX = (random.nextFloat() - 0.5F) * 2.0F * this.vibrationIntensity;
			float offsetY = (random.nextFloat() - 0.5F) * 2.0F * this.vibrationIntensity;
			guiGraphics.pose().pushPose();
			guiGraphics.pose().translate(offsetX, offsetY, 0.0F);
			super.renderBossBar(event);
			guiGraphics.pose().popPose();
		} else {
			super.renderBossBar(event);
		}
	}

	public float getVibrationIntensity() {
		return this.vibrationIntensity;
	}
}