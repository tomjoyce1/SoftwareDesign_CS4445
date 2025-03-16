package models.map.takeoff;

import models.flight.FlightInterface;

public class ScheduledFlight {
    private final FlightInterface flight;
    private final int destinationRow;
    private final int destinationCol;
    private int currentRow;
    private int currentCol;

    public ScheduledFlight(FlightInterface flight, int destinationRow, int destinationCol) {
        this.flight = flight;
        this.destinationRow = destinationRow;
        this.destinationCol = destinationCol;
    }

    public FlightInterface getFlight() {
        return flight;
    }

    public int getDestinationRow() {
        return destinationRow;
    }

    public int getDestinationCol() {
        return destinationCol;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentPosition(int row, int col) {
        this.currentRow = row;
        this.currentCol = col;
    }
}