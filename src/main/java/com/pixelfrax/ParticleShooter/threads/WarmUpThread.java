package com.pixelfrax.ParticleShooter.threads;

import com.pixelfrax.ParticleShooter.GameState;
import com.pixelfrax.ParticleShooter.handler.ThreadHandler;
import com.pixelfrax.ParticleShooter.nms.Title;
import com.pixelfrax.ParticleShooter.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class WarmUpThread extends Thread {

    private int time;
    private ChatUtil cu;
    private Title title;

    public WarmUpThread(int time, ChatUtil cu) {
        this.time = time;
        this.cu = cu;
        title = new Title();
    }

    @Override
    public void run() {
        while (GameState.isGameState(GameState.WARMUP)) {
            if (ThreadHandler.isAllowToRun(this)) {
                for (; time >= 0; time--) {
                    if (time <= 10 && time > 0) {
                        cu.broadCast(ChatColor.AQUA + "Spiel startet in " + ChatColor.GOLD + time + ChatColor.AQUA + " sekunden!");
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            title.send(all, ChatColor.GREEN + "" + time, "", 0, 1, 0);
                            all.playSound(all.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
                        }
                    }

                    if (time == 0) {
                        cu.broadCast(ChatColor.AQUA + "Last die Spiele beginnen!");
                        GameState.setGameState(GameState.INGAME);

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            title.send(all, ChatColor.GREEN + "Last die...", ChatColor.RED + "Spiele beginnen!", 1, 2, 1);
                            all.playSound(all.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1, 1);
                        }

                        ThreadHandler.stop();

                        Thread t = new Thread(new IngameThread());
                        ThreadHandler.addThread(t);
                        t.start();


                        for (Player all : Bukkit.getOnlinePlayers()) {
                            //TODO: Give weapons
                        }
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
}
