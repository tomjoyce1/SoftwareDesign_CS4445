package models.flight;

import models.map.MapCell;
import models.states.FlightState;
import weatherpubsub.Subscriber;

public interface FlightInterface extends Subscriber {
    String getFlightNumber();
    String getType();

    boolean takeOff();
    void land();
    void hold();

    int getFuel();
    void setFuel(int fuel);
    void consumeFuel();

    String getState();
    void setState(FlightState state);

    boolean isStormNotified();

    boolean isScheduled();
    void setScheduled(boolean scheduled);

    MapCell getCurrentAirportCell();
    void setCurrentAirportCell(MapCell cell);
}
