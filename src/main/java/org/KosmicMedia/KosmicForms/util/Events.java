package org.KosmicMedia.KosmicForms.util;

import org.KosmicMedia.KosmicForms.MainClass;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.Iterator;

public class Events implements Listener {
   MainClass plugin;
   HashMap mobs = new HashMap();

   public Events(MainClass jp) {
      this.plugin = jp;
   }

   @EventHandler
   private void onKill(PlayerDeathEvent event) {
      this.registerMobs();
      if (event.getEntity() instanceof Player) {
         Player p = event.getEntity();
         DamageCause dc = p.getLastDamageCause().getCause();
         if (dc == DamageCause.VOID) {
            Iterator var5 = this.mobs.keySet().iterator();

            while(var5.hasNext()) {
               String str = (String)var5.next();
               if (str.equals(getLastDamager(p))) {
                  event.setDeathMessage(ChatColor.translateAlternateColorCodes('&', ((String)this.mobs.get(getLastDamager(p))).replace("{PLAYER}", p.getDisplayName())));
               }
            }
         }
      }

   }

   public static String getLastDamager(Player p) {
      Iterator var2 = p.getNearbyEntities(6.0D, 6.0D, 6.0D).iterator();

      Entity e;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         e = (Entity)var2.next();
      } while(!(e instanceof LivingEntity));

      return e.getType().getName();
   }

   public void registerMobs() {
      DbcNames dbcn = new DbcNames(this.plugin);
      FileConfiguration dm = dbcn.getData();
      Iterator var4 = dm.getStringList("DeathMessages").iterator();

      while(var4.hasNext()) {
         String str = (String)var4.next();
         String entity = str.split(" - ")[0];
         String message = str.split(" - ")[1];
         this.mobs.put(entity, message);
      }

   }
}
