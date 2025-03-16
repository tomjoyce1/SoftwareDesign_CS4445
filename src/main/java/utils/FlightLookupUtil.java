package utils;

import models.flight.FlightInterface;

import java.util.List;

public class FlightLookupUtil {

    private FlightLookupUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static FlightInterface findFlightByNumber(List<FlightInterface> flights, String flightNumber) {
        return flights.stream()
                .filter(f -> f.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElse(null);
    }
}
