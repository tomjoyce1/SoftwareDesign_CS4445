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
        if (!flight.getState().equals("In the air")) {
            ConsoleLogger.logInfo("Altitude Radar: Aircraft is not in flight.");
            return;
        }
        ConsoleLogger.logInfo("Altitude Radar: Flying at an altitude of " + flight.getAltitude());
    }
}
