package models.map.takeoff;

import models.map.AirTrafficMap;
import models.pathfinder.PathFinder;

import java.util.List;

public class FlightPathCalculator {
    private final AirTrafficMap airTrafficMap;
    public FlightPathCalculator(AirTrafficMap map) {
        this.airTrafficMap = map;
    }

    public List<int[]> calculatePath(int srcRow, int srcCol, int destRow, int destCol) {
        return new PathFinder(airTrafficMap).findShortestPath(srcRow, srcCol, destRow, destCol);
    }
}