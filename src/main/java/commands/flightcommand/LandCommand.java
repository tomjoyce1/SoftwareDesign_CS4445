package commands.flightcommand;

import commands.Command;
import models.flight.Flight;

public class LandCommand implements Command {
    private final Flight flight;

    public LandCommand(Flight flight) {
        this.flight = flight;
    }

    @Override
    public void execute() {
        flight.land();
    }
}
