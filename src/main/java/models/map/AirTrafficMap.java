package models.map;

import models.flight.IFlight;
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
        setAirport(6,8, "DUB");
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

    public void placeFlight(int row, int col, IFlight flight) {
        MapCell cell = getCell(row, col);
        cell.addFlight(flight);
    }

    public void removeFlight(int row, int col, IFlight flight) {
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
                            .map(IFlight::getFlightNumber)
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

    public int[] findFlightPosition(IFlight flight) {
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

    public boolean canPlaceFlightAt(IFlight flight, int row, int col) {
        MapCell cell = getCell(row, col);
        if (cell.isLocked() && cell.getLockedBy() != null && !cell.getLockedBy().equals(flight)) {
            if (cell.getLockedBy().getState().equals("Holding")) {
                ConsoleLogger.logError("Unable to take off to " + cell.getAirportLabel() + " because Flight " + cell.getLockedBy().getFlightNumber() + " is holding.");
                return false;
            }
        }
        return true;
    }
}
