package src.strategy;

import src.Location;
import src.observer.Driver;

import java.util.Comparator;
import java.util.List;

public class HighestRatedDriverMatchingStrategy extends DriverMatchingStrategy {

    public List<Driver> findMatchingDrivers(List<Driver> drivers, Location pickup){

            return drivers.stream()
                    .filter(driver ->
                            calculateDistance(driver.getLocation(), pickup) <= PROXIMITY * 2
                    )
                    .sorted(Comparator.comparingDouble(Driver::getRating).reversed())
                    .limit(5)
                    .toList();
        }

}
