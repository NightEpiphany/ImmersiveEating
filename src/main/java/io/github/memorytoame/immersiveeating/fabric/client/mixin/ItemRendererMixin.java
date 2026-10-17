package io.github.memorytoame.immersiveeating.fabric.client.mixin;

import com.geckolib.GeckoLibConstants;
import com.geckolib.animatable.GeoItem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.memorytoame.immersiveeating.fabric.client.animation.ClientAnimationManager;
import io.github.memorytoame.immersiveeating.fabric.client.render.CustomRenderer;
import io.github.memorytoame.immersiveeating.fabric.client.render.TransformationMatrixProperties;
import io.github.memorytoame.immersiveeating.fabric.common.content.definition.FoodDefinitionManager;
import io.github.memorytoame.immersiveeating.fabric.init.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FirstPersonHandsAndItemsRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.FirstPersonHandsAndItemsRenderState;
import net.minecraft.client.renderer.state.level.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Submits GeckoLib's animated food model in the 26.3 first-person renderer. */
@Environment(EnvType.CLIENT)
@Mixin(FirstPersonHandsAndItemsRenderer.class)
public abstract class ItemRendererMixin {
   @Inject(method = "submitArmWithItem", at = @At("HEAD"), cancellable = true)
   private void food$renderCustomMainHand(
      PlayerRenderState playerState,
      FirstPersonHandsAndItemsRenderState state,
      float partialTicks,
      float xRot,
      InteractionHand hand,
      float attack,
      ItemStack stack,
      float inverseArmHeight,
      PoseStack poseStack,
      SubmitNodeCollector renderTasks,
      int lightCoords,
      CallbackInfo ci
   ) {
      if (hand != InteractionHand.MAIN_HAND || !FoodDefinitionManager.init_ItemList.contains(stack.getItem())) {
         return;
      }

      HumanoidArm mainArm = playerState.avatarRenderState == null
         ? HumanoidArm.RIGHT
         : playerState.avatarRenderState.mainArm;
      ItemDisplayContext displayContext = mainArm == HumanoidArm.LEFT
         ? ItemDisplayContext.FIRST_PERSON_LEFT_HAND
         : ItemDisplayContext.FIRST_PERSON_RIGHT_HAND;
      Identifier id = BuiltInRegistries.ITEM.getKey(stack.getItem());
      poseStack.pushPose();
      if (mainArm == HumanoidArm.LEFT) {
         poseStack.translate(
            -1.0F - TransformationMatrixProperties.NORMAL.getTranslateX(),
            TransformationMatrixProperties.NORMAL.getTranslateY(),
            TransformationMatrixProperties.NORMAL.getTranslateZ()
         );
      } else {
         poseStack.translate(
            TransformationMatrixProperties.NORMAL.getTranslateX(),
            TransformationMatrixProperties.NORMAL.getTranslateY(),
            TransformationMatrixProperties.NORMAL.getTranslateZ()
         );
      }

      CustomRenderer customRenderer = CustomRenderer.rendererMap.computeIfAbsent(id.getPath(), key -> new CustomRenderer(id.toString()));
      ItemStack proxy = new ItemStack(ModItems.EMPTY);
      Long stackId = stack.get(GeckoLibConstants.STACK_ANIMATABLE_ID_COMPONENT.get());
      long instanceId = stackId == null ? Long.MAX_VALUE : stackId;
      if (instanceId == Long.MAX_VALUE && playerState.avatarRenderState != null) {
         instanceId = ClientAnimationManager.getOrCreateControllerId(Minecraft.getInstance().player);
      }

      if (instanceId != Long.MAX_VALUE) {
         proxy.set(GeckoLibConstants.STACK_ANIMATABLE_ID_COMPONENT.get(), instanceId);
      }

      customRenderer.renderByItem(
         proxy,
         displayContext,
         poseStack,
         renderTasks,
         lightCoords,
         OverlayTexture.NO_OVERLAY,
         playerState
      );
      poseStack.popPose();
      ci.cancel();
   }

}
