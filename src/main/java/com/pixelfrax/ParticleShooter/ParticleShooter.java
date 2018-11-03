package com.pixelfrax.ParticleShooter;

import org.bukkit.plugin.java.JavaPlugin;

public class ParticleShooter extends JavaPlugin {

    private ParticleShooter plugin;

    public void onEnable() {
        plugin = this;

        registerCommands();
        registerListener();
    }

    public void onDisable() {

    }

    /*
    registers all commands
     */
    private void registerCommands() {

    }

    /*
    registers all listeners
     */
    private void registerListener() {

    }

}
