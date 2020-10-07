package org.KosmicMedia.KosmicForms.Commands;

import org.KosmicMedia.KosmicForms.MainClass;
import org.KosmicMedia.KosmicForms.util.ConfigManager;
import org.KosmicMedia.KosmicForms.util.Messages;
import org.KosmicMedia.KosmicForms.util.Yamls;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class Reload {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);

   public static void run(CommandSender sender, String[] args) {
      plugin.reloadConfig();
      ConfigManager.Create();
      Yamls.PlayerData.reloadPlayerData();

      try {
         Class.forName("org.KosmicMedia.KosmicFormsAddForms.MainClass");
         sender.sendMessage(Messages.prefix + ChatColor.BLUE + "Datos del plugin y configuración recargada.");
      } catch (ClassNotFoundException var3) {
         sender.sendMessage(Messages.prefix + ChatColor.BLUE + "Datos del plugin y configuración recargada.");
      }

   }
}
