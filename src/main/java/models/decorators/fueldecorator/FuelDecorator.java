package models.decorators.fueldecorator;

import models.decorators.FlightDecorator;
import models.flight.Flight;

public class FuelDecorator extends FlightDecorator {
    private int fuelConsumptionRate = 10;

    public FuelDecorator(Flight flight) {
        super(flight);
    }

    @Override
    public void consumeFuel() {
        int currentFuel = decoratedFlight.getFuel();
        decoratedFlight.setFuel(currentFuel - fuelConsumptionRate);
    }

    public void setFuelConsumptionRate(int rate) {
        this.fuelConsumptionRate = rate;
    }
}
