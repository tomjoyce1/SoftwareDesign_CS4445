package commands.flightcommand;

import commands.Command;
import models.flight.IFlight;

public class LandCommand implements Command {
    private final IFlight flight;

    public LandCommand(IFlight flight) {
        this.flight = flight;
    }

    @Override
    public void execute() {
        flight.land();
    }
}
