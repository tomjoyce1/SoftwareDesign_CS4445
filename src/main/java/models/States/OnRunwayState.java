package models.States;

import models.Flight.Flight;

public class OnRunwayState implements FlightState {

    @Override
    public void takeOff(Flight flight) {
        System.out.println("Taxiing to take off");
        flight.setState(new InAirState());
    }
    
    @Override
    public void land(Flight flight){
        System.out.println("Already on the ground");
    }
    
    @Override
    public void hold(Flight flight){
        System.out.println("Cannot hold while on ground");
    }
    
    @Override
    public String getStateName(){
        return "On ground/Runway";
    }


}