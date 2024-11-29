package com.samy.superhub.interactions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class InteractionsManager {

    public static void interactWithPlayer(Player player, Player target) {
        Inventory inventory = Bukkit.createInventory(null, 54, target.getName());

        ItemStack targetHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta metaTargetHead = (SkullMeta) targetHead.getItemMeta();
        metaTargetHead.setOwnerProfile(target.getPlayerProfile());
        metaTargetHead.setDisplayName(ChatColor.GOLD + target.getName());
        targetHead.setItemMeta(metaTargetHead);

        inventory.setItem(5, targetHead);

        player.openInventory(inventory);
    }
}
