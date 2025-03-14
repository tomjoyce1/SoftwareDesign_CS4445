package models.map;

import models.flight.Flight;
import utils.AirportSelectionUtil;
import views.ConsoleLogger;
import views.SimulatorView;

public class FlightAirportAssigner {

    public static void assignFlightToAirport(Flight flight, AirTrafficMap airTrafficMap, SimulatorView view) {
        // Use the utility to select an airport (no exclusion needed, so pass -1 for both)
        int[] selectedCoordinates = AirportSelectionUtil.selectAirport(airTrafficMap, view, -1, -1);
        if (selectedCoordinates == null) {
            return;
        }
        
        // Retrieve the airport label from the selected cell
        String airportLabel = airTrafficMap.getCell(selectedCoordinates[0], selectedCoordinates[1]).getAirportLabel();
        airTrafficMap.placeFlight(selectedCoordinates[0], selectedCoordinates[1], flight);
        ConsoleLogger.logSuccess("Flight " + flight.getFlightNumber() 
            + " stored at airport " + airportLabel);
    }
}