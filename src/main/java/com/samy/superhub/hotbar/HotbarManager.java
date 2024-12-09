package com.samy.superhub.hotbar;


import com.samy.superhub.hotbar.hide.DyeManager;
import com.samy.superhub.hotbar.hide.HidePlayersManager;
import com.samy.superhub.hotbar.compass.CompassManager;
import com.samy.superhub.hotbar.profil.ProfilManager;
import com.samy.superhub.hotbar.shop.ShopManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class HotbarManager {

    public CompassManager compassManager;
    public ShopManager shopManager;
    public ProfilManager profilManager;
    public DyeManager dyeManager;

    public HotbarManager(Map<UUID, List<UUID>> friends){
        this.compassManager = new CompassManager();
//        this.shopManager = new ShopManager();
//        this.profilManager = new ProfilManager();
        this.dyeManager = new DyeManager(new HidePlayersManager(friends));
        //TODO: minecraft for season pass, level of pass in xp bar
    }

    public HashMap<ItemStack, Integer> createItemsInInventory(Player player){
        HashMap<ItemStack, Integer> items = new HashMap<>();

        ItemStack playItem = compassManager.getPlayItem();
        items.put(playItem, 0);

//        ItemStack shopItem = shopManager.getShopItem();
//        items.put(shopItem, 1);
//
//        ItemStack profilItem = profilManager.getProfilItem(player);
//        items.put(profilItem, 7);

        ItemStack showingPlayers = dyeManager.getCurrentDye();
        items.put(showingPlayers, 8);

        return items;
    }

    public void interactItems(ItemStack item, Player player){
        if (item.getType() == Material.COMPASS){
            player.openInventory(compassManager.createInventory());
//        } else if (item.getType() == Material.GOLD_INGOT){
//            player.openInventory(shopManager.createInventory());
//        } else if (item.getType() == Material.SKULL_ITEM){
//            player.openInventory(profilManager.createInventory(player));
          } else if (item.getType() == Material.INK_SACK){
            dyeManager.clickItem(player);
        }
    }

    public void itemClick(String title, ItemStack item, Player player){
        String pseudo = player.getName();
        if (title.equals("Jeux")){
            compassManager.clickItem(item, player);
//        } else if (title.equals("Shop")){
//            shopManager.clickItem(item, player);
//        } else if (title.equals(pseudo)){
//            profilManager.clickItem(item, player);
        } else if (title.equals("player")){
            interactItems(item, player);
        }
    }
}
