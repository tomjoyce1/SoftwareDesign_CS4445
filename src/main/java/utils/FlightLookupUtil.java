package utils;

import models.flight.Flight;
import java.util.List;

public class FlightLookupUtil {


    public static Flight findFlightByNumber(List<Flight> flights, String flightNumber) {
        return flights.stream()
                      .filter(f -> f.getFlightNumber().equals(flightNumber))
                      .findFirst()
                      .orElse(null);
    }
}
