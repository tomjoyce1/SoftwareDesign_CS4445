package Commands.GameCommand;

import Commands.Command;
import Flight.Flight;
import Flight.FlightTypes.FlightType;
import Flight.FlightFactory;
import Interceptors.InterceptorDispatcher;

import java.util.List;
import java.util.Scanner;

public class CreateFlightCommand implements Command {
    private final List<Flight> flights;
    private final Scanner scanner;
    private final InterceptorDispatcher dispatcher;

    public CreateFlightCommand(List<Flight> flights, Scanner scanner, InterceptorDispatcher dispatcher) {
        this.flights = flights;
        this.scanner = scanner;
        this.dispatcher = dispatcher;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Create New Flight.Flight ===");
        System.out.println("Flight.Flight types: PRIVATE, PASSENGER, MILITARY, CARGO");
        System.out.print("Enter flight type: ");
        String typeStr = scanner.nextLine().toUpperCase();

        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine().toUpperCase();
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
