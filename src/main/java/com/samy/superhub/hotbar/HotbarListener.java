package com.samy.superhub.hotbar;

import com.samy.superhub.hotbar.HotbarManager;
import com.samy.superhub.SuperHubPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class HotbarListener implements Listener {

    public final SuperHubPlugin plugin;

    public HotbarListener(SuperHubPlugin plugin) {
        this.plugin = plugin;
    }


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
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK){
            HotbarManager.interactItems(item, player, plugin);
        }
    }

    @EventHandler
    public void onCLick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if (item == null) return;
        String inventoryName = event.getView().getTitle();
        if (event.getClickedInventory().equals(player.getInventory())) {
            inventoryName = "player";
        }
        event.setCancelled(true);

        HotbarManager.itemClick(inventoryName, item, player, plugin);
        player.setItemOnCursor(null);
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onSwapHandItems(PlayerSwapHandItemsEvent event){
        event.setCancelled(true);
    }
}
