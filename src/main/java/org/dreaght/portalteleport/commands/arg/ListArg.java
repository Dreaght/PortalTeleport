package org.dreaght.portalteleport.commands.arg;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dreaght.portalteleport.Config;
import org.dreaght.portalteleport.PortalTeleport;
import org.dreaght.portalteleport.commands.AbstractCommand;
import org.dreaght.portalteleport.utils.Cuboid;
import org.dreaght.portalteleport.utils.Region;

import java.util.ArrayList;
import java.util.List;

public class ListArg extends AbstractCommand {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("list");
        }

        return completions;
    }

    @Override
    public void commandHandler(Player player, String[] args) {
        List<Region> regions = PortalTeleport.getCfg().getAllRegions();

        if (regions.isEmpty()) {
            player.sendMessage(ChatColor.RED + "There is no portals yet.");
            return;
        }
        player.sendMessage(ChatColor.GREEN + "Portals list:");

        for (Region region : regions) {
            Cuboid cuboid = new Cuboid(region.getLocation1(), region.getLocation2());

            TextComponent message = new TextComponent(ChatColor.GOLD + region.getUUID());
            BaseComponent[] hoverComponents = new BaseComponent[]{
                    new TextComponent(ChatColor.GRAY + "Loc1: " + ChatColor.GOLD + cuboid.getMinX()
                            + ", " + cuboid.getMinY() + ", " + cuboid.getMinZ() + "\n"),
                    new TextComponent(ChatColor.GRAY + "Loc2: " + ChatColor.GOLD + cuboid.getMinX()
                            + ", " + cuboid.getMinY() + ", " + cuboid.getMinZ())
            };
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverComponents));
            player.spigot().sendMessage(message);
        }
    }
}
