package models.flightTest.flightTypes;

import models.flight.flighttypes.CargoFlight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CargoFlightTest {

    @Test
    public void cargoFlightReturnsCorrectType() {
        CargoFlight flight = new CargoFlight("CARGO01");
        assertEquals("Cargo Flight", flight.getType());
    }

    @Test
    public void cargoFlightNumberIsAssignedProperly() {
        CargoFlight flight = new CargoFlight("CARGO02");
        assertEquals("CARGO02", flight.getFlightNumber());
    }

    @Test
    public void cargoFlightInitialFuelValueIsHundred() {
        CargoFlight flight = new CargoFlight("CARGO03");
        assertEquals(100, flight.getFuel());
    }

    @Test
    public void cargoFlightTakeOffReducesFuelByTen() {
        CargoFlight flight = new CargoFlight("CARGO04");
        flight.setFuel(100);
        flight.takeOff();
        assertEquals(90, flight.getFuel());
    }

    @Test
    public void cargoFlightSetFuelUpdatesFuelValue() {
        CargoFlight flight = new CargoFlight("CARGO05");
        flight.setFuel(80);
        assertEquals(80, flight.getFuel());
    }
}