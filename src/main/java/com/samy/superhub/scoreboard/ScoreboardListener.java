package com.samy.superhub.scoreboard;

import com.samy.api.SuperAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;
import java.util.List;

public class ScoreboardListener implements Listener {

    public SuperAPI superAPI;

    public ScoreboardListener(SuperAPI superAPI){
        this.superAPI = superAPI;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        List<String> lines = Arrays.asList(
                "playerName",
                "coins",
                "connectedPlayers"
        );
        superAPI.setScoreboard("Super serveur de Samy", lines, p);
    }
}
