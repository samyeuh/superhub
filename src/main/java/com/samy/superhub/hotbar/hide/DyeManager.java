package com.samy.superhub.hotbar.hide;

import com.samy.superhub.SuperHubPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DyeManager {

    public List<String> dyeLore = Arrays.asList(
            ChatColor.WHITE + "Tous les joueurs sont " + ChatColor.GREEN + "affichés",
            ChatColor.WHITE + "Uniquement les joueurs avec qui vous êtes " + ChatColor.LIGHT_PURPLE + "amis" + ChatColor.WHITE + " sont " + ChatColor.GREEN + "affichés",
            ChatColor.WHITE + "Tous les joueurs sont " + ChatColor.RED + " masqués"
    );
    public List<ItemStack> dyeItems = Arrays.asList(getGreenDye(), getPinkDye(), getGrayDye());
    public HidePlayersManager hideManager;
    public SuperHubPlugin plugin;
    public int state = 0; // 0 = showing, 1 = friends only, 2 = hiding

    public DyeManager(HidePlayersManager hideManager, SuperHubPlugin plugin){
        this.hideManager = hideManager;
        this.plugin = plugin;
    }

    public ItemStack getCurrentDye(){
        return dyeItems.get(state);
    }

    private ItemStack getGreenDye() {
        ItemStack showingPlayers = new ItemStack(Material.LIME_DYE);
        ItemMeta metaPlayers = showingPlayers.getItemMeta();
        assert metaPlayers != null;
        metaPlayers.setDisplayName(ChatColor.GOLD + "Joueurs: " + ChatColor.GREEN + "visibles");
        List<String> dyeLoreCopy = new ArrayList<>(dyeLore);
        dyeLoreCopy.set(0, " > " + ChatColor.BOLD + dyeLoreCopy.get(0));
        metaPlayers.setLore(dyeLoreCopy);
        showingPlayers.setItemMeta(metaPlayers);
        return showingPlayers;
    }

    private ItemStack getPinkDye(){
        ItemStack friendsOnly = new ItemStack(Material.PINK_DYE);
        ItemMeta metaFriendsOnly = friendsOnly.getItemMeta();
        assert metaFriendsOnly != null;

        metaFriendsOnly.setDisplayName(ChatColor.GOLD + "Joueurs: " + ChatColor.LIGHT_PURPLE + "amis uniquement");
        List<String> dyeLoreCopy = new ArrayList<>(dyeLore);
        dyeLoreCopy.set(1, " > " + ChatColor.BOLD + dyeLoreCopy.get(1));
        metaFriendsOnly.setLore(dyeLoreCopy);
        friendsOnly.setItemMeta(metaFriendsOnly);
        return friendsOnly;
    }

    private ItemStack getGrayDye(){
        ItemStack hidingPlayers = new ItemStack(Material.GRAY_DYE);
        ItemMeta metaHidingPlayers = hidingPlayers.getItemMeta();
        assert metaHidingPlayers != null;
        metaHidingPlayers.setDisplayName(ChatColor.GOLD + "Joueurs: " + ChatColor.RED + "masqués");
        List<String> dyeLoreCopy = new ArrayList<>(dyeLore);
        dyeLoreCopy.set(2, " > " + ChatColor.BOLD + dyeLoreCopy.get(2));
        metaHidingPlayers.setLore(dyeLoreCopy);
        hidingPlayers.setItemMeta(metaHidingPlayers);
        return hidingPlayers;
    }

    public void clickItem(Player player){
        switch (state){
            case 0:
                showFriends(player);
                break;
            case 1:
                hidePlayers(player);
                break;
            default:
                showPlayers(player);
                break;
        }
        player.getInventory().setItem(8, getCurrentDye());
    }


    private void hidePlayers(Player player) {
        hideManager.hidePlayers(plugin, player);
        state = 2;
    }

    private void showPlayers(Player player) {
        hideManager.showPlayers(plugin, player);
        state = 0;
    }

    private void showFriends(Player player) {
        hideManager.showFriends(plugin, player);
        state = 1;
    }
}
