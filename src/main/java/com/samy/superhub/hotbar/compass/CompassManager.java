package com.samy.superhub.hotbar.compass;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CompassManager {

    public ItemStack getPlayItem() {
        ItemStack playItem = new ItemStack(Material.COMPASS);
        ItemMeta metaPlay = playItem.getItemMeta();
        assert metaPlay != null;

        metaPlay.setDisplayName(ChatColor.GOLD + "Jouer");
        playItem.setItemMeta(metaPlay);

        return playItem;
    }

    public Inventory createInventory(){

        Inventory inv = Bukkit.createInventory(null, 27, "Jeux");
        ItemStack rush = new ItemStack(Material.BED);
        ItemMeta metaRush = rush.getItemMeta();
        assert metaRush != null;

        metaRush.setDisplayName(ChatColor.BLUE + "Rush");
        metaRush.setLore(Arrays.asList(ChatColor.GRAY + "bon jeu"));
        rush.setItemMeta(metaRush);
        inv.setItem(13, rush);

        return inv;
    }

    public void clickItem(ItemStack item, Player player){
        if (item.getType() == Material.BED){
            player.sendMessage(ChatColor.GOLD + "Tu viens de rejoindre le" + ChatColor.BLUE + " Rush" + ChatColor.GOLD + " !");
            player.closeInventory();
        }
    }
}
