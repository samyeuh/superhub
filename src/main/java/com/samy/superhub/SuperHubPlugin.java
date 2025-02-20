package com.samy.superhub;

import com.samy.api.SuperAPI;
import com.samy.superhub.actionbar.ActionBarListener;
import com.samy.superhub.chat.ChatListener;
import com.samy.superhub.hotbar.HotbarListener;
import com.samy.superhub.interactions.InteractionsListener;
import com.samy.superhub.scoreboard.HubScoreboardManager;
import com.samy.superhub.scoreboard.ScoreboardListener;
import com.samy.superhub.spawn.SpawnCommand;
import com.samy.superhub.spawn.SpawnListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SuperHubPlugin extends JavaPlugin {

    private SuperAPI api;
    private Map<UUID, List<UUID>> friends;

    @Override
    public void onEnable() {
        api = SuperAPI.getInstance();

        enableSpawn();
        enableHotBar();
        enableActionBar();
        enableChat();
        enableInteractions();
       // enableRankTab();
        enableScoreboard();

        getLogger().info("SuperHubPlugin est activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("SuperHubPlugin est désactivé.");
    }

    private void enableSpawn(){
        getCommand("spawn").setExecutor(new SpawnCommand());
        getServer().getPluginManager().registerEvents(new SpawnListener(), this);
    }

    private void enableHotBar(){
        refreshFriends();
        getServer().getPluginManager().registerEvents(new HotbarListener(this, friends), this);
    }

    private void enableActionBar(){
        getServer().getPluginManager().registerEvents(new ActionBarListener(this), this);
    }

    private void enableChat(){
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    private void enableInteractions(){
        getServer().getPluginManager().registerEvents(new InteractionsListener(), this);
    }

//    private void enableRankTab(){
//        api.createRankTab();
//    }

    private void enableScoreboard(){
        HubScoreboardManager hubScoreboardManager = new HubScoreboardManager(api.getScoreboardManager());
        getServer().getPluginManager().registerEvents(new ScoreboardListener(hubScoreboardManager), this);
    }

    public void refreshFriends() {
        friends = api.getFriendsManager().getFriendsMap();
    }
}
