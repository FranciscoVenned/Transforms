package org.KosmicMedia.KosmicForms.Menus.ControlPanel;

import org.KosmicMedia.KosmicForms.FormManager;
import org.KosmicMedia.KosmicForms.MainClass;
import org.KosmicMedia.KosmicForms.Methods;
import org.KosmicMedia.KosmicForms.NBTEditor;
import org.KosmicMedia.KosmicForms.util.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Main {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);

   public static void openMenu(Player player) {
      Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.BLUE + "Panel de Control");
      ItemStack a = Methods.createItemStackWithGlow(Material.DIAMOND, 0, ChatColor.WHITE + "Give TP", "TP: 10000000000");
      ItemStack b = Methods.createItemStackWithGlow(Material.EYE_OF_ENDER, 0, ChatColor.WHITE + "Dar Habilidades", "");
      ItemStack c = Methods.createItemStackWithGlow(Material.EMERALD, 0, ChatColor.WHITE + "Dar Hakai", "");
      ItemStack d = Methods.createItemStackWithGlow(Material.COAL, 0, ChatColor.WHITE + "Establecer Maximo", "");
      ItemStack e = Methods.createItemStack(Material.NETHER_STAR, 0, ChatColor.WHITE + "Dar todas las transformaciones", (String)"");
      inv.setItem(12, b);
      inv.setItem(13, a);
      inv.setItem(14, c);
      inv.setItem(15, d);
      inv.setItem(11, e);
      player.openInventory(inv);
   }

   public static void Logic(Player player, int slot) {
      if (slot == 13) {
         NBTEditor.EditInt(player, "jrmcTpint", NBTEditor.GetInt(player, "jrmcTpint") + 1000000000);
         player.closeInventory();
      }

      if (slot == 12) {
         NBTEditor.EditString(player, "jrmcSSltX", "TR10");
         NBTEditor.EditString(player, "jrmcSSlts", "FZ9,JP9,DS9,FL9,EN9,OC9,KS9,MD9,KK9,GF9,KP9,KF9,KB9,DF9,KI9,UI9,");
         player.closeInventory();
      }

      if (slot == 14) {
         NBTEditor.EditString(player, "jrmcTech1", ChatColor.LIGHT_PURPLE + "Hakai" + ChatColor.RESET + ";0;Kosmic Assets;1;2;100;0;0;0;0;3;1;0;0;0;0;0;10;0;10,0,10,10,10,10,10");
         player.closeInventory();
      }

      if (slot == 15) {
         player.chat("/jrmca set all max");
      }

      if (slot == 11) {
         for(int i = 0; i < FormManager.getForms().size(); ++i) {
            PlayerManager.setFormLevel(player, i, 5);
         }
      }

   }
}
