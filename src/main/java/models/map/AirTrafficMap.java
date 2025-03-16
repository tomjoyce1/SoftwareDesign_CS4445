package models.map;

import models.flight.FlightInterface;
import views.ConsoleLogger;

import java.util.ArrayList;
import java.util.List;

public class AirTrafficMap {
    private final MapCell[][] grid;
    private final int rows;
    private final int cols;

    public AirTrafficMap(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new MapCell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new MapCell(false);
            }
        }
        setAirport(0, 0, "LIM");
        setAirport(6, 8, "DUB");
        setAirport(9, 9, "COR");
    }

    public MapCell getCell(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("Invalid cell coordinates");
        }
        return grid[row][col];
    }

    public void setAirport(int row, int col, String airportLabel) {
        MapCell cell = getCell(row, col);
        cell.setAirport(true);
        cell.setAirportLabel(airportLabel);
    }

    public void placeFlight(int row, int col, FlightInterface flight) {
        getCell(row, col).addFlight(flight);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void printMap() {
        for (int i = 0; i < rows; i++) {
            StringBuilder rowOutput = new StringBuilder();
            for (int j = 0; j < cols; j++) {
                MapCell cell = grid[i][j];
                if (cell.isAirport()) {
                    rowOutput.append("[").append(cell.getAirportLabel()).append("]");
                } else if (!cell.getFlights().isEmpty()) {
                    String flightsDisplay = String.join(",", cell.getFlights().stream()
                            .map(FlightInterface::getFlightNumber)
                            .toArray(String[]::new));
                    rowOutput.append("[").append(flightsDisplay).append("]");
                } else {
                    rowOutput.append("[   ]");
                }
            }
            ConsoleLogger.logStandard(rowOutput.toString());
        }
    }

    public int[] findFlightPosition(FlightInterface flight) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                MapCell cell = getCell(i, j);
                if (cell.isAirport() && cell.getFlights().contains(flight)) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    public List<int[]> getAvailableDestinations(int excludeRow, int excludeCol) {
        List<int[]> available = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == excludeRow && j == excludeCol) continue;
                MapCell cell = getCell(i, j);
                if (cell.isAirport()) {
                    available.add(new int[]{i, j});
                }
            }
        }
        return available;
    }

    public boolean canPlaceFlightAt(FlightInterface flight, int row, int col) {
        MapCell cell = getCell(row, col);
        FlightInterface lockedBy = cell.getLockedBy();

        if (cell.isLocked() && lockedBy != null && !lockedBy.equals(flight) && "Holding".equals(lockedBy.getState())) {
            ConsoleLogger.logError("Unable to take off to " + cell.getAirportLabel() + " because Flight " + lockedBy.getFlightNumber() + " is holding.");
            return false;
        }

        return true;
    }
}