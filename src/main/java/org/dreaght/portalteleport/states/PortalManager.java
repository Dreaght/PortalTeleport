package org.dreaght.portalteleport.states;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.dreaght.portalteleport.utils.Region;

import java.util.HashMap;
import java.util.Map;

public class PortalManager {
    private static Map<Player, Region> playerLocations = new HashMap<>();

    public static Region getRegion(Player player) {
        return playerLocations.get(player);
    }

    public static boolean containsPlayer(Player player) {
        return playerLocations.containsKey(player);
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
            return;
        }

        playerLocations.get(player).setLocation1(location1);
    }

    public static Location getLocation2(Player player) {
        return playerLocations.get(player).getLocation2();
    }

    public static void setLocation2(Player player, Location location2) {
        if (!playerLocations.containsKey(player)) {
            return;
        }

        playerLocations.get(player).setLocation2(location2);
    }

    public static void removePlayer(Player player) {
        playerLocations.remove(player);
    }
}
