package org.KosmicMedia.KosmicForms;

import net.minecraft.server.v1_7_R4.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class Methods {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);

   public static ItemStack createItemStack(Material material, int damage, String name, String lore) {
      ItemStack itemStack = new ItemStack(material, 1, (short)damage);
      ItemMeta itemStackMeta = itemStack.getItemMeta();
      itemStackMeta.setDisplayName(name);
      itemStackMeta.setLore(Arrays.asList(lore));
      itemStack.setItemMeta(itemStackMeta);
      return itemStack;
   }

   public static ItemStack createItemStack(int material, int damage, String name, String lore) {
      ItemStack itemStack = new ItemStack(material, 1, (short)damage);
      ItemMeta itemStackMeta = itemStack.getItemMeta();
      itemStackMeta.setDisplayName(name);
      itemStackMeta.setLore((List)null);
      itemStackMeta.setLore(Arrays.asList(lore));
      itemStack.setItemMeta(itemStackMeta);
      return itemStack;
   }

   public static ItemStack createItemStack(Material material, int damage, String name, ItemMeta lore) {
      ItemStack itemStack = new ItemStack(material, 1, (short)damage);
      ItemMeta itemStackMeta = itemStack.getItemMeta();
      itemStackMeta.setDisplayName(name);
      itemStackMeta.setLore((List)null);
      itemStackMeta.setLore((List)lore);
      itemStack.setItemMeta(itemStackMeta);
      return itemStack;
   }

   public static ItemStack createItemStackWithGlow(Material material, int damage, String name, String lore) {
      ItemStack itemStack = new ItemStack(material, 1, (short)damage);
      ItemMeta itemStackMeta = itemStack.getItemMeta();
      itemStackMeta.setDisplayName(name);
      itemStackMeta.setLore(Arrays.asList(lore));
      itemStack.setItemMeta(itemStackMeta);
      itemStack = addGlow(itemStack);
      return itemStack;
   }

   public static ItemStack addGlow(ItemStack item) {
      net.minecraft.server.v1_7_R4.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
      NBTTagCompound tag = null;
      if (!nmsStack.hasTag()) {
         tag = new NBTTagCompound();
         nmsStack.setTag(tag);
      }

      if (tag == null) {
         tag = nmsStack.getTag();
      }

      NBTTagList ench = new NBTTagList();
      tag.set("ench", ench);
      nmsStack.setTag(tag);
      return CraftItemStack.asCraftMirror(nmsStack);
   }

   private static double fusionHelp(String form, double dmg) {
      double a = dmg * 3.5D * ((double)plugin.getConfig().getInt("FormDamages." + form + ".Level5") - 1.0D);
      return a;
   }

   public static void sendActionBar(Player player, String string) {
      String stringa = string.replace("_", " ");
      String s = ChatColor.translateAlternateColorCodes('&', stringa);
      IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + s + "\"}");
      PacketPlayOutChat bar = new PacketPlayOutChat(icbc);
      ((CraftPlayer)player).getHandle().playerConnection.sendPacket(bar);
   }

   private static String lowerCaseString(String string, String string2) {
      return string.toLowerCase().indexOf(string2.toLowerCase()) == -1 ? string : string.replaceAll(string.substring(string.toLowerCase().indexOf(string2.toLowerCase()), string.toLowerCase().indexOf(string2.toLowerCase()) + string2.length()), string2);
   }

   public static boolean getLookingAt(Player player, LivingEntity living) {
      AxisAlignedBB box = ((CraftLivingEntity)living).getHandle().boundingBox;

      for(double y = box.b; y <= box.e; y += 0.3D) {
         for(double x = box.a; x <= box.d; x += 0.3D) {
            for(double z = box.c; z <= box.f; z += 0.3D) {
               if ((new Location(living.getWorld(), x, y, z)).toVector().subtract(player.getLocation().toVector()).normalize().dot(player.getLocation().getDirection()) > 0.89D) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static String f_namegen(String s1, String s2) {
      return s1.substring(0, s1.length() / 2) + s2.substring(s2.length() / 2);
   }

   public static boolean getLookingCloselyAt(Player player, LivingEntity living) {
      AxisAlignedBB box = ((CraftLivingEntity)living).getHandle().boundingBox;

      for(double y = box.b; y <= box.e; y += 0.3D) {
         for(double x = box.a; x <= box.d; x += 0.3D) {
            for(double z = box.c; z <= box.f; z += 0.3D) {
               double d = (new Location(living.getWorld(), x, y, z)).toVector().subtract(player.getLocation().toVector()).normalize().dot(player.getLocation().getDirection());
               if (d > 0.83D && d < 0.9D) {
                  return true;
               }
            }
         }
      }

      return false;
   }
}
