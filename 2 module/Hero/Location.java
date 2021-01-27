package com.company;

public class Location {
    public double x, y, z;

    public Location() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location(Location location) {
        this.x = location.x;
        this.y = location.y;
        this.z = location.z;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Location(this);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
