package commands.flightcommand;

import commands.Command;
import models.flight.IFlight;

public class HoldCommand implements Command {
    private final IFlight flight;

    public HoldCommand(IFlight flight) {
        this.flight = flight;
    }

    @Override
    public void execute() {
        flight.hold();
    }
}
