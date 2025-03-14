package models.map;

import models.flight.IFlight;
import utils.AirportSelectionUtil;
import views.ConsoleLogger;
import views.SimulatorView;

public class FlightAirportAssigner {

    public static void assignFlightToAirport(IFlight flight, AirTrafficMap airTrafficMap, SimulatorView view) {
        int[] selectedCoordinates = AirportSelectionUtil.selectAirport(airTrafficMap, view, -1, -1);
        if (selectedCoordinates == null) {
            return;
        }
        
        String airportLabel = airTrafficMap.getCell(selectedCoordinates[0], selectedCoordinates[1]).getAirportLabel();
        airTrafficMap.placeFlight(selectedCoordinates[0], selectedCoordinates[1], flight);
        ConsoleLogger.logSuccess("Flight " + flight.getFlightNumber() + " stored at airport " + airportLabel);
    }
}