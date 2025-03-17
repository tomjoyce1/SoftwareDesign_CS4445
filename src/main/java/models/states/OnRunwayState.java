package models.states;

import models.flight.Flight;
import views.ConsoleLogger;

public class OnRunwayState implements FlightState {

    @Override
    public boolean takeOff(Flight flight) {
        ConsoleLogger.logSuccess("Taxiing to take off");
        flight.setState(new InAirState());
        return true;
    }

    @Override
    public void land(Flight flight) {
        ConsoleLogger.logWarning("WARNING: Already on the ground");
    }

    @Override
    public void hold(Flight flight) {
        ConsoleLogger.logWarning("WARNING: Cannot hold while on ground");
    }

    @Override
    public String getStateName() {
        return "On ground/Runway";
    }


}