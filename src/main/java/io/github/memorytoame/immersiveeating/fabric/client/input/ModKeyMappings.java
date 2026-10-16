package io.github.memorytoame.immersiveeating.fabric.client.input;

import io.github.memorytoame.immersiveeating.fabric.KaleidoscopeImmersiveEating;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;

@Environment(EnvType.CLIENT)
public final class ModKeyMappings {
   private static final KeyMapping ANIMATION_KEY = KeyMappingHelper.registerKeyMapping(new KeyMapping(
           "key.optional_pickup.pickup",
           21,
           new KeyMapping.Category(
                   KaleidoscopeImmersiveEating.id("key.categories.optional_pickup")
           )
   ));

   private ModKeyMappings() {
   }

   public static void register() {}
}

