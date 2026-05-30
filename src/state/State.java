package src.state;

import src.Ride;
import src.observer.Driver;

public abstract class State {


//- ride: Ride
//+ assignDriver(driver: Driver) -> err
//+ cancelRideByUser() -> err
//+ cancelRideByDriver() -> err
//+ readyToPickup() -> err
//+ pickup(otp: String) -> err
//+ completeRide() -> err


    private Ride ride;

    public State(Ride ride) {
        this.ride = ride;
    }

    public void assignDriver(Driver driver, String otp) throws Exception {
        throw new Exception("This operation is not allowed in this state");
    }
    public void cancelRideByUser() throws Exception {
        throw new Exception("This operation is not allowed in this state");
    }
    public void cancelRideByDriver() throws Exception {
        throw new Exception("This operation is not allowed in this state");
    }
    public void readyToPickup() throws Exception {
        throw new Exception("This operation is not allowed in this state");
    }
    public void pickup(String otp) throws Exception {
        throw new Exception("This operation is not allowed in this state");
    }
    public void completeRide() throws Exception {
        throw new Exception("This operation is not allowed in this state");
    }

    public Ride getRide() {
        return ride;
    }
}
