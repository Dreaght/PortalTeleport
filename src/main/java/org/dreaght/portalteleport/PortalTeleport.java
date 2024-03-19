package org.dreaght.portalteleport;

import org.bukkit.plugin.java.JavaPlugin;
import org.dreaght.portalteleport.commands.PortalCommand;
import org.dreaght.portalteleport.listeners.OnItemUse;

import java.util.Objects;

public final class PortalTeleport extends JavaPlugin {
    private static PortalTeleport instance;

    public static PortalTeleport getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();
        new Config().resetConfig();

        getServer().getPluginManager().registerEvents(new OnItemUse(), this);
        Objects.requireNonNull(getCommand("portal")).setExecutor(new PortalCommand());
    }
}
