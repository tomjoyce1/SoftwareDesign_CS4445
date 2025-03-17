package models.map.takeoff;

import models.map.AirTrafficMap;
import models.flight.FlightInterface;

import java.util.List;

public class FlightMovementManager {
    private final AirTrafficMap airTrafficMap;

    public FlightMovementManager(AirTrafficMap airTrafficMap) {
        this.airTrafficMap = airTrafficMap;
    }

    public void moveFlight(FlightInterface flight, int fromRow, int fromCol, int toRow, int toCol) {
        if (!airTrafficMap.canPlaceFlightAt(flight, toRow, toCol)) {
            return;
        }
        airTrafficMap.getCell(fromRow, fromCol).removeFlight(flight);
        airTrafficMap.getCell(toRow, toCol).addFlight(flight);
    }

    public void moveFlightAlongPath(FlightInterface flight, List<int[]> path) {
        int[] current = path.get(0);
        for (int i = 1; i < path.size(); i++) {
            int[] next = path.get(i);
            moveFlight(flight, current[0], current[1], next[0], next[1]);
            current = next;
            airTrafficMap.printMap();
            sleep(500);
        }
    }

    public void moveFlightAlongPath(FlightInterface flight, List<int[]> path, int destRow, int destCol) {
        int[] current = path.get(0);
        for (int i = 1; i < path.size(); i++) {
            int[] next = path.get(i);
            moveFlight(flight, current[0], current[1], next[0], next[1]);
            current = next;
            airTrafficMap.printMap();
            sleep(500);
        }
    }

    private void sleep(int millis) {
        try { Thread.sleep(millis); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}