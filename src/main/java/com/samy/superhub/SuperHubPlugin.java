package com.samy.superhub;

import com.samy.api.SuperAPI;
import com.samy.superhub.actionbar.ActionBarListener;
import com.samy.superhub.chat.ChatListener;
import com.samy.superhub.hotbar.HotbarListener;
import com.samy.superhub.interactions.InteractionsListener;
import com.samy.superhub.spawn.SpawnCommand;
import com.samy.superhub.spawn.SpawnListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SuperHubPlugin extends JavaPlugin {

    private SuperAPI corePlugin;
    private Map<UUID, List<UUID>> friends;

    @Override
    public void onEnable() {
        corePlugin = (SuperAPI) getServer().getPluginManager().getPlugin("SuperServeurPlugin");
        if(corePlugin == null){
            getLogger().info("SuperServeur n'est pas activé !");
        } else {
            getLogger().info("SuperServeur est activé !");
        }

        enableSpawn();
        enableHotBar();
        enableActionBar();
        enableChat();
        enableInteractions();
        enableRankTab();

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

    private void enableRankTab(){
        corePlugin.createRankTab();
    }

    public void refreshFriends() {
        friends = corePlugin.getFriends();
    }
}
