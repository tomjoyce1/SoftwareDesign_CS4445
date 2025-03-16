package models.map.takeoff;

import java.util.List;

import models.map.MapCell;
import models.map.AirTrafficMap;
import views.SimulatorView;
import views.ConsoleLogger;

public class DestinationSelector {

    private final AirTrafficMap airTrafficMap;
    private final SimulatorView view;

    public DestinationSelector(AirTrafficMap map, SimulatorView view) {
        this.airTrafficMap = map;
        this.view = view;
    }

    public int[] promptForDestination(int currentRow, int currentCol) {
        List<int[]> destinations = airTrafficMap.getAvailableDestinations(currentRow, currentCol);
        if (destinations.isEmpty()) {
            ConsoleLogger.logError("No destination airports available!");
            return new int[0];
        }
        for (int i = 0; i < destinations.size(); i++) {
            MapCell cell = airTrafficMap.getCell(destinations.get(i)[0], destinations.get(i)[1]);
            ConsoleLogger.logStandard((i + 1) + ". " + cell.getAirportLabel());
        }
        ConsoleLogger.logStandard("Enter the number corresponding to your destination airport:");

        int choice = Integer.parseInt(view.getUserInput());
        if (choice < 1 || choice > destinations.size()) {
            ConsoleLogger.logError("Selection out of range!");
            return new int[0];
        }
        return destinations.get(choice - 1);
    }
}