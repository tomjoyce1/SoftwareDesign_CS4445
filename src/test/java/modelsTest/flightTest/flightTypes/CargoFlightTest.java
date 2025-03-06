package modelsTest.flightTest.flightTypes;

import models.flight.flighttypes.CargoFlight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CargoFlightTest {

    @Test
    void cargoFlightReturnsCorrectType() {
        CargoFlight flight = new CargoFlight("CARGO01");
        assertEquals("Cargo Flight", flight.getType());
    }

    @Test
    void cargoFlightNumberIsAssignedProperly() {
        CargoFlight flight = new CargoFlight("CARGO02");
        assertEquals("CARGO02", flight.getFlightNumber());
    }

    @Test
    void cargoFlightInitialFuelValueIsHundred() {
        CargoFlight flight = new CargoFlight("CARGO03");
        assertEquals(100, flight.getFuel());
    }

    @Test
    void cargoFlightTakeOffReducesFuelByTen() {
        CargoFlight flight = new CargoFlight("CARGO04");
        flight.setFuel(100);
        flight.takeOff();
        assertEquals(90, flight.getFuel());
    }

    @Test
    void cargoFlightSetFuelUpdatesFuelValue() {
        CargoFlight flight = new CargoFlight("CARGO05");
        flight.setFuel(80);
        assertEquals(80, flight.getFuel());
    }
}