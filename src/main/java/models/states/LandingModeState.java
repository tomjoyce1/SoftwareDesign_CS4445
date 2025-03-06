package models.states;

import models.flight.Flight;
import views.ConsoleLogger;

public class LandingModeState implements FlightState {
    @Override
    public void takeOff(Flight flight) {
        ConsoleLogger.logError("Cannot take off while landing. Do you have a pilot's licence?");
    }

    @Override
    public void land(Flight flight) {
        ConsoleLogger.logSuccess("Landing completed");
        flight.setState(getNextState("On ground/Runway"));
    }

    @Override
    public void hold(Flight flight) {
        ConsoleLogger.logWarning("Aborting landing attempt, returning to previous altitude");
        flight.setState(getNextState("In the air"));
    }

    @Override
    public String getStateName() {
        return "Landing mode";
    }
}