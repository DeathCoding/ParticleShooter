package com.pixelfrax.ParticleShooter.commands;

import com.pixelfrax.ParticleShooter.utils.ChatUtil;
import com.pixelfrax.ParticleShooter.utils.LocationUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {

    private ChatUtil cu;
    private LocationUtil lu;

    public SetupCommand(ChatUtil cu) {
        this.cu = cu;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return true;
        }

        Player p = (Player)commandSender;
        lu = new LocationUtil(p);

        if (strings.length == 0) {
            if (command.getName().equalsIgnoreCase("setup")) {
                cu.sendMessage(p, ChatColor.AQUA + "/setup lobby");
                cu.sendMessage(p, ChatColor.AQUA + "/setup set <1-12>");
            }
        }

        if (strings.length == 1) {
            if (strings[0].equalsIgnoreCase("lobby")) {
                lu.setLocation("lobby");
                cu.sendMessage(p, ChatColor.AQUA + "Lobby spawn erfollgeich gesetzt!");
            }
        }

        if (strings.length == 2) {
            if (strings[0].equalsIgnoreCase("set")) {
                int count = 0;
                try {
                    count = Integer.parseInt(strings[0]);

                    if (count < 0 || count > 12) {
                        cu.sendMessage(p, ChatColor.AQUA + "Die Spawn ID muss zwischen 1 und 12 sein!");
                    }
                } catch (NumberFormatException e) {
                    cu.sendMessage(p, ChatColor.AQUA + "Die eingegebene Spawn ID ist nicht valid!");
                }

                lu.setLocation("spawn." + count);
                cu.sendMessage(p, ChatColor.AQUA + "Spawn ID: " + ChatColor.GOLD + count + ChatColor.AQUA + " wurde gesetzt!");
            }
        }
        return true;
    }
}
