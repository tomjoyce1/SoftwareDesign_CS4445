package commands.gamecommand;

import commands.Command;
import models.map.AirTrafficMap;
import models.map.takeoff.ScheduledFlight;
import models.map.takeoff.FlightSimulator;
import models.collision.CollisionDetector;
import views.ConsoleLogger;
import views.SimulatorView;

import java.util.List;

public class ClearScheduledFlightsForTakeOffCommand implements Command {
    private final AirTrafficMap airTrafficMap;
    private final List<ScheduledFlight> scheduledFlights;
    private final FlightSimulator flightSimulator;

    public ClearScheduledFlightsForTakeOffCommand(AirTrafficMap airTrafficMap, List<ScheduledFlight> scheduledFlights, SimulatorView view) {
        this.airTrafficMap = airTrafficMap;
        this.scheduledFlights = scheduledFlights;
        this.flightSimulator = new FlightSimulator(airTrafficMap, view);
    }

    @Override
public void execute() {
    ConsoleLogger.logInfo("Clearing scheduled flights, total scheduled: " + scheduledFlights.size());
    if (scheduledFlights.isEmpty()) {
        ConsoleLogger.logStandard("No scheduled flights to process.");
        return;
    }
    
    // For each scheduled flight, attempt to take off (but do not remove them yet)
    for (ScheduledFlight sf : scheduledFlights) {
        if (!sf.getFlight().takeOff()) {
            ConsoleLogger.logWarning("Scheduled flight " + sf.getFlight().getFlightNumber() + " did not take off due to storm conditions.");
        }
    }

    CollisionDetector collisionDetector = new CollisionDetector(airTrafficMap, 0);
    
    while (true) {
        // Process collisions and remove crashed flights.
        collisionDetector.checkAndHandleCollisions(scheduledFlights);
        scheduledFlights.removeIf(sf -> sf.getFlight().getState().equals("Crashed"));
        
        // If no scheduled flights remain, break out.
        if (scheduledFlights.isEmpty()) {
            ConsoleLogger.logWarning("All scheduled flights have either crashed or completed their simulation.");
            break;
        }
        
        // Update positions of scheduled flights.
        boolean allArrived = flightSimulator.updateScheduledFlights(scheduledFlights);
        if (allArrived) {
            // For each remaining flight at its destination, prompt for landing.
            for (ScheduledFlight sf : scheduledFlights) {
                flightSimulator.simulateLandingProcedure(sf.getFlight(), sf.getDestinationRow(), sf.getDestinationCol());
            }
            break;
        }
        
        airTrafficMap.printMap();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            break;
        }
    } 

    scheduledFlights.clear();
    ConsoleLogger.logSuccess("All scheduled flights have been processed.");
    }
}
