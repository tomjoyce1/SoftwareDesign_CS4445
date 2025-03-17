package commands.flightcommand;

import commands.Command;
import models.flight.FlightInterface;
import views.ConsoleLogger;

public class RefuelPlaneCommand implements Command {
    private final FlightInterface flight;

    public RefuelPlaneCommand(FlightInterface flight) {
        this.flight = flight;
    }

    @Override
    public void execute() {
        if ("Crashed".equals(flight.getState())) {
            ConsoleLogger.logError("Flight " + flight.getFlightNumber() + " has crashed and cannot be refueled.");
            return;
        }
        flight.setFuel(100);
        ConsoleLogger.logSuccess("Flight " + flight.getFlightNumber() + " has been refueled to full capacity.");
    }
}