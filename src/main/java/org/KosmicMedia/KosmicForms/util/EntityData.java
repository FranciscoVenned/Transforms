package org.KosmicMedia.KosmicForms.util;

import org.bukkit.Location;

public class EntityData {
   public Location firedfrom;
   public Integer range;
   public Double damage;

   public EntityData(Location loc, Integer range, Double damage) {
      this.firedfrom = loc;
      this.range = range;
      this.damage = damage;
   }

   public Location getFiredFrom() {
      return this.firedfrom;
   }

   public Integer getRange() {
      return this.range;
   }

   public Double getDamage() {
      return this.damage;
   }
}
