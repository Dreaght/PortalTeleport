package org.dreaght.portalteleport;

import org.bukkit.plugin.java.JavaPlugin;
import org.dreaght.portalteleport.commands.PortalCommand;
import org.dreaght.portalteleport.listeners.*;
import org.dreaght.portalteleport.states.RegionMarkers;

import java.util.Objects;

public final class PortalTeleport extends JavaPlugin {
    private static RegionMarkers regionMarkers;
    private static Config config;

    public static Config getCfg() {
        return config;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        config = new Config(this);

        getServer().getPluginManager().registerEvents(new OnItemUse(this), this);
        getServer().getPluginManager().registerEvents(new OnMove(), this);
        getServer().getPluginManager().registerEvents(new OnSneak(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new OnChat(), this);

        Objects.requireNonNull(getCommand("portal")).setExecutor(new PortalCommand());

        regionMarkers = new RegionMarkers(this);
        regionMarkers.startMarkingAllRegions();
    }

    public static RegionMarkers getRegionMarkers() {
        return regionMarkers;
    }
}
