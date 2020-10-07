package org.KosmicMedia.KosmicForms.Menus;

import org.KosmicMedia.KosmicForms.FormManager;
import org.KosmicMedia.KosmicForms.MainClass;
import org.KosmicMedia.KosmicForms.MainMenu;
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

import java.util.Arrays;
import java.util.List;

public class SelectFormMenu {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);
   public static ItemStack e;

   public static void openSelectFormMenu(Player player) {
      Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, ChatColor.BLUE + "Seleccionar Transformacion");
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
         formItem = new ItemStack(Material.getMaterial(FormManager.getMenuItemID(i)), 1, (short)FormManager.getMenuItemDamage(i));
         formItemMeta = formItem.getItemMeta();
         formItemMeta.setDisplayName(FormManager.getColor(i) + FormManager.getFormattedName(i));
         List formItemLore;
         if (PlayerManager.getFormLevel(player, i) == null) {
            formItemLore = Arrays.asList(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "No comprado", FormManager.getColor(i) + "Costo: " + FormManager.getCosts(i, PlayerManager.getFormLevel(player, i)), FormManager.getColor(i) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(i, PlayerManager.getFormLevel(player, i)) * 100.0D + "%", FormManager.getColor(i) + "Regeneracion de Vida: " + FormManager.getRegen(i, PlayerManager.getFormLevel(player, i)) * 100.0D + "%", FormManager.getColor(i) + "Defensa: " + FormManager.getDefenses(i, PlayerManager.getFormLevel(player, i)) * 100.0D + "%", FormManager.getColor(i) + "Dodge Chance: " + FormManager.getDodgeChances(i, PlayerManager.getFormLevel(player, i)) * 100.0D + "%", FormManager.getColor(i) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(i, PlayerManager.getFormLevel(player, i)) + ".0%", FormManager.getColor(i) + "Ki Regen: " + FormManager.getKiRegen(i, PlayerManager.getFormLevel(player, i)) + ".0%");
            formItemMeta.setLore(formItemLore);
         } else if (PlayerManager.getFormLevel(player, i) > 0) {
            formItemLore = Arrays.asList(FormManager.getColor(i) + "Nivel Comprado: " + PlayerManager.getFormLevel(player, i), FormManager.getColor(i) + "Costo: " + FormManager.getCosts(i, PlayerManager.getFormLevel(player, i)), FormManager.getColor(i) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(i, PlayerManager.getFormLevel(player, i)) * 100.0D + "%", FormManager.getColor(i) + "Regeneracion de Vida: " + FormManager.getRegen(i, PlayerManager.getFormLevel(player, i)) * 100.0D + "%", FormManager.getColor(i) + "Defensa: " + FormManager.getDefenses(i, PlayerManager.getFormLevel(player, i)) * 100.0D + "%", FormManager.getColor(i) + "Dodge Chance: " + FormManager.getDodgeChances(i, PlayerManager.getFormLevel(player, i)) * 100.0D + "%", FormManager.getColor(i) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(i, PlayerManager.getFormLevel(player, i)) + ".0%", FormManager.getColor(i) + "Ki Regen: " + FormManager.getKiRegen(i, PlayerManager.getFormLevel(player, i)) + ".0%");
            formItemMeta.setLore(formItemLore);
         } else if (PlayerManager.getFormLevel(player, i) == 0) {
            formItemLore = Arrays.asList(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "No comprado", FormManager.getColor(i) + "Costo: " + FormManager.getCosts(i, PlayerManager.getFormLevel(player, i)), FormManager.getColor(i) + "Multiplicador de Daño: " + FormManager.getDamageMultiplier(i, PlayerManager.getFormLevel(player, i)) * 100.0D + "%", FormManager.getColor(i) + "Regeneracion de Vida: " + FormManager.getRegen(i, PlayerManager.getFormLevel(player, i)) * 100.0D + "%", FormManager.getColor(i) + "Defensa: " + FormManager.getDefenses(i, PlayerManager.getFormLevel(player, i)) * 100.0D + "%", FormManager.getColor(i) + "Dodge Chance: " + FormManager.getDodgeChances(i, PlayerManager.getFormLevel(player, i)) * 100.0D + "%", FormManager.getColor(i) + "Regeneracion de Estamina: " + FormManager.getStaminaRegen(i, PlayerManager.getFormLevel(player, i)) + ".0%", FormManager.getColor(i) + "Ki Regen: " + FormManager.getKiRegen(i, PlayerManager.getFormLevel(player, i)) + ".0%");
            formItemMeta.setLore(formItemLore);
         }

         formItem.setItemMeta(formItemMeta);
         inv.setItem(FormManager.getMenuLocation(i), formItem);
      }

      ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK, 1, (short)1);
      ItemMeta exitMeta = exit.getItemMeta();
      exitMeta.setDisplayName(ChatColor.RED + "Salir del menu");
      exit.setItemMeta(exitMeta);
      ItemStack deselect = new ItemStack(Material.REDSTONE, 1);
      ItemMeta deselectMeta = exit.getItemMeta();
      deselectMeta.setDisplayName(ChatColor.RED + "Deseleccionar Forma");
      deselect.setItemMeta(deselectMeta);
      inv.setItem(49, exit);
      inv.setItem(4, deselect);
      inv.setItem(0, e);
      inv.setItem(8, e);
      inv.setItem(49, exit);
      inv.setItem(inv.getSize() - 9, e);
      inv.setItem(inv.getSize() - 1, e);
      player.openInventory(inv);
   }

   public static void Logic(Player player, int slot) {
      int i;
      for(i = 0; i < FormManager.getForms().size(); ++i) {
         try {
            Class.forName("me.dpohvar.powernbt.api.NBTManager");
            if (slot == FormManager.getMenuLocation(i)) {
               if (FormManager.isFormRaceLimited(i)) {
                  if (PlayerManager.ValidateRace(player, FormManager.getRaces(i))) {
                     if (PlayerManager.getFormLevel(player, i) == null) {
                        player.sendMessage(Messages.prefix + ChatColor.RED + "Usted no tiene este forma");
                     } else if (PlayerManager.getFormLevel(player, i) > 0) {
                        if (PlayerManager.getSelectedForm(player) == null) {
                           PlayerManager.setSelectedForm(player, i);
                           PlayerManager.Deform(player);
                           player.sendMessage(Messages.prefix + ChatColor.BLUE + "Ha seleccionado " + FormManager.getColor(i) + FormManager.getFormattedName(i) + " forma");
                           player.closeInventory();
                        } else if (PlayerManager.getSelectedForm(player).equalsIgnoreCase(FormManager.getFormName(i))) {
                           player.sendMessage(Messages.prefix + ChatColor.RED + "Forma ya seleccionado");
                        } else {
                           PlayerManager.setSelectedForm(player, i);
                           PlayerManager.Deform(player);
                           player.sendMessage(Messages.prefix + ChatColor.BLUE + "Ha seleccionado " + FormManager.getColor(i) + FormManager.getFormattedName(i) + " forma");
                           player.closeInventory();
                        }
                     } else if (PlayerManager.getFormLevel(player, i) == 0) {
                        player.sendMessage(Messages.prefix + ChatColor.RED + "Usted no tiene este forma");
                     }
                  } else {
                     player.sendMessage(Messages.prefix + ChatColor.RED + "Debes estar en la carrera " + ChatColor.YELLOW + Messages.getDbcFormName(FormManager.getRaces(i)) + ChatColor.RED + " para usar esta forma");
                  }
               } else if (PlayerManager.getFormLevel(player, i) == null) {
                  player.sendMessage(Messages.prefix + ChatColor.RED + "Usted no tiene este forma");
               } else if (PlayerManager.getFormLevel(player, i) > 0) {
                  if (PlayerManager.getSelectedForm(player) == null) {
                     PlayerManager.setSelectedForm(player, i);
                     PlayerManager.Deform(player);
                     player.sendMessage(Messages.prefix + ChatColor.BLUE + "Ha seleccionado " + FormManager.getColor(i) + FormManager.getFormattedName(i) + " forma");
                     player.closeInventory();
                  } else if (PlayerManager.getSelectedForm(player).equalsIgnoreCase(FormManager.getFormName(i))) {
                     player.sendMessage(Messages.prefix + ChatColor.RED + "Forma ya seleccionado");
                  } else {
                     PlayerManager.setSelectedForm(player, i);
                     PlayerManager.Deform(player);
                     player.sendMessage(Messages.prefix + ChatColor.BLUE + "Ha seleccionado " + FormManager.getColor(i) + FormManager.getFormattedName(i) + " forma");
                     player.closeInventory();
                  }
               } else if (PlayerManager.getFormLevel(player, i) == 0) {
                  player.sendMessage(Messages.prefix + ChatColor.RED + "Usted no tiene este forma");
               }
            }
         } catch (ClassNotFoundException var4) {
            if (slot == FormManager.getMenuLocation(i)) {
               if (PlayerManager.getFormLevel(player, i) == null) {
                  player.sendMessage(ChatColor.RED + "Usted no tiene este forma");
               } else if (PlayerManager.getFormLevel(player, i) > 0) {
                  if (PlayerManager.getSelectedForm(player) == null) {
                     PlayerManager.setSelectedForm(player, i);
                     PlayerManager.Deform(player);
                     player.sendMessage(ChatColor.BLUE + "Ha seleccionado " + FormManager.getColor(i) + FormManager.getFormattedName(i) + " form");
                     player.closeInventory();
                  } else if (PlayerManager.getSelectedForm(player).equalsIgnoreCase(FormManager.getFormName(i))) {
                     player.sendMessage(ChatColor.RED + "Forma ya seleccionado");
                  } else {
                     PlayerManager.setSelectedForm(player, i);
                     PlayerManager.Deform(player);
                     player.sendMessage(ChatColor.BLUE + "Ha seleccionado " + FormManager.getColor(i) + FormManager.getFormattedName(i) + " form");
                     player.closeInventory();
                  }
               } else if (PlayerManager.getFormLevel(player, i) == 0) {
                  player.sendMessage(ChatColor.RED + "Usted no tiene este forma");
               }
            }
         }
      }

      i = plugin.getConfig().getInt("Settings.MenuSize");
      boolean var3;
      if (plugin.getConfig().getInt("Settings.MenuSize") < 3) {
         var3 = true;
      }

      if (plugin.getConfig().getInt("Settings.MenuSize") > 6) {
         var3 = true;
      }

      if (slot == 49) {
         MainMenu.openMenu(player);
      }

      if (slot == 4) {
         if (PlayerManager.getSelectedForm(player) == null) {
            player.sendMessage(ChatColor.RED + "No has seleccion formas.");
         } else {
            for(i = 0; i < FormManager.getForms().size(); ++i) {
               if (PlayerManager.getSelectedForm(player).equalsIgnoreCase(FormManager.getFormName(i))) {
                  PlayerManager.removeSelectedForm(player);
                  player.sendMessage(ChatColor.BLUE + "Ha deseleccionado " + FormManager.getColor(i) + FormManager.getFormattedName(i) + " forma");
                  PlayerManager.Deform(player);
                  break;
               }

               player.closeInventory();
            }
         }
      }

   }
}
