package models.decorators.flightdecorator;

import models.decorators.FlightDecorator;
import models.flight.Flight;

public class PassengerDecorator extends FlightDecorator {
    private int passengerCount;

    public PassengerDecorator(Flight flight, int initialPassengerCount) {
        super(flight);
        this.passengerCount = initialPassengerCount;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void addPassengers(int count) {
        passengerCount += count;
    }
}