package models.decoratorsTest.fuelDecoratorTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import models.decorators.fueldecorator.FuelDecorator;
import models.flight.Flight;

class FuelDecoratorTest {

    @Test
    void consumeFuelReducesFuelByDefaultRate() {
        DummyFlight dummyFlight = new DummyFlight(100);
        FuelDecorator fuelDecorator = new FuelDecorator(dummyFlight);
        fuelDecorator.consumeFuel();
        assertEquals(90, dummyFlight.getFuel());
    }

    @Test
    void consumeFuelReducesFuelByCustomRate() {
        DummyFlight dummyFlight = new DummyFlight(100);
        FuelDecorator fuelDecorator = new FuelDecorator(dummyFlight);
        fuelDecorator.setFuelConsumptionRate(20);
        fuelDecorator.consumeFuel();
        assertEquals(80, dummyFlight.getFuel());
    }

    @Test
    void consumeFuelAllowsNegativeFuelWhenInsufficient() {
        DummyFlight dummyFlight = new DummyFlight(5);
        FuelDecorator fuelDecorator = new FuelDecorator(dummyFlight);
        fuelDecorator.consumeFuel();
        assertEquals(-5, dummyFlight.getFuel());
    }

    // DummyFlight provides a minimal implementation of Flight for controlled tests
    private static class DummyFlight extends Flight {
        private int fuel;

        public DummyFlight(int initialFuel) {
            super("dummy"); // call parent constructor with a string value
            this.fuel = initialFuel;
        }

        @Override
        public int getFuel() {
            return fuel;
        }

        @Override
        public void setFuel(int fuel) {
            this.fuel = fuel;
        }

        @Override
        public String getType() {
            return "dummy";
        }
    }
}