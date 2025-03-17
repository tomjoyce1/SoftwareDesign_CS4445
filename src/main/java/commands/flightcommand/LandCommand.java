package commands.flightcommand;

import commands.Command;
import models.flight.FlightInterface;

public class LandCommand implements Command {
    private final FlightInterface flight;

    public LandCommand(FlightInterface flight) {
        this.flight = flight;
    }

    @Override
    public void execute() {
        flight.land();
    }
}
