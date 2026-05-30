package src.state;

import src.Ride;

public class DriverPickingUpState extends State{
    public DriverPickingUpState(Ride ride) {
        super(ride);
    }

    //DriverPickingUpState implements State
    //+ cancelRideByUser() -> void
    //+ cancelRideByDriver() -> void
    //+ readyToPickup() -> void


    public void cancelRideByUser() throws Exception {
        this.getRide().getDriver().makeAvailable();
        this.getRide().notifyDriver("Ride " + this.getRide().getId() + " is cancelled by User: " + this.getRide().getUser().getId());
        this.getRide().setState(new CancelledState(this.getRide()));
    }
    public void cancelRideByDriver() throws Exception {
        this.getRide().getDriver().makeAvailable();
        this.getRide().notifyUser("Ride " + this.getRide().getId() + " is cancelled by Driver: " + this.getRide().getDriver().getId());
        this.getRide().setState(new WaitingForDriversState(this.getRide()));
    }
    public void readyToPickup() {
        this.getRide().getUser().notify("Driver " + this.getRide().getDriver().getId() + " is ready to pickup");
        this.getRide().setState(new ReadyForPickupState(this.getRide()));
    }

}
