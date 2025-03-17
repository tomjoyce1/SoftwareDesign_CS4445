package commands.gamecommand;

import commands.Command;
import models.SimulatorModel;
import views.ConsoleLogger;

public class RemoveCrashedFlightsCommand implements Command {
    private final SimulatorModel model;

    public RemoveCrashedFlightsCommand(SimulatorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.removeCrashedFlights();
        ConsoleLogger.logSuccess("All crashed flights have been removed from the system.");
    }
}