package models.flightTest.flightTypes;

import models.flight.flighttypes.PrivateFlight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrivateFlightTest {

    @Test
    void privateFlightReturnsCorrectType() {
        PrivateFlight flight = new PrivateFlight("PVT001");
        assertEquals("Private Flight", flight.getType());
    }

    @Test
    void privateFlightNumberIsAssignedProperly() {
        PrivateFlight flight = new PrivateFlight("PVT002");
        assertEquals("PVT002", flight.getFlightNumber());
    }

    @Test
    void privateFlightInitialFuelValueIsHundred() {
        PrivateFlight flight = new PrivateFlight("PVT003");
        assertEquals(100, flight.getFuel());
    }

    @Test
    void privateFlightTakeOffReducesFuelByTen() {
        PrivateFlight flight = new PrivateFlight("PVT004");
        flight.setFuel(100);
        flight.takeOff();
        assertEquals(90, flight.getFuel());
    }

    @Test
    void privateFlightSetFuelUpdatesFuelValue() {
        PrivateFlight flight = new PrivateFlight("PVT005");
        flight.setFuel(80);
        assertEquals(80, flight.getFuel());
    }

    @Test
    void privateFlightTakeOffWithLowFuelResultsInNegativeFuel() {
        PrivateFlight flight = new PrivateFlight("PVT006");
        flight.setFuel(5);
        flight.takeOff();
        assertEquals(-5, flight.getFuel());
    }
}