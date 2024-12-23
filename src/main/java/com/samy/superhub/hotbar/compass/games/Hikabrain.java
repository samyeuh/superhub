package com.samy.superhub.hotbar.compass.games;

import com.samy.superhub.hotbar.compass.AbstractGame;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class Hikabrain extends AbstractGame {

    HashMap<ItemStack, String> games = new HashMap<>();

    @Override
    public String getGameId() {
        return "hikabrain";
    }

    @Override
    public String getName() {
        return "Hikabrain";
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Material.BED, 1);
    }

    @Override
    public int getSlotInCompass() {
        return 13;
    }

    @Override
    public Inventory seeGameInventory() {
        Inventory inv = Bukkit.createInventory(null, 27, "Hikabrain");

        ItemStack onePlayer = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta metaOnePlayer = onePlayer.getItemMeta();
        metaOnePlayer.setDisplayName("1 joueur");
        onePlayer.setItemMeta(metaOnePlayer);
        inv.setItem(12, onePlayer);

        ItemStack twoPlayer = new ItemStack(Material.IRON_SWORD);
        ItemMeta metaTwoPlayer = twoPlayer.getItemMeta();
        metaTwoPlayer.setDisplayName("2 joueurs");
        twoPlayer.setItemMeta(metaTwoPlayer);
        inv.setItem(14, twoPlayer);

        games.put(onePlayer, "HK1v1");
        games.put(twoPlayer, "HK2v2");
        return inv;
    }

    @Override
    public void clickItem(ItemStack item, Player player) {
        if (games.containsKey(item)){
            player.sendMessage("Tu rejoins la file d'attente du jeu " + games.get(item) + " !");
        }
    }

    @Override
    public HashMap<ItemStack, String> getGames() {
        return games;
    }

}
