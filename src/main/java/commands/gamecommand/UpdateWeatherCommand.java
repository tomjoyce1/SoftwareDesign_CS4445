package commands.gamecommand;

import commands.Command;
import bookmarks.InterceptorDispatcher;
import views.ConsoleLogger;
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
        ConsoleLogger.logTitle("\n=== Weather Control ===");
        ConsoleLogger.logStandard("Weather types: \n");
        ConsoleLogger.logOption(new String[]{"Stormy", "Sunny", "Foggy"});

        String choice = view.getUserInput();
        ConsoleLogger.logStandard("Enter weather details: ");
        String details = view.getUserInput();
        dispatcher.dispatch(choice + details);
        setupWeatherActions(details);

        Runnable action = weatherActions.get(choice);
        if (action != null) {
            action.run();
        } else {
            ConsoleLogger.logError("Invalid weather type!");
        }
    }

    private void setupWeatherActions(String details) {
        weatherActions.put("1", () -> weatherStation.reportStorm(details));
        weatherActions.put("2", () -> weatherStation.reportSunny(details));
        weatherActions.put("3", () -> weatherStation.reportFoggy(details));
    }
}
