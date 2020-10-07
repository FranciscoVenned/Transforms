package org.KosmicMedia.KosmicForms;

import org.KosmicMedia.KosmicForms.Commands.MainCommand;
import org.KosmicMedia.KosmicForms.Menus.FormMenu;
import org.KosmicMedia.KosmicForms.Menus.RaceMenu;
import org.KosmicMedia.KosmicForms.Menus.SelectFormMenu;
import org.KosmicMedia.KosmicForms.util.GetStats;
import org.KosmicMedia.KosmicForms.util.Messages;
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

public class MainMenu {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);
   public static int raceslot = 49;

   public static void openMenu(Player player) {
      boolean b = false;
      if (player.hasPermission(MainCommand.RacePerm)) {
         b = true;
      }

      String s = ChatColor.BLUE + "Menu de Transformaciones";
      int a = 6;
      Inventory inv = Bukkit.createInventory((InventoryHolder)null, a * 9, s);
      boolean RedOrBlue = false;

      int i;
      ItemStack formItem;
      ItemMeta formItemMeta;
      for(i = 0; i < 54; ++i) {
         formItem = new ItemStack(160, 1, (short)3);
         if (RedOrBlue) {
            formItem.setDurability((short)11);
            RedOrBlue = false;
         } else {
            formItem.setDurability((short)14);
            RedOrBlue = true;
         }

         formItemMeta = formItem.getItemMeta();
         formItemMeta.setDisplayName("");
         formItem.setItemMeta(formItemMeta);
         inv.setItem(i, formItem);
      }

      for(i = 0; i < FormManager.getForms().size(); ++i) {
         formItem = new ItemStack(FormManager.getMenuItemID(i), 1, (short)FormManager.getMenuItemDamage(i));
         formItemMeta = formItem.getItemMeta();
         formItemMeta.setDisplayName(FormManager.getColor(i) + FormManager.getFormattedName(i));
         ArrayList list = new ArrayList();
         int a1 = PlayerManager.getFormLevel(player, i);
         int help;
         if (a1 == 0) {
            help = 1;
         } else {
            help = a1;
         }

         int str = GetStats.getSTR(player);
         list.add(FormManager.getColor(i) + "Daño: " + FormManager.getDamageMultiplier(i, help) * 100.0D + "%");
         list.add(FormManager.getColor(i) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(i, help) + "%");
         list.add(FormManager.getColor(i) + "Defensa: " + FormManager.getDefenses(i, help) * 100.0D + "%");
         list.add(FormManager.getColor(i) + "Dodge Chance: " + FormManager.getDodgeChances(i, help) * 100.0D + "%");
         list.add(FormManager.getColor(i) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(i, help) + ".0%");
         list.add(FormManager.getColor(i) + "Regeneracion de Ki: " + FormManager.getKiRegen(i, help) + ".0%");
         list.add(FormManager.getColor(i) + "Salida de Daño: " + (double)str * 1.5D * 3.5D * (FormManager.getDamageMultiplier(i, help) - 1.0D) / 100.0D * (double)PlayerManager.getRelease(player));
         if (FormManager.getFormName(i).equals("SK") || FormManager.getFormName(i).equals("SaiyanDay") || FormManager.getFormName(i).equalsIgnoreCase("FSSJ")) {
            list.add(FormManager.getColor(i) + "Multi-Stage");
         }

         formItemMeta.setLore(list);
         formItem.setItemMeta(formItemMeta);
         inv.setItem(FormManager.getMenuLocation(i), formItem);
      }

      ItemStack f = Methods.createItemStack(Material.ENDER_PORTAL, 0, "", (String)"");
      formItem = new ItemStack(Material.BOOK, 1, (short)2);
      formItemMeta = formItem.getItemMeta();
      formItemMeta.setDisplayName(ChatColor.BLUE + "Seleccionar Forma");
      formItem.setItemMeta(formItemMeta);
      inv.setItem(4, formItem);
      if (b) {
      }

      player.openInventory(inv);
   }

   public static void Logic(Player player, int slot) {
      if (slot == 4) {
         SelectFormMenu.openSelectFormMenu(player);
      }

      boolean b = false;
      if (player.hasPermission(MainCommand.RacePerm)) {
         b = true;
      }

      if (b && slot == raceslot) {
         RaceMenu.openMenu(player);
      }

      for(int i = 0; i < FormManager.getForms().size(); ++i) {
         if (slot == FormManager.getMenuLocation(i)) {
            if (FormManager.getBuyable(i)) {
               openformmenus(player, i);
            } else {
               player.sendMessage(Messages.prefix + ChatColor.RED + "No se puede comprar esta forma.");
            }
            break;
         }
      }

   }

   public static void openformmenus(Player player, int formNumber) {
      try {
         Class.forName("me.dpohvar.powernbt.api.NBTManager");
         if (!FormManager.getFormName(formNumber).equalsIgnoreCase("MUI") && !FormManager.getFormName(formNumber).equalsIgnoreCase("UIOMEN")) {
            if (!FormManager.isFormRaceLimited(formNumber)) {
               FormMenu.openFormMenu(player, formNumber);
            } else if (PlayerManager.ValidateRace(player, FormManager.getRaces(formNumber))) {
               if (FormManager.getBuyable(formNumber)) {
                  FormMenu.openFormMenu(player, formNumber);
               } else {
                  player.sendMessage(Messages.prefix + ChatColor.RED + "No se puede comprar esta forma!");
               }
            } else {
               player.sendMessage(Messages.prefix + ChatColor.RED + "Debes estar en la carrera " + ChatColor.YELLOW + Messages.getDbcFormName(FormManager.getRaces(formNumber)) + ChatColor.RED + " para usar esta forma");
            }
         } else {
            FormMenu.openFormMenu(player, formNumber);
         }
      } catch (ClassNotFoundException var3) {
         FormMenu.openFormMenu(player, formNumber);
      }

   }
}
