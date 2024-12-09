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

        ItemStack targetHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta metaTargetHead = (SkullMeta) targetHead.getItemMeta();
        metaTargetHead.setOwner(target.getName());
        metaTargetHead.setDisplayName(ChatColor.GOLD + target.getName());
        targetHead.setItemMeta(metaTargetHead);

        inventory.setItem(4, targetHead);

        player.openInventory(inventory);
    }
}
