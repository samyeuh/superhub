package com.samy.superhub.scoreboard;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ScoreboardListener implements Listener {

    public HubScoreboardManager scoreboardManager;

    public ScoreboardListener(HubScoreboardManager scoreboardManager){
        this.scoreboardManager = scoreboardManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        scoreboardManager.refreshAllScoreboards();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        scoreboardManager.refreshAllScoreboards();
    }
}
