package Flight.FlightTypes;

import Flight.Flight;

public class CargoFlight extends Flight {
    @Override
    public String getType() {
        return "Flight.FlightTypes.CargoFlight";
    }

    public CargoFlight(String flightNumber) {
        super(flightNumber);
    }
}
