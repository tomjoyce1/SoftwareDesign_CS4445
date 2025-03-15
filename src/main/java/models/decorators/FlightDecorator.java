package models.decorators;

import models.flight.IFlight;
import models.map.MapCell;
import models.states.FlightState;

public abstract class FlightDecorator implements IFlight {
    protected final IFlight decoratedFlight;

    protected FlightDecorator(IFlight flight) {
        this.decoratedFlight = flight;
    }

    @Override
    public String getFlightNumber() {
        return decoratedFlight.getFlightNumber();
    }

    @Override
    public String getType() {
        return decoratedFlight.getType();
    }

    @Override
    public boolean takeOff() {
        return decoratedFlight.takeOff();
    }

    @Override
    public void land() {
        decoratedFlight.land();
    }

    @Override
    public void hold() {
        decoratedFlight.hold();
    }

    @Override
    public int getFuel() {
        return decoratedFlight.getFuel();
    }

    @Override
    public void setFuel(int fuel) {
        decoratedFlight.setFuel(fuel);
    }

    @Override
    public void consumeFuel() {
        decoratedFlight.consumeFuel();
    }

    @Override
    public String getState() {
        return decoratedFlight.getState();
    }

    @Override
    public void setState(FlightState state) {
        decoratedFlight.setState(state);
    }

    @Override
    public boolean isStormNotified() {
        return decoratedFlight.isStormNotified();
    }

    @Override
    public boolean isScheduled() {
        return decoratedFlight.isScheduled();
    }

    @Override
    public void setScheduled(boolean scheduled) {
        decoratedFlight.setScheduled(scheduled);
    }

    @Override
    public MapCell getCurrentAirportCell() {
        return decoratedFlight.getCurrentAirportCell();
    }

    @Override
    public void setCurrentAirportCell(MapCell cell) {
        decoratedFlight.setCurrentAirportCell(cell);
    }

    @Override
    public void receive(String topic, String message) {
        decoratedFlight.receive(topic, message);
    }
}
