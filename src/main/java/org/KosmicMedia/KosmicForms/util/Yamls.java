package org.KosmicMedia.KosmicForms.util;

import org.KosmicMedia.KosmicForms.MainClass;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Yamls {
   public MainClass plugin = (MainClass)MainClass.getPlugin(MainClass.class);
   public FileConfiguration playerscfg;
   public File playersfile;
   public static Yamls PlayerData;

   public void setup() {
      if (!this.plugin.getDataFolder().exists()) {
         this.plugin.getDataFolder().mkdir();
      }

      this.playersfile = new File(this.plugin.getDataFolder(), "DBC-DATA.yml");
      if (!this.playersfile.exists()) {
         try {
            this.playersfile.createNewFile();
         } catch (IOException var2) {
         }
      }

      this.playerscfg = YamlConfiguration.loadConfiguration(this.playersfile);
   }

   public FileConfiguration getPlayerData() {
      return this.playerscfg;
   }

   public void savePlayerData() {
      try {
         this.playerscfg.save(this.playersfile);
      } catch (IOException var2) {
      }

   }

   public void reloadPlayerData() {
      this.playerscfg = YamlConfiguration.loadConfiguration(this.playersfile);
   }
}
