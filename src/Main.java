import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static WeatherStation weatherStation;
    private static List<Flight> flights;
    private static Scanner scanner;

    public static void main(String[] args) {
        initialize();
        runGameLoop();
    }

    private static void initialize() {
        weatherStation = new WeatherStation();
        flights = new ArrayList<>();
        scanner = new Scanner(System.in);
        System.out.println("=== Airport Control System ===");
    }

    private static void runGameLoop() {
        boolean running = true;
        while (running) {
            displayMenu();
            String choice = scanner.nextLine().toUpperCase();
            
            switch (choice) {
                case "1":
                    createNewFlight();
                    break;
                case "2":
                    controlFlight();
                    break;
                case "3":
                    updateWeather();
                    break;
                case "4":
                    listFlights();
                    break;
                case "Q":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Create new flight");
        System.out.println("2. Control flight");
        System.out.println("3. Update weather");
        System.out.println("4. List all flights");
        System.out.println("Q. Quit");
        System.out.print("Choose an option: ");
    }

    private static void createNewFlight() {
        System.out.println("\n=== Create New Flight ===");
        System.out.println("Flight types: PRIVATE, PASSENGER, MILITARY, CARGO");
        System.out.print("Enter flight type: ");
        String typeStr = scanner.nextLine().toUpperCase();
        
        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine().toUpperCase();
        
        try {
            FlightType type = FlightType.valueOf(typeStr);
            Flight flight = FlightFactory.createFlight(type, flightNumber);
            flights.add(flight);
            System.out.println("Created " + flight.getType() + " " + flightNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid flight type!");
        }
    }

    private static void controlFlight() {
        if (flights.isEmpty()) {
            System.out.println("No flights available!");
            return;
        }

        listFlights();
        System.out.print("Enter flight number to control: ");
        String flightNumber = scanner.nextLine().toUpperCase();
        
        Flight selectedFlight = flights.stream()
                .filter(f -> f.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElse(null);
                
        if (selectedFlight == null) {
            System.out.println("Flight not found!");
            return;
        }

        System.out.println("\n=== Flight Controls ===");
        System.out.println("1. Take off");
        System.out.println("2. Land");
        System.out.println("3. Hold");
        System.out.print("Choose action: ");
        
        String action = scanner.nextLine();
        FlightCommand command = null;
        
        switch (action) {
            case "1":
                command = new TakeOffCommand(selectedFlight);
                break;
            case "2":
                command = new LandCommand(selectedFlight);
                break;
            case "3":
                command = new HoldCommand(selectedFlight);
                break;
            default:
                System.out.println("Invalid action!");
                return;
        }
        
        command.execute();
    }

    private static void updateWeather() {
        System.out.println("\n=== Weather Control ===");
        System.out.println("1. Report Storm");
        System.out.println("2. Report Sunny");
        System.out.println("3. Report Foggy");
        System.out.print("Choose weather: ");
        
        String choice = scanner.nextLine();
        System.out.print("Enter weather details: ");
        String details = scanner.nextLine();
        
        switch (choice) {
            case "1":
                weatherStation.reportStorm(details);
                break;
            case "2":
                weatherStation.reportSunny(details);
                break;
            case "3":
                weatherStation.reportFoggy(details);
                break;
            default:
                System.out.println("Invalid weather type!");
        }
    }

    private static void listFlights() {
        System.out.println("\n=== Current Flights ===");
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }
        
        for (Flight flight : flights) {
            System.out.printf("%s %s - Status: %s%n", 
                flight.getType(), 
                flight.getFlightNumber(), 
                flight.getState());
        }
    }
}
