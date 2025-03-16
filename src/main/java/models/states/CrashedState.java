package models.states;

import models.flight.Flight;
import views.ConsoleLogger;

public class CrashedState implements FlightState {
    private static final String FLIGHTPREFIX = "Flight ";

    @Override
    public void takeOff(Flight flight) {
        ConsoleLogger.logError(FLIGHTPREFIX + flight.getFlightNumber() + " has crashed and cannot take off.");
    }

    @Override
    public void land(Flight flight) {
        ConsoleLogger.logError(FLIGHTPREFIX + flight.getFlightNumber() + " has crashed and cannot land.");
    }

    @Override
    public void hold(Flight flight) {
        ConsoleLogger.logError(FLIGHTPREFIX + flight.getFlightNumber() + " has crashed and is out of control.");
    }

    @Override
    public String getStateName() {
        return "Crashed";
    }
}