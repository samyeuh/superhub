package com.samy.superhub.hotbar.profil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.util.List;

public class ProfilManager {

    public ItemStack getProfilItem(Player player){
        ItemStack profilItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta metaProfil = (SkullMeta) profilItem.getItemMeta();
        assert metaProfil != null;
        metaProfil.setOwnerProfile(player.getPlayerProfile());
        metaProfil.setDisplayName(ChatColor.GOLD + player.getName());
        metaProfil.setLore(List.of(ChatColor.GRAY + "ton profil de gros bg"));
        profilItem.setItemMeta(metaProfil);
        return profilItem;
    }

    public Inventory createInventory(Player player){
        Inventory inv = Bukkit.createInventory(null, 54, player.getName());

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta metaPlayerHead = (SkullMeta) playerHead.getItemMeta();
        PlayerProfile profile = player.getPlayerProfile();
        assert metaPlayerHead != null;
        metaPlayerHead.setOwnerProfile(profile);
        metaPlayerHead.setDisplayName(ChatColor.GOLD + player.getName());
        metaPlayerHead.setLore(List.of(ChatColor.GRAY + "ton profil de gros bg"));
        playerHead.setItemMeta(metaPlayerHead);

        ItemStack blueGlass = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta glassMeta = blueGlass.getItemMeta();
        assert glassMeta != null;
        glassMeta.setDisplayName(ChatColor.BLACK + ".");
        blueGlass.setItemMeta(glassMeta);

        for (int i = 9; i < 18; i++) {
            inv.setItem(i, blueGlass);
        }
        inv.setItem(4, playerHead);

        return inv;
    }

    public void clickItem(ItemStack item, Player player){
        if (item.getType() == Material.LIGHT_BLUE_STAINED_GLASS_PANE){
            return;
        }
        player.sendMessage(ChatColor.GOLD + "Tu viens de cliquer sur ton profil !");
        player.closeInventory();
    }
}
