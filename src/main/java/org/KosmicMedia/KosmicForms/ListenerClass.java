package org.KosmicMedia.KosmicForms;

import net.minecraft.server.v1_7_R4.PacketPlayOutWorldParticles;
import org.KosmicMedia.KosmicForms.Menus.ControlPanel.Main;
import org.KosmicMedia.KosmicForms.Menus.FormMenu;
import org.KosmicMedia.KosmicForms.Menus.SelectFormMenu;
import org.KosmicMedia.KosmicForms.util.*;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class ListenerClass implements Listener {
   Plugin plugin = MainClass.getPlugin(MainClass.class);
   public HashMap clickPlayers = new HashMap();
   public HashMap cooldownPlayers = new HashMap();
   public static int Time1 = 0;
   public static int overTime1 = 1;
   public static final WeakHashMap shotprojectiledata = new WeakHashMap();
   public Map projectileUUID = new HashMap();

   public ListenerClass(MainClass plugin) {
      plugin.getServer().getPluginManager().registerEvents(this, plugin);
   }

   @EventHandler
   public void onJoin(PlayerJoinEvent e) {
      Player player = e.getPlayer();
      Particles.online.add(player);
      PlayerInventory inv = player.getInventory();
      ItemStack a = Methods.createItemStack(Material.NETHER_STAR, 1, ChatColor.DARK_PURPLE + "Transformations", (String)"");
      Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".Fuse", 0);
      Yamls.PlayerData.savePlayerData();
      if (this.plugin.getConfig().getBoolean("Settings.FormsStar") && !inv.contains(a)) {
         inv.addItem(new ItemStack[]{a});
      }

      if (Yamls.PlayerData.getPlayerData().getBoolean("PlayerData." + player.getUniqueId() + ".isMajin") || Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".KFRace") == 1) {
         this.delayTask(player);
      }

      if (Yamls.PlayerData.getPlayerData().getBoolean("PlayerData." + player.getUniqueId() + ".isDemon") || Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".KFRace") == 2) {
         this.delayTask1(player);
      }

   }

   private void delayTask1(final Player p) {
      Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
         public void run() {
            p.setResourcePack("https://www.dropbox.com/s/0o6rkma6mhzi2cn/MaleDemon.zip?dl=1");
         }
      }, 1L);
   }

   private void delayTask(final Player p) {
      Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
         public void run() {
            p.setResourcePack("https://www.dropbox.com/s/h0wvqrsu3za1enq/Male%20Majin.zip?dl=1");
         }
      }, 1L);
   }

   @EventHandler
   private void customSkill(PlayerAnimationEvent e) {
   }

   @EventHandler
   private void openMenu(PlayerInteractEvent e) {
      ItemStack a = Methods.createItemStack(Material.NETHER_STAR, 1, ChatColor.DARK_PURPLE + "Transformations", (String)"");
      if (e.getPlayer().getItemInHand().equals(a)) {
         MainMenu.openMenu(e.getPlayer());
      }

   }

   @EventHandler
   private void onRightClick(PlayerInteractEntityEvent e) {
      ItemStack potara = Methods.createItemStack(this.plugin.getConfig().getInt("Settings.PotaraItemID"), this.plugin.getConfig().getInt("Settings.PotaraItemDamage"), ChatColor.WHITE + "Potara Earring", "");
      Player requester;
      if (e.getPlayer().getItemInHand().equals(potara) && e.getRightClicked() instanceof Player) {
         requester = e.getPlayer();
         requester = (Player)e.getRightClicked();
         if (requester.isSneaking() && requester.getInventory().contains(potara)) {
            NBTEditor.EditString(requester, "jrmcFuzion", requester.getName() + "," + requester.getName() + ",5656556");
            NBTEditor.EditString(requester, "jrmcFuzion", requester.getName() + "," + requester.getName() + ",5656556");
            Yamls.PlayerData.getPlayerData().set("PlayerData." + requester.getUniqueId() + ".invis", true);
            requester.sendMessage(ChatColor.YELLOW + "Usted se ha fusionado con " + requester.getName() + "!");
            requester.sendMessage(ChatColor.YELLOW + "Usted se ha fusionado con " + requester.getName() + "!");
            String fznn = Methods.f_namegen(requester.getName(), requester.getName());
            requester.getServer().broadcastMessage(requester.getName() + " y " + requester.getName() + " fusionado con " + fznn + "!");
            Yamls.PlayerData.getPlayerData().set("PlayerData." + requester.getUniqueId() + ".Fuse", (double)NBTEditor.GetInt(requester, "jrmcStrI"));
            Yamls.PlayerData.savePlayerData();
            Inventory ci = requester.getInventory();
            ci.removeItem(new ItemStack[]{potara});
         }
      }

      ItemStack potaraGlow = Methods.createItemStackWithGlow(Material.GHAST_TEAR, 0, ChatColor.WHITE + "Potara Earring", "");
      if (e.getPlayer().getItemInHand().equals(potaraGlow) && e.getRightClicked() instanceof Player) {
         requester = e.getPlayer();
         Player requested = (Player)e.getRightClicked();
         if (requester.isSneaking() && requested.getInventory().contains(potaraGlow)) {
            NBTEditor.EditString(requester, "jrmcFuzion", requester.getName() + "," + requested.getName() + ",5656556");
            NBTEditor.EditString(requested, "jrmcFuzion", requester.getName() + "," + requested.getName() + ",5656556");
            Yamls.PlayerData.getPlayerData().set("PlayerData." + requested.getUniqueId() + ".invis", true);
            requester.sendMessage(ChatColor.YELLOW + "Usted se ha fusionado con " + requested.getName() + "!");
            requested.sendMessage(ChatColor.YELLOW + "Usted se ha fusionado con " + requester.getName() + "!");
            String fznn = Methods.f_namegen(requester.getName(), requested.getName());
            requester.getServer().broadcastMessage(requester.getName() + " y " + requested.getName() + " fusionado con " + fznn + "!");
            Yamls.PlayerData.getPlayerData().set("PlayerData." + requester.getUniqueId() + ".Fuse", (double)NBTEditor.GetInt(requested, "jrmcStrI"));
            Yamls.PlayerData.savePlayerData();
            Inventory ci = requester.getInventory();
            ci.removeItem(new ItemStack[]{potara});
         }
      }

   }

   @EventHandler
   public void onDeath(PlayerDeathEvent e) {
      Player player = e.getEntity();
      if (PlayerManager.isPlayerFormed(player)) {
         Messages.deform(PlayerManager.getActiveForm(player), PlayerManager.getFormLevel(player, PlayerManager.getActiveForm(player)));
         PlayerManager.Deform(player);
         Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".Fuse", 0);
         Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".invis", false);
         Yamls.PlayerData.savePlayerData();
      }

   }

   @EventHandler
   public void onQuit(PlayerQuitEvent e) {
      Player p = e.getPlayer();
      Particles.online.remove(p);
      PlayerManager.Deform(p);
   }

   @EventHandler
   public void onKick(PlayerKickEvent e) {
      Player p = e.getPlayer();
      Particles.online.remove(p);
      PlayerManager.Deform(p);
   }

   @EventHandler
   public void onSneak(PlayerToggleSneakEvent e) {
      Player player = e.getPlayer();
      Location loc = player.getLocation();
      if (e.isSneaking()) {
         long currTime = System.currentTimeMillis();
         if (!this.clickPlayers.containsKey(e.getPlayer().getName())) {
            this.clickPlayers.put(e.getPlayer().getName(), currTime);
         }

         long lastClick = (Long)this.clickPlayers.get(e.getPlayer().getName());
         if (currTime - lastClick >= 1L) {
            if (currTime - lastClick > 300L) {
               this.clickPlayers.remove(e.getPlayer().getName());
               this.clickPlayers.put(e.getPlayer().getName(), currTime);
            } else {
               this.clickPlayers.remove(e.getPlayer().getName());
               this.clickPlayers.put(e.getPlayer().getName(), currTime);
               int i;
               int help;
               Color color2;
               Color color;
               String b;
               Color Color1;
               Color Color2;
               Boolean Good;
               String a;
               Boolean Good1;
               if (this.cooldownPlayers.containsKey(player.getName())) {
                  if ((Long)this.cooldownPlayers.get(player.getName()) > currTime) {
                     long rest = (Long)this.cooldownPlayers.get(player.getName()) - currTime;
                     player.sendMessage("§cDebes esperar " + (new DecimalFormat("#.#")).format(rest / 1000L) + " segundos para volver a transformarte.");
                     return;
                  }

                  this.cooldownPlayers.put(player.getName(), currTime + 5000L);
                  if (PlayerManager.isPlayerFormed(player)) {
                     for(i = 0; i < FormManager.getForms().size(); ++i) {
                        if (i == PlayerManager.getActiveForm(player)) {
                           if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("SaiyanDay") && Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".SaiyanDayHelp") == 0) {
                              PlayerManager.Deform(player);
                              player.sendMessage(Messages.prefix + ChatColor.BLUE + "Has ascendido a " + ChatColor.AQUA + ChatColor.ITALIC + "Dominado SSB nivel de Forma " + PlayerManager.getFormLevel(player, i) + ChatColor.BLUE + "!");
                              Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "SaiyanDay2DONTTOUCH");
                              Yamls.PlayerData.savePlayerData();
                              help = PlayerManager.getFormLevel(player, i);
                              Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SaiyanDay2DONTTOUCHForm.SaiyanDay2DONTTOUCHLevel", help);
                              Yamls.PlayerData.savePlayerData();
                              color2 = Color.BLUE;
                              color2 = Color.AQUA;
                              Fireworks.explosionSpecial(loc, color2, color2);
                              Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SaiyanDayHelp", 1);
                              Yamls.PlayerData.savePlayerData();
                           } else if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("FSSJ") && Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".FSSJHelp") == 0) {
                              Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "FSSJ3DontTouch");
                              Yamls.PlayerData.savePlayerData();
                              help = PlayerManager.getFormLevel(player, i);
                              Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".FSSJ3DontTouchForm.FSSJ3DontTouchLevel", help);
                              Yamls.PlayerData.savePlayerData();
                              player.sendMessage(Messages.prefix + ChatColor.BLUE + "Has ascendido a " + ChatColor.DARK_RED + ChatColor.BOLD + "Falso SS3 de nivel " + PlayerManager.getFormLevel(player, i) + ChatColor.BLUE + "!");
                              color2 = Color.ORANGE;
                              color2 = Color.MAROON;
                              Fireworks.explosionSpecial(loc, color2, color2);
                              Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".FSSJHelp", 1);
                              Yamls.PlayerData.savePlayerData();
                           } else if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("SK")) {
                              if (Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".SKHelp") == 0) {
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "SK2DONTTOUCH");
                                 Yamls.PlayerData.savePlayerData();
                                 help = PlayerManager.getFormLevel(player, i);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SK2DONTTOUCHForm.SK2DONTTOUCHLevel", help);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SK3DONTTOUCHForm.SK3DONTTOUCHLevel", help);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SK4DONTTOUCHForm.SK4DONTTOUCHLevel", help);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SK5DONTTOUCHForm.SK5DONTTOUCHLevel", help);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SK6DONTTOUCHForm.SK6DONTTOUCHLevel", help);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SK7DONTTOUCHForm.SK7DONTTOUCHLevel", help);
                                 Yamls.PlayerData.savePlayerData();
                                 color2 = Color.RED;
                                 color2 = Color.ORANGE;
                                 Fireworks.explosion(loc, color2, color2);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SKHelp", 1);
                                 Yamls.PlayerData.savePlayerData();
                                 player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Mastered Kaioken x2");
                              } else if (Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".SKHelp") == 1) {
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "SK3DONTTOUCH");
                                 Yamls.PlayerData.savePlayerData();
                                 color = Color.RED;
                                 color2 = Color.ORANGE;
                                 Fireworks.explosion(loc, color, color2);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SKHelp", 2);
                                 Yamls.PlayerData.savePlayerData();
                                 player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Mastered Kaioken x3");
                              } else if (Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".SKHelp") == 2) {
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "SK4DONTTOUCH");
                                 Yamls.PlayerData.savePlayerData();
                                 color = Color.RED;
                                 color2 = Color.ORANGE;
                                 Fireworks.explosion(loc, color, color2);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SKHelp", 3);
                                 Yamls.PlayerData.savePlayerData();
                                 player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Mastered Kaioken x10");
                              } else if (Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".SKHelp") == 3) {
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "SK5DONTTOUCH");
                                 Yamls.PlayerData.savePlayerData();
                                 color = Color.RED;
                                 color2 = Color.ORANGE;
                                 Fireworks.explosion(loc, color, color2);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SKHelp", 4);
                                 Yamls.PlayerData.savePlayerData();
                                 player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Mastered Kaioken x20");
                              } else if (Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".SKHelp") == 4) {
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "SK6DONTTOUCH");
                                 Yamls.PlayerData.savePlayerData();
                                 color = Color.RED;
                                 color2 = Color.ORANGE;
                                 Fireworks.explosion(loc, color, color2);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SKHelp", 5);
                                 Yamls.PlayerData.savePlayerData();
                                 player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Mastered Kaioken x50");
                              } else if (Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".SKHelp") == 5) {
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "SK7DONTTOUCH");
                                 Yamls.PlayerData.savePlayerData();
                                 color = Color.RED;
                                 color2 = Color.ORANGE;
                                 Fireworks.explosionSpecial(loc, color, color2);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SKHelp", -1);
                                 Yamls.PlayerData.savePlayerData();
                                 player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Mastered Kaioken x100");
                              } else {
                                 player.sendMessage(Messages.deform(i, PlayerManager.getFormLevel(player, i)));
                                 PlayerManager.Deform(player);
                              }
                           } else {
                              player.sendMessage(Messages.deform(i, PlayerManager.getFormLevel(player, i)));
                              PlayerManager.Deform(player);
                              NBTEditor.EditInt(player, "jrmcAlign", Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".Align"));
                           }
                           break;
                        }
                     }
                  } else {
                     for(i = 0; i < FormManager.getForms().size(); ++i) {
                        if (!Yamls.PlayerData.getPlayerData().getBoolean("PlayerData." + player.getUniqueId() + ".isAndroid") && !Yamls.PlayerData.getPlayerData().getBoolean("PlayerData." + player.getUniqueId() + ".isDemon") && !Yamls.PlayerData.getPlayerData().getBoolean("PlayerData." + player.getUniqueId() + ".isMajin") && PlayerManager.getSelectedForm(player) != null && PlayerManager.getSelectedForm(player).equalsIgnoreCase(FormManager.getFormName(i))) {
                           try {
                              Class.forName("me.dpohvar.powernbt.api.NBTManager");
                              if (!PlayerManager.ValidateRace(player, FormManager.getRaces(i))) {
                                 player.sendMessage(Messages.prefix + ChatColor.RED + "Debes estar en la carrera " + ChatColor.YELLOW + Messages.getDbcFormName(FormManager.getRaces(i)) + ChatColor.RED + " para usar esta forma!");
                              } else if (!PlayerManager.ValidateAlignment(player, FormManager.getAlignment(i))) {
                                 player.sendMessage(Messages.prefix + ChatColor.RED + "Debes estar en la alineación " + ChatColor.YELLOW + Messages.getAlignmentName(FormManager.getAlignment(i)) + ChatColor.RED + " para usar esta forma!");
                              } else if (GetStats.getLevel(player) < FormManager.getLevel(i)) {
                                 player.sendMessage(Messages.prefix + ChatColor.RED + "Su nivel debe ser " + ChatColor.YELLOW + FormManager.getLevel(i) + ChatColor.RED + " o mayor para usar esta forma!");
                              } else {
                                 PlayerManager.Transform(player, i);
                                 Good = false;
                                 Good1 = false;
                                 if (FormManager.getColor1(i) != null && FormManager.getColor1(i).toString() != "") {
                                    Good = true;
                                 }

                                 if (FormManager.getColor2(i) != null && FormManager.getColor2(i).toString() != "") {
                                    Good1 = true;
                                 }

                                 if (Good && Good1) {
                                    a = FormManager.getColor1(i);
                                    b = FormManager.getColor2(i);
                                    Color1 = Color.WHITE;
                                    Color2 = Color.BLACK;
                                    Color1 = utils.isColor(a);
                                    Color2 = utils.isColor(b);
                                    Fireworks.explosion(loc, Color1, Color2);
                                 }

                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".Align", GetStats.getAlign(player));
                                 Yamls.PlayerData.savePlayerData();
                                 if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("GOD")) {
                                    NBTEditor.EditInt(player, "jrmcAlign", 57);
                                 }

                                 if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("SK")) {
                                    Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SKHelp", 0);
                                    Yamls.PlayerData.savePlayerData();
                                 }

                                 if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("FSSJ")) {
                                    Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".FSSJHelp", 0);
                                    Yamls.PlayerData.savePlayerData();
                                 }

                                 if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("SaiyanDay")) {
                                    Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SaiyanDayHelp", 0);
                                    Yamls.PlayerData.savePlayerData();
                                 }

                                 player.sendMessage(Messages.transform(i, PlayerManager.getFormLevel(player, i)));
                              }
                           } catch (ClassNotFoundException var16) {
                              PlayerManager.Transform(player, i);
                              player.sendMessage(Messages.transform(i, PlayerManager.getFormLevel(player, i)));
                           }
                        }
                     }
                  }
               } else {
                  this.cooldownPlayers.put(player.getName(), currTime + 5000L);
                  if (PlayerManager.isPlayerFormed(player)) {
                     for(i = 0; i < FormManager.getForms().size(); ++i) {
                        if (i == PlayerManager.getActiveForm(player)) {
                           if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("SaiyanDay") && Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".SaiyanDayHelp") == 0) {
                              PlayerManager.Deform(player);
                              player.sendMessage(Messages.prefix + ChatColor.BLUE + "Has ascendido a " + ChatColor.AQUA + ChatColor.ITALIC + "Dominado SSB nivel de Forma " + PlayerManager.getFormLevel(player, i) + ChatColor.BLUE + "!");
                              Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "SaiyanDay2DONTTOUCH");
                              Yamls.PlayerData.savePlayerData();
                              help = PlayerManager.getFormLevel(player, i);
                              Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SaiyanDay2DONTTOUCHForm.SaiyanDay2DONTTOUCHLevel", help);
                              Yamls.PlayerData.savePlayerData();
                              color2 = Color.BLUE;
                              color2 = Color.AQUA;
                              Fireworks.explosionSpecial(loc, color2, color2);
                              Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SaiyanDayHelp", 1);
                              Yamls.PlayerData.savePlayerData();
                           } else if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("FSSJ") && Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".FSSJHelp") == 0) {
                              Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "FSSJ3DontTouch");
                              Yamls.PlayerData.savePlayerData();
                              help = PlayerManager.getFormLevel(player, i);
                              Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".FSSJ3DontTouchForm.FSSJ3DontTouchLevel", help);
                              Yamls.PlayerData.savePlayerData();
                              player.sendMessage(Messages.prefix + ChatColor.BLUE + "Has ascendido a " + ChatColor.DARK_RED + ChatColor.BOLD + "Falso SS3 de nivel " + PlayerManager.getFormLevel(player, i) + ChatColor.BLUE + "!");
                              color2 = Color.ORANGE;
                              color2 = Color.MAROON;
                              Fireworks.explosionSpecial(loc, color2, color2);
                              Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".FSSJHelp", 1);
                              Yamls.PlayerData.savePlayerData();
                           } else if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("SK")) {
                              if (Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".SKHelp") == 0) {
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "SK2DONTTOUCH");
                                 Yamls.PlayerData.savePlayerData();
                                 help = PlayerManager.getFormLevel(player, i);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SK2DONTTOUCHForm.SK2DONTTOUCHLevel", help);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SK3DONTTOUCHForm.SK3DONTTOUCHLevel", help);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SK4DONTTOUCHForm.SK4DONTTOUCHLevel", help);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SK5DONTTOUCHForm.SK5DONTTOUCHLevel", help);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SK6DONTTOUCHForm.SK6DONTTOUCHLevel", help);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SK7DONTTOUCHForm.SK7DONTTOUCHLevel", help);
                                 Yamls.PlayerData.savePlayerData();
                                 color2 = Color.RED;
                                 color2 = Color.ORANGE;
                                 Fireworks.explosion(loc, color2, color2);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SKHelp", 1);
                                 Yamls.PlayerData.savePlayerData();
                                 player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Mastered Kaioken x2");
                              } else if (Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".SKHelp") == 1) {
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "SK3DONTTOUCH");
                                 Yamls.PlayerData.savePlayerData();
                                 color = Color.RED;
                                 color2 = Color.ORANGE;
                                 Fireworks.explosion(loc, color, color2);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SKHelp", 2);
                                 Yamls.PlayerData.savePlayerData();
                                 player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Mastered Kaioken x3");
                              } else if (Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".SKHelp") == 2) {
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "SK4DONTTOUCH");
                                 Yamls.PlayerData.savePlayerData();
                                 color = Color.RED;
                                 color2 = Color.ORANGE;
                                 Fireworks.explosion(loc, color, color2);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SKHelp", 3);
                                 Yamls.PlayerData.savePlayerData();
                                 player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Mastered Kaioken x10");
                              } else if (Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".SKHelp") == 3) {
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "SK5DONTTOUCH");
                                 Yamls.PlayerData.savePlayerData();
                                 color = Color.RED;
                                 color2 = Color.ORANGE;
                                 Fireworks.explosion(loc, color, color2);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SKHelp", 4);
                                 Yamls.PlayerData.savePlayerData();
                                 player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Mastered Kaioken x20");
                              } else if (Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".SKHelp") == 4) {
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "SK6DONTTOUCH");
                                 Yamls.PlayerData.savePlayerData();
                                 color = Color.RED;
                                 color2 = Color.ORANGE;
                                 Fireworks.explosion(loc, color, color2);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SKHelp", 5);
                                 Yamls.PlayerData.savePlayerData();
                                 player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Mastered Kaioken x50");
                              } else if (Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".SKHelp") == 5) {
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".ActiveForm", "SK7DONTTOUCH");
                                 Yamls.PlayerData.savePlayerData();
                                 color = Color.RED;
                                 color2 = Color.ORANGE;
                                 Fireworks.explosionSpecial(loc, color, color2);
                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SKHelp", -1);
                                 Yamls.PlayerData.savePlayerData();
                                 player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Mastered Kaioken x100");
                              } else {
                                 player.sendMessage(Messages.deform(i, PlayerManager.getFormLevel(player, i)));
                                 PlayerManager.Deform(player);
                              }
                           } else {
                              player.sendMessage(Messages.deform(i, PlayerManager.getFormLevel(player, i)));
                              PlayerManager.Deform(player);
                              NBTEditor.EditInt(player, "jrmcAlign", Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".Align"));
                           }
                           break;
                        }
                     }
                  } else {
                     for(i = 0; i < FormManager.getForms().size(); ++i) {
                        if (!Yamls.PlayerData.getPlayerData().getBoolean("PlayerData." + player.getUniqueId() + ".isAndroid") && !Yamls.PlayerData.getPlayerData().getBoolean("PlayerData." + player.getUniqueId() + ".isDemon") && !Yamls.PlayerData.getPlayerData().getBoolean("PlayerData." + player.getUniqueId() + ".isMajin") && PlayerManager.getSelectedForm(player) != null && PlayerManager.getSelectedForm(player).equalsIgnoreCase(FormManager.getFormName(i))) {
                           try {
                              Class.forName("me.dpohvar.powernbt.api.NBTManager");
                              if (!PlayerManager.ValidateRace(player, FormManager.getRaces(i))) {
                                 player.sendMessage(Messages.prefix + ChatColor.RED + "Debes estar en la carrera " + ChatColor.YELLOW + Messages.getDbcFormName(FormManager.getRaces(i)) + ChatColor.RED + " para usar esta forma!");
                              } else if (!PlayerManager.ValidateAlignment(player, FormManager.getAlignment(i))) {
                                 player.sendMessage(Messages.prefix + ChatColor.RED + "Debes estar en la alineación " + ChatColor.YELLOW + Messages.getAlignmentName(FormManager.getAlignment(i)) + ChatColor.RED + " para usar esta forma!");
                              } else if (GetStats.getLevel(player) < FormManager.getLevel(i)) {
                                 player.sendMessage(Messages.prefix + ChatColor.RED + "Su nivel debe ser " + ChatColor.YELLOW + FormManager.getLevel(i) + ChatColor.RED + " o mayor para usar esta forma!");
                              } else {
                                 PlayerManager.Transform(player, i);
                                 Good = false;
                                 Good1 = false;
                                 if (FormManager.getColor1(i) != null && FormManager.getColor1(i).toString() != "") {
                                    Good = true;
                                 }

                                 if (FormManager.getColor2(i) != null && FormManager.getColor2(i).toString() != "") {
                                    Good1 = true;
                                 }

                                 if (Good && Good1) {
                                    a = FormManager.getColor1(i);
                                    b = FormManager.getColor2(i);
                                    Color1 = Color.WHITE;
                                    Color2 = Color.BLACK;
                                    Color1 = utils.isColor(a);
                                    Color2 = utils.isColor(b);
                                    Fireworks.explosion(loc, Color1, Color2);
                                 }

                                 Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".Align", GetStats.getAlign(player));
                                 Yamls.PlayerData.savePlayerData();
                                 if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("GOD")) {
                                    NBTEditor.EditInt(player, "jrmcAlign", 57);
                                 }

                                 if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("SK")) {
                                    Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SKHelp", 0);
                                    Yamls.PlayerData.savePlayerData();
                                 }

                                 if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("FSSJ")) {
                                    Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".FSSJHelp", 0);
                                    Yamls.PlayerData.savePlayerData();
                                 }

                                 if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("SaiyanDay")) {
                                    Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".SaiyanDayHelp", 0);
                                    Yamls.PlayerData.savePlayerData();
                                 }

                                 player.sendMessage(Messages.transform(i, PlayerManager.getFormLevel(player, i)));
                              }
                           } catch (ClassNotFoundException var15) {
                              PlayerManager.Transform(player, i);
                              player.sendMessage(Messages.transform(i, PlayerManager.getFormLevel(player, i)));
                           }
                        }
                     }
                  }
               }
            }
         }
      }

   }

   @EventHandler
   public void onPlayerMove(PlayerMoveEvent event) {
      Player player = event.getPlayer();
      if (player.getLocation().getWorld().getName().equalsIgnoreCase("TimeChamber") && (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getZ() != event.getTo().getZ())) {
         NBTEditor.EditInt(player, "jrmcTpint", NBTEditor.GetInt(player, "jrmcTpint") + this.plugin.getConfig().getInt("DBCAddons.TimeChamber.TPGain") * overTime1);
         ++Time1;
         if (Time1 >= this.plugin.getConfig().getInt("DBCAddons.TimeChamber.TimeInSecondsBeforeOverTime") * 20 && this.plugin.getConfig().getInt("DBCAddons.TimeChamber.TPGainOverTime") >= 1) {
            overTime1 += this.plugin.getConfig().getInt("DBCAddons.TimeChamber.TPGainOverTime") - 1;
         }

         if (NBTEditor.GetInt(player, "jrmcTpint") >= 2000000001) {
            NBTEditor.EditInt(player, "jrmcTpint", 2000000000);
         }
      }

   }

   @EventHandler
   public void onChangeDim(PlayerChangedWorldEvent e) {
      overTime1 = 0;
      Time1 = 0;
      Particles.time = 0;
      Particles.overTime = 0;
   }

   @EventHandler
   public void onInventoryCLick(InventoryClickEvent e) {
      if (e.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "Menu de Transformaciones") || e.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + "Alpha " + ChatColor.AQUA + "Reborn " + ChatColor.RED + "forms Menu") || e.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "Final " + ChatColor.RED + "Stand " + ChatColor.RED + "forms Menu")) {
         e.setCancelled(true);
         MainMenu.Logic((Player)e.getWhoClicked(), e.getSlot());
      }

      if (e.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "Seleccionar Transformacion")) {
         e.setCancelled(true);
         SelectFormMenu.Logic((Player)e.getWhoClicked(), e.getSlot());
      }

      if (e.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "Panel de Control (WIP)") || e.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "Panel de Control")) {
         e.setCancelled(true);
         Main.Logic((Player)e.getWhoClicked(), e.getSlot());
      }

      for(int i = 0; i < FormManager.getForms().size(); ++i) {
         if (e.getInventory().getName().equalsIgnoreCase(FormManager.getColor(i) + "Forma " + FormManager.getFormattedName(i))) {
            e.setCancelled(true);
            FormMenu.Logic((Player)e.getWhoClicked(), e.getSlot(), i);
            break;
         }
      }

   }

   @EventHandler
   public void onPlayerDealDamageEvent(EntityDamageByEntityEvent e) {
      if (this.plugin.getConfig().getBoolean("Settings.Regen") && e.getDamager() instanceof Player) {
         Player damager = (Player)e.getDamager();
         if (Yamls.PlayerData.getPlayerData().getInt("PlayerData." + damager.getUniqueId() + ".Fuse") != 0) {
            ((Damageable)e.getEntity()).damage((double)Yamls.PlayerData.getPlayerData().getInt("PlayerData." + damager.getUniqueId() + ".Fuse") * 3.5D / 5000.0D / 60.0D * 3.5D * (double)PlayerManager.getRelease(damager));
         }

         int str;
         int i;
         double a;
         if (Yamls.PlayerData.getPlayerData().getBoolean("PlayerData." + damager.getUniqueId() + ".isMajin") || Yamls.PlayerData.getPlayerData().getInt("PlayerData." + damager.getUniqueId() + ".KFRace") == 1) {
            str = NBTEditor.GetInt(damager, "jrmcStrI");
            i = NBTEditor.GetInt(damager, "jrmcState");
            a = 1.0D;
            if (i == 0) {
               a = this.plugin.getConfig().getDouble("DBCAddedMultis.MajinBase");
            }

            if (i == 1) {
               a = this.plugin.getConfig().getDouble("DBCAddedMultis.MajinState1");
            }

            if (i == 2) {
               a = this.plugin.getConfig().getDouble("DBCAddedMultis.MajinState2");
            }

            if (i == 3) {
               a = this.plugin.getConfig().getDouble("DBCAddedMultis.MajinState3");
            }

            ((Damageable)e.getEntity()).damage((double)str / 100.0D * a * 3.5D * (double)PlayerManager.getRelease(damager));
         }

         if (Yamls.PlayerData.getPlayerData().getBoolean("PlayerData." + damager.getUniqueId() + ".isDemon") || Yamls.PlayerData.getPlayerData().getInt("PlayerData." + damager.getUniqueId() + ".KFRace") == 2) {
            str = NBTEditor.GetInt(damager, "jrmcStrI");
            i = NBTEditor.GetInt(damager, "jrmcState");
            a = 1.0D;
            if (i == 0) {
               a = this.plugin.getConfig().getDouble("DBCAddedMultis.DemonBase");
            }

            if (i == 1) {
               a = this.plugin.getConfig().getDouble("DBCAddedMultis.DemonState1");
            }

            if (i == 10) {
               a = this.plugin.getConfig().getDouble("DBCAddedMultis.DemonState2");
            }

            if (i == 7) {
               a = this.plugin.getConfig().getDouble("DBCAddedMultis.DemonBuff");
            }

            if (i == 15) {
               a = this.plugin.getConfig().getDouble("DBCAddedMultis.DemonState3");
            }

            ((Damageable)e.getEntity()).damage((double)str / 100.0D * a * 3.5D * (double)PlayerManager.getRelease(damager));
            ((LivingEntity)e.getEntity()).setNoDamageTicks(5);
         }

         if (Yamls.PlayerData.getPlayerData().getBoolean("PlayerData." + damager.getUniqueId() + ".isAndroid") || Yamls.PlayerData.getPlayerData().getInt("PlayerData." + damager.getUniqueId() + ".KFRace") == 3) {
            str = NBTEditor.GetInt(damager, "jrmcStrI");
            a = this.plugin.getConfig().getDouble("DBCAddedMultis.Android");
            ((Damageable)e.getEntity()).damage((double)str / 100.0D * a * 3.5D * (double)PlayerManager.getRelease(damager));
            ((LivingEntity)e.getEntity()).setNoDamageTicks(5);
         }

         str = GetStats.getSTR(damager);

         for(i = 0; i < FormManager.getForms().size(); ++i) {
            if (PlayerManager.isPlayerFormed(damager) && i == PlayerManager.getActiveForm(damager)) {
               Location loc = e.getEntity().getLocation();
               PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles("largeexplode", (float)loc.getX(), (float)loc.getY() + 1.0F, (float)loc.getZ(), 0.35F, 0.6F, 0.35F, 1.0F, 1);
               ((CraftPlayer)damager).getHandle().playerConnection.sendPacket(packet);
               PacketPlayOutWorldParticles packet1 = new PacketPlayOutWorldParticles("smoke", (float)loc.getX(), (float)loc.getY() + 1.0F, (float)loc.getZ(), 0.35F, 0.6F, 0.35F, 1.0F, 5);
               ((CraftPlayer)damager).getHandle().playerConnection.sendPacket(packet1);
               PacketPlayOutWorldParticles packet11 = new PacketPlayOutWorldParticles("crit", (float)loc.getX(), (float)loc.getY() + 1.0F, (float)loc.getZ(), 0.35F, 0.6F, 0.35F, 1.0F, 25);
               ((CraftPlayer)damager).getHandle().playerConnection.sendPacket(packet11);

               try {
                  Class.forName("me.dpohvar.powernbt.api.NBTManager");
                  if (PlayerManager.getFormLevel(damager, i) == 1 && PlayerManager.getStamina(damager) > 0) {
                     ((Damageable)e.getEntity()).damage((double)str * 1.5D * 3.5D * (FormManager.getDamageMultiplier(i, 1) - 1.0D) / 100.0D * (double)PlayerManager.getRelease(damager));
                  }

                  if (PlayerManager.getFormLevel(damager, i) == 2 && PlayerManager.getStamina(damager) > 0) {
                     ((Damageable)e.getEntity()).damage((double)str * 1.5D * 3.5D * (FormManager.getDamageMultiplier(i, 2) - 1.0D) / 100.0D * (double)PlayerManager.getRelease(damager));
                  }

                  if (PlayerManager.getFormLevel(damager, i) == 3 && PlayerManager.getStamina(damager) > 0) {
                     ((Damageable)e.getEntity()).damage((double)str * 1.5D * 3.5D * (FormManager.getDamageMultiplier(i, 3) - 1.0D) / 100.0D * (double)PlayerManager.getRelease(damager));
                  }

                  if (PlayerManager.getFormLevel(damager, i) == 4 && PlayerManager.getStamina(damager) > 0) {
                     ((Damageable)e.getEntity()).damage((double)str * 1.5D * 3.5D * (FormManager.getDamageMultiplier(i, 4) - 1.0D) / 100.0D * (double)PlayerManager.getRelease(damager));
                  }

                  if (PlayerManager.getFormLevel(damager, i) > 4 && PlayerManager.getStamina(damager) > 0) {
                     ((Damageable)e.getEntity()).damage((double)str * 1.5D * 3.5D * (FormManager.getDamageMultiplier(i, 5) - 1.0D) / 100.0D * (double)PlayerManager.getRelease(damager));
                  }

                  ((LivingEntity)e.getEntity()).setNoDamageTicks(5);
               } catch (ClassNotFoundException var12) {
                  if (PlayerManager.getFormLevel(damager, i) == 1) {
                     ((Damageable)e.getEntity()).damage(Double.parseDouble(String.valueOf(FormManager.getDamage(i, 1))));
                  }

                  if (PlayerManager.getFormLevel(damager, i) == 2) {
                     ((Damageable)e.getEntity()).damage(Double.parseDouble(String.valueOf(FormManager.getDamage(i, 2))));
                  }

                  if (PlayerManager.getFormLevel(damager, i) == 3) {
                     ((Damageable)e.getEntity()).damage(Double.parseDouble(String.valueOf(FormManager.getDamage(i, 3))));
                  }

                  if (PlayerManager.getFormLevel(damager, i) == 4) {
                     ((Damageable)e.getEntity()).damage(Double.parseDouble(String.valueOf(FormManager.getDamage(i, 4))));
                  }

                  if (PlayerManager.getFormLevel(damager, i) <= 4) {
                     break;
                  }
               }

               ((Damageable)e.getEntity()).damage(Double.parseDouble(String.valueOf(FormManager.getDamage(i, 5))));
               break;
            }
         }
      }

   }
}
