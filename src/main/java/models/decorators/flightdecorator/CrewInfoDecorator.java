package models.decorators.flightdecorator;

import models.decorators.FlightDecorator;
import models.flight.FlightInterface;

public class CrewInfoDecorator extends FlightDecorator {
    private String pilotName;
    private int crewCount;

    public CrewInfoDecorator(FlightInterface flight, String pilotName, int crewCount) {
        super(flight);
        this.pilotName = pilotName;
        this.crewCount = crewCount;
    }

    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    public int getCrewCount() {
        return crewCount;
    }

    public void setCrewCount(int crewCount) {
        this.crewCount = crewCount;
    }
}