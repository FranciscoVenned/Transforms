package org.KosmicMedia.KosmicForms.util;

import org.KosmicMedia.KosmicForms.MainClass;
import org.KosmicMedia.KosmicForms.NBTEditor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GetStats {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);

   private static int getCurStam(Player player) {
      int out = NBTEditor.GetInt(player, "jrmcStamina");
      return out;
   }

   public static int getMaxStam(Player player) {
      int Class = NBTEditor.GetInt(player, "jrmcClass");
      int help = 0;
      if (Class == 0) {
         help = 4;
      } else if (Class == 1) {
         help = 3;
      } else if (Class == 2) {
         help = 4;
      }

      int out = NBTEditor.GetInt(player, "jrmcCnsI") * help + 7500;
      return out;
   }

   public static int getCurEnrgy(Player player) {
      int out = NBTEditor.GetInt(player, "jrmcEnrgy");
      return out;
   }

   public static int getMaxEnrgy(Player player) {
      int Class = NBTEditor.GetInt(player, "jrmcClass");
      int help = 0;
      if (Class == 0) {
         help = 4;
      } else if (Class == 1) {
         help = 2;
      } else if (Class == 2) {
         help = 2;
      }

      int out = NBTEditor.GetInt(player, "jrmcCncI") * help;
      return out;
   }

   private static int getCurHP(Player player) {
      int out = NBTEditor.GetInt(player, "jrmcBdy");
      return out;
   }

   private static int getMaxHP(Player player) {
      int Class = NBTEditor.GetInt(player, "jrmcClass");
      int help = 0;
      if (Class == 0) {
         help = 20;
      } else if (Class == 1) {
         help = 18;
      } else if (Class == 2) {
         help = 22;
      }

      int out = NBTEditor.GetInt(player, "jrmcCnsI") * help;
      return out;
   }

   private static int getCurRelease(Player player) {
      int out = NBTEditor.GetInt(player, "jrmcRelease");
      return out;
   }

   private static int getMaxRelease(Player player) {
      String s = NBTEditor.GetString(player, "jrmcSSlts");
      int help = 50;
      if (s.contains("OC0")) {
         help = 55;
      }

      if (s.contains("OC1")) {
         help = 60;
      }

      if (s.contains("OC2")) {
         help = 65;
      }

      if (s.contains("OC3")) {
         help = 70;
      }

      if (s.contains("OC4")) {
         help = 75;
      }

      if (s.contains("OC5")) {
         help = 80;
      }

      if (s.contains("OC6")) {
         help = 85;
      }

      if (s.contains("OC7")) {
         help = 90;
      }

      if (s.contains("OC8")) {
         help = 95;
      }

      if (s.contains("OC9")) {
         help = 100;
      }

      return help;
   }

   public static int getSTR(Player player) {
      return NBTEditor.GetInt(player, "jrmcStrI");
   }

   public static int getDEX(Player player) {
      return NBTEditor.GetInt(player, "jrmcDexI");
   }

   public static int getCON(Player player) {
      return NBTEditor.GetInt(player, "jrmcCnsI");
   }

   public static int getWIL(Player player) {
      return NBTEditor.GetInt(player, "jrmcWilI");
   }

   public static int getMND(Player player) {
      return NBTEditor.GetInt(player, "jrmcIntI");
   }

   public static int getSPI(Player player) {
      return NBTEditor.GetInt(player, "jrmcCncI");
   }

   public static int getAlign(Player player) {
      return NBTEditor.GetInt(player, "jrmcAlign");
   }

   public static int getLevel(Player player) {
      int str = getSTR(player);
      int dex = getDEX(player);
      int con = getCON(player);
      int wil = getWIL(player);
      int mnd = getMND(player);
      int spi = getSPI(player);
      return (str + dex + con + wil + mnd + spi) / 5;
   }
}
