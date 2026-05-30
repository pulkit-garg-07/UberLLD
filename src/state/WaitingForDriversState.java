package src.state;

import src.Ride;
import src.observer.Driver;

public class WaitingForDriversState extends State{
    public WaitingForDriversState(Ride ride) {
        super(ride);
    }

    public void assignDriver(Driver driver, String otp){
        driver.makeUnavailable();
        this.getRide().setDriver(driver);
        this.getRide().setOtp(otp);
        this.getRide().notifyUser("Driver " + driver.getId() + " is assigned to you, please share otp: " + otp + " to driver");
        this.getRide().setState(new DriverPickingUpState(this.getRide()));
    }

    public void cancelRideByUser() throws Exception {
        this.getRide().setState(new CancelledState(this.getRide()));
    }

    //WaitingForDriversState implements State
    //+ assignDriver(driver: Driver) -> void
    //+ cancelRideByUser() -> void



}
