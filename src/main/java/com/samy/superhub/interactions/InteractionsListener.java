package com.samy.superhub.interactions;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class InteractionsListener implements Listener {

    @EventHandler
    public void onInteractPlayer(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        if (event.getRightClicked() instanceof Player){
            Player target = (Player) event.getRightClicked();
            InteractionsManager.interactWithPlayer(player, target);
        } else {
            event.setCancelled(true);
        }
    }
}
