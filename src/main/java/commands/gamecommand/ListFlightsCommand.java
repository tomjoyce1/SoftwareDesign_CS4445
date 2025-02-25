package commands.gamecommand;

import commands.Command;
import models.flight.Flight;
import views.ConsoleLogger;

import java.util.List;

public class ListFlightsCommand implements Command {
    private final List<Flight> flights;

    public ListFlightsCommand(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public void execute() {
        ConsoleLogger.logTitle("\n=== Current Flights ===");
        if (flights.isEmpty()) {
            ConsoleLogger.logError("No flights available.");
            return;
        }

        for (Flight flight : flights) {
            ConsoleLogger.logInfo(String.format("%s %s - Status: %s%n",
                    flight.getType(),
                    flight.getFlightNumber(),
                    flight.getState()));
        }
    }
}
