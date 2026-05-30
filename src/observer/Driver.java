package src.observer;



import src.Location;
import src.Vehicle;



//Driver implements Observer
//- id
//- location: Location
//- isAvailable: boolean
//- vehicle: Vehicle
//- rating
//+ makeUnavailable() -> void
//+ makeAvailable() -> void


public class Driver implements Observer{
    private String id;
    private Location location;
    private boolean isAvailable;
    private Vehicle vehicle;
    private double rating;

    public Driver(String id, Location location, Vehicle vehicle, double rating) {
        this.id = id;
        this.location = location;
        this.isAvailable = true;
        this.vehicle = vehicle;
        this.rating = rating;
    }

    @Override
    public void notify(String msg) {
        System.out.println("Driver" + " " + id + ": " + msg);
    }

    public void makeUnavailable() {
        this.isAvailable = false;
    }
    public void makeAvailable() {
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getRating() {
        return rating;
    }
}
