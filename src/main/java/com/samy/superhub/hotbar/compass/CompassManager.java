package com.samy.superhub.hotbar.compass;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CompassManager {

    public final GameManager gameManager;

    public CompassManager(){
        this.gameManager = new GameManager();
    }

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
        gameManager.getGamesInCompass().forEach((game, slot) -> inv.setItem(slot, game.getItem()));
        return inv;
    }

    public void clickItem(ItemStack item, Player player){
        for (AbstractGame game : gameManager.getGamesInCompass().keySet()){
            if (game.getItem().equals(item)) {
                player.openInventory(game.seeGameInventory());
            } else if (game.getGames().containsKey(item)){
                game.clickItem(item, player);
                player.closeInventory();
            }
        }
    }
}
