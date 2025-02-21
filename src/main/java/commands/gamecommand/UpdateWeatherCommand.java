package commands.gamecommand;

import commands.Command;
import bookmarks.InterceptorDispatcher;
import views.SimulatorView;
import weatherpubsub.WeatherStation;
import java.util.HashMap;
import java.util.Map;

public class UpdateWeatherCommand implements Command {
    private final WeatherStation weatherStation;
    private final SimulatorView view;
    private final InterceptorDispatcher dispatcher;
    private static final Map<String, Runnable> weatherActions = new HashMap<>();

    public UpdateWeatherCommand(WeatherStation weatherStation, SimulatorView view, InterceptorDispatcher dispatcher) {
        this.weatherStation = weatherStation;
        this.view = view;
        this.dispatcher = dispatcher;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Weather Control ===");
        System.out.println("1. Report Storm");
        System.out.println("2. Report Sunny");
        System.out.println("3. Report Foggy");
        System.out.print("Choose weather: ");

        String choice = view.getUserInput();
        System.out.print("Enter weather details: ");
        String details = view.getUserInput();
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
