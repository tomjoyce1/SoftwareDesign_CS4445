package Commands.FlightCommand;

import Commands.Command;
import Flight.Flight;

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
