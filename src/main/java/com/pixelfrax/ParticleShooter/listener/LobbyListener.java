package com.pixelfrax.ParticleShooter.listener;

import com.pixelfrax.ParticleShooter.GameState;
import com.pixelfrax.ParticleShooter.handler.Game;
import com.pixelfrax.ParticleShooter.manager.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LobbyListener implements Listener {

    private Game game;
    FileManager fm;
    private int minPlayer;

    public LobbyListener(Game game, FileManager fm) {
        this.game = game;
        this.fm = fm;
        minPlayer = fm.getCfg().getInt("minPlayer");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(GameState.isGameState(GameState.LOBBY)) {
            game.setCanStart(Bukkit.getOnlinePlayers().size() >= minPlayer);

            e.getPlayer().getInventory().clear();
            e.getPlayer().getInventory().setArmorContents(null);
            e.getPlayer().setHealth(20D);
            e.getPlayer().setFoodLevel(20);
            e.getPlayer().setFireTicks(0);
            e.getPlayer().setFlying(false);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(GameState.isGameState(GameState.LOBBY)) {
            game.setCanStart(Bukkit.getOnlinePlayers().size() - 1 >= minPlayer);

            e.setQuitMessage(ChatColor.AQUA + "Der Spieler " + ChatColor.GOLD + e.getPlayer().getName() + ChatColor.AQUA + " hat das Spiel verlassen!");
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDamageEvent(EntityDamageEvent e) {
        if(!GameState.isGameState(GameState.INGAME)) {
            e.setCancelled(true);
        }
        e.setCancelled(false);
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

}
