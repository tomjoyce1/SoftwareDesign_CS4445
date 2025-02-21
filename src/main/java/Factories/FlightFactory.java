package Factories;

import Models.Flight.Flight;
import Models.Flight.FlightTypes.CargoFlight;
import Models.Flight.FlightTypes.MilitaryFlight;
import Models.Flight.FlightTypes.PassengerFlight;
import Models.Flight.FlightTypes.PrivateFlight;
import Models.Flight.FlightTypes.FlightType;

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
