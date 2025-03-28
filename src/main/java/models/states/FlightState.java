package models.states;

import models.flight.Flight;

public interface FlightState {
    boolean takeOff(Flight flight);

    void land(Flight flight);

    void hold(Flight flight);

    String getStateName();
}