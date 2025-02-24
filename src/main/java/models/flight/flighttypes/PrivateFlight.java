package models.flight.flighttypes;

import models.flight.Flight;

public class PrivateFlight extends Flight {
    public PrivateFlight(String flightNumber) {
        super(flightNumber);
    }

    @Override
    public String getType() {
        return "Private Flight";
    }
}
