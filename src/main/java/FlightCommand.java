@FunctionalInterface
public interface FlightCommand extends Command {}

class TakeOffCommand implements FlightCommand {
    private final Flight flight;

    public TakeOffCommand(Flight flight) {
        this.flight = flight;
    }

    @Override
    public void execute() {
        flight.takeOff();
    }
}

class LandCommand implements FlightCommand {
    private final Flight flight;

    public LandCommand(Flight flight) {
        this.flight = flight;
    }

    @Override
    public void execute() {
        flight.land();
    }
}

class HoldCommand implements FlightCommand {
    private final Flight flight;

    public HoldCommand(Flight flight) {
        this.flight = flight;
    }

    @Override
    public void execute() {
        flight.hold();
    }
}