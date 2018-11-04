package com.pixelfrax.ParticleShooter.handler;

import com.pixelfrax.ParticleShooter.GameState;
import com.pixelfrax.ParticleShooter.threads.WarmUpThread;
import com.pixelfrax.ParticleShooter.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Game {

    private boolean canstart = false;
    private boolean hasstarted = false;
    private LocationUtil lu;

    public Game() {
        lu = new LocationUtil();
    }

    public void setCanStart(boolean can) {
        canstart = can;
    }

    public void setHasStarted(boolean can) {
        hasstarted = can;
    }

    /**
     * starts the game
     */
    public void start() {
        setHasStarted(true);
        int counts = 1;

        if(hasstarted) {
            for(Player player : Bukkit.getOnlinePlayers()) {

                player.teleport(lu.getLocation("spawn." + counts));
                counts++;
            }

            GameState.setGameState(GameState.WARMUP);
            Thread t = new Thread(new WarmUpThread());
			ThreadHandler.stop();
            ThreadHandler.addThread(t);
            t.start();
        }
    }

    public boolean canStart() {
        return canstart;
    }

    public boolean hasStarted() {
        return hasstarted;
    }

    //TODO: When game stops
    public static void stop() {

    }

}
