package src;
//Uber LLD
//
//Functional requirements:
//        1. users should be able to get the fares for different vehicle types for the pickup and drop location specified.
//        2. users should be able to initiate a ride with the selected fare and vehicle
//3. system should send notifications to the available drivers in proximity
//4. drivers should be able to accept/reject a ride
//5. user should get all the updates regarding driver's location once assigned
//        6. driver should drop the user at the drop location and get available again
//
//Non-functional requirements
//1. system should handle the users concurrently requesting for a ride and no 2 users should be assigned the same driver
//2. system should handle the drivers concurrently accepting a ride and no two drivers should be assigned the same ride.
//
//
//        Entities
//
//1. User
//2. Driver
//3. Ride
//4. Vehicle
//5. Location
//
//
//Flows:
//        1. user -> with his Location (lat,long) should specify the drop Location (lat,long) and get the price fares for different vehicleTypes
//2. user -> select the (Price, vehicleType) -> initiate a ride: WAITING_FOR_DRIVERS
//3. notify all the available drivers using a particular algo
//4. driver -> accept a ride in WAITING_FOR_DRIVERS -> DRIVER_PICKING_UP, system should generate an OTP and send to user, make driver unavailable till the ride completes
//5. driver's Location changes to User's Location and update the ride with DRIVER_PICKING_UP -> READY_TO_PICKUP. send notification to user for this.
//        6. user can cancel the ride DRIVER_PICKING_UP -> CANCELLED. READY_TO_PICKUP can not go to CANCELLED. if cancelled, send a notification to driver and make him available again.
//        7. driver enters the OTP given by user and if valid READY_TO_PICKUP -> IN_TRANSIT, if invalid -> READY_TO_PICKUP, send notification to user for this
//        8. driver completes the ride and IN_TRANSIT -> COMPLETED. make him available again.
//
//
//        --out-of-scope--
//        1. payments
//2. penalties for cancellation
//
//
//        Location
//- latitude: double
//- longitude: double
//---------------------------------
//
//
//interface Observer
//- notify(msg) -> void
//
//User implements Observer
//- id
//- location: Location
//
//Driver implements Observer
//- id
//- location: Location
//- isAvailable: boolean
//- vehicle: Vehicle
//- rating
//+ makeUnavailable() -> void
//+ makeAvailable() -> void
//
//---------------------------------
//
//  Vehicle
// BIKE, AUTO, CAB
//
//
//
//Fare
//- price: double
//- vehicle: Vehicle
//
//
//
//---------------------------------------
//
//
//
//abstract class State
//
//- ride: Ride
//+ assignDriver(driver: Driver) -> err
//+ cancelRideByUser() -> err
//+ cancelRideByDriver() -> err
//+ readyToPickup() -> err
//+ pickup(otp: String) -> err
//+ completeRide() -> err
//
//WaitingForDriversState implements State
//+ assignDriver(driver: Driver) -> void
//+ cancelRideByUser() -> void
//
//
//DriverPickingUpState implements State
//+ cancelRideByUser() -> void
//+ cancelRideByDriver() -> void
//+ readyToPickup() -> void
//
//ReadyForPickupState implements State
//+ pickup(otp: String) -> void
//
//InTransitState implements State
//+ completeRide() -> void
//
//CompletedState implements State
//
//
//CancelledState implements State
//
//
//        Ride
//- id
//- user: User
//- driver: Optional[Driver]
//        - pickup: Location
//- drop: Location
//- state: State
//- otp: Optional[int]
//        - fare: Fare
//+ setState(state: State) -> void
//+ notifyAll() -> void
//+ notifyUser() -> void
//+ notifyDriver() -> void
//
//---------------------------------------
//
//
//FareEstimationStrategy
//+ calculate(pickup: Location, drop: Location) -> Fares[]
//
//BasicFareEstimationStrategy implements FareEstimationStrategy
//+ calculate(pickup, Location, drop: Location) -> Fares[]
//
//NightSurgeFareEstimationStrategy implements FareEstimationStrategy
//+ calculate(pickup, Location, drop: Location) -> Fares[]
//
//---------------------------------------
//
//DriverMatchingStrategy
//+ findMatchingDrivers(drivers: Drivers[]) -> Drivers[]
//
//        NearestDriverMatchingStrategy
//+ findMatchingDrivers(drivers: Drivers[]) -> Drivers[]
//
//        HighestRatedDriverMatchingStrategy
//+ findMatchingDrivers(drivers: Drivers[]) -> Drivers[]
//
//---------------------------------------
//System
//- rides: Rides[]
//- users: Users[]
//- drivers: Drivers[]
//- fareEstimationStrategy: FareEstimationStrategy
//- driversSearchStrategy: DriverMatchingStrategy
//
//+ getFareForTrip(pickup: Location, drop: Location) -> Fares[]
//+ createRide(fare: Fare, pickup: Location, drop: Location, user: User) -> Ride
//+ acceptRide(driver: Driver, ride: Ride) -> void
//+ cancelRide(driver: Driver, ride: Ride) -> void
//+ readyToPickup(driver: Driver, ride: Ride) -> void
//+ cancelRide(user: User, ride: Ride) -> void
//+ pickup(driver: Driver, ride: Ride, otp: int) -> void
//+ completeRide(driver: Driver, ride: Ride) -> void
//+ setFareEstimationStrategy(strategy: FareEstimationStrategy) -> void
//+ setDriverMatchingStrategy(strategy: DriverMatchingStrategy) -> void
//


import src.observer.Driver;
import src.observer.User;

import java.util.List;

public class Main{
    public static void main(String[] args) throws Exception {
        UberSystem system = new UberSystem();
        User user1 = system.getUsers().getFirst();
        User user2 = system.getUsers().getLast();

        List<Fare> fares1 = system.getFareForTrip(user1.getLocation(), new Location(200,200));
        for(Fare f: fares1){
            System.out.println(f.getPrice() + " " + f.getVehicle());
        }

        Ride ride = system.createRide(fares1.getFirst(), user1.getLocation(), new Location(200,200), user1);

        Driver driver1 = system.getDrivers().getFirst();
        system.acceptRide(driver1, ride);
        system.readyToPickup(ride);
        system.pickup(ride, ride.getOtp());


    }
}