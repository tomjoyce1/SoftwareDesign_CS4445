package commands.gamecommand;

import commands.Command;
import models.map.AirTrafficMap;
import models.map.MapCell;
import models.flight.FlightInterface;
import views.ConsoleLogger;
import views.SimulatorView;

import java.util.List;

public class ViewCellContentsCommand implements Command {
    private final AirTrafficMap airTrafficMap;
    private final SimulatorView view;

    public ViewCellContentsCommand(AirTrafficMap airTrafficMap, SimulatorView view) {
        this.airTrafficMap = airTrafficMap;
        this.view = view;
    }

    @Override
    public void execute() {
        int row = getUserInputAsInt("Enter cell row (0 to " + (airTrafficMap.getRows() - 1) + "): ");
        int col = getUserInputAsInt("Enter cell column (0 to " + (airTrafficMap.getCols() - 1) + "): ");

        try {
            MapCell cell = airTrafficMap.getCell(row, col);
            List<FlightInterface> flights = cell.getFlights();
            if (flights.isEmpty()) {
                ConsoleLogger.logStandard("No flights in cell (" + row + ", " + col + ").");
            } else {
                ConsoleLogger.logTitle("Flights in cell (" + row + ", " + col + "):");
                for (FlightInterface flight : flights) {
                    ConsoleLogger.logInfo(flight.getFlightNumber() + " - " + flight.getType() +
                            " (State: " + flight.getState() + ", Fuel: " + flight.getFuel() + ")");
                }
            }
        } catch (IllegalArgumentException e) {
            ConsoleLogger.logError("Error: " + e.getMessage());
        }
    }

    private int getUserInputAsInt(String prompt) {
        ConsoleLogger.logStandard(prompt);
        return Integer.parseInt(view.getUserInput());
    }
}