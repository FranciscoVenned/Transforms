package org.KosmicMedia.KosmicForms.forms;

import org.KosmicMedia.KosmicForms.MainClass;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Iterator;

public class Form {
   public HashMap levels = new HashMap();
   public String name;
   public String formatedName;
   public String color;
   public String particles;
   public String hairColors;
   public String hair;
   public String customHair;
   public String eye1;
   public String eye2;
   public String statuseffs;
   public String color1;
   public String color2;
   public int menuLocation;
   public int menuIDS;
   public int upgrateIDS;
   public int race;
   public int auraColor;
   public int alignment;
   public int state;
   public int kaio;
   public int level;
   public byte menuIDSDamage;
   public byte upgrateIDSDamage;
   public float speed;
   public boolean stackWithDBCFormss;
   public boolean stackWithDBCKaiokens;
   public boolean stackWithDBCMystics;
   public boolean stackWithDBCUltraInstincts;
   public boolean buyable;

   public Form(MainClass plugin, String path) {
      this.name = plugin.getForms().get(path + ".name");
      this.formatedName = plugin.getForms().get(path + ".FormattedName");
      this.color = plugin.getForms().get(path + ".Color");
      this.particles = plugin.getForms().get(path + ".Particle");
      this.speed = plugin.getForms().getFloat(path + ".Speed");
      this.race = plugin.getForms().getInt(path + ".Race");
      this.stackWithDBCFormss = plugin.getForms().getBoolean(path + ".StackWithDBCForms");
      this.stackWithDBCKaiokens = plugin.getForms().getBoolean(path + ".StackWithDBCKaioken");
      this.stackWithDBCMystics = plugin.getForms().getBoolean(path + ".StackWithDBCMystic");
      this.stackWithDBCUltraInstincts = plugin.getForms().getBoolean(path + ".StackWithDBCUltraInstinct");
      this.hairColors = plugin.getForms().get(path + ".HairColor");
      this.auraColor = plugin.getForms().getInt(path + ".AuraColor");
      this.buyable = plugin.getForms().getBoolean(path + ".BuyAble");
      this.alignment = plugin.getForms().getInt(path + ".Alignment");
      this.hair = plugin.getForms().get(path + ".Hair");
      this.customHair = plugin.getForms().get(path + ".CustomHair");
      this.eye1 = plugin.getForms().get(path + ".Eye1");
      this.eye2 = plugin.getForms().get(path + ".Eye2");
      this.statuseffs = plugin.getForms().get(path + ".StatusEffs");
      this.state = plugin.getForms().getInt(path + ".State");
      this.kaio = plugin.getForms().getInt(path + ".Kaio");
      this.level = plugin.getForms().getInt(path + ".Level");
      this.color1 = plugin.getForms().get(path + ".Color1");
      this.color2 = plugin.getForms().get(path + ".Color2");
      this.menuLocation = plugin.getForms().getInt(path + ".FormMenu.MenuLocation");
      this.menuIDS = plugin.getForms().getInt(path + ".FormMenu.MenuItemID");
      this.menuIDSDamage = Byte.parseByte(plugin.getForms().get(path + ".FormMenu.MenuItemDamage"));
      this.upgrateIDS = plugin.getForms().getInt(path + ".FormMenu.UpgradeItemID");
      this.upgrateIDSDamage = Byte.parseByte(plugin.getForms().get(path + ".FormMenu.UpgradeItemDamage"));
      ConfigurationSection lvl = plugin.getForms().getConfig().getConfigurationSection(path + ".levels");
      Iterator var4 = lvl.getKeys(false).iterator();

      while(var4.hasNext()) {
         String l = (String)var4.next();
         int leve = this.levels.size();
         this.levels.put(leve, new FormLevel(plugin, path + ".levels." + l));
      }

   }

   public HashMap getLevels() {
      return this.levels;
   }

   public String getName() {
      return this.name;
   }

   public String getFormatedName() {
      return this.formatedName;
   }

   public String getColor() {
      return this.color;
   }

   public String getParticles() {
      return this.particles;
   }

   public String getHairColors() {
      return this.hairColors;
   }

   public String getHair() {
      return this.hair;
   }

   public String getCustomHair() {
      return this.customHair;
   }

   public String getEye1() {
      return this.eye1;
   }

   public String getEye2() {
      return this.eye2;
   }

   public String getStatuseffs() {
      return this.statuseffs;
   }

   public String getColor1() {
      return this.color1;
   }

   public String getColor2() {
      return this.color2;
   }

   public int getMenuLocation() {
      return this.menuLocation;
   }

   public int getMenuIDS() {
      return this.menuIDS;
   }

   public int getUpgrateIDS() {
      return this.upgrateIDS;
   }

   public int getRace() {
      return this.race;
   }

   public int getAuraColor() {
      return this.auraColor;
   }

   public int getAlignment() {
      return this.alignment;
   }

   public int getState() {
      return this.state;
   }

   public int getKaio() {
      return this.kaio;
   }

   public int getLevel() {
      return this.level;
   }

   public byte getMenuIDSDamage() {
      return this.menuIDSDamage;
   }

   public byte getUpgrateIDSDamage() {
      return this.upgrateIDSDamage;
   }

   public float getSpeed() {
      return this.speed;
   }

   public boolean isStackWithDBCFormss() {
      return this.stackWithDBCFormss;
   }

   public boolean isStackWithDBCKaiokens() {
      return this.stackWithDBCKaiokens;
   }

   public boolean isStackWithDBCMystics() {
      return this.stackWithDBCMystics;
   }

   public boolean isStackWithDBCUltraInstincts() {
      return this.stackWithDBCUltraInstincts;
   }

   public boolean isBuyable() {
      return this.buyable;
   }
}
