package views;

import java.util.Scanner;

import models.decorators.radardecorator.RadarDisplay;

public class SimulatorView {
    private final Scanner scanner;
    private final RadarDisplay radar;

    public SimulatorView(RadarDisplay radar) {
        this.radar = radar;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        ConsoleLogger.logTitle("\n=== Main Menu for ISE International Airport ===");
        ConsoleLogger.logOption(new String[]
                {"Create new flight", "Control flight",
                        "Update weather", "List all flights",
                        "Check Flight Status"}, true);
    }

    public String getUserInput() {
        return scanner.nextLine().toUpperCase();
    }

    public void displayRadar() {
        ConsoleLogger.logTitle("\n--- Radar Display ---");
        radar.show();
    }
}
