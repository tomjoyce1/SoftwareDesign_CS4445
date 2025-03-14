package models.map;

import models.flight.IFlight;
import java.util.ArrayList;
import java.util.List;

public class MapCell {
    private boolean isAirport;
    private String airportLabel;
    private final List<IFlight> flights;
    private boolean locked = false;
    private IFlight lockedBy = null;

    public MapCell(boolean isAirport) {
        this.isAirport = isAirport;
        this.flights = new ArrayList<>();
        this.airportLabel = "";
    }

    public boolean isAirport() {
        return isAirport;
    }

    public void setAirport(boolean airport) {
        this.isAirport = airport;
    }

    public String getAirportLabel() {
        return airportLabel;
    }

    public void setAirportLabel(String airportLabel) {
        this.airportLabel = airportLabel;
    }

    public List<IFlight> getFlights() {
        return flights;
    }

    public void addFlight(IFlight flight) {
        flights.add(flight);
        flight.setCurrentAirportCell(this);
    }

    public void removeFlight(IFlight flight) {
        flights.remove(flight);
    }

    public boolean isLocked() {
        return locked;
    }
    
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public IFlight getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(IFlight flight) {
        this.lockedBy = flight;
        this.locked = (flight != null);
    }
}