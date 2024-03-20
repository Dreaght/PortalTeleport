package org.dreaght.portalteleport.listeners;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.dreaght.portalteleport.Config;
import org.dreaght.portalteleport.PortalTeleport;
import org.dreaght.portalteleport.states.PlayerChatManager;
import org.dreaght.portalteleport.states.PlayerManager;
import org.dreaght.portalteleport.utils.PortalItems;
import org.dreaght.portalteleport.utils.Region;

import java.util.Objects;

public class MenuListener implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (!(event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "PortalTeleport"))) {
            return;
        }

        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null) {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        handleClickedItem(player, clickedItem);

        event.setCancelled(true);
    }

    private void handleClickedItem(Player player, ItemStack clickedItem) {
        Region region = PlayerManager.getPlayerRegion(player);
        Config config = new Config();

        if (region == null) {
            player.closeInventory();
            return;
        }

        if (clickedItem.isSimilar(PortalItems.WINDOW_CLOSE)) {
            player.closeInventory();
        } else if (clickedItem.isSimilar(PortalItems.REGION_CONFIRM)) {
            config.setConfirmed(region.getUUID(), true);
            PlayerManager.removePlayer(player);
            PortalTeleport.getRegionMarkers().cancelMarkingRegion(region.getUUID());
        } else if (clickedItem.isSimilar(PortalItems.REGION_DELETE)) {
            config.removeRegion(region.getUUID());
            PlayerManager.removePlayer(player);
            PortalTeleport.getRegionMarkers().cancelMarkingRegion(region.getUUID());
        } else if (Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName().equals(ChatColor.GREEN + "Action")) {
            player.sendMessage("");
            player.sendMessage(ChatColor.YELLOW + "Enter the command below! " + ChatColor.RED + "Don't use '/'.");
            sendClickableCommand(player, (ChatColor.RED + "[CANCEL]"), "portal cancel");

            PlayerChatManager.addPlayer(player, region);
            PlayerManager.removePlayer(player);
        }

        player.closeInventory();
    }

    private void sendClickableCommand(Player player, String message, String command) {
        TextComponent component = new TextComponent(
                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + command));

        player.spigot().sendMessage(component);
    }
}
