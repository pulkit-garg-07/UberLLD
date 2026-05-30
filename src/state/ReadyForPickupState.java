package src.state;

import src.Fare;
import src.Location;
import src.Ride;
import src.observer.User;

public class ReadyForPickupState extends State {

    //ReadyForPickupState implements State
    //+ pickup(otp: String) -> void


    public ReadyForPickupState(Ride ride) {
        super(ride);
    }

    public void pickup(String otp) throws Exception {
        if(otp == null)
            throw new Exception("OTP is not valid");
        if(!otp.equals(this.getRide().getOtp()))
            throw new Exception("OTP is not valid");
        this.getRide().getUser().notify("Ride has started");
        this.getRide().setState(new InTransitState(this.getRide()));
    }
}
