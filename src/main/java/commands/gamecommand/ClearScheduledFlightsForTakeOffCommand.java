package commands.gamecommand;

import commands.Command;
import models.map.AirTrafficMap;
import models.map.takeoff.ScheduledFlight;
import models.map.takeoff.FlightSimulator;
import models.map.takeoff.FlightLandingManager;
import models.collision.CollisionDetector;
import views.ConsoleLogger;
import views.SimulatorView;

import java.util.List;

public class ClearScheduledFlightsForTakeOffCommand implements Command {
    private final AirTrafficMap airTrafficMap;
    private final List<ScheduledFlight> scheduledFlights;
    private final FlightSimulator flightSimulator;
    private final FlightLandingManager landingManager;

    public ClearScheduledFlightsForTakeOffCommand(AirTrafficMap airTrafficMap, List<ScheduledFlight> scheduledFlights, SimulatorView view) {
        this.airTrafficMap = airTrafficMap;
        this.scheduledFlights = scheduledFlights;
        this.flightSimulator = new FlightSimulator(airTrafficMap, view);
        this.landingManager = new FlightLandingManager(airTrafficMap, view);
    }

    @Override
public void execute() {
    ConsoleLogger.logInfo("Clearing scheduled flights, total scheduled: " + scheduledFlights.size());
    if (scheduledFlights.isEmpty()) {
        ConsoleLogger.logStandard("No scheduled flights to process.");
        return;
    }
    
    for (ScheduledFlight sf : scheduledFlights) {
        if (!sf.getFlight().takeOff()) {
            ConsoleLogger.logWarning("Scheduled flight " + sf.getFlight().getFlightNumber() + " did not take off due to storm conditions.");
        }
    }

    CollisionDetector collisionDetector = new CollisionDetector(airTrafficMap, 0);
    
    while (true) {
        collisionDetector.checkAndHandleCollisions(scheduledFlights);
        scheduledFlights.removeIf(sf -> sf.getFlight().getState().equals("Crashed"));
        
        if (scheduledFlights.isEmpty()) {
            ConsoleLogger.logWarning("All scheduled flights have either crashed or completed their simulation.");
            break;
        }
        
        boolean allArrived = flightSimulator.updateScheduledFlights(scheduledFlights);
        if (allArrived) {
            for (ScheduledFlight sf : scheduledFlights) {
                landingManager.simulateLandingProcedure(sf.getFlight(), sf.getDestinationRow(), sf.getDestinationCol());
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
    for (ScheduledFlight sf : scheduledFlights) {
        sf.getFlight().setScheduled(false);
    }
    ConsoleLogger.logSuccess("All scheduled flights have been processed.");
    }
}
