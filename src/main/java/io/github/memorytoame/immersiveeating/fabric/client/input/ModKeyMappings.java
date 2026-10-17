package io.github.memorytoame.immersiveeating.fabric.client.input;

import com.mojang.blaze3d.platform.InputConstants;
import io.github.memorytoame.immersiveeating.fabric.KaleidoscopeImmersiveEating;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;

@Environment(EnvType.CLIENT)
public final class ModKeyMappings {
   public static final KeyMapping ANIMATION = KeyMappingHelper.registerKeyMapping(new KeyMapping(
           "key.food.animation",
           InputConstants.KEY_U,
           new KeyMapping.Category(
                   KaleidoscopeImmersiveEating.id("key.categories.food")
           )
   ));

   private ModKeyMappings() {
   }

   public static void register() {}
}
