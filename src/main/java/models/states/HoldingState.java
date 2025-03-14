package models.states;

import models.flight.Flight;
import models.map.MapCell;
import views.ConsoleLogger;

public class HoldingState implements FlightState {

    @Override
    public void takeOff(Flight flight) {
        ConsoleLogger.logWarning("Flight " + flight.getFlightNumber() + " is holding and cannot take off until it lands.");
    }

    @Override
    public void land(Flight flight) {
        ConsoleLogger.logSuccess("Flight " + flight.getFlightNumber() + " is now landing.");
        // Unlock the cell once the flight lands
        MapCell cell = flight.getCurrentAirportCell(); // assume a getter to obtain the cell where the flight is holding
        if (cell != null) {
            cell.setLocked(false);
        }
        flight.setState(new OnRunwayState());
    }

    @Override
    public void hold(Flight flight) {
        ConsoleLogger.logInfo("Flight " + flight.getFlightNumber() + " remains holding.");
    }

    @Override
    public String getStateName() {
        return "Holding";
    }
}