package models.flight.flighttypes;

import models.flight.Flight;
import models.flight.FlightInterface;
import models.map.AirTrafficMap;
import views.SimulatorView;

public class PassengerFlight extends Flight {

    @Override
    public String getType() {
        return "Passenger Flight";
    }

    public PassengerFlight(String flightNumber, FlightInterface flightInterface, AirTrafficMap airTrafficMap, SimulatorView simulatorView) {
        super(flightNumber, flightInterface, airTrafficMap, simulatorView);
    }
}