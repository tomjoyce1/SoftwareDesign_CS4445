package commands.gamecommand;

import commands.Command;
import models.map.AirTrafficMap;
import models.map.takeoff.ScheduledFlight;
import models.map.takeoff.FlightSimulator;
import models.collision.CollisionDetector;
import views.ConsoleLogger;
import views.SimulatorView;
import java.util.Iterator;

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
            ConsoleLogger.logWarning("Scheduled flight " + sf.getFlight().getFlightNumber() 
                    + " did not take off due to storm conditions.");
        }
    }
    
    CollisionDetector collisionDetector = new CollisionDetector(airTrafficMap, 0);
    
    // Run simulation loop until all scheduled flights have reached their destination.
    while (true) {
        // If any collision occurs, exit the loop.
        if (collisionDetector.checkAndHandleCollisions(scheduledFlights)) {
            break;
        }
        
        // Update positions of scheduled flights.
        if (flightSimulator.updateScheduledFlights(scheduledFlights)) {
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
    
    // Now that all scheduled flights have been simulated (and landed), clear the list.
    scheduledFlights.clear();
    ConsoleLogger.logSuccess("\"All scheduled flights have been removed due to adverse weather conditions or a crash.");
}
}
