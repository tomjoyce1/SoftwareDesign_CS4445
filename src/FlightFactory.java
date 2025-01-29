public class FlightFactory {
    public static Flight createFlight(FlightType type, String flightNumber) {
        switch (type) {
            case PRIVATE:
                return new PrivateFlight(flightNumber);
            case CARGO:
                return new CargoFlight(flightNumber);
            case MILITARY:
                return new MilitaryFlight(flightNumber);
            default:
                throw new IllegalArgumentException("Unknown flight type");
        }
    }
}
