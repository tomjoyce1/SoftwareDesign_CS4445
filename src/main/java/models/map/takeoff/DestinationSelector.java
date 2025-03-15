package models.map.takeoff;

import java.util.List;

import models.flight.IFlight;
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

    public boolean destinationAvailable(IFlight flight, int destRow, int destCol) {
        return airTrafficMap.canPlaceFlightAt(flight, destRow, destCol);
    }
    
}
