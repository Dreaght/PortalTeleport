package org.dreaght.portalteleport.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.dreaght.portalteleport.Config;
import org.dreaght.portalteleport.PortalTeleport;
import org.dreaght.portalteleport.utils.Region;

import java.util.List;
import java.util.Objects;

public class OnMove implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        List<Region> regions = PortalTeleport.getCfg().getAllRegions();

        for (Region region : regions) {
            if (region.inRegion(Objects.requireNonNull(event.getTo()))) {
                String command = region.getCommand();
                if (command == null || !region.isConfirmed()) {
                    return;
                }

                player.chat("/" + command);
                return;
            }
        }
    }
}
