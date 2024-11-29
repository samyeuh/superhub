package com.samy.superhub.hotbar;

import com.samy.superhub.SuperHubPlugin;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class HidePlayersManager {

    public static void hidePlayers(SuperHubPlugin plugin, Player player){
        for (Player p : player.getWorld().getPlayers()){
            player.hidePlayer(plugin, p);
        }
    }

    public static void showPlayers(SuperHubPlugin plugin, Player player){
        for (Player p : player.getWorld().getPlayers()){
            player.showPlayer(plugin, p);
        }
    }
}
