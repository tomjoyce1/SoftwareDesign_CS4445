package commands.gamecommand;

import commands.Command;
import models.decorators.flightdecorator.CrewInfoDecorator;
import models.decorators.flightdecorator.FlightAgencyDecorator;
import models.decorators.flightdecorator.PassengerDecorator;
import models.flight.FlightInterface;
import views.ConsoleLogger;
import views.SimulatorView;

import java.util.List;

public class ViewFlightInfo implements Command {

    private final List<FlightInterface> flights;
    private final SimulatorView view;

    public ViewFlightInfo(List<FlightInterface> flights, SimulatorView view) {
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
        flights.forEach(flight -> ConsoleLogger.logStandard("Flight Number: "
                + flight.getFlightNumber() + ", Flight Type: " + flight.getType()));

        ConsoleLogger.logStandard("\nEnter flight number to view details: ");
        String flightNumber = view.getUserInput();

        FlightInterface flight = flights.stream()
                .filter(f -> f.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElse(null);

        if (flight == null) {
            ConsoleLogger.logError("Flight not found!");
            return;
        }

        displayFlightInfo(flight);
    }

    private void displayFlightInfo(FlightInterface flight) {
        ConsoleLogger.logTitle("\n=== Flight Information ===");
        ConsoleLogger.logStandard("Flight Number: " + flight.getFlightNumber());
        ConsoleLogger.logStandard("Flight Type: " + flight.getType());
        ConsoleLogger.logStandard("Fuel: " + flight.getFuel());

        if (flight instanceof PassengerDecorator passengerDecorator) {
            ConsoleLogger.logStandard("Passenger Count: " + passengerDecorator.getPassengerCount());
        }

        if (flight instanceof FlightAgencyDecorator flightAgencyDecorator) {
            ConsoleLogger.logStandard("Flight Agency: " + flightAgencyDecorator.getFlightAgency());
        }

        if (flight instanceof CrewInfoDecorator crewInfoDecorator) {
            ConsoleLogger.logStandard("Pilot Name: " + crewInfoDecorator.getPilotName());
            ConsoleLogger.logStandard("Crew Count: " + crewInfoDecorator.getCrewCount());
        }
    }
}