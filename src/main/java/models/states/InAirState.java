package models.states;

import models.flight.Flight;
import views.ConsoleLogger;

public class InAirState implements FlightState {
    @Override
    public void takeOff(Flight flight) {
        ConsoleLogger.logWarning("Already in the clouds");
    }

    @Override
    public void land(Flight flight) {
        ConsoleLogger.logSuccess("Transitioning to landing now");
        flight.setState(new LandingModeState());
    }

    @Override
    public void hold(Flight flight) {
        ConsoleLogger.logSuccess("Holding at current altitude");
    }

    @Override
    public String getStateName() {
        return "In the air";
    }


}