package com.pixelfrax.ParticleShooter.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private File f;
    private FileConfiguration cfg;
    private String path;

    /**
     *initializes path
     * creates new File
     * sets configuration
     * @param path
     */
    public FileManager(String path) {
        this.path = path;
        f = new File(path, "cfg.yml");
        cfg = YamlConfiguration.loadConfiguration(f);
    }

    /**
     * setups the default cfg
     */
    public void setDefaults() {
        cfg.options().copyDefaults(true);

        cfg.addDefault("pluginPrefix", "[PixelShooter]");
        cfg.addDefault("countdown_lobby", 60);
        cfg.addDefault("countdown_warmup", 10);
        cfg.addDefault("minPlayer", 6);

        try {
            cfg.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return the file
     */
    public File getF() {
        return f;
    }

    /**
     * sets the file
     * @param f
     */
    public void setF(File f) {
        this.f = f;
    }

    /**
     *
     * @return the configuration
     */
    public FileConfiguration getCfg() {
        return cfg;
    }

    /**
     * sets the Config
     * @param cfg
     */
    public void setCfg(FileConfiguration cfg) {
        this.cfg = cfg;
    }

    /**
     *
     * @return the files path
     */
    public String getPath() {
        return path;
    }

    /**
     * sets the files path
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }
}
