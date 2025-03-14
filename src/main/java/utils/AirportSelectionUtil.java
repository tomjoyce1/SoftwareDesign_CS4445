package utils;

import models.map.AirTrafficMap;
import models.map.MapCell;
import views.ConsoleLogger;
import views.SimulatorView;
import java.util.ArrayList;
import java.util.List;

public class AirportSelectionUtil {

    public static int[] selectAirport(AirTrafficMap airTrafficMap, SimulatorView view, int excludeRow, int excludeCol) {
        List<int[]> airportLocations = new ArrayList<>();
        List<String> airportLabels = new ArrayList<>();

        for (int i = 0; i < airTrafficMap.getRows(); i++) {
            for (int j = 0; j < airTrafficMap.getCols(); j++) {
                if (i == excludeRow && j == excludeCol) continue;
                MapCell cell = airTrafficMap.getCell(i, j);
                if (cell.isAirport()) {
                    airportLocations.add(new int[]{i, j});
                    airportLabels.add(cell.getAirportLabel());
                }
            }
        }

        if (airportLocations.isEmpty()) {
            ConsoleLogger.logError("No destination airports available!");
            return null;
        }

        ConsoleLogger.logStandard("Available destination airports:");
        for (int idx = 0; idx < airportLabels.size(); idx++) {
            ConsoleLogger.logStandard((idx + 1) + ". " + airportLabels.get(idx));
        }
        ConsoleLogger.logStandard("Enter the number corresponding to the destination airport:");

        // Replace in-line parsing with a call to InputParserUtil.
        Integer choice = utils.InputParserUtil.parseInt(view.getUserInput());
        if (choice == null) {
            return null;
        }
        if (choice < 1 || choice > airportLocations.size()) {
            ConsoleLogger.logError("Selection out of range!");
            return null;
        }
        return airportLocations.get(choice - 1);
    }
}
