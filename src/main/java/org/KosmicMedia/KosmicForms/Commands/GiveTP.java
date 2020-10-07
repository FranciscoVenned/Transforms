package org.KosmicMedia.KosmicForms.Commands;

import org.KosmicMedia.KosmicForms.FormManager;
import org.KosmicMedia.KosmicForms.util.Messages;
import org.KosmicMedia.KosmicForms.util.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveTP {
   public static void run(CommandSender sender, String[] args) {
      int length = args.length;
      if (length >= 4) {
         boolean validform;
         int i;
         Player target;
         if (args[1].equalsIgnoreCase("Set")) {
            validform = false;

            for(i = 0; i < FormManager.getForms().size(); ++i) {
               if (args[2].equalsIgnoreCase(FormManager.getFormName(i))) {
                  validform = true;
                  target = null;
                  if (sender instanceof Player) {
                     target = (Player)sender;
                  }

                  if (length == 5) {
                     if (Bukkit.getPlayer(args[4]) == null) {
                        sender.sendMessage(Messages.prefix + ChatColor.RED + "Jugador no válido");
                        return;
                     }

                     Player target2 = Bukkit.getPlayer(args[4]);
                     PlayerManager.setFormTP(target2, i, Integer.parseInt(args[3]));
                     sender.sendMessage(Messages.prefix + ChatColor.BLUE + "Ha establecido con éxito " + ChatColor.YELLOW + target2.getName() + ChatColor.BLUE + " " + ChatColor.RESET + FormManager.getColor(i) + FormManager.getFormattedName(i) + " forma TP " + ChatColor.BLUE + "cantidad de " + ChatColor.YELLOW + args[3]);
                  } else if (sender instanceof Player) {
                     PlayerManager.setFormTP(target, i, Integer.parseInt(args[3]));
                     sender.sendMessage(Messages.prefix + ChatColor.BLUE + "Ha establecido con éxito " + ChatColor.YELLOW + target.getName() + ChatColor.BLUE + " " + ChatColor.RESET + FormManager.getColor(i) + FormManager.getFormattedName(i) + " forma TP " + ChatColor.BLUE + "cantidad de " + ChatColor.YELLOW + args[3]);
                  }
                  break;
               }
            }

            if (!validform) {
               sender.sendMessage(Messages.prefix + ChatColor.RED + "Forma invalida");
            }
         }

         if (args[1].equalsIgnoreCase("add")) {
            validform = false;

            for(i = 0; i < FormManager.getForms().size(); ++i) {
               if (args[2].equalsIgnoreCase(FormManager.getFormName(i))) {
                  validform = true;
                  target = null;
                  if (sender instanceof Player) {
                     target = (Player)sender;
                  }

                  boolean validPlayer = false;
                  int x;
                  if (length != 5) {
                     if (sender instanceof Player) {
                        if (PlayerManager.getFormTP(target, i) == null) {
                           boolean var7 = false;
                        } else {
                           x = PlayerManager.getFormTP(target, i);
                        }

                        x = Integer.parseInt(args[3]);
                        PlayerManager.setFormTP(target, i, x + x);
                        sender.sendMessage(Messages.prefix + ChatColor.BLUE + "Usted ha dado con éxito " + ChatColor.YELLOW + target.getName() + " " + ChatColor.YELLOW + x + " " + ChatColor.RESET + FormManager.getColor(i) + FormManager.getFormattedName(i) + " forma TP ");
                     } else {
                        sender.sendMessage(Messages.prefix + ChatColor.RED + "Debe seleccionar un jugador.");
                     }
                  } else if (length == 5) {
                     if (Bukkit.getPlayer(args[4]) == null) {
                        sender.sendMessage(Messages.prefix + ChatColor.RED + "Jugador no válido");
                        return;
                     }

                     Player target2 = Bukkit.getPlayer(args[4]);
                     if (PlayerManager.getFormTP(target2, i) == null) {
                        x = 0;
                     } else {
                        x = PlayerManager.getFormTP(target2, i);
                     }

                     int x21 = Integer.parseInt(args[3]);
                     PlayerManager.setFormTP(target2, i, x + x21);
                     sender.sendMessage(Messages.prefix + ChatColor.BLUE + "Usted ha dado con éxito " + ChatColor.YELLOW + target2.getName() + " " + ChatColor.YELLOW + x21 + " " + ChatColor.RESET + FormManager.getColor(i) + FormManager.getFormattedName(i) + " forma TP ");
                  }
                  break;
               }
            }

            if (!validform) {
               sender.sendMessage(Messages.prefix + ChatColor.YELLOW + args[2] + ChatColor.RED + " No es una forma válida");
            } else {
               validform = false;
            }
         }
      } else {
         sender.sendMessage(Messages.prefix + ChatColor.RED + "/KosmicForms GiveTP <SET/ADD> <" + Messages.formlist() + "> <AMOUNT> {USERNAME}");
      }

   }
}
