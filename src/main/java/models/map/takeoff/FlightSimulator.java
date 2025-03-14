package models.map.takeoff;

import models.flight.Flight;
import models.map.AirTrafficMap;
import models.map.MapCell;
import models.pathfinder.PathFinder;
import models.states.HoldingState;
import views.ConsoleLogger;
import views.SimulatorView;
import java.util.List;

public class FlightSimulator {
    private final AirTrafficMap airTrafficMap;
    private final SimulatorView view;

    public FlightSimulator(AirTrafficMap airTrafficMap, SimulatorView view) {
        this.airTrafficMap = airTrafficMap;
        this.view = view;
    }

    // Calculate the flight path using a PathFinder.
    public List<int[]> calculatePath(int srcRow, int srcCol, int destRow, int destCol) {
        return new PathFinder(airTrafficMap).findShortestPath(srcRow, srcCol, destRow, destCol);
    }

    // Delegates to AirTrafficMap's utility method.
    private int[] findFlightPosition(Flight flight) {
        return airTrafficMap.findFlightPosition(flight);
    }

    // Prompt the user to choose a destination airport.
    private int[] promptForDestination(int currentRow, int currentCol) {
        List<int[]> destinations = airTrafficMap.getAvailableDestinations(currentRow, currentCol);
        if (destinations.isEmpty()) {
            ConsoleLogger.logError("No destination airports available!");
            return null;
        }
        for (int i = 0; i < destinations.size(); i++) {
            int[] coord = destinations.get(i);
            MapCell cell = airTrafficMap.getCell(coord[0], coord[1]);
            ConsoleLogger.logStandard((i + 1) + ". " + cell.getAirportLabel());
        }
        ConsoleLogger.logStandard("Enter the number corresponding to your destination airport:");

        Integer choice = utils.InputParserUtil.parseInt(view.getUserInput());
        if (choice == null || choice < 1 || choice > destinations.size()) {
            ConsoleLogger.logError("Selection out of range!");
            return null;
        }
        return destinations.get(choice - 1);
    }

    // Helper method to check if the destination cell is available
    private boolean destinationAvailable(Flight flight, int destRow, int destCol) {
        return airTrafficMap.canPlaceFlightAt(flight, destRow, destCol);
    }

    // Simulate a regular flight takeoff.
    public void simulateTakeOff(Flight flight, int destRow, int destCol) {
        int[] current = findFlightPosition(flight);
        if (current == null) {
            ConsoleLogger.logError("Flight " + flight.getFlightNumber() + " is not at an airport.");
            return;
        }
        if (destRow == -1 || destCol == -1) {
            int[] d = promptForDestination(current[0], current[1]);
            if (d == null) {
                ConsoleLogger.logError("Destination coordinates not provided.");
                return;
            }
            destRow = d[0];
            destCol = d[1];
        }
        // Check destination availability before proceeding.
        if (!destinationAvailable(flight, destRow, destCol)) {
            return; // Abort simulation if destination is locked.
        }
        flight.takeOff();
        simulateFlightMovement(flight, current[0], current[1], destRow, destCol);
    }

    // Simulate a scheduled flight takeoff.
    public void simulateScheduledTakeOff(ScheduledFlight sf) {
        Flight flight = sf.getFlight();
        int destRow = sf.getDestinationRow();
        int destCol = sf.getDestinationCol();
        
        // Check destination availability before simulation begins.
        if (!destinationAvailable(flight, destRow, destCol)) {
            ConsoleLogger.logError("Scheduled flight " + flight.getFlightNumber() + " cannot take off because destination " + airTrafficMap.getCell(destRow, destCol).getAirportLabel() + " is locked.");
            return;
        }
        
        ConsoleLogger.logInfo("Simulating scheduled takeoff for Flight " + flight.getFlightNumber() + " to " + sf.getDestinationAirportLabel());
        flight.takeOff();
        simulateFlightMovement(flight, sf.getSourceRow(), sf.getSourceCol(), destRow, destCol);
    }

    // Update positions of scheduled flights one step; returns true if all have reached their destination.
    public boolean updateScheduledFlights(List<ScheduledFlight> schedFlights) {
        boolean allArrived = true;
        for (ScheduledFlight sf : schedFlights) {
            if (sf.getCurrentRow() == sf.getDestinationRow() &&
                sf.getCurrentCol() == sf.getDestinationCol()) {
                continue; // Flight has arrived.
            }
            allArrived = false;
            List<int[]> path = calculatePath(sf.getCurrentRow(), sf.getCurrentCol(), sf.getDestinationRow(), sf.getDestinationCol());
            if (path.size() < 2) {
                ConsoleLogger.logError("No valid path for flight " + sf.getFlight().getFlightNumber());
                continue;
            }
            int[] nextStep = path.get(1);
            // Here we could also use destinationAvailable() if desired.
            moveFlight(sf.getFlight(), sf.getCurrentRow(), sf.getCurrentCol(), nextStep[0], nextStep[1]);
            sf.setCurrentPosition(nextStep[0], nextStep[1]);
        }
        return allArrived;
    }

    // Simulate flight movement along a path and then handle landing.
    public void simulateFlightMovement(Flight flight, int srcRow, int srcCol, int destRow, int destCol) {
        List<int[]> path = calculatePath(srcRow, srcCol, destRow, destCol);
        if (path.isEmpty()) {
            ConsoleLogger.logError("No valid path for flight " + flight.getFlightNumber());
            return;
        }
        moveFlightAlongPath(flight, path, destRow, destCol);
        simulateLandingProcedure(flight, destRow, destCol);
    }

    // Move a flight along the given path.
    public void moveFlightAlongPath(Flight flight, List<int[]> path, int destRow, int destCol) {
        int[] current = path.get(0);
        for (int i = 1; i < path.size(); i++) {
            int[] next = path.get(i);
            moveFlight(flight, current[0], current[1], next[0], next[1]);
            current = next;
            airTrafficMap.printMap();
            sleep(500);
        }
        ConsoleLogger.logSuccess("Flight " + flight.getFlightNumber() + " reached (" + destRow + ", " + destCol + ").");
    }

    // Helper to move a flight between cells.
    private void moveFlight(Flight flight, int fromRow, int fromCol, int toRow, int toCol) {
        if (!airTrafficMap.canPlaceFlightAt(flight, toRow, toCol)) {
            return; // Abort move if destination is locked.
        }
        airTrafficMap.getCell(fromRow, fromCol).removeFlight(flight);
        airTrafficMap.getCell(toRow, toCol).addFlight(flight);
    }

    private void sleep(int millis) {
        try { 
            Thread.sleep(millis); 
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt(); 
        }
    }

    // Handle landing or holding decision.
    private void simulateLandingProcedure(Flight flight, int destRow, int destCol) {
        MapCell cell = airTrafficMap.getCell(destRow, destCol);
        ConsoleLogger.logInfo("Flight " + flight.getFlightNumber() + " reached " 
                + cell.getAirportLabel() + ". Enter L to land or H to hold:");
        String decision = view.getUserInput();
        if ("L".equalsIgnoreCase(decision)) {
            flight.land();
            cell.setLockedBy(null);
        } else if ("H".equalsIgnoreCase(decision)) {
            cell.setLockedBy(flight);
            flight.setState(new HoldingState());
            ConsoleLogger.logInfo("Flight " + flight.getFlightNumber() + " is now holding at " + cell.getAirportLabel());
        } else {
            ConsoleLogger.logError("Invalid choice. Defaulting to landing.");
            flight.land();
            cell.setLockedBy(null);
        }
    }
}
