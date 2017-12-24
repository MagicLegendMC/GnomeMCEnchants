package net.gnomemc.play;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Lists;

public class InvBackpack implements Listener {

    private Inventory backpack;

    public InvBackpack(JavaPlugin p) {
        backpack = Bukkit.getServer().createInventory(null, 27, "Backpacks");

        final ItemStack buy = createItem(Material.CHEST, ChatColor.GREEN + "Buy a new Backpack");
        final ItemStack upgrade = createItem(Material.CHEST, ChatColor.GOLD + "Upgrade your Backpack");

        backpack.setItem(11, buy);
        backpack.setItem(15, upgrade);

        Bukkit.getServer().getPluginManager().registerEvents(this, p);
    }

    private ItemStack createItem(Material chest, String name) {
        ItemStack i = new ItemStack(chest, 1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Lists.newArrayList(ChatColor.GRAY + "You can " + ChatColor.GREEN + "purchase backpacks " + ChatColor.GREEN + "here!"));
        i.setItemMeta(im);
        return i;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.getInventory().equals(backpack)) return;
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Buy a new Backpacks")) {
            e.setCancelled(true);
            e.getWhoClicked().sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "You are buying a Backpack");
            e.getWhoClicked().closeInventory();
        }
    }

    public Inventory getBackpack() {
        return backpack;
    }
}


