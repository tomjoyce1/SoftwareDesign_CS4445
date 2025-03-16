package factories;

import models.decorators.flightdecorator.CrewInfoDecorator;
import models.decorators.flightdecorator.FlightAgencyDecorator;
import models.decorators.flightdecorator.PassengerDecorator;
import models.flight.FlightInterface;
import models.flight.flighttypes.*;

public class FlightFactory {

    private FlightFactory() {
        throw new IllegalStateException("This constructor should not be accessed!");
    }

    public static FlightInterface createFlight(FlightType type, String flightNumber) {
        return switch (type) {
            case PRIVATE -> new PrivateFlight(flightNumber);
            case PASSENGER -> new PassengerFlight(flightNumber);
            case MILITARY -> new MilitaryFlight(flightNumber);
            case CARGO -> new CargoFlight(flightNumber);
        };
    }

    public static FlightInterface createDecoratedFlight(FlightType type, String flightNumber, int initialPassengers, String flightAgency, String pilotName, int crewCount) {
        FlightInterface flight = createFlight(type, flightNumber);
        if (type != FlightType.MILITARY && type != FlightType.CARGO) {
            flight = new PassengerDecorator(flight, initialPassengers);
        }
        if (type != FlightType.MILITARY) {
            flight = new FlightAgencyDecorator(flight, flightAgency);
        }
        return new CrewInfoDecorator(flight, pilotName, crewCount);
    }
}