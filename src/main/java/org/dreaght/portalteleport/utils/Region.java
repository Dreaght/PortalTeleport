package org.dreaght.portalteleport.utils;

import org.bukkit.Location;

public class Region {
    private String UUID;
    private Location location1;
    private Location location2;

    public Region(Location location1, Location location2) {
        this.location1 = location1;
        this.location2 = location2;
    }

    public String getUUID() {
        return this.UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public boolean inRegion(Location location) {
        Cuboid cuboid = new Cuboid(this.location1, this.location2);
        return cuboid.contains(location);
    }

    public Location getLocation1() {
        return this.location1;
    }

    public void setLocation1(Location location1) {
        this.location1 = location1;
    }

    public Location getLocation2() {
        return this.location2;
    }

    public void setLocation2(Location location2) {
        this.location2 = location2;
    }
}
