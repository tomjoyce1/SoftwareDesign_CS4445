package models;

import models.flight.IFlight;
import models.map.AirTrafficMap;
import models.map.takeoff.ScheduledFlight;
import views.ConsoleLogger;
import weatherpubsub.WeatherStation;
import bookmarks.InterceptorDispatcher;
import bookmarks.LoggingInterceptor;

import java.util.ArrayList;
import java.util.List;

public class SimulatorModel {
    private final WeatherStation weatherStation;
    private final List<IFlight> flights;
    private final InterceptorDispatcher dispatcher;
    private final AirTrafficMap airTrafficMap;
    private final List<ScheduledFlight> scheduledFlights = new ArrayList<>();

    public SimulatorModel() {
        this.weatherStation = new WeatherStation();
        this.flights = new ArrayList<>();
        this.dispatcher = new InterceptorDispatcher();
        dispatcher.addInterceptor(new LoggingInterceptor());
        this.airTrafficMap = new AirTrafficMap(12, 12);
    }

    public WeatherStation getWeatherStation() {
        return weatherStation;
    }

    public List<IFlight> getFlights() {
        return flights;
    }

    public InterceptorDispatcher getDispatcher() {
        return dispatcher;
    }

    public AirTrafficMap getAirTrafficMap() {
        return airTrafficMap;
    }

    public List<ScheduledFlight> getScheduledFlights() {
        return scheduledFlights;
    }
}