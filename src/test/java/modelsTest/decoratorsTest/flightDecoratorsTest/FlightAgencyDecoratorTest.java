package modelsTest.decoratorsTest.flightDecoratorsTest;

import models.flight.Flight;
import models.decorators.flightdecorator.FlightAgencyDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightAgencyDecoratorTest {

    private Flight flight;
    private FlightAgencyDecorator flightAgencyDecorator;

    @BeforeEach
    void setUp() {
        flight = Mockito.mock(Flight.class);
        flightAgencyDecorator = new FlightAgencyDecorator(flight, "Some Agency");
    }

    @Test
    void testGetFlightAgency() {
        assertEquals("Some Agency", flightAgencyDecorator.getFlightAgency());
    }

    @Test
    void testTakeOff() {
        flightAgencyDecorator.takeOff();
        Mockito.verify(flight).takeOff();
    }

    @Test
    void testGetType() {
        Mockito.when(flight.getType()).thenReturn("Private");
        assertEquals("Private", flightAgencyDecorator.getType());
    }

    @Test
    void testGetFuel() {
        Mockito.when(flight.getFuel()).thenReturn(100);
        assertEquals(100, flightAgencyDecorator.getFuel());
    }

    @Test
    void testConsumeFuel() {
        flightAgencyDecorator.consumeFuel();
        Mockito.verify(flight).consumeFuel();
    }

    @Test
    void testSetFuel() {
        flightAgencyDecorator.setFuel(50);
        Mockito.verify(flight).setFuel(50);
    }
}