package org.KosmicMedia.KosmicForms.Commands;

import org.KosmicMedia.KosmicForms.FormManager;
import org.KosmicMedia.KosmicForms.MainClass;
import org.KosmicMedia.KosmicForms.MainMenu;
import org.KosmicMedia.KosmicForms.Menus.ControlPanel.Main;
import org.KosmicMedia.KosmicForms.Menus.RaceMenu;
import org.KosmicMedia.KosmicForms.NBTEditor;
import org.KosmicMedia.KosmicForms.forms.Form;
import org.KosmicMedia.KosmicForms.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements Listener, CommandExecutor, TabCompleter {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);
   public static String Command = "KosmicForms";
   public static String SetFormPerm = "Kosmic.SetForm";
   public static String GiveTPPerm = "Kosmic.GiveTP";
   public static String ReloadPerm = "Kosmic.Reload";
   public static String MenuPerm = "Kosmic.Menu";
   public static String GetPerm = "Kosmic.Get";
   public static String FusePerm = "Kosmic.CP";
   public static String RacePerm = "Kosmic.Race";

   public MainCommand(MainClass plugin) {
   }

   public static void permissions() {
      Permission SetFormPermission = new Permission(SetFormPerm);
      Permission GiveTPPermission = new Permission(GiveTPPerm);
      Permission ReloadPermission = new Permission(ReloadPerm);
      Permission MenuPermission = new Permission(MenuPerm);
      Permission GetPermission = new Permission(GetPerm);
      Permission FusePermission = new Permission(FusePerm);
      Permission RacePermission = new Permission(RacePerm);
      plugin.getServer().getPluginManager().addPermission(SetFormPermission);
      plugin.getServer().getPluginManager().addPermission(GiveTPPermission);
      plugin.getServer().getPluginManager().addPermission(ReloadPermission);
      plugin.getServer().getPluginManager().addPermission(MenuPermission);
      plugin.getServer().getPluginManager().addPermission(GetPermission);
      plugin.getServer().getPluginManager().addPermission(FusePermission);
      plugin.getServer().getPluginManager().addPermission(RacePermission);
   }

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      if (cmd.getName().equalsIgnoreCase(Command)) {
         if (args.length == 0) {
            if (sender.hasPermission(SetFormPerm)) {
               Messages.helpMessage(sender);
            } else if (sender.hasPermission(GiveTPPerm)) {
               Messages.helpMessage(sender);
            } else if (sender.hasPermission(ReloadPerm)) {
               Messages.helpMessage(sender);
            } else if (sender.hasPermission(GetPerm)) {
               Messages.helpMessage(sender);
            } else if (sender.hasPermission(FusePerm)) {
               Messages.helpMessage(sender);
            } else {
               Messages.helpMessage(sender);
            }
         } else {
            if (args[0].equalsIgnoreCase("CP")) {
               if (sender.hasPermission(FusePerm)) {
                  Main.openMenu((Player)sender);
               } else {
                  sender.sendMessage(Messages.prefix + ChatColor.DARK_RED + "Permisos insuficientes");
               }
            }

            if (args[0].equalsIgnoreCase("race")) {
               if (sender.hasPermission(RacePerm)) {
                  RaceMenu.openMenu((Player)sender);
               } else {
                  sender.sendMessage(Messages.prefix + ChatColor.DARK_RED + "Permisos insuficientes");
               }
            }

            if (args[0].equalsIgnoreCase("SetForm")) {
               if (sender.hasPermission(SetFormPerm)) {
                  SetForm.run(sender, args);
               } else {
                  sender.sendMessage(Messages.prefix + ChatColor.DARK_RED + "Permisos insuficientes");
               }
            }

            if (args[0].equalsIgnoreCase("xptotp")) {
               XpToTp.run(sender, args);
            }

            if (args[0].equalsIgnoreCase("GiveTP")) {
               if (sender.hasPermission(GiveTPPerm)) {
                  GiveTP.run(sender, args);
               } else {
                  sender.sendMessage(Messages.prefix + ChatColor.DARK_RED + "Permisos insuficientes");
               }
            }

            if (args[0].equalsIgnoreCase("reload")) {
               if (sender.hasPermission(ReloadPerm)) {
                  Reload.run(sender, args);
               } else {
                  sender.sendMessage(Messages.prefix + ChatColor.DARK_RED + "Permisos insuficientes");
               }
            }

            if (args[0].equalsIgnoreCase("Get")) {
               if (sender.hasPermission(GetPerm)) {
                  Get.run(sender, args);
               } else {
                  sender.sendMessage(Messages.prefix + ChatColor.DARK_RED + "Permisos insuficientes");
               }
            }

            if (args[0].equalsIgnoreCase("GetItem")) {
               if (args[1] != null) {
                  if (sender.hasPermission(GetPerm)) {
                     GetItem.run(sender, args);
                  } else {
                     sender.sendMessage(Messages.prefix + ChatColor.DARK_RED + "Permisos insuficientes");
                  }
               } else {
                  sender.sendMessage(Messages.prefix + ChatColor.DARK_RED + "Argumentos invalidos");
               }
            }

            ArrayList CommandList = new ArrayList();
            CommandList.add("setform");
            CommandList.add("givetp");
            CommandList.add("reload");
            CommandList.add("get");
            CommandList.add("cp");
            CommandList.add("race");
            CommandList.add("getitem");
            if (!CommandList.contains(args[0].toLowerCase())) {
               if (sender.hasPermission(SetFormPerm)) {
                  Messages.helpMessage(sender);
               } else if (sender.hasPermission(GiveTPPerm)) {
                  Messages.helpMessage(sender);
               } else if (sender.hasPermission(ReloadPerm)) {
                  Messages.helpMessage(sender);
               } else if (sender.hasPermission(GetPerm)) {
                  Messages.helpMessage(sender);
               } else if (sender.hasPermission(FusePerm)) {
                  Messages.helpMessage(sender);
               } else {
                  Messages.helpMessage(sender);
               }
            }
         }
      }

      if (cmd.getName().equalsIgnoreCase("kmenu")) {
         MainMenu.openMenu((Player)sender);
      }

      String arg;
      Player target2;
      if (cmd.getName().equalsIgnoreCase("ksetform")) {
         arg = args[0];
         if (Bukkit.getPlayer(args[1]) != null) {
            target2 = Bukkit.getPlayer(args[1]);
            NBTEditor.EditString(target2, "jrmcSSltX", "TR" + Integer.parseInt(arg));
         }
      }

      if (cmd.getName().equalsIgnoreCase("khakai")) {
         arg = args[0];
         if (Bukkit.getPlayer(args[1]) != null) {
            target2 = Bukkit.getPlayer(args[1]);
            NBTEditor.EditString(target2, "jrmcTech1", ChatColor.LIGHT_PURPLE + "Hakai" + ChatColor.RESET + ";0;Kosmic Assets;1;2;100;0;0;0;0;3;1;0;0;0;0;0;10;0;10,0,10,10,10,10,10");
         }
      }

      return false;
   }

   public List onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
      if (cmd.getName().equalsIgnoreCase("KosmicForms")) {
         List list = new ArrayList();
         if (args.length == 1) {
            if ("SetForm".toLowerCase().startsWith(args[0])) {
               list.add("SetForm");
            }

            if ("GiveTP".toLowerCase().startsWith(args[0])) {
               list.add("GiveTP");
            }

            if ("Reload".toLowerCase().startsWith(args[0])) {
               list.add("Reload");
            }

            if ("Get".toLowerCase().startsWith(args[0])) {
               list.add("Get");
            }

            if ("CP".toLowerCase().startsWith(args[0])) {
               list.add("CP");
            }

            if ("XpToTp".toLowerCase().startsWith(args[0])) {
               list.add("XpToTp");
            }

            if ("Race".toLowerCase().startsWith(args[0])) {
               list.add("Race");
            }

            if ("GetItem".toLowerCase().startsWith(args[0])) {
               list.add("GetItem");
            }
         }

         if (args.length == 2) {
            if (args[0].equalsIgnoreCase("SetForm")) {
               for(int h = 0; h < FormManager.getForms().size(); ++h) {
                  if (((Form)FormManager.getForms().get(h)).getName().toLowerCase().startsWith(args[1].toLowerCase())) {
                     list.add(((Form)FormManager.getForms().get(h)).getName());
                  }
               }
            }

            if (args[0].equalsIgnoreCase("GiveTP")) {
               if ("Set".toLowerCase().startsWith(args[1])) {
                  list.add("Set");
               }

               if ("Add".toLowerCase().startsWith(args[1])) {
                  list.add("Add");
               }
            }

            if (args[0].equalsIgnoreCase("Get")) {
               if ("HairColor".toLowerCase().startsWith(args[1])) {
                  list.add("HairColor");
               }

               if ("AuraColor".toLowerCase().startsWith(args[1])) {
                  list.add("AuraColor");
               }

               if ("TailColor".toLowerCase().startsWith(args[1])) {
                  list.add("TailColor");
               }

               if ("EyeColors".toLowerCase().startsWith(args[1])) {
                  list.add("EyeColors");
               }

               if ("StatusEffects".toLowerCase().startsWith(args[1])) {
                  list.add("StatusEffects");
               }

               if ("State".toLowerCase().startsWith(args[1])) {
                  list.add("State");
               }

               if ("Kaio".toLowerCase().startsWith(args[1])) {
                  list.add("Kaio");
               }
            }

            if (args[0].equalsIgnoreCase("GetItem")) {
               if ("PotaraEarring".toLowerCase().startsWith(args[1])) {
                  list.add("PotaraEarring");
               }

               if ("TimeSkip".toLowerCase().startsWith(args[1])) {
                  list.add("TimeSkip");
               }
            }
         }

         Player player;
         if (args.length == 3) {
            if (args[0].equalsIgnoreCase("GiveTP") && args[0].equalsIgnoreCase("GiveTP")) {
               for(int h = 0; h < FormManager.getForms().size(); ++h) {
                  if (((Form)FormManager.getForms().get(h)).getName().toLowerCase().startsWith(args[1].toLowerCase())) {
                     list.add(((Form)FormManager.getForms().get(h)).getName());
                  }
               }
            }

            if (args[0].equalsIgnoreCase("Get") && Bukkit.getPlayer(args[2]) != null) {
               player = Bukkit.getPlayer(args[2]);
               list.add(player.getName());
            }
         }

         if (args.length == 4 && args[0].equalsIgnoreCase("SetForm") && Bukkit.getPlayer(args[3]) != null) {
            player = Bukkit.getPlayer(args[3]);
            list.add(player.getName());
         }

         if (args.length == 5 && Bukkit.getPlayer(args[4]) != null) {
            player = Bukkit.getPlayer(args[4]);
            list.add(player.getName());
         }

         return list;
      } else {
         return null;
      }
   }
}
