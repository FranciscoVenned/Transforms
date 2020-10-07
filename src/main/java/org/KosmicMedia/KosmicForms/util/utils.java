package org.KosmicMedia.KosmicForms.util;

import org.bukkit.Color;

public class utils {
   public static boolean inMagicMode = false;
   public static String fusePartner = "";
   public static boolean fused = false;
   public static String fuseSlave = "";

   public static String getClass(byte type) {
      switch(type) {
      case 0:
         return "Martial Artist";
      case 1:
         return "Spiritualist";
      case 2:
         return "Warrior";
      default:
         return "Unknown";
      }
   }

   private static String getAlignment(byte align) {
      if (align >= 45 && align <= 55) {
         return "Neutral";
      } else if (align < 45) {
         return "Evil";
      } else {
         return align > 55 ? "Good" : "Unknown";
      }
   }

   public static Color isColor(String a) {
      Color Color1 = Color.WHITE;
      if (a.equalsIgnoreCase("AQUA")) {
         Color1 = Color.AQUA;
      }

      if (a.equalsIgnoreCase("BLACK")) {
         Color1 = Color.BLACK;
      }

      if (a.equalsIgnoreCase("BLUE")) {
         Color1 = Color.BLUE;
      }

      if (a.equalsIgnoreCase("FUCHSIA")) {
         Color1 = Color.FUCHSIA;
      }

      if (a.equalsIgnoreCase("GRAY")) {
         Color1 = Color.GRAY;
      }

      if (a.equalsIgnoreCase("BLACK")) {
         Color1 = Color.BLACK;
      }

      if (a.equalsIgnoreCase("GREEN")) {
         Color1 = Color.GREEN;
      }

      if (a.equalsIgnoreCase("LIME")) {
         Color1 = Color.LIME;
      }

      if (a.equalsIgnoreCase("MAROON")) {
         Color1 = Color.MAROON;
      }

      if (a.equalsIgnoreCase("NAVY")) {
         Color1 = Color.NAVY;
      }

      if (a.equalsIgnoreCase("OLIVE")) {
         Color1 = Color.OLIVE;
      }

      if (a.equalsIgnoreCase("ORANGE")) {
         Color1 = Color.ORANGE;
      }

      if (a.equalsIgnoreCase("PURPLE")) {
         Color1 = Color.PURPLE;
      }

      if (a.equalsIgnoreCase("RED")) {
         Color1 = Color.RED;
      }

      if (a.equalsIgnoreCase("SILVER")) {
         Color1 = Color.SILVER;
      }

      if (a.equalsIgnoreCase("TEAL")) {
         Color1 = Color.TEAL;
      }

      if (a.equalsIgnoreCase("WHITE")) {
         Color1 = Color.WHITE;
      }

      if (a.equalsIgnoreCase("YELLOW")) {
         Color1 = Color.YELLOW;
      }

      return Color1;
   }
}
