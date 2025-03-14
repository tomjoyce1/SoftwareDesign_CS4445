package models.map;

import models.flight.Flight;
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

        // Initialize each cell as non-airport by default.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new MapCell(false);
            }
        }
        // Mark two cells as airports with different labels.
        setAirport(0, 0, "APT-A");
        setAirport(9, 9, "APT-B");
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

    public void placeFlight(int row, int col, Flight flight) {
        MapCell cell = getCell(row, col);
        if (cell.isLocked()) {
            ConsoleLogger.logError("Unable to clear access to " + cell.getAirportLabel() 
                + " until Flight " + flight.getFlightNumber() + " has landed.");
            return;
        }
        cell.addFlight(flight);
    }

    public void removeFlight(int row, int col, Flight flight) {
        MapCell cell = getCell(row, col);
        if (cell.isLocked()) {
            ConsoleLogger.logError("Unable to clear access to " + cell.getAirportLabel() 
                + " until Flight " + flight.getFlightNumber() + " has landed.");
            return;
        }
        cell.removeFlight(flight);
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
                    String flightsDisplay = cell.getFlights().stream()
                            .map(Flight::getFlightNumber)
                            .reduce((a, b) -> a + "," + b)
                            .orElse("");
                    rowOutput.append("[").append(flightsDisplay).append("]");
                } else {
                    rowOutput.append("[   ]");
                }
            }
            System.out.println(rowOutput.toString());
        }
    }

    public int[] findFlightPosition(Flight flight) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                MapCell cell = getCell(i, j);
                if (cell.isAirport() && cell.getFlights().contains(flight)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
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

    public boolean canPlaceFlightAt(Flight flight, int row, int col) {
        MapCell cell = getCell(row, col);
        if (cell.isLocked() && cell.getLockedBy() != null && !cell.getLockedBy().equals(flight)) {
            ConsoleLogger.logError("Unable to clear access to " + cell.getAirportLabel() 
                + " until Flight " + cell.getLockedBy().getFlightNumber() + " has landed.");
            return false;
        }
        return true;
    }
}
