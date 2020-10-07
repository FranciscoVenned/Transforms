package org.KosmicMedia.KosmicForms.Menus;

import org.KosmicMedia.KosmicForms.FormManager;
import org.KosmicMedia.KosmicForms.Methods;
import org.KosmicMedia.KosmicForms.util.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

class CreateUpgradeItemLogic {
   public static void Create(Player player, ItemMeta Meta, int formNumber, Integer level, ItemStack Item) {
      int PrevLevel = level - 1;
      List Lore;
      if (PlayerManager.getFormLevel(player, formNumber) == null) {
         Lore = Arrays.asList(ChatColor.RED + "Nivel 1 no ocomprado", FormManager.getColor(formNumber) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Regeneracion de Vida: " + FormManager.getRegen(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Defensa: " + FormManager.getDefenses(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Dodge Chance: " + FormManager.getDodgeChances(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(formNumber, level));
         Meta.setLore(Lore);
      } else if (PlayerManager.getFormLevel(player, formNumber) == PrevLevel) {
         Lore = Arrays.asList(FormManager.getColor(formNumber) + "Click para comprar", FormManager.getColor(formNumber) + "Costo: " + FormManager.getCosts(formNumber, level), FormManager.getColor(formNumber) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Regeneracion de Vida: " + FormManager.getRegen(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Defensa: " + FormManager.getDefenses(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Dodge Chance: " + FormManager.getDodgeChances(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(formNumber, level));
         Meta.setLore(Lore);
      } else if (PlayerManager.getFormLevel(player, formNumber) > PrevLevel) {
         Lore = Arrays.asList(FormManager.getColor(formNumber) + "Ya comprado", FormManager.getColor(formNumber) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Regeneracion de Vida: " + FormManager.getRegen(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Defensa: " + FormManager.getDefenses(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Dodge Chance: " + FormManager.getDodgeChances(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(formNumber, level));
         Methods.addGlow(Item);
         Meta.setLore(Lore);
      } else if (PlayerManager.getFormLevel(player, formNumber) < PrevLevel) {
         Lore = Arrays.asList(ChatColor.RED + "Nivel " + PrevLevel + " no comprado", FormManager.getColor(formNumber) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Regeneracion de Vida: " + FormManager.getRegen(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Defensa: " + FormManager.getDefenses(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Dodge Chance: " + FormManager.getDodgeChances(formNumber, level) * 100.0D + "%", FormManager.getColor(formNumber) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(formNumber, level));
         Meta.setLore(Lore);
      }

   }

   public static void CreateFirst(Player player, ItemMeta Meta, int formNumber, ItemStack Item) {
      List Lore;
      if (PlayerManager.getFormLevel(player, formNumber) == null) {
         Lore = Arrays.asList(FormManager.getColor(formNumber) + "Click para comprar", FormManager.getColor(formNumber) + "Costo: " + FormManager.getCosts(formNumber, 1), FormManager.getColor(formNumber) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(formNumber, 1) * 100.0D + "%", FormManager.getColor(formNumber) + "Regeneracion de Vida: " + FormManager.getRegen(formNumber, 1) * 100.0D + "%", FormManager.getColor(formNumber) + "Defensa: " + FormManager.getDefenses(formNumber, 1) * 100.0D + "%", FormManager.getColor(formNumber) + "Dodge Chance: " + FormManager.getDodgeChances(formNumber, 1) * 100.0D + "%", FormManager.getColor(formNumber) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(formNumber, 1));
         Meta.setLore(Lore);
      } else if (PlayerManager.getFormLevel(player, formNumber) > 0) {
         Lore = Arrays.asList(FormManager.getColor(formNumber) + "Ya comprado", FormManager.getColor(formNumber) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(formNumber, 1) * 100.0D + "%", FormManager.getColor(formNumber) + "Regeneracion de Vida: " + FormManager.getRegen(formNumber, 1) * 100.0D + "%", FormManager.getColor(formNumber) + "Defensa: " + FormManager.getDefenses(formNumber, 1) * 100.0D + "%", FormManager.getColor(formNumber) + "Dodge Chance: " + FormManager.getDodgeChances(formNumber, 1) * 100.0D + "%", FormManager.getColor(formNumber) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(formNumber, 1));
         Methods.addGlow(Item);
         Meta.setLore(Lore);
      } else if (PlayerManager.getFormLevel(player, formNumber) < 1) {
         Lore = Arrays.asList(FormManager.getColor(formNumber) + "Click para comprar", FormManager.getColor(formNumber) + "Costo: " + FormManager.getCosts(formNumber, 1), FormManager.getColor(formNumber) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(formNumber, 1) * 100.0D + "%", FormManager.getColor(formNumber) + "Regeneracion de Vida: " + FormManager.getRegen(formNumber, 1) * 100.0D + "%", FormManager.getColor(formNumber) + "Defensa: " + FormManager.getDefenses(formNumber, 1) * 100.0D + "%", FormManager.getColor(formNumber) + "Dodge Chance: " + FormManager.getDodgeChances(formNumber, 1) * 100.0D + "%", FormManager.getColor(formNumber) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(formNumber, 1));
         Meta.setLore(Lore);
      }

   }
}
