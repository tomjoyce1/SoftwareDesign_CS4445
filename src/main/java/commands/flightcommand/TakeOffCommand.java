package commands.flightcommand;

import commands.Command;
import models.flight.Flight;

public class TakeOffCommand implements Command {
    private final Flight flight;

    public TakeOffCommand(Flight flight) {
        this.flight = flight;
    }

    @Override
    public void execute() {
        flight.takeOff();
    }
}
