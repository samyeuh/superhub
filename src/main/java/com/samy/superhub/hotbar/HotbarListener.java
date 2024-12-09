package com.samy.superhub.hotbar;

import com.samy.superhub.SuperHubPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class HotbarListener implements Listener {

    public final SuperHubPlugin plugin;
    public final HotbarManager hotbarManager;

    public HotbarListener(SuperHubPlugin plugin, Map<UUID, List<UUID>> friendsMap) {
        this.plugin = plugin;
        this.hotbarManager = new HotbarManager(friendsMap);
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();
        player.getInventory().setHeldItemSlot(5);
        HashMap<ItemStack, Integer> items = hotbarManager.createItemsInInventory(player);
        items.forEach((item, slot) -> player.getInventory().setItem(slot, item));
        player.getInventory().setHeldItemSlot(4);
        player.sendMessage(ChatColor.GREEN + "Bienvenue sur le serveur !");

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        plugin.refreshFriends();
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        if (item == null) return;
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK){
            hotbarManager.interactItems(item, player);
        }
    }

    @EventHandler
    public void onCLick(InventoryClickEvent event){
        plugin.refreshFriends();
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if (item == null) return;
        String inventoryName = event.getView().getTitle();
        if (Objects.equals(event.getClickedInventory(), player.getInventory())) {
            inventoryName = "player";
        }
        event.setCancelled(true);

        hotbarManager.itemClick(inventoryName, item, player);
        player.setItemOnCursor(null);
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event){
        event.setCancelled(true);
    }

//    @EventHandler
//    public void onSwapHandItems(PlayerSwapHandItemsEvent event){
//        event.setCancelled(true);
//    }
}
