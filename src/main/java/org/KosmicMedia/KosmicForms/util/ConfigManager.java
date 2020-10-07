package org.KosmicMedia.KosmicForms.util;

import org.KosmicMedia.KosmicForms.MainClass;
import org.bukkit.plugin.Plugin;

public class ConfigManager {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);

   public static void Create() {
      Create2();
      plugin.getConfig().options().copyDefaults(true);
      plugin.saveDefaultConfig();
      plugin.saveConfig();
   }

   public static void Create2() {
      CreateSettings();
      plugin.saveConfig();
   }

   public static void CreateSettings() {
      if (!plugin.getConfig().isSet("Settings.AddForms")) {
         plugin.getConfig().set("Settings.AddForms", true);
      }

      if (!plugin.getConfig().isSet("Settings.Particles")) {
         plugin.getConfig().set("Settings.Particles", true);
      }

      if (!plugin.getConfig().isSet("Settings.Regen")) {
         plugin.getConfig().set("Settings.Regen", true);
      }

      if (!plugin.getConfig().isSet("Settings.Defense")) {
         plugin.getConfig().set("Settings.Defense", 2);
      }

      if (!plugin.getConfig().isSet("Settings.KiRegen")) {
         plugin.getConfig().set("Settings.KiRegen", 2);
      }

      if (!plugin.getConfig().isSet("Settings.Damage")) {
         plugin.getConfig().set("Settings.Damage", true);
      }

      if (!plugin.getConfig().isSet("Settings.FormsStar")) {
         plugin.getConfig().set("Settings.FormsStar", true);
      }

      if (!plugin.getConfig().isSet("Settings.PotaraItemID")) {
         plugin.getConfig().set("Settings.PotaraItemID", 370);
      }

      if (!plugin.getConfig().isSet("Settings.PotaraItemDamage")) {
         plugin.getConfig().set("Settings.PotaraItemDamage", 0);
      }

      if (!plugin.getConfig().isSet("Settings.XpToTp")) {
         plugin.getConfig().set("Settings.XpToTp", 10000);
      }

   }
}
