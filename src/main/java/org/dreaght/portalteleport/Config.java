package org.dreaght.portalteleport;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Config {
    private Plugin plugin = PortalTeleport.getInstance();
    private FileConfiguration config = plugin.getConfig();

    public void resetConfig() {
        if (config.getString("regions") == null) {
            config.createSection("regions");
        }

        plugin.saveConfig();
    }

    public String getRegions() {
        List<String> keys = new ArrayList<>(config.getConfigurationSection("regions").getKeys(false));
        return String.join(", ", keys);
    }

    public void createRegion(String regionName) {
        config.createSection("regions." + regionName);
    }

    public void removeRegion(String regionName) {
        config.set(("regions." + regionName), null);
    }

    public String getPos1(String regionName) {
        return config.getString("regions." + regionName + ".pos1");
    }

    public String getPos2(String regionName) {
        return config.getString("regions." + regionName + ".pos2");
    }

    public void setPos1(String regionName, Location location) {
        config.set(("regions." + regionName + ".pos1"), locationToString(location));
    }

    public void setPos2(String regionName, Location location) {
        config.set(("regions." + regionName + ".pos2"), locationToString(location));
    }

    private String locationToString(Location location) {
        String locString = location.getWorld().getName() + "," +
                location.getX() + "," +
                location.getY() + "," +
                location.getZ() + "," +
                location.getYaw() + "," +
                location.getPitch();

        return locString;
    }

    private Location getLocationFromString(String locString) {
        String[] locData = locString.split(",");
        String worldName = locData[0];
        double x = Double.parseDouble(locData[1]);
        double y = Double.parseDouble(locData[2]);
        double z = Double.parseDouble(locData[3]);
        float yaw = Float.parseFloat(locData[4]);
        float pitch = Float.parseFloat(locData[5]);

        Location location = new Location(plugin.getServer().getWorld(worldName), x, y, z, yaw, pitch);

        return location;
    }
}
