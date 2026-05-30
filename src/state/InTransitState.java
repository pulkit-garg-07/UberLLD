package src.state;

import src.Fare;
import src.Location;
import src.Ride;
import src.observer.User;

public class InTransitState extends State {


    public InTransitState(Ride ride) {
        super(ride);
    }
    //InTransitState implements State
    //+ completeRide() -> void
    public void completeRide() {
        this.getRide().notifyAllObservers("Ride: " + this.getRide().getId() + " has been completed");
        this.getRide().getDriver().makeAvailable();
        this.getRide().setState(new CompletedState(this.getRide()));
    }
}
