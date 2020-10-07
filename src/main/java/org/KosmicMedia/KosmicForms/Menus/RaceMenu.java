package org.KosmicMedia.KosmicForms.Menus;

import org.KosmicMedia.KosmicForms.MainClass;
import org.KosmicMedia.KosmicForms.MainMenu;
import org.KosmicMedia.KosmicForms.Methods;
import org.KosmicMedia.KosmicForms.NBTEditor;
import org.KosmicMedia.KosmicForms.util.Messages;
import org.KosmicMedia.KosmicForms.util.PlayerManager;
import org.KosmicMedia.KosmicForms.util.Yamls;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class RaceMenu implements Listener {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);

   public RaceMenu(Plugin plugin) {
      plugin.getServer().getPluginManager().registerEvents(this, plugin);
   }

   public static void openMenu(Player player) {
      Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.LIGHT_PURPLE + "Race Menu");
      ItemStack d = Methods.createItemStack(Material.WOOL, 4, ChatColor.GOLD + "Human", (String)"");
      ItemStack s = Methods.createItemStack(Material.WOOL, 2, ChatColor.LIGHT_PURPLE + "Majin", (String)"TP Cost: 1,000,000,000");
      inv.setItem(13, s);
      ItemStack j = Methods.createItemStack(Material.WOOL, 14, ChatColor.DARK_RED + "Demon", (String)"TP Cost: 1,000,000,000");
      inv.setItem(10, j);
      ItemStack k = Methods.createItemStack(Material.WOOL, 4, ChatColor.DARK_RED + "Android", (String)"TP Cost: 1,000,000,000");
      inv.setItem(16, k);
      ItemStack f = Methods.createItemStack(Material.WOOL, 12, ChatColor.YELLOW + "Saiyan", (String)"");
      ItemStack g = Methods.createItemStack(Material.WOOL, 13, ChatColor.DARK_GREEN + "Namekian", (String)"");
      ItemStack h = Methods.createItemStack(Material.WOOL, 0, ChatColor.WHITE + "Arcosian", (String)"");
      ItemStack a = Methods.createItemStack(Material.WOOL, 14, ChatColor.RED + "Reincarnate Character", (String)"");
      ItemStack exit = Methods.createItemStack(Material.REDSTONE, 0, ChatColor.RED + "Salir", (String)"");
      inv.setItem(4, a);
      inv.setItem(22, exit);
      player.openInventory(inv);
   }

   @EventHandler
   public void onInventoryCLick(InventoryClickEvent e) {
      Player player = (Player)e.getWhoClicked();
      if (e.getInventory().getName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Race Menu")) {
         e.setCancelled(true);
         if (e.getSlot() == 13) {
            if (NBTEditor.GetInt(player, "jrmcTpint") >= 1000000000) {
               PlayerManager.Deform(player);
               NBTEditor.EditInt(player, "jrmcState", 0);
               NBTEditor.EditInt(player, "jrmcRace", 0);
               NBTEditor.EditInt(player, "jrmcTpint", NBTEditor.GetInt(player, "jrmcTpint") - 1000000000);
               NBTEditor.EditString(player, "jrmcDNS", "0000c0018jbH410018j3C0rgre00000000000002000U6770U677");
               NBTEditor.EditString(player, "jrmcDNSH", "008080185050000018191950500000191919505000001819195050000050505550500000505055505000005050555050000050505550500000505055505000015050505050000050505550500000505055505000005050555050000050505550500000505045505000005050455050000050504550500000505045505000005050455050000150505050500000505045505000005050455050000050504550500000505045505000005550505050000078505050500000785050505000005550505050000055505050500000745050505000007450505050000055505050500000555050505000007150505050000071505050500000555050505000005550505050000055505050500000555050505000005550505050000032505050500000255050505000002550505050000034505050500000305050505000002350505050000023505050500000385050505000003450505050009719345067540097193249675000003450505050000069505050500000195050505000001950505050000028505050500020");
               player.setResourcePack("https://www.dropbox.com/s/h0wvqrsu3za1enq/Male%20Majin.zip?dl=1");
               NBTEditor.EditString(player, "jrmcSSltX", "TR0");
               Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".isMajin", true);
               Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".isDemon", false);
               Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".isAndroid", false);
               player.sendMessage(Messages.prefix + ChatColor.LIGHT_PURPLE + "Usted es ahora un Majin!");
            } else {
               player.sendMessage(ChatColor.RED + "No tienes suficiente TP");
            }
         }

         if (e.getSlot() == 10) {
            if (NBTEditor.GetInt(player, "jrmcTpint") >= 1000000000) {
               PlayerManager.Deform(player);
               NBTEditor.EditInt(player, "jrmcState", 0);
               NBTEditor.EditInt(player, "jrmcRace", 0);
               NBTEditor.EditInt(player, "jrmcTpint", NBTEditor.GetInt(player, "jrmcTpint") - 1000000000);
               NBTEditor.EditString(player, "jrmcDNS", "0000c00188td410018owf0rgre0000000000000002187Gl187Gl");
               NBTEditor.EditString(player, "jrmcDNSH", "822854491965000045505660500000454954545050822845521865000043547650500000475078505000005050765050000050507850500000475074505000004950765050000050507650500000475074505000004950765050000047507650500000675036505000004945525050000049455250500000504152505000004945565050000049475650500000494754505000004947565050000049455650500000494958505000004950765050000049507650500000505078505000004950785050000080494950500000804949505000008049495050000080494950500000494949505000004949495050000049494950500000494949505000005550505050000055505050500000555050505000005050505050000045507150500000495474505000004960745050000049547650500000505674505000004958725050000050567450500000505076505000005058765050000049567250500000505474505000005049765050000049547150500000495876505000004954765050000050497650500020");
               player.setResourcePack("https://www.dropbox.com/s/0o6rkma6mhzi2cn/MaleDemon.zip?dl=1");
               NBTEditor.EditString(player, "jrmcSSltX", "TR0");
               Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".isMajin", false);
               Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".isDemon", true);
               Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".isAndroid", false);
               player.sendMessage(Messages.prefix + ChatColor.DARK_RED + "Usted es ahora un Demon!");
            } else {
               player.sendMessage(ChatColor.RED + "No tienes suficiente TP");
            }
         }

         if (e.getSlot() == 16) {
            if (NBTEditor.GetInt(player, "jrmcTpint") >= 1000000000) {
               PlayerManager.Deform(player);
               NBTEditor.EditInt(player, "jrmcState", 0);
               NBTEditor.EditString(player, "jrmcSSltX", (String)null);
               NBTEditor.EditInt(player, "jrmcTpint", NBTEditor.GetInt(player, "jrmcTpint") - 1000000000);
               Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".isMajin", false);
               Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".isDemon", false);
               Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".isAndroid", true);
               player.sendMessage(Messages.prefix + ChatColor.GOLD + "Usted es ahora un Android!");
               player.setResourcePack("https://www.dropbox.com/s/eac1hr7cma3kzjl/Blank.zip?dl=1");
            } else {
               player.sendMessage(ChatColor.RED + "No tienes suficiente TP");
            }
         }

         if (e.getSlot() == 4) {
            PlayerManager.Deform(player);
            boolean b4 = player.isOp();
            Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".KFRace", 0);
            Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".isMajin", false);
            Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".isDemon", false);
            Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".isAndroid", false);
            Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState", 0);
            Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultState2", 0);
            Yamls.PlayerData.getPlayerData().set("PlayerData." + player.getUniqueId() + ".DefaultStatus", "");
            player.setOp(true);
            player.chat("/jrmcrei " + player.getName() + " 100 true true");
            player.setOp(b4);
            NBTEditor.EditString(player, "jrmcSSltX", "TR0");
            player.setResourcePack("https://www.dropbox.com/s/eac1hr7cma3kzjl/Blank.zip?dl=1");
         }

         if (e.getSlot() == 22) {
            MainMenu.openMenu(player);
         }
      }

   }
}
