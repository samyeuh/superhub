package com.samy.superhub.scoreboard;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.samy.api.scoreboard.IScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class HubScoreboardManager {

    public IScoreboardManager scoreboardManager;

    public HubScoreboardManager(IScoreboardManager scoreboardManager) {
        this.scoreboardManager = scoreboardManager;
    }

    public void setScoreboard(Player player) {
        InputStream json = Bukkit.getPluginManager().getPlugin("SuperHubPlugin").getResource("hub.json");

        try (InputStreamReader reader = new InputStreamReader(json, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            String title = jsonObject.get("title").getAsString();
            String footer = jsonObject.get("footer").getAsString();

            String objName = "obj_" + System.currentTimeMillis() + player.getName();
            objName = objName.substring(0, 16);

            Objective objective = Bukkit.getScoreboardManager().getNewScoreboard().registerNewObjective("custom", objName);
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            JsonArray linesArray = jsonObject.getAsJsonArray("lines");

            objective.setDisplayName(title);
            objective.getScore(footer).setScore(-2);
            objective.getScore(" ").setScore(-1);

            for (int i = 0; i < linesArray.size(); i++) {
                String line = linesArray.get(i).getAsString();
                objective.getScore(applyPlaceholders(line, player)).setScore(linesArray.size() - i);
            }

            scoreboardManager.setScoreboard(player, objective, null);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void refreshAllScoreboards() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            setScoreboard(player);
        }
    }

    private String applyPlaceholders(String line, Player player) {
        return line.replace("%online_players%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("%player_name%", player.getName())
                .replace("%coins%", String.valueOf(getPlayerCoins(player))); // À lier avec ton système de coins
    }

    private int getPlayerCoins(Player player) {
        return 1000;
    }
}
