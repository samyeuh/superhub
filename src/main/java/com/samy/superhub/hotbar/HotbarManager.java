package com.samy.superhub;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.checkerframework.checker.units.qual.A;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HotbarManager {


    public static HashMap<ItemStack, Integer> createItemsInInventory(Player player){
        HashMap<ItemStack, Integer> items = new HashMap<>();
        String pseudo = player.getName();

        ItemStack playItem = new ItemStack(Material.COMPASS);
        ItemMeta metaPlay = playItem.getItemMeta();
        metaPlay.setDisplayName(ChatColor.GOLD + "Jouer");
        metaPlay.setLore(Arrays.asList(ChatColor.GRAY + "pas peur de kiffer"));
        playItem.setItemMeta(metaPlay);
        items.put(playItem, 0);

        ItemStack shopItem = new ItemStack(Material.GOLD_INGOT);
        ItemMeta metaShop = shopItem.getItemMeta();
        metaShop.setDisplayName(ChatColor.GOLD + "Boutique");
        metaShop.setLore(Arrays.asList(ChatColor.GRAY + "c trop bien ici tkt"));
        shopItem.setItemMeta(metaShop);
        items.put(shopItem, 1);

        ItemStack profilItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta metaProfil = (SkullMeta) profilItem.getItemMeta();
        metaProfil.setOwnerProfile(player.getPlayerProfile());
        metaProfil.setDisplayName(ChatColor.GOLD + pseudo);
        metaProfil.setLore(Arrays.asList(ChatColor.GRAY + "ton profil de gros bg"));
        profilItem.setItemMeta(metaProfil);
        items.put(profilItem, 7);

        ItemStack showingPlayers = new ItemStack(Material.LIME_DYE);
        ItemMeta metaPlayers = showingPlayers.getItemMeta();
        metaPlayers.setDisplayName(ChatColor.GOLD + "Joueurs: " + ChatColor.GREEN + "visibles");
        metaPlayers.setLore(Arrays.asList(ChatColor.GRAY + "pour afficher ou non les joueurs"));
        showingPlayers.setItemMeta(metaPlayers);
        items.put(showingPlayers, 8);

        return items;
    }

    public static void interactItems(ItemStack item, Player player){
        if (item.getType() == Material.COMPASS){
            interactCompass(player);
        } else if (item.getType() == Material.GOLD_INGOT){
            interactGoldIngot(player);
        } else if (item.getType() == Material.PLAYER_HEAD){
            interactPlayerHead(player);
        } else if (item.getType() == Material.LIME_DYE){
            interactLimeDye(player);
        } else if (item.getType() == Material.GRAY_DYE){
            interactGrayDye(player);
        }
    }

    private static void interactCompass(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, "Jeux");

        ItemStack rush = new ItemStack(Material.BLUE_BED);
        ItemMeta metaRush = rush.getItemMeta();
        metaRush.setDisplayName(ChatColor.RED + "Rush");
        metaRush.setLore(List.of(ChatColor.GRAY + "bon jeu"));
        rush.setItemMeta(metaRush);
        inv.setItem(13, rush);

        player.openInventory(inv);
    }

    private static void interactGoldIngot(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, "Shop");

        ItemStack vip = new ItemStack(Material.DIAMOND);
        ItemMeta metaVip = vip.getItemMeta();
        metaVip.setDisplayName(ChatColor.AQUA + "VIP");
        metaVip.setLore(List.of(ChatColor.GRAY + "pour les riches"));
        vip.setItemMeta(metaVip);

        inv.setItem(13, vip);
        player.openInventory(inv);
    }

    private static void interactPlayerHead(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, player.getName());

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta metaPlayerHead = (SkullMeta) playerHead.getItemMeta();
        PlayerProfile profile = player.getPlayerProfile();
        metaPlayerHead.setOwnerProfile(profile);
        metaPlayerHead.setDisplayName(ChatColor.GOLD + player.getName());
        metaPlayerHead.setLore(List.of(ChatColor.GRAY + "ton profil de gros bg"));
        playerHead.setItemMeta(metaPlayerHead);

        ItemStack blueGlass = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta glassMeta = blueGlass.getItemMeta();
        glassMeta.setDisplayName(ChatColor.BLACK + ".");
        blueGlass.setItemMeta(glassMeta);

        for (int i = 9; i < 18; i++) {
            inv.setItem(i, blueGlass);
        }
        inv.setItem(4, playerHead);
        player.openInventory(inv);
    }

    private static void interactLimeDye(Player player) {
        ItemStack hidingPlayers = new ItemStack(Material.GRAY_DYE);
        ItemMeta metaHidingPlayers = hidingPlayers.getItemMeta();
        metaHidingPlayers.setDisplayName(ChatColor.GOLD + "Joueurs: " + ChatColor.RED + "masqués");
        metaHidingPlayers.setLore(Arrays.asList(ChatColor.GRAY + "pour afficher ou non les joueurs"));
        hidingPlayers.setItemMeta(metaHidingPlayers);

        player.sendMessage(ChatColor.GOLD + "Tu viens de" + ChatColor.RED + " masquer" + ChatColor.GOLD + " les joueurs !");
        player.getInventory().setItem(8, hidingPlayers);
    }

    private static void interactGrayDye(Player player) {
        ItemStack showingPlayers = new ItemStack(Material.LIME_DYE);
        ItemMeta metaPlayers = showingPlayers.getItemMeta();
        metaPlayers.setDisplayName(ChatColor.GOLD + "Joueurs: " + ChatColor.GREEN + "visibles");
        metaPlayers.setLore(Arrays.asList(ChatColor.GRAY + "pour afficher ou non les joueurs"));
        showingPlayers.setItemMeta(metaPlayers);

        player.sendMessage(ChatColor.GOLD + "Tu viens d'" + ChatColor.GREEN + "afficher" + ChatColor.GOLD + " les joueurs !");
        player.getInventory().setItem(8, showingPlayers);
    }

    public static void itemClick(Inventory inv, ItemStack item, Player player){
    }
}
