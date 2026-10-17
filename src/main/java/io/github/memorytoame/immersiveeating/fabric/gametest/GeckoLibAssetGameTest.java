package io.github.memorytoame.immersiveeating.fabric.gametest;

import io.github.memorytoame.immersiveeating.fabric.client.animation.GeckoLibAssetPaths;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;

/** Verifies that a packaged GeckoLib 5 item has the resources used by the renderer. */
public final class GeckoLibAssetGameTest {
   @GameTest
   public void dongpoPorkResourcesUseGeckoLib5Layout(GameTestHelper helper) {
      helper.assertTrue(
         "food:item/dongpo_pork".equals(GeckoLibAssetPaths.itemCacheId("dongpo_pork").toString()),
         "The model cache id must use the GeckoLib 5 item prefix"
      );
      helper.assertTrue(
         hasResource("assets/food/geckolib/models/item/dongpo_pork.geo.json"),
         "The dongpo_pork geo model must be under geckolib/models/item"
      );
      helper.assertTrue(
         hasResource("assets/food/geckolib/animations/item/dongpo_pork.animation.json"),
         "The dongpo_pork animation must be under geckolib/animations/item"
      );
      helper.succeed();
   }

   private static boolean hasResource(String path) {
      return GeckoLibAssetGameTest.class.getClassLoader().getResource(path) != null;
   }
}
