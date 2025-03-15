package models.map.takeoff;

import models.flight.IFlight;
import models.map.AirTrafficMap;
import views.ConsoleLogger;
import views.SimulatorView;

import java.util.List;

    public class FlightTakeOffManager {
        private final DestinationSelector destinationSelector;
        private final FlightPathCalculator pathCalculator;
        private final FlightMovementManager movementManager;
        private final FlightLandingManager landingManager;
        private final AirTrafficMap airTrafficMap;
        
        public FlightTakeOffManager(AirTrafficMap map, SimulatorView view) {
            this.airTrafficMap = map;
            this.destinationSelector = new DestinationSelector(map, view);
            this.pathCalculator = new FlightPathCalculator(map);
            this.movementManager = new FlightMovementManager(map);
            this.landingManager = new FlightLandingManager(map, view);
        }

    public void simulateFlightMovement(IFlight flight, int srcRow, int srcCol, int destRow, int destCol) {
        List<int[]> path = pathCalculator.calculatePath(srcRow, srcCol, destRow, destCol);
        if (path.isEmpty()) {
            ConsoleLogger.logError("No valid path for flight " + flight.getFlightNumber());
            return;
        }
        movementManager.moveFlightAlongPath(flight, path, destRow, destCol);
        landingManager.simulateLandingProcedure(flight, destRow, destCol);
    }
        
    public void simulateTakeOff(IFlight flight, int destRow, int destCol) {
        int[] currentPos = airTrafficMap.findFlightPosition(flight);
        if (currentPos == null) {
            ConsoleLogger.logError("Flight " + flight.getFlightNumber() + " is not at an airport.");
            return;
        }
        if (destRow == -1 || destCol == -1) {
            int[] selectedDest = destinationSelector.promptForDestination(currentPos[0], currentPos[1]);
            if (selectedDest == null) {
                ConsoleLogger.logError("Destination coordinates not provided.");
                return;
            }
            destRow = selectedDest[0];
            destCol = selectedDest[1];
        }
        if (!airTrafficMap.canPlaceFlightAt(flight, destRow, destCol)) {
            return;
        }
        flight.takeOff();
        List<int[]> path = pathCalculator.calculatePath(currentPos[0], currentPos[1], destRow, destCol);
        movementManager.moveFlightAlongPath(flight, path);
        landingManager.simulateLandingProcedure(flight, destRow, destCol);
    }

    public void simulateScheduledTakeOff(ScheduledFlight sf) {
        IFlight flight = sf.getFlight();
        int destRow = sf.getDestinationRow();
        int destCol = sf.getDestinationCol();
        
        if (!destinationSelector.destinationAvailable(flight, destRow, destCol)) {
            ConsoleLogger.logError("Scheduled flight " + flight.getFlightNumber() + " cannot take off because destination " + airTrafficMap.getCell(destRow, destCol).getAirportLabel() + " is locked.");
            return;
        }
        
        ConsoleLogger.logInfo("Simulating scheduled takeoff for Flight " + flight.getFlightNumber() + " to " + sf.getDestinationAirportLabel());
        flight.takeOff();
        simulateFlightMovement(flight, sf.getSourceRow(), sf.getSourceCol(), destRow, destCol);
    }

    public boolean updateScheduledFlights(List<ScheduledFlight> schedFlights) {
        boolean allArrived = true;
        for (ScheduledFlight sf : schedFlights) {
            if (sf.getCurrentRow() == sf.getDestinationRow() &&
                sf.getCurrentCol() == sf.getDestinationCol()) {
                continue;
            }
            allArrived = false;
            List<int[]> path = pathCalculator.calculatePath(sf.getCurrentRow(), sf.getCurrentCol(), sf.getDestinationRow(), sf.getDestinationCol());
            if (path.size() < 2) {
                ConsoleLogger.logError("No valid path for flight " + sf.getFlight().getFlightNumber());
                continue;
            }
            int[] nextStep = path.get(1);
            movementManager.moveFlight(sf.getFlight(), sf.getCurrentRow(), sf.getCurrentCol(), nextStep[0], nextStep[1]);
            sf.setCurrentPosition(nextStep[0], nextStep[1]);
        }
        return allArrived;
    }
}