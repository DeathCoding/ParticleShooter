package com.pixelfrax.ParticleShooter.threads;

import com.pixelfrax.ParticleShooter.GameState;
import com.pixelfrax.ParticleShooter.handler.ThreadHandler;
import com.pixelfrax.ParticleShooter.manager.FileManager;
import com.pixelfrax.ParticleShooter.nms.ActionBarUtil;
import com.pixelfrax.ParticleShooter.nms.Title;
import com.pixelfrax.ParticleShooter.utils.ChatUtil;
import com.pixelfrax.ParticleShooter.utils.ScoreboardUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class IngameThread extends Thread {

    int sec;
    int min;
    private ChatUtil cu;
    private FileManager fm;
    private ActionBarUtil abu;
    private Title title;
    private ScoreboardUtil util;

    public IngameThread(ChatUtil cu, FileManager fm, ScoreboardUtil util) {
        this.cu = cu;
        this.fm = fm;
        this.util = util;

        abu = new ActionBarUtil();
        title = new Title();
    }

    @Override
    public void run() {
        while (GameState.isGameState(GameState.INGAME)) {
//			if(ThreadHandler.isAllowToRun(this)) {
            while (min >= 0) {
                for (sec = 59; sec >= 0; sec--) {

                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (!(sec < 10)) {
                            abu.send(all, ChatColor.GOLD + ">> " + ChatColor.DARK_PURPLE + min + ":" + sec + ChatColor.GOLD + " <<");
                        } else {
                            abu.send(all, ChatColor.GOLD + ">> " + ChatColor.DARK_PURPLE + min + ":0" + sec + ChatColor.GOLD + " <<");
                        }
                    }

                    if (min == 0) {
                        if (sec == 0) {
                            cu.broadCast(ChatColor.AQUA + "Der Spieler " + ChatColor.GOLD + util.bestPlayer() + ChatColor.AQUA + " hat gewonnen!");
                            ThreadHandler.stop();
                            GameState.setGameState(GameState.END);
                        }
                    }

                    if (sec == 0) {
                        min--;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
//		}

}
