package org.dreaght.portalteleport.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.dreaght.portalteleport.Config;
import org.dreaght.portalteleport.utils.PortalItems;
import org.dreaght.portalteleport.utils.PortalManager;

import java.util.UUID;

public class OnItemUse implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack itemStack = event.getItem();

        if (itemStack == null || !(itemStack.isSimilar(PortalItems.BLUE_POINT) ||
                itemStack.isSimilar(PortalItems.RED_POINT))) {
            return;
        }

        if (event.getClickedBlock() == null) { return; }
        Location blockLocation = event.getClickedBlock().getLocation();

        if (event.getAction().name().contains("LEFT")) {
            PortalManager.updateLocationA(player, blockLocation);
            player.getInventory().setItemInMainHand(PortalItems.RED_POINT);
        } else if (event.getAction().name().contains("RIGHT")) {
            PortalManager.updateLocationB(player, blockLocation);
            player.getInventory().setItemInMainHand(PortalItems.BLUE_POINT);
        }

        if (PortalManager.hasAllLocations(player)) {
            handlePortalCreation(player);
        }
    }

    private void handlePortalCreation(Player player) {
        player.getInventory().setItemInMainHand(null);
        PortalManager.removePlayer(player);

        String portalID = UUID.randomUUID().toString();

        Config config = new Config();

        config.createRegion(portalID);
        config.setPos1(portalID, PortalManager.getLocationA(player));
        config.setPos2(portalID, PortalManager.getLocationB(player));
    }
}
