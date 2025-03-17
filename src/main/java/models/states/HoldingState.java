package models.states;

import models.flight.Flight;
import models.map.MapCell;
import views.ConsoleLogger;

public class HoldingState implements FlightState {
    private static final String FLIGHTPREFIX = "Flight ";

    @Override
    public boolean takeOff(Flight flight) {
        ConsoleLogger.logWarning(FLIGHTPREFIX + flight.getFlightNumber() + " is holding and cannot take off until it lands.");
        return false;
    }

    @Override
    public void land(Flight flight) {
        ConsoleLogger.logSuccess(FLIGHTPREFIX + flight.getFlightNumber() + " is now landing.");
        MapCell cell = flight.getCurrentAirportCell();
        if (cell != null) {
            cell.setLocked(false);
        }
        flight.setState(new OnRunwayState());
    }

    @Override
    public void hold(Flight flight) {
        ConsoleLogger.logInfo(FLIGHTPREFIX + flight.getFlightNumber() + " remains holding.");
    }

    @Override
    public String getStateName() {
        return "Holding";
    }
}