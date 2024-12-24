package com.samy.superhub.hotbar.compass.games;

import com.samy.api.SuperAPI;
import com.samy.superhub.hotbar.compass.AbstractGame;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hikabrain extends AbstractGame {

    HashMap<ItemStack, String> games = new HashMap<>();
    private final SuperAPI api;

    public Hikabrain() {
        api = SuperAPI.getInstance();
    }

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
        ItemStack item = new ItemStack(Material.SANDSTONE, 1, (short) 2);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("Hikabrain");
        List<String> lore = new ArrayList<>();
        lore.add("Battez vous sur un pont et");
        lore.add("marquez des points en allant");
        lore.add("sur le lit de vos adversaires !");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
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
            api.getQueueManager().addPlayer(player, games.get(item));
        }
    }

    @Override
    public HashMap<ItemStack, String> getGames() {
        return games;
    }

}
