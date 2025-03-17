package commands.flightcommand;

import commands.Command;
import models.flight.FlightInterface;

public class TakeOffCommand implements Command {
    private final FlightInterface flight;

    public TakeOffCommand(FlightInterface flight) {
        this.flight = flight;
    }
    
    @Override
    public void execute() {
        flight.takeOff();
    }
}
