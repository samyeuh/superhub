package com.samy.superhub.actionbar;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.List;
import java.util.Random;

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
            int index = random.nextInt(messages.size());
            message = messages.get(index);
        }while(message.equals(lastMessage));

        lastMessage = message;
        message = replacePlaceholders(ChatColor.AQUA + message);
        printMessageToPlayer(message);
    }

    private void printMessageToPlayer(String msg){
        IChatBaseComponent chatComponent = new ChatComponentText(msg);
        // Créer le paquet pour la barre d'action (type 2 = ActionBar)
        PacketPlayOutChat packet = new PacketPlayOutChat(chatComponent, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
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
