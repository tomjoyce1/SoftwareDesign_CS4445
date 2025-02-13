import Commands.Command;
import Commands.GameCommand.*;
import Flight.Flight;
import Interceptors.*;
import WeatherPubSub.WeatherStation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Simulator {
    private static WeatherStation weatherStation;
    private static List<Flight> flights;
    private static Scanner scanner;
    private static final InterceptorDispatcher dispatcher = new InterceptorDispatcher();
    private static final Map<String, Command> commands = new HashMap<>();
    private static final RadarDisplay radar = new BasicRadarDisplay();

    public static void startSimulation() {
        initialise();
        runGameLoop();  
    } 

    private static void initialise() {
        dispatcher.addInterceptor(new LoggingInterceptor());
        weatherStation = new WeatherStation();
        flights = new ArrayList<>();
        scanner = new Scanner(System.in);
        System.out.println("=== Airport Control System ===");
        setUpCommands();
    }

    private static void runGameLoop() {
        boolean running = true;
        while (running) {
            displayMenu();
            String choice = scanner.nextLine().toUpperCase();
            dispatcher.dispatch(choice);

            Command command = commands.get(choice);
            if (command != null) {
                command.execute();
                if (command instanceof QuitCommand) {
                    running = false;
                }
            } else {
                System.out.println("Invalid option!");
            }
        }
        scanner.close();
    }

    private static void setUpCommands() {
        commands.put("1", new CreateFlightCommand(flights, scanner, dispatcher));
        commands.put("2", new ControlFlightCommand(flights, scanner, dispatcher));
        commands.put("3", new UpdateWeatherCommand(weatherStation, scanner, dispatcher));
        commands.put("4", new ListFlightsCommand(flights));
        commands.put("5", new CheckFlightStatusCommand(flights, scanner, radar));
        commands.put("Q", new QuitCommand());
    }

    private static void displayMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Create new flight");
        System.out.println("2. Control flight");
        System.out.println("3. Update weather");
        System.out.println("4. List all flights");
        System.out.println("5. Check Flight Status");
        System.out.println("Q. Quit");
        System.out.print("Choose an option: ");
    }
}
