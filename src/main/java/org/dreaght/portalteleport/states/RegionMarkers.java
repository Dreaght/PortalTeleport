package org.dreaght.portalteleport.states;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.dreaght.portalteleport.Config;
import org.dreaght.portalteleport.PortalTeleport;
import org.dreaght.portalteleport.utils.Cuboid;
import org.dreaght.portalteleport.utils.Region;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionMarkers {
    private final Plugin plugin;
    private Map<String, BukkitRunnable> taskMap = new HashMap<>();

    public RegionMarkers(Plugin plugin) {
        this.plugin = plugin;
    }

    public void startMarkingRegion(Region region) {
        if (region == null) { return; }

        BukkitRunnable runnable = new BukkitRunnable() {
            World world = region.getLocation1().getWorld();
            Cuboid cuboid = new Cuboid(region.getLocation1(), region.getLocation2());

            @Override
            public void run() {
                cuboid.iterateBlocks(block -> {
                    assert world != null;
                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);
                    world.spawnParticle(Particle.REDSTONE, block.getLocation().clone().add(.5, .5, .5),
                            1, dustOptions);
                });

                if (region.isConfirmed()) {
                    cancel();
                }
            }
        };

        runnable.runTaskTimer(plugin, 5, 5);
        taskMap.put(region.getUUID(), runnable);
    }

    public void startMarkingAllRegions() {
        List<Region> regions = PortalTeleport.getCfg().getAllRegions();
        regions.forEach(this::startMarkingRegion);
    }

    public void cancelMarkingRegion(String regionUUID) {
        BukkitRunnable task = taskMap.get(regionUUID);
        if (task != null) {
            task.cancel();
            taskMap.remove(regionUUID);
        }
    }
}
