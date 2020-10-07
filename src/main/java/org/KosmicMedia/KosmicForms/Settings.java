package org.KosmicMedia.KosmicForms;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class Settings {
   public YamlConfiguration config;
   public File file;
   public MainClass u;

   public Settings(MainClass u, String s) {
      this.u = u;
      this.file = new File(this.u.getDataFolder(), String.valueOf(s) + ".yml");
      this.config = YamlConfiguration.loadConfiguration(this.file);
      Reader reader = new InputStreamReader(u.getResource(String.valueOf(s) + ".yml"));
      YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(reader);

      try {
         if (!this.file.exists()) {
            this.config.addDefaults(loadConfiguration);
            this.config.options().copyDefaults(true);
            this.config.save(this.file);
         } else {
            this.config.addDefaults(loadConfiguration);
            this.config.options().copyDefaults(true);
            this.config.save(this.file);
            this.config.load(this.file);
         }
      } catch (InvalidConfigurationException | IOException var6) {
      }

   }

   public void sDefault(String s, String s2) {
      if (!this.config.contains(s)) {
         this.config.set(s, s2);
         this.save();
      }

   }

   public void save() {
      try {
         this.config.save(this.file);
      } catch (IOException var2) {
      }

   }

   public YamlConfiguration getConfig() {
      return this.config;
   }

   public File getFile() {
      return this.file;
   }

   public String get(String s) {
      return this.config.getString(s) == null ? null : this.config.getString(s).replaceAll("<l>", "¡").replaceAll("&", "§").replaceAll("-,-", "ñ");
   }

   public float getFloat(String s) {
      return Float.parseFloat(this.config.getString(s));
   }

   public int getInt(String s) {
      return this.config.getInt(s);
   }

   public double getDouble(String s) {
      return this.config.getDouble(s);
   }

   public List getList(String s) {
      return this.config.getStringList(s);
   }

   public boolean isSet(String s) {
      return this.config.isSet(s);
   }

   public void set(String s, Object o) {
      this.config.set(s, o);
   }

   public boolean getBoolean(String s) {
      return this.config.getBoolean(s);
   }
}
