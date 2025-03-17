package commands.gamecommand;

import commands.Command;
import models.flight.FlightInterface;
import views.ConsoleLogger;
import views.SimulatorView;

import java.util.List;

public class CheckFlightStatusCommand implements Command {
    private final List<FlightInterface> flights;
    private final SimulatorView view;

    public CheckFlightStatusCommand(List<FlightInterface> flights, SimulatorView view) {
        this.flights = flights;
        this.view = view;
    }

    @Override
    public void execute() {
        if (flights.isEmpty()) {
            ConsoleLogger.logError("No flights available!");
            return;
        }

        new ListFlightsCommand(flights).execute();
        ConsoleLogger.logStandard("Enter flight number to check status: ");
        String flightNumber = view.getUserInput();

        FlightInterface selectedFlight = utils.FlightLookupUtil.findFlightByNumber(flights, flightNumber);

        if (selectedFlight == null) {
            ConsoleLogger.logError("Flight not found!");
            return;
        }

        String scheduledText = selectedFlight.isScheduled() ? "True" : "False";
        ConsoleLogger.logInfo(String.format("%s %s - Status: %s, Fuel: %d, Scheduled: %s%n",
                selectedFlight.getType(),
                selectedFlight.getFlightNumber(),
                selectedFlight.getState(),
                selectedFlight.getFuel(),
                scheduledText));
    }
}