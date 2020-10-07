package org.KosmicMedia.KosmicForms;

import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.effect.AtomEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import net.minecraft.server.v1_7_R4.NBTTagInt;
import net.minecraft.server.v1_7_R4.PacketPlayOutWorldParticles;
import org.KosmicMedia.KosmicForms.util.*;
import org.apache.commons.lang.math.IntRange;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Particles implements Listener {
   public static MainClass plugin = (MainClass)MainClass.getPlugin(MainClass.class);
   public static ArrayList online = new ArrayList();
   public static final String Pain = "jrmcGyJ7dp";
   public static final String Heat = "jrmcEf8slc";
   public static final String KO = "jrmcHar4va";
   public static HashMap playerHPs = new HashMap();
   static Random rand = new Random();
   public static int time = 0;
   public static int overTime = 1;
   static int i;
   static int ki;
   static int i2 = 0;
   static float height;
   static float size;
   public static int efm = 0;

   public static void EUIstart(Player player) {
      Location loc = player.getLocation();
      Location loc2 = loc.add(0.0D, 1.0D, 0.0D);
      DynamicLocation loc3 = new DynamicLocation(loc2);
      AtomEffect atomEffect = new AtomEffect(MainClass.effectManager);
      atomEffect.setDynamicOrigin(loc3);
      atomEffect.orbitals = 4;
      atomEffect.particleCount = 3;
      atomEffect.type = EffectType.REPEATING;
      atomEffect.radiusNucleus = 1.0F;
      atomEffect.particleNucleus = ParticleEffect.SPELL_MOB;
      atomEffect.colorNucleus = Color.BLACK;
      atomEffect.radius = 2.0D;
      atomEffect.particleOrbital = ParticleEffect.FIREWORKS_SPARK;
      atomEffect.iterations = 20;
      atomEffect.autoOrient = true;
      atomEffect.start();
      if (!PlayerManager.isPlayerFormed(player)) {
         atomEffect.cancel();
      }

   }

   public static void startRunnables() {
      if (plugin.getConfig().getBoolean("Settings.Particles")) {
         particles();
      }

      if (plugin.getConfig().getBoolean("Settings.Damage")) {
         regen();
      }

      if (plugin.getConfig().getInt("Settings.Defense") != 0) {
         defence();
      }

      Disable();
      CreateConfig();
      OverCharge();
      if (plugin.getConfig().getInt("Settings.KiRegen") != ki) {
         ki = plugin.getConfig().getInt("Settings.KiRegen");
      }

   }

   public static void summonTransformParticle(Player player, String particle) {
      Location loc = player.getLocation();
      Iterator var4 = player.getWorld().getPlayers().iterator();

      while(var4.hasNext()) {
         Player on = (Player)var4.next();
         PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particle, (float)loc.getX(), (float)loc.getY() + 1.0F, (float)loc.getZ(), 0.35F, 0.6F, 0.35F, 1.0F, 300);
         ((CraftPlayer)on).getHandle().playerConnection.sendPacket(packet);
      }

   }

   public static void particles() {
      (new BukkitRunnable() {
         public void run() {
            int var10 = 0;

            Iterator var2;
            World w;
            for(var2 = Particles.plugin.getServer().getWorlds().iterator(); var2.hasNext(); var10 += w.getPlayers().size()) {
               w = (World)var2.next();
            }

            var2 = Particles.online.iterator();

            while(true) {
               Location loc;
               int race;
               Player player;
               do {
                  if (!var2.hasNext()) {
                     try {
                        Class.forName("me.dpohvar.powernbt.api.NBTManager");
                        Iterator var13 = Particles.online.iterator();

                        while(true) {
                           while(true) {
                              Player on;
                              do {
                                 if (!var13.hasNext()) {
                                    return;
                                 }

                                 on = (Player)var13.next();
                              } while(!PlayerManager.isPlayerFormed(on));

                              for(Particles.i = 0; Particles.i < FormManager.getForms().size(); ++Particles.i) {
                                 if (Particles.i == PlayerManager.getActiveForm(on)) {
                                    if (!FormManager.getStackWithDBCForms(Particles.i) && NBTEditor.GetInt(on, "jrmcState") > 0) {
                                       NBTEditor.EditInt(on, "jrmcState", 0);
                                       on.sendMessage(Messages.prefix + ChatColor.RED + "You cannot stack " + FormManager.getColor(Particles.i) + FormManager.getFormattedName(Particles.i) + " form" + ChatColor.RESET + ChatColor.RED + " with DBC forms!");
                                    }

                                    if (!FormManager.getStackWithDBCKaioken(Particles.i) && NBTEditor.GetInt(on, "jrmcState2") > 0) {
                                       NBTEditor.EditInt(on, "jrmcState2", 0);
                                       on.sendMessage(Messages.prefix + ChatColor.RED + "You cannot stack " + FormManager.getColor(Particles.i) + FormManager.getFormattedName(Particles.i) + " form" + ChatColor.RESET + ChatColor.RED + " with Kaioken!");
                                    }

                                    if (!FormManager.getStackWithDBCMystic(Particles.i) && NBTEditor.GetString(on, "jrmcStatusEff").contains("C")) {
                                       NBTEditor.EditString(on, "jrmcStatusEff", NBTEditor.GetString(on, "jrmcStatusEff").replace("C", ""));
                                       on.sendMessage(Messages.prefix + ChatColor.RED + "You cannot stack " + FormManager.getColor(Particles.i) + FormManager.getFormattedName(Particles.i) + " form" + ChatColor.RESET + ChatColor.RED + " with Mystic!");
                                    }

                                    if (!FormManager.getStackWithDBCUltraInstinct(Particles.i) && NBTEditor.GetString(on, "jrmcStatusEff").contains("N")) {
                                       NBTEditor.EditString(on, "jrmcStatusEff", NBTEditor.GetString(on, "jrmcStatusEff").replace("N", ""));
                                       on.sendMessage(Messages.prefix + ChatColor.RED + "You cannot stack " + FormManager.getColor(Particles.i) + FormManager.getFormattedName(Particles.i) + " form" + ChatColor.RESET + ChatColor.RED + " with the DBC Ultra Instinct!");
                                    }

                                    if (NBTEditor.GetInt(on, "jrmcRelease") < 1) {
                                       PlayerManager.Deform(on);
                                       on.sendMessage(Messages.deform(Particles.i, PlayerManager.getFormLevel(on, Particles.i)));
                                    }
                                    break;
                                 }
                              }
                           }
                        }
                     } catch (ClassNotFoundException var11) {
                        return;
                     }
                  }

                  player = (Player)var2.next();
                  loc = player.getLocation();
                  if (Yamls.PlayerData.playerscfg.getBoolean("PlayerData." + player.getUniqueId() + ".isMajin") || Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".KFRace") == 1) {
                     NBTEditor.EditInt(player, "jrmcBdy", NBTEditor.GetInt(player, "jrmcBdy") + 100 + GetStats.getCON(player) / 100);
                     Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState", 0);
                     Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState2", 0);
                     Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultStatus", "");
                     player.setFoodLevel(20);
                  }

                  if (Yamls.PlayerData.playerscfg.getBoolean("PlayerData." + player.getUniqueId() + ".invis") && !NBTEditor.GetString(player, "jrmcStatusEff").contains("I")) {
                     NBTEditor.EditString(player, "jrmcStatusEff", NBTEditor.GetString(player, "jrmcStatusEff") + "I");
                  }

                  race = NBTEditor.GetInt(player, "jrmcRace");
                  if (race == 1 || race == 2) {
                     Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".KFRace", 0);
                  }
               } while(!PlayerManager.isPlayerFormed(player));

               Particles.i = 0;
               boolean isFormGood = false;

               int var9;
               for(var9 = 0; var9 < FormManager.getForms().size(); ++var9) {
                  if (var9 == PlayerManager.getActiveForm(player)) {
                     Particles.i = var9;
                     isFormGood = true;
                     break;
                  }
               }

               if (!isFormGood) {
                  PlayerManager.Deform(player);
               }

               for(var9 = 0; var9 < var10; ++var9) {
                  if (FormManager.getStatusEffs(Particles.i) != "-1" && FormManager.getStatusEffs(Particles.i) != null && !NBTEditor.GetString(player, "jrmcStatusEff").contains(FormManager.getStatusEffs(Particles.i))) {
                     NBTEditor.EditString(player, "jrmcStatusEff", NBTEditor.GetString(player, "jrmcStatusEff") + FormManager.getStatusEffs(Particles.i));
                  }

                  if (!FormManager.getCustomHair(Particles.i).equals("-1") && FormManager.getCustomHair(Particles.i) != null) {
                     NBTEditor.EditString(player, "jrmcDNSH", FormManager.getCustomHair(Particles.i));
                  }

                  if (!FormManager.getHair(Particles.i).equals("-1") && FormManager.getHair(Particles.i) != null) {
                     PlayerManager.setHair(player, FormManager.getHair(Particles.i));
                  }

                  if (FormManager.getState(Particles.i) != -1 && FormManager.getState(Particles.i) != null) {
                     NBTEditor.Edit(player, "jrmcSaiRg", 0);
                     int state = FormManager.getState(Particles.i);
                     if (race == 0 && state <= 3) {
                        NBTEditor.EditJinsVar(player, "jrmcState", FormManager.getState(Particles.i));
                     } else if ((race == 1 || race == 2) && state <= 15) {
                        Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".KFRace", 0);
                        NBTEditor.EditJinsVar(player, "jrmcState", FormManager.getState(Particles.i));
                     } else if (race == 3 && state <= 3) {
                        NBTEditor.EditJinsVar(player, "jrmcState", FormManager.getState(Particles.i));
                     } else if (race == 4 && state <= 7) {
                        NBTEditor.EditJinsVar(player, "jrmcState", FormManager.getState(Particles.i));
                     } else {
                        NBTEditor.EditJinsVar(player, "jrmcState", FormManager.getState(Particles.i));
                     }
                  }

                  if (FormManager.getKaio(Particles.i) != -1 && FormManager.getKaio(Particles.i) != null) {
                     NBTEditor.EditInt(player, "jrmcState2", FormManager.getKaio(Particles.i));
                  }

                  if (FormManager.getParticle(Particles.i) != "-1") {
                     Iterator var15 = Particles.online.iterator();

                     while(var15.hasNext()) {
                        Player onx = (Player)var15.next();
                        PacketPlayOutWorldParticles packetall = new PacketPlayOutWorldParticles(FormManager.getParticle(Particles.i), (float)loc.getX(), (float)loc.getY() + 1.0F, (float)loc.getZ(), 0.2F, 0.5F, 0.2F, 0.05F, 5);
                        ((CraftPlayer)onx).getHandle().playerConnection.sendPacket(packetall);
                     }
                  }

                  if (FormManager.getFormName(Particles.i).equalsIgnoreCase("EUI")) {
                     Particles.EUIstart(player);
                     Particles.efm = 1;
                  }

                  if (NBTEditor.GetInt(player, "jrmcEf8slc") > 0) {
                     NBTEditor.EditInt(player, "jrmcEf8slc", 0);
                  }

                  if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("SaiyanDay")) {
                     if (!NBTEditor.GetString(player, "jrmcStatusEff").contains("U")) {
                        NBTEditor.EditString(player, "jrmcStatusEff", NBTEditor.GetString(player, "jrmcStatusEff") + "U");
                     }

                     if (!NBTEditor.GetString(player, "jrmcStatusEff").contains("Z")) {
                        NBTEditor.EditString(player, "jrmcStatusEff", NBTEditor.GetString(player, "jrmcStatusEff") + "Z");
                     }
                  }

                  if (PlayerManager.getSelectedForm(player).equalsIgnoreCase("SaiyanDay2DONTTOUCH")) {
                     if (NBTEditor.GetString(player, "jrmcStatusEff").contains("U")) {
                        NBTEditor.EditString(player, "jrmcStatusEff", NBTEditor.GetString(player, "jrmcStatusEff").replace("U", ""));
                     }

                     if (NBTEditor.GetString(player, "jrmcStatusEff").contains("Z")) {
                        NBTEditor.EditString(player, "jrmcStatusEff", NBTEditor.GetString(player, "jrmcStatusEff").replace("Z", ""));
                     }
                  }
               }
            }
         }
      }).runTaskTimerAsynchronously(plugin, 0L, 3L);
   }

   public static void regen() {
      (new BukkitRunnable() {
         public void run() {
            Iterator var1 = Particles.online.iterator();

            while(true) {
               while(var1.hasNext()) {
                  Player on = (Player)var1.next();

                  for(int i = 0; i < FormManager.getForms().size(); ++i) {
                     if (PlayerManager.isPlayerFormed(on) && i == PlayerManager.getActiveForm(on)) {
                        NBTEditor.Edit(on, "jrmcStamina", NBTEditor.GetInt(on, "jrmcStamina") + GetStats.getMaxStam(on) * (FormManager.getStaminaRegen(i, PlayerManager.getFormLevel(on, i)) / 100));
                        PlayerManager.setHealthCapped(on, (int)((long)PlayerManager.getHealth(on) + Math.round(PlayerManager.getMaxHealth(on) * FormManager.getRegen(i, PlayerManager.getFormLevel(on, i)))));
                        break;
                     }
                  }
               }

               return;
            }
         }
      }).runTaskTimerAsynchronously(plugin, 0L, 100L);
   }

   public static void KiRegen() {
      (new BukkitRunnable() {
         public void run() {
            Iterator var1 = Particles.online.iterator();

            while(var1.hasNext()) {
               Player on = (Player)var1.next();
               NBTEditor.Edit(on, "jrmcEnrgy", GetStats.getCurEnrgy(on) + GetStats.getMaxEnrgy(on) * FormManager.getKiRegen(Particles.i, PlayerManager.getFormLevel(on, Particles.i)) / 100);
            }

         }
      }).runTaskTimerAsynchronously(plugin, 0L, (long)(60 / ki));
   }

   public static void defence() {
      int x = 1;
      if (plugin.getConfig().getInt("Settings.Defence") == 1) {
         x = 2;
      }

      (new BukkitRunnable() {
         public void run() {
            Iterator var1 = Particles.online.iterator();

            while(true) {
               while(true) {
                  while(true) {
                     Player player;
                     do {
                        if (!var1.hasNext()) {
                           return;
                        }

                        player = (Player)var1.next();
                     } while(player.getGameMode() == GameMode.CREATIVE);

                     int bdy = NBTEditor.GetInt(player, "jrmcBdy");
                     if (PlayerManager.isPlayerFormed(player)) {
                        if (!Particles.playerHPs.containsKey(player.getName())) {
                           Particles.playerHPs.put(player.getName(), bdy);
                        } else {
                           if (bdy < (Integer)Particles.playerHPs.get(player.getName())) {
                              for(int i = 0; i < FormManager.getForms().size(); ++i) {
                                 if (i == PlayerManager.getActiveForm(player)) {
                                    int damage = (Integer)Particles.playerHPs.get(player.getName()) - NBTEditor.GetInt(player, "jrmcBdy");
                                    double x2 = (double)damage * FormManager.getDefenses(i, 5);
                                    IntRange x4 = new IntRange(0, (double)(GetStats.getDEX(player) / 2000 + 50) * FormManager.getDodgeChances(i, PlayerManager.getFormLevel(player, i)) - 0.01D);
                                    int x3 = Particles.rand.nextInt(100);
                                    if (x4.containsInteger(x3)) {
                                       NBTEditor.EditInt(player, "jrmcBdy", (Integer)Particles.playerHPs.get(player.getName()));
                                       if (damage > 500) {
                                          NBTTagCompound nbtTagCompound = new NBTTagCompound();
                                          ((CraftPlayer)player).getHandle().e(nbtTagCompound);
                                          nbtTagCompound.getCompound("JRMCEP").set("blocking", new NBTTagInt(1));
                                          ((CraftPlayer)player).getHandle().f(nbtTagCompound);
                                       }
                                    } else {
                                       NBTEditor.EditDouble(player, "jrmcBdy", (double)(Integer)Particles.playerHPs.get(player.getName()) - x2);
                                    }
                                    break;
                                 }
                              }

                              Particles.playerHPs.remove(player.getName());
                              Particles.playerHPs.put(player.getName(), NBTEditor.GetInt(player, "jrmcBdy"));
                           }

                           Particles.playerHPs.remove(player.getName());
                           Particles.playerHPs.put(player.getName(), NBTEditor.GetInt(player, "jrmcBdy"));
                        }
                     } else {
                        Particles.playerHPs.remove(player.getName());
                        Particles.playerHPs.put(player.getName(), bdy);
                     }
                  }
               }
            }
         }
      }).runTaskTimer(plugin, 0L, (long)x);
   }

   public static void CreateConfig() {
      (new BukkitRunnable() {
         public void run() {
            ConfigManager.Create();
         }
      }).runTaskLater(plugin, 400L);
   }

   public static void Disable() {
      (new BukkitRunnable() {
         public void run() {
            Iterator var1 = Particles.online.iterator();

            while(var1.hasNext()) {
               Player player = (Player)var1.next();
               if (Yamls.PlayerData.playerscfg.getBoolean("PlayerData." + player.getUniqueId() + ".isDemon") || Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".KFRace") == 2) {
                  NBTEditor.EditInt(player, "jrmcStamina", 500000);
                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState", 0);
                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState2", 0);
                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultStatus", "");
                  player.setFoodLevel(20);
                  NBTEditor.EditInt(player, "jrmcAlign", 10);
                  NBTEditor.EditInt(player, "jrmcAuraColor", 0);
                  if (NBTEditor.GetInt(player, "jrmcState") == 0) {
                     NBTEditor.EditString(player, "jrmcDNS", "0000c00188td410018owf0rgre0000000000000002187Gl187Gl");
                     NBTEditor.EditInt(player, "jrmcRace", 0);
                  }

                  if (NBTEditor.GetInt(player, "jrmcRace") == 0) {
                     if (NBTEditor.GetInt(player, "jrmcState") == 1) {
                        NBTEditor.EditString(player, "jrmcDNS", "0000c00188td410018owf0rgre0000000000000002187Gl187Gl");
                     }

                     if (NBTEditor.GetInt(player, "jrmcState") == 2) {
                        NBTEditor.EditString(player, "jrmcDNS", "0200c00047ZZ4100047ZZ047ZZ0000000000000005047ZZ047ZZ");
                        NBTEditor.EditInt(player, "jrmcRace", 2);
                        NBTEditor.EditInt(player, "jrmcState", 7);
                     }

                     if (NBTEditor.GetInt(player, "jrmcState") == 3) {
                        NBTEditor.EditInt(player, "jrmcRace", 2);
                        NBTEditor.EditInt(player, "jrmcState", 15);
                     }
                  }

                  if (NBTEditor.GetInt(player, "jrmcRace") == 2) {
                     if (NBTEditor.GetInt(player, "jrmcState") == 8) {
                        NBTEditor.EditString(player, "jrmcDNS", "0200c00188td410018owf0rgre0000000000000002187Gl187Gl");
                        NBTEditor.EditInt(player, "jrmcRace", 2);
                        NBTEditor.EditInt(player, "jrmcState", 10);
                     }

                     if (NBTEditor.GetInt(player, "jrmcState") == 10) {
                        NBTEditor.EditString(player, "jrmcDNS", "0200c00188td410018owf0rgre0000000000000002187Gl187Gl");
                        NBTEditor.EditInt(player, "jrmcRace", 2);
                     }

                     if (NBTEditor.GetInt(player, "jrmcState") == 15) {
                        NBTEditor.EditInt(player, "jrmcRace", 2);
                        NBTEditor.EditString(player, "jrmcDNS", "0200c00187Gl410018owf0rgre0000000000000002187Gl187Gl");
                     }
                  }

                  NBTEditor.EditString(player, "jrmcDNSH", "822854491965000045505660500000454954545050822845521865000043547650500000475078505000005050765050000050507850500000475074505000004950765050000050507650500000475074505000004950765050000047507650500000675036505000004945525050000049455250500000504152505000004945565050000049475650500000494754505000004947565050000049455650500000494958505000004950765050000049507650500000505078505000004950785050000080494950500000804949505000008049495050000080494950500000494949505000004949495050000049494950500000494949505000005550505050000055505050500000555050505000005050505050000045507150500000495474505000004960745050000049547650500000505674505000004958725050000050567450500000505076505000005058765050000049567250500000505474505000005049765050000049547150500000495876505000004954765050000050497650500020");
                  if (NBTEditor.GetString(player, "jrmcStatusEff").contains("J")) {
                     NBTEditor.EditString(player, "jrmcStatusEff", NBTEditor.GetString(player, "jrmcStatusEff").replace("J", ""));
                  }
               }

               if (NBTEditor.GetInt(player, "jrmcRace") == 0 && Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".DefaultState") >= 4) {
                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState", 0);
               }

               if ((NBTEditor.GetInt(player, "jrmcRace") == 1 || NBTEditor.GetInt(player, "jrmcRace") == 2) && Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".DefaultState") >= 16) {
                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState", 0);
               }

               if (NBTEditor.GetInt(player, "jrmcRace") == 3 && Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".DefaultState") >= 4) {
                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState", 0);
               }

               if (NBTEditor.GetInt(player, "jrmcRace") == 4 && Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".DefaultState") >= 8) {
                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState", 0);
               }

               if (Yamls.PlayerData.playerscfg.getBoolean("PlayerData." + player.getUniqueId() + ".isAndroid") || Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".KFRace") == 3) {
                  NBTEditor.EditInt(player, "jrmcEnrgy", 5000000);
                  NBTEditor.EditInt(player, "jrmcStamina", 500000);
                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState", 0);
                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState2", 0);
                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultStatus", "");
                  player.setFoodLevel(20);
               }

               if (NBTEditor.GetInt(player, "jrmcAccept") == 0) {
                  if (PlayerManager.isPlayerFormed(player)) {
                     PlayerManager.Deform(player);
                  }

                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".KFRace", 0);
                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState", 0);
                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState2", 0);
                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultStatus", "");
               }

               if (Particles.plugin.getConfig().getBoolean("DBCFixes.DisableSwoop") && NBTEditor.GetString(player, "jrmcStatusEff").contains("S")) {
                  NBTEditor.EditInt(player, "jrmcStamina", 490000);
               }

               if (Particles.plugin.getConfig().getBoolean("DBCFixes.DisableKO") && NBTEditor.GetInt(player, "jrmcHar4va") >= 1) {
                  NBTEditor.EditInt(player, "jrmcHar4va", 0);
               }

               if (Particles.plugin.getConfig().getBoolean("DBCFixes.DisablePain") && NBTEditor.GetInt(player, "jrmcGyJ7dp") >= 1) {
                  NBTEditor.EditInt(player, "jrmcGyJ7dp", 0);
               }

               if (NBTEditor.GetInt(player, "jrmcState") == 8 && Yamls.PlayerData.getPlayerData().getInt("PlayerData." + player.getUniqueId() + ".KFRace") == 0 && NBTEditor.GetInt(player, "jrmcSaiRG") >= 90) {
                  NBTEditor.EditInt(player, "jrmcState", 14);
               }

               if (Yamls.PlayerData.playerscfg.getBoolean("PlayerData." + player.getUniqueId() + ".isMajin") && !NBTEditor.GetString(player, "jrmcStatusEff").contains("J")) {
                  NBTEditor.EditString(player, "jrmcStatusEff", NBTEditor.GetString(player, "jrmcStatusEff") + "J");
               }

               if (!Yamls.PlayerData.getPlayerData().getBoolean("PlayerData." + player.getUniqueId() + ".isAndroid") && !Yamls.PlayerData.getPlayerData().getBoolean("PlayerData." + player.getUniqueId() + ".isDemon") && !Yamls.PlayerData.getPlayerData().getBoolean("PlayerData." + player.getUniqueId() + ".isMajin")) {
                  Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".KFRace", 0);
               }

               player.setFoodLevel(2000);
               if (Particles.plugin.getConfig().getBoolean("DBCFixes.FixNegativeTP")) {
                  if (NBTEditor.GetInt(player, "jrmcTpint") < 0) {
                     NBTEditor.EditInt(player, "jrmcTpint", 0);
                  }

                  if (NBTEditor.GetInt(player, "jrmcState") < 0) {
                     NBTEditor.EditInt(player, "jrmcState", 0);
                  }

                  if (NBTEditor.GetInt(player, "jrmcState2") < 0) {
                     NBTEditor.EditInt(player, "jrmcState2", 0);
                  }
               }
            }

         }
      }).runTaskTimerAsynchronously(plugin, 1L, 1L);
   }

   public static void OverCharge() {
      (new BukkitRunnable() {
         public void run() {
            Iterator var1 = Particles.online.iterator();

            while(var1.hasNext()) {
               Player player = (Player)var1.next();
               if (Particles.plugin.getConfig().getBoolean("DBCAddons.125Release") && NBTEditor.GetString(player, "jrmcStatusEff").contains("A")) {
                  if (!player.isSneaking()) {
                     if (NBTEditor.GetInt(player, "jrmcRelease") >= 100 && NBTEditor.GetInt(player, "jrmcRelease") < 125 && NBTEditor.GetString(player, "jrmcSSlts").contains("GF")) {
                        NBTEditor.EditInt(player, "jrmcRelease", NBTEditor.GetInt(player, "jrmcRelease") + 5);
                     }
                  } else {
                     NBTEditor.EditString(player, "jrmcStatusEff", NBTEditor.GetString(player, "jrmcStatusEff").replace("A", ""));
                     if (NBTEditor.GetInt(player, "jrmcRelease") > 5) {
                        NBTEditor.EditInt(player, "jrmcRelease", NBTEditor.GetInt(player, "jrmcRelease") - 5);
                     }
                  }
               }
            }

         }
      }).runTaskTimerAsynchronously(plugin, 0L, 20L);
   }
}
