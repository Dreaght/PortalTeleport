package org.dreaght.portalteleport;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.dreaght.portalteleport.utils.Region;

import java.util.ArrayList;
import java.util.List;

public class Config {
    private Plugin plugin;
    private FileConfiguration config;

    public Config(Plugin plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
        resetConfig();
    }

    public void resetConfig() {
        if (config.getString("regions") == null) {
            config.createSection("regions");
        }

        plugin.saveConfig();
    }

    public boolean isRegionExist(String uuid) {
        return config.getConfigurationSection("regions." + uuid) != null;
    }

    public List<Region> getAllRegions() {
        List<String> keys = getRegionUUIDs();
        List<Region> regions = new ArrayList<>();

        keys.forEach(key -> regions.add(getRegionByUUID(key)));

        return regions;
    }

    public List<String> getRegionUUIDs() {
        return new ArrayList<>(config.getConfigurationSection("regions").getKeys(false));
    }

    public Region getRegionByUUID(String uuid) {
        Location location1 = getLocationFromString(getPos1(uuid));
        Location location2 = getLocationFromString(getPos2(uuid));

        Region region = new Region(location1, location2);
        region.setUUID(uuid);
        region.setConfirmed(getConfirmed(uuid));
        region.setCommand(getCommandOfRegion(uuid));

        return region;
    }

    public void createRegion(String regionName) {
        config.createSection("regions." + regionName);
    }

    public void removeRegion(String regionName) {
        config.set(("regions." + regionName), null);
        plugin.saveConfig();
    }

    public void clearAllRegions() {
        List<String> keys = getRegionUUIDs();
        keys.forEach(key -> {
            removeRegion(key);
            PortalTeleport.getRegionMarkers().cancelMarkingRegion(key);
        });
    }

    public String getCommandOfRegion(String uuid) {
        return config.getString("regions." + uuid + ".command");
    }

    public void setCommandOfRegion(String uuid, String command) {
        config.set("regions." + uuid + ".command", command);
        plugin.saveConfig();
    }

    public boolean getConfirmed(String uuid) {
        return config.getBoolean("regions." + uuid + ".confirmed");
    }

    public void setConfirmed(String uuid, boolean var) {
        config.set("regions." + uuid + ".confirmed", var);
        plugin.saveConfig();
    }

    public String getPos1(String regionName) {
        return config.getString("regions." + regionName + ".pos1");
    }

    public void setPos1(String regionName, Location location) {
        config.set(("regions." + regionName + ".pos1"), locationToString(location));
        plugin.saveConfig();
    }

    public String getPos2(String regionName) {
        return config.getString("regions." + regionName + ".pos2");
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
