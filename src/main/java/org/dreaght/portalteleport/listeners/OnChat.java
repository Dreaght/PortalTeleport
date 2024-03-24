package org.dreaght.portalteleport.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.dreaght.portalteleport.Config;
import org.dreaght.portalteleport.PortalTeleport;
import org.dreaght.portalteleport.states.PlayerChatManager;
import org.dreaght.portalteleport.utils.Region;

public class OnChat implements Listener {
    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();

        if (!PlayerChatManager.containsPLayer(player)) {
            return;
        }

        String message = event.getMessage();
        Region region = PlayerChatManager.getRegion(player);

        PortalTeleport.getCfg().setCommandOfRegion(region.getUUID(), message);
        player.sendMessage(ChatColor.GREEN + "You have set a new command for this portal!");

        event.setCancelled(true);
    }
}
