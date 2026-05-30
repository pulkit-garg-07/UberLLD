package src.observer;
import src.Location;

//User implements Observer
//- id
//- location: Location

public class User implements Observer{
    private String id;
    private Location location;

    public User(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    @Override
    public void notify(String msg) {
       System.out.println(msg);
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }
}
