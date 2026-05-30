package src;

import src.observer.Driver;
import src.observer.User;
import src.strategy.BasicFareEstimationStrategy;
import src.strategy.DriverMatchingStrategy;
import src.strategy.FareEstimationStrategy;
import src.strategy.NearestDriverMatchingStrategy;

import java.util.ArrayList;
import java.util.List;

public class UberSystem {

    private List<Ride> rides;
    private List<User> users;
    private List<Driver> drivers;
    private FareEstimationStrategy fareEstimationStrategy;
    private DriverMatchingStrategy driverMatchingStrategy;

    public UberSystem() {

        this.fareEstimationStrategy = new BasicFareEstimationStrategy();
        this.driverMatchingStrategy = new NearestDriverMatchingStrategy();
        init();

    }

    public List<Fare> getFareForTrip(Location pickup, Location drop) {
        return this.fareEstimationStrategy.calculate(pickup, drop);
    }
    public Ride createRide(Fare fare, Location pickup, Location drop, User user) {
        Ride ride = new Ride(String.valueOf(rides.size()+1), user, pickup, drop, fare);
        List<Driver> nearbyDrivers = driverMatchingStrategy.findMatchingDrivers(
                drivers.stream().filter(driver -> driver.getVehicle().equals(fare.getVehicle())).toList(), ride.getPickup());
        for(Driver driver: nearbyDrivers){
            driver.notify("Ride: " + ride.getId() + " is nearby. Pls Accept/ Reject the ride with Fair : " + fare.getPrice());
        }
        return ride;
    }

    public void acceptRide(Driver driver, Ride ride) throws Exception {
        String otp = generateOTP();
        ride.getState().assignDriver(driver, otp);
    }
    private String generateOTP(){
        return String.valueOf((int)Math.floor(Math.random()*10000));
    }
    public void cancelRideByDriver(Ride ride) throws Exception {
        ride.getState().cancelRideByDriver();
    }
    public void cancelRideByUser(Ride ride) throws Exception {
        ride.getState().cancelRideByUser();
    }

    public void readyToPickup(Ride ride) throws Exception {
        ride.getState().readyToPickup();
    }

    public void pickup(Ride ride, String otp) throws Exception {
        ride.getState().pickup(otp);
    }
    public void completeRide(Driver driver, Ride ride) throws Exception {
        ride.getState().completeRide();
    }
    private void init(){
        this.rides = new ArrayList<>();
        this.users = new ArrayList<>();
        this.drivers = new ArrayList<>();
        for(int i=1;i<10;i++){
            users.add(new User(String.valueOf(i), new Location(i+100, i+101)));
        }
        for(int i=1;i<10;i++){
            drivers.add(new Driver(String.valueOf(i),new Location(i+200, i+201), Vehicle.CAB, (i+7)%6));
        }
        for(int i=1;i<10;i++){
            drivers.add(new Driver(String.valueOf(i),new Location(i+198, i+199), Vehicle.BIKE, (i+3)%6));
        }
        for(int i=1;i<10;i++){
            drivers.add(new Driver(String.valueOf(i),new Location(i+199, i+197), Vehicle.AUTO, (i+4)%6));
        }


    }

    public List<Ride> getRides() {
        return rides;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setFareEstimationStrategy(FareEstimationStrategy fareEstimationStrategy) {
        this.fareEstimationStrategy = fareEstimationStrategy;
    }

    public void setDriverMatchingStrategy(DriverMatchingStrategy driverMatchingStrategy) {
        this.driverMatchingStrategy = driverMatchingStrategy;
    }
}
