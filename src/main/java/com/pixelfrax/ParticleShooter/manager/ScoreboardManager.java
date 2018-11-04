package com.pixelfrax.ParticleShooter.manager;

import com.pixelfrax.ParticleShooter.utils.ScoreboardUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class ScoreboardManager {

    ScoreboardUtil su;

    public ScoreboardManager() { }

    public void update() {
        su = new ScoreboardUtil(ChatColor.AQUA + "Ingame-Stats", DisplaySlot.SIDEBAR);
        for(Player all : Bukkit.getOnlinePlayers()) {
            for(Player players : Bukkit.getOnlinePlayers()) {
                su.addScore(ChatColor.GOLD + all.getName(), KillManager.getKills(all));
                players.setScoreboard(su.build());
            }
        }
    }

}
