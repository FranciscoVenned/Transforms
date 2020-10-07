package org.KosmicMedia.KosmicForms.Menus;

import org.KosmicMedia.KosmicForms.FormManager;
import org.KosmicMedia.KosmicForms.MainClass;
import org.KosmicMedia.KosmicForms.MainMenu;
import org.KosmicMedia.KosmicForms.Methods;
import org.KosmicMedia.KosmicForms.util.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class FormMenu {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);

   public static void openFormMenu(Player player, int i2) {
      Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, FormManager.getColor(i2) + "Forma " + FormManager.getFormattedName(i2));
      boolean RedOrBlue = false;

      for(int i = 0; i < 27; ++i) {
         ItemStack GlassThingy = new ItemStack(160, 1, (short)3);
         if (RedOrBlue) {
            GlassThingy.setDurability((short)11);
            RedOrBlue = false;
         } else {
            GlassThingy.setDurability((short)14);
            RedOrBlue = true;
         }

         ItemMeta GlassThingyMeta = GlassThingy.getItemMeta();
         GlassThingyMeta.setDisplayName("");
         GlassThingy.setItemMeta(GlassThingyMeta);
         inv.setItem(i, GlassThingy);
      }

      ItemStack formLvl1 = new ItemStack(FormManager.getUpgradeItemID(i2), 1, (short)FormManager.getUpgradeItemDamage(i2));
      ItemMeta formLvl1Meta = formLvl1.getItemMeta();
      formLvl1Meta.setDisplayName(FormManager.getColor(i2) + ChatColor.BOLD + FormManager.getFormattedName(i2) + " Form lvl 1");
      ArrayList list1 = new ArrayList();
      list1.add(FormManager.getColor(i2) + "Daño: " + FormManager.getDamageMultiplier(i2, 1) * 100.0D + "%");
      list1.add(FormManager.getColor(i2) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(i2, 1) + "%");
      list1.add(FormManager.getColor(i2) + "Defensa: " + FormManager.getDefenses(i2, 1) * 100.0D + "%");
      list1.add(FormManager.getColor(i2) + "Dodge Chance: " + FormManager.getDodgeChances(i2, 1) * 100.0D + "%");
      list1.add(FormManager.getColor(i2) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(i2, 1));
      CreateUpgradeItemLogic.CreateFirst(player, formLvl1Meta, i2, formLvl1);
      formLvl1.setItemMeta(formLvl1Meta);
      if (PlayerManager.getFormLevel(player, i2) == 1) {
         formLvl1 = Methods.addGlow(formLvl1);
      }

      ItemStack formLvl2 = new ItemStack(FormManager.getUpgradeItemID(i2), 1, (short)FormManager.getUpgradeItemDamage(i2));
      ItemMeta formLvl2Meta = formLvl2.getItemMeta();
      formLvl2Meta.setDisplayName(FormManager.getColor(i2) + ChatColor.BOLD + FormManager.getFormattedName(i2) + " Form lvl 2");
      ArrayList list2 = new ArrayList();
      list2.add(FormManager.getColor(i2) + "Daño: " + FormManager.getDamageMultiplier(i2, 2) * 100.0D + "%");
      list2.add(FormManager.getColor(i2) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(i2, 2) + "%");
      list2.add(FormManager.getColor(i2) + "Defensa: " + FormManager.getDefenses(i2, 2) * 100.0D + "%");
      list2.add(FormManager.getColor(i2) + "Dodge Chance: " + FormManager.getDodgeChances(i2, 2) * 100.0D + "%");
      list2.add(FormManager.getColor(i2) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(i2, 2));
      CreateUpgradeItemLogic.Create(player, formLvl2Meta, i2, 2, formLvl2);
      formLvl2.setItemMeta(formLvl2Meta);
      if (PlayerManager.getFormLevel(player, i2) == 2) {
         formLvl2 = Methods.addGlow(formLvl2);
      }

      ItemStack formLvl3 = new ItemStack(FormManager.getUpgradeItemID(i2), 1, (short)FormManager.getUpgradeItemDamage(i2));
      ItemMeta formLvl3Meta = formLvl3.getItemMeta();
      formLvl3Meta.setDisplayName(FormManager.getColor(i2) + ChatColor.BOLD + FormManager.getFormattedName(i2) + " Form lvl 3");
      ArrayList list3 = new ArrayList();
      list3.add(FormManager.getColor(i2) + "Daño: " + FormManager.getDamageMultiplier(i2, 3) * 100.0D + "%");
      list3.add(FormManager.getColor(i2) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(i2, 3) + "%");
      list3.add(FormManager.getColor(i2) + "Defensa: " + FormManager.getDefenses(i2, 3) * 100.0D + "%");
      list3.add(FormManager.getColor(i2) + "Dodge Chance: " + FormManager.getDodgeChances(i2, 3) * 100.0D + "%");
      list3.add(FormManager.getColor(i2) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(i2, 3));
      CreateUpgradeItemLogic.Create(player, formLvl3Meta, i2, 3, formLvl3);
      formLvl3.setItemMeta(formLvl3Meta);
      if (PlayerManager.getFormLevel(player, i2) == 3) {
         formLvl3 = Methods.addGlow(formLvl3);
      }

      ItemStack formLvl4 = new ItemStack(FormManager.getUpgradeItemID(i2), 1, (short)FormManager.getUpgradeItemDamage(i2));
      ItemMeta formLvl4Meta = formLvl4.getItemMeta();
      formLvl4Meta.setDisplayName(FormManager.getColor(i2) + ChatColor.BOLD + FormManager.getFormattedName(i2) + " Form lvl 4");
      ArrayList list4 = new ArrayList();
      list4.add(FormManager.getColor(i2) + "Daño: " + FormManager.getDamageMultiplier(i2, 4) * 100.0D + "%");
      list4.add(FormManager.getColor(i2) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(i2, 4) + "%");
      list4.add(FormManager.getColor(i2) + "Defensa: " + FormManager.getDefenses(i2, 4) * 100.0D + "%");
      list4.add(FormManager.getColor(i2) + "Dodge Chance: " + FormManager.getDodgeChances(i2, 4) * 100.0D + "%");
      list4.add(FormManager.getColor(i2) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(i2, 4));
      CreateUpgradeItemLogic.Create(player, formLvl4Meta, i2, 4, formLvl4);
      formLvl4.setItemMeta(formLvl4Meta);
      if (PlayerManager.getFormLevel(player, i2) == 4) {
         formLvl4 = Methods.addGlow(formLvl4);
      }

      ItemStack formLvl5 = new ItemStack(FormManager.getUpgradeItemID(i2), 1, (short)FormManager.getUpgradeItemDamage(i2));
      ItemMeta formLvl5Meta = formLvl5.getItemMeta();
      formLvl5Meta.setDisplayName(FormManager.getColor(i2) + ChatColor.BOLD + FormManager.getFormattedName(i2) + " Form lvl 5");
      ArrayList list5 = new ArrayList();
      list5.add(FormManager.getColor(i2) + "Daño: " + FormManager.getDamageMultiplier(i2, 5) * 100.0D + "%");
      list5.add(FormManager.getColor(i2) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(i2, 5) + "%");
      list5.add(FormManager.getColor(i2) + "Defensa: " + FormManager.getDefenses(i2, 5) * 100.0D + "%");
      list5.add(FormManager.getColor(i2) + "Dodge Chance: " + FormManager.getDodgeChances(i2, 5) * 100.0D + "%");
      list5.add(FormManager.getColor(i2) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(i2, 5));
      formLvl5Meta.setLore(list5);
      CreateUpgradeItemLogic.Create(player, formLvl5Meta, i2, 5, formLvl5);
      formLvl5.setItemMeta(formLvl5Meta);
      if (PlayerManager.getFormLevel(player, i2) == 5) {
         formLvl5 = Methods.addGlow(formLvl5);
      }

      ItemStack formTP = new ItemStack(FormManager.getMenuItemID(i2), 1, (short)FormManager.getMenuItemDamage(i2));
      ItemMeta formTPMeta = formTP.getItemMeta();
      if (PlayerManager.getFormTP(player, i2) == null) {
         formTPMeta.setDisplayName(FormManager.getColor(i2) + ChatColor.BOLD + FormManager.getFormattedName(i2) + " TP: " + ChatColor.RESET + FormManager.getColor(i2) + 0);
      } else {
         formTPMeta.setDisplayName(FormManager.getColor(i2) + ChatColor.BOLD + FormManager.getFormattedName(i2) + " TP: " + ChatColor.RESET + FormManager.getColor(i2) + PlayerManager.getFormTP(player, i2));
      }

      formTP.setItemMeta(formTPMeta);
      ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK, 1);
      ItemMeta exitMeta = exit.getItemMeta();
      exitMeta.setDisplayName(ChatColor.RED + "Salir del menu");
      exit.setItemMeta(exitMeta);
      ItemStack Blank = Methods.createItemStack(Material.STAINED_GLASS_PANE, 15, "", (String)"");
      ItemStack e = Methods.createItemStack(Material.STAINED_GLASS_PANE, 6, "", (String)"");
      inv.setItem(4, formTP);
      inv.setItem(11, formLvl1);
      inv.setItem(12, formLvl2);
      inv.setItem(13, formLvl3);
      inv.setItem(14, formLvl4);
      inv.setItem(15, formLvl5);
      inv.setItem(0, e);
      inv.setItem(1, Blank);
      inv.setItem(2, Blank);
      inv.setItem(3, Blank);
      inv.setItem(5, Blank);
      inv.setItem(6, Blank);
      inv.setItem(7, Blank);
      inv.setItem(8, e);
      inv.setItem(9, Blank);
      inv.setItem(10, Blank);
      inv.setItem(16, Blank);
      inv.setItem(17, Blank);
      inv.setItem(18, e);
      inv.setItem(19, Blank);
      inv.setItem(20, Blank);
      inv.setItem(21, Blank);
      inv.setItem(22, exit);
      inv.setItem(23, Blank);
      inv.setItem(24, Blank);
      inv.setItem(25, Blank);
      inv.setItem(26, e);
      player.openInventory(inv);
   }

   public static void Logic(Player player, int slot, int i2) {
      if (slot == 11) {
         UpdrageLogic.first(player, i2, 1);
      }

      if (slot == 12) {
         UpdrageLogic.normal(player, i2, 2);
      }

      if (slot == 13) {
         UpdrageLogic.normal(player, i2, 3);
      }

      if (slot == 14) {
         UpdrageLogic.normal(player, i2, 4);
      }

      if (slot == 15) {
         UpdrageLogic.normal(player, i2, 5);
      }

      if (slot == 22) {
         MainMenu.openMenu(player);
      }

   }
}
