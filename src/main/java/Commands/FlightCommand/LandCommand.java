package Commands.FlightCommand;

import Commands.Command;
import Models.Flight.Flight;

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
