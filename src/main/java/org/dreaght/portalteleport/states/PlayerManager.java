package org.dreaght.portalteleport.states;

import org.bukkit.entity.Player;
import org.dreaght.portalteleport.utils.Region;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private static Map<Player, Region> playerRegionMap = new HashMap<>();

    public static Region getPlayerRegion(Player player) {
        return playerRegionMap.get(player);
    }

    public static void addPlayerRegion(Player player, Region region) {
        playerRegionMap.put(player, region);
    }

    public static void removePlayer(Player player) {
        playerRegionMap.remove(player);
    }
}
