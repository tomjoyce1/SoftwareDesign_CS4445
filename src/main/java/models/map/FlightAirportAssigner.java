package models.map;

import models.flight.FlightInterface;
import utils.AirportSelectionUtil;
import views.ConsoleLogger;
import views.SimulatorView;

public class FlightAirportAssigner {

    public static void assignFlightToAirport(FlightInterface flight, AirTrafficMap airTrafficMap, SimulatorView view) {
        int[] selectedCoordinates = AirportSelectionUtil.selectAirport(airTrafficMap, view, -1, -1);
        if (selectedCoordinates == null) {
            return;
        }

        MapCell cell = airTrafficMap.getCell(selectedCoordinates[0], selectedCoordinates[1]);
        String airportLabel = cell.getAirportLabel();
        airTrafficMap.placeFlight(selectedCoordinates[0], selectedCoordinates[1], flight);
        ConsoleLogger.logSuccess("Flight " + flight.getFlightNumber() + " stored at airport " + airportLabel);
    }

    private FlightAirportAssigner() {
        throw new IllegalStateException("Utility class");
    }
}