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
        ConsoleLogger.logStandard("4. Remove Crashed Flights");

    
        ConsoleLogger.logTitle("\n=== Weather Operations ===");
        ConsoleLogger.logStandard("5. Update weather");
    
        ConsoleLogger.logTitle("\n=== Flight Information ===");
        ConsoleLogger.logStandard("6. List all flights");
        ConsoleLogger.logStandard("7. Check Flight Status");
        ConsoleLogger.logStandard("8. View Flight Info");
    
        ConsoleLogger.logTitle("\n=== Map Operations ===");
        ConsoleLogger.logStandard("9. View Air Traffic Map");
        ConsoleLogger.logStandard("10. View Cell Contents");
    
        ConsoleLogger.logTitle("\n=== Exit ===");
        ConsoleLogger.logStandard("Q. Quit");
        ConsoleLogger.logStandard("\nChoose action: ");
    }

    public String getUserInput() {
        return scanner.nextLine().toUpperCase();
    }
}