package src.strategy;

import src.Fare;
import src.Location;
import src.Vehicle;

import java.util.List;

public class BasicFareEstimationStrategy implements FareEstimationStrategy{
    @Override
    public List<Fare> calculate(Location pickup, Location drop) {
        double distance = Math.abs(pickup.getLatitude()-drop.getLatitude());
        return List.of(
                new Fare(1 + 2*distance, Vehicle.BIKE),
                new Fare(2 + 3*distance, Vehicle.AUTO),
                new Fare(3 + 4*distance, Vehicle.CAB)
        );

    }
}
