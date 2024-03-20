package org.dreaght.portalteleport.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.dreaght.portalteleport.commands.arg.*;

import java.util.*;

public class PortalCommand implements CommandExecutor, TabCompleter {
    private final Map<String, AbstractCommand> subcommands;

    public PortalCommand() {
        this.subcommands = new HashMap<>();
        subcommands.put("make", new MakeArg());
        subcommands.put("list", new ListArg());
        subcommands.put("remove", new RemoveArg());
        subcommands.put("menu", new MenuArg());
        subcommands.put("clear", new ClearArg());
        subcommands.put("cancel", new CancelArg());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (args.length > 0 && subcommands.containsKey(args[0].toLowerCase())) {
            subcommands.get(args[0].toLowerCase()).onCommand(sender, command, label, args);
        } else {
            sender.sendMessage(String.format("Usage: /portal %s", subcommands.keySet()));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(subcommands.keySet());
        } else if (subcommands.containsKey(args[0].toLowerCase())) {
            return subcommands.get(args[0].toLowerCase()).onTabComplete(sender, command, alias, args);
        }
        return Collections.emptyList();
    }
}
