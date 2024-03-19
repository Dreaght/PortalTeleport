package org.dreaght.portalteleport.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PortalManager {
    private static Map<Player, Region> playerLocations = new HashMap<>();

    public static String getPlayerLocations() {
        return playerLocations.toString();
    }

    public static boolean hasAllLocations(Player player) {
        if (!playerLocations.containsKey(player)) {
            return false;
        }

        Region pair = playerLocations.get(player);

        return pair.getLocation1() != null && pair.getLocation2() != null;
    }

    public static void addPlayer(Player player) {
        if (playerLocations.containsKey(player)) {
            return;
        }

        playerLocations.put(player, new Region(null, null));
    }

    public static Location getLocation1(Player player) {
        return playerLocations.get(player).getLocation1();
    }

    public static void setLocation1(Player player, Location location1) {
        if (!playerLocations.containsKey(player)) {
            playerLocations.put(player, new Region(location1, null));
            return;
        }

        playerLocations.get(player).setLocation1(location1);
    }

    public static Location getLocation2(Player player) {
        return playerLocations.get(player).getLocation2();
    }

    public static void setLocation2(Player player, Location location2) {
        if (!playerLocations.containsKey(player)) {
            playerLocations.put(player, new Region(null, location2));
            return;
        }

        playerLocations.get(player).setLocation2(location2);
    }

    public static void removePlayer(Player player) {
        if (playerLocations.containsKey(player)) {
            playerLocations.remove(player);
        }
    }
}
