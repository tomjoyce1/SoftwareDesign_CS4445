package commands.flightcommand;

import commands.Command;
import models.flight.FlightInterface;

public class HoldCommand implements Command {
    private final FlightInterface flight;

    public HoldCommand(FlightInterface flight) {
        this.flight = flight;
    }

    @Override
    public void execute() {
        flight.hold();
    }
}
