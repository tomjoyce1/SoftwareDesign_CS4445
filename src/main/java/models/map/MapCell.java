package models.map;

import models.flight.Flight;
import views.ConsoleLogger;

import java.util.ArrayList;
import java.util.List;

public class MapCell {
    private boolean isAirport;
    private String airportLabel;
    private final List<Flight> flights;
    private boolean locked = false;
    private Flight lockedBy = null;

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

    public List<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        // If cell is locked by a different flight, abort
        if (locked && lockedBy != null && !lockedBy.equals(flight)) {
            ConsoleLogger.logError("Unable to clear access to " + airportLabel + " until Flight " 
                + lockedBy.getFlightNumber() + " has landed.");
            return;
        }
        flights.add(flight);
        // Set the flight's current cell to this cell.
        flight.setCurrentAirportCell(this);
    }

    public void removeFlight(Flight flight) {
        flights.remove(flight);
    }

    public boolean isLocked() {
        return locked;
    }
    
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Flight getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(Flight flight) {
        this.lockedBy = flight;
        this.locked = (flight != null);
    }
}