package io.github.memorytoame.immersiveeating.fabric.client.animation;

import net.minecraft.resources.Identifier;

/** Centralized GeckoLib 5 asset paths for the food item models. */
public final class GeckoLibAssetPaths {
   public static final String NAMESPACE = "food";

   private GeckoLibAssetPaths() {
   }

   /** Base id passed to {@code DefaultedItemGeoModel}. */
   public static Identifier itemBase(String itemId) {
      return Identifier.fromNamespaceAndPath(NAMESPACE, itemId);
   }

   /** Cache id used by GeckoLib 5 after stripping its resource directory and suffix. */
   public static Identifier itemCacheId(String itemId) {
      return Identifier.fromNamespaceAndPath(NAMESPACE, "item/" + itemId);
   }

   /** Raw animation resource path used when checking for a named animation. */
   public static Identifier animationResource(String itemId) {
      return Identifier.fromNamespaceAndPath(
         NAMESPACE,
         "geckolib/animations/item/" + itemId + ".animation.json"
      );
   }
}
