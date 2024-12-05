package com.samy.superhub.hotbar;

import com.samy.superhub.SuperHubPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record HidePlayersManager(Map<UUID, List<UUID>> friendsMap) {

    public void hidePlayers(SuperHubPlugin plugin, Player player) {
        for (Player p : player.getWorld().getPlayers()) {
            player.hidePlayer(plugin, p);
        }
    }

    public void showPlayers(SuperHubPlugin plugin, Player player) {
        for (Player p : player.getWorld().getPlayers()) {
            player.showPlayer(plugin, p);
        }
    }

    public void showFriends(SuperHubPlugin plugin, Player player) {
        List<UUID> friends = friendsMap.get(player.getUniqueId());
        if (friends == null) {
            hidePlayers(plugin, player);
            return;
        }
        for (Player p : player.getWorld().getPlayers()) {
            if (friends.contains(p.getUniqueId())) {
                player.showPlayer(plugin, p);
            } else {
                player.hidePlayer(plugin, p);
            }
        }
    }
}
