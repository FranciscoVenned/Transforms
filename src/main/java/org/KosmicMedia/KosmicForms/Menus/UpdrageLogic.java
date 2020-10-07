package org.KosmicMedia.KosmicForms.Menus;

import org.KosmicMedia.KosmicForms.FormManager;
import org.KosmicMedia.KosmicForms.MainClass;
import org.KosmicMedia.KosmicForms.NBTEditor;
import org.KosmicMedia.KosmicForms.util.GetStats;
import org.KosmicMedia.KosmicForms.util.Messages;
import org.KosmicMedia.KosmicForms.util.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class UpdrageLogic {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);

   private UpdrageLogic(UpdrageLogic plugin) {
   }

   public static void normal(Player player, int formNumber, Integer level) {
      if (!FormManager.getBuyable(formNumber)) {
         Create(player, formNumber, level);
      } else {
         Create(player, formNumber, level);
      }

   }

   public static void first(Player player, int formNumber, Integer level) {
      if (!FormManager.getBuyable(formNumber)) {
         CreateFirst(player, formNumber, level);
      } else {
         CreateFirst(player, formNumber, level);
      }

   }

   private static void Create(Player player, int formNumber, Integer level) {
      int PrevLevel = level - 1;
      if (PlayerManager.getFormLevel(player, formNumber) == null) {
         player.sendMessage(Messages.prefix + ChatColor.RED + "Nivel " + PrevLevel + " no comprado.");
      } else if (PlayerManager.getFormLevel(player, formNumber) >= level) {
         player.sendMessage(Messages.prefix + ChatColor.RED + "Ya tienes esta forma");
      } else if (PlayerManager.getFormLevel(player, formNumber) < PrevLevel) {
         player.sendMessage(Messages.prefix + ChatColor.RED + "Nivel " + PrevLevel + " no comprado.");
      } else if (PlayerManager.getFormTP(player, formNumber) < FormManager.getCosts(formNumber, level)) {
         player.sendMessage(Messages.prefix + ChatColor.RED + "No tienes suficiente " + FormManager.getColor(formNumber) + FormManager.getFormattedName(formNumber) + " TP " + ChatColor.RESET + ChatColor.RED + "para comprar esta forma");
      } else {
         PlayerManager.setFormLevel(player, formNumber, level);
         int x = PlayerManager.getFormTP(player, formNumber);
         PlayerManager.setFormTP(player, formNumber, x - FormManager.getCosts(formNumber, level));
         NBTEditor.Edit(player, "jrmcIntI", GetStats.getMND(player) - 5);
         player.closeInventory();
         player.sendMessage(Messages.prefix + ChatColor.BLUE + "Usted ha comprado con éxito " + FormManager.getColor(formNumber) + FormManager.getFormattedName(formNumber) + " nivel " + level);
      }

   }

   private static void CreateFirst(Player player, int formNumber, int level) {
      int x;
      if (PlayerManager.getFormLevel(player, formNumber) == null) {
         if (PlayerManager.getFormTP(player, formNumber) == null) {
            player.sendMessage(Messages.prefix + ChatColor.RED + "No tienes suficiente " + FormManager.getColor(formNumber) + FormManager.getFormattedName(formNumber) + " TP " + ChatColor.RESET + ChatColor.RED + "para comprar esta forma");
         } else if (PlayerManager.getFormTP(player, formNumber) < FormManager.getCosts(formNumber, level)) {
            player.sendMessage(Messages.prefix + ChatColor.RED + "No tienes suficiente " + FormManager.getColor(formNumber) + FormManager.getFormattedName(formNumber) + " TP " + ChatColor.RESET + ChatColor.RED + "para comprar esta forma");
         } else {
            PlayerManager.setFormLevel(player, formNumber, level);
            x = PlayerManager.getFormTP(player, formNumber);
            PlayerManager.setFormTP(player, formNumber, x - FormManager.getCosts(formNumber, level));
            NBTEditor.Edit(player, "jrmcIntI", GetStats.getMND(player) - 5);
            player.closeInventory();
            player.sendMessage(Messages.prefix + ChatColor.BLUE + "Usted ha comprado con éxito " + FormManager.getColor(formNumber) + FormManager.getFormattedName(formNumber) + " nivel " + level);
         }
      } else if (PlayerManager.getFormLevel(player, formNumber) >= level) {
         player.sendMessage(Messages.prefix + ChatColor.RED + "Ya tienes esta forma");
      } else if (PlayerManager.getFormTP(player, formNumber) < FormManager.getCosts(formNumber, level)) {
         player.sendMessage(Messages.prefix + ChatColor.RED + "No tienes suficiente " + FormManager.getColor(formNumber) + FormManager.getFormattedName(formNumber) + " TP " + ChatColor.RESET + ChatColor.RED + "para comprar esta forma");
      } else {
         PlayerManager.setFormLevel(player, formNumber, level);
         x = PlayerManager.getFormTP(player, formNumber);
         PlayerManager.setFormTP(player, formNumber, x - FormManager.getCosts(formNumber, level));
         NBTEditor.Edit(player, "jrmcIntI", GetStats.getMND(player) - 5);
         player.closeInventory();
         player.sendMessage(Messages.prefix + ChatColor.BLUE + "Usted ha comprado con éxito " + FormManager.getColor(formNumber) + FormManager.getFormattedName(formNumber) + " nivel " + level);
      }

   }
}
