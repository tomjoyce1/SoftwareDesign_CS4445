package commands.flightcommand;

import commands.Command;
import models.flight.FlightInterface;
import models.map.AirTrafficMap;
import models.map.takeoff.ScheduledFlight;
import views.ConsoleLogger;
import views.SimulatorView;
import java.util.List;

public class ScheduleTakeOffCommand implements Command {
    private final FlightInterface flight;
    private final AirTrafficMap airTrafficMap;
    private final SimulatorView view;
    private final List<ScheduledFlight> scheduledFlights;
    private static final String FLIGHTPREFIX = "Flight ";


    public ScheduleTakeOffCommand(FlightInterface flight, AirTrafficMap airTrafficMap, SimulatorView view, List<ScheduledFlight> scheduledFlights) {
        this.flight = flight;
        this.airTrafficMap = airTrafficMap;
        this.view = view;
        this.scheduledFlights = scheduledFlights;
    }

    @Override
    public void execute() {
        if ("Crashed".equals(flight.getState())) {
            ConsoleLogger.logError("Flight " + flight.getFlightNumber() + " has crashed and cannot take off.");
            return;
        }
        boolean alreadyScheduled = scheduledFlights.stream()
                .anyMatch(sf -> sf.getFlight().equals(flight));
        if (alreadyScheduled) {
            ConsoleLogger.logWarning(FLIGHTPREFIX + flight.getFlightNumber() + " is already scheduled for takeoff.");
            ConsoleLogger.logWarning(FLIGHTPREFIX + flight.getFlightNumber() + " is already scheduled for takeoff.");
            return;
        }
        
        int[] currentPosition = airTrafficMap.findFlightPosition(flight);
        if (currentPosition == null) {
            ConsoleLogger.logError(FLIGHTPREFIX + flight.getFlightNumber() + " is not stationed at an airport.");
            ConsoleLogger.logError(FLIGHTPREFIX + flight.getFlightNumber() + " is not stationed at an airport.");
            return;
        }
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];

        int[] dest = utils.AirportSelectionUtil.selectAirport(airTrafficMap, view, currentRow, currentCol);
        if (dest == null) {
            return;
        }
        int destinationRow = dest[0];
        int destinationCol = dest[1];
        String destinationLabel = airTrafficMap.getCell(destinationRow, destinationCol).getAirportLabel();

        if (!airTrafficMap.canPlaceFlightAt(flight, destinationRow, destinationCol)) {
            ConsoleLogger.logError(FLIGHTPREFIX + flight.getFlightNumber() + " cannot be scheduled for takeoff because destination " 
                    + destinationLabel + " is locked.");
            return;
        }
    
        ScheduledFlight scheduledFlight = new ScheduledFlight(flight, currentRow, currentCol, destinationRow, destinationCol, destinationLabel);        flight.setScheduled(true);
        scheduledFlights.add(scheduledFlight);
        ConsoleLogger.logSuccess(FLIGHTPREFIX + flight.getFlightNumber() + " scheduled for takeoff to " + destinationLabel);
    }
}