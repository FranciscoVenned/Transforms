package org.KosmicMedia.KosmicForms.util;

import org.KosmicMedia.KosmicForms.MainClass;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DbcNames {
   public MainClass pl;
   File file;
   FileConfiguration fc;
   String FileName = "DeathMessages.yml";

   public DbcNames(MainClass pl) {
      this.pl = pl;
   }

   public File getFile() {
      this.file = new File(this.pl.getDataFolder(), this.FileName);
      return this.file;
   }

   public FileConfiguration getData() {
      this.fc = YamlConfiguration.loadConfiguration(this.getFile());
      return this.fc;
   }

   public void saveData() {
      this.file = new File(this.pl.getDataFolder(), this.FileName);

      try {
         this.fc.save(this.getFile());
      } catch (IOException var2) {
         var2.printStackTrace();
         System.out.println("[DbcDeathMessages] Attempting to fix error...");
         this.createData();
         this.saveData();
      }

   }

   public void createData() {
      if (!this.getFile().exists()) {
         if (!this.pl.getDataFolder().exists()) {
            this.pl.getDataFolder().mkdirs();
         }

         this.pl.saveResource(this.FileName, false);
      }

   }
}
