package com.samy.superhub.hotbar;

import com.samy.superhub.SuperHubPlugin;
import com.samy.superhub.hotbar.HidePlayersManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.checkerframework.checker.units.qual.A;

import java.util.*;

public class HotbarManager {

    public static List<String> dyeLore = Arrays.asList(ChatColor.WHITE + "Tous les joueurs sont " + ChatColor.GREEN + "affichés",
            ChatColor.WHITE + "Uniquement les joueurs avec qui vous êtes " + ChatColor.LIGHT_PURPLE + "amis" + ChatColor.WHITE + " sont " + ChatColor.GREEN + "affichés",
            ChatColor.WHITE + "Tous les joueurs sont " + ChatColor.RED + " masqués");

    public HidePlayersManager hidePlayersManager;

    public HotbarManager(Map<UUID, List<UUID>> friends){
        this.hidePlayersManager = new HidePlayersManager(friends);
    }

    public HashMap<ItemStack, Integer> createItemsInInventory(Player player){
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
        List<String> dyeLoreCopy = new ArrayList<>(dyeLore);
        dyeLoreCopy.set(0, " > " + ChatColor.BOLD + dyeLoreCopy.get(0));
        metaPlayers.setLore(dyeLoreCopy);
        showingPlayers.setItemMeta(metaPlayers);
        items.put(showingPlayers, 8);

        return items;
    }

    public void interactItems(ItemStack item, Player player, SuperHubPlugin plugin){
        if (item.getType() == Material.COMPASS){
            interactCompass(player);
        } else if (item.getType() == Material.GOLD_INGOT){
            interactGoldIngot(player);
        } else if (item.getType() == Material.PLAYER_HEAD){
            interactPlayerHead(player);
        } else if (item.getType() == Material.LIME_DYE){
            interactLimeDye(player, plugin);
        } else if (item.getType() == Material.GRAY_DYE){
            interactGrayDye(player, plugin);
        } else if (item.getType() == Material.PINK_DYE){
            interactPinkDye(player, plugin);
        }
    }

    private void interactCompass(Player player) {
        player.playSound(player.getLocation(), Sound.ENTITY_FISH_SWIM, 2f, 2f);
        Inventory inv = Bukkit.createInventory(null, 54, "Jeux");

        ItemStack rush = new ItemStack(Material.BLUE_BED);
        ItemMeta metaRush = rush.getItemMeta();
        metaRush.setDisplayName(ChatColor.BLUE + "Rush");
        metaRush.setLore(List.of(ChatColor.GRAY + "bon jeu"));
        rush.setItemMeta(metaRush);
        inv.setItem(13, rush);

        player.openInventory(inv);
    }

    private void interactGoldIngot(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, "Shop");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2f, 2f);

        ItemStack vip = new ItemStack(Material.DIAMOND);
        ItemMeta metaVip = vip.getItemMeta();
        metaVip.setDisplayName(ChatColor.AQUA + "VIP");
        metaVip.setLore(List.of(ChatColor.GRAY + "pour les riches"));
        vip.setItemMeta(metaVip);

        inv.setItem(13, vip);
        player.openInventory(inv);
    }

    private void interactPlayerHead(Player player) {
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

    private void interactLimeDye(Player player, SuperHubPlugin plugin) {
        ItemStack friendsOnly = new ItemStack(Material.PINK_DYE);
        ItemMeta metaFriendsOnly = friendsOnly.getItemMeta();

        metaFriendsOnly.setDisplayName(ChatColor.GOLD + "Joueurs: " + ChatColor.LIGHT_PURPLE + "amis uniquement");
        List<String> dyeLoreCopy = new ArrayList<>(dyeLore);
        dyeLoreCopy.set(1, " > " + ChatColor.BOLD + dyeLoreCopy.get(1));
        metaFriendsOnly.setLore(dyeLoreCopy);
        friendsOnly.setItemMeta(metaFriendsOnly);

        hidePlayersManager.showFriends(plugin, player);
        player.getInventory().setItem(8, friendsOnly);
    }

    private void interactPinkDye(Player player, SuperHubPlugin plugin){
        ItemStack hidingPlayers = new ItemStack(Material.GRAY_DYE);
        ItemMeta metaHidingPlayers = hidingPlayers.getItemMeta();
        player.playSound(player.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 2f, 2f);

        metaHidingPlayers.setDisplayName(ChatColor.GOLD + "Joueurs: " + ChatColor.RED + "masqués");
        List<String> dyeLoreCopy = new ArrayList<>(dyeLore);
        dyeLoreCopy.set(2, " > " + ChatColor.BOLD + dyeLoreCopy.get(2));
        metaHidingPlayers.setLore(dyeLoreCopy);
        hidingPlayers.setItemMeta(metaHidingPlayers);

        hidePlayersManager.hidePlayers(plugin, player);
        player.getInventory().setItem(8, hidingPlayers);
    }

    private void interactGrayDye(Player player, SuperHubPlugin plugin) {
        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 2f, 2f);

        ItemStack showingPlayers = new ItemStack(Material.LIME_DYE);
        ItemMeta metaPlayers = showingPlayers.getItemMeta();
        metaPlayers.setDisplayName(ChatColor.GOLD + "Joueurs: " + ChatColor.GREEN + "visibles");
        List<String> dyeLoreCopy = new ArrayList<>(dyeLore);
        dyeLoreCopy.set(0, " > " + ChatColor.BOLD + dyeLoreCopy.get(0));
        metaPlayers.setLore(dyeLoreCopy);
        showingPlayers.setItemMeta(metaPlayers);

        hidePlayersManager.showPlayers(plugin, player);
        player.getInventory().setItem(8, showingPlayers);
    }

    public void itemClick(String title, ItemStack item, Player player, SuperHubPlugin plugin){
        String pseudo = player.getName();
        if (title.equals("Jeux")){
            itemClickGames(item, player);
        } else if (title.equals("Shop")){
            player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_PLACE, 2f, 2f);
            itemClickShop(item, player);
        } else if (title.equals(pseudo)){
            itemClickProfil(item, player);
        } else if (title.equals("player")){
            interactItems(item, player, plugin);
        }
    }

    private void itemClickGames(ItemStack item, Player player) {
        if (item.getType() == Material.BLUE_BED){
            player.sendMessage(ChatColor.GOLD + "Tu viens de rejoindre le" + ChatColor.BLUE + " Rush" + ChatColor.GOLD + " !");
            player.closeInventory();
        }
    }

    private void itemClickShop(ItemStack item, Player player) {
        if (item.getType() == Material.DIAMOND){
            player.sendMessage(ChatColor.GOLD + "Tu viens d'acheter le" + ChatColor.AQUA + " VIP" + ChatColor.GOLD + " !");
            player.closeInventory();
        }
    }

    private void itemClickProfil(ItemStack item, Player player) {
        if (item.getType() == Material.LIGHT_BLUE_STAINED_GLASS_PANE){
            return;
        }
        player.sendMessage(ChatColor.GOLD + "Tu viens de cliquer sur ton profil !");
        player.closeInventory();
    }
}
