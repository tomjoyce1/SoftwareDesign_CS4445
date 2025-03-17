package models.flight.flighttypes;

import models.flight.Flight;
import models.flight.FlightInterface;
import models.map.AirTrafficMap;
import views.SimulatorView;

public class MilitaryFlight extends Flight {
    public MilitaryFlight(String flightNumber, FlightInterface flightInterface, AirTrafficMap airTrafficMap, SimulatorView view) {
        super(flightNumber, flightInterface, airTrafficMap, view);
    }

    @Override
    public String getType() {
        return "Military Flight";
    }
}