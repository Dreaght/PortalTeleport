package org.dreaght.portalteleport.commands.arg;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dreaght.portalteleport.Config;
import org.dreaght.portalteleport.PortalTeleport;
import org.dreaght.portalteleport.commands.AbstractCommand;
import org.dreaght.portalteleport.utils.PortalMenu;
import org.dreaght.portalteleport.utils.Region;

import java.util.ArrayList;
import java.util.List;

public class MenuArg extends AbstractCommand {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("menu");
        }

        return completions;
    }

    @Override
    public void commandHandler(Player player, String[] args) {
        if (args.length < 2) {
            sendUsageMessage(player, "menu <region ID>");
            return;
        }

        Config config = new Config();
        if (!config.isRegionExist(args[1])) {
            player.sendMessage(ChatColor.RED + "That portal doesn't exist.");
            return;
        }

        Region region = config.getRegionByUUID(args[1]);

        PortalMenu.handleMenuCreation(player, region);
    }
}
