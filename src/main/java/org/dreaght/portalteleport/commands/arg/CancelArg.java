package org.dreaght.portalteleport.commands.arg;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dreaght.portalteleport.commands.AbstractCommand;
import org.dreaght.portalteleport.states.PlayerChatManager;

import java.util.List;

public class CancelArg extends AbstractCommand {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

    @Override
    public void commandHandler(Player player, String[] args) {
        PlayerChatManager.removePlayer(player);
        player.sendMessage(ChatColor.RED + "You have canceled text input.");
    }
}
