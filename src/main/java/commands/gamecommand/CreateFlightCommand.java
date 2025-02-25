package commands.gamecommand;

import commands.Command;
import factories.FlightFactory;
import bookmarks.InterceptorDispatcher;
import models.flight.Flight;
import models.flight.flighttypes.FlightType;
import views.ConsoleLogger;
import views.SimulatorView;

import java.util.List;
import java.util.Arrays;

public class CreateFlightCommand implements Command {
    private final List<Flight> flights;
    private final SimulatorView view;
    private final InterceptorDispatcher dispatcher;

    public CreateFlightCommand(List<Flight> flights, SimulatorView view, InterceptorDispatcher dispatcher) {
        this.flights = flights;
        this.view = view;
        this.dispatcher = dispatcher;
    }

    @Override
    public void execute() {
        ConsoleLogger.logTitle("\n=== Create New Flight ===");
        ConsoleLogger.logStandard("Flight types: \n");
        ConsoleLogger.logOption(Arrays.stream(FlightType.values())
                .map(Enum::name)
                .toArray(String[]::new));

        ConsoleLogger.logStandard("Enter flight type: ");
        String typeStr = view.getUserInput();

        ConsoleLogger.logStandard("Enter flight number: ");
        String flightNumber = view.getUserInput();
        dispatcher.dispatch("Flight number is " + flightNumber + ", type is " + typeStr);

        try {
            FlightType type = FlightType.valueOf(typeStr);
            Flight flight = FlightFactory.createFlight(type, flightNumber);
            flights.add(flight);
            ConsoleLogger.logSuccess("Created " + flight.getType() + " " + flightNumber);
        } catch (IllegalArgumentException e) {
            ConsoleLogger.logError("Invalid flight type!");
        }
    }
}
