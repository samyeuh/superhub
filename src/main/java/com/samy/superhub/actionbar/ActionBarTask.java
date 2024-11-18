package com.samy.superhub.actionbar;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ActionBarTask extends BukkitRunnable {

    private final Player player;
    private final List<String> messages;
    private final Random random;
    private String lastMessage = "";

    public ActionBarTask(Player player, List<String> messages) {
        this.player = player;
        this.messages = messages;
        this.random = new Random();

    }

    @Override
    public void run() {
        String message;
        do {
            int index = random.nextInt(1, messages.size() +1);
            message = messages.get(random.nextInt(index));
        }while(message.equals(lastMessage));

        lastMessage = message;
        message = replacePlaceholders(ChatColor.AQUA + message);
        printMessageToPlayer(message);
    }

    private void printMessageToPlayer(String msg){
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(msg));
    }

    private String replacePlaceholders(String message) {
        if (message.contains("%playerMaj%")) {
            return message.replace("%playerMaj%", ChatColor.GOLD + player.getName().toUpperCase() + ChatColor.AQUA);
        }
        if (message.contains("%player%")) {
            return message.replace("%player%", ChatColor.GOLD + player.getName() + ChatColor.AQUA);
        }

        return message;
    }
}
