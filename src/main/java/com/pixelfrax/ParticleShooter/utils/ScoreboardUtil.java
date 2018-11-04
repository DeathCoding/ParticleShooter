package com.pixelfrax.ParticleShooter.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ScoreboardUtil {

    private static Objective objective;
    private Scoreboard scoreboard;

    public ScoreboardUtil(String name, DisplaySlot slot) {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective(name,"dummy");
        objective.setDisplaySlot(slot);
    }

    public ScoreboardUtil addScore(String name , int score) {
        objective.getScore(name).setScore(score);
        return this;
    }

    public ScoreboardUtil setScore(String scoreName , int newScore) {
        objective.getScore(scoreName).setScore(newScore);
        return this;
    }

    public static HashMap<String, Integer> playerscores = new HashMap<String, Integer>();

    @SuppressWarnings("deprecation")
    public String bestPlayer() {
        for(Player all : Bukkit.getOnlinePlayers()) {
            playerscores.put(all.getName(), objective.getScore(all).getScore());
        }
        ArrayList<Integer> values = new ArrayList<Integer>();
        for(Integer all : playerscores.values()) {
            values.add(all);
        }
        Collections.sort(values);
        Collections.reverse(values);
        return getKey(values.get(0), playerscores);
    }

    public String getKey(Integer value, HashMap<String, Integer> hs) {
        String key = "";
        for(String keys : hs.keySet()) {
            if(hs.get(keys) == value) {
                key = keys;
            }
        }

        return key;
    }

    public Scoreboard build() {
        return scoreboard;
    }

}
