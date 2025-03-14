package views;

import java.util.Scanner;

public class SimulatorView {
    private final Scanner scanner;


    public SimulatorView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        ConsoleLogger.logTitle("\n=== Flight Operations ===");
        ConsoleLogger.logStandard("1. Create new flight");
        ConsoleLogger.logStandard("2. Control flight");
        ConsoleLogger.logStandard("3. Clear Scheduled Flights For Take Off");
    
        ConsoleLogger.logTitle("\n=== Weather Operations ===");
        ConsoleLogger.logStandard("4. Update weather");
    
        ConsoleLogger.logTitle("\n=== Flight Information ===");
        ConsoleLogger.logStandard("5. List all flights");
        ConsoleLogger.logStandard("6. Check Flight Status");
        ConsoleLogger.logStandard("7. View Flight Info");
    
        ConsoleLogger.logTitle("\n=== Map Operations ===");
        ConsoleLogger.logStandard("8. View Air Traffic Map");
        ConsoleLogger.logStandard("9. View Cell Contents");
    
        ConsoleLogger.logTitle("\n=== Exit ===");
        ConsoleLogger.logStandard("Q. Quit");
        ConsoleLogger.logStandard("\nChoose action: ");
    }

    public String getUserInput() {
        return scanner.nextLine().toUpperCase();
    }
}
