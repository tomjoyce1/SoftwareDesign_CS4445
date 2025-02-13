package FlightStates;

import Flight.Flight;

public class InAirState implements FlightState {
    @Override
    public void takeOff(Flight flight) {
        System.out.println("Already in the clouds");
    }
    
    @Override
    public void land(Flight flight){
        System.out.println("Transitioning to landing now");
        flight.setState(new LandingModeState());
    }
    
    @Override
    public void hold(Flight flight){
        System.out.println("Holding at current altitude");
    }
    
    @Override
    public String getStateName(){
        return "In the air";
    }


}