package utils;

import models.flight.IFlight;
import java.util.List;

public class FlightLookupUtil {


    public static IFlight findFlightByNumber(List<IFlight> flights, String flightNumber) {
        return flights.stream()
                      .filter(f -> f.getFlightNumber().equals(flightNumber))
                      .findFirst()
                      .orElse(null);
    }
}
