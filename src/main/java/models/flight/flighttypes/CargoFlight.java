package models.flight.flighttypes;

import models.flight.Flight;
import models.flight.FlightInterface;
import models.map.AirTrafficMap;
import views.SimulatorView;

public class CargoFlight extends Flight {
    @Override
    public String getType() {
        return "Cargo Flight";
    }

    public CargoFlight(String flightNumber, FlightInterface flightInterface, AirTrafficMap airTrafficMap, SimulatorView view) {
        super(flightNumber, flightInterface, airTrafficMap, view);
    }
}