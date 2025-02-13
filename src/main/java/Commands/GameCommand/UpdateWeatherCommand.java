package Commands.GameCommand;

import Commands.Command;
import Interceptors.InterceptorDispatcher;
import WeatherPubSub.WeatherStation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UpdateWeatherCommand implements Command {
    private final WeatherStation weatherStation;
    private final Scanner scanner;
    private final InterceptorDispatcher dispatcher;
    private static final Map<String, Runnable> weatherActions = new HashMap<>();

    public UpdateWeatherCommand(WeatherStation weatherStation, Scanner scanner, InterceptorDispatcher dispatcher) {
        this.weatherStation = weatherStation;
        this.scanner = scanner;
        this.dispatcher = dispatcher;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Weather Control ===");
        System.out.println("1. Report Storm");
        System.out.println("2. Report Sunny");
        System.out.println("3. Report Foggy");
        System.out.print("Choose weather: ");

        String choice = scanner.nextLine();
        System.out.print("Enter weather details: ");
        String details = scanner.nextLine();
        dispatcher.dispatch(choice + details);
        setupWeatherActions(details);

        Runnable action = weatherActions.get(choice);
        if (action != null) {
            action.run();
        } else {
            System.out.println("Invalid weather type!");
        }
    }

    private void setupWeatherActions(String details) {
        weatherActions.put("1", () -> weatherStation.reportStorm(details));
        weatherActions.put("2", () -> weatherStation.reportSunny(details));
        weatherActions.put("3", () -> weatherStation.reportFoggy(details));
    }
}
