package models.decorators.flightdecorator;

import models.decorators.FlightDecorator;
import models.flight.FlightInterface;

public class FlightAgencyDecorator extends FlightDecorator {
    private final String flightAgency;

    public FlightAgencyDecorator(FlightInterface flight, String flightAgency) {
        super(flight);
        this.flightAgency = flightAgency;
    }

    public String getFlightAgency() {
        return flightAgency;
    }
}