package models.decorators.flightdecorator;

import models.decorators.FlightDecorator;
import models.flight.FlightInterface;

public class PassengerDecorator extends FlightDecorator {
    private int passengerCount;

    public PassengerDecorator(FlightInterface flight, int initialPassengerCount) {
        super(flight);
        this.passengerCount = initialPassengerCount;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void addPassengers(int count) {
        passengerCount += count;
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public void setScheduled(boolean scheduled) {
        decoratedFlight.setScheduled(scheduled);
    }
}