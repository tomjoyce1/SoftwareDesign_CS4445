package controllers;

import commands.Command;

import java.util.HashMap;
import java.util.Map;

import models.SimulatorModel;
import views.ConsoleLogger;
import views.SimulatorView;

import commands.gamecommand.CheckFlightStatusCommand;
import commands.gamecommand.ClearScheduledFlightsForTakeOffCommand;
import commands.gamecommand.ControlFlightCommand;
import commands.gamecommand.CreateFlightCommand;
import commands.gamecommand.ListFlightsCommand;
import commands.gamecommand.QuitCommand;
import commands.gamecommand.RemoveCrashedFlightsCommand;
import commands.gamecommand.UpdateWeatherCommand;
import commands.gamecommand.ViewCellContentsCommand;
import commands.gamecommand.ViewFlightInfo;
import commands.gamecommand.ViewMapCommand;

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
        commands.put("1", new CreateFlightCommand(model.getFlights(), view, model.getDispatcher(), model.getAirTrafficMap()));
        commands.put("2", new ControlFlightCommand(model.getFlights(), view, model.getDispatcher(), model.getAirTrafficMap(), model));
        commands.put("3", new ClearScheduledFlightsForTakeOffCommand(model.getAirTrafficMap(), model.getScheduledFlights(), view));
        commands.put("4", new RemoveCrashedFlightsCommand(model));
        commands.put("5", new UpdateWeatherCommand(model.getWeatherStation(), view, model.getDispatcher()));
        commands.put("6", new ListFlightsCommand(model.getFlights()));
        commands.put("7", new CheckFlightStatusCommand(model.getFlights(), view));
        commands.put("8", new ViewFlightInfo(model.getFlights(), view));
        commands.put("9", new ViewMapCommand(model.getAirTrafficMap()));
        commands.put("10", new ViewCellContentsCommand(model.getAirTrafficMap(), view));
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
                ConsoleLogger.logError("Invalid option! Please select from menu.");
            }
        }
    }
}