package src;


import src.observer.Driver;
import src.observer.User;
import src.state.State;
import src.state.WaitingForDriversState;

public class Ride {
    //Ride
    //- id
    //- user: User
    //- driver: Optional[Driver]
    //- pickup: Location
    //- drop: Location
    //- state: State
    //- otp: Optional[int]
    //- fare: Fare
    //+ setState(state: State) -> void
    //+ notifyAll() -> void
    //+ notifyUser() -> void
    //+ notifyDriver() -> void

    private String id;
    private User user;
    private Driver driver;
    private Location pickup;
    private Location drop;
    private State state;
    private String otp;
    private Fare fare;

    public Ride(String id, User user, Location pickup, Location drop, Fare fare) {
        this.id = id;
        this.user = user;
        this.driver = null;
        this.pickup = pickup;
        this.drop = drop;
        this.state = new WaitingForDriversState(this);
        this.otp = null;
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", driver=" + driver +
                ", pickup=" + pickup +
                ", drop=" + drop +
                ", state=" + state +
                ", otp='" + otp + '\'' +
                ", fare=" + fare +
                '}';
    }

    public void setState(State state) {
        this.state = state;
    }

    public void notifyAllObservers(String msg){
        this.notifyUser(msg);
        this.notifyDriver(msg);
    }
    public void notifyUser(String msg){
        this.user.notify(msg);
    }
    public void notifyDriver(String msg){
        this.driver.notify(msg);
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Driver getDriver() {
        return driver;
    }

    public Location getPickup() {
        return pickup;
    }

    public Location getDrop() {
        return drop;
    }

    public State getState() {
        return state;
    }

    public String getOtp() {
        return otp;
    }

    public Fare getFare() {
        return fare;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
