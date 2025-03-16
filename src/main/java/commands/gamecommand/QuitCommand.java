package commands.gamecommand;

import commands.Command;
import views.ConsoleLogger;

public class QuitCommand implements Command {
    @Override
    public void execute() {
        ConsoleLogger.logSuccess("Exiting the system...");
    }
}