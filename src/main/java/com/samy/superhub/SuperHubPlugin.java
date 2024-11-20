package com.samy.superhub;

import com.samy.superhub.actionbar.ActionBarListener;
import com.samy.superhub.chat.ChatListener;
import com.samy.superhub.hotbar.HotbarListener;
import com.samy.superhub.spawn.SpawnCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperHubPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        enableSpawn();
        enableHotBar();
        enableActionBar();
        enableChat();

        getLogger().info("SuperHubPlugin est activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("SuperHubPlugin est désactivé.");
    }

    private void enableSpawn(){
        getCommand("spawn").setExecutor(new SpawnCommand());
    }

    private void enableHotBar(){
        getServer().getPluginManager().registerEvents(new HotbarListener(), this);
    }

    private void enableActionBar(){
        getServer().getPluginManager().registerEvents(new ActionBarListener(this), this);
    }

    private void enableChat(){
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }
}
