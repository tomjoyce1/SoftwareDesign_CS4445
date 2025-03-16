package commands.gamecommand;

import commands.Command;
import bookmarks.InterceptorDispatcher;
import models.flight.FlightInterface;
import models.map.AirTrafficMap;
import models.SimulatorModel;
import views.ConsoleLogger;
import views.SimulatorView;
import commands.flightcommand.TakeOffCommand;
import commands.flightcommand.LandCommand;
import commands.flightcommand.HoldCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlFlightCommand implements Command {
    private final List<FlightInterface> flights;
    private final SimulatorView view;
    private final InterceptorDispatcher dispatcher;
    private final AirTrafficMap airTrafficMap;
    private final SimulatorModel model;
    private static final Map<String, Command> commands = new HashMap<>();

    public ControlFlightCommand(List<FlightInterface> flights, SimulatorView view, InterceptorDispatcher dispatcher, AirTrafficMap airTrafficMap, SimulatorModel model) {
        this.flights = flights;
        this.view = view;
        this.dispatcher = dispatcher;
        this.airTrafficMap = airTrafficMap;
        this.model = model;
    }

    @Override
    public void execute() {
        if (flights.isEmpty()) {
            ConsoleLogger.logError("No flights available!");
            return;
        }

        new ListFlightsCommand(flights).execute();
        ConsoleLogger.logStandard("Enter flight number to control: ");
        String flightNumber = view.getUserInput();

        FlightInterface selectedFlight = utils.FlightLookupUtil.findFlightByNumber(flights, flightNumber);

        if (selectedFlight == null) {
            ConsoleLogger.logError("Flight not found!");
            return;
        }

        ConsoleLogger.logTitle("\n=== Flight Controls ===");
        ConsoleLogger.logOption(new String[]{"Take off", "Land", "Hold", "Schedule Take Off"});

        String action = view.getUserInput();
        dispatcher.dispatch("Controlling " + flightNumber + action);

        setUpFlightCommands(selectedFlight);
        Command command = commands.get(action);
        if (command != null) {
            command.execute();
        } else {
            ConsoleLogger.logError("Invalid option!");
        }
    }

    private void setUpFlightCommands(FlightInterface selectedFlight) {
        commands.put("1", new TakeOffCommand(selectedFlight, airTrafficMap, view));
        commands.put("2", new LandCommand(selectedFlight));
        commands.put("3", new HoldCommand(selectedFlight));
        commands.put("4", new ScheduleTakeOffCommand(selectedFlight, airTrafficMap, view, model.getScheduledFlights()));
    }
}
