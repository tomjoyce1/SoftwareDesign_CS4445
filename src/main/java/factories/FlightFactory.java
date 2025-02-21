package factories;

import models.Flight.Flight;
import models.Flight.FlightTypes.CargoFlight;
import models.Flight.FlightTypes.MilitaryFlight;
import models.Flight.FlightTypes.PassengerFlight;
import models.Flight.FlightTypes.PrivateFlight;
import models.Flight.FlightTypes.FlightType;

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
