package org.dreaght.portalteleport.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.dreaght.portalteleport.commands.arg.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortalCommand implements CommandExecutor, TabCompleter {
    private final Map<String, AbstractCommand> subcommands;

    public PortalCommand() {
        this.subcommands = new HashMap<>();
        subcommands.put("make", new MakeArg());
        subcommands.put("remove", new RemoveArg());
        subcommands.put("list", new ListArg());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && subcommands.containsKey(args[0].toLowerCase())) {
            subcommands.get(args[0].toLowerCase()).onCommand(sender, command, label, args);
        } else {
            sender.sendMessage(String.format("Usage: /portal %s", subcommands.keySet()));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
