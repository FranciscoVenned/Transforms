package org.KosmicMedia.KosmicForms.util;

import org.KosmicMedia.KosmicForms.FormManager;
import org.KosmicMedia.KosmicForms.MainClass;
import org.KosmicMedia.KosmicForms.NBTEditor;
import org.KosmicMedia.KosmicForms.Particles;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerManager {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);

   public static void Transform(Player player, int formNumber) {
      if (isPlayerFormed(player)) {
         Deform(player);
      }

      Particles.summonTransformParticle(player, FormManager.getParticle(formNumber));
      setDefaultTailColor(player, getTailColor(player));
      setTailColor(player, FormManager.getHairColor(formNumber));
      setDefaultHairColor(player, getHairColor(player));
      setHairColor(player, FormManager.getHairColor(formNumber));
      setDefaultAuraColor(player, getAuraColor(player));
      setAuraColor(player, FormManager.getAuraColor(formNumber));
      setDefaultHair(player, getHair(player));
      setHair(player, FormManager.getHair(formNumber));
      setDefaultCustomHair(player, getCustomHair(player));
      if (!FormManager.getCustomHair(formNumber).contains(Integer.toString(-1))) {
         setCustomHair(player, FormManager.getCustomHair(formNumber));
      }

      setDefaultEye1(player, getEye1(player));
      setEye1(player, FormManager.getEye1(formNumber));
      setDefaultEye2(player, getEye2(player));
      setEye2(player, FormManager.getEye2(formNumber));
      setDefaultStatusEffs(player, getStatusEffs(player));
      if (FormManager.getState(formNumber) != -1) {
         setDefaultState(player, getState(player));
      }

      if (FormManager.getKaio(formNumber) != -1) {
         setDefaultState2(player, getState2(player));
      }

      if (isPlayerFormed(player)) {
         for(int i = 0; i < FormManager.getForms().size(); ++i) {
            if (getActiveForm(player) == i && FormManager.hasSpeed(i)) {
               player.setWalkSpeed(FormManager.getSpeed(i));
            }
         }
      }

      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", FormManager.getFormName(formNumber));
      Yamls.PlayerData.savePlayerData();
   }

   public static void Deform(Player player) {
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", (Object)null);
      Yamls.PlayerData.savePlayerData();
      setTailColor(player, getDefaultTailColor(player));
      setHairColor(player, getDefaultHairColor(player));
      setAuraColor(player, getDefaultAuraColor(player));
      setHair(player, getDefaultHair(player));
      setCustomHair(player, getDefaultCustomHair(player));
      setEye1(player, getDefaultEye1(player));
      setEye2(player, getDefaultEye2(player));
      player.setWalkSpeed(0.2F);
      String status = getDefaultStatusEffs(player);
      if (!status.equals("-1")) {
         NBTEditor.EditString(player, "jrmcStatusEff", getDefaultStatusEffs(player));
      }

      int state = getDefaultState(player);
      int state2 = getDefaultState(player);
      if (state > -1) {
         NBTEditor.EditState(player, getDefaultState(player));
      }

      if (state2 > -1) {
         NBTEditor.EditInt(player, "jrmcState2", getDefaultState2(player));
      }

   }

   public static boolean ValidateRace(Player player, int formRace) {
      boolean isIt = false;
      int race = NBTEditor.GetInt(player, "jrmcRace");
      if (race == formRace) {
         isIt = true;
      } else {
         if (race == 2 && formRace == 1) {
            isIt = true;
         }

         if (race == 1 && formRace == 2) {
            isIt = true;
         }
      }

      if (-1 == formRace) {
         isIt = true;
      }

      return isIt;
   }

   public static boolean ValidateAlignment(Player player, int formAlignment) {
      boolean isIt = false;
      int alignment = NBTEditor.GetInt(player, "jrmcAlign");
      int WhatIsIt = -1;
      if (alignment < 33) {
         WhatIsIt = 0;
      } else if (alignment < 67) {
         WhatIsIt = 1;
      } else if (alignment < 101) {
         WhatIsIt = 2;
      }

      if (WhatIsIt == -1) {
         isIt = true;
      }

      if (formAlignment == -1) {
         isIt = true;
      }

      if (WhatIsIt == formAlignment) {
         isIt = true;
      }

      return isIt;
   }

   public static Integer getActiveForm(Player player) {
      Integer x = null;
      boolean isFormGood = false;
      if (Yamls.PlayerData.getPlayerData().isSet("PlayerData." + player.getUniqueId() + ".ActiveForm")) {
         for(int i = 0; i < FormManager.getForms().size(); ++i) {
            if (Yamls.PlayerData.getPlayerData().getString("PlayerData." + player.getUniqueId() + ".ActiveForm").equalsIgnoreCase(FormManager.getFormName(i))) {
               x = i;
               isFormGood = true;
            }
         }

         if (!isFormGood) {
            Deform(player);
         }
      }

      return x;
   }

   public static Boolean isPlayerFormed(Player player) {
      Boolean x = false;
      if (getActiveForm(player) != null) {
         x = true;
      }

      return x;
   }

   public static Integer getFormLevel(Player player, int formNumber) {
      Integer x = 0;
      if (hasForm(player, formNumber)) {
         x = Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + "." + FormManager.getFormName(formNumber) + "Form." + FormManager.getFormName(formNumber) + "Level");
      }

      return x;
   }

   public static void setFormLevel(Player player, int formNumber, int level) {
      if (level > 5) {
         level = 5;
      }

      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + "." + FormManager.getFormName(formNumber) + "Form." + FormManager.getFormName(formNumber) + "Level", level);
      Yamls.PlayerData.savePlayerData();
   }

   public static Boolean hasForm(Player player, int formNumber) {
      Boolean x = false;
      if (Yamls.PlayerData.getPlayerData().isSet("PlayerData." + player.getUniqueId() + "." + FormManager.getFormName(formNumber) + "Form." + FormManager.getFormName(formNumber) + "Level")) {
         x = true;
      }

      return x;
   }

   public static Integer getFormTP(Player player, int formNumber) {
      Integer x = 0;
      if (Yamls.PlayerData.getPlayerData().isSet("PlayerData." + player.getUniqueId() + "." + FormManager.getFormName(formNumber) + "Form." + FormManager.getFormName(formNumber) + "TP")) {
         x = Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + "." + FormManager.getFormName(formNumber) + "Form." + FormManager.getFormName(formNumber) + "TP");
      }

      return x;
   }

   public static void setFormTP(Player player, int formNumber, int amount) {
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + "." + FormManager.getFormName(formNumber) + "Form." + FormManager.getFormName(formNumber) + "TP", amount);
      Yamls.PlayerData.savePlayerData();
   }

   public static String getActiveFormName(Player player) {
      return FormManager.getFormName(getActiveForm(player));
   }

   public static Integer getPlayerDBCForm(Player player) {
      int form = NBTEditor.GetInt(player, "jrmcState");
      return form;
   }

   public static Integer getPlayerRace(Player player) {
      int race = NBTEditor.GetInt(player, "jrmcRace");
      return race;
   }

   public static String getHairColor(Player player) {
      String color;
      for(color = NBTEditor.GetString(player, "jrmcDNS"); color.length() != 12; color = color.substring(0, color.length() - 1)) {
      }

      for(int i = 0; i < 7; ++i) {
         color = color.substring(1);
      }

      return color;
   }

   public static String getTailColor(Player player) {
      String color = NBTEditor.GetString(player, "jrmcDNS");
      if (NBTEditor.GetString(player, "jrmcDNS").length() > 19) {
         int i;
         for(i = 0; i < 26; ++i) {
            color = color.substring(0, color.length() - 1);
         }

         for(i = 0; i < 21; ++i) {
            color = color.substring(1);
         }
      } else {
         while(color.length() != 5) {
            color = color.substring(1);
         }
      }

      return color;
   }

   public static void setDefaultHairColor(Player player, String color) {
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultHairColor", color);
      Yamls.PlayerData.savePlayerData();
   }

   public static void setDefaultTailColor(Player player, String color) {
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultTailColor", color);
      Yamls.PlayerData.savePlayerData();
   }

   public static String getDefaultHairColor(Player player) {
      String x = "-1";
      if (Yamls.PlayerData.getPlayerData().isSet("PlayerData." + player.getUniqueId() + ".DefaultHairColor")) {
         x = Yamls.PlayerData.getPlayerData().getString("PlayerData." + player.getUniqueId() + ".DefaultHairColor");
      }

      return x;
   }

   public static String getDefaultTailColor(Player player) {
      String x = "-1";
      if (Yamls.PlayerData.getPlayerData().isSet("PlayerData." + player.getUniqueId() + ".DefaultTailColor")) {
         x = Yamls.PlayerData.getPlayerData().getString("PlayerData." + player.getUniqueId() + ".DefaultTailColor");
      }

      return x;
   }

   public static void setHairColor(Player player, String color) {
      if (color != "-1") {
         String after = NBTEditor.GetString(player, "jrmcDNS");

         String before;
         for(before = NBTEditor.GetString(player, "jrmcDNS"); before.length() != 7; before = before.substring(0, before.length() - 1)) {
         }

         for(int i = 0; i < 12; ++i) {
            after = after.substring(1);
         }

         if (color.length() == 5) {
            NBTEditor.EditString(player, "jrmcDNS", before + color + after);
         }
      }

   }

   public static void setTailColor(Player player, String color) {
      if (color != "-1" && color.length() == 5) {
         String tailbefore = NBTEditor.GetString(player, "jrmcDNS");
         String tailafter = NBTEditor.GetString(player, "jrmcDNS");
         int i;
         if (NBTEditor.GetString(player, "jrmcDNS").length() > 19) {
            for(i = 0; i < 31; ++i) {
               tailbefore = tailbefore.substring(0, tailbefore.length() - 1);
            }

            for(i = 0; i < 26; ++i) {
               tailafter = tailafter.substring(1);
            }
         } else {
            for(i = 0; i < 5; ++i) {
               tailbefore = tailbefore.substring(0, tailbefore.length() - 1);
            }

            tailafter = "";
         }

         NBTEditor.EditString(player, "jrmcDNS", tailbefore + color + tailafter);
      }

   }

   public static Integer getAuraColor(Player player) {
      int color = NBTEditor.GetInt(player, "jrmcAuraColor");
      return color;
   }

   public static Integer getDefaultAuraColor(Player player) {
      int x = -1;
      if (Yamls.PlayerData.getPlayerData().isSet("PlayerData." + player.getUniqueId() + ".DefaultAuraColor")) {
         x = Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".DefaultAuraColor");
      }

      return x;
   }

   public static void setDefaultAuraColor(Player player, int color) {
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultAuraColor", color);
      Yamls.PlayerData.savePlayerData();
   }

   public static void setAuraColor(Player player, int color) {
      if (color != -1 && String.valueOf(color).length() > 0) {
         NBTEditor.EditInt(player, "jrmcAuraColor", color);
      }

   }

   public static Integer getHealth(Player player) {
      int health = NBTEditor.GetInt(player, "jrmcBdy");
      return health;
   }

   public static Double getMaxHealth(Player player) {
      double maxHealth = (double)(NBTEditor.GetInt(player, "jrmcCnsI") * 22);
      return maxHealth;
   }

   public static void setHealth(Player player, int health) {
      NBTEditor.EditInt(player, "jrmcBdy", health);
   }

   public static void setHealthCapped(Player player, int health) {
      if ((double)health > getMaxHealth(player)) {
         NBTEditor.EditDouble(player, "jrmcBdy", getMaxHealth(player));
      } else {
         NBTEditor.EditInt(player, "jrmcBdy", health);
      }

   }

   public static int getSTR(Player player) {
      int x = NBTEditor.GetInt(player, "jrmcStrI");
      return x;
   }

   public static int getDEX(Player player) {
      int x = NBTEditor.GetInt(player, "jrmcDexI");
      return x;
   }

   public static int getCON(Player player) {
      int x = NBTEditor.GetInt(player, "jrmcCnsI");
      return x;
   }

   public static int getWIL(Player player) {
      int x = NBTEditor.GetInt(player, "jrmcWilI");
      return x;
   }

   public static int getMND(Player player) {
      int x = NBTEditor.GetInt(player, "jrmcIntI");
      return x;
   }

   public static int getSPI(Player player) {
      int x = NBTEditor.GetInt(player, "jrmcCncI");
      return x;
   }

   public static void setSTR(Player player, int amount) {
      if (amount > 100000) {
         NBTEditor.EditInt(player, "jrmcStrI", 100000);
      } else {
         NBTEditor.EditInt(player, "jrmcStrI", amount);
      }

   }

   public static void setDEX(Player player, int amount) {
      if (amount > 100000) {
         NBTEditor.EditInt(player, "jrmcDexI", 100000);
      } else {
         NBTEditor.EditInt(player, "jrmcDexI", amount);
      }

   }

   public static void setCON(Player player, int amount) {
      if (amount > 100000) {
         NBTEditor.EditInt(player, "jrmcCnsI", 100000);
      } else {
         NBTEditor.EditInt(player, "jrmcCnsI", amount);
      }

   }

   public static void setWIL(Player player, int amount) {
      if (amount > 100000) {
         NBTEditor.EditInt(player, "jrmcWilI", 100000);
      } else {
         NBTEditor.EditInt(player, "jrmcWilI", amount);
      }

   }

   public static void setMND(Player player, int amount) {
      if (amount > 100000) {
         NBTEditor.EditInt(player, "jrmcIntI", 100000);
      } else {
         NBTEditor.EditInt(player, "jrmcIntI", amount);
      }

   }

   public static void setSPI(Player player, int amount) {
      if (amount > 100000) {
         NBTEditor.EditInt(player, "jrmcCncI", 100000);
      } else {
         NBTEditor.EditInt(player, "jrmcCncI", amount);
      }

   }

   public static Integer getRelease(Player player) {
      int release = NBTEditor.GetInt(player, "jrmcRelease");
      return release;
   }

   public static Integer getStamina(Player player) {
      int stamina = NBTEditor.GetInt(player, "jrmcStamina");
      return stamina;
   }

   public static String getSelectedForm(Player player) {
      String x = null;
      if (Yamls.PlayerData.getPlayerData().isSet("PlayerData." + player.getUniqueId() + ".SelectedForm")) {
         x = Yamls.PlayerData.getPlayerData().getString("PlayerData." + player.getUniqueId() + ".SelectedForm");
      }

      return x;
   }

   public static void setSelectedForm(Player player, int formNumber) {
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SelectedForm", FormManager.getFormName(formNumber));
      Yamls.PlayerData.savePlayerData();
   }

   public static void removeSelectedForm(Player player) {
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SelectedForm", (Object)null);
      Yamls.PlayerData.savePlayerData();
   }

   public static String getHair(Player player) {
      String hair;
      for(hair = NBTEditor.GetString(player, "jrmcDNS"); hair.length() != 5; hair = hair.substring(0, hair.length() - 1)) {
      }

      for(int i = 0; i < 4; ++i) {
         hair = hair.substring(1);
      }

      return hair;
   }

   public static void setHair(Player player, String hair) {
      String after = NBTEditor.GetString(player, "jrmcDNS");

      String before;
      for(before = NBTEditor.GetString(player, "jrmcDNS"); before.length() != 4; before = before.substring(0, before.length() - 1)) {
      }

      for(int i = 0; i < 5; ++i) {
         after = after.substring(1);
      }

      if (hair.length() == 1) {
         NBTEditor.EditString(player, "jrmcDNS", before + hair + after);
      }

   }

   public static String getDefaultHair(Player player) {
      return Yamls.PlayerData.getPlayerData().isSet("PlayerData." + player.getUniqueId() + ".DefaultHair") ? Yamls.PlayerData.getPlayerData().getString("PlayerData." + player.getUniqueId() + ".DefaultHair") : "-1";
   }

   public static void setDefaultHair(Player player, String hair) {
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultHair", hair);
      Yamls.PlayerData.savePlayerData();
   }

   public static String getCustomHair(Player player) {
      String hair = NBTEditor.GetString(player, "jrmcDNSH");
      return hair;
   }

   public static void setCustomHair(Player player, String hair) {
      NBTEditor.EditString(player, "jrmcDNSH", hair);
   }

   public static String getDefaultCustomHair(Player player) {
      return Yamls.PlayerData.getPlayerData().isSet("PlayerData." + player.getUniqueId() + ".DefaultCustomHair") ? Yamls.PlayerData.getPlayerData().getString("PlayerData." + player.getUniqueId() + ".DefaultCustomHair") : "-1";
   }

   public static void setDefaultCustomHair(Player player, String customHair) {
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultCustomHair", customHair);
      Yamls.PlayerData.savePlayerData();
   }

   public static String getEye1(Player player) {
      String hair = NBTEditor.GetString(player, "jrmcDNS");
      if (hair.length() != 52) {
         return null;
      } else {
         while(hair.length() != 5) {
            hair = hair.substring(1);
         }

         return hair;
      }
   }

   public static void setEye1(Player player, String eyeColor) {
      String before = NBTEditor.GetString(player, "jrmcDNS");

      for(int i = 0; i < 5; ++i) {
         before = before.substring(0, before.length() - 1);
      }

      if (eyeColor.length() == 5) {
         NBTEditor.EditString(player, "jrmcDNS", before + eyeColor);
      }

   }

   public static String getDefaultEye1(Player player) {
      return Yamls.PlayerData.getPlayerData().isSet("PlayerData." + player.getUniqueId() + ".DefaultEye1") ? Yamls.PlayerData.getPlayerData().getString("PlayerData." + player.getUniqueId() + ".DefaultEye1") : "-1";
   }

   public static void setDefaultEye1(Player player, String hair) {
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultEye1", hair);
      Yamls.PlayerData.savePlayerData();
   }

   public static String getEye2(Player player) {
      String hair = NBTEditor.GetString(player, "jrmcDNS");
      if (hair.length() != 52) {
         return null;
      } else {
         for(int i = 0; i < 5; ++i) {
            hair = hair.substring(0, hair.length() - 1);
         }

         while(hair.length() != 5) {
            hair = hair.substring(1);
         }

         return hair;
      }
   }

   public static void setEye2(Player player, String hair) {
      String after = getEye1(player);
      String before = NBTEditor.GetString(player, "jrmcDNS");
      if (before.length() == 52) {
         for(int i = 0; i < 10; ++i) {
            before = before.substring(0, before.length() - 1);
         }

         if (hair.length() == 5) {
            NBTEditor.EditString(player, "jrmcDNS", before + hair + after);
         }
      }

   }

   public static String getDefaultEye2(Player player) {
      return Yamls.PlayerData.getPlayerData().isSet("PlayerData." + player.getUniqueId() + ".DefaultEye2") ? Yamls.PlayerData.getPlayerData().getString("PlayerData." + player.getUniqueId() + ".DefaultEye2") : "-1";
   }

   public static void setDefaultEye2(Player player, String hair) {
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultEye2", hair);
      Yamls.PlayerData.savePlayerData();
   }

   public static String getStatusEffs(Player player) {
      String status = NBTEditor.GetString(player, "jrmcStatusEff");
      return status;
   }

   public static Integer getState(Player player) {
      return NBTEditor.GetInt(player, "jrmcState");
   }

   public static Integer getState2(Player player) {
      return NBTEditor.GetInt(player, "jrmcState2");
   }

   public static void setDefaultStatusEffs(Player player, String status) {
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultStatus", status);
      Yamls.PlayerData.savePlayerData();
   }

   public static void setDefaultState(Player player, Integer state) {
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState", state);
      Yamls.PlayerData.savePlayerData();
   }

   public static String getDefaultStatusEffs(Player player) {
      return Yamls.PlayerData.getPlayerData().isSet("PlayerData." + player.getUniqueId() + ".DefaultStatus") ? Yamls.PlayerData.getPlayerData().getString("PlayerData." + player.getUniqueId() + ".DefaultStatus") : "-1";
   }

   public static Integer getDefaultState(Player player) {
      return Yamls.PlayerData.getPlayerData().isSet("PlayerData." + player.getUniqueId() + ".DefaultState") ? Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".DefaultState") : -1;
   }

   public static void setDefaultState2(Player player, Integer state) {
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState2", state);
      Yamls.PlayerData.savePlayerData();
   }

   public static Integer getDefaultState2(Player player) {
      return Yamls.PlayerData.getPlayerData().isSet("PlayerData." + player.getUniqueId() + ".DefaultState2") ? Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".DefaultState2") : -1;
   }
}
