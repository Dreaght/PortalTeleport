package org.dreaght.portalteleport.listeners;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.dreaght.portalteleport.Config;
import org.dreaght.portalteleport.PortalTeleport;
import org.dreaght.portalteleport.states.RegionMarkers;
import org.dreaght.portalteleport.utils.PortalItems;
import org.dreaght.portalteleport.states.PortalManager;
import org.dreaght.portalteleport.utils.Region;

import java.util.UUID;

public class OnItemUse implements Listener {
    private final Plugin plugin;

    public OnItemUse(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack itemStack = event.getItem();

        if (itemStack == null || !(itemStack.isSimilar(PortalItems.BLUE_POINT) ||
                itemStack.isSimilar(PortalItems.RED_POINT))) {
            return;
        }

        if (illegalItemCheck(player)) { return; }

        handleClickedPosition(event, player);

        if (PortalManager.hasAllLocations(player)) {
            handlePortalCreation(player);
        }

        event.setCancelled(true);
    }

    private void handleClickedPosition(PlayerInteractEvent event, Player player) {
        if (event.getClickedBlock() == null) { return; }
        Location blockLocation = event.getClickedBlock().getLocation();

        if (event.getAction().name().contains("LEFT")) {
            PortalManager.setLocation1(player, blockLocation);
            player.getInventory().setItemInMainHand(PortalItems.RED_POINT);
        } else if (event.getAction().name().contains("RIGHT")) {
            PortalManager.setLocation2(player, blockLocation);
            player.getInventory().setItemInMainHand(PortalItems.BLUE_POINT);
        }
    }

    private void handlePortalCreation(Player player) {
        player.getInventory().setItemInMainHand(null);

        String portalID = UUID.randomUUID().toString();

        Config config = PortalTeleport.getCfg();

        config.createRegion(portalID);
        config.setPos1(portalID, PortalManager.getLocation1(player));
        config.setPos2(portalID, PortalManager.getLocation2(player));

        Region region = PortalManager.getRegion(player);
        region.setUUID(portalID);

        PortalTeleport.getRegionMarkers().startMarkingRegion(region);

        PortalManager.removePlayer(player);

        sendPortalCreationMessage(player, portalID);
    }

    private boolean illegalItemCheck(Player player) {
        boolean contains = PortalManager.containsPlayer(player);

        if (!contains) {
            player.getInventory().setItemInMainHand(null);
            return true;
        }

        return false;
    }

    private void sendClickableCommand(Player player, String message, String command) {
        TextComponent component = new TextComponent(
                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + command));

        player.spigot().sendMessage(component);
    }

    private void sendPortalCreationMessage(Player player, String uuid) {
        player.sendMessage("");
        player.sendMessage(ChatColor.GREEN + "The portal has been created!");
        player.sendMessage(ChatColor.GRAY + "(Press Shift while in the portal to Open Menu)");
        player.sendMessage("");
        sendClickableCommand(player, (ChatColor.GREEN + ">>> Or click to this message! <<<"), ("portal menu " + uuid));
        player.sendMessage("");
    }
}
