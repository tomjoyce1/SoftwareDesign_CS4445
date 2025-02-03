package main.java;
public class FlightFactory {
    public static Flight createFlight(FlightType type, String flightNumber) {
        switch (type) {
            case PRIVATE:
                return new PrivateFlight(flightNumber);
            case PASSENGER:
                return new PassengerFlight(flightNumber);
            case MILITARY:
                return new MilitaryFlight(flightNumber);
            case CARGO:
                return new CargoFlight(flightNumber);
            default:
                return null;
        }
    }
}
