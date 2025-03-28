package factoriesTest;

import factories.FlightFactory;
import models.flight.FlightInterface;
import models.flight.flighttypes.FlightType;
import models.flight.flighttypes.PrivateFlight;
import models.flight.flighttypes.PassengerFlight;
import models.flight.flighttypes.MilitaryFlight;
import models.flight.flighttypes.CargoFlight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightFactoryTest {

    @Test
    void createPrivateFlightSucceeds() {
        FlightInterface flight = FlightFactory.createFlight(FlightType.PRIVATE, "FL001");
        assertNotNull(flight);
        assertInstanceOf(PrivateFlight.class, flight);
    }

    @Test
    void createPassengerFlightSucceeds() {
        FlightInterface flight = FlightFactory.createFlight(FlightType.PASSENGER, "FL002");
        assertNotNull(flight);
        assertInstanceOf(PassengerFlight.class, flight);
    }

    @Test
    void createMilitaryFlightSucceeds() {
        FlightInterface flight = FlightFactory.createFlight(FlightType.MILITARY, "FL003");
        assertNotNull(flight);
        assertInstanceOf(MilitaryFlight.class, flight);
    }

    @Test
    void createCargoFlightSucceeds() {
        FlightInterface flight = FlightFactory.createFlight(FlightType.CARGO, "FL004");
        assertNotNull(flight);
        assertInstanceOf(CargoFlight.class, flight);
    }

}