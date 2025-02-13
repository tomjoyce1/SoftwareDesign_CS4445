package Flight.FlightTypes;

import Flight.Flight;

public class PassengerFlight extends Flight {
    @Override
    public String getType() {
        return "Flight.FlightTypes.PassengerFlight";
    }

    public PassengerFlight(String flightNumber) {
        super(flightNumber);
    }
    
}
