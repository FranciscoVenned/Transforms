package org.KosmicMedia.KosmicForms.util;

import org.KosmicMedia.KosmicForms.FormManager;
import org.KosmicMedia.KosmicForms.MainClass;
import org.KosmicMedia.KosmicForms.forms.Form;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class Messages {
   static Plugin plugin = MainClass.getPlugin(MainClass.class);
   public static String prefix;
   static String version;

   public static void helpMessage(CommandSender player) {
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2-------=======Galaxy Forms Help=======-------"));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/kMenu &7- &9Abra el menú de formularios."));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/KosmicForms &3GiveTP Add &e<Form> <Amount> {Player} &7- &9Añadir formulario TP a un jugador."));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/KosmicForms &3GiveTP Set &e<Form> <Amout> {Player} &7- &9Establecer la cantidad de TP de forma de jugador."));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/KosmicForms &3SetForm &e<Form> <Level> {Player} &7- &9Establecer el nivel de forma de un jugador."));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/KosmicForms &3Get &e<HairColor/AuraColor/EyesColor/StatusEffects/State/Kaio> {Player} &7- &9Obtener los valores de un jugador."));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/KosmicForms &3GetItem &7- &9Da un elemento de item!"));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/KosmicForms &3Race &7- &9Abre el menú de carreras."));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/KosmicForms &3CP &7- &9Abre el panel de control."));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2/KosmicForms &3Reload &7- &9Recarga los archivos de configuración y datos."));
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&oVersion: " + version));
   }

   public static String getDbcFormName(int formNumber) {
      String name = ChatColor.DARK_RED + "" + ChatColor.BOLD + "";
      if (formNumber == 0) {
         name = "Humano";
      }

      if (formNumber == 1) {
         name = "Saiyan";
      }

      if (formNumber == 2) {
         name = "Half Saiyan";
      }

      if (formNumber == 3) {
         name = "Namekian";
      }

      if (formNumber == 4) {
         name = "Arcosian";
      }

      return name;
   }

   public static String getAlignmentName(int formNumber) {
      String name = ChatColor.DARK_RED + "" + ChatColor.BOLD + "";
      if (formNumber == 0) {
         name = ChatColor.DARK_RED + "Malvado";
      }

      if (formNumber == 1) {
         name = ChatColor.LIGHT_PURPLE + "Neutral";
      }

      if (formNumber == 2) {
         name = ChatColor.AQUA + "Bueno";
      }

      return name;
   }

   public static String formlist() {
      StringBuilder x = new StringBuilder();

      for(int i = 0; i < FormManager.getForms().size(); ++i) {
         x.append(((Form)FormManager.getForms().get(i)).getName().toUpperCase() + "/");
      }

      String y = x.toString();
      return y.substring(0, y.length() - 1);
   }

   public static String transform(int formNumber, int level) {
      return prefix + ChatColor.BLUE + "Has ascendido a pelotudo" + FormManager.getColor(formNumber) + FormManager.getFormattedName(formNumber) + " Nivel de forma " + level + ChatColor.BLUE + "!";
   }

   public static String deform(int formNumber, int level) {
      return prefix + ChatColor.BLUE + "Usted tiene " + ChatColor.RED + "descendió" + ChatColor.BLUE + " de " + FormManager.getColor(formNumber) + FormManager.getFormattedName(formNumber) + " Nivel de forma " + level + ChatColor.BLUE + ".";
   }

   public static void main(String[] args) {
   }

   static {
      prefix = ChatColor.BLACK + "" + ChatColor.BOLD + "{" + ChatColor.RESET + ChatColor.LIGHT_PURPLE + "Galaxy " + ChatColor.LIGHT_PURPLE + "Forms" + ChatColor.BLACK + ChatColor.BOLD + "}" + ChatColor.RESET;
      version = plugin.getDescription().getVersion();
   }
}
