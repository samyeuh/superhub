package com.samy.superhub.hotbar;


import com.samy.superhub.hotbar.hide.DyeManager;
import com.samy.superhub.hotbar.hide.HidePlayersManager;
import com.samy.superhub.hotbar.compass.CompassManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class HotbarManager {

    public CompassManager compassManager;
    public DyeManager dyeManager;

    public HotbarManager(Map<UUID, List<UUID>> friends){
        this.compassManager = new CompassManager();
        this.dyeManager = new DyeManager(new HidePlayersManager(friends));
        //TODO: minecraft for season pass, level of pass in xp bar
    }

    public HashMap<ItemStack, Integer> createItemsInInventory(){
        HashMap<ItemStack, Integer> items = new HashMap<>();

        ItemStack playItem = compassManager.getPlayItem();
        items.put(playItem, 0);

        ItemStack showingPlayers = dyeManager.getCurrentDye();
        items.put(showingPlayers, 8);

        return items;
    }

    public void interactItems(ItemStack item, Player player){
        if (item.getType() == Material.COMPASS){
            player.openInventory(compassManager.createInventory());
          } else if (item.getType() == Material.INK_SACK){
            dyeManager.clickItem(player);
        }
    }

    public void itemClick(String title, ItemStack item, Player player) {
        if (title.equals("player")) {
            interactItems(item, player);
        } else {
            compassManager.clickItem(item, player);
        }
    }
}
