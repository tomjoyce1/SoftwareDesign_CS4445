package models.States;

import models.Flight.Flight;

public class LandingModeState implements FlightState {

    @Override
    public void takeOff(Flight flight) {
        System.out.println("Cannot take off while landing. Do you have a pilot's licence?");
    }
    
    @Override
    public void land(Flight flight){
        System.out.println("Landing completed");
        flight.setState(new OnRunwayState());
    }
    
    @Override
    public void hold(Flight flight){
        System.out.println("Aborting landing attempt, returning to previous altitude");
        flight.setState(new InAirState());
    }
    
    @Override
    public String getStateName(){
        return "Landing mode";
    }
    }