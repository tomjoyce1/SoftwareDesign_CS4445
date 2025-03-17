package models.flight.flighttypes;

import models.flight.Flight;
import models.flight.FlightInterface;
import models.map.AirTrafficMap;
import views.SimulatorView;

public class PrivateFlight extends Flight {
    public PrivateFlight(String flightNumber, FlightInterface flightInterface, AirTrafficMap airTrafficMap, SimulatorView simulatorView) {
        super(flightNumber, flightInterface, airTrafficMap, simulatorView);
    }

    @Override
    public String getType() {
        return "Private Flight";
    }
}