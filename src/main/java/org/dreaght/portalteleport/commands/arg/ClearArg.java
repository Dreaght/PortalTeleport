package org.dreaght.portalteleport.commands.arg;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.dreaght.portalteleport.Config;
import org.dreaght.portalteleport.PortalTeleport;
import org.dreaght.portalteleport.commands.AbstractCommand;

import java.util.ArrayList;
import java.util.List;

public class ClearArg extends AbstractCommand {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("clear");
        }

        return completions;
    }

    @Override
    public void commandHandler(Player player, String[] args) {
        PortalTeleport.getCfg().clearAllRegions();
        player.sendMessage(ChatColor.GREEN + "All portals have been removed.");
    }
}
