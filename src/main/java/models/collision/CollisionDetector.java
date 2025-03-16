package models.collision;

import models.map.takeoff.ScheduledFlight;
import models.map.AirTrafficMap;
import models.states.CrashedState;
import views.ConsoleLogger;

import java.util.List;

public class CollisionDetector {
    private final AirTrafficMap airTrafficMap;

    public CollisionDetector(AirTrafficMap airTrafficMap) {
        this.airTrafficMap = airTrafficMap;
    }

    public void checkAndHandleCollisions(List<ScheduledFlight> scheduledFlights) {
        // iterates over all flight pairs
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
                }
            }
        }
    }

    private boolean isColliding(ScheduledFlight sf1, ScheduledFlight sf2) {
        // simple collision when 2 of our flights are in the same cell
        return sf1.getCurrentRow() == sf2.getCurrentRow() &&
                sf1.getCurrentCol() == sf2.getCurrentCol();
    }
}