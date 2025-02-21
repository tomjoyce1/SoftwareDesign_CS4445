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
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Create new flight");
        System.out.println("2. Control flight");
        System.out.println("3. Update weather");
        System.out.println("4. List all flights");
        System.out.println("5. Check Flight Status");
        System.out.println("Q. Quit");
        System.out.print("Choose an option: ");
    }

    public String getUserInput() {
        return scanner.nextLine().toUpperCase();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayRadar() {
        System.out.println("\n--- Radar Display ---");
        radar.show();
    }
}
