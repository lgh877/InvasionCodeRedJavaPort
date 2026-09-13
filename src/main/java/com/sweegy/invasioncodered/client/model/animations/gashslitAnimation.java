package com.sweegy.invasioncodered.client.model.animations;

import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.AnimationChannel;

// Save this class in your mod and generate all required imports
/**
 * Made with Blockbench 5.1.3 Exported for Minecraft version 1.19 or later with
 * Mojang mappings
 * 
 * @author Author
 */
public class gashslitAnimation {
	public static final AnimationDefinition walk = AnimationDefinition.Builder.withLength(1.5F).looping()
			.addAnimation("robes", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5F, KeyframeAnimations.degreeVec(16.98F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(16.98F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2083F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4167F, KeyframeAnimations.degreeVec(39.54F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.7084F, KeyframeAnimations.degreeVec(16.7F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.0F, KeyframeAnimations.degreeVec(-37.13F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(-38.42F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-31.8F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4167F, KeyframeAnimations.degreeVec(-44.54F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.7084F, KeyframeAnimations.degreeVec(-23.93F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(29.06F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25F, KeyframeAnimations.degreeVec(34.44F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(2.5F, -7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.9583F, KeyframeAnimations.degreeVec(2.5F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5F, KeyframeAnimations.degreeVec(-25.0F, -10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.0F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(-25.0F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-15.32F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4167F, KeyframeAnimations.degreeVec(-19.7F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.625F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8333F, KeyframeAnimations.degreeVec(6.84F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0417F, KeyframeAnimations.degreeVec(18.79F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(17.32F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.88F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2083F, KeyframeAnimations.posVec(0.0F, 1.73F, -0.77F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.4167F, KeyframeAnimations.posVec(0.0F, 0.35F, -0.98F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.625F, KeyframeAnimations.posVec(0.0F, 0.0F, -0.5F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.8333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.34F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0417F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.94F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25F, KeyframeAnimations.posVec(0.0F, 0.35F, 0.87F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 1.88F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2083F, KeyframeAnimations.degreeVec(15.32F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4167F, KeyframeAnimations.degreeVec(19.7F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.625F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-6.84F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0417F, KeyframeAnimations.degreeVec(-18.79F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(-17.32F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.77F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.4167F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.98F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.625F, KeyframeAnimations.posVec(0.0F, 1.97F, 0.5F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.8333F, KeyframeAnimations.posVec(0.0F, 1.53F, -0.34F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0417F, KeyframeAnimations.posVec(0.0F, 0.0F, -0.94F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.25F, KeyframeAnimations.posVec(0.0F, 0.0F, -0.87F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.build();
	public static final AnimationDefinition run = AnimationDefinition.Builder.withLength(0.6667F).looping()
			.addAnimation("whole", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.1667F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.3333F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.6667F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("robes",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.0833F, KeyframeAnimations.degreeVec(57.4053F, 4.3288F, -2.5048F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.1667F, KeyframeAnimations.degreeVec(43.4786F, -14.3734F, -2.5875F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.3333F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.4167F, KeyframeAnimations.degreeVec(57.4053F, 4.3288F, -2.5048F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(43.4786F, -14.3734F, -2.5875F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.6667F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("robes", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.0833F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 0.9F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.1667F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.4167F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 0.9F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.6667F, KeyframeAnimations.scaleVec(1.0F, 0.9F, 1.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.1667F, KeyframeAnimations.degreeVec(30.0F, 7.5F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 15.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5F, KeyframeAnimations.degreeVec(-30.0F, 7.5F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 15.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("leftArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -7.5F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.1667F, KeyframeAnimations.degreeVec(-30.0F, -7.5F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, -7.5F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(30.0F, -7.5F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, -7.5F, -12.5F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.0833F, KeyframeAnimations.degreeVec(-14.24F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.1667F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(-13.83F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.58F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4167F, KeyframeAnimations.degreeVec(14.65F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5F, KeyframeAnimations.degreeVec(19.98F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5833F, KeyframeAnimations.degreeVec(13.4F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-1.16F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.88F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.0833F, KeyframeAnimations.posVec(0.0F, 1.81F, -0.71F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.1667F, KeyframeAnimations.posVec(0.0F, 0.66F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, 0.0F, -0.69F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.03F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4167F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.73F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5833F, KeyframeAnimations.posVec(0.0F, 0.94F, 0.67F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.6667F, KeyframeAnimations.posVec(0.0F, 1.92F, -0.06F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.0833F, KeyframeAnimations.degreeVec(14.24F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.1667F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.25F, KeyframeAnimations.degreeVec(13.83F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-0.58F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4167F, KeyframeAnimations.degreeVec(-14.65F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(-19.98F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5833F, KeyframeAnimations.degreeVec(-13.4F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(1.16F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.0833F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.71F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.1667F, KeyframeAnimations.posVec(0.0F, 0.71F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, 1.83F, 0.69F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, 1.86F, -0.03F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4167F, KeyframeAnimations.posVec(0.0F, 0.78F, -0.73F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5833F, KeyframeAnimations.posVec(0.0F, 0.0F, -0.67F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.6667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.06F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.0833F, KeyframeAnimations.degreeVec(-67.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.4167F, KeyframeAnimations.degreeVec(-67.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 2.0F, 2.0F), AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition block = AnimationDefinition.Builder.withLength(0.2083F).looping()
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -10.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("robes", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-81.2802F, 15.8665F, 41.274F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-71.0948F, -4.0735F, -38.1995F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(42.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -12.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -9.0F, -4.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("whole", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 9.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("robe", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("robe3", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -12.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("robe3", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-66.9353F, 31.2508F, -38.4186F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(-2.0F, 4.0F, 2.0F), AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition charge = AnimationDefinition.Builder.withLength(1.75F).looping()
			.addAnimation("torso",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.5F, KeyframeAnimations.degreeVec(38.3552F, -34.0778F, -19.9548F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.5F, KeyframeAnimations.degreeVec(-25.535F, 30.961F, 2.3657F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.5F, KeyframeAnimations.degreeVec(-111.293F, 37.3329F, -3.8261F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("leftArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.5F, KeyframeAnimations.degreeVec(10.0618F, -7.4911F, -33.797F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition attack = AnimationDefinition.Builder.withLength(0.6667F).addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.1249F, -10.8517F, -4.948F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -37.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, -37.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.4167F, KeyframeAnimations.degreeVec(12.8F, 29.9393F, 0.6242F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("leftArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, -12.5F, -60.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.25F, KeyframeAnimations.degreeVec(11.4919F, -12.474F, -110.293F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.375F, KeyframeAnimations.degreeVec(6.4957F, -15.6363F, -89.9223F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.4167F, KeyframeAnimations.degreeVec(-100.303F, 27.1711F, -78.5452F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.5417F, KeyframeAnimations.degreeVec(33.596F, 21.911F, -69.1907F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.2917F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.375F, KeyframeAnimations.scaleVec(1.0F, 1.4F, 1.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.4583F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.375F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(20.4092F, -19.0921F, 36.813F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("leftItemLayer", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(-25.0F, 0.0F, 25.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.build();
	public static final AnimationDefinition slowattack = AnimationDefinition.Builder.withLength(1.875F).addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7083F, KeyframeAnimations.degreeVec(0.0F, -42.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9167F, KeyframeAnimations.degreeVec(0.0F, -42.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(21.5811F, 45.3326F, 16.3816F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(24.9694F, 52.2268F, 20.877F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-60.0F, 0.0F, 67.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4583F, KeyframeAnimations.degreeVec(-60.0F, 0.0F, 67.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(-82.5F, 0.0F, 67.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.9583F, KeyframeAnimations.degreeVec(-82.5F, 0.0F, 67.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0417F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 67.5F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.5F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 67.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.SCALE, new Keyframe(0.9583F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0417F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.3F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.1667F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.4583F, KeyframeAnimations.degreeVec(1.3184F, -9.9136F, -7.6143F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(1.0417F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(1.4583F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(1.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.build();
	public static final AnimationDefinition flex = AnimationDefinition.Builder.withLength(1.5417F)
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.01F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0.0F, 27.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.25F, KeyframeAnimations.degreeVec(15.0F, 27.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.4167F, KeyframeAnimations.degreeVec(15.0F, 27.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5833F, KeyframeAnimations.degreeVec(-35.0F, 27.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-114.0F, 27.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.875F, KeyframeAnimations.degreeVec(-115.82F, 32.89F, -3.21F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.9583F, KeyframeAnimations.degreeVec(-118.5285F, 40.0163F, -7.7456F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0417F, KeyframeAnimations.degreeVec(-111.3108F, 1.573F, 10.9563F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.125F, KeyframeAnimations.degreeVec(-118.546F, 40.976F, -8.0152F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2083F, KeyframeAnimations.degreeVec(-175.3742F, 36.4231F, -7.1246F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.2472F, -1.936F, -15.1226F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(-1.1986F, -0.2534F, -4.845F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(1.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("rightItemLayer",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.5417F, KeyframeAnimations.degreeVec(-180.0F, 65.0F, -180.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.875F, KeyframeAnimations.degreeVec(-156.7295F, -17.7179F, -204.7606F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(1.1667F, KeyframeAnimations.degreeVec(51.459F, 81.4207F, -21.5982F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.build();
	public static final AnimationDefinition prepare = AnimationDefinition.Builder.withLength(0.7F)
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-2.628F, -17.6038F, 26.7266F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightItemLayer", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(228.5298F, -41.4904F, -219.8459F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightItemLayer", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-93.389F, 42.45F, -2.2889F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(-4.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition stepback = AnimationDefinition.Builder.withLength(0.6667F).looping()
			.addAnimation("whole",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-14.4905F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.6667F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 42.5F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-63.8014F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition ragepose = AnimationDefinition.Builder.withLength(0.0F).looping()
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition flew2 = AnimationDefinition.Builder.withLength(12.2917F)
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("robe3", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-73.8755F, -1.4811F, -1.4536F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 2.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-75.9933F, 27.0394F, 52.508F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.0F, -5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(59.8498F, -1.3318F, -77.8278F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition shoot = AnimationDefinition.Builder.withLength(0.625F).addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.0833F, KeyframeAnimations.degreeVec(0.0F, -12.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, -22.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.2083F, KeyframeAnimations.degreeVec(27.5F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(0.0F, 22.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.4583F, KeyframeAnimations.degreeVec(10.4166F, -4.5287F, -5.1846F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5417F, KeyframeAnimations.degreeVec(13.1949F, -17.8406F, -8.718F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(15.0F, 22.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("rightArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.125F, KeyframeAnimations.degreeVec(-72.2918F, -3.5558F, 79.6936F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.25F, KeyframeAnimations.degreeVec(64.0054F, -16.429F, 71.178F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.625F, KeyframeAnimations.degreeVec(-50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("leftArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.1667F, KeyframeAnimations.degreeVec(34.1037F, 1.516F, 0.2661F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-105.8963F, 1.516F, 0.2661F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.4167F, KeyframeAnimations.degreeVec(-64.8012F, 16.3038F, -51.6481F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(62.7162F, 2.354F, -81.4848F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.625F, KeyframeAnimations.degreeVec(34.1037F, 1.516F, 0.2661F), AnimationChannel.Interpolations.CATMULLROM)))
			.build();
	public static final AnimationDefinition quickat1 = AnimationDefinition.Builder.withLength(0.5F).looping()
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.0F, -22.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(1.3436F, 21.4058F, 7.1F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-31.3369F, 8.3901F, 88.5963F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(63.1096F, -0.284F, -51.7583F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition quickat2 = AnimationDefinition.Builder.withLength(0.5F).looping()
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 30.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(6.1191F, -21.6937F, -16.1735F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(43.3072F, -35.8301F, 32.1858F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 3.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-64.6933F, 3.9103F, -49.6454F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -3.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftItemLayer", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -47.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -7.0F), AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition quickat3 = AnimationDefinition.Builder.withLength(0.5F).looping()
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("robe3", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-59.4013F, -10.8035F, -6.3253F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -3.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightItemLayer", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-50.9759F, 2.4958F, 9.6139F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftItemLayer", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -40.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -7.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition quickat4 = AnimationDefinition.Builder.withLength(0.5F)
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-18.4474F, 60.8721F, -20.9002F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("robe3", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -40.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-73.8759F, -39.5678F, 48.8358F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-22.1848F, -8.2183F, -81.2659F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 3.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftItemLayer", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(70.0F, -45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -3.0F), AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition quickat5 = AnimationDefinition.Builder.withLength(0.5F).looping()
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(15.9153F, -19.291F, -5.3815F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.1855F, 10.2936F, 11.1664F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-123.2357F, -11.8491F, 51.5365F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightItemLayer", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -72.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(56.3072F, 3.3423F, -49.4686F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition death = AnimationDefinition.Builder.withLength(4.5833F)
			.addAnimation("whole",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(3.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(4.0833F, KeyframeAnimations.degreeVec(25.0F, 0.0F, -195.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("whole", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(3.2083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.375F, KeyframeAnimations.posVec(-7.95F, -3.91F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.4583F, KeyframeAnimations.posVec(-12.43F, -5.86F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.5417F, KeyframeAnimations.posVec(-15.11F, -10.15F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.625F, KeyframeAnimations.posVec(-19.55F, -14.43F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.75F, KeyframeAnimations.posVec(-21.58F, -20.86F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.875F, KeyframeAnimations.posVec(-15.36F, -29.29F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(4.0833F, KeyframeAnimations.posVec(-5.0F, -38.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5417F, KeyframeAnimations.degreeVec(32.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.625F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8333F, KeyframeAnimations.degreeVec(62.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.9583F, KeyframeAnimations.degreeVec(63.0591F, 5.7358F, 11.125F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(65.5591F, 5.7358F, 11.125F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.4583F, KeyframeAnimations.degreeVec(65.56F, 5.74F, 11.13F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5833F, KeyframeAnimations.posVec(0.0F, -3.0F, -3.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -6.0F, -3.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-40.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.2083F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.2917F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.5833F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8333F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2.125F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.9167F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.2917F, KeyframeAnimations.degreeVec(2.7212F, -45.8095F, 4.73F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightArm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.8464F, 2.6822F, 27.5198F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.5833F, KeyframeAnimations.degreeVec(-57.3993F, -4.7638F, 34.4946F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.7917F, KeyframeAnimations.degreeVec(-58.4934F, 10.0355F, 11.226F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(3.0417F, KeyframeAnimations.degreeVec(-58.49F, 10.04F, 11.23F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(3.2917F, KeyframeAnimations.degreeVec(-57.8036F, -0.6739F, 4.8741F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(3.625F, KeyframeAnimations.degreeVec(-57.8F, -0.67F, 4.87F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(3.7083F, KeyframeAnimations.degreeVec(-57.8F, -0.67F, 4.87F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(4.0F, KeyframeAnimations.degreeVec(17.0858F, 85.0746F, 113.2287F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftArm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -35.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5833F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-55.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.7917F, KeyframeAnimations.degreeVec(-62.6992F, 7.1959F, -49.4297F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.1667F, KeyframeAnimations.degreeVec(-62.6992F, 7.1959F, -49.4297F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(-44.0175F, -34.4278F, -73.6328F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.125F, KeyframeAnimations.degreeVec(11.4293F, 59.704F, -41.7754F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.1667F, KeyframeAnimations.degreeVec(21.4718F, 74.1514F, -30.951F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.625F, KeyframeAnimations.degreeVec(32.0514F, 5.6618F, 2.1999F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.7917F, KeyframeAnimations.degreeVec(87.0514F, 5.6618F, 2.1999F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(3.1667F, KeyframeAnimations.degreeVec(87.05F, 5.66F, 2.2F), AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(4.25F, KeyframeAnimations.degreeVec(72.8684F, -8.5302F, 26.3973F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5833F, KeyframeAnimations.posVec(0.0F, -1.0F, -4.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.7917F, KeyframeAnimations.posVec(0.0F, -10.0F, -4.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.1667F, KeyframeAnimations.posVec(0.0F, -10.0F, -4.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(4.25F, KeyframeAnimations.posVec(0.0F, -6.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(32.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.5F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(87.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(3.2917F, KeyframeAnimations.degreeVec(87.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.5417F, KeyframeAnimations.degreeVec(70.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftLeg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5417F, KeyframeAnimations.posVec(0.0F, -3.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -10.0F, -6.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.2917F, KeyframeAnimations.posVec(0.0F, -10.0F, -6.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(4.5417F, KeyframeAnimations.posVec(0.0F, -6.0F, -3.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
}