package factories;

import models.decorators.flightdecorator.CrewInfoDecorator;
import models.decorators.flightdecorator.FlightAgencyDecorator;
import models.decorators.flightdecorator.PassengerDecorator;
import models.flight.Flight;
import models.flight.flighttypes.CargoFlight;
import models.flight.flighttypes.MilitaryFlight;
import models.flight.flighttypes.PassengerFlight;
import models.flight.flighttypes.PrivateFlight;
import models.flight.flighttypes.FlightType;

public class FlightFactory {

    private FlightFactory() {
        throw new IllegalStateException("This constructor should not be accessed!");
    }

    public static Flight createFlight(FlightType type, String flightNumber) {
        return switch (type) {
            case PRIVATE -> new PrivateFlight(flightNumber);
            case PASSENGER -> new PassengerFlight(flightNumber);
            case MILITARY -> new MilitaryFlight(flightNumber);
            case CARGO -> new CargoFlight(flightNumber);
        };
    }

    // new method that applies decorators based on user inputs
    public static Flight createDecoratedFlight(FlightType type, String flightNumber, int initialPassengers, String flightAgency, String pilotName, int crewCount) {
        Flight flight = createFlight(type, flightNumber);

        if (type != FlightType.MILITARY && type != FlightType.CARGO) {
            flight = new PassengerDecorator(flight, initialPassengers);
        }
        
        if (type != FlightType.MILITARY) {
            flight = new FlightAgencyDecorator(flight, flightAgency);
        }
        
        flight = new CrewInfoDecorator(flight, pilotName, crewCount);
        
        return flight;
    }
}
