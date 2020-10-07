package org.KosmicMedia.KosmicForms.Commands;

import org.KosmicMedia.KosmicForms.util.Messages;
import org.KosmicMedia.KosmicForms.util.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Get {
   public static void run(CommandSender sender, String[] args) {
      ArrayList CommandList = new ArrayList();
      CommandList.add("HairColor");
      CommandList.add("AuraColor");
      CommandList.add("TailColor");
      CommandList.add("EyeColors");
      CommandList.add("StatusEffects");
      CommandList.add("State");
      if (!CommandList.contains(args[1])) {
         sender.sendMessage(Messages.prefix + ChatColor.RED + "/KosmicForms <GET> <AURACOLOR/HAIRCOLOR/TAILCOLOR/EYECOLORS/STATUSEFFECTS/STATE> {PLAYER}");
      } else {
         int length = args.length;
         if (length == 3) {
            Player player;
            if (args[1].equalsIgnoreCase("HairColor")) {
               if (Bukkit.getPlayer(args[2]) == null) {
                  sender.sendMessage(Messages.prefix + ChatColor.RED + "Jugador invalido");
                  return;
               }

               player = Bukkit.getPlayer(args[2]);
               sender.sendMessage(Messages.prefix + ChatColor.YELLOW + player.getName() + ChatColor.BLUE + " color de cabello es " + ChatColor.YELLOW + PlayerManager.getHairColor(player) + ChatColor.BLUE + ".");
            }

            if (args[1].equalsIgnoreCase("AuraColor")) {
               if (Bukkit.getPlayer(args[2]) == null) {
                  sender.sendMessage(Messages.prefix + ChatColor.RED + "Jugador invalido");
                  return;
               }

               player = Bukkit.getPlayer(args[2]);
               sender.sendMessage(Messages.prefix + ChatColor.YELLOW + player.getName() + ChatColor.BLUE + " color de aura es " + ChatColor.YELLOW + PlayerManager.getAuraColor(player) + ChatColor.BLUE + ".");
            }

            if (args[1].equalsIgnoreCase("TailColor")) {
               if (Bukkit.getPlayer(args[2]) == null) {
                  sender.sendMessage(Messages.prefix + ChatColor.RED + "Jugador invalido");
                  return;
               }

               player = Bukkit.getPlayer(args[2]);
               sender.sendMessage(Messages.prefix + ChatColor.YELLOW + player.getName() + ChatColor.BLUE + " color de camino es " + ChatColor.YELLOW + PlayerManager.getTailColor(player) + ChatColor.BLUE + ".");
            }

            if (args[1].equalsIgnoreCase("EyeColors")) {
               if (Bukkit.getPlayer(args[2]) == null) {
                  sender.sendMessage(Messages.prefix + ChatColor.RED + "Jugador invalido");
                  return;
               }

               player = Bukkit.getPlayer(args[2]);
               sender.sendMessage(Messages.prefix + ChatColor.YELLOW + player.getName() + ChatColor.BLUE + " color de ojos son " + ChatColor.YELLOW + PlayerManager.getEye1(player) + ChatColor.BLUE + " y " + ChatColor.YELLOW + PlayerManager.getEye2(player) + ChatColor.BLUE + ".");
            }

            if (args[1].equalsIgnoreCase("StatusEffects")) {
               if (Bukkit.getPlayer(args[2]) == null) {
                  sender.sendMessage(Messages.prefix + ChatColor.RED + "Jugador invalido");
                  return;
               }

               player = Bukkit.getPlayer(args[2]);
               sender.sendMessage(Messages.prefix + ChatColor.YELLOW + player.getName() + ChatColor.BLUE + " estado de efectos son " + ChatColor.YELLOW + PlayerManager.getStatusEffs(player) + ChatColor.BLUE + ".");
            }

            if (args[1].equalsIgnoreCase("State")) {
               if (Bukkit.getPlayer(args[2]) == null) {
                  sender.sendMessage(Messages.prefix + ChatColor.RED + "Jugador invalido");
                  return;
               }

               player = Bukkit.getPlayer(args[2]);
               sender.sendMessage(Messages.prefix + ChatColor.YELLOW + player.getName() + ChatColor.BLUE + " estado es " + ChatColor.YELLOW + PlayerManager.getState(player) + ChatColor.BLUE + ".");
            }

            if (args[1].equalsIgnoreCase("Kaio")) {
               if (Bukkit.getPlayer(args[2]) == null) {
                  sender.sendMessage(Messages.prefix + ChatColor.RED + "Jugador invalido");
                  return;
               }

               player = Bukkit.getPlayer(args[2]);
               sender.sendMessage(Messages.prefix + ChatColor.YELLOW + player.getName() + ChatColor.BLUE + " nivel de Kaio es " + ChatColor.YELLOW + PlayerManager.getState2(player) + ChatColor.BLUE + ".");
            }
         } else if (length == 2) {
            if (args[1].equalsIgnoreCase("HairColor")) {
               sender.sendMessage(Messages.prefix + ChatColor.BLUE + "Tu cabello es de color " + ChatColor.YELLOW + PlayerManager.getHairColor((Player)sender) + ChatColor.BLUE + ".");
            } else if (args[1].equalsIgnoreCase("AuraColor")) {
               sender.sendMessage(Messages.prefix + ChatColor.BLUE + "Tu aura es de color " + ChatColor.YELLOW + PlayerManager.getAuraColor((Player)sender) + ChatColor.BLUE + ".");
            } else if (args[1].equalsIgnoreCase("TailColor")) {
               sender.sendMessage(Messages.prefix + ChatColor.BLUE + "Tu camino es de color " + ChatColor.YELLOW + PlayerManager.getTailColor((Player)sender) + ChatColor.BLUE + ".");
            } else if (args[1].equalsIgnoreCase("EyeColors")) {
               sender.sendMessage(Messages.prefix + ChatColor.BLUE + "Tu color de ojos son " + ChatColor.YELLOW + PlayerManager.getEye1((Player)sender) + ChatColor.BLUE + " y " + ChatColor.YELLOW + PlayerManager.getEye2((Player)sender) + ChatColor.BLUE + ".");
            } else if (args[1].equalsIgnoreCase("StatusEffects")) {
               sender.sendMessage(Messages.prefix + ChatColor.BLUE + "Tu estado de efectos son " + ChatColor.YELLOW + PlayerManager.getStatusEffs((Player)sender) + ChatColor.BLUE + ".");
            } else if (args[1].equalsIgnoreCase("State")) {
               sender.sendMessage(Messages.prefix + ChatColor.BLUE + "Tu estado es " + ChatColor.YELLOW + PlayerManager.getState((Player)sender) + ChatColor.BLUE + ".");
            } else {
               sender.sendMessage(Messages.prefix + ChatColor.RED + "/KosmicForms <GET> <AURACOLOR/HAIRCOLOR/TAILCOLOR/EYECOLORS/STATUSEFFECTS/STATE> {PLAYER}");
            }
         } else {
            sender.sendMessage(Messages.prefix + ChatColor.RED + "/KosmicForms <GET> <AURACOLOR/HAIRCOLOR/TAILCOLOR/EYECOLORS/STATUSEFFECTS/STATE> {PLAYER}");
         }
      }

   }
}
