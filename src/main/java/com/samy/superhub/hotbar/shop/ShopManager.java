package com.samy.superhub.hotbar.shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ShopManager {

    public ItemStack getShopItem(){
        ItemStack shopItem = new ItemStack(Material.GOLD_INGOT);
        ItemMeta metaShop = shopItem.getItemMeta();
        assert metaShop != null;
        metaShop.setDisplayName(ChatColor.GOLD + "Boutique");
        metaShop.setLore(List.of(ChatColor.GRAY + "c trop bien ici tkt"));
        shopItem.setItemMeta(metaShop);
        return shopItem;
    }

    public Inventory createInventory(){
        Inventory inv = Bukkit.createInventory(null, 54, "Shop");
        ItemStack vip = new ItemStack(Material.DIAMOND);
        ItemMeta metaVip = vip.getItemMeta();
        assert metaVip != null;
        metaVip.setDisplayName(ChatColor.AQUA + "VIP");
        metaVip.setLore(List.of(ChatColor.GRAY + "pour les riches"));
        vip.setItemMeta(metaVip);

        inv.setItem(13, vip);

        return inv;
    }

    public void clickItem(ItemStack item, Player player){
        if (item.getType() == Material.DIAMOND){
            player.sendMessage(ChatColor.GOLD + "Tu viens de rejoindre le" + ChatColor.AQUA + " VIP" + ChatColor.GOLD + " !");
            player.closeInventory();
        }
    }
}
