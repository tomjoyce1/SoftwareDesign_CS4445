package models.decorators.flightdecorator;

import models.decorators.FlightDecorator;
import models.flight.IFlight;

public class PassengerDecorator extends FlightDecorator {
    private int passengerCount;

    public PassengerDecorator(IFlight flight, int initialPassengerCount) {
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
        // e.g. "Passenger Flight" or add some suffix:
        return super.getType() + " [Passengers]";
    }
}
