package modelsTest.decoratorsTest.flightDecoratorsTest;

import models.flight.Flight;
import models.decorators.flightdecorator.PassengerDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PassengerDecoratorTest {

    private Flight flight;
    private PassengerDecorator passengerDecorator;

    @BeforeEach
    void setUp() {
        flight = Mockito.mock(Flight.class);
        passengerDecorator = new PassengerDecorator(flight, 100);
    }

    @Test
    void testGetPassengerCount() {
        assertEquals(100, passengerDecorator.getPassengerCount());
    }

    @Test
    void testAddPassengers() {
        passengerDecorator.addPassengers(50);
        assertEquals(150, passengerDecorator.getPassengerCount());
    }

    @Test
    void testTakeOff() {
        passengerDecorator.takeOff();
        Mockito.verify(flight).takeOff();
    }

    @Test
    void testGetType() {
        Mockito.when(flight.getType()).thenReturn("Private");
        assertEquals("Private", passengerDecorator.getType());
    }

    @Test
    void testGetFuel() {
        Mockito.when(flight.getFuel()).thenReturn(100);
        assertEquals(100, passengerDecorator.getFuel());
    }

    @Test
    void testConsumeFuel() {
        passengerDecorator.consumeFuel();
        Mockito.verify(flight).consumeFuel();
    }

    @Test
    void testSetFuel() {
        passengerDecorator.setFuel(50);
        Mockito.verify(flight).setFuel(50);
    }
}