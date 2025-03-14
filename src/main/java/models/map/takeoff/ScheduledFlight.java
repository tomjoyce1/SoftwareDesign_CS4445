package models.map.takeoff;

import models.flight.IFlight;

public class ScheduledFlight {
    private final IFlight flight;
    private final int sourceRow;
    private final int sourceCol;
    private final int destinationRow;
    private final int destinationCol;
    private int currentRow;
    private int currentCol;
    private final String destinationAirportLabel;

    public ScheduledFlight(IFlight flight, int sourceRow, int sourceCol, int destinationRow, int destinationCol, String destinationAirportLabel) {
        this.flight = flight;
        this.sourceRow = sourceRow;
        this.sourceCol = sourceCol;
        this.destinationRow = destinationRow;
        this.destinationCol = destinationCol;
        this.destinationAirportLabel = destinationAirportLabel;
        this.currentRow = sourceRow;
        this.currentCol = sourceCol;
    }
    
    public IFlight getFlight() {
        return flight;
    }

    public int getSourceRow() {
        return sourceRow;
    }

    public int getSourceCol() {
        return sourceCol;
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

    public String getDestinationAirportLabel() {
        return destinationAirportLabel;
    }
}