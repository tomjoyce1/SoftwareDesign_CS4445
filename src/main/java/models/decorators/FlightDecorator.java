package models.decorators;

import models.flight.Flight;

public abstract class FlightDecorator extends Flight {
    protected Flight decoratedFlight;

    protected FlightDecorator(Flight flight) {
        super(flight.getFlightNumber());
        this.decoratedFlight = flight;
    }

    @Override
    public void takeOff() {
        decoratedFlight.takeOff();
    }

    @Override
    public String getType() {
        return decoratedFlight.getType();
    }

    @Override
    public int getFuel() {
        return decoratedFlight.getFuel();
    }

    @Override
    public void consumeFuel() {
        decoratedFlight.consumeFuel();
    }
    
    @Override
    public void setFuel(int fuel) {
        decoratedFlight.setFuel(fuel);
    }
} 

