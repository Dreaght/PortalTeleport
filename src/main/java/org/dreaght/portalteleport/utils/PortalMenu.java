package org.dreaght.portalteleport.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.dreaght.portalteleport.states.PlayerManager;

import java.util.List;

public class PortalMenu {
    public static void handleMenuCreation(Player player, Region region) {
        Inventory inventory = Bukkit.createInventory(player, 36, ChatColor.DARK_GRAY + "PortalTeleport");

        Cuboid cuboid = new Cuboid(region.getLocation1(), region.getLocation2());

        inventory.setItem(13, PortalItems.REGION_CONFIRM);
        inventory.setItem(16, PortalItems.REGION_DELETE);
        inventory.setItem(31, PortalItems.WINDOW_CLOSE);
        inventory.setItem(10, getRegionInfoItem(region, cuboid));
        inventory.setItem(32, getSettingsItem(region));

        player.openInventory(inventory);
        PlayerManager.addPlayerRegion(player, region);
    }

    private static ItemStack getSettingsItem(Region region) {
        ItemStack regionInfo = PortalItems.REGION_SETTINGS.clone();

        ItemMeta meta = regionInfo.getItemMeta();
        if (meta != null) {
            List<String> lore = meta.getLore();
            if (lore != null) {
                String command = region.getCommand();

                System.out.println(command);

                if (command == null) {
                    lore.add(ChatColor.GRAY + "You've not set a command yet.");
                } else {
                    lore.add(ChatColor.GRAY + "Current command: " + ChatColor.GOLD + "/" + command);
                }
                lore.add(ChatColor.YELLOW + "Click to set command!");
                meta.setLore(lore);
                regionInfo.setItemMeta(meta);
            }
        }

        return regionInfo;
    }

    private static ItemStack getRegionInfoItem(Region region, Cuboid cuboid) {
        ItemStack regionInfo = PortalItems.REGION_INFO.clone();

        ItemMeta meta = regionInfo.getItemMeta();
        if (meta != null) {
            List<String> lore = meta.getLore();
            if (lore != null) {
                lore.add(ChatColor.GRAY + "UUID: " + ChatColor.GOLD + region.getUUID());
                lore.add(ChatColor.GRAY + "Pos1: " + ChatColor.GOLD + cuboid.getMinX()
                        + ", " + cuboid.getMinY() + ", " + cuboid.getMinZ());
                lore.add(ChatColor.GRAY + "Pos2: " + ChatColor.GOLD + cuboid.getMaxX()
                        + ", " + cuboid.getMaxY() + ", " + cuboid.getMaxZ());
                meta.setLore(lore);
                regionInfo.setItemMeta(meta);
            }
        }

        return regionInfo;
    }
}
