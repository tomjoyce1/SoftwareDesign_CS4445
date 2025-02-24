package models;

import models.flight.Flight;
import weatherpubsub.WeatherStation;
import bookmarks.InterceptorDispatcher;
import bookmarks.LoggingInterceptor;

import java.util.ArrayList;
import java.util.List;

public class SimulatorModel {
    private final WeatherStation weatherStation;
    private final List<Flight> flights;
    private final InterceptorDispatcher dispatcher;

    public SimulatorModel() {
        this.weatherStation = new WeatherStation();
        this.flights = new ArrayList<>();
        this.dispatcher = new InterceptorDispatcher();
        dispatcher.addInterceptor(new LoggingInterceptor());
    }

    public WeatherStation getWeatherStation() {
        return weatherStation;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public InterceptorDispatcher getDispatcher() {
        return dispatcher;
    }
}