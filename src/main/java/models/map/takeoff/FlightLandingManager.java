package models.map.takeoff;

import models.map.AirTrafficMap;
import models.map.MapCell;
import models.flight.FlightInterface;
import views.ConsoleLogger;
import views.SimulatorView;
import models.states.HoldingState;

public class FlightLandingManager {
    private final AirTrafficMap airTrafficMap;
    private final SimulatorView view;
    private static final String FLIGHTPREFIX = "Flight ";

    public FlightLandingManager(AirTrafficMap airTrafficMap, SimulatorView view) {
        this.view = view;
        this.airTrafficMap = airTrafficMap;
    }

    public void simulateLandingProcedure(FlightInterface flight, int destRow, int destCol) {
        MapCell cell = airTrafficMap.getCell(destRow, destCol);
        ConsoleLogger.logInfo(FLIGHTPREFIX + flight.getFlightNumber() + " reached " + cell.getAirportLabel() + ". Enter L to land or H to hold:");
        String decision = view.getUserInput();

        if ("L".equalsIgnoreCase(decision)) {
            handleLanding(flight, cell);
        } else if ("H".equalsIgnoreCase(decision)) {
            handleHolding(flight, cell);
        } else {
            ConsoleLogger.logError("Invalid choice. Defaulting to landing.");
            handleLanding(flight, cell);
        }
    }

    private void handleLanding(FlightInterface flight, MapCell cell) {
        if (!"On ground/Runway".equals(flight.getState())) {
            flight.land();
        } else {
            ConsoleLogger.logWarning(FLIGHTPREFIX + flight.getFlightNumber() + " is already on the ground.");
        }
        cell.setLockedBy(null);
    }

    private void handleHolding(FlightInterface flight, MapCell cell) {
        flight.setState(new HoldingState());
        cell.setLockedBy(flight);
        ConsoleLogger.logInfo(FLIGHTPREFIX + flight.getFlightNumber() + " is now holding at " + cell.getAirportLabel());
    }
}