package commands.flightcommand;

import commands.Command;
import models.flight.IFlight;
import models.map.AirTrafficMap;
import models.map.takeoff.FlightSimulator;
import views.SimulatorView;

public class TakeOffCommand implements Command {
    private final IFlight flight;
    private final AirTrafficMap airTrafficMap;
    private final SimulatorView view;

    public TakeOffCommand(IFlight flight, AirTrafficMap airTrafficMap, SimulatorView view) {
        this.flight = flight;
        this.airTrafficMap = airTrafficMap;
        this.view = view;
    }

    @Override
    public void execute() {
        FlightSimulator simulator = new FlightSimulator(airTrafficMap, view);
        simulator.simulateTakeOff(flight, -1, -1);
    }
}
