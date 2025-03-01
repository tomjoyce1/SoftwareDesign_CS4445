package models.decorators.radardecorator;

import models.flight.Flight;

//this class helps ensure that the altitude radar displays the correct altitude for each flight type
public class RadarDisplayFactory {
    public static RadarDisplay createRadarDisplay(Flight flight) {
        RadarDisplay radar = new BasicRadarDisplay();
        radar = new AltitudeRadar(radar, flight);        
        return radar;
    }
}
