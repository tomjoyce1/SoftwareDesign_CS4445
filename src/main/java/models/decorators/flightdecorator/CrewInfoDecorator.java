package models.decorators.flightdecorator;

import models.decorators.FlightDecorator;
import models.flight.IFlight;

public class CrewInfoDecorator extends FlightDecorator {
    private String pilotName;
    private int crewCount;

    public CrewInfoDecorator(IFlight flight, String pilotName, int crewCount) {
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

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public void setScheduled(boolean scheduled) {
        decoratedFlight.setScheduled(scheduled);
    }
}