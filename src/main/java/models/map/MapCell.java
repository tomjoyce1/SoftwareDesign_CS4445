package models.map;

import models.flight.FlightInterface;

import java.util.ArrayList;
import java.util.List;

public class MapCell {
    private boolean isAirport;
    private String airportLabel;
    private final List<FlightInterface> flights = new ArrayList<>();
    private boolean locked = false;
    private FlightInterface lockedBy = null;

    public MapCell(boolean isAirport) {
        this.isAirport = isAirport;
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

    public List<FlightInterface> getFlights() {
        return flights;
    }

    public void addFlight(FlightInterface flight) {
        flights.add(flight);
        flight.setCurrentAirportCell(this);
    }

    public void removeFlight(FlightInterface flight) {
        flights.remove(flight);
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public FlightInterface getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(FlightInterface flight) {
        this.lockedBy = flight;
        this.locked = (flight != null);
    }
}