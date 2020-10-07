package org.KosmicMedia.KosmicForms.Commands;

import org.KosmicMedia.KosmicForms.MainClass;
import org.KosmicMedia.KosmicForms.Methods;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class GetItem {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);

   public static void run(CommandSender sender, String[] args) {
      ItemStack potara = Methods.createItemStack(plugin.getConfig().getInt("Settings.PotaraItemID"), plugin.getConfig().getInt("Settings.PotaraItemDamage"), ChatColor.WHITE + "Potara Earring", "");
      if (args[1].equalsIgnoreCase("PotaraEarring")) {
         Inventory playerinv = ((Player)sender).getInventory();
         playerinv.addItem(new ItemStack[]{potara});
      }

      ItemStack timeskip = Methods.createItemStack(Material.getMaterial("CUSTOMNPCS_NPCORB"), 10, ChatColor.DARK_PURPLE + "Time Skip", (String)"");
      if (args[1].equalsIgnoreCase("TimeSkip")) {
         Inventory playerinv = ((Player)sender).getInventory();
         playerinv.addItem(new ItemStack[]{timeskip});
      }

   }
}
