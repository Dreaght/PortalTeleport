package org.dreaght.portalteleport.states;

import org.bukkit.entity.Player;
import org.dreaght.portalteleport.utils.Region;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerChatManager {
    private static Map<Player, Region> playerChatWaitList = new HashMap<>();

    public static boolean containsPLayer(Player player) {
        return playerChatWaitList.containsKey(player);
    }

    public static Map<Player, Region> getPlayers() {
        return playerChatWaitList;
    }

    public static void addPlayer(Player player, Region region) {
        playerChatWaitList.put(player, region);
    }

    public static void removePlayer(Player player) {
        playerChatWaitList.remove(player);
    }

    public static Region getRegion(Player player) {
        return playerChatWaitList.get(player);
    }
}
