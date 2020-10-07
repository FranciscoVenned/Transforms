package org.KosmicMedia.KosmicForms;

import de.slikey.effectlib.EffectManager;
import org.KosmicMedia.KosmicForms.Commands.MainCommand;
import org.KosmicMedia.KosmicForms.Menus.RaceMenu;
import org.KosmicMedia.KosmicForms.util.DbcNames;
import org.KosmicMedia.KosmicForms.util.Events;
import org.KosmicMedia.KosmicForms.util.PlayerManager;
import org.KosmicMedia.KosmicForms.util.Yamls;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;

public class MainClass extends JavaPlugin implements Listener {
   public static EffectManager effectManager;
   public Settings forms;


   public void onEnable() {
      Particles.startRunnables();
      this.forms = new Settings(this, "forms");
      this.loadConfigManager();
      this.getCommands();
      MainCommand.permissions();
      new ListenerClass(this);
      new RaceMenu(this);
      new FormManager(this);
      DbcNames dbcn = new DbcNames(this);
      dbcn.createData();
      this.registerEvents(this, new Events(this));
      effectManager = new EffectManager(this);
      if (!this.getConfig().isSet("DBCAddedMultis.Rose")) {
         this.getConfig().set("DBCAddedMultis.Rose", 1.5D);
      }

      if (!this.getConfig().isSet("DBCAddedMultis.Legendary")) {
         this.getConfig().set("DBCAddedMultis.Legendary", 1.1D);
      }

      if (!this.getConfig().isSet("DBCAddedMultis.Android")) {
         this.getConfig().set("DBCAddedMultis.Android", 2.5D);
      }

      if (!this.getConfig().isSet("DBCAddedMultis.DemonBase")) {
         this.getConfig().set("DBCAddedMultis.DemonBase", 1);
      }

      if (!this.getConfig().isSet("DBCAddedMultis.DemonState1")) {
         this.getConfig().set("DBCAddedMultis.DemonState1", 1.25D);
      }

      if (!this.getConfig().isSet("DBCAddedMultis.DemonState2")) {
         this.getConfig().set("DBCAddedMultis.DemonState2", 2.5D);
      }

      if (!this.getConfig().isSet("DBCAddedMultis.DemonState3")) {
         this.getConfig().set("DBCAddedMultis.DemonState3", 3);
      }

      if (!this.getConfig().isSet("DBCAddedMultis.DemonBuff")) {
         this.getConfig().set("DBCAddedMultis.DemonBuff", 2.25D);
      }

      if (!this.getConfig().isSet("DBCAddedMultis.MajinBase")) {
         this.getConfig().set("DBCAddedMultis.MajinBase", 1);
      }

      if (!this.getConfig().isSet("DBCAddedMultis.MajinState1")) {
         this.getConfig().set("DBCAddedMultis.MajinState1", 1.25D);
      }

      if (!this.getConfig().isSet("DBCAddedMultis.MajinState2")) {
         this.getConfig().set("DBCAddedMultis.MajinState2", 1.5D);
      }

      if (!this.getConfig().isSet("DBCAddedMultis.MajinState3")) {
         this.getConfig().set("DBCAddedMultis.MajinState3", 2);
      }

      if (!this.getConfig().isSet("DBCFixes.DisableSwoop")) {
         this.getConfig().set("DBCFixes.DisableSwoop", true);
      }

      if (!this.getConfig().isSet("DBCFixes.DisableKO")) {
         this.getConfig().set("DBCFixes.DisableKO", false);
      }

      if (!this.getConfig().isSet("DBCFixes.DisablePain")) {
         this.getConfig().set("DBCFixes.DisablePain", true);
      }

      if (!this.getConfig().isSet("DBCFixes.FixNegativeTP")) {
         this.getConfig().set("DBCFixes.FixNegativeTP", true);
      }

      if (!this.getConfig().isSet("DBCAddons.125Release")) {
         this.getConfig().set("DBCAddons.125Release", true);
      }

      if (!this.getConfig().isSet("DBCAddons.TimeChamber.TPGain")) {
         this.getConfig().set("DBCAddons.TimeChamber.TPGain", 10);
      }

      if (!this.getConfig().isSet("DBCAddons.TimeChamber.HealthLost")) {
         this.getConfig().set("DBCAddons.TimeChamber.HealthLost", 4000);
      }

      if (!this.getConfig().isSet("DBCAddons.TimeChamber.HealthLostOverTime")) {
         this.getConfig().set("DBCAddons.TimeChamber.HealthLostOverTime", 1.1D);
      }

      if (!this.getConfig().isSet("DBCAddons.TimeChamber.TPGain")) {
         this.getConfig().set("DBCAddons.TimeChamber.TPGainOverTime", 3);
      }

      if (!this.getConfig().isSet("DBCAddons.TimeChamber.TimeInSecondsBeforeOverTime")) {
         this.getConfig().set("DBCAddons.TimeChamber.TimeInSecondsBeforeOverTime", 10);
      }

      this.getServer().getLogger().info("This ain't it chief");
      this.getServer().getLogger().info("Time Chamber Idea Made By: Cody#5119");
   }

   public void onDisable() {
      Iterator var1 = this.getServer().getWorlds().iterator();

      while(var1.hasNext()) {
         World w = (World)var1.next();
         Iterator var3 = w.getPlayers().iterator();

         while(var3.hasNext()) {
            Player p = (Player)var3.next();
            PlayerManager.Deform(p);
         }
      }

      effectManager.dispose();
      this.getServer().getLogger().info("This still ain't it chief");
   }

   public Settings getForms() {
      return this.forms;
   }

   public void getCommands() {
      this.getCommand("kmenu").setExecutor(new MainCommand(this));
      this.getCommand("ksetform").setExecutor(new MainCommand(this));
      this.getCommand("khakai").setExecutor(new MainCommand(this));
      this.getCommand(MainCommand.Command).setExecutor(new MainCommand(this));
      this.getCommand(MainCommand.Command).setTabCompleter(new MainCommand(this));
   }

   public void loadConfigManager() {
      Yamls.PlayerData = new Yamls();
      Yamls.PlayerData.setup();
      Yamls.PlayerData.savePlayerData();
      Yamls.PlayerData.reloadPlayerData();
   }

   public void registerEvents(Plugin plugin, Listener... listeners) {
      Listener[] arrayOfListener = listeners;
      int j = listeners.length;

      for(int i = 0; i < j; ++i) {
         Listener listener = arrayOfListener[i];
         Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
      }

   }
}
