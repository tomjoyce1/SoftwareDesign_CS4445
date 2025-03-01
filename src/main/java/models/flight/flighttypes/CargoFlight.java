package models.flight.flighttypes;

import models.flight.Flight;

public class CargoFlight extends Flight {
    @Override
    public String getType() {
        return "Cargo Flight";
    }

    @Override
    public String getAltitude() {
        return "20,000ft";
    }

    public CargoFlight(String flightNumber) {
        super(flightNumber);
    }
}
