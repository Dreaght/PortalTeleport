package org.dreaght.portalteleport.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class AbstractCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return false;
        if (args.length == 0) return false;

        Player player = ((Player) commandSender).getPlayer();

        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null!");
        }

        if (!player.isOp()) {
            return false;
        }

        commandHandler(player, args);

        return true;
    }

    public abstract List<String> onTabComplete(CommandSender sender, Command command,
                                      String alias, String[] args);

    public void sendUsageMessage(Player player, String message) {
        player.sendMessage(String.format("Usage: /zombies %s", message));
    }

    public abstract void commandHandler(Player player, String[] args);
}
