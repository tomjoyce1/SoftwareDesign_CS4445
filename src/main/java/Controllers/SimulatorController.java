package Controllers;

import Commands.Command;
import Commands.GameCommand.*;
import java.util.HashMap;
import java.util.Map;
import Models.SimulatorModel;
import Views.SimulatorView;

public class SimulatorController {
    private final SimulatorModel model;
    private final SimulatorView view;
    private final Map<String, Command> commands;

    public SimulatorController(SimulatorModel model, SimulatorView view) {
        this.model = model;
        this.view = view;
        this.commands = new HashMap<>();
        setUpCommands();
    }

    private void setUpCommands() {
        commands.put("1", new CreateFlightCommand(model.getFlights(), view, model.getDispatcher()));
        commands.put("2", new ControlFlightCommand(model.getFlights(), view, model.getDispatcher()));
        commands.put("3", new UpdateWeatherCommand(model.getWeatherStation(), view, model.getDispatcher()));
        commands.put("4", new ListFlightsCommand(model.getFlights(), view));
        commands.put("5", new CheckFlightStatusCommand(model.getFlights(), view));
        commands.put("Q", new QuitCommand());
    }

    public void startSimulation() {
        boolean running = true;
        while (running) {
            view.displayMenu();
            String choice = view.getUserInput();
            model.getDispatcher().dispatch(choice);
            
            Command command = commands.get(choice);
            if (command != null) {
                command.execute();
                if (command instanceof QuitCommand) {
                    running = false;
                }
            } else {
                view.displayMessage("Invalid option! Please select from menu.");
            }
        }
    }
}