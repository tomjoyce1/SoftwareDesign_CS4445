package Flight;

import Flight.FlightTypes.*;

public class FlightFactory {
    public static Flight createFlight(FlightType type, String flightNumber) {
        return switch (type) {
            case PRIVATE -> new PrivateFlight(flightNumber);
            case PASSENGER -> new PassengerFlight(flightNumber);
            case MILITARY -> new MilitaryFlight(flightNumber);
            case CARGO -> new CargoFlight(flightNumber);
        };
    }
}
