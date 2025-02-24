package commands.gamecommand;

import commands.Command;

public class QuitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Exiting the system...");
    }
}
