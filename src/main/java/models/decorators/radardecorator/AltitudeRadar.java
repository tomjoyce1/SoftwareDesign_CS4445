package models.decorators.radardecorator;

import models.flight.Flight;
import views.ConsoleLogger;

public class AltitudeRadar extends RadarDecorator {
    private Flight flight;

    public AltitudeRadar(RadarDisplay radar, Flight flight) {
        super(radar);
        this.flight = flight;
    }

    @Override
    public void show() {
        super.show();
        displayAdditionalData();
    }

    private void displayAdditionalData() {

        if(!flight.getState().equals("In the air")) {
            ConsoleLogger.logInfo("Altitude Radar: Aircraft is not in flight.");
            return;
        }

        if(flight.getType().equals("Cargo Flight")) {
            ConsoleLogger.logInfo("Altitude Radar: Flying at an altitude of 20,000ft.");
        } else if(flight.getType().equals("Passenger Flight")) {
        ConsoleLogger.logInfo("Altitude Radar: Flying at an altitude of 30,000ft.");
        } else if(flight.getType().equals("Private Flight")) {
            ConsoleLogger.logInfo("Altitude Radar: Flying at an altitude of 40,000ft.");
        } else if(flight.getType().equals("Military Flight")) {
            ConsoleLogger.logInfo("Altitude Radar: Flying at an altitude of 50,000ft.");
        }
    }
}
