package Commands.GameCommand;

import Commands.Command;
import Radar.RadarDisplay;
import Flight.Flight;

import java.util.List;
import java.util.Scanner;

public class CheckFlightStatusCommand implements Command {
    private final List<Flight> flights;
    private final Scanner scanner;
    private final RadarDisplay radarDisplay;

    public CheckFlightStatusCommand(List<Flight> flights, Scanner scanner, RadarDisplay radarDisplay) {
        this.flights = flights;
        this.scanner = scanner;
        this.radarDisplay = radarDisplay;
    }

    @Override
    public void execute() {
        if (flights.isEmpty()) {
            System.out.println("No flights available!");
            return;
        }

        new ListFlightsCommand(flights).execute();
        System.out.print("Enter flight number to check status: ");
        String flightNumber = scanner.nextLine().toUpperCase();

        Flight selectedFlight = null;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Flight not found!");
            return;
        }

        System.out.printf("%s %s - Status: %s, Fuel: %d%n",
                selectedFlight.getType(),
                selectedFlight.getFlightNumber(),
                selectedFlight.getState(),
                selectedFlight.getFuel());

        System.out.println("\n--- Radar Display ---");
        radarDisplay.show();
    }
}