package org.KosmicMedia.KosmicForms.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class CustomSkullsUtility {
   public static ItemStack getSkullItemStack(int amount, String playerName, List Lore, String displayName) {
      ItemStack s = new ItemStack(397, amount);
      s.setDurability((short)3);
      SkullMeta meta = (SkullMeta)s.getItemMeta();
      meta.setDisplayName(displayName);
      meta.setOwner(playerName);
      meta.setLore(Lore);
      s.setItemMeta(meta);
      return s;
   }

   public static ItemStack getSkullItemStack(int amount, byte data) {
      amount = Math.max(0, amount);
      return new ItemStack(397, amount, (short)0, data);
   }
}
