package com.samy.superhub.hotbar.hide;

import com.samy.superhub.SuperHubPlugin;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HidePlayersManager {

    private final Map<UUID, List<UUID>> friendsMap;

    public HidePlayersManager(Map<UUID, List<UUID>> friendsMap){
        this.friendsMap = friendsMap;
    }

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
