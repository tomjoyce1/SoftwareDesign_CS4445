package views;

import java.util.Scanner;

public class SimulatorView {
    private final Scanner scanner;


    public SimulatorView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        ConsoleLogger.logTitle("\n=== Main Menu for ISE International Airport ===");
        ConsoleLogger.logOption(new String[]
                {"Create new flight", "Control flight",
                        "Update weather", "List all flights",
                        "Check Flight Status", "View Flight Info"}, true);
    }

    public String getUserInput() {
        return scanner.nextLine().toUpperCase();
    }
}
