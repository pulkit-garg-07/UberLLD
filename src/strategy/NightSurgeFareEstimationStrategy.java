package src.strategy;

import src.Fare;
import src.Location;
import src.Vehicle;

import java.util.List;

public class NightSurgeFareEstimationStrategy implements FareEstimationStrategy{
    static final double NIGHT_SURGE = 2;
    @Override
    public List<Fare> calculate(Location pickup, Location drop) {
        double distance = Math.abs(pickup.getLatitude()-drop.getLatitude()) + Math.abs(pickup.getLongitude()-drop.getLongitude());
        return List.of(
                new Fare(1 + 2.5*distance + NIGHT_SURGE, Vehicle.BIKE),
                new Fare(2 + 3.5*distance + NIGHT_SURGE, Vehicle.AUTO),
                new Fare(3 + 4.5*distance + NIGHT_SURGE, Vehicle.CAB)
        );
    }
}
