package com.samy.superhub.actionbar;

import com.samy.superhub.SuperHubPlugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class ActionBarListener implements Listener {

    private SuperHubPlugin plugin;

    public ActionBarListener(SuperHubPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        List<String> messages = plugin.getConfig().getStringList("actionbar.messages");
        ActionBarTask actionBar = new ActionBarTask(player, messages);
        actionBar.runTaskTimer(plugin, 0, 100);
    }
}
