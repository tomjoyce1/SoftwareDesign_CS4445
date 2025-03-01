package models.flight.flighttypes;

import models.flight.Flight;

public class PrivateFlight extends Flight {
    public PrivateFlight(String flightNumber) {
        super(flightNumber);
    }

    @Override
    public String getAltitude() {
        return "40,000ft";
    }

    @Override
    public String getType() {
        return "Private Flight";
    }
}
