package models.map.takeoff;

import models.map.AirTrafficMap;
import models.map.MapCell;
import models.flight.IFlight;
import views.ConsoleLogger;
import views.SimulatorView;
import models.states.HoldingState;

public class FlightLandingManager {
    private final AirTrafficMap airTrafficMap;
    private final SimulatorView view;

    public FlightLandingManager(AirTrafficMap airTrafficMap, SimulatorView view) {
        this.view = view;
        this.airTrafficMap = airTrafficMap;
    }

    public void simulateLandingProcedure(IFlight flight, int destRow, int destCol) {
        MapCell cell = airTrafficMap.getCell(destRow, destCol);
        ConsoleLogger.logInfo("Flight " + flight.getFlightNumber() + " reached " + cell.getAirportLabel() + ". Enter L to land or H to hold:");
        String decision = view.getUserInput();
        
        if ("L".equalsIgnoreCase(decision)) {
            if (!flight.getState().equals("On ground/Runway")) {
                flight.land();
            } else {
                ConsoleLogger.logWarning("Flight " + flight.getFlightNumber() + " is already on the ground.");
            }
            cell.setLockedBy(null);
        } else if ("H".equalsIgnoreCase(decision)) {
            flight.setState(new HoldingState());
            cell.setLockedBy(flight);
            ConsoleLogger.logInfo("Flight " + flight.getFlightNumber() + " is now holding at " + cell.getAirportLabel());
        } else {
            ConsoleLogger.logError("Invalid choice. Defaulting to landing.");
            if (!flight.getState().equals("On ground/Runway")) {
                flight.land();
            }
            cell.setLockedBy(null);
        }
    }
}