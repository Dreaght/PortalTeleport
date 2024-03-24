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

public class RemoveArg extends AbstractCommand {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("remove");
        }

        return completions;
    }

    @Override
    public void commandHandler(Player player, String[] args) {
        if (args.length < 2) {
            sendUsageMessage(player, "remove <region ID>");
            return;
        }

        PortalTeleport.getCfg().removeRegion(args[1]);
        PortalTeleport.getRegionMarkers().cancelMarkingRegion(args[1]);

        player.sendMessage(ChatColor.GREEN + "Done! The portal has been removed.");
    }
}
