package org.KosmicMedia.KosmicForms.util;

import org.KosmicMedia.KosmicForms.MainClass;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Fireworks {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);

   public static void explosion(Location loc, Color color, Color color2) {
      final org.bukkit.entity.Firework fw = (org.bukkit.entity.Firework)loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
      FireworkMeta meta = fw.getFireworkMeta();
      meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(color).withFade(color2).with(Type.BALL_LARGE).build());
      meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(color2).withFade(color).with(Type.BURST).build());
      meta.setPower(0);
      fw.setFireworkMeta(meta);
      (new BukkitRunnable() {
         public void run() {
            fw.detonate();
         }
      }).runTaskLater(plugin, 0L);
   }

   public static void explosionSpecial(Location loc, Color color, Color color2) {
      final org.bukkit.entity.Firework fw = (org.bukkit.entity.Firework)loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
      FireworkMeta meta = fw.getFireworkMeta();
      meta.addEffect(FireworkEffect.builder().trail(true).flicker(true).withColor(color).withFade(color2).with(Type.BALL_LARGE).build());
      meta.addEffect(FireworkEffect.builder().trail(true).flicker(true).withColor(color2).withFade(color).with(Type.BURST).build());
      meta.addEffect(FireworkEffect.builder().withColor(color).withFade(color2).with(Type.STAR).build());
      meta.setPower(0);
      fw.setFireworkMeta(meta);
      (new BukkitRunnable() {
         public void run() {
            fw.detonate();
         }
      }).runTaskLater(plugin, 0L);
   }
}
