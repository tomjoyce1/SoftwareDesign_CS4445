package commands.gamecommand;

import commands.Command;
import models.map.AirTrafficMap;
import models.map.MapCell;
import models.flight.Flight;
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
        ConsoleLogger.logStandard("Enter cell row (0 to " + (airTrafficMap.getRows() - 1) + "): ");
        String rowInput = view.getUserInput();
        Integer row = utils.InputParserUtil.parseInt(rowInput);
        if (row == null) {
            return;
        }

        ConsoleLogger.logStandard("Enter cell column (0 to " + (airTrafficMap.getCols() - 1) + "): ");
        String colInput = view.getUserInput();
        Integer col = utils.InputParserUtil.parseInt(colInput);
        if (col == null) {
            return;
}

        try {
            MapCell cell = airTrafficMap.getCell(row, col);
            List<Flight> flights = cell.getFlights();
            if (flights.isEmpty()) {
                ConsoleLogger.logStandard("No flights in cell (" + row + ", " + col + ").");
            } else {
                ConsoleLogger.logTitle("Flights in cell (" + row + ", " + col + "):");
                for (Flight flight : flights) {
                    ConsoleLogger.logInfo(flight.getFlightNumber() + " - " + flight.getType() +
                            " (State: " + flight.getState() + ", Fuel: " + flight.getFuel() + ")");
                }
            }
        } catch (IllegalArgumentException e) {
            ConsoleLogger.logError("Error: " + e.getMessage());
        }
    }
}