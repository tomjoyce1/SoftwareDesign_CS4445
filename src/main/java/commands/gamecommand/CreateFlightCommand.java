package commands.gamecommand;

import commands.Command;
import factories.FlightFactory;
import bookmarks.InterceptorDispatcher;
import models.flight.FlightInterface;
import models.flight.flighttypes.FlightType;
import models.map.AirTrafficMap;
import models.map.FlightAirportAssigner;
import views.ConsoleLogger;
import views.SimulatorView;

import java.util.List;
import java.util.Arrays;

public class CreateFlightCommand implements Command {
    private final List<FlightInterface> flights;
    private final SimulatorView view;
    private final InterceptorDispatcher dispatcher;
    private final AirTrafficMap airTrafficMap;

    public CreateFlightCommand(List<FlightInterface> flights, SimulatorView view, InterceptorDispatcher dispatcher, AirTrafficMap airTrafficMap) {
        this.flights = flights;
        this.view = view;
        this.dispatcher = dispatcher;
        this.airTrafficMap = airTrafficMap;
    }

    @Override
    public void execute() {
        ConsoleLogger.logTitle("\n=== Create New Flight ===");
        ConsoleLogger.logStandard("Flight types: \n");
        String[] flightTypeOptions = Arrays.stream(FlightType.values()).map(Enum::name).toArray(String[]::new);
        ConsoleLogger.logOption(flightTypeOptions);

        int typeIndex = getUserInputAsInt("Enter flight type index (e.g., 1): ");
        if (typeIndex < 1 || typeIndex > FlightType.values().length) {
            ConsoleLogger.logError("Invalid flight type selection!");
            return;
        }

        FlightType type = FlightType.values()[typeIndex - 1];
        String flightNumber = getUserInput("Enter flight number: ");
        if (flights.stream().anyMatch(f -> f.getFlightNumber().equals(flightNumber))) {
            ConsoleLogger.logError("Flight number already exists!");
            return;
        }

        int initialPassengerCount = 0;
        if (type != FlightType.MILITARY && type != FlightType.CARGO) {
            initialPassengerCount = getUserInputAsInt("Enter initial passenger count: ");
        }

        String flightAgency = "";
        if (type != FlightType.MILITARY) {
            flightAgency = getUserInput("Enter flight agency name: ");
        }

        String pilotName = getUserInput("Enter pilot's name: ");
        if (!pilotName.matches("^[a-zA-Z\\s]+$")) {
            ConsoleLogger.logError("Pilot names must contain only letters and spaces!");
            return;
        }

        int crewCount = getUserInputAsInt("Enter crew count: ");

        try {
            dispatcher.dispatch(String.format("Flight number is: %s, type is: %s, passenger count is: %d, agency is: %s, pilot name is: %s, crew count is: %d",
                    flightNumber, type.name(), initialPassengerCount, flightAgency, pilotName, crewCount));
            FlightInterface flight = FlightFactory.createDecoratedFlight(type, flightNumber, initialPassengerCount, flightAgency, pilotName, crewCount);
            flights.add(flight);
            ConsoleLogger.logSuccess("Created " + flight.getType() + " " + flightNumber);
            FlightAirportAssigner.assignFlightToAirport(flight, airTrafficMap, view);
        } catch (IllegalArgumentException e) {
            ConsoleLogger.logError("Invalid flight type or input!");
        }
    }

    private String getUserInput(String prompt) {
        ConsoleLogger.logStandard(prompt);
        return view.getUserInput();
    }

    private int getUserInputAsInt(String prompt) {
        return Integer.parseInt(getUserInput(prompt));
    }
}