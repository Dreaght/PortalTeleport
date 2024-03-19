package org.dreaght.portalteleport;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.dreaght.portalteleport.utils.Region;

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

    public String getUUIDsAsString() {
        return String.join(", ", getRegionUUIDs());
    }

    public List<Region> getAllRegions() {
        List<String> keys = getRegionUUIDs();
        List<Region> regions = new ArrayList<>();

        keys.forEach(key -> regions.add(getRegionByUUID(key)));

        return regions;
    }

    private List<String> getRegionUUIDs() {
        return new ArrayList<>(config.getConfigurationSection("regions").getKeys(false));
    }

    public Region getRegionByUUID(String uuid) {
        Location location1 = getLocationFromString(getPos1(uuid));
        Location location2 = getLocationFromString(getPos2(uuid));

        Region region = new Region(location1, location2);
        region.setUUID(uuid);

        return region;
    }

    public void createRegion(String regionName) {
        config.createSection("regions." + regionName);
    }

    public void removeRegion(String regionName) {
        config.set(("regions." + regionName), null);
        plugin.saveConfig();
    }

    public String getPos1(String regionName) {
        return config.getString("regions." + regionName + ".pos1");
    }

    public String getPos2(String regionName) {
        return config.getString("regions." + regionName + ".pos2");
    }

    public void setPos1(String regionName, Location location) {
        config.set(("regions." + regionName + ".pos1"), locationToString(location));
        plugin.saveConfig();
    }

    public void setPos2(String regionName, Location location) {
        config.set(("regions." + regionName + ".pos2"), locationToString(location));
        plugin.saveConfig();
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
