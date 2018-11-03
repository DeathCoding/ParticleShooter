package com.pixelfrax.ParticleShooter;

import com.pixelfrax.ParticleShooter.commands.SetupCommand;
import com.pixelfrax.ParticleShooter.manager.FileManager;
import com.pixelfrax.ParticleShooter.utils.ChatUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class ParticleShooter extends JavaPlugin {

    private FileManager fm;
    private ParticleShooter plugin;
    private ChatUtil cu;

    public void onEnable() {
        plugin = this;

        registerCommands();
        registerListener();

        fm = new FileManager("plugins/ParticleShooter");
        cu = new ChatUtil(fm.getCfg().getString("pluginPrefix"));
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
        ///ddd
    }

}
