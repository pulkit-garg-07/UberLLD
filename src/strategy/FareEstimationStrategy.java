package src.strategy;

import src.Fare;
import src.Location;

import java.util.List;

public interface FareEstimationStrategy {
    //FareEstimationStrategy
    //+ calculate(pickup: Location, drop: Location) -> Fares[]
    public List<Fare> calculate(Location pickup, Location drop);
}
