package org.KosmicMedia.KosmicForms.Commands;

import org.KosmicMedia.KosmicForms.MainClass;
import org.KosmicMedia.KosmicForms.NBTEditor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class XpToTp {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);

   public static void run(CommandSender sender, String[] args) {
      try {
         Integer.parseInt(args[1]);
         int level = Integer.parseInt(args[1]);
         Player player = (Player)sender;
         if (player.getExpToLevel() >= level) {
            int playerlevel = player.getLevel();
            player.setExp((float)(playerlevel - level));
            NBTEditor.Edit(player, "jrmcTpint", NBTEditor.GetInt(player, "jrmcTpint") + plugin.getConfig().getInt("Settings.XpToTp") * level);
         }
      } catch (Exception var5) {
         sender.sendMessage(ChatColor.RED + "Not a real integer!");
      }

   }
}
