package com.pixelfrax.ParticleShooter.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class KillManager {
    private HashMap<Player, Integer> kills = new HashMap<Player, Integer>();

    public void addKills(Player player, Integer amount) {
        kills.put(player, getKills(player) + amount);
    }

    public Integer getKills(Player player) {
        if(kills.containsKey(player)) {
            return kills.get(player);
        } else {
            return 0;
        }
    }
}
