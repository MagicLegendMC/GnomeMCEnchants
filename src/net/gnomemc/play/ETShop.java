package net.gnomemc.play;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Lists;

public class ETShop implements Listener {

    private Inventory inv;
    private InvBackpack backpack;

    public ETShop(JavaPlugin plugin) {
        inv = Bukkit.getServer().createInventory(null, 45, "/etshop");

        final ItemStack bp = createItem(Material.CHEST, ChatColor.AQUA.toString() + ChatColor.BOLD + "Backpacks");
        final ItemStack pick = createItem(DyeColor.YELLOW, "Survival");
        final ItemStack withdraw = createItem(DyeColor.RED, "Adventure");

        inv.setItem(2, bp);
        inv.setItem(4, pick);
        inv.setItem(6, withdraw);

        backpack = new InvBackpack(plugin);

        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private ItemStack createItem(DyeColor dc, String name) {
        ItemStack i = new Wool(dc).toItemStack(1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Lists.newArrayList("Set your gamemode", "to " + name.toLowerCase() + " mode"));
        i.setItemMeta(im);
        return i;
    }

    private ItemStack createItem(Material chest, String name) {
        ItemStack i = new ItemStack(chest, 1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Lists.newArrayList(ChatColor.GRAY + "You can " + ChatColor.GREEN + "purchase backpacks " + ChatColor.GRAY + "here!"));
        i.setItemMeta(im);
        return i;
    }

    public void show(Player p) {
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.getInventory().equals(inv) || !(e.getWhoClicked() instanceof Player)) return;
        final Player clicker = (Player) e.getWhoClicked();

        e.setCancelled(true);

        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Backpacks")) {
            clicker.closeInventory();
            clicker.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "You have opened the backpack store!");
            clicker.openInventory(backpack.getBackpack());
        }

        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Creative"))
            clicker.setGameMode(GameMode.CREATIVE);
        
        else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Adventure"))
            clicker.setGameMode(GameMode.ADVENTURE);

    }

}
