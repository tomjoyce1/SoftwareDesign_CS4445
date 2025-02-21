package factories;

import models.flight.Flight;
import models.flight.flighttypes.CargoFlight;
import models.flight.flighttypes.MilitaryFlight;
import models.flight.flighttypes.PassengerFlight;
import models.flight.flighttypes.PrivateFlight;
import models.flight.flighttypes.FlightType;

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
