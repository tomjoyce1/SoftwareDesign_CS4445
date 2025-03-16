package models.flight.flighttypes;

import models.flight.Flight;

public class MilitaryFlight extends Flight {
    @Override
    public String getType() {
        return "Military Flight";
    }

    public MilitaryFlight(String flightNumber) {
        super(flightNumber);
    }
}
