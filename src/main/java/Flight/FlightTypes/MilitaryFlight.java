package Flight.FlightTypes;

import Flight.Flight;

public class MilitaryFlight extends Flight {
    @Override
    public String getType() {
        return "Flight.FlightTypes.MilitaryFlight";
    }

    @Override
    protected boolean shouldSubscribeToWeather() {
        return false;
    }

    public MilitaryFlight(String flightNumber) {
        super(flightNumber);
    }
}
