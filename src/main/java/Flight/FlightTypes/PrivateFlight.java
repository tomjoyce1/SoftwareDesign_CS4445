package Flight.FlightTypes;

import Flight.Flight;

public class PrivateFlight extends Flight {
    public PrivateFlight(String flightNumber) {
        super(flightNumber);
    }

    @Override
    public String getType() {
        return "Private Flight";
    }
}
