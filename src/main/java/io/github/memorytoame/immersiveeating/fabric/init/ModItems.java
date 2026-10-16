package io.github.memorytoame.immersiveeating.fabric.init;

import io.github.memorytoame.immersiveeating.fabric.KaleidoscopeImmersiveEating;
import io.github.memorytoame.immersiveeating.fabric.common.content.item.Empty;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public final class ModItems {
   public static final ResourceKey<Item> EMPTY_KEY = ResourceKey.create(
           Registries.ITEM,
           Identifier.fromNamespaceAndPath(KaleidoscopeImmersiveEating.MOD_ID, "empty")
   );
   public static final Empty EMPTY = Registry.register(
      BuiltInRegistries.ITEM,
      EMPTY_KEY,
      new Empty(new Item.Properties().setId(EMPTY_KEY))
   );

   public static void init() {}

   private ModItems() {
   }
}

