package models.states;

import models.flight.Flight;
import views.ConsoleLogger;

public class OnRunwayState implements FlightState {
    @Override
    public void takeOff(Flight flight) {
        ConsoleLogger.logSuccess("Taxiing to take off");
        flight.setState(getNextState("In the air"));
    }

    @Override
    public void land(Flight flight) {
        ConsoleLogger.logWarning("Already on the ground");
    }

    @Override
    public void hold(Flight flight) {
        ConsoleLogger.logWarning("Cannot hold while on ground");
    }

    @Override
    public String getStateName() {
        return "On ground/Runway";
    }
}