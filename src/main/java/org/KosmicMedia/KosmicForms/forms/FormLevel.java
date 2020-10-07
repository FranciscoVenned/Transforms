package org.KosmicMedia.KosmicForms.forms;

import org.KosmicMedia.KosmicForms.MainClass;

public class FormLevel {
   public int damage;
   public int cost;
   public int stamina;
   public int kitRegen;
   public double multiplier;
   public double regen;
   public double defense;
   public double dogmeChance;

   public FormLevel(MainClass plugin, String path) {
      this.damage = plugin.getForms().getInt(path + ".FormDamage");
      this.cost = plugin.getForms().getInt(path + ".FormCost");
      this.stamina = plugin.getForms().getInt(path + ".FormStaminaRegen");
      this.kitRegen = plugin.getForms().getInt(path + ".FormKiRegen");
      this.multiplier = plugin.getForms().getDouble(path + ".FormDamageMultiplier");
      this.regen = plugin.getForms().getDouble(path + ".FormRegen");
      this.defense = plugin.getForms().getDouble(path + ".FormDefense");
      this.dogmeChance = plugin.getForms().getDouble(path + ".FormDodgeChance");
   }

   public int getDamage() {
      return this.damage;
   }

   public int getCost() {
      return this.cost;
   }

   public int getStamina() {
      return this.stamina;
   }

   public int getKitRegen() {
      return this.kitRegen;
   }

   public double getMultiplier() {
      return this.multiplier;
   }

   public double getRegen() {
      return this.regen;
   }

   public double getDefense() {
      return this.defense;
   }

   public double getDogmeChance() {
      return this.dogmeChance;
   }
}
