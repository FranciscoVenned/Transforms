package org.KosmicMedia.KosmicForms.Commands;

import org.KosmicMedia.KosmicForms.FormManager;
import org.KosmicMedia.KosmicForms.util.Messages;
import org.KosmicMedia.KosmicForms.util.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetForm {
   public static void run(CommandSender sender, String[] args) {
      int length = args.length;
      if (length >= 3) {
         boolean validform = false;

         for(int i = 0; i < FormManager.getForms().size(); ++i) {
            if (args[1].equalsIgnoreCase(FormManager.getFormName(i))) {
               validform = true;
               Player target = null;
               if (sender instanceof Player) {
                  target = (Player)sender;
               }

               if (length < 4) {
                  if (sender instanceof Player) {
                     int level = Integer.parseInt(args[2]);
                     PlayerManager.setFormLevel(target, i, level);
                     sender.sendMessage(Messages.prefix + ChatColor.BLUE + "Usted ha dado con éxito pelotudo " + ChatColor.YELLOW + target.getName() + FormManager.getColor(i) + " " + FormManager.getFormattedName(i) + " form " + ChatColor.BLUE + "Level " + ChatColor.YELLOW + Integer.parseInt(args[2]));
                     PlayerManager.Deform(target);
                  }
               } else {
                  if (Bukkit.getPlayer(args[3]) == null) {
                     sender.sendMessage(Messages.prefix + ChatColor.RED + "Jugador invalido");
                     return;
                  }

                  Player target2 = Bukkit.getPlayer(args[3]);
                  int level = Integer.parseInt(args[2]);
                  PlayerManager.setFormLevel(target2, i, level);
                  sender.sendMessage(Messages.prefix + ChatColor.BLUE + "Usted ha dado con éxito " + ChatColor.YELLOW + target2.getName() + FormManager.getColor(i) + " " + FormManager.getFormattedName(i) + " forma " + ChatColor.BLUE + "Nivel " + ChatColor.YELLOW + Integer.parseInt(args[2]));
                  PlayerManager.Deform(target2);
               }
               break;
            }
         }

         if (!validform) {
            sender.sendMessage(Messages.prefix + ChatColor.YELLOW + args[1] + ChatColor.RED + " Esta es una forma invalida");
         } else {
            validform = false;
         }
      } else {
         sender.sendMessage(Messages.prefix + ChatColor.RED + "/KosmicForms SetForm <" + Messages.formlist() + "> <LEVEL> {USERNAME}");
      }

   }
}
