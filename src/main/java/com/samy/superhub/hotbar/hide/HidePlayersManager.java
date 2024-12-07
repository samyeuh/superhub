package com.samy.superhub.hotbar.hide;


import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HidePlayersManager {

    private final Map<UUID, List<UUID>> friendsMap;

    public HidePlayersManager(Map<UUID, List<UUID>> friendsMap){
        this.friendsMap = friendsMap;
    }

    public void hidePlayers(Player player) {
        for (Player p : player.getWorld().getPlayers()) {
            player.hidePlayer(p);
        }
    }

    public void showPlayers(Player player) {
        for (Player p : player.getWorld().getPlayers()) {
            player.showPlayer(p);
        }
    }

    public void showFriends(Player player) {
        List<UUID> friends = friendsMap.get(player.getUniqueId());
        if (friends == null) {
            hidePlayers(player);
            return;
        }
        for (Player p : player.getWorld().getPlayers()) {
            if (friends.contains(p.getUniqueId())) {
                player.showPlayer(p);
            } else {
                player.hidePlayer(p);
            }
        }
    }
}
