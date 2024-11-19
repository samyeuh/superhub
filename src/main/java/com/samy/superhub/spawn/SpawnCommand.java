package com.samy.superhub.spawn;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)){
            return false;
        }
        Player player = (Player) sender;
        Location spawn = new Location(player.getWorld(), 10.5, -58, 8.5, 177f, 0.0f);
        player.teleport(spawn);
        return false;
    }
}