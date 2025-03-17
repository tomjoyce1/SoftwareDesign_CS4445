package models.map.takeoff;

import models.flight.FlightInterface;
import models.map.AirTrafficMap;
import views.SimulatorView;
import java.util.List;

public class FlightSimulator {
    private final FlightTakeOffManager takeOffManager;

    public FlightSimulator(AirTrafficMap airTrafficMap, SimulatorView view) {

        this.takeOffManager = new FlightTakeOffManager(airTrafficMap, view);
    }
    
    public void simulateTakeOff(FlightInterface flight, int destRow, int destCol) {
        takeOffManager.simulateTakeOff(flight, destRow, destCol);
    }
    
    public boolean updateScheduledFlights(List<ScheduledFlight> scheduledFlights) {
        return takeOffManager.updateScheduledFlights(scheduledFlights);
    }
}