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

        for(Flight flight : flights) {
            if(flight.getFlightNumber().equals(flightNumber)) {
                ConsoleLogger.logError("Flight number already exists!");
                return;
            }
        }

        FlightType type;
        try {
            type = FlightType.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            ConsoleLogger.logError("Invalid flight type!");
            return;
        }

        // *new prompts for further attributes 
        int initialPassengerCount = 0;
        if (type != FlightType.MILITARY && type != FlightType.CARGO) {
            ConsoleLogger.logStandard("Enter initial passenger count: ");
            String passengerInput = view.getUserInput();
            try {
                initialPassengerCount = Integer.parseInt(passengerInput);
            } catch (NumberFormatException e) {
                ConsoleLogger.logError("Invalid input for passenger count!");
                return;
            }
        }

        String flightAgency = "";
        if (type != FlightType.MILITARY) {
        ConsoleLogger.logStandard("Enter flight agency name: ");
        flightAgency = view.getUserInput();
    }      

        ConsoleLogger.logStandard("Enter pilot's name: ");
        String pilotName = view.getUserInput();
        if (!pilotName.matches("^[a-zA-Z\\s]+$")) {
            ConsoleLogger.logError("Pilot names must contain only letters and spaces!");
        return;
        }

        ConsoleLogger.logStandard("Enter crew count: ");
        String crewInput = view.getUserInput();
        int crewCount = 0;
        try {
            crewCount = Integer.parseInt(crewInput);
        } catch (NumberFormatException e) {
            ConsoleLogger.logError("Invalid input for crew count!");
            return;
        }
        

        try {
            type = FlightType.valueOf(typeStr);
            dispatcher.dispatch("Flight number is: " + flightNumber 
                                + ", type is: " + typeStr 
                                + ", passenger count is: " + initialPassengerCount 
                                + ", agency is: " + flightAgency 
                                + ", pilot name is: " + pilotName 
                                + ", crew count is: " + crewCount);
            Flight flight = FlightFactory.createDecoratedFlight(type, flightNumber, initialPassengerCount, flightAgency, pilotName, crewCount);
            flights.add(flight);
            ConsoleLogger.logSuccess("Created " + flight.getType() + " " + flightNumber);
        } catch (IllegalArgumentException e) {
            ConsoleLogger.logError("Invalid flight type or input!");
        }
    }
}
