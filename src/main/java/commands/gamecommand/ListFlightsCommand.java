package commands.gamecommand;

import commands.Command;
import models.flight.IFlight;
import views.ConsoleLogger;

import java.util.List;

public class ListFlightsCommand implements Command {
    private final List<IFlight> flights;

    public ListFlightsCommand(List<IFlight> flights) {
        this.flights = flights;
    }

    @Override
    public void execute() {
        ConsoleLogger.logTitle("\n=== Current Flights ===");
        if (flights.isEmpty()) {
            ConsoleLogger.logError("No flights available.");
            return;
        }

        for (IFlight flight : flights) {
            String scheduledText = flight.isScheduled() ? "True" : "False";
            ConsoleLogger.logInfo(String.format("%s %s - Status: %s - Scheduled: %s",
                    flight.getType(),
                    flight.getFlightNumber(),
                    flight.getState(),
                    scheduledText));
        }
    }
}
