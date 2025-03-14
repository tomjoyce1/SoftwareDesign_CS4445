package models.decorators.flightdecorator;

import models.decorators.FlightDecorator;
import models.flight.IFlight;

public class FlightAgencyDecorator extends FlightDecorator {
    private String flightAgency;

    public FlightAgencyDecorator(IFlight flight, String flightAgency) {
        super(flight);
        this.flightAgency = flightAgency;
    }

    public String getFlightAgency() {
        return flightAgency;
    }

    @Override
    public String getType() {
        return super.getType() + " (Agency: " + flightAgency + ")";
    }
}
