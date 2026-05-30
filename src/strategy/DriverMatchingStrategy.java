package src.strategy;

import src.Location;
import src.observer.Driver;

import java.util.List;

public abstract class DriverMatchingStrategy {

    protected static final int PROXIMITY = 1000;
    public abstract List<Driver> findMatchingDrivers(List<Driver> drivers, Location pickup);

    protected double calculateDistance(Location location1, Location location2) {
        return Math.abs(location1.getLatitude()-location2.getLatitude()) + Math.abs(location1.getLongitude()-location2.getLongitude());
    }
}
