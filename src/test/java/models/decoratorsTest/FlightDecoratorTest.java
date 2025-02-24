package models.decoratorsTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import models.decorators.FlightDecorator;
import models.flight.Flight;

public class FlightDecoratorTest {

    @Test
    public void decoratorDelegatesTakeOffCall() {
        DummyFlight dummy = new DummyFlight("FL123");
        FlightDecorator decorator = new FlightDecorator(dummy) {};
        decorator.takeOff();
        assertTrue(dummy.takeOffCalled);
    }

    @Test
    public void decoratorDelegatesGetTypeCall() {
        DummyFlight dummy = new DummyFlight("FL123");
        dummy.type = "TestType";
        FlightDecorator decorator = new FlightDecorator(dummy) {};
        assertEquals("TestType", decorator.getType());
    }

    @Test
    public void decoratorDelegatesGetFuelCall() {
        DummyFlight dummy = new DummyFlight("FL123");
        dummy.fuel = 120;
        FlightDecorator decorator = new FlightDecorator(dummy) {};
        assertEquals(120, decorator.getFuel());
    }

    @Test
    public void decoratorDelegatesConsumeFuelCall() {
        DummyFlight dummy = new DummyFlight("FL123");
        dummy.fuel = 100;
        FlightDecorator decorator = new FlightDecorator(dummy) {};
        decorator.consumeFuel();
        assertTrue(dummy.consumeFuelCalled);
        assertEquals(90, dummy.fuel);
    }

    @Test
    public void decoratorMultipleConsumeFuelCallsDecreaseFuelCorrectly() {
        DummyFlight dummy = new DummyFlight("FL123");
        dummy.fuel = 100;
        FlightDecorator decorator = new FlightDecorator(dummy) {};
        decorator.consumeFuel();
        decorator.consumeFuel();
        assertEquals(80, dummy.fuel);
    }

    private static class DummyFlight extends Flight {
        boolean takeOffCalled = false;
        boolean consumeFuelCalled = false;
        int fuel;
        String type = "Default";

        public DummyFlight(String flightNumber) {
            super(flightNumber);
        }

        @Override
        public void takeOff() {
            takeOffCalled = true;
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public int getFuel() {
            return fuel;
        }

        @Override
        public void consumeFuel() {
            fuel -= 10;
            consumeFuelCalled = true;
        }
    }
}