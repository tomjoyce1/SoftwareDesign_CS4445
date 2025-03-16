package utils;

import models.map.AirTrafficMap;
import models.map.MapCell;
import views.ConsoleLogger;
import views.SimulatorView;

import java.util.ArrayList;
import java.util.List;

public class AirportSelectionUtil {

    public static int[] selectAirport(AirTrafficMap airTrafficMap, SimulatorView view, int excludeRow, int excludeCol) {
        List<int[]> airportLocations = getAirportLocations(airTrafficMap, excludeRow, excludeCol);
        List<String> airportLabels = getAirportLabels(airTrafficMap, excludeRow, excludeCol);

        if (airportLocations.isEmpty()) {
            ConsoleLogger.logError("No destination airports available!");
            return new int[0];
        }

        displayAvailableAirports(airportLabels);
        int choice = getUserChoice(view);

        if (choice < 1 || choice > airportLocations.size()) {
            ConsoleLogger.logError("Selection out of range!");
            return new int[0];
        }
        return airportLocations.get(choice - 1);
    }

    private static List<int[]> getAirportLocations(AirTrafficMap airTrafficMap, int excludeRow, int excludeCol) {
        List<int[]> airportLocations = new ArrayList<>();
        for (int i = 0; i < airTrafficMap.getRows(); i++) {
            for (int j = 0; j < airTrafficMap.getCols(); j++) {
                if (i != excludeRow || j != excludeCol) {
                    MapCell cell = airTrafficMap.getCell(i, j);
                    if (cell.isAirport()) {
                        airportLocations.add(new int[]{i, j});
                    }
                }
            }
        }
        return airportLocations;
    }

    private static List<String> getAirportLabels(AirTrafficMap airTrafficMap, int excludeRow, int excludeCol) {
        List<String> airportLabels = new ArrayList<>();
        for (int i = 0; i < airTrafficMap.getRows(); i++) {
            for (int j = 0; j < airTrafficMap.getCols(); j++) {
                if (i != excludeRow || j != excludeCol) {
                    MapCell cell = airTrafficMap.getCell(i, j);
                    if (cell.isAirport()) {
                        airportLabels.add(cell.getAirportLabel());
                    }
                }
            }
        }
        return airportLabels;
    }

    private static void displayAvailableAirports(List<String> airportLabels) {
        ConsoleLogger.logStandard("Available destination airports:");
        for (int idx = 0; idx < airportLabels.size(); idx++) {
            ConsoleLogger.logStandard((idx + 1) + ". " + airportLabels.get(idx));
        }
        ConsoleLogger.logStandard("Enter the number corresponding to the destination airport:");
    }

    private static int getUserChoice(SimulatorView view) {
        return Integer.parseInt(view.getUserInput());
    }

    private AirportSelectionUtil() {
        throw new IllegalStateException("Utility class");
    }
}