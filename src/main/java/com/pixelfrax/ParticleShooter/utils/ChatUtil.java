package com.pixelfrax.ParticleShooter.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ChatUtil {

    public String prefix;

    /**
     * initialize prefix
     * @param prefix
     */
    public ChatUtil(String prefix) {
        this.prefix = prefix;
    }

    /**
     * sends message to a player
     * @param p
     * @param message
     */
    public void sendMessage(Player p, String message) {
        p.sendMessage(prefix + " " + message);
    }

    /**
     * sends message to all players
     * @param message
     */
    public void broadCast(String message) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage(message);
        }
    }

    /**
     *
     * @return prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * sets the prefix
     * @param prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
