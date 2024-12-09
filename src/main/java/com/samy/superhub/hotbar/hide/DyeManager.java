package com.samy.superhub.hotbar.hide;

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
            ChatColor.GRAY + "" + ChatColor.ITALIC + "Tous les joueurs sont affichés",
            ChatColor.GRAY + "" + ChatColor.ITALIC + "Uniquement les joueurs avec qui vous êtes amis sont affichés",
            ChatColor.GRAY + "" + ChatColor.ITALIC + "Tous les joueurs sont masqués"
    );

    public List<String> dyeLoreSelected = Arrays.asList(
            ChatColor.GREEN + "> " + ChatColor.BOLD + "Tous les joueurs sont affichés",
            ChatColor.LIGHT_PURPLE + "> " + ChatColor.BOLD + "Uniquement les joueurs avec qui vous êtes amis sont affichés",
            ChatColor.RED + "> " + ChatColor.BOLD + "Tous les joueurs sont masqués"
    );
    public List<ItemStack> dyeItems = Arrays.asList(getGreenDye(), getPinkDye(), getGrayDye());
    public HidePlayersManager hideManager;
    public int state = 0; // 0 = showing, 1 = friends only, 2 = hiding

    public DyeManager(HidePlayersManager hideManager){
        this.hideManager = hideManager;
    }

    public ItemStack getCurrentDye(){
        return dyeItems.get(state);
    }

    private ItemStack getGreenDye() {
        ItemStack showingPlayers = new ItemStack(Material.INK_SACK, 1, (short) 10);
        ItemMeta metaPlayers = showingPlayers.getItemMeta();
        assert metaPlayers != null;
        metaPlayers.setDisplayName(ChatColor.GREEN + "Joueurs visibles");
        List<String> dyeLoreCopy = new ArrayList<>(dyeLore);
        List<String> dyeLoreSelectedCopy = new ArrayList<>(dyeLoreSelected);
        dyeLoreCopy.set(0, dyeLoreSelectedCopy.get(0));
        metaPlayers.setLore(dyeLoreCopy);
        showingPlayers.setItemMeta(metaPlayers);
        return showingPlayers;
    }

    private ItemStack getPinkDye(){
        ItemStack friendsOnly = new ItemStack(Material.INK_SACK, 1, (short) 9);
        ItemMeta metaFriendsOnly = friendsOnly.getItemMeta();
        assert metaFriendsOnly != null;

        metaFriendsOnly.setDisplayName(ChatColor.LIGHT_PURPLE + "Amis uniquement");
        List<String> dyeLoreCopy = new ArrayList<>(dyeLore);
        List<String> dyeLoreSelectedCopy = new ArrayList<>(dyeLoreSelected);
        dyeLoreCopy.set(1, dyeLoreSelectedCopy.get(1));
        metaFriendsOnly.setLore(dyeLoreCopy);
        friendsOnly.setItemMeta(metaFriendsOnly);
        return friendsOnly;
    }

    private ItemStack getGrayDye(){
        ItemStack hidingPlayers = new ItemStack(Material.INK_SACK, 1, (short) 8);
        ItemMeta metaHidingPlayers = hidingPlayers.getItemMeta();
        assert metaHidingPlayers != null;
        metaHidingPlayers.setDisplayName(ChatColor.RED + "Joueurs masqués");
        List<String> dyeLoreCopy = new ArrayList<>(dyeLore);
        List<String> dyeLoreSelectedCopy = new ArrayList<>(dyeLoreSelected);
        dyeLoreCopy.set(2, dyeLoreSelectedCopy.get(2));
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
        hideManager.hidePlayers(player);
        state = 2;
    }

    private void showPlayers(Player player) {
        hideManager.showPlayers(player);
        state = 0;
    }

    private void showFriends(Player player) {
        hideManager.showFriends(player);
        state = 1;
    }
}
