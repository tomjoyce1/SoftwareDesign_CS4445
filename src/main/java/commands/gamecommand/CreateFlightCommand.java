package commands.gamecommand;

import commands.Command;
import factories.FlightFactory;
import bookmarks.InterceptorDispatcher;
import models.flight.IFlight;
import models.flight.flighttypes.FlightType;
import models.map.AirTrafficMap;
import models.map.FlightAirportAssigner;
import views.ConsoleLogger;
import views.SimulatorView;

import java.util.List;
import java.util.Arrays;

public class CreateFlightCommand implements Command {
    private final List<IFlight> flights;
    private final SimulatorView view;
    private final InterceptorDispatcher dispatcher;
    private final AirTrafficMap airTrafficMap;

    public CreateFlightCommand(List<IFlight> flights, SimulatorView view, InterceptorDispatcher dispatcher, AirTrafficMap airTrafficMap) {
        this.airTrafficMap = airTrafficMap;
        this.flights = flights;
        this.view = view;
        this.dispatcher = dispatcher;
    }

    @Override
    public void execute() {
        ConsoleLogger.logTitle("\n=== Create New Flight ===");
        ConsoleLogger.logStandard("Flight types: \n");
        String[] flightTypeOptions = Arrays.stream(FlightType.values())
                .map(Enum::name)
                .toArray(String[]::new);
        ConsoleLogger.logOption(flightTypeOptions);

        ConsoleLogger.logStandard("Enter flight type index (e.g., 1): ");
        Integer typeIndex = utils.InputParserUtil.parseInt(view.getUserInput());
        if (typeIndex == null || typeIndex < 1 || typeIndex > FlightType.values().length) {
            ConsoleLogger.logError("Invalid flight type selection!");
            return;
        }

        FlightType type = FlightType.values()[typeIndex - 1];

        ConsoleLogger.logStandard("Enter flight number: ");
        String flightNumber = view.getUserInput();

        for(IFlight flight : flights) {
            if(flight.getFlightNumber().equals(flightNumber)) {
                ConsoleLogger.logError("Flight number already exists!");
                return;
            }
        }

        int initialPassengerCount = 0;
        if (type != FlightType.MILITARY && type != FlightType.CARGO) {
            ConsoleLogger.logStandard("Enter initial passenger count: ");
            String passengerInput = view.getUserInput();
            Integer parsedPassengerCount = utils.InputParserUtil.parseInt(passengerInput);
            if (parsedPassengerCount == null) {
                return;
            }
            initialPassengerCount = parsedPassengerCount;
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
        Integer parsedCrewCount = utils.InputParserUtil.parseInt(crewInput);
        if (parsedCrewCount == null) {
            return;
        }
        crewCount = parsedCrewCount;
        

        try {
            dispatcher.dispatch("Flight number is: " + flightNumber 
                                + ", type is: " + type.name() 
                                + ", passenger count is: " + initialPassengerCount 
                                + ", agency is: " + flightAgency 
                                + ", pilot name is: " + pilotName 
                                + ", crew count is: " + crewCount);
            IFlight flight = FlightFactory.createDecoratedFlight(type, flightNumber, initialPassengerCount, flightAgency, pilotName, crewCount);
            flights.add(flight);
            ConsoleLogger.logSuccess("Created " + flight.getType() + " " + flightNumber);

            FlightAirportAssigner.assignFlightToAirport(flight, airTrafficMap, view);
        } catch (IllegalArgumentException e) {
            ConsoleLogger.logError("Invalid flight type or input!");
        }
    }
}
