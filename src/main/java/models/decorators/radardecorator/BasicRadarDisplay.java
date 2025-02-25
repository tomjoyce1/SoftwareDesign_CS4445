package models.decorators.radardecorator;

import views.ConsoleLogger;

public class BasicRadarDisplay implements RadarDisplay {
    @Override
    public void show() {
        ConsoleLogger.logInfo("Displaying basic radar with aircraft coordinates.");
    }
}
