package models.decorators.flightdecorator;

import models.decorators.FlightDecorator;
import models.flight.FlightInterface;

public class PassengerDecorator extends FlightDecorator {
    private final int passengerCount;

    public PassengerDecorator(FlightInterface flight, int initialPassengerCount) {
        super(flight);
        this.passengerCount = initialPassengerCount;
    }

    public int getPassengerCount() {
        return passengerCount;
    }
}