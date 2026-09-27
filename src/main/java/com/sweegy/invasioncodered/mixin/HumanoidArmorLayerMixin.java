package com.sweegy.invasioncodered.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sweegy.invasioncodered.client.renderer.CustomRenderTypes;
import com.sweegy.invasioncodered.interfaces.ICustomGlint;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> {

    @Inject(
            method = "renderArmorPiece(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;ILnet/minecraft/client/model/HumanoidModel;)V",
            at = @At("HEAD")
    )
    private void captureArmorStack(PoseStack pPoseStack, MultiBufferSource pBuffer, T pLivingEntity, EquipmentSlot pSlot, int pPackedLight, A pModel, CallbackInfo ci) {
        ItemStack stack = pLivingEntity.getItemBySlot(pSlot);
        ICustomGlint.CURRENT_ITEM.set(stack);
    }

    @Inject(
            method = "renderArmorPiece(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;ILnet/minecraft/client/model/HumanoidModel;)V",
            at = @At("RETURN")
    )
    private void clearArmorStack(PoseStack pPoseStack, MultiBufferSource pBuffer, T pLivingEntity, EquipmentSlot pSlot, int pPackedLight, A pModel, CallbackInfo ci) {
        ICustomGlint.CURRENT_ITEM.remove();
    }

    @Redirect(
            method = "renderArmorPiece(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;ILnet/minecraft/client/model/HumanoidModel;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;renderGlint(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/Model;)V",
                    remap = false
            )
    )
    private void redirectArmorGlint(HumanoidArmorLayer<?, ?, ?> instance, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, Model pModel) {
        ItemStack stack = ICustomGlint.CURRENT_ITEM.get();

        if (stack != null && !stack.isEmpty() && stack.getItem() instanceof ICustomGlint customGlintItem && customGlintItem.hasCustomGlint(stack)) {
            ResourceLocation texture = customGlintItem.getCustomGlintTexture(stack);

            if (texture != null) {
                RenderType customGlintRenderType = CustomRenderTypes.getCustomArmorEntityGlint(texture);
                pModel.renderToBuffer(pPoseStack, pBuffer.getBuffer(customGlintRenderType), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

                return;
            }
        }

        pModel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.armorEntityGlint()), pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}