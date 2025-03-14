package models.states;

import models.flight.Flight;
import views.ConsoleLogger;

public class CrashedState implements FlightState {

    @Override
    public void takeOff(Flight flight) {
        ConsoleLogger.logError("Flight " + flight.getFlightNumber() + " has crashed and cannot take off.");
    }

    @Override
    public void land(Flight flight) {
        ConsoleLogger.logError("Flight " + flight.getFlightNumber() + " has crashed and cannot land.");
    }

    @Override
    public void hold(Flight flight) {
        ConsoleLogger.logError("Flight " + flight.getFlightNumber() + " has crashed and is out of control.");
    }

    @Override
    public String getStateName() {
        return "Crashed";
    }
}