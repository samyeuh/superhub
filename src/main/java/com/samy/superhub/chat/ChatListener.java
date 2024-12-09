package com.samy.superhub.chat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        String playerName = event.getPlayer().getDisplayName();
        String message = event.getMessage();
        event.setFormat(playerName + ": " + message);
    }
}
