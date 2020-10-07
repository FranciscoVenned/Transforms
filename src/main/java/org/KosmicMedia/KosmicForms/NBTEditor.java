package org.KosmicMedia.KosmicForms;


import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class NBTEditor {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);

   public static void Edit(Player p, String NBTkey, Object NBTvalue) {
      NBTCompound Forgadata = NBTManager.getInstance().readForgeData(p);
      NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
      PlayerPersisted.put(NBTkey, NBTvalue);
      Forgadata.put("PlayerPersisted", PlayerPersisted);
      NBTManager.getInstance().writeForgeData(p, Forgadata);
   }

   public static int GetInt(Player p, String NBTkey) {
      NBTCompound Forgadata = NBTManager.getInstance().readForgeData(p);
      NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
      int out = PlayerPersisted.getInt(NBTkey);
      return out;
   }

   public static String GetString(Player p, String NBTkey) {
      NBTCompound Forgadata = NBTManager.getInstance().readForgeData(p);
      NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
      String out = PlayerPersisted.getString(NBTkey);
      return out;
   }

   public static void setState(Player player, int State) {
      NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
      NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
      PlayerPersisted.put("jrmcState", State);
      Forgadata.put("PlayerPersisted", PlayerPersisted);
      NBTManager.getInstance().writeForgeData(player, Forgadata);
   }

   public static void EditInt(Player player, String NBTkey, int NBTvalue) {
      NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
      NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
      PlayerPersisted.put(NBTkey, NBTvalue);
      Forgadata.put("PlayerPersisted", PlayerPersisted);
      NBTManager.getInstance().writeForgeData(player, Forgadata);
   }

   public static void EditDouble(Player player, String NBTkey, Double NBTvalue) {
      NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
      NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
      PlayerPersisted.put(NBTkey, NBTvalue);
      Forgadata.put("PlayerPersisted", PlayerPersisted);
      NBTManager.getInstance().writeForgeData(player, Forgadata);
   }

   public static void EditRootInt(Player player, String Folder, String NBTkey, Object NBTvalue) {
      NBTCompound Root = NBTManager.getInstance().read(player);
      NBTCompound Folder1 = Root.getCompound(Folder);
      Folder1.put(NBTkey, NBTvalue);
      Root.put(Folder, Folder1);
      NBTManager.getInstance().write(player, Root);
   }

   public static void EditJinsVar(Player player, String NBTKey, int NBTvalue) {
      NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
      NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
      PlayerPersisted.put(NBTKey, NBTvalue);
      Forgadata.put("PlayerPersisted", PlayerPersisted);
      NBTManager.getInstance().writeForgeData(player, Forgadata);
   }

   public static int GetRootInt(Player player, String Folder, String NBTkey) {
      NBTCompound Root = NBTManager.getInstance().read(player);
      return Root.getCompound(Folder).getInt(NBTkey);
   }

   public static void EditState(Player player, int NBTvalue) {
      NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
      NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
      PlayerPersisted.put("jrmcState", NBTvalue);
      Forgadata.put("PlayerPersisted", PlayerPersisted);
      NBTManager.getInstance().writeForgeData(player, Forgadata);
   }

   public static void EditString(Player player, String NBTkey, String NBTvalue) {
      NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
      NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
      PlayerPersisted.put(NBTkey, NBTvalue);
      Forgadata.put("PlayerPersisted", PlayerPersisted);
      NBTManager.getInstance().writeForgeData(player, Forgadata);
   }
}
