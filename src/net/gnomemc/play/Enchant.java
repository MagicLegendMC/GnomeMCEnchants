package net.gnomemc.play;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Enchant extends JavaPlugin implements Listener {
	
	private ETShop etshop;
	private Disenchanter disenchanter;
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Enchant plugin;
	
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has been Enabled!");
		etshop = new ETShop(this);
		disenchanter = new Disenchanter(this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Has been Disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		
		if(commandLabel.equalsIgnoreCase("enchanthelp")) {
			player.sendMessage(colorize("&a&l-=[*]=- &2&lGnomeMC Enchants &a&l-=[*]=-"));
			player.sendMessage(colorize("&2* &b/etshop &a- Enchant Your Pickaxe!"));
			player.sendMessage(colorize("&2* &b/tokentop &a- Lists The Top 10 People With The Most Tokens"));
			player.sendMessage(colorize("&2* &b/disenchanter &a- Allows you to disenchant your pickaxe!"));
			player.sendMessage(colorize(""));
			player.sendMessage(colorize("&a&l-=[*]=- &2&lAdmin Commands &a-=[*]=-"));
			player.sendMessage(colorize("&2* &b/tokengive <player> <amount> &a- &aGive Tokens To Players!"));
			player.sendMessage(colorize("&2* &b/tokenreset <player> &a- &aReset a Players Tokens"));		
		}
		if(commandLabel.equalsIgnoreCase("etshop")) {
			etshop.show(player);
		}
		if(commandLabel.equalsIgnoreCase("disenchanter")) {
			disenchanter.show(player);
		}
		return false;
	}
	

	private String colorize(String todo) {
		return ChatColor.translateAlternateColorCodes('&', todo);
	}
}
