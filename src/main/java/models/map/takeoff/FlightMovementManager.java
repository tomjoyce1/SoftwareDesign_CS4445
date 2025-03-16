package models.map.takeoff;

import models.map.AirTrafficMap;
import models.flight.FlightInterface;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FlightMovementManager {
    private final AirTrafficMap airTrafficMap;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public FlightMovementManager(AirTrafficMap airTrafficMap) {
        this.airTrafficMap = airTrafficMap;
    }

    public void moveFlight(FlightInterface flight, int fromRow, int fromCol, int toRow, int toCol) {
        if (airTrafficMap.canPlaceFlightAt(flight, toRow, toCol)) {
            airTrafficMap.getCell(fromRow, fromCol).removeFlight(flight);
            airTrafficMap.getCell(toRow, toCol).addFlight(flight);
        }
    }

    public void moveFlightAlongPath(FlightInterface flight, List<int[]> path) {
        for (int i = 1; i < path.size(); i++) {
            int[] current = path.get(i - 1);
            int[] next = path.get(i);
            scheduler.schedule(() -> {
                moveFlight(flight, current[0], current[1], next[0], next[1]);
                airTrafficMap.printMap();
            }, i * 500L, TimeUnit.MILLISECONDS);
        }
    }
}