package models.decorators.flightdecorator;

import models.decorators.FlightDecorator;
import models.flight.Flight;

public class FlightAgencyDecorator extends FlightDecorator {
    private String flightAgency;

    public FlightAgencyDecorator(Flight flight, String flightAgency) {
        super(flight);
        this.flightAgency = flightAgency;
    }

    public String getFlightAgency() {
        return flightAgency;
    }
}
