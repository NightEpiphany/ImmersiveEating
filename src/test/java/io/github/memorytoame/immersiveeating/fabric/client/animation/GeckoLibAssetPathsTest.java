package io.github.memorytoame.immersiveeating.fabric.client.animation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class GeckoLibAssetPathsTest {
   @Test
   void buildsGeckoLib5ItemCacheId() {
      assertEquals("food:item/dongpo_pork", GeckoLibAssetPaths.itemCacheId("dongpo_pork").toString());
   }

   @Test
   void buildsGeckoLib5AnimationResourcePath() {
      assertEquals(
         "food:geckolib/animations/item/dongpo_pork.animation.json",
         GeckoLibAssetPaths.animationResource("dongpo_pork").toString()
      );
   }

   @Test
   void goldenSaladAnimatedBonesHaveAnInitialHoldKeyframe() throws Exception {
      String resourcePath = "assets/food/geckolib/animations/item/golden_salad.animation.json";
      try (var stream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
         assertNotNull(stream, "golden_salad animation resource must be packaged");
         try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            JsonObject bones = JsonParser.parseReader(reader)
               .getAsJsonObject()
               .getAsJsonObject("animations")
               .getAsJsonObject("eat")
               .getAsJsonObject("bones");
            for (String boneName : new String[] {"bone2", "bone", "bone3", "bone4", "bone6"}) {
               JsonObject bone = bones.getAsJsonObject(boneName);
               for (String transform : new String[] {"rotation", "position"}) {
                  var vector = bone.getAsJsonObject(transform).getAsJsonObject("0.0").getAsJsonArray("vector");
                  for (int axis = 0; axis < 3; axis++) {
                     assertEquals(0.0F, vector.get(axis).getAsFloat(), boneName + " " + transform + " initial value");
                  }
               }
            }
         }
      }
   }
}
