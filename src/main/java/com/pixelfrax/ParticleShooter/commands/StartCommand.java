package com.pixelfrax.ParticleShooter.commands;

import com.pixelfrax.ParticleShooter.handler.Game;
import com.pixelfrax.ParticleShooter.handler.ThreadHandler;
import com.pixelfrax.ParticleShooter.manager.FileManager;
import com.pixelfrax.ParticleShooter.threads.LobbyThread;
import com.pixelfrax.ParticleShooter.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {

    private FileManager fm;
    private ChatUtil cu;
    private Game game;

    public StartCommand(FileManager fm, ChatUtil cu, Game game) {
        this.fm = fm;
        this.cu = cu;
        this.game = game;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            if (cmd.getName().equalsIgnoreCase("start")) {
                if (player.hasPermission("start")) {
                    game.setCanStart(true);
                    Thread t = new Thread(new LobbyThread(cu, game, fm));
                    ThreadHandler.addThread(t);
                    t.start();
                }
            }
        }
        return true;
    }

}
