package io.github.memorytoame.immersiveeating.fabric.client.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

@Environment(EnvType.CLIENT)
public final class ClientReloadEvents {
   private static boolean registered;
   private static boolean reloaded;

   private ClientReloadEvents() {
   }

   public static void register() {
      if (registered) {
         return;
      }
      registered = true;

      ClientPlayConnectionEvents.JOIN.register((_, _, _) -> {
         if (reloaded) {
            return;
         }
         reloaded = true;
      });
      ClientPlayConnectionEvents.DISCONNECT.register((_, _) -> {
         reloaded = false;
         FirstPersonModEvents.onClientLogout();
      });
   }
}
