package models.flight.flighttypes;

import models.flight.Flight;

public class PassengerFlight extends Flight {
    @Override
    public String getType() {
        return "Passenger Flight";
    }

    @Override
    public String getAltitude() {
        return "30,000ft";
    }

    public PassengerFlight(String flightNumber) {
        super(flightNumber);
    }
    
}
