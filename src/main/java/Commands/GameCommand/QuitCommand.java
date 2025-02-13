package Commands.GameCommand;

import Commands.Command;

public class QuitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Exiting the system...");
    }
}
