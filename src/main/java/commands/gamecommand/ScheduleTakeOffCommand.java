package commands.gamecommand;

import commands.Command;
import models.flight.IFlight;
import models.map.AirTrafficMap;
import models.map.takeoff.ScheduledFlight;
import views.ConsoleLogger;
import views.SimulatorView;
import java.util.List;

public class ScheduleTakeOffCommand implements Command {
    private final IFlight flight;
    private final AirTrafficMap airTrafficMap;
    private final SimulatorView view;
    private final List<ScheduledFlight> scheduledFlights;

    public ScheduleTakeOffCommand(IFlight flight, AirTrafficMap airTrafficMap, SimulatorView view, List<ScheduledFlight> scheduledFlights) {
        this.flight = flight;
        this.airTrafficMap = airTrafficMap;
        this.view = view;
        this.scheduledFlights = scheduledFlights;
    }

    @Override
    public void execute() {
        // Avoid duplicates: check if already scheduled.
        boolean alreadyScheduled = scheduledFlights.stream()
                .anyMatch(sf -> sf.getFlight().equals(flight));
        if (alreadyScheduled) {
            ConsoleLogger.logWarning("Flight " + flight.getFlightNumber() + " is already scheduled for takeoff.");
            return;
        }
        
        // Use AirTrafficMap's findFlightPosition() instead of iterating manually.
        int[] currentPosition = airTrafficMap.findFlightPosition(flight);
        if (currentPosition == null) {
            ConsoleLogger.logError("Flight " + flight.getFlightNumber() + " is not stationed at an airport.");
            return;
        }
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];

        // Use the utility method for destination selection.
        int[] dest = utils.AirportSelectionUtil.selectAirport(airTrafficMap, view, currentRow, currentCol);
        if (dest == null) {
            return;
        }
        int destinationRow = dest[0];
        int destinationCol = dest[1];
        String destinationLabel = airTrafficMap.getCell(destinationRow, destinationCol).getAirportLabel();

        // Check destination availability.
        if (!airTrafficMap.canPlaceFlightAt(flight, destinationRow, destinationCol)) {
            ConsoleLogger.logError("Flight " + flight.getFlightNumber() + " cannot be scheduled for takeoff because destination " 
                    + destinationLabel + " is locked.");
            return;
        }
    
        // Create a ScheduledFlight and add it to the shared list.
        ScheduledFlight scheduledFlight = new ScheduledFlight(
                flight, currentRow, currentCol, destinationRow, destinationCol, destinationLabel);
        scheduledFlights.add(scheduledFlight);
        ConsoleLogger.logSuccess("Flight " + flight.getFlightNumber() + " scheduled for takeoff to " + destinationLabel);
    }
}