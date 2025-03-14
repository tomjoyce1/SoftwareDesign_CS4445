package commands.gamecommand;

import commands.Command;
import models.flight.IFlight;
import views.ConsoleLogger;
import views.SimulatorView;

import java.util.List;

public class CheckFlightStatusCommand implements Command {
    private final List<IFlight> flights;
    private final SimulatorView view;

    public CheckFlightStatusCommand(List<IFlight> flights, SimulatorView view) {
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
    IFlight selectedFlight = utils.FlightLookupUtil.findFlightByNumber(flights, flightNumber);

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
