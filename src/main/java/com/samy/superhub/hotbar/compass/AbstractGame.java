package com.samy.superhub.hotbar.compass;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public abstract class AbstractGame {

    public abstract String getGameId();
    public abstract String getName();
    public abstract ItemStack getItem();
    public abstract int getSlotInCompass();
    public abstract Inventory seeGameInventory();
    public abstract void clickItem(ItemStack item, Player player);
    public abstract HashMap<ItemStack, String> getGames();
}
