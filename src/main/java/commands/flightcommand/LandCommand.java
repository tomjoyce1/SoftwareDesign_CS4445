package commands.flightcommand;

import commands.Command;
import models.Flight.Flight;

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
