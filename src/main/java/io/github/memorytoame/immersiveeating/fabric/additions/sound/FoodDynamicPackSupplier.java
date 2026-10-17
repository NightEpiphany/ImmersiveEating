package io.github.memorytoame.immersiveeating.fabric.additions.sound;

import java.util.stream.Stream;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackMetadataResources;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.Pack.Metadata;
import org.jspecify.annotations.NonNull;

public final class FoodDynamicPackSupplier implements Pack.ResourcesSupplier {
   @Override
   public @NonNull PackMetadataResources openMetadata(@NonNull PackLocationInfo location) {
      return new FoodDynamicPack();
   }

   @Override
   public @NonNull Stream<PackResources> openResources(
      @NonNull PackLocationInfo location,
      @NonNull Metadata metadata
   ) {
      return Stream.of(new FoodDynamicPack());
   }
}
