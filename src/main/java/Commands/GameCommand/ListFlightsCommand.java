package Commands.GameCommand;

import Commands.Command;
import Flight.Flight;

import java.util.List;

public class ListFlightsCommand implements Command {
    private final List<Flight> flights;

    public ListFlightsCommand(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Current Flights ===");
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }

        for (Flight flight : flights) {
            System.out.printf("%s %s - Status: %s%n",
                    flight.getType(),
                    flight.getFlightNumber(),
                    flight.getState());
        }
    }
}
