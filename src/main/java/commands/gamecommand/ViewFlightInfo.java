package commands.gamecommand;

import commands.Command;
import models.decorators.flightdecorator.CrewInfoDecorator;
import models.decorators.flightdecorator.FlightAgencyDecorator;
import models.decorators.flightdecorator.PassengerDecorator;
import models.flight.Flight;
import views.ConsoleLogger;
import views.SimulatorView;

import java.util.List;

public class ViewFlightInfo implements Command {

    private final List<Flight> flights;
    private final SimulatorView view;

    public ViewFlightInfo(List<Flight> flights, SimulatorView view) {
        this.flights = flights;
        this.view = view;
    }

    @Override
    public void execute() {
        if (flights.isEmpty()) {
            ConsoleLogger.logError("No flights available.");
            return;
        }

        ConsoleLogger.logTitle("\n=== List of Flights ===");
        flights.forEach(flight -> ConsoleLogger.logStandard("Flight Number: " + flight.getFlightNumber() + ", Flight Type: " + flight.getType()));

        ConsoleLogger.logStandard("\nEnter flight number to view details: ");
        String flightNumber = view.getUserInput();

        Flight flight = flights.stream()
                .filter(f -> f.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElse(null);

        if (flight == null) {
            ConsoleLogger.logError("Flight not found!");
            return;
        }

        displayFlightInfo(flight);
    }

    private void displayFlightInfo(Flight flight) {
        ConsoleLogger.logTitle("\n=== Flight Information ===");
        ConsoleLogger.logStandard("Flight Number: " + flight.getFlightNumber());
        ConsoleLogger.logStandard("Flight Type: " + flight.getType());
        ConsoleLogger.logStandard("Fuel: " + flight.getFuel());

        if (flight instanceof PassengerDecorator) {
            PassengerDecorator passengerDecorator = (PassengerDecorator) flight;
            ConsoleLogger.logStandard("Passenger Count: " + passengerDecorator.getPassengerCount());
        }

        if (flight instanceof FlightAgencyDecorator) {
            FlightAgencyDecorator flightAgencyDecorator = (FlightAgencyDecorator) flight;
            ConsoleLogger.logStandard("Flight Agency: " + flightAgencyDecorator.getFlightAgency());
        }

        if (flight instanceof CrewInfoDecorator) {
            CrewInfoDecorator crewInfoDecorator = (CrewInfoDecorator) flight;
            ConsoleLogger.logStandard("Pilot Name: " + crewInfoDecorator.getPilotName());
            ConsoleLogger.logStandard("Crew Count: " + crewInfoDecorator.getCrewCount());
        }
    }
}