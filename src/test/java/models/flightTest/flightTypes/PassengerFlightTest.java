package models.flightTest.flightTypes;

import models.flight.flighttypes.PassengerFlight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PassengerFlightTest {

    @Test
    public void passengerFlightReturnsCorrectType() {
        PassengerFlight flight = new PassengerFlight("PF001");
        assertEquals("Passenger Flight", flight.getType());
    }

    @Test
    public void passengerFlightNumberIsAssignedProperly() {
        PassengerFlight flight = new PassengerFlight("PF002");
        assertEquals("PF002", flight.getFlightNumber());
    }

    @Test
    public void passengerFlightInitialFuelValueIsHundred() {
        PassengerFlight flight = new PassengerFlight("PF003");
        assertEquals(100, flight.getFuel());
    }

    @Test
    public void passengerFlightTakeOffReducesFuelByTen() {
        PassengerFlight flight = new PassengerFlight("PF004");
        flight.setFuel(100);
        flight.takeOff();
        assertEquals(90, flight.getFuel());
    }

    @Test
    public void passengerFlightSetFuelUpdatesFuelValue() {
        PassengerFlight flight = new PassengerFlight("PF005");
        flight.setFuel(80);
        assertEquals(80, flight.getFuel());
    }
}