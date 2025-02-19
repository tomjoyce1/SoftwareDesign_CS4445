package Models.Flight.FlightTypes;

import Models.Flight.Flight;

public class PrivateFlight extends Flight {
    public PrivateFlight(String flightNumber) {
        super(flightNumber);
    }

    @Override
    public String getType() {
        return "Private Flight";
    }
}
