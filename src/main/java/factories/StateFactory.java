package factories;

import models.states.FlightState;
import models.states.InAirState;
import models.states.LandingModeState;
import models.states.OnRunwayState;

public class StateFactory {
    public static FlightState getState(String stateName) {
        return switch (stateName) {
            case "In the air" -> new InAirState();
            case "Landing mode" -> new LandingModeState();
            case "On ground/Runway" -> new OnRunwayState();
            default -> throw new IllegalArgumentException("Unknown state: " + stateName);
        };
    }

    private StateFactory() {
        throw new IllegalStateException("Utility class");
    }
}