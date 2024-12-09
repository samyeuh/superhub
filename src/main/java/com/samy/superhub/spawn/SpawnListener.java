package com.samy.superhub.spawn;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class SpawnListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Location spawn = new Location(player.getWorld(), 193.5, 5, 970.5, 180f, 0.0f);
        player.teleport(spawn);
        player.setGameMode(GameMode.ADVENTURE);
    }

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent event){
        event.setCancelled(true);
    }
}
