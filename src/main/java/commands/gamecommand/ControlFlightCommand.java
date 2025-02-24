package commands.gamecommand;

import commands.Command;
import bookmarks.InterceptorDispatcher;
import models.flight.Flight;
import views.SimulatorView;
import commands.flightcommand.TakeOffCommand;
import commands.flightcommand.LandCommand;
import commands.flightcommand.HoldCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlFlightCommand implements Command {
    private final List<Flight> flights;
    private final SimulatorView view;
    private final InterceptorDispatcher dispatcher;
    private static final Map<String, Command> commands = new HashMap<>();

    public ControlFlightCommand(List<Flight> flights, SimulatorView view, InterceptorDispatcher dispatcher) {
        this.flights = flights;
        this.view = view;
        this.dispatcher = dispatcher;
    }

    @Override
    public void execute() {
        if (flights.isEmpty()) {
            System.out.println("No flights available!");
            return;
        }

        new ListFlightsCommand(flights, view).execute();
        System.out.print("Enter flight number to control: ");
        String flightNumber = view.getUserInput();

        Flight selectedFlight = flights.stream()
                .filter(f -> f.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElse(null);

        if (selectedFlight == null) {
            System.out.println("Flight.Flight not found!");
            return;
        }

        System.out.println("\n=== Flight.Flight Controls ===");
        System.out.println("1. Take off");
        System.out.println("2. Land");
        System.out.println("3. Hold");
        System.out.print("Choose action: ");

        String action = view.getUserInput();
        dispatcher.dispatch("Controlling " + flightNumber + action);

        setUpFlightCommands(selectedFlight);
        Command command = commands.get(action);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Invalid option!");
        }
    }

    private static void setUpFlightCommands(Flight selectedFlight) {
        commands.put("1", new TakeOffCommand(selectedFlight));
        commands.put("2", new LandCommand(selectedFlight));
        commands.put("3", new HoldCommand(selectedFlight));
    }
}
