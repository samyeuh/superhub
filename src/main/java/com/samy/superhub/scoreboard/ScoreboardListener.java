package com.samy.superhub.scoreboard;


import com.samy.api.scoreboard.IScoreboardManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;
import java.util.List;

public class ScoreboardListener implements Listener {

    public IScoreboardManager scoreboardManager;

    public ScoreboardListener(IScoreboardManager scoreboardManager){
        this.scoreboardManager = scoreboardManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        List<String> lines = Arrays.asList(
                "playerName",
                "coins",
                "connectedPlayers"
        );
        scoreboardManager.setScoreboard(p,"Super serveur de Samy", lines);
    }
}
