package models.flight.flighttypes;

import models.flight.Flight;

public class MilitaryFlight extends Flight {
    @Override
    public String getType() {
        return "Military Flight";
    }


    @Override
    public String getAltitude() {
        return "50,000ft";
    }

    @Override
    protected boolean shouldSubscribeToWeather() {
        return false;
    }

    public MilitaryFlight(String flightNumber) {
        super(flightNumber);
    }
}
