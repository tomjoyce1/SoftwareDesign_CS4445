package models.Flight.FlightTypes;

import models.Flight.Flight;

public class PassengerFlight extends Flight {
    @Override
    public String getType() {
        return "Passenger Flight";
    }

    public PassengerFlight(String flightNumber) {
        super(flightNumber);
    }
    
}
