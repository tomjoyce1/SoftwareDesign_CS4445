package models.flightTest.flightTypes;

import models.flight.flighttypes.PassengerFlight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerFlightTest {

    @Test
    void passengerFlightReturnsCorrectType() {
        PassengerFlight flight = new PassengerFlight("PF001");
        assertEquals("Passenger Flight", flight.getType());
    }

    @Test
    void passengerFlightNumberIsAssignedProperly() {
        PassengerFlight flight = new PassengerFlight("PF002");
        assertEquals("PF002", flight.getFlightNumber());
    }

    @Test
    void passengerFlightInitialFuelValueIsHundred() {
        PassengerFlight flight = new PassengerFlight("PF003");
        assertEquals(100, flight.getFuel());
    }

    @Test
    void passengerFlightTakeOffReducesFuelByTen() {
        PassengerFlight flight = new PassengerFlight("PF004");
        flight.setFuel(100);
        flight.takeOff();
        assertEquals(90, flight.getFuel());
    }

    @Test
    void passengerFlightSetFuelUpdatesFuelValue() {
        PassengerFlight flight = new PassengerFlight("PF005");
        flight.setFuel(80);
        assertEquals(80, flight.getFuel());
    }
}