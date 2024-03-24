package org.dreaght.portalteleport.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.dreaght.portalteleport.Config;
import org.dreaght.portalteleport.PortalTeleport;
import org.dreaght.portalteleport.utils.PortalMenu;
import org.dreaght.portalteleport.utils.Region;

import java.util.List;
import java.util.Objects;

public class OnSneak implements Listener {
    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();

        if (!event.isSneaking() || !player.isOnGround()) {
            return;
        }

        List<Region> regions = PortalTeleport.getCfg().getAllRegions();

        for (Region region : regions) {
            if (region.inRegion(Objects.requireNonNull(player.getLocation()))) {
                if (region.isConfirmed()) {
                    return;
                }

                PortalMenu.handleMenuCreation(player, region);
                return;
            }
        }
    }
}
