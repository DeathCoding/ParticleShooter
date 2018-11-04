package com.pixelfrax.ParticleShooter.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LocationUtil {

    private Player p;
    private File f;
    private FileConfiguration cfg;

    /**
     *
     creates File
     sets configuration
     initialize player
     * @param p
     */
    public LocationUtil(Player p) {
        this.p = p;
        f = new File("plugins/PixelShooter", "locs.yml");
        cfg = YamlConfiguration.loadConfiguration(f);
    }

    public LocationUtil() { }

    /**
     * sets the location
     * @param path
     */
    public void setLocation(String path) {
        cfg.set(path + ".world", p.getLocation().getWorld().getName());
        cfg.set(path + ".x", p.getLocation().getX());
        cfg.set(path + ".y", p.getLocation().getY());
        cfg.set(path + ".z", p.getLocation().getZ());
        cfg.set(path + ".pitch", p.getLocation().getPitch());
        cfg.set(path + ".yaw", p.getLocation().getYaw());

        try {
            cfg.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns the location
     * @param path
     * @return
     */
    public Location getLocation(String path) {
        String world = cfg.getString(path + ".world");
        double x = cfg.getDouble(path + ".x");
        double y = cfg.getDouble(path + ".y");
        double z = cfg.getDouble(path + ".z");
        double pitch = cfg.getDouble(path + ".pitch");
        double yaw = cfg.getDouble(path + ".yaw");

        Location loc = new Location(Bukkit.getWorld(world), x, y ,z);
        loc.setPitch((float) pitch);
        loc.setYaw((float) yaw);

        return loc;
    }

    public Player getP() {
        return p;
    }

    public void setP(Player p) {
        this.p = p;
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }

    public FileConfiguration getCfg() {
        return cfg;
    }

    public void setCfg(FileConfiguration cfg) {
        this.cfg = cfg;
    }
}
