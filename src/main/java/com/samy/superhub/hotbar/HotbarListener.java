package com.samy.superhub.hotbar;

import com.samy.superhub.HotbarManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class HotbarListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        Location spawn = new Location(player.getWorld(), 10.5, -58, 8.5, 177f, 0.0f);
        player.teleport(spawn);

        player.getInventory().clear();
        player.getInventory().setHeldItemSlot(5);
        HashMap<ItemStack, Integer> items = HotbarManager.createItemsInInventory(player);
        items.forEach((item, slot) -> player.getInventory().setItem(slot, item));
        player.updateInventory();
        player.getInventory().setHeldItemSlot(4);
        player.sendMessage(ChatColor.GREEN + "Bienvenue sur le serveur !");

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        if (item == null) return;

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){
            HotbarManager.interactItems(item, player);
        }
    }

    @EventHandler
    public void onCLick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if (inv == null || item == null) return;
        HotbarManager.itemClick(inv, item, player);
    }
}
