package commands.flightcommand;

import commands.Command;
import models.Flight.Flight;

public class HoldCommand implements Command {
    private final Flight flight;

    public HoldCommand(Flight flight) {
        this.flight = flight;
    }

    @Override
    public void execute() {
        flight.hold();
    }
}
