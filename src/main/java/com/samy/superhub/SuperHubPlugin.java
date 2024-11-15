package com.samy.superhub;

import org.bukkit.plugin.java.JavaPlugin;

public class SuperHubPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("SuperHubPlugin est activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("SuperHubPlugin est désactivé.");
    }
}
