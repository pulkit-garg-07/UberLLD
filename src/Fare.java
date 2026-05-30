package src;
//
//Fare
//- price: double
//- vehicle: Vehicle
public class Fare {
    private double price;
    private Vehicle vehicle;

    public Fare(double price, Vehicle vehicle) {
        this.price = price;
        this.vehicle = vehicle;
    }

    public double getPrice() {
        return price;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
