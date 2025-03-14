package models.collision;

import models.map.takeoff.ScheduledFlight;
import models.map.AirTrafficMap;
import models.states.CrashedState;
import views.ConsoleLogger;

import java.util.List;

public class CollisionDetector {
    private final AirTrafficMap airTrafficMap;
    private final int collisionThreshold; // e.g., 0 for same-cell collisions

    public CollisionDetector(AirTrafficMap airTrafficMap, int collisionThreshold) {
        this.airTrafficMap = airTrafficMap;
        this.collisionThreshold = collisionThreshold;
    }
    
    /**
     * Checks for collisions among scheduled flights. If two flights are in the same cell,
     * logs the crash, sets their state to Crashed, and removes them from the map.
     * Returns true if any collision occurred.
     */
    public boolean checkAndHandleCollisions(List<ScheduledFlight> scheduledFlights) {
        for (int i = 0; i < scheduledFlights.size(); i++) {
            for (int j = i + 1; j < scheduledFlights.size(); j++) {
                ScheduledFlight sf1 = scheduledFlights.get(i);
                ScheduledFlight sf2 = scheduledFlights.get(j);
                if (isColliding(sf1, sf2)) {
                    String crashLocation = "[" + sf1.getCurrentRow() + "," + sf1.getCurrentCol() + "]";
                    ConsoleLogger.logError("Crash detected at " + crashLocation + " between flights " +
                                             sf1.getFlight().getFlightNumber() + " and " +
                                             sf2.getFlight().getFlightNumber());
                    sf1.getFlight().setState(new CrashedState());
                    sf2.getFlight().setState(new CrashedState());
                    airTrafficMap.getCell(sf1.getCurrentRow(), sf1.getCurrentCol()).removeFlight(sf1.getFlight());
                    airTrafficMap.getCell(sf2.getCurrentRow(), sf2.getCurrentCol()).removeFlight(sf2.getFlight());
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isColliding(ScheduledFlight sf1, ScheduledFlight sf2) {
        // A simple collision is when both flights are in the same cell.
        return sf1.getCurrentRow() == sf2.getCurrentRow() &&
               sf1.getCurrentCol() == sf2.getCurrentCol();
        // To extend to "within one cell", you might compute the Manhattan distance.
    }
}