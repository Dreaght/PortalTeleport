package org.dreaght.portalteleport.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PortalManager {
    private static Map<Player, Location[]> playerLocations;

    public PortalManager() {
        playerLocations = new HashMap<>();
    }

    public static boolean hasAllLocations(Player player) {
        Location[] locations = playerLocations.get(player);
        if (locations != null && locations.length == 2) {
            Location locationA = locations[0];
            Location locationB = locations[1];
            return locationA != null && locationB != null;
        }
        return false;
    }

    public static Location getLocationA(Player player) {
        return playerLocations.get(player)[0];
    }

    public static Location getLocationB(Player player) {
        return playerLocations.get(player)[1];
    }

    public static void updateLocationA(Player player, Location newLocationA) {
        Location[] locations = playerLocations.get(player);
        if (locations == null) {
            locations = new Location[2];
        }
        locations[0] = newLocationA;
        playerLocations.put(player, locations);
    }

    public static void updateLocationB(Player player, Location newLocationB) {
        Location[] locations = playerLocations.get(player);
        if (locations == null) {
            locations = new Location[2];
        }
        locations[1] = newLocationB;
        playerLocations.put(player, locations);
    }

    public static Location[] getPlayerLocations(Player player) {
        return playerLocations.get(player);
    }

    public static void removePlayer(Player player) {
        playerLocations.remove(player);
    }
}
