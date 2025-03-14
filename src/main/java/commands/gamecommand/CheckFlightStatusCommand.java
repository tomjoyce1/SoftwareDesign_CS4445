package commands.gamecommand;

import commands.Command;
import models.flight.Flight;
import views.ConsoleLogger;
import views.SimulatorView;

import java.util.List;

public class CheckFlightStatusCommand implements Command {
    private final List<Flight> flights;
    private final SimulatorView view;

    public CheckFlightStatusCommand(List<Flight> flights, SimulatorView view) {
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

    // Use the helper method for flight lookup
    Flight selectedFlight = utils.FlightLookupUtil.findFlightByNumber(flights, flightNumber);

    if (selectedFlight == null) {
        ConsoleLogger.logError("Flight not found!");
        return;
    }

    ConsoleLogger.logInfo(String.format("%s %s - Status: %s, Fuel: %d%n",
            selectedFlight.getType(),
            selectedFlight.getFlightNumber(),
            selectedFlight.getState(),
            selectedFlight.getFuel()));
    }
}
