package com.samy.superhub;

import com.samy.superhub.actionbar.ActionBarListener;
import com.samy.superhub.chat.ChatListener;
import com.samy.superhub.hotbar.HotbarListener;
import com.samy.superhub.interactions.InteractionsListener;
import com.samy.superhub.spawn.SpawnCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SuperHubPlugin extends JavaPlugin {

    private Plugin corePlugin;
    private Map<UUID, List<UUID>> friends;

    @Override
    public void onEnable() {
        corePlugin = getServer().getPluginManager().getPlugin("SuperServeurPlugin");
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

    public void refreshFriends() {
        try {
            Method getFriends = corePlugin.getClass().getMethod("getFriends");
            friends = (Map<UUID, List<UUID>>) getFriends.invoke(corePlugin);
        } catch (Exception e) {
            getLogger().warning("Impossible de récupérer la liste d'amis.");
            throw new RuntimeException(e);
        }
    }
}
