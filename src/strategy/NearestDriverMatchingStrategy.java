package src.strategy;

import src.Location;
import src.observer.Driver;


import java.util.Comparator;
import java.util.List;

public class NearestDriverMatchingStrategy extends DriverMatchingStrategy{

    public List<Driver> findMatchingDrivers(List<Driver> drivers, Location pickup){
        return drivers.stream()
                .filter(driver ->
                        calculateDistance(driver.getLocation(), pickup) <= PROXIMITY
                )
                .sorted(Comparator.comparingDouble(
                        driver -> calculateDistance(driver.getLocation(), pickup)
                ))
                .limit(5)
                .toList();
    }

}
