package com.samy.superhub;

import com.samy.superhub.hotbar.HotbarListener;
import com.samy.superhub.spawn.SpawnCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperHubPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("spawn").setExecutor(new SpawnCommand());
        getServer().getPluginManager().registerEvents(new HotbarListener(), this);
        getLogger().info("SuperHubPlugin est activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("SuperHubPlugin est désactivé.");
    }
}
