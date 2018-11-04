package com.pixelfrax.ParticleShooter.threads;

import com.pixelfrax.ParticleShooter.GameState;
import com.pixelfrax.ParticleShooter.handler.Game;
import com.pixelfrax.ParticleShooter.handler.ThreadHandler;
import com.pixelfrax.ParticleShooter.manager.FileManager;
import com.pixelfrax.ParticleShooter.nms.Title;
import com.pixelfrax.ParticleShooter.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LobbyThread extends Thread {

    private int time;
    private ChatUtil cu;
    private Game game;
    private FileManager fm;
    private Title title;

    public LobbyThread(ChatUtil cu, Game game, FileManager fm) {
        this.cu = cu;
        this.game = game;
        this.fm = fm;

        time = fm.getCfg().getInt("countdown_lobby");
        title = new Title();
    }

    @Override
    public void run() {
        while(GameState.isGameState(GameState.LOBBY)) {

            if(game.hasStarted()) {
                try {
                    this.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }

            if(ThreadHandler.isAllowToRun(this)) {
                cu.broadCast(ChatColor.AQUA + "Es sind nicht genug Spieler online, mindestents 6 Spieler (" + Bukkit.getOnlinePlayers().size() + "/12");
                return;
            }

            for(; time >= 0; time --) {
                if(time % 10 == 0 || time <= 10) {
                    cu.broadCast(ChatColor.AQUA + "Das Spiel startet in " + ChatColor.GOLD + time + ChatColor.AQUA + " sekunden!");
                }

                if(time <= 10 && time > 0) {
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        title.send(all, ChatColor.GREEN + "" + time, "", 0, 1, 0);
                        all.playSound(all.getLocation(), Sound.BLOCK_NOTE_SNARE, 1, 1);
                    }
                }

                if(time == 0) {
                    cu.broadCast(ChatColor.AQUA + "Das Spiel startet jetzt!");
                    game.start();
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        all.getInventory().clear();
                    }
                }

                for(Player all : Bukkit.getOnlinePlayers()) {
                    all.setLevel(time);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
