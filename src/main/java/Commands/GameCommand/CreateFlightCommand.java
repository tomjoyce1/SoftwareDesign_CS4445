package Commands.GameCommand;

import Commands.Command;
import Factories.FlightFactory;
import Interceptors.InterceptorDispatcher;
import Models.Flight.*;
import Models.Flight.FlightTypes.FlightType;
import Views.SimulatorView;

import java.util.List;

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
        System.out.println("\n=== Create New Flight ===");
        System.out.println("Flight.Flight types: PRIVATE, PASSENGER, MILITARY, CARGO");
        System.out.print("Enter flight type: ");
        String typeStr = view.getUserInput();

        System.out.print("Enter flight number: ");
        String flightNumber = view.getUserInput();
        dispatcher.dispatch("Flight.Flight number is " + flightNumber + ", type is " + typeStr);

        try {
            FlightType type = FlightType.valueOf(typeStr);
            Flight flight = FlightFactory.createFlight(type, flightNumber);
            flights.add(flight);
            System.out.println("Created " + flight.getType() + " " + flightNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid flight type!");
        }
    }
}
