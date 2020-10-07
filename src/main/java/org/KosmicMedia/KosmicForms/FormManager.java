package org.KosmicMedia.KosmicForms;

import org.KosmicMedia.KosmicForms.forms.Form;
import org.KosmicMedia.KosmicForms.forms.FormLevel;
import org.apache.commons.lang.math.IntRange;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Iterator;

public class FormManager {
   public MainClass plugin;
   public static HashMap forms = new HashMap();

   public FormManager(MainClass plugin) {
      this.plugin = plugin;
      ConfigurationSection fr = plugin.getForms().getConfig().getConfigurationSection("FormOthers");
      Iterator var3 = fr.getKeys(false).iterator();

      while(var3.hasNext()) {
         String f = (String)var3.next();
         forms.put(forms.size(), new Form(plugin, "FormOthers." + f));
      }

   }

   public static boolean isFormRaceLimited(int formNumber) {
      boolean isIt = false;
      IntRange range = new IntRange(0, 4);
      if (range.containsInteger(getRaces(formNumber))) {
         isIt = true;
      }

      return isIt;
   }

   public static boolean isFormHairColored(int formNumber) {
      boolean isIt = false;
      if (getHairColor(formNumber) != "-1") {
         isIt = true;
      }

      return isIt;
   }

   public static String getFormName(int formNumber) {
      return ((Form)forms.get(formNumber)).getName();
   }

   public static String getFormattedName(int formNumber) {
      return ((Form)forms.get(formNumber)).getFormatedName();
   }

   public static String getColor(int formNumber) {
      return ((Form)forms.get(formNumber)).getColor();
   }

   public static int getDamage(int formNumber, int level) {
      return level == 0 ? ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level)).getDamage() : ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level - 1)).getDamage();
   }

   public static Double getDamageMultiplier(int formNumber, int level) {
      return level == 0 ? ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level)).getMultiplier() : ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level - 1)).getMultiplier();
   }

   public static double getRegen(int formNumber, int level) {
      return level == 0 ? ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level)).getRegen() : ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level - 1)).getDefense();
   }

   public static Double getDefenses(int formNumber, int level) {
      return level == 0 ? ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level)).getDefense() : ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level - 1)).getDefense();
   }

   public static int getCosts(int formNumber, int level) {
      return level == 0 ? ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level)).getCost() : ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level - 1)).getCost();
   }

   public static int getStaminaRegen(int formNumber, int level) {
      return level == 0 ? ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level)).getStamina() : ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level - 1)).getStamina();
   }

   public static Double getDodgeChances(int formNumber, int level) {
      return level == 0 ? ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level)).getDogmeChance() : ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level - 1)).getDogmeChance();
   }

   public static int getKiRegen(int formNumber, int level) {
      return level == 0 ? ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level)).getKitRegen() : ((FormLevel)((Form)forms.get(formNumber)).getLevels().get(level - 1)).getKitRegen();
   }

   public static Integer getMenuLocation(int formNumber) {
      return ((Form)forms.get(formNumber)).getMenuLocation();
   }

   public static Integer getMenuItemID(int formNumber) {
      return ((Form)forms.get(formNumber)).getMenuIDS();
   }

   public static Byte getMenuItemDamage(int formNumber) {
      return ((Form)forms.get(formNumber)).getMenuIDSDamage();
   }

   public static Integer getUpgradeItemID(int formNumber) {
      return ((Form)forms.get(formNumber)).getUpgrateIDS();
   }

   public static Byte getUpgradeItemDamage(int formNumber) {
      return ((Form)forms.get(formNumber)).getUpgrateIDSDamage();
   }

   public static Float getSpeed(int formNumber) {
      return ((Form)forms.get(formNumber)).getSpeed();
   }

   public static boolean hasSpeed(int formNumber) {
      return getSpeed(formNumber) != -1.0F;
   }

   public static Integer getRaces(int formNumber) {
      return ((Form)forms.get(formNumber)).getRace();
   }

   public static Boolean getStackWithDBCForms(int formNumber) {
      return ((Form)forms.get(formNumber)).isStackWithDBCFormss();
   }

   public static Boolean getStackWithDBCKaioken(int formNumber) {
      return ((Form)forms.get(formNumber)).isStackWithDBCKaiokens();
   }

   public static Boolean getStackWithDBCMystic(int formNumber) {
      return ((Form)forms.get(formNumber)).isStackWithDBCMystics();
   }

   public static Boolean getStackWithDBCUltraInstinct(int formNumber) {
      return ((Form)forms.get(formNumber)).isStackWithDBCUltraInstincts();
   }

   public static String getHairColor(int formNumber) {
      return ((Form)forms.get(formNumber)).getHairColors();
   }

   public static Integer getAuraColor(int formNumber) {
      return ((Form)forms.get(formNumber)).getAuraColor();
   }

   public static String getParticle(int formNumber) {
      return ((Form)forms.get(formNumber)).getParticles();
   }

   public static Boolean getBuyable(int formNumber) {
      return ((Form)forms.get(formNumber)).isBuyable();
   }

   public static Integer getAlignment(int formNumber) {
      return ((Form)forms.get(formNumber)).getAlignment();
   }

   public static String getHair(int formNumber) {
      return ((Form)forms.get(formNumber)).getHair();
   }

   public static String getCustomHair(int formNumber) {
      return ((Form)forms.get(formNumber)).getCustomHair();
   }

   public static String getEye1(int formNumber) {
      return ((Form)forms.get(formNumber)).getEye1();
   }

   public static String getEye2(int formNumber) {
      return ((Form)forms.get(formNumber)).getEye2();
   }

   public static String getStatusEffs(int formNumber) {
      return ((Form)forms.get(formNumber)).getStatuseffs();
   }

   public static Integer getState(int formNumber) {
      return ((Form)forms.get(formNumber)).getState();
   }

   public static Integer getKaio(int formNumber) {
      return ((Form)forms.get(formNumber)).getKaio();
   }

   public static Integer getLevel(int formNumber) {
      return ((Form)forms.get(formNumber)).getLevel();
   }

   public static String getColor1(int formNumber) {
      return ((Form)forms.get(formNumber)).getColor1();
   }

   public static String getColor2(int formNumber) {
      return ((Form)forms.get(formNumber)).getColor2();
   }

   public static HashMap getForms() {
      return forms;
   }
}
