package net.gnomemc.play;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.bukkit.plugin.Plugin;

public class Disenchanter implements Listener  {
	
	private Inventory inv;
	private ItemStack yes, no, glass;
	
	public Disenchanter(Plugin p) {
		inv = Bukkit.getServer().createInventory(null, 9, "/disenchanter");
		
		yes = createItem(DyeColor.GREEN, "Yes");
		no = createItem(DyeColor.RED, "No");
		glass = createItem(Material.STAINED_GLASS_PANE, ChatColor.RESET.toString());
		
		inv.setItem(2, yes);
		inv.setItem(6, no);
		inv.setItem(0, glass);
		inv.setItem(1, glass);
		inv.setItem(3, glass);
		inv.setItem(4, glass);
		inv.setItem(5, glass);
		inv.setItem(7, glass);
		inv.setItem(8, glass);
		
		Bukkit.getServer().getPluginManager().registerEvents(this, p);
			
	}
	
	private ItemStack createItem(Material stainedGlassPane, String name) {
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 0);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(" ");
		i.setItemMeta(im);
		return i;
	}

	private ItemStack createItem(DyeColor dc, String name) {
		ItemStack i = new Wool(dc).toItemStack(1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList("Disenchant your pickaxe!"));
		i.setItemMeta(im);
		return i;
	}
	

	
	public void show(Player p) {
		p.openInventory(inv);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!e.getInventory().equals(inv)) return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Yes")) {
			e.setCancelled(true);
			e.getWhoClicked().sendMessage(colorize("&a&lYou have been refunded Φ "));
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("No")) {
			e.setCancelled(true);
			e.getWhoClicked().sendMessage(colorize("&c&lYou have stopped disenchanting"));
		}
	}
	
	private String colorize(String todo) {
		return ChatColor.translateAlternateColorCodes('&', todo);
	}

	
	
}
