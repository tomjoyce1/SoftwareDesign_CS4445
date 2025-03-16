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
        flight.setState(new OnRunwayState());
    }

    @Override
    public void hold(Flight flight) {
        ConsoleLogger.logWarning("WARNING: Aborting landing attempt, returning to previous altitude");
        flight.setState(new InAirState());
    }

    @Override
    public String getStateName() {
        return "Landing mode";
    }
}