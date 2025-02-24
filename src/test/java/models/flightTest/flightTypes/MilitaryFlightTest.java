package models.flightTest.flightTypes;

import models.flight.flighttypes.MilitaryFlight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MilitaryFlightTest {

    @Test
    public void militaryFlightReturnsCorrectType() {
        MilitaryFlight flight = new MilitaryFlight("MIL001");
        assertEquals("Military Flight", flight.getType());
    }

    @Test
    public void militaryFlightNumberIsAssignedProperly() {
        MilitaryFlight flight = new MilitaryFlight("MIL002");
        assertEquals("MIL002", flight.getFlightNumber());
    }

    @Test
    public void militaryFlightInitialFuelValueIsHundred() {
        MilitaryFlight flight = new MilitaryFlight("MIL003");
        assertEquals(100, flight.getFuel());
    }

    @Test
    public void militaryFlightTakeOffReducesFuelByTen() {
        MilitaryFlight flight = new MilitaryFlight("MIL004");
        flight.setFuel(100);
        flight.takeOff();
        assertEquals(90, flight.getFuel());
    }

    @Test
    public void militaryFlightSetFuelUpdatesFuelValue() {
        MilitaryFlight flight = new MilitaryFlight("MIL005");
        flight.setFuel(80);
        assertEquals(80, flight.getFuel());
    }
}