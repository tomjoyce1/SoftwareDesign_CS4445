package models.flight.flighttypes;

import models.flight.Flight;

public class CargoFlight extends Flight {
    @Override
    public String getType() {
        return "Cargo Flight";
    }

    public CargoFlight(String flightNumber) {
        super(flightNumber);
    }
}
