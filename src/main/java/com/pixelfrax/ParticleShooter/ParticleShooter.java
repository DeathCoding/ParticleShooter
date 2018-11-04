package com.pixelfrax.ParticleShooter;

import com.pixelfrax.ParticleShooter.commands.SetupCommand;
import com.pixelfrax.ParticleShooter.handler.Game;
import com.pixelfrax.ParticleShooter.listener.LobbyListener;
import com.pixelfrax.ParticleShooter.manager.FileManager;
import com.pixelfrax.ParticleShooter.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ParticleShooter extends JavaPlugin {

    private FileManager fm;
    private ParticleShooter plugin;
    private ChatUtil cu;
    private Game game;

    public void onEnable() {
        plugin = this;

        registerCommands();
        registerListener();

        fm = new FileManager("plugins/ParticleShooter");
        cu = new ChatUtil(fm.getCfg().getString("pluginPrefix"));
        game = new Game();
    }

    public void onDisable() {

    }

    /*
    registers all commands
     */
    private void registerCommands() {
        getCommand("setup").setExecutor(new SetupCommand(cu));
    }

    /*
    registers all listeners
     */
    private void registerListener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new LobbyListener(game, fm), this);
    }

}
